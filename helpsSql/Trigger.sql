-- Drop existing trigger and functions related to rider position calculation
DROP TRIGGER IF EXISTS trigger_calculate_position ON round_result;
DROP FUNCTION IF EXISTS calculate_rider_position();
DROP FUNCTION IF EXISTS calculate_rider_position_after(BIGINT);

-- Create the function to handle position recalculation (if you need it back)
CREATE OR REPLACE FUNCTION calculate_rider_position()
RETURNS TRIGGER AS $$
BEGIN
    IF (TG_OP = 'INSERT' OR TG_OP = 'DELETE') THEN
        PERFORM calculate_rider_position_after(NEW.round_id);
RETURN NEW;
END IF;

RETURN NULL;
END;
$$ LANGUAGE plpgsql;


CREATE TRIGGER trigger_calculate_position
    AFTER INSERT OR DELETE ON round_result
    FOR EACH ROW
    EXECUTE FUNCTION calculate_rider_position();
