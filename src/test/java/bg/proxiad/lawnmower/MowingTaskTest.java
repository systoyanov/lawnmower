package bg.proxiad.lawnmower;

import static org.junit.Assert.assertArrayEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.StringJoiner;

import org.junit.Test;

import bg.proxiad.lawnmower.task.MowingTaskFactory;

public class MowingTaskTest {

	@Test
	public void testWithSpecFile() throws IOException {
		try (final InputStream input = MowingTaskTest.class.getClassLoader().getResourceAsStream("input.txt")) {
			MowingTaskFactory.create(input, System.out).execute();
		}
	}
	
	@Test
	public void testSpecificationExample() {
		String[] ouputLines = executeTask("5 5", "1 2 N", "LFLFLFLFF", "3 3 E", "FFRFFRFRRF");
		String[] expectedOutputLines = { "1 3 N", "5 1 E"};
		assertArrayEquals(expectedOutputLines, ouputLines);
	}

	@Test
	public void testOutOfBoundsCommandIgnoredX() {
		String[] ouputLines = executeTask("5 5", "5 5 E", "FRF", "0 0 W", "FRF");
		String[] expectedOutputLines = { "5 4 S", "0 1 N"};
		assertArrayEquals(expectedOutputLines, ouputLines);
	}
	
	@Test
	public void testOutOfBoundsCommandIgnoredY() {
		String[] ouputLines = executeTask("5 5", "5 5 N", "FLF", "0 0 S", "FLF");
		String[] expectedOutputLines = { "4 5 W", "1 0 E"};
		assertArrayEquals(expectedOutputLines, ouputLines);
	}
	
	@Test
	public void testMowersDoNotInterfere() {
		String[] ouputLines = executeTask("5 5", "1 2 N", "LFLFLFLFF", "1 2 N", "LFLFLFLFF");
		String[] expectedOutputLines = { "1 3 N", "1 3 N"};
		assertArrayEquals(expectedOutputLines, ouputLines);
	}
	
	@Test
	public void testNoMowerInput() {
		String[] ouputLines = executeTask("5 5");
		String[] expectedOutputLines = { "" };
		assertArrayEquals(expectedOutputLines, ouputLines);
	}
	
	@Test
	public void testEmptyCommandLine() {
		String[] ouputLines = executeTask("5 5", "1 2 N", "", "3 3 E", "FFRFFRFRRF");
		String[] expectedOutputLines = { "1 2 N", "5 1 E"};
		assertArrayEquals(expectedOutputLines, ouputLines);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testEmptyLawnSizeLine() {
		executeTask("", "1 2 N", "LFLFLFLFF");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testEmptyMowerPositionLine() {
		executeTask("5 5", "", "LFLFLFLFF");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testInvalidLawnX() {
		executeTask("X 5", "1 2 N", "LFLFLFLFF");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testInvalidLawnY() {
		executeTask("5 Y", "1 2 N", "LFLFLFLFF");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testOutOfBoundsInitalPositionX() {
		executeTask("5 5", "6 2 N", "LFLFLFLFF");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNegativeInitalPositionX() {
		executeTask("5 5", "-1 2 N", "LFLFLFLFF");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testOutOfBoundsInitalPositionY() {
		executeTask("5 5", "2 6 N", "LFLFLFLFF");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNegativeInitalPositionY() {
		executeTask("5 5", "2 -1 N", "LFLFLFLFF");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNaNInitialPositionX() {
		executeTask("X 5", "X 2 N", "LFLFLFLFF");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNaNInitialPositionY() {
		executeTask("5 5", "2 Y N", "LFLFLFLFF");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testInvalidOrientation() {
		executeTask("5 5", "2 2 Z", "LFLFLFLFF");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testInvalidCommand() {
		executeTask("5 5", "2 2 N", "Z");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testMissingCommandsLine() {
		executeTask("5 5", "2 2 N");
	}

	private ByteArrayInputStream buildInput(String... lines) {
		StringJoiner sj = new StringJoiner(System.getProperty("line.separator"));
		for (String line : lines) {
			sj.add(line);
		}
		
		return new ByteArrayInputStream(sj.toString().getBytes());
	}
	
	private String[] executeTask(String... lines) {
		ByteArrayInputStream input = buildInput(lines);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		MowingTaskFactory.create(input, outputStream).execute();
		String output = new String(outputStream.toByteArray());
		String[] ouputLines = output.split(System.getProperty("line.separator"));
		return ouputLines;
	}
	

}
