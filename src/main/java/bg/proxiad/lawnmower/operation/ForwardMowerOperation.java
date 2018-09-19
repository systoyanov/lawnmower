package bg.proxiad.lawnmower.operation;

import static bg.proxiad.lawnmower.Orientation.EAST;
import static bg.proxiad.lawnmower.Orientation.NORTH;
import static bg.proxiad.lawnmower.Orientation.SOUTH;
import static bg.proxiad.lawnmower.Orientation.WEST;

import java.util.Map;

import bg.proxiad.lawnmower.MowerCommand;
import bg.proxiad.lawnmower.MowerStatus;
import bg.proxiad.lawnmower.Orientation;
/**
 * Implementation of the {@link MowerCommand#FORWARD}. 
 * The class is thread safe.
 * 
 * @author svetozar
 *
 */
public final class ForwardMowerOperation implements MowerOperation {
	private Map<Orientation, Transition> result = Map.of(
			EAST, Transition.of(1, 0),
			SOUTH, Transition.of(0, -1),
			WEST, Transition.of(-1, 0),
			NORTH, Transition.of(0, 1));
	
	/*
	 * (non-Javadoc)
	 * @see lawnmower.operations.MowerOperation#applyTo(lawnmower.MowingState)
	 */
	@Override
	public MowerStatus applyTo(MowerStatus currentState) {
		final Transition transition = result.get(currentState.getOrientation());
		final int newPosX = currentState.getPosition().getX() + transition.getDeltaX();
		final int newPosY = currentState.getPosition().getY() + transition.getDeltaY();
		
		return new MowerStatus(newPosX, newPosY, currentState.getOrientation());
	}
	
	/**
	 * Helper class representing a transition parameters that have to 
	 * be applied to the current coordinates.
	 * 
	 * @author svetozar
	 *
	 */
	private static class Transition {
		private int deltaX, deltaY;
		
		public Transition(int deltaX, int deltaY) {
			this.deltaX = deltaX;
			this.deltaY = deltaY;
		}
		
		public int getDeltaX() {
			return deltaX;
		}

		public int getDeltaY() {
			return deltaY;
		}

		public static Transition of(int deltaX, int deltaY) {
			return new Transition(deltaX, deltaY);
		}
	}

}
