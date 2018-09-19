package bg.proxiad.lawnmower;

import java.util.Optional;
import java.util.stream.Stream;
/**
 * Enum which represents the possible orientations of a element .
 * 
 * @author svetozar
 *
 */
public enum Orientation {
	EAST("E"), WEST("W"), SOUTH("S"), NORTH("N");

	private final String code;

	private Orientation(final String code) {
		this.code = code;
	}
	
	public String code() {
		return code;
	}
	
	/**
	 * Returns the enum constant of corresponding to the code.
	 * 
	 * @param code not null the code of the command
	 * @return not null, the enum constant corresponding to the code
	 * @throws IllegalArgumentException if the code is unknown
	 */
	public static Orientation valueForCode(final String code) {
		final Optional<Orientation> orientation = Stream.of(values())
			.filter(x -> x.code.equals(code))
			.findFirst();
		if(!orientation.isPresent()) {
			throw new IllegalArgumentException(String.format("Value '%s' is invalid orientation", code));
		}
		return orientation.get();
	}
	
}
