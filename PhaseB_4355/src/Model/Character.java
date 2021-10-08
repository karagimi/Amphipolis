package Model;

public abstract class Character {
	protected boolean isUsed;
	protected Player player;
	
	/**
	 * <b>Constructor:</b> It creates an instance of Character
	 * and sets two variables: isUsed and player.
	 * <b>PostCondition:</b> A character has been created.
	 * @param color
	 * @param isUsed
	 * @param player
	 */
	public Character(boolean isUsed,Player player) {
		this.isUsed=isUsed;
		this.player=player;
	}
	
	/**
	 * <b>Accessor:</b> Returns if the character has been used already.
	 * <b>Postcondition:</b> Checked if the character has been used.
	 */
	public boolean getIsUsed() {
		return this.isUsed;
	}
	
	/**
	 * <b>Accessor:</b> Returns the player that owns the character card.
	 * <b>Postcondition:</b>The player that owns the card has been returned.
	 * @return
	 */
	public Player getPlayer() {
		return this.player;
	}
	
	
	
	
}
