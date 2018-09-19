package bg.proxiad.lawnmower.task;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.function.Consumer;
import java.util.function.Supplier;

import bg.proxiad.lawnmower.MowerCommand;
import bg.proxiad.lawnmower.MowerStatus;
import bg.proxiad.lawnmower.config.MowingTaskConfig;
import bg.proxiad.lawnmower.config.InputStreamMowingTaskConfigSupplier;

/**
 * Class containing helper factory methods to initiate the {@link MowingTask}.
 * 
 * @author svetozar
 *
 */
public final class MowingTaskFactory {

	private MowingTaskFactory() {
	}

	/**
	 * Factory method initiating a {@link MowingTask} configured by a text
	 * instructions read from an {@link InputStream} without executing the actual
	 * process. <br>
	 * <br>
	 * IMPORTANT: In order to execute the process you must invoke the
	 * {@link MowingTask#execute()} method of the object returned by this factory
	 * method. The caller has the responsibility to close the streams provided as
	 * parameters after the process is executed. <br>
	 * Input format:
	 * 
	 * <pre>
	 * 5 5 	(lawn size X, Y)
	 * 1 2 N 	(first mower coordinates X, Y and direction, please check {@link Direction}) 
	 * LFLFLFF 	(first commands of the mower, please check {@link MowerCommand})
	 * 3 3 E 	(second mower coordinates X, Y and direction) 
	 * FFRFRRF	(second commands of the mower)
	 * ... 	(additional mower coordinates and commands)
	 * </pre>
	 * 
	 * Output format:
	 * 
	 * <pre>
	 * 1 3 N (first mower coordinates X, Y and direction)
	 * 5 1 E (second mower coordinates X, Y and direction)
	 * ... 	(additional mower coordinates)
	 * </pre>
	 * 
	 * 
	 * @param input, not null
	 * @param output, not null
	 * @return not null, the mowing process build with the given input
	 */
	public static MowingTask create(final InputStream input, final OutputStream output) {
		final PrintStream pOutput = new PrintStream(output);
		final Supplier<MowingTaskConfig> fileParser = new InputStreamMowingTaskConfigSupplier(input);
		final Consumer<MowerStatus> statusPrinter = (state) -> pOutput.println(formattedString(state));
		return create(fileParser, statusPrinter);

	}

	/**
	 * Factory method initiating a {@link MowingTask} configured by a configuration
	 * supplier and status consumer.
	 * 
	 * @param configSupplier not null
	 * @param stateConsumer  not null
	 * @return not null, the mowing process build with the given input
	 */
	public static MowingTask create(final Supplier<MowingTaskConfig> configSupplier,
			final Consumer<MowerStatus> stateConsumer) {
		return new MowingTask(configSupplier, stateConsumer);

	}

	private static String formattedString(MowerStatus phase) {
		return String.format("%s %s %s", phase.getPosition().getX(), phase.getPosition().getY(),
				phase.getOrientation().code());
	}
}
