package bg.proxiad.lawnmower;

import bg.proxiad.lawnmower.config.MowerConfig;

/**
 * Object representation of a lawn that has to be mowed. The object is designed
 * to be immutable and thread safe.
 * 
 * @author svetozar
 *
 */
public class Lawn {
	private final Position topRightCorner;

	/**
	 * Constructor.
	 * 
	 * @param topRightCorner not null, coordinates of the top right corner
	 */
	public Lawn(final Position topRightCorner) {
		this.topRightCorner = topRightCorner;
	}

	/**
	 * Verify that a mower can move to the position specified as input of the
	 * method.
	 * 
	 * @param position not null
	 * 
	 * @return true if the position is valid on the given lawn, false otherwise
	 */
	public boolean isValidPosition(final Position position) {
		return position.getX() >= 0 && position.getX() <= topRightCorner.getX() && position.getY() >= 0
				&& position.getY() <= topRightCorner.getY();
	}

	/**
	 * Creates and adds a mower on to the lawn.
	 * 
	 * @param mowerConfig not null, configuration describing the mower to be added
	 * 
	 * @return not null, an object allowing commands to be executed with the mower
	 *         on the lawn
	 */
	public LawnMower add(final MowerConfig mowerConfig) {
		final Position initalPosition = mowerConfig.getInitalPosition();
		if (!isValidPosition(initalPosition)) {
			throw new IllegalArgumentException(String
					.format("Invalid initial mower position %s %s", initalPosition.getX(), initalPosition.getY()));
		}
		return new LawnMower(mowerConfig, this);
	}
}
