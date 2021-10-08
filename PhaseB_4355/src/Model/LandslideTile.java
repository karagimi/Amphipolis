package Model;

public class LandslideTile implements Tile {
	private String category;
	/**
	 * <b>Constructor:</b> Creates a new LandslideTile and sets its category.
	 * <b>Postcondition:</b> A new LandslideTile has been created and tile's category has been set.
	 */
	public LandslideTile() {
		this.category="LandslideTile";
	}

	/**
	 * <b>Transformer</b> Sets the tile's category.
	 * <b>Postcondition</b> Tile's category has been set.
	 * @param category
	 */
	public void setCategory(String category) {
		this.category=category;
	}

	/**
	 * <b>Accessor:</b> Returns the tile's category.
	 * <b>Postcondition</b> The category of a tile has been returned.
	 * @param category
	 */
	public String getCategory() {
		return this.category;
	}

	
}
