 package Controller;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Collections;

import Model.Player;
import Model.PlayerColor;
import Model.Bag;
import Model.Board;
import Model.Tile;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Model.Archaeologist;
import Model.*;

public class Controller {
	private ArrayList<Player> players=new ArrayList<Player>();
	private ArrayList<Player> standings=new ArrayList<Player>();
	private Player P1,P2,P3,P4;
	private boolean notstarted,empty_board;
	private Bag alltiles=new Bag();
	public Board board=new Board();
	
	/**
	 * <b>Constructor:</b> Constructs a new Controller and
	 * sets up the game for the players to play.
	 * <b>Postcondition:</b> A new Controller has been constructed
	 * and the game has been set. 
	 */
	public Controller() {
		this.notstarted=true;
		this.empty_board=true;
		PlayerColor blue=PlayerColor.BLUE;
		PlayerColor red=PlayerColor.RED;
		PlayerColor yellow=PlayerColor.YELLOW;
		PlayerColor black=PlayerColor.BLACK;
		P1=new Player(blue,"P1");
		P2=new Player(red,"P2");
		P3=new Player(yellow,"P3");
		P4=new Player(black,"P4");
		players.add(P1);
		players.add(P2);
		players.add(P3);
		players.add(P4);
		alltiles.init_tiles();
		init_character_cards();
		firstToPlay();
		this.notstarted=false;
	}
	
	/**
	 * <b>Transformer:</b> Initializes the character cards
	 * in the beginning of the game.
	 * <b>Postcondition:</b> The character cards have been initialized.
	 */
	public void init_character_cards() {
		P1.addCharacter(new Archaeologist(false,P1));
		P1.addCharacter(new Assistant(false,P1));
		P1.addCharacter(new Digger(false,P1));
		P1.addCharacter(new Professor(false,P1));
		
		P2.addCharacter(new Archaeologist(false,P2));
		P2.addCharacter(new Assistant(false,P2));
		P2.addCharacter(new Digger(false,P2));
		P2.addCharacter(new Professor(false,P2));
		
		P3.addCharacter(new Archaeologist(false,P3));
		P3.addCharacter(new Assistant(false,P3));
		P3.addCharacter(new Digger(false,P3));
		P3.addCharacter(new Professor(false,P3));
		
		P4.addCharacter(new Archaeologist(false,P4));
		P4.addCharacter(new Assistant(false,P4));
		P4.addCharacter(new Digger(false,P4));
		P4.addCharacter(new Professor(false,P4));
	}
	
	
	/**
	 * <b>Accessor:</b> Returns which player has the turn to play.
	 * <b>Postcondition:</b> The player that it is his turn to play, is returned.
	 * @return the player that has the turn to play.
	 */
	public String seeTurn() {
		return board.getTurn();
	}
	
	/**
	 * <b>Transformer:</b> Ends the turn of a player if the player pushes the "End Turn" Button.
	 * <b>Postcondition:</b> The turn of the player has ended.
	 */
	public void EndTurn() {
		
	}
	
