-- Drop existing trigger and function
DROP TRIGGER IF EXISTS trigger_calculate_position ON round_result;
DROP FUNCTION IF EXISTS calculate_rider_position();
DROP FUNCTION IF EXISTS calculate_rider_position_after(BIGINT);

-- Create the main function to handle position recalculation
CREATE OR REPLACE FUNCTION calculate_rider_position()
RETURNS TRIGGER AS $$
BEGIN
    -- Recalculate positions based on operation type (insert/update/delete)
    IF (TG_OP = 'INSERT' OR TG_OP = 'UPDATE') THEN
        -- Call the recalculation function after insert or update
        PERFORM calculate_rider_position_after(NEW.round_id);
RETURN NEW;
ELSIF (TG_OP = 'DELETE') THEN
        -- Call the recalculation function after delete
        PERFORM calculate_rider_position_after(OLD.round_id);
RETURN OLD;
END IF;

RETURN NULL;  -- Return null for unsupported operations (though all are covered)
END;
$$ LANGUAGE plpgsql;

-- Create the function to recalculate rider positions after an update
CREATE OR REPLACE FUNCTION calculate_rider_position_after(p_round_id BIGINT)
RETURNS VOID AS $$
BEGIN
    -- Temporarily disable the trigger to avoid recursion
    PERFORM pg_catalog.set_config('session_replication_role', 'replica', true);

    -- Recalculate rider positions for the specified round
UPDATE round_result AS rr
SET position = subq.new_pos
    FROM (
        SELECT
            rider_id,
            ROW_NUMBER() OVER (ORDER BY duration) AS new_pos
        FROM round_result
        WHERE round_id = p_round_id  -- Use the function parameter p_round_id
    ) AS subq
WHERE rr.round_id = p_round_id  -- Qualify rr to refer to the outer table
  AND rr.rider_id = subq.rider_id;

-- Re-enable the trigger after the update
PERFORM pg_catalog.set_config('session_replication_role', 'origin', true);
END;
$$ LANGUAGE plpgsql;

-- Create the trigger to invoke the recalculation function after changes
CREATE TRIGGER trigger_calculate_position
    AFTER INSERT OR UPDATE OR DELETE ON round_result
    FOR EACH ROW
    EXECUTE FUNCTION calculate_rider_position();




-- Drop existing trigger and function if they exist
DROP TRIGGER IF EXISTS update_general_results_trigger ON rounds;
DROP FUNCTION IF EXISTS update_general_results();

-- Create the updated function with proper type handling
CREATE OR REPLACE FUNCTION update_general_results()
RETURNS TRIGGER AS $$
BEGIN
    -- Only proceed if the round is being closed (is_closed changes to true)
    IF NEW.is_closed = true AND (OLD.is_closed IS NULL OR OLD.is_closed = false) THEN
        -- Insert or update general results for each rider in this round
        INSERT INTO general_results (competition_id, rider_id, general_time, range)
SELECT
    r.competition_id,
    rr.rider_id,
    COALESCE(
            (SELECT gr.general_time FROM general_results gr
             WHERE gr.competition_id = r.competition_id
               AND gr.rider_id = rr.rider_id), 0) + rr.duration AS new_general_time,
    0  -- temporary rank, will be updated
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

-- Create the trigger
CREATE TRIGGER update_general_results_trigger
    AFTER UPDATE OF is_closed ON rounds
    FOR EACH ROW
    EXECUTE FUNCTION update_general_results();
