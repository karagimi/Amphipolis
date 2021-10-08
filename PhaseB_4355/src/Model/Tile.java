package Model;

public interface Tile {
	/**
	 * <b>Transformer:</b> Sets the tile's category.
	 * <b>Postcondition:</b> Tile's category has been set.
	 * @param category
	 */
	public void setCategory(String category);
	
	/**
	 * <b>Accessor:</b> Returns the tile's category.
	 * <b>Postcondition:</b> The category of a tile has been returned.
	 * @param category
	 */
	public String getCategory();
}