	/**
	 * <b>Transformer:</b> Draws tiles for the player if the player pushes the "Draw Tiles" button.
	 * <b>Postcondition</b> The tiles for the player have been drawn.
	 * @param p
	 */
	public void DrawTiles() {
		int pid;
		Player player=getPlayers().get(0);
		String player_name=seeTurn();
		for(int i=0;i<4;i++) {
			if(getPlayers().get(i).getName().equals(player_name)) {
				pid=i;
				player=getPlayers().get(i);
			}
		}
		
		if(alltiles.getBag().size()>=4) {
			for(int i=0;i<4;i++) {
				if(alltiles.getBag().get(i) instanceof MosaicTile) {
					board.getMosaicArea().add((MosaicTile)alltiles.getBag().get(i));
				}
				
				if(alltiles.getBag().get(i) instanceof AmphoraTile) {
					board.getAmphoraArea().add((AmphoraTile)alltiles.getBag().get(i));
				}
			
				if(alltiles.getBag().get(i) instanceof SkeletonTile) {
						board.getSkeletonArea().add((SkeletonTile)alltiles.getBag().get(i));
				}
			
				if(alltiles.getBag().get(i) instanceof CaryatidTile) {
					board.getStatueArea().add((CaryatidTile)alltiles.getBag().get(i));
				}
			
				if(alltiles.getBag().get(i) instanceof SphinxTile) {
					board.getStatueArea().add((SphinxTile)alltiles.getBag().get(i));
				}
			
				if(alltiles.getBag().get(i) instanceof LandslideTile) {
					board.getLandslideArea().add((LandslideTile)alltiles.getBag().get(i));
				}
			
			}
		}else {
			for(int i=0;i<alltiles.getBag().size();i++) {
				if(alltiles.getBag().get(i) instanceof MosaicTile) {
					board.getMosaicArea().add((MosaicTile)alltiles.getBag().get(i));
				}
				
				if(alltiles.getBag().get(i) instanceof AmphoraTile) {
					board.getAmphoraArea().add((AmphoraTile)alltiles.getBag().get(i));
				}
			
				if(alltiles.getBag().get(i) instanceof SkeletonTile) {
						board.getSkeletonArea().add((SkeletonTile)alltiles.getBag().get(i));
				}
			
				if(alltiles.getBag().get(i) instanceof CaryatidTile) {
					board.getStatueArea().add((CaryatidTile)alltiles.getBag().get(i));
				}
			
				if(alltiles.getBag().get(i) instanceof SphinxTile) {
					board.getStatueArea().add((SphinxTile)alltiles.getBag().get(i));
				}
			
				if(alltiles.getBag().get(i) instanceof LandslideTile) {
					board.getLandslideArea().add((LandslideTile)alltiles.getBag().get(i));
				}
			
			}
		}
	}
	
	
	/**
	 * <b>Accessor:</b> Returns if the game has started or not.
	 * <b>Postcondition:</b> The state of the game has been returned.
	 * @return notstarted
	 */
	public boolean hasStarted() {
		return this.notstarted;
	}
	
	/**
	 * <b>Transformer:</b> Sets the state of the game.
	 * <b>Postcondition:</b> The state of the game has been set.
	 * @param notstarted
	 */
	public void setHasStarted(boolean notstarted) {
		this.notstarted=notstarted;
	}
	
	/**
	 * <b>Accessor:</b> Returns the players that participate in the game.
	 * <b>Postcondition:</b> The players have been returned.
	 * @return players
	 */
	public ArrayList<Player> getPlayers() {
		return this.players;
	}
	
	/**
	 * <b>Accessor:</b> Returns the tiles of a specific player.
	 * <b>Postcondition:</b> The tiles of the player have been returned.
	 * @param player
	 * @return getTiles() 
	 */
	public ArrayList<Tile> getPlayersTiles(Player player) {
		return player.getTiles();
	}
	
	/**
	 * <b>Accessor:</b> Returns the tiles that are in the bag.
	 * <b>Postcondition:</b> The tiles that are in the bag have been returned.
	 * @return alltiles
	 */
	public Bag getAllTiles() {
		return this.alltiles;
	}
	
	/**
	 * <b>Accessor:</b> Sets the turn via setTurn() that is implemented in board Class.
	 * <b>PostCondition:</b> The turn of the players has been set.
	 */
	public void getSetTurn() {
		board.setTurn(players);
	}
	
	/**
	 * <b>Accessor:</b> Returns the method getTurn() that is implemented in board Class.
	 * <b>Postcondition:</b> The method getTurned() has been returned.
	 * @return getTurn()
	 */
	public String getBoardTurn() {
		return board.getTurn();
	}
	
