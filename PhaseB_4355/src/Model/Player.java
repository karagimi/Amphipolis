package Model;

import java.util.ArrayList;
import javax.swing.JLabel;

public class Player  {
	private String name;
	private boolean hasPlayed;
	private boolean finished;
	private ArrayList<Tile> player_tiles;
	private ArrayList<Character> player_characters;
	private ArrayList<JLabel> playerTileLabels;
	private int points=0;
	private PlayerColor color;
	
	
	/**
	 * <b>Constructor:</b> Creates a player by setting its name,color and hasPlayed=false. 
	 * Also, it creates ArrayLists for the tiles of the player and their characters.
	 * <b>Postcondition:</b> A player has been created.
	 * @param newName
	 */
	public Player(PlayerColor color,String newName) {
		this.name=newName;
		player_tiles=new ArrayList<Tile>();
		player_characters=new ArrayList<Character>();
		playerTileLabels=new ArrayList<JLabel>();
		this.hasPlayed=false;
		this.color=color;
	}
	
	/**
	 * <b>Accessor:</b> Returns the player's color.
	 * <b>Postcondition:</b> The player's color has been returned.
	 * @return the player's color
	 */
	public PlayerColor getColor() {
		return this.color;
	}
	
	/**
	 * <b>Accessor:</b> Returns the player's name.
	 * <b>Postcondition:</b> The name of the player has been returned.
	 * @return name
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * <b>Transformer:</b> Sets the player's name.
	 * <b>Postcondition:</b> Player's name has been set.
	 * @param newName
	 */
	public void setName(String newName) {
		this.name=newName;
	}
	
	/**
	* <b>Observer:</b> Returns if a player has played at least one time.
    * <b>Postcondition:</b> Returns if a player has played at least one time.
    * @return true if a player has played at least one time false otherwise
	 */
	public boolean has_Played() {
		return this.hasPlayed;
	}
	
	/**
	 * <b>Transformer:</b> Returns if a player has finished their turn.
	 * <b>Postcondition:</b> Returns if a player has finished their turn.
	 * @return true if a player has finished their turn , false otherwise.
	 */
	public boolean has_finished() {
		return this.finished;
	}
	
	
	/**
	 * <b>Accessor:</b> Returns the characters of the player.
	 * <b>Postcondition:</b> The characters of the player have been returned.
	 * @return player_characters
	 */
	public ArrayList<Character> getCharacter() {
		return this.player_characters;
	}
		
	
	/**
	 * <b>Accessor:</b> Returns the tiles that are in the collection of the player.
	 * <b>Postcondition:</b> The tiles of the player's collection have been returned.
	 * @return player_tiles
	 */
	public ArrayList<Tile> getTiles() {
		return this.player_tiles;
	}
	
	/**
	 * <b>Transformer:</b> Adds a tile to the list.
	 * <b>Postcondition:</b> The tile has been added to the list.
	 * @param tile
	 */
	public void addTile(Tile tile) {
		player_tiles.add(tile);
	}
	
	/**
	 * <b>Transformer:</b> Removes a tile from the list.
	 * <b>Postcondition:</b> The tile has been removed from the list.
	 * @param tile
	 */
	public void removeTile(Tile tile) {
		player_tiles.remove(tile);
	}
	
	
	/**
	 * <b>Transformer:</b> Adds a character to the list.
	 * <b>Postcondition:</b> The character has been added to the list.
	 * @param character
	 */
	public void addCharacter(Character character) {
		player_characters.add(character);
	}
	
	
	/**
	 * <b>Transformer:</b> Removes a character from the list.
	 * <b>Postcodntiion:</b> The character has been removed from the list.
	 * @param character
	 */
	public void removeCharacter(Character character) {
		player_characters.remove(character);
	}
	
	/**
	 * <b>Accessor:</b> Returns the tile labels of the player.
	 * <b>Postcondition:</b> The tile labels of the player have been returned.
	 * @return playerTileLabels
	 */
	public ArrayList<JLabel> getLabels() {
		return this.playerTileLabels;
	}
	
