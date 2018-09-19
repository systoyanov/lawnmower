package bg.proxiad.lawnmower.task;

import static java.util.stream.StreamSupport.stream;

import java.util.function.Consumer;
import java.util.function.Supplier;

import bg.proxiad.lawnmower.Lawn;
import bg.proxiad.lawnmower.MowerStatus;
import bg.proxiad.lawnmower.config.MowerConfig;
import bg.proxiad.lawnmower.config.MowingTaskConfig;

/**
 * Class representing the task of mowing a predefined lawn with N number of mowers. 
 * 
 * @author svetozar
 *
 */
public class MowingTask {
	
	private Supplier<MowingTaskConfig> configSupplier; 
	private Consumer<MowerStatus> stateConsumer;
	
	/**
	 * Constructor.
	 * 
	 * @param configSupplier	not null, supplier of the task configuration
	 * @param stateConsumer	not null, the consumer which will handle the task result
	 */
	MowingTask(final Supplier<MowingTaskConfig> configSupplier, final Consumer<MowerStatus> stateConsumer) {
		this.configSupplier = configSupplier;
		this.stateConsumer = stateConsumer;
	}

	/**
	 * Executes the mowing tasks by applying the commands provided in configuration in sequential order.
	 * 
	 */
	public void execute() {
		final MowingTaskConfig taskConfig = configSupplier.get();
		final Lawn lawn = new Lawn(taskConfig.getTopRightCorner());
		final Iterable<MowerConfig> iterable = () -> taskConfig.getMowersConfig();
		
		stream(iterable.spliterator(), false)
			.map(mowerConfig -> lawn.add(mowerConfig)
					.execute(mowerConfig.getCommands()))
			.forEach(stateConsumer);
	}

}
