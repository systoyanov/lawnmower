package bg.proxiad.lawnmower.config;

import java.util.Iterator;

import bg.proxiad.lawnmower.Position;

/**
 * Class which defines the configuration properties of the mowing task.
 * 
 * @author svetozar
 *
 */
public final class MowingTaskConfig {
	private final Position topRightCorner;
	private final Iterator<MowerConfig> mowerIterator;

	/**
	 * Constructor.
	 * 
	 * @param topRightCorner not null, the top right corner of the lawn
	 * @param mowerIterator  not null, a iterator which provides a collection of
	 *                       mower configuration that should be placed on the lawn
	 */
	public MowingTaskConfig(final Position topRightCorner, final Iterator<MowerConfig> mowerIterator) {
		this.topRightCorner = topRightCorner;
		this.mowerIterator = mowerIterator;
	}

	/**
	 * Get the top right corner of the lawn that has to be mowed.
	 * 
	 * @return not null
	 */
	public Position getTopRightCorner() {
		return topRightCorner;
	}

	/**
	 * Get iterator over the set of configurations for the mowers that have to be
	 * used for mowing.
	 * 
	 * @return not null
	 */
	public Iterator<MowerConfig> getMowersConfig() {
		return mowerIterator;
	}

}
