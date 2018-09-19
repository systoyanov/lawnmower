package bg.proxiad.lawnmower;

/**
 * This class represents a position on a <code>Lawn<code>.
 * The objects of this class are immutable and thread-safe.
 * 
 * @author svetozar
 * 
 */
public final class Position {

	private final int x;
	private final int y;

	/**
	 * Constructor.
	 * 
	 * @param x	value for X axis
	 * @param y	value for Y axis
	 */
	public Position(final int x, final int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Gets the value for X axis.
	 * 
	 * @return
	 */
	public int getX() {
		return x;
	}

	/**
	 * Gets the value for Y axis.
	 * 
	 * @return
	 */
	public int getY() {
		return y;
	}

}