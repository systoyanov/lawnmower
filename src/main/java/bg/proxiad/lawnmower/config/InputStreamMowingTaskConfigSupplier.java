package bg.proxiad.lawnmower.config;

import static java.lang.Integer.parseInt;

import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import bg.proxiad.lawnmower.Orientation;
import bg.proxiad.lawnmower.MowerCommand;
import bg.proxiad.lawnmower.Position;

/**
 * Supplier of {@link MowingTaskConfig} which uses input stream to load the
 * configuration
 * 
 * @author svetozar
 *
 */
public class InputStreamMowingTaskConfigSupplier implements Supplier<MowingTaskConfig> {
	private static final String WHITESPACE = "\\s";

	private final Scanner scanner;

	/**
	 * Constructor.
	 * 
	 * @param input	not null
	 */
	@SuppressWarnings("resource")
	public InputStreamMowingTaskConfigSupplier(InputStream input) {
		this.scanner = new Scanner(input).useDelimiter("\\R");
	}

	@Override
	public MowingTaskConfig get() {
		final Position topRightCorner = parsePositionLine(nextLine());

		return new MowingTaskConfig(topRightCorner, new Iterator<MowerConfig>() {

			@Override
			public MowerConfig next() {
				return parseMowerConfig(scanner);
			}

			@Override
			public boolean hasNext() {
				return scanner.hasNext();
			}
		});

	}

	private MowerConfig parseMowerConfig(Scanner scanner) {
		final String positionLine = nextLine();
		final Position position = parsePositionLine(positionLine);
		final String[] positionTokens = positionLine.split(WHITESPACE);
		final String direction = positionTokens[2];

		if(!scanner.hasNext()) {
			throw new IllegalArgumentException("Commands line missing");
		}
		final String operationsLine = nextLine();
		final List<MowerCommand> operations = operationsLine.chars().mapToObj(c -> (char) c).map(MowerCommand::valueOf)
				.collect(Collectors.toList());

		return new MowerConfig(position, Orientation.valueForCode(direction), operations);
	}

	private Position parsePositionLine(final String positionLine) {
		try {
			final String[] positionTokens = positionLine.split(WHITESPACE);
			return new Position(parseInt(positionTokens[0]), parseInt(positionTokens[1]));
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("Invalid position specified");
		}
	}
	
	private String nextLine() {
		return scanner.next();
	}

}
