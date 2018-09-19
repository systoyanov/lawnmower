package bg.proxiad.lawnmower;
/**
 * Class representing the status of a mower on the lawn.
 * 
 * @author svetozar
 *
 */
public final class MowerStatus {
	private final Position position;
	private final Orientation orientation;

	/**
	 * Constructor.
	 * @param posX	position on the X axis
	 * @param posY	position on the Y axis
	 * @param orientation	not null
	 */
	public MowerStatus(final int posX, final int posY, final Orientation orientation) {
		this.position = new Position(posX, posY);
		this.orientation = orientation;
	}
	
	public MowerStatus(final Position position, final Orientation orientation) {
		this.position = position;
		this.orientation = orientation;
	}

	public Position getPosition() {
		return position;
	}

	public Orientation getOrientation() {
		return orientation;
	}

}
