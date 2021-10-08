package Model;

public class AmphoraTile extends FindingTile {
	private AmphoraTileColor color;
	
	/**
	 * <b>Constructor:</b> Constructs a new instance of AmphoraTile,
	 * sets the color and via the superclass(FindingTile)
	 * sets the category=AmphoraTile.
	 * <b>Postcondition</b>  Tile's category and color have been set.
	 * @param color
	 */
	public AmphoraTile(AmphoraTileColor color) {
		super("AmphoraTile");
		this.color=color;
	}
	
	/**
	 * <b>Accessor:</b> Returns the Amphoratile's color.
	 * <b>Postcondition:</b> The color of an Amphoratile has been returned.
	 * @return color
	 */
	public AmphoraTileColor getColor() {
		return this.color;
	}
	
	/**
	 * <b>Transformer:</b> Sets the Amphoratile's color.
	 * <b>Postcondition:</b> Amphoratile's color has been set.
	 * @param color
	 */
	public void setColor(AmphoraTileColor color) {
		this.color=color;
	}
}
