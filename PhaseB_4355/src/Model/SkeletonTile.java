package Model;

public class SkeletonTile extends FindingTile {
	private String category;
	private String size;
	private String body_part;
	
	/**
	 * <b>Constructor:</b> Constructs a new instance of SkeletonTile,
	 * sets the size and the body_part and via the superclass(FindingTile)
	 * sets the category=SkeletonTile.
	 * <b>Postcondition</b>  Tile's category,size and body_part have been set.
	 * @param category
	 */
	public SkeletonTile(String size,String body_part) {
		super("SkeletonTile");
		this.category="SkeletonTile";
		this.size=size;
		this.body_part=body_part;
	}
	
	/**
	 * <b>Accessor:</b> Returns the size of the skeleton.
	 * <b>Postcondition:</b> The size of the skeleton has been returned.
	 * @return size of skeleton
	 */
	public String getSize() {
		return this.size;
	}
	
	/**
	 * <b>Accessor:</b> Returns the body part of the skeleton(upper-down).
	 * <b>Postcondition:</b> The body part of the skeleton has been returned.
	 * @return body part of skeleton
	 */
	public String getBodyPart() {
		return this.body_part;
	}
	/**
	 * <b>Accessor:</b> Returns the tile's category.
	 * <b>Postcondition:</b> The category of a tile has been returned.
	 * @return category
	 */
	public String getCategory() {
		return this.category;
	}
	
	/**
	 * <b>Transformer:</b> Sets the tile's category.
	 * <b>Postcondition:</b> Tile's category has been set.
	 * @param category
	 */
	public void setCategory(String category) {
		this.category=category;
	}
}
