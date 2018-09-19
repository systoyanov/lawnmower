package bg.proxiad.lawnmower;

import java.util.List;

import bg.proxiad.lawnmower.config.MowerConfig;
import bg.proxiad.lawnmower.operation.MowerOperation;
/**
 * Object representation of a mower placed on a lawn.
 *  
 * @author svetozar
 *
 */
public class LawnMower{
	private MowerStatus currentState;
	private final Lawn lawn;

	/**
	 * Constructor.
	 * @param config	not null, configuration
	 * @param lawn	not null, the mawn on which the mower operate
	 */
	public LawnMower(final MowerConfig config, final Lawn lawn) {
		this.currentState = new MowerStatus(config.getInitalPosition(), config.getInitialOrientation());
		this.lawn = lawn;
	}

	/**
	 * Execute the provided commands sequentially in the order given.
	 * 
	 * @param commands	not null, may be empty
	 * 
	 * @return	not null, the mower status after the commands are executed
	 * 
	 */
	public MowerStatus execute(final List<MowerCommand> commands) {
		for (MowerCommand command : commands) {
			final MowerStatus nextState = MowerOperation.forType(command).applyTo(currentState);
			currentState = lawn.isValidPosition(nextState.getPosition()) ? nextState : currentState;
		}
		return currentState;
	}

}
