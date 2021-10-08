package Model;
import java.util.ArrayList;
import Controller.Controller;

public class Board {
	private ArrayList<MosaicTile> MosaicsSortingArea; 
	private ArrayList<StatueTile> MarbleStatuesSortingArea;
	private ArrayList<AmphoraTile> AmphorasSortingArea;
	private ArrayList<SkeletonTile> SkeletonsSortingArea;
	private ArrayList<LandslideTile> EntranceSpaces;
	private String currentPlayer;
	private String last_player;
	
	
	/**
	 * <b>Constructor:</b> Creates the ArrayLists of the five tile areas.
	 * <b>Postcondition:</b> The five tile areas have been created.
	 */
	public Board() {
		MosaicsSortingArea=new ArrayList<MosaicTile>();
		MarbleStatuesSortingArea=new ArrayList<StatueTile>();
		AmphorasSortingArea=new ArrayList<AmphoraTile>();
		SkeletonsSortingArea=new ArrayList<SkeletonTile>();
		EntranceSpaces=new ArrayList<LandslideTile>();
	}
	
	
	/**
	 * <b>Transformer:</b> Sets the player's turn.(which player has the turn to play).
	 * <b>Postcondition:</b> Player's turn has been set.
	 * @param players
	 */
	public void setTurn(ArrayList <Player> players) {
			if(players.get(0).getName().equals(last_player)) {
				this.currentPlayer=players.get(1).getName();
			}
			if(players.get(1).getName().equals(last_player)) {
				this.currentPlayer=players.get(2).getName();
			}
			if(players.get(2).getName().equals(last_player)) {
				this.currentPlayer=players.get(3).getName();
			}
			if(players.get(3).getName().equals(last_player)) {
				this.currentPlayer=players.get(0).getName();
			}
			this.last_player=this.currentPlayer;
	}
	
	/**
	 * <b>Accessor:</b> Returns the player's name whose turn is to play.
	 * <b>Postcondition:</b> The player's name has been returned.
	 * @return
	 */
	public String getTurn() {
		return this.currentPlayer;
	}
	
	public void setCurrentPlayer(String name) {
		this.currentPlayer=name;
	}
	
	/**
	 * <b>Accessor:</b> Returns the Tiles of the MosaicsSortingArea.
	 * <b>Postcondition:</b> Tiles of the MosaicsSortingArea have been returned.
	 * @return Tiles of MosaicsSortingArea
	 */
	public ArrayList<MosaicTile> getMosaicArea() {
		return this.MosaicsSortingArea;
	}
	
	/**
	 * <b>Accessor:</b> Returns the Tiles of the MarbleStatuesSortingArea.
	 * <b>Postcondition:</b> Tiles of the MarbleStatuesSortingArea have been returned.
	 * @return Tiles of MarbleStatuesSortingArea
	 */
	public ArrayList<StatueTile> getStatueArea() {
		return this.MarbleStatuesSortingArea;
	}
	
	/**
	 * <b>Accessor:</b> Returns the Tiles of the AmphorasSortingArea.
	 * <b>Postcondition:</b> Tiles of the AmphorasSortingArea have been returned.
	 * @return Tiles of AmphorasSortingArea
	 */
	public ArrayList<AmphoraTile> getAmphoraArea() {
		return this.AmphorasSortingArea;
	}
	
	/**
	 * <b>Accessor:</b> Returns the Tiles of the SkeletonsSortingArea.
	 * <b>Postcondition:</b> Tiles of the SkeletonsSortingArea have been returned.
	 * @return Tiles of SkeletonsSortingArea
	 */
	public ArrayList<SkeletonTile> getSkeletonArea() {
		return this.SkeletonsSortingArea;
	}
	
	/**
	 * <b>Accessor:</b> Returns the Tiles of the EntranceSpaces.
	 * <b>Postcondition:</b> Tiles of the EntranceSpaces have been returned.
	 * @return Tiles of EntranceSpaces.
	 */
	public ArrayList<LandslideTile> getLandslideArea() {
		return this.EntranceSpaces;
	}
	
	/**
	 * <b>Accessor:</b> Returns the last player that played.
	 * <b>Postcondition:</b> The last player who played is returned.
	 * @return the last player
	 */
	public String getLastPlayer() {
		return this.last_player;
	}
	
	
	/**
	 * <b>Transformer:</b> Sets the last player.
	 * <b>Postcondition:</b> The last player has been set. 
	 * @param name
	 */
	public void setLastPlayer(String name) {
		this.last_player=name;
	}
	
	
	/**
	 * <b>Observer:</b> Checks if a player has finished their turn.
     * <b>Postcondition:</b> Returns true if a player has finished, else false.
	 * @param p
	 * @return true if a player has finished, false otherwise
	 */
	public boolean checkIfPlayerFinished(Player p) {
		if(p.has_finished()==true) {
			return true;
		}else{
			return false;
		}
	}
	
	
	/**
	 *<b>Observer:</b> Checks if the game ends.
	 *<b>Postcondition:</b> Checked if the game had to end.
	 * @return true if it ends false otherwise.
	 */
	public boolean End() {
		if(EntranceSpaces.size()>=16) {
			return true;
		}else {
			return false;
		}
	}
	
	
}
