DROP TRIGGER IF EXISTS trigger_calculate_position ON round_result;
DROP FUNCTION IF EXISTS calculate_rider_position();
DROP FUNCTION IF EXISTS calculate_rider_position_after(BIGINT);

CREATE OR REPLACE FUNCTION calculate_rider_position()
RETURNS TRIGGER AS $$
BEGIN
    IF (TG_OP = 'INSERT' OR TG_OP = 'UPDATE') THEN
        PERFORM calculate_rider_position_after(NEW.round_id);
RETURN NEW;
ELSIF (TG_OP = 'DELETE') THEN
        PERFORM calculate_rider_position_after(OLD.round_id);
RETURN OLD;
END IF;

RETURN NULL;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION calculate_rider_position_after(p_round_id BIGINT)
RETURNS VOID AS $$
BEGIN
    PERFORM pg_catalog.set_config('session_replication_role', 'replica', true);

UPDATE round_result AS rr
SET position = subq.new_pos
    FROM (
        SELECT
            rider_id,
            ROW_NUMBER() OVER (ORDER BY duration) AS new_pos
        FROM round_result
        WHERE round_id = p_round_id
    ) AS subq
WHERE rr.round_id = p_round_id
  AND rr.rider_id = subq.rider_id;

PERFORM pg_catalog.set_config('session_replication_role', 'origin', true);
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trigger_calculate_position
    AFTER INSERT OR UPDATE OR DELETE ON round_result
    FOR EACH ROW
    EXECUTE FUNCTION calculate_rider_position();




DROP TRIGGER IF EXISTS update_general_results_trigger ON rounds;
DROP FUNCTION IF EXISTS update_general_results();

CREATE OR REPLACE FUNCTION update_general_results()
RETURNS TRIGGER AS $$
BEGIN
    IF NEW.is_closed = true AND (OLD.is_closed IS NULL OR OLD.is_closed = false) THEN
        INSERT INTO general_results (competition_id, rider_id, general_time, range)
SELECT
    r.competition_id,
    rr.rider_id,
    COALESCE(
            (SELECT gr.general_time FROM general_results gr
             WHERE gr.competition_id = r.competition_id
               AND gr.rider_id = rr.rider_id), 0) + rr.duration AS new_general_time,
    0
FROM round_result rr
         JOIN rounds r ON r.id = rr.round_id
WHERE r.id = NEW.id
    ON CONFLICT (competition_id, rider_id) DO UPDATE
                                                  SET general_time = EXCLUDED.general_time;

-- Update rankings for all riders in this competition
WITH ranked_results AS (
    SELECT
        competition_id,
        rider_id,
        general_time,
        RANK() OVER (
                    PARTITION BY competition_id
                    ORDER BY general_time
                ) as new_rank
    FROM general_results
    WHERE competition_id = (SELECT competition_id FROM rounds WHERE id = NEW.id)
)
UPDATE general_results gr
SET range = rr.new_rank
    FROM ranked_results rr
WHERE gr.competition_id = rr.competition_id
  AND gr.rider_id = rr.rider_id;
END IF;
RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER update_general_results_trigger
    AFTER UPDATE OF is_closed ON rounds
    FOR EACH ROW
    EXECUTE FUNCTION update_general_results();