	/**
	 * <b>Accessor:</b> Returns the player that was selected randomly, to play first.
	 * <b>Postcondition</b> The player that will play first has been returned.
	 * @return the player that will play first.
	 */
	public Player firstToPlay() {
		Random r=new Random();
		int firstPlayer=r.nextInt((3-0)+1)+0; 
		board.setLastPlayer(players.get(firstPlayer).getName());
		board.setCurrentPlayer(players.get(firstPlayer).getName());
		return players.get(firstPlayer); 
	}
	
	
	/**
	 * <b>Accessor:</b> Finds the winner of the game
	 * <b>Postcondition:</b>
	 * @return the winner
	 */
	public Player Winner() {
		Points();
		int max=P1.getPoints();
		Player winner=P1;
		
		if(P2.getPoints()>max) {
			max=P2.getPoints();
			winner=P2;
		}
		
		if(P3.getPoints()>max) {
			max=P3.getPoints();
			winner=P3;
		}
		
		if(P4.getPoints()>max) {
			max=P4.getPoints();
			winner=P4;
		}
		return winner; 
	}
	
	/**
	 * <b>Transformer:</b> Concludes the calculation process with the addition of the StatueTile points.
	 * <b>Postcondition:</b> The calculation process has finished.
	 */
	public void Points() {
		P1.pointCalculator(P1.getTiles());
		P2.pointCalculator(P2.getTiles());
		P3.pointCalculator(P3.getTiles());
		P4.pointCalculator(P4.getTiles());
		int maxCar=-5;
		int flag=0;
		Player player_max=P1;
		Player player_min=P1;
		ArrayList<Player> maxPlayersCar=new ArrayList<Player>();
		ArrayList<Player> minPlayersCar=new ArrayList<Player>();
		for(int i=0;i<getPlayers().size();i++) {
			if(getPlayers().get(i).getCaryatid()>maxCar) {
				player_max=getPlayers().get(i);
				maxCar=player_max.getCaryatid();
			}
		}
		
		
		int minCar=500;
		for(int i=0;i<getPlayers().size();i++) {
			if(getPlayers().get(i).getCaryatid()<minCar) {
				player_min=getPlayers().get(i);
				minCar=player_min.getCaryatid();
			}
		}
		
		
		for(int i=0;i<getPlayers().size();i++) {
			if(getPlayers().get(i).getCaryatid()==player_max.getCaryatid()) {
				maxPlayersCar.add(getPlayers().get(i));
			}
		}
		
		for(int i=0;i<getPlayers().size();i++) {
			if(getPlayers().get(i).getCaryatid()==player_min.getCaryatid()) {
				minPlayersCar.add(getPlayers().get(i));
			}
		}
		
		for(int i=0;i<maxPlayersCar.size();i++) {
			maxPlayersCar.get(i).givePoints(6);
		}
		
		for(int i=0;i<getPlayers().size();i++) {
			flag=0;
			for(int j=0;j<maxPlayersCar.size();j++) {
				if(getPlayers().get(i)==maxPlayersCar.get(j)) {
					flag=1;
				}
			}
			
			for(int k=0;k<minPlayersCar.size();k++) {
				if(getPlayers().get(i)==minPlayersCar.get(k)) {
					flag=1;
				}
			}
			
			if(flag==0) {
				getPlayers().get(i).givePoints(3);
			}
		}
		
		int maxSp=-5;
		int flagSp=0;
		Player player_max_Sp=P1;
		Player player_min_Sp=P1;
		ArrayList<Player> maxPlayersSp=new ArrayList<Player>();
		ArrayList<Player> minPlayersSp=new ArrayList<Player>();
		for(int i=0;i<getPlayers().size();i++) {
			if(getPlayers().get(i).getSphinx()>maxSp) {
				player_max_Sp=getPlayers().get(i);
				maxSp=player_max_Sp.getSphinx();
			}
		}
		
		
		int min_Sp=500;
		for(int i=0;i<getPlayers().size();i++) {
			if(getPlayers().get(i).getSphinx()<min_Sp) {
				player_min_Sp=getPlayers().get(i);
				min_Sp=player_min_Sp.getSphinx();
			}
		}
		
		
		for(int i=0;i<getPlayers().size();i++) {
			if(getPlayers().get(i).getSphinx()==player_max_Sp.getSphinx()) {
				maxPlayersSp.add(getPlayers().get(i));
			}
		}
		
		for(int i=0;i<getPlayers().size();i++) {
			if(getPlayers().get(i).getSphinx()==player_min_Sp.getSphinx()) {
				minPlayersSp.add(getPlayers().get(i));
			}
		}
		
		for(int i=0;i<maxPlayersSp.size();i++) {
			maxPlayersSp.get(i).givePoints(6);
		}
		
		for(int i=0;i<getPlayers().size();i++) {
			flagSp=0;
			for(int j=0;j<maxPlayersSp.size();j++) {
				if(getPlayers().get(i)==maxPlayersSp.get(j)) {
					flagSp=1;
				}
			}
			
			for(int k=0;k<minPlayersSp.size();k++) {
				if(getPlayers().get(i)==minPlayersSp.get(k)) {
					flagSp=1;
				}
			}
			
			if(flagSp==0) {
				getPlayers().get(i).givePoints(3);
			}
		}
	}
	
