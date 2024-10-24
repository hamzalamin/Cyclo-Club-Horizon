DROP TRIGGER IF EXISTS trigger_calculate_position ON round_result;
DROP FUNCTION IF EXISTS calculate_rider_position();

-- Create function to handle all position updates
CREATE OR REPLACE FUNCTION calculate_rider_position()
RETURNS TRIGGER AS $$
BEGIN
    IF (TG_OP = 'INSERT') THEN
UPDATE round_result
SET position = position + 1
WHERE round_id = NEW.round_id
  AND duration > NEW.duration;

-- Then calculate the correct position for the new record
SELECT COUNT(*) + 1 INTO NEW.position
FROM round_result
WHERE round_id = NEW.round_id
  AND duration < NEW.duration;

RETURN NEW;
END IF;

    -- For UPDATE
    IF (TG_OP = 'UPDATE') THEN
        IF NEW.duration != OLD.duration THEN
UPDATE round_result
SET position = null
WHERE round_id = NEW.round_id
  AND rider_id = NEW.rider_id;

UPDATE round_result
SET position = subq.new_pos
    FROM (
                SELECT
                    rider_id,
                    ROW_NUMBER() OVER (ORDER BY duration) AS new_pos
                FROM round_result
                WHERE round_id = NEW.round_id
                  AND rider_id != NEW.rider_id
            ) AS subq
WHERE round_result.round_id = NEW.round_id
  AND round_result.rider_id = subq.rider_id;

-- Calculate new position for the updated record
SELECT COUNT(*) + 1 INTO NEW.position
FROM round_result
WHERE round_id = NEW.round_id
  AND duration < NEW.duration;
END IF;

RETURN NEW;
END IF;

    -- For DELETE
    IF (TG_OP = 'DELETE') THEN
UPDATE round_result
SET position = position - 1
WHERE round_id = OLD.round_id
  AND position > OLD.position;

RETURN OLD;
END IF;

RETURN NULL;
END;
$$ LANGUAGE plpgsql;

-- Create BEFORE trigger
CREATE TRIGGER trigger_calculate_position
    BEFORE INSERT OR UPDATE OR DELETE ON round_result
    FOR EACH ROW
    EXECUTE FUNCTION calculate_rider_position();
