package bg.proxiad.lawnmower.operation;

import static bg.proxiad.lawnmower.MowerCommand.FORWARD;
import static bg.proxiad.lawnmower.MowerCommand.LEFT;
import static bg.proxiad.lawnmower.MowerCommand.RIGHT;

import java.util.Map;

import bg.proxiad.lawnmower.MowerCommand;
import bg.proxiad.lawnmower.MowerStatus;

/**
 * Interface representing an available operation of the mower.
 * 
 * @author svetozar
 *
 */
public interface MowerOperation {
	public static final Map<MowerCommand, MowerOperation> OPERATION_MAP = Map.of(
			LEFT, new LeftMowerOperation(),
			RIGHT, new RightMowerOperation(),
			FORWARD, new ForwardMowerOperation());
	
	/**
	 * Applies the mower operation to the current mowing state. As a result of the
	 * operation a new mowing state might be constructed if the operation is applicable 
	 * to the current state.
	 * 
	 * @param state	not null, state of the mowing process before the operation is applied
	 * @return	not null, state of the mowing process after the operation is applied
	 */
	public MowerStatus applyTo(MowerStatus state);
	
	/**
	 * Factory method which creates the operation corresponding to given {@link MowerCommand}.
	 * 
	 * @param operationType not null, the operation type for the operation that need to be constructed 
	 * @return not null, the corresponding operation
	 */
	public static MowerOperation forType(MowerCommand operationType) {
		return OPERATION_MAP.get(operationType);
	}
	
}
