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