	/**
	 * <b>Transformer:</b> Sets the order of the players in relation to their points.
	 * <b>Postcondition:</b> Sets the order of the players in relation to their points.
	 */
	public void Standings() {
		standings.add(Winner()); 
		for(int i=0;i<players.size();i++) {
			if(players.get(i)!=standings.get(0)) {
				standings.add(players.get(i));
			}
		}
		
		int min=500;
		Player player_min=P1;
		for(int i=0;i<standings.size();i++) {
			 if(standings.get(i).getPoints()<min) {
				 min=standings.get(i).getPoints();
				 player_min=standings.get(i);
			 }
		}
		for(int i=0;i<standings.size();i++) {
			if(standings.get(i)==player_min) {
				Collections.swap(standings, i, 3);
				break;
			}
		}
		
		if(standings.get(1).getPoints()<standings.get(2).getPoints()) {
			Collections.swap(standings, 1, 2);
		}
		
	}
	/**
	 * <b>Transformer:</b> Creates frame,panels and labels that have to do with the standings.
	 * <b>Postcondition:</b> Creates frame,panels and labels that have to do with the standings.
	 */
	public void winnerFrame() {
		JFrame winnerFrame=new JFrame();
		winnerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		winnerFrame.setBounds(100, 100, 727, 499);
		winnerFrame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\karag\\eclipse-workspace\\Project Phase A\\images_2020\\sphinx.png"));
		
		JPanel contentPane=new JPanel();
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		winnerFrame.setContentPane(contentPane);
		contentPane.setLayout(null);
		

		JLabel lblNewLabel = new JLabel("STANDINGS");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(292, 0, 270, 85);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("1st "+standings.get(0).getName()+" "+standings.get(0).getPoints());
		lblNewLabel_1.setForeground(new Color(255, 140, 0));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(292, 113, 100, 25);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("2nd "+standings.get(1).getName()+" "+standings.get(1).getPoints());
		lblNewLabel_2.setForeground(new Color(128, 128, 128));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_2.setBounds(292, 154, 100, 25);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("3rd "+standings.get(2).getName()+" "+standings.get(2).getPoints());
		lblNewLabel_3.setForeground(new Color(204, 51, 0));
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_3.setBounds(292, 193, 100, 25);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("4th "+standings.get(3).getName()+" "+standings.get(3).getPoints());
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_4.setBounds(292, 232, 100, 25);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5=new JLabel("Congratulations "+standings.get(0).getName()+" "+"for the win!!");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_5.setBounds(200, 380, 488, 31);
		contentPane.add(lblNewLabel_5);
		
		winnerFrame.setVisible(true);
	}
}