package bg.proxiad.lawnmower;

import java.util.Optional;
import java.util.stream.Stream;
/**
 * Enum which represents the different commands supported by a mower.
 * 
 * @author svetozar
 *
 */
public enum MowerCommand {
	LEFT('L'), RIGHT('R'), FORWARD('F');

	private final char code;

	private MowerCommand(final char code) {
		this.code = code;
	}

	/**
	 * Returns the enum constant of corresponding to the code.
	 * 
	 * @param code not null the code of the command
	 * @return not null, the enum constant corresponding to the code
	 * @throws IllegalArgumentException if the code is unknown
	 */
	public static MowerCommand valueOf(final char code) {
		final Optional<MowerCommand> command = Stream.of(values())
				.filter(x -> x.code == code)
				.findFirst();
		if(!command.isPresent()) {
			throw new IllegalArgumentException(String.format("Value '%s' is invalid command", code));
		}
		return command.get();
	}

}