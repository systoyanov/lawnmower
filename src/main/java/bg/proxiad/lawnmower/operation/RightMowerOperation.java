package bg.proxiad.lawnmower.operation;

import static bg.proxiad.lawnmower.Orientation.EAST;
import static bg.proxiad.lawnmower.Orientation.NORTH;
import static bg.proxiad.lawnmower.Orientation.SOUTH;
import static bg.proxiad.lawnmower.Orientation.WEST;

import java.util.Map;

import bg.proxiad.lawnmower.Orientation;
import bg.proxiad.lawnmower.MowerCommand;
import bg.proxiad.lawnmower.MowerStatus;

/**
 * Implementation of the {@link MowerCommand#RIGHT}. 
 * The class is thread safe.
 * 
 * @author svetozar
 *
 */
public class RightMowerOperation implements MowerOperation {
	private static final Map<Orientation, Orientation> NEXT_ORIENTATION = Map.of(
			EAST, SOUTH,
			SOUTH, WEST,
			WEST, NORTH,
			NORTH, EAST);
	
	/*
	 * (non-Javadoc)
	 * @see lawnmower.operations.MowerOperation#applyTo(lawnmower.MowingState)
	 */
	public MowerStatus applyTo(MowerStatus currentState) {
		return new MowerStatus(currentState.getPosition().getX(), currentState.getPosition().getY(), 
				NEXT_ORIENTATION.get(currentState.getOrientation()));
	}

}