	/**
	 * <b>Accessor:</b> Returns the points of the player.
	 * <b>Postcondition:</b> The points have been returned.
	 * @return points
	 */
	public int getPoints() {
		return this.points;
	}
	
	
	/**
	 * <b>Transformer:</b> Calculates the points of the player.
	 * <b>Postcondition</b> Points of the player have been returned.
	 * @return points
	 */
	public int pointCalculator(ArrayList<Tile> tiles) {
		int MosnumG=0,MosnumR=0,MosnumY=0;
		int AmpnumB=0,AmpnumBR=0,AmpnumR=0,AmpnumG=0,AmpnumY=0,AmpnumP=0;
		int flagB=0,flagBR=0,flagR=0,flagG=0,flagY=0,flagP=0;
		int numBigTop=0,numBigBot=0;
		int numSmallTop=0,numSmallBot=0,numAdult=0,numChild=0;
		int t=0;
		points=0;
		for(int i=0;i<getTiles().size();i++) {		//Mosaic
			if(player_tiles.get(i) instanceof MosaicTile) {
				MosaicTile obj=(MosaicTile) player_tiles.get(i);
				if(obj.getColor()==MosaicTileColor.GREEN) {
					MosnumG++;
				}else if(obj.getColor()==MosaicTileColor.RED) {
					MosnumR++;
				}else if(obj.getColor()==MosaicTileColor.YELLOW) {
					MosnumY++;
				}
			}
		}
		
		//ΠΕΡΙΠΤΩΣΗ ΓΙΑ ΦΟΥΛ ΜΩΣΑΙΚΟ
		while(MosnumG>=4) {
				for(int i=0;i<4;i++) {
					MosnumG--;
				}
				t=0;
				for(int i=0;i<player_tiles.size();i++) {
					if(player_tiles.get(i) instanceof MosaicTile) {
						MosaicTile obj=(MosaicTile) player_tiles.get(i);
						if(obj.getColor()==MosaicTileColor.GREEN) {
							player_tiles.remove(i);
							t++;
							i=0;
						}
					}
				
					if(t==4) {
						break;
					}
					
				}
				points=points+4;
		}
		
		
		while(MosnumR>=4) {
			for(int i=0;i<4;i++) {
				MosnumR--;
			}
			t=0;
			for(int i=0;i<player_tiles.size();i++) {
				if(player_tiles.get(i) instanceof MosaicTile) {
					MosaicTile obj=(MosaicTile) player_tiles.get(i);
					if(obj.getColor()==MosaicTileColor.RED) {
						player_tiles.remove(i);
						t++;
						i=0;
					}
				}
				
				if(t==4) {
					break;
				}
				
			}
			points=points+4;
		}
		
		
		while(MosnumY>=4) {
			for(int i=0;i<4;i++) {
				MosnumY--;
			}
			t=0;
			for(int i=0;i<player_tiles.size();i++) {
				if(player_tiles.get(i) instanceof MosaicTile) {
					MosaicTile obj=(MosaicTile) player_tiles.get(i);
					if(obj.getColor()==MosaicTileColor.YELLOW) {
						player_tiles.remove(i);
						t++;
						i=0;
					}
				}
				
				if(t==4) {
					break;
				}
				
			}
			points=points+4;
		}
		
		
		
		//ΠΕΡΙΠΤΩΣΗ ΓΙΑ ΔΙΑΦΟΡΕΤΙΚΑ ΧΡΩΜΑΤΑ MOSAIC
		while(MosnumG+MosnumR+MosnumY>3) {
			if(MosnumG>=1) {
				t=0;
				for(int i=0;i<player_tiles.size();i++) {
					if(player_tiles.get(i) instanceof MosaicTile) {
						MosaicTile obj=(MosaicTile) player_tiles.get(i);
						if(obj.getColor()==MosaicTileColor.GREEN) {
							player_tiles.remove(i);
							MosnumG--;
							t++;
						}
					}
					
					if(t==1) {
						break;
					}
				}
			}
			
			if(MosnumR>=1) {
				t=0;
				for(int i=0;i<player_tiles.size();i++) {
					if(player_tiles.get(i) instanceof MosaicTile) {
						MosaicTile obj=(MosaicTile) player_tiles.get(i);
						if(obj.getColor()==MosaicTileColor.RED) {
							player_tiles.remove(i);
							MosnumR--;
							t++;
						}
					}
					
					if(t==1) {
						break;
					}
				}
			}
			
			if(MosnumY>=1) {
				t=0;
				for(int i=0;i<player_tiles.size();i++) {
					if(player_tiles.get(i) instanceof MosaicTile) {
						MosaicTile obj=(MosaicTile) player_tiles.get(i);
						if(obj.getColor()==MosaicTileColor.YELLOW) {
							player_tiles.remove(i);
							MosnumY--;
							t++;
						}
					}
					
					if(t==1) {
						break;
					}
				}
			}
			points=points+2;
		}
		
		
		
		//ΣΚΕΛΕΤΟΙ ΟΙΚΟΓΕΝΕΙΑ
		
		for(int i=0;i<player_tiles.size();i++) {
			if(player_tiles.get(i) instanceof SkeletonTile) {
				SkeletonTile obj=(SkeletonTile) player_tiles.get(i);
				if(obj.getSize().equals("big") && obj.getBodyPart().equals("top")) {
					numBigTop++;
				}
			
				if(obj.getSize().equals("big") && obj.getBodyPart().equals("bottom")) {
					numBigBot++;
				}
			
				if(obj.getSize().equals("small") && obj.getBodyPart().equals("top")) {
					numSmallTop++;
				}
			
				if(obj.getSize().equals("small") && obj.getBodyPart().equals("bottom")) {
					numSmallBot++;
				}
			}
		}
		
		while(numBigTop>=1 && numBigBot>=1) {
			numAdult++;
			numBigTop--;
			numBigBot--;
		}
		
		while(numSmallTop>=1 && numSmallBot>=1) {
			numChild++;
			numSmallTop--;
			numSmallBot--;
		}
		
		
		while(numAdult>=2 && numChild>=1) {
			numAdult=numAdult-2;
			numChild=numChild-1;
			t=0;
			for(int i=0;i<player_tiles.size();i++) {
				if(player_tiles.get(i) instanceof SkeletonTile) {
					SkeletonTile obj=(SkeletonTile) player_tiles.get(i);
					if(obj.getSize().equals("big") && obj.getBodyPart().equals("top"))  {
						player_tiles.remove(i);
						t++;
						i=0;
					}
				}	
				
				if(t==2) {
					break;
				}
				
			}
			
			t=0;
			for(int i=0;i<player_tiles.size();i++) {
				if(player_tiles.get(i) instanceof SkeletonTile) {
					SkeletonTile obj=(SkeletonTile) player_tiles.get(i);
					if(obj.getSize().equals("big") && obj.getBodyPart().equals("bottom"))  {
						player_tiles.remove(i);
						t++;
						i=0;
					}
				}	
				
				if(t==2) {
					break;
				}
				
			}
			
			t=0;
			for(int i=0;i<player_tiles.size();i++) {
				if(player_tiles.get(i) instanceof SkeletonTile) {
					SkeletonTile obj=(SkeletonTile) player_tiles.get(i);
					if(obj.getSize().equals("small") && obj.getBodyPart().equals("top"))  {
						player_tiles.remove(i);
						t++;
					}
				}	
				
				if(t==1) {
					break;
				}
				
			}
			
			t=0;
			for(int i=0;i<player_tiles.size();i++) {
				if(player_tiles.get(i) instanceof SkeletonTile) {
					SkeletonTile obj=(SkeletonTile) player_tiles.get(i);
					if(obj.getSize().equals("small") && obj.getBodyPart().equals("bottom"))  {
						player_tiles.remove(i);
						t++;
					}
				}	
				
				if(t==1) {
					break;
				}
				
			}
			points=points+6;
		}
		
		
		//ΟΛΟΚΛΗΡΩΜΕΝΟΣ ΣΚΕΛΕΤΟΣ
		while(numAdult>=1) {
			numAdult--;
			t=0;
			for(int i=0;i<player_tiles.size();i++) {
				if(player_tiles.get(i) instanceof SkeletonTile) {
					SkeletonTile obj=(SkeletonTile) player_tiles.get(i);
					if(obj.getSize().equals("big") && obj.getBodyPart().equals("top")) {
						player_tiles.remove(i);
						t++;
					}
				}
				
				if(t==1) {
					break;
				}
			}
			
			t=0;
			for(int i=0;i<player_tiles.size();i++) {
				if(player_tiles.get(i) instanceof SkeletonTile) {
					SkeletonTile obj=(SkeletonTile) player_tiles.get(i);
					if(obj.getSize().equals("big") && obj.getBodyPart().equals("bottom")) {
						player_tiles.remove(i);
						t++;
					}
				}
				
				if(t==1) {
					break;
				}
			}
			points++;
		}
		
		
		
		while(numChild>=1) {
			numChild--;
			t=0;
			for(int i=0;i<player_tiles.size();i++) {
				if(player_tiles.get(i) instanceof SkeletonTile) {
					SkeletonTile obj=(SkeletonTile) player_tiles.get(i);
					if(obj.getSize().equals("small") && obj.getBodyPart().equals("top")) {
						player_tiles.remove(i);
						t++;
					}
				}
				
				if(t==1) {
					break;
				}
			}
			
			t=0;
			for(int i=0;i<player_tiles.size();i++) {
				if(player_tiles.get(i) instanceof SkeletonTile) {
					SkeletonTile obj=(SkeletonTile) player_tiles.get(i);
					if(obj.getSize().equals("small") && obj.getBodyPart().equals("bottom")) {
						player_tiles.remove(i);
						t++;
					}
				}
				
				if(t==1) {
					break;
				}
			}
			points++;
		}
		
		
		//ΑΜΦΟΡΕΙΣ 6 ΔΙΑΦΟΡΕΤΙΚΑ ΧΡΩΜΑΤΑ
		for(int i=0;i<player_tiles.size();i++) {
			if(player_tiles.get(i) instanceof AmphoraTile) {
				AmphoraTile obj=(AmphoraTile) player_tiles.get(i);
				if(obj.getColor()==AmphoraTileColor.BLUE) {
					AmpnumB++;
				}
				
				if(obj.getColor()==AmphoraTileColor.BROWN) {
					AmpnumBR++;
				}
				
				if(obj.getColor()==AmphoraTileColor.GREEN) {
					AmpnumG++;
				}
				
				if(obj.getColor()==AmphoraTileColor.PURPLE) {
					AmpnumP++;
				}
				
				if(obj.getColor()==AmphoraTileColor.RED) {
					AmpnumR++;
				}
				
				if(obj.getColor()==AmphoraTileColor.YELLOW) {
					AmpnumY++;
				}
			}
		}
		
		while(AmpnumB>=1 && AmpnumBR>=1 && AmpnumG>=1 && AmpnumP>=1 && AmpnumR>=1 && AmpnumY>=1) {
			AmpnumB--;
			AmpnumBR--;
			AmpnumG--;
			AmpnumP--;
			AmpnumR--;
			AmpnumY--;
			t=0;
			for(int i=0;i<player_tiles.size();i++) { 
				if(player_tiles.get(i) instanceof AmphoraTile) {
					AmphoraTile obj=(AmphoraTile) player_tiles.get(i);
					if(obj.getColor()==AmphoraTileColor.BLUE) {
						player_tiles.remove(i);
						t++;
					}
				}
				
				if(t==1) {
					break;
				}
			}
			
			t=0;
			for(int i=0;i<player_tiles.size();i++) { 
				if(player_tiles.get(i) instanceof AmphoraTile) {
					AmphoraTile obj=(AmphoraTile) player_tiles.get(i);
					if(obj.getColor()==AmphoraTileColor.BROWN) {
						player_tiles.remove(i);
						t++;
					}
				}
				
				if(t==1) {
					break;
				}
			}
			
			t=0;
			for(int i=0;i<player_tiles.size();i++) { 
				if(player_tiles.get(i) instanceof AmphoraTile) {
					AmphoraTile obj=(AmphoraTile) player_tiles.get(i);
					if(obj.getColor()==AmphoraTileColor.GREEN) {
						player_tiles.remove(i);
						t++;
					}
				}
				
				if(t==1) {
					break;
				}
			}
			
			t=0;
			for(int i=0;i<player_tiles.size();i++) { 
				if(player_tiles.get(i) instanceof AmphoraTile) {
					AmphoraTile obj=(AmphoraTile) player_tiles.get(i);
					if(obj.getColor()==AmphoraTileColor.PURPLE) {
						player_tiles.remove(i);
						t++;
					}
				}
				
				if(t==1) {
					break;
				}
			}
			
			t=0;
			for(int i=0;i<player_tiles.size();i++) { 
				if(player_tiles.get(i) instanceof AmphoraTile) {
					AmphoraTile obj=(AmphoraTile) player_tiles.get(i);
					if(obj.getColor()==AmphoraTileColor.RED) {
						player_tiles.remove(i);
						t++;
					}
				}
				
				if(t==1) {
					break;
				}
			}
				
			t=0;
			for(int i=0;i<player_tiles.size();i++) { 
				if(player_tiles.get(i) instanceof AmphoraTile) {
					AmphoraTile obj=(AmphoraTile) player_tiles.get(i);
					if(obj.getColor()==AmphoraTileColor.YELLOW) {
						player_tiles.remove(i);
						t++;
					}
				}
				
				if(t==1) {
					break;
				}
			}
			points=points+6;
		}
		
		
		
		//ΑΜΦΟΡΕΙΣ 5 ΔΙΑΦΟΡΕΤΙΚΑ ΧΡΩΜΑΤA
		int AmpNum=0;
		do {
			for(int i=0;i<player_tiles.size();i++) {
				if(player_tiles.get(i) instanceof AmphoraTile) {
					AmphoraTile obj=(AmphoraTile) player_tiles.get(i);
					if(obj.getColor()==AmphoraTileColor.BLUE) {
						flagB=1;
					}
				
					if(obj.getColor()==AmphoraTileColor.BROWN) {
						flagBR=1;
					}
				
					if(obj.getColor()==AmphoraTileColor.GREEN) {
						flagG=1;
					}
				
					if(obj.getColor()==AmphoraTileColor.PURPLE) {
						flagP=1;
					}
				
					if(obj.getColor()==AmphoraTileColor.RED) {
						flagR=1;
					}
				
					if(obj.getColor()==AmphoraTileColor.YELLOW) {
						flagY=1;
					}
				}
			}
		
			AmpNum=flagB+flagBR+flagG+flagP+flagR+flagY;
			if(AmpNum==5) {
				if(flagB==1) {
					AmpnumB--;
					t=0;
					for(int i=0;i<player_tiles.size();i++) {
						if(player_tiles.get(i) instanceof AmphoraTile) {
							AmphoraTile obj=(AmphoraTile) player_tiles.get(i);
							if(obj.getColor()==AmphoraTileColor.BLUE) {
								player_tiles.remove(i);
								t++;
							}
						}
					
						if(t==1) {
							flagB=0;
							break;
						}
					}
				}

				if(flagBR==1) {
					AmpnumBR--;
					t=0;
					for(int i=0;i<player_tiles.size();i++) {
						if(player_tiles.get(i) instanceof AmphoraTile) {
							AmphoraTile obj=(AmphoraTile) player_tiles.get(i);
							if(obj.getColor()==AmphoraTileColor.BROWN) {
								player_tiles.remove(i);
								t++;
							}
						}

						if(t==1) {
							flagBR=0;
							break;
						}
					}
				}

				if(flagG==1) {
					AmpnumG--;
					t=0;
					for(int i=0;i<player_tiles.size();i++) {
						if(player_tiles.get(i) instanceof AmphoraTile) {
							AmphoraTile obj=(AmphoraTile) player_tiles.get(i);
							if(obj.getColor()==AmphoraTileColor.GREEN) {
								player_tiles.remove(i);
								t++;
							}
						}

						if(t==1) {
							flagG=0;
							break;
						}
					}
				}

				if(flagP==1) {
					AmpnumP--;
					t=0;
					for(int i=0;i<player_tiles.size();i++) {
						if(player_tiles.get(i) instanceof AmphoraTile) {
							AmphoraTile obj=(AmphoraTile) player_tiles.get(i);
							if(obj.getColor()==AmphoraTileColor.PURPLE) {
								player_tiles.remove(i);
								t++;
							}
						}

						if(t==1) {
							flagP=0;
							break;
						}
					}
				}

				if(flagR==1) {
					AmpnumR--;
					t=0;
					for(int i=0;i<player_tiles.size();i++) {
						if(player_tiles.get(i) instanceof AmphoraTile) {
							AmphoraTile obj=(AmphoraTile) player_tiles.get(i);
							if(obj.getColor()==AmphoraTileColor.RED) {
								player_tiles.remove(i);
								t++;
							}
						}

						if(t==1) {
							flagR=0;
							break;
						}
					}
				}

				if(flagY==1) {
					AmpnumY--;
					t=0;
					for(int i=0;i<player_tiles.size();i++) {
						if(player_tiles.get(i) instanceof AmphoraTile) {
							AmphoraTile obj=(AmphoraTile) player_tiles.get(i);
							if(obj.getColor()==AmphoraTileColor.YELLOW) {
								player_tiles.remove(i);
								t++;
							}
						}

						if(t==1) {
							flagY=0;
							break;
						}
					}
				}
				points=points+4;
			}
		}while(AmpNum==5);
		
		
		//ΑΜΦΟΡΕΙΣ ΜΕ 4 ΔΙΑΦΟΡΕΤΙΚΑ ΧΡΩΜΑΤΑ
		do {
			for(int i=0;i<player_tiles.size();i++) {
				if(player_tiles.get(i) instanceof AmphoraTile) {
					AmphoraTile obj=(AmphoraTile) player_tiles.get(i);
					if(obj.getColor()==AmphoraTileColor.BLUE) {
						flagB=1;
					}

					if(obj.getColor()==AmphoraTileColor.BROWN) {
						flagBR=1;
					}

					if(obj.getColor()==AmphoraTileColor.GREEN) {
						flagG=1;
					}

					if(obj.getColor()==AmphoraTileColor.PURPLE) {
						flagP=1;
					}

					if(obj.getColor()==AmphoraTileColor.RED) {
						flagR=1;
					}

					if(obj.getColor()==AmphoraTileColor.YELLOW) {
						flagY=1;
					}
				}
			}

			AmpNum=flagB+flagBR+flagG+flagP+flagR+flagY;
			if(AmpNum==4) {
				if(flagB==1) {
					AmpnumB--;
					t=0;
					for(int i=0;i<player_tiles.size();i++) {
						if(player_tiles.get(i) instanceof AmphoraTile) {
							AmphoraTile obj=(AmphoraTile) player_tiles.get(i);
							if(obj.getColor()==AmphoraTileColor.BLUE) {
								player_tiles.remove(i);
								t++;
							}
						}

						if(t==1) {
							flagB=0;
							break;
						}
					}
				}

				if(flagBR==1) {
					AmpnumBR--;
					t=0;
					for(int i=0;i<player_tiles.size();i++) {
						if(player_tiles.get(i) instanceof AmphoraTile) {
							AmphoraTile obj=(AmphoraTile) player_tiles.get(i);
							if(obj.getColor()==AmphoraTileColor.BROWN) {
								player_tiles.remove(i);
								t++;
							}
						}

						if(t==1) {
							flagBR=0;
							break;
						}
					}
				}

				if(flagG==1) {
					AmpnumG--;
					t=0;
					for(int i=0;i<player_tiles.size();i++) {
						if(player_tiles.get(i) instanceof AmphoraTile) {
							AmphoraTile obj=(AmphoraTile) player_tiles.get(i);
							if(obj.getColor()==AmphoraTileColor.GREEN) {
								player_tiles.remove(i);
								t++;
							}
						}

						if(t==1) {
							flagG=0;
							break;
						}
					}
				}

				if(flagP==1) {
					AmpnumP--;
					t=0;
					for(int i=0;i<player_tiles.size();i++) {
						if(player_tiles.get(i) instanceof AmphoraTile) {
							AmphoraTile obj=(AmphoraTile) player_tiles.get(i);
							if(obj.getColor()==AmphoraTileColor.PURPLE) {
								player_tiles.remove(i);
								t++;
							}
						}

						if(t==1) {
							flagP=0;
							break;
						}
					}
				}

				if(flagR==1) {
					AmpnumR--;
					t=0;
					for(int i=0;i<player_tiles.size();i++) {
						if(player_tiles.get(i) instanceof AmphoraTile) {
							AmphoraTile obj=(AmphoraTile) player_tiles.get(i);
							if(obj.getColor()==AmphoraTileColor.RED) {
								player_tiles.remove(i);
								t++;
							}
						}

						if(t==1) {
							flagR=0;
							break;
						}
					}
				}

				if(flagY==1) {
					AmpnumY--;
					t=0;
					for(int i=0;i<player_tiles.size();i++) {
						if(player_tiles.get(i) instanceof AmphoraTile) {
							AmphoraTile obj=(AmphoraTile) player_tiles.get(i);
							if(obj.getColor()==AmphoraTileColor.YELLOW) {
								player_tiles.remove(i);
								t++;
							}
						}

						if(t==1) {
							flagY=0;
							break;
						}
					}
				}
				points=points+2;
			}
		}while(AmpNum==4);
		
		
		//ΑΜΦΟΡΕΙΣ ΜΕ 3 ΔΙΑΦΟΡΕΤΙΚΑ ΧΡΩΜΑΤΑ
		do {
			for(int i=0;i<player_tiles.size();i++) {
				if(player_tiles.get(i) instanceof AmphoraTile) {
					AmphoraTile obj=(AmphoraTile) player_tiles.get(i);
					if(obj.getColor()==AmphoraTileColor.BLUE) {
						flagB=1;
					}

					if(obj.getColor()==AmphoraTileColor.BROWN) {
						flagBR=1;
					}

					if(obj.getColor()==AmphoraTileColor.GREEN) {
						flagG=1;
					}

					if(obj.getColor()==AmphoraTileColor.PURPLE) {
						flagP=1;
					}

					if(obj.getColor()==AmphoraTileColor.RED) {
						flagR=1;
					}

					if(obj.getColor()==AmphoraTileColor.YELLOW) {
						flagY=1;
					}
				}
			}

			AmpNum=flagB+flagBR+flagG+flagP+flagR+flagY;
			if(AmpNum==3) {
				if(flagB==1) {
					AmpnumB--;
					t=0;
					for(int i=0;i<player_tiles.size();i++) {
						if(player_tiles.get(i) instanceof AmphoraTile) {
							AmphoraTile obj=(AmphoraTile) player_tiles.get(i);
							if(obj.getColor()==AmphoraTileColor.BLUE) {
								player_tiles.remove(i);
								t++;
							}
						}

						if(t==1) {
							flagB=0;
							break;
						}
					}
				}

				if(flagBR==1) {
					AmpnumBR--;
					t=0;
					for(int i=0;i<player_tiles.size();i++) {
						if(player_tiles.get(i) instanceof AmphoraTile) {
							AmphoraTile obj=(AmphoraTile) player_tiles.get(i);
							if(obj.getColor()==AmphoraTileColor.BROWN) {
								player_tiles.remove(i);
								t++;
							}
						}

						if(t==1) {
							flagBR=0;
							break;
						}
					}
				}

				if(flagG==1) {
					AmpnumG--;
					t=0;
					for(int i=0;i<player_tiles.size();i++) {
						if(player_tiles.get(i) instanceof AmphoraTile) {
							AmphoraTile obj=(AmphoraTile) player_tiles.get(i);
							if(obj.getColor()==AmphoraTileColor.GREEN) {
								player_tiles.remove(i);
								t++;
							}
						}

						if(t==1) {
							flagG=0;
							break;
						}
					}
				}

				if(flagP==1) {
					AmpnumP--;
					t=0;
					for(int i=0;i<player_tiles.size();i++) {
						if(player_tiles.get(i) instanceof AmphoraTile) {
							AmphoraTile obj=(AmphoraTile) player_tiles.get(i);
							if(obj.getColor()==AmphoraTileColor.PURPLE) {
								player_tiles.remove(i);
								t++;
							}
						}

						if(t==1) {
							flagP=0;
							break;
						}
					}
				}

				if(flagR==1) {
					AmpnumR--;
					t=0;
					for(int i=0;i<player_tiles.size();i++) {
						if(player_tiles.get(i) instanceof AmphoraTile) {
							AmphoraTile obj=(AmphoraTile) player_tiles.get(i);
							if(obj.getColor()==AmphoraTileColor.RED) {
								player_tiles.remove(i);
								t++;
							}
						}

						if(t==1) {
							flagR=0;
							break;
						}
					}
				}

				if(flagY==1) {
					AmpnumY--;
					t=0;
					for(int i=0;i<player_tiles.size();i++) {
						if(player_tiles.get(i) instanceof AmphoraTile) {
							AmphoraTile obj=(AmphoraTile) player_tiles.get(i);
							if(obj.getColor()==AmphoraTileColor.YELLOW) {
								player_tiles.remove(i);
								t++;
							}
						}

						if(t==1) {
							flagY=0;
							break;
						}
					}
				}
				points=points+1;
			}
		}while(AmpNum==3);
		
		
		return points;
	}
	
	/**
	 *<b>Transformer:</b> Adds points to the player.
	 *<b>Postcondition:</b> The points are added.
	 * @param points
	 */
	public void givePoints(int points) {
		this.points=this.points+points;
	}
	
	
	/**
	 * <b>Transformer:</b> Counts the player's total CaryatidTiles.
	 * <b>Postcondition:</b> The player's CaryatidTiles have been counted.
	 * @return numCar
	 */
	public int getCaryatid() {
		int numCar=0;
		for(int i=0;i<player_tiles.size();i++) {
			if(player_tiles.get(i) instanceof CaryatidTile) {
				numCar++;
			}
		}
		return numCar;
	}
	
	/**
	 * <b>Transformer:</b> Counts the player's total SphinxTiles.
	 * <b>Postcondition:</b> The player's SphinxTiles have been counted.
	 * @return numSp
	 */
	public int getSphinx() {
		int numSp=0;
		for(int i=0;i<player_tiles.size();i++) {
			if(player_tiles.get(i) instanceof SphinxTile) {
				numSp++;
			}
		}
		return numSp;
	}
	
}
