package Model;

public class FindingTile implements Tile {
	private String category;
	
	/**
	 * <b>Constructor:</b> Creates a new FindingTile and sets its category.
	 * <b>Postcondition</b> A new FindingTile has been created and tile's category has been set.
	 * @param category
	 */
	public FindingTile(String category) {
		this.category=category;
	}
	/**
	 * <b>Accessor:</b> Returns the Findingtile's category
	 * <b>Postcondition:</b> The category of a Findingtile has been returned
	 * @return String category
	 */
	public String getCategory() {
		return this.category;
	}
	
	/**
	 * <b>Transformer:</b> Sets the Findingtile's category
	 * <b>Postcondition:</b> Findingtile's category has been set
	 * @param category
	 */
	public void setCategory(String category) {
		this.category=category;
	}
	
}

