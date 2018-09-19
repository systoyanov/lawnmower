package bg.proxiad.lawnmower.config;

import static java.util.Collections.unmodifiableList;

import java.util.List;

import bg.proxiad.lawnmower.MowerCommand;
import bg.proxiad.lawnmower.Orientation;
import bg.proxiad.lawnmower.Position;

/**
 * Class which defines the configuration properties of a mower that can be placed on a lawn.
 * 
 * @author svetozar
 *
 */
public final class MowerConfig {
	private final Position initalPosition;
	private final Orientation initialOrientation;
	private final List<MowerCommand> commands;

	/**
	 * Constructor.
	 * 
	 * @param initalPosition	not null, the initial position of the mower
	 * @param initialOrientation	not null, the initial orientation 
	 * @param commands	not null, list of commands to executed by the mower
	 */
	public MowerConfig(final Position initalPosition, final Orientation initialOrientation,
			final List<MowerCommand> commands) {
		this.initalPosition = initalPosition;
		this.initialOrientation = initialOrientation;
		this.commands = unmodifiableList(commands);
	}

	/**
	 * Get the initial position of the mower on the lawn.
	 * 
	 * @return	not null
	 */
	public Position getInitalPosition() {
		return initalPosition;
	}

	/**
	 * Get the initial orientation of the mower on the lawn.
	 * 
	 * @return	not null
	 */
	public Orientation getInitialOrientation() {
		return initialOrientation;
	}

	/**
	 * Get the list of commands to be executed by the mower.
	 * 
	 * @return	not null
	 */
	public List<MowerCommand> getCommands() {
		return commands;
	}

}
