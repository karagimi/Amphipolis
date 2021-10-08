package Model;

public class StatueTile extends FindingTile {
	private String category;
	
	/**
	 * <b>Constructor:</b> Passes the category of the Statuetile to the superclass(FindingTile).
	 * <b>Postcondition</b> Statuetile's category has been set.
	 * @param category
	 */
	public StatueTile(String category) {
		super(category);
		this.category=category;
	}
	
	/**
	 * <b>Accessor:</b> Returns the Statuetile's category.
	 * <b>Postcondition:</b> The category of a Statuetile has been returned.
	 * @return String category
	 */
	public String getCategory() {
		return this.category;
	}
	
	/**
	 * <b>Transformer:</b> Sets the Statuetile's category.
	 * <b>Postcondition:</b> Statuetile's category has been set.
	 * @param category
	 */
	public void setCategory(String category) {
		this.category=category;
	}
	
}
