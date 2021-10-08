package Model;

public class Archaeologist extends Character {
	
	/**
	 * <b>Constructor</b> Creates an instance of Archaeologist
	 * and through the superclass it sets two characteristics:
	 * isUsed and player.
	 * <b>Postcondition</b> An Archaeologist has been created.
	 * @param isUsed
	 * @param player
	 */
	public Archaeologist(boolean isUsed,Player player) {
		super(isUsed,player);
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
	 * @return player
	 */
	public Player getPlayer() {
		return this.player;
	}
}
