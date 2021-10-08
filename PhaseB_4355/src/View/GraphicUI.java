package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import Controller.Controller;
import Model.*;

public class GraphicUI {

	private JFrame frmAmphipolis;
	Controller controller=new Controller();
	private JPanel panel=new JPanel();
	private JLabel lblNewLabel=new JLabel("");
	private JPanel panel_1=new JPanel();
	private JLabel lblNewLabel_1=new JLabel("Use Character");
	private JLabel lblNewLabel_6=new JLabel("Player "+controller.getBoardTurn());
	private JButton ArchaeologistButton = new JButton("");
	private JButton AssistantButton = new JButton("");
	private JButton DiggerButton = new JButton("");
	private JButton ProfessorButton = new JButton("");
	private JButton EndTurn = new JButton("End Turn");
	private int posMos_x=15;
	private int posMos_y=10;
	private int posSt_x=365;
	private int posSt_y=10;
	private int posLand_x=192;
	private int posLand_y=208;
	private int posAmp_x=133;
	private int posAmp_y=429;
	private int posSke_x=365;
	private int posSke_y=429;
	private boolean flagMos;
	private boolean flagAmp;
	private boolean flagSke;
	private boolean flagSt;
	private int numPicked=0;
	private ArrayList<JButton> MosTileButtons=new ArrayList<JButton>();
	private ArrayList<JButton> AmpTileButtons=new ArrayList<JButton>();
	private ArrayList<JButton> SkeTileButtons=new ArrayList<JButton>();
	private ArrayList<JButton> StTileButtons=new ArrayList<JButton>();
	private boolean flagArch;
	private boolean flagAssist;
	private boolean flagDig;
	private boolean flagPro;
	private boolean flagDrawTiles;
	private boolean flagChoseTile;
	

	/**
	 * <b>Constructor:</b> Creates a new Window and calls initialize() to create the game.
	 * <b>Postcondition:</b> The game has been created.
	 */
	public GraphicUI() {
		createGame();
	}
	
	

	/**
	 * <b>Transformer:</b> It creates the whole game.
	 * <b>Postcondition:</b> The game has been created.
	 */
	private void createGame() {
		frmAmphipolis = new JFrame();
		frmAmphipolis.setTitle("Amphipolis");
		frmAmphipolis.setIconImage(Toolkit.getDefaultToolkit().getImage("images_2020\\sphinx.png"));
		frmAmphipolis.setBounds(100, 100, 770, 644);
		frmAmphipolis.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAmphipolis.getContentPane().setLayout(null);
		
		
		
		panel.setBounds(0, -2, 539, 482);
		frmAmphipolis.getContentPane().add(panel);
		
		ImageIcon imageIcon=new ImageIcon("images_2020\\background.png");
		Image image=imageIcon.getImage();
		Image newimg=image.getScaledInstance(539, 482, java.awt.Image.SCALE_SMOOTH);
		imageIcon=new ImageIcon(newimg);
		
		
		panel_1.setBounds(549, 0, 205, 482);
		frmAmphipolis.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_6.setBounds(62, 11, 87, 25);
		panel_1.add(lblNewLabel_6);	
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(0, 482, 754, 123);
		frmAmphipolis.getContentPane().add(panel_3);
		panel_3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		init_characterButtons();
		
	
		JButton DrawTiles = new JButton("Draw Tiles");
		DrawTiles.setBounds(0, 394, 205, 45);
		panel_1.add(DrawTiles);	
		
		DrawTiles.addActionListener(new ActionListener() {
			
			/**
			 * <b>Transformer:</b> Doing some action after pressing the "Draw Tiles" button.
			 * <b>Postcondition:</b> Doing some action after pressing the "Draw Tiles" button.
			 */
			public void actionPerformed(ActionEvent e) {
				EndTurn.setEnabled(false);
				controller.DrawTiles();	//Τραβάει 4 Tiles από το Bag και τα βάζει στα ArrayList των αντίστοιχων περιοχών.
				flagDrawTiles=true;
				int bagLength=controller.getAllTiles().getBag().size();
				if(bagLength>=4) {
					bagLength=4;
				}else {
					bagLength=controller.getAllTiles().getBag().size();
				}
				for(int i=0;i<bagLength;i++) {
					if(controller.getAllTiles().getBag().get(i) instanceof MosaicTile) {
						if(posMos_x<=175) {
							posMos_x=posMos_x+40;
							if(posMos_x==215) {
								posMos_y=posMos_y+40;
								posMos_x=15;
							}
						}
						JButton button=new JButton();
						MosaicTile obj=(MosaicTile) controller.getAllTiles().getBag().get(i);
						MosTileButtons.add(button);
						panel.add(button);
						
						if(obj.getColor()==MosaicTileColor.GREEN) {
							button.addActionListener(new ActionListener() {
								
								/**
								 * <b>Transformer:</b> Doing some action if a Green MosaicTile button is pressed.
								 * <b>Postcondition:</b> Doing some action if a Green MosaicTile button is pressed.
								 */
								public void actionPerformed(ActionEvent b) {
									if(((!(flagAmp||flagSke||flagSt)&&(numPicked<2)&&(!flagArch && !flagPro)) || ((flagArch||flagPro)&&((!flagMos)&&(numPicked<2)))) && flagDrawTiles) {
										EndTurn.setEnabled(true);
										flagMos=true;
										JLabel label=new JLabel();
										Player player=controller.getPlayers().get(0);
										for(int i=0;i<4;i++) {
											if(controller.seeTurn().equals(controller.getPlayers().get(i).getName())) {
												player=controller.getPlayers().get(i);
												player.getLabels().add(label);
											}
										}
									
										for(int i=0;i<player.getLabels().size();i++) {
											panel_3.add(player.getLabels().get(i));
										}
										ImageIcon imageIconLabelMos=new ImageIcon("images_2020\\mosaic_green.png");
										Image imageLabelMos=imageIconLabelMos.getImage();
										Image newimgLabelMos=imageLabelMos.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
										imageIconLabelMos=new ImageIcon(newimgLabelMos);
										label.setIcon(imageIconLabelMos);
										button.setVisible(false);
										for(int i=0;i<4;i++) {
											if(controller.seeTurn().equals(controller.getPlayers().get(i).getName())) {
												controller.getPlayers().get(i).addTile(obj);
											}
										}
										
										for(int i=0;i<controller.board.getMosaicArea().size();i++) {
											if(controller.board.getMosaicArea().get(i).getColor()==MosaicTileColor.GREEN) {
												controller.board.getMosaicArea().remove(i);
												break;
											}
										}
										numPicked++;
										if(flagPro) {
											numPicked=1;
										}
										if(numPicked==1 || numPicked==2) {
											flagChoseTile=true;
										}
										if(flagArch) {
											flagMos=false;
											flagAmp=true;
											flagSke=true;
											flagSt=true;
										}
										
									
									}
									
								}
								
							});
							button.setBounds(posMos_x, posMos_y , 40 , 40);
							ImageIcon imageIconButtonMos=new ImageIcon("images_2020\\mosaic_green.png");
							Image imageButtonMos=imageIconButtonMos.getImage();
							Image newimgMos=imageButtonMos.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
							imageIconButtonMos=new ImageIcon(newimgMos);
							button.setIcon(imageIconButtonMos);
							
						}
						
						if(obj.getColor()==MosaicTileColor.RED) {
							button.addActionListener(new ActionListener() {
								
								/**
								 * <b>Transformer:</b> Doing some action if a Red MosaicTile button is pressed.
								 * <b>Postcondition:</b> Doing some action if a Red MosaicTile button is pressed.
								 */
								public void actionPerformed(ActionEvent b) {
									if((!(flagAmp||flagSke||flagSt)&&(numPicked<2 && (!flagArch && !flagPro)) || ((flagArch||flagPro)&&((!flagMos)&&(numPicked<2)))) && flagDrawTiles) {
										EndTurn.setEnabled(true);
										flagMos=true;
										JLabel label=new JLabel();
										Player player=controller.getPlayers().get(0);
										for(int i=0;i<4;i++) {
											if(controller.seeTurn().equals(controller.getPlayers().get(i).getName())) {
												player=controller.getPlayers().get(i);
												player.getLabels().add(label);
											}
										}
					
										for(int i=0;i<player.getLabels().size();i++) {
											panel_3.add(player.getLabels().get(i));
										}
										ImageIcon imageIconLabelMos=new ImageIcon("images_2020\\mosaic_red.png");
										Image imageLabelMos=imageIconLabelMos.getImage();
										Image newimgLabelMos=imageLabelMos.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
										imageIconLabelMos=new ImageIcon(newimgLabelMos);
										label.setIcon(imageIconLabelMos);
										button.setVisible(false);
										for(int i=0;i<4;i++) {
											if(controller.seeTurn().equals(controller.getPlayers().get(i).getName())) {
												controller.getPlayers().get(i).addTile(obj);
											}
										}
										
										for(int i=0;i<controller.board.getMosaicArea().size();i++) {
											if(controller.board.getMosaicArea().get(i).getColor()==MosaicTileColor.RED) {
												controller.board.getMosaicArea().remove(i);
												break;
											}
										}
										
										numPicked++;
										if(flagPro) {
											numPicked=1;
										}
										if(numPicked==1 || numPicked==2) {
											flagChoseTile=true;
										}
										if(flagArch) {
											flagMos=false;
											flagAmp=true;
											flagSke=true;
											flagSt=true;
										}
									}
									
									
								}
									
							});
							button.setBounds(posMos_x, posMos_y , 40 , 40);
							ImageIcon imageIconButtonMos=new ImageIcon("images_2020\\mosaic_red.png");
							Image imageButtonMos=imageIconButtonMos.getImage();
							Image newimgMos=imageButtonMos.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
							imageIconButtonMos=new ImageIcon(newimgMos);
							button.setIcon(imageIconButtonMos);
						}
						
						if(obj.getColor()==MosaicTileColor.YELLOW) {
							button.addActionListener(new ActionListener() {
								
								/**
								 * <b>Transformer:</b> Doing some action if a Yellow MosaicTile button is pressed.
								 * <b>Postcondition:</b> Doing some action if a Yellow MosaicTile button is pressed.
								 */
								public void actionPerformed(ActionEvent b) {
									if((!(flagAmp||flagSke||flagSt)&&(numPicked<2 && (!flagArch && !flagPro)) || ((flagArch||flagPro)&&((!flagMos)&&(numPicked<2))))&& flagDrawTiles) {
										EndTurn.setEnabled(true);
										flagMos=true;
										JLabel label=new JLabel();
										Player player=controller.getPlayers().get(0);
										for(int i=0;i<4;i++) {
											if(controller.seeTurn().equals(controller.getPlayers().get(i).getName())) {
												player=controller.getPlayers().get(i);
												player.getLabels().add(label);
											}
										}
									
										for(int i=0;i<player.getLabels().size();i++) {
											panel_3.add(player.getLabels().get(i));
										}
										ImageIcon imageIconLabelMos=new ImageIcon("images_2020\\mosaic_yellow.png");
										Image imageLabelMos=imageIconLabelMos.getImage();
										Image newimgLabelMos=imageLabelMos.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
										imageIconLabelMos=new ImageIcon(newimgLabelMos);
										label.setIcon(imageIconLabelMos);
										button.setVisible(false);
										for(int i=0;i<4;i++) {
											if(controller.seeTurn().equals(controller.getPlayers().get(i).getName())) {
												controller.getPlayers().get(i).addTile(obj);
											}
										}
										
										for(int i=0;i<controller.board.getMosaicArea().size();i++) {
											if(controller.board.getMosaicArea().get(i).getColor()==MosaicTileColor.YELLOW) {
												controller.board.getMosaicArea().remove(i);
												break;
											}
										}
									
										numPicked++;
										if(flagPro) {
											numPicked=1;
										}
										if(numPicked==1 || numPicked==2) {
											flagChoseTile=true;
										}
										if(flagArch) {
											flagMos=false;
											flagAmp=true;
											flagSke=true;
											flagSt=true;
										} 
									}
									
								}
								
								
							});
							button.setBounds(posMos_x, posMos_y , 40 , 40);
							ImageIcon imageIconButtonMos=new ImageIcon("images_2020\\mosaic_yellow.png");
							Image imageButtonMos=imageIconButtonMos.getImage();
							Image newimgMos=imageButtonMos.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
							imageIconButtonMos=new ImageIcon(newimgMos);
							button.setIcon(imageIconButtonMos);
						}
						
					}
					
					if(controller.getAllTiles().getBag().get(i) instanceof AmphoraTile) {
						if(posAmp_x>=-42) {
							posAmp_x=posAmp_x-35;
							if(posAmp_x==-42) {
								posAmp_y=posAmp_y-40;
								posAmp_x=133;
							}
						}
						JButton button=new JButton();
						AmphoraTile obj=(AmphoraTile) controller.getAllTiles().getBag().get(i);
						AmpTileButtons.add(button);
						panel.add(button);
						if(obj.getColor()==AmphoraTileColor.BLUE) {
							button.addActionListener(new ActionListener() {
								
								/**
								 * <b>Transformer:</b> Doing some action if a Blue AmphoraTile button is pressed.
								 * <b>Postcondition:</b> Doing some action if a Blue AmphoraTile button is pressed.
								 */
								public void actionPerformed(ActionEvent b) {
									if((!(flagMos||flagSke||flagSt)&&(numPicked<2 && (!flagArch && !flagPro)) || ((flagArch||flagPro)&&((!flagAmp)&&(numPicked<2))))&& flagDrawTiles) {
										EndTurn.setEnabled(true);
										flagAmp=true;
										JLabel label=new JLabel();
										Player player=controller.getPlayers().get(0);
										for(int i=0;i<4;i++) {
											if(controller.seeTurn().equals(controller.getPlayers().get(i).getName())) {
												player=controller.getPlayers().get(i);
												player.getLabels().add(label);
											}
										}
									
										for(int i=0;i<player.getLabels().size();i++) {
											panel_3.add(player.getLabels().get(i));
										}
										ImageIcon imageIconLabelAmp=new ImageIcon("images_2020\\amphora_blue.png");
										Image imageLabelAmp=imageIconLabelAmp.getImage();
										Image newimgLabelAmp=imageLabelAmp.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
										imageIconLabelAmp=new ImageIcon(newimgLabelAmp);
										label.setIcon(imageIconLabelAmp);
										button.setVisible(false);
										for(int i=0;i<4;i++) {
											if(controller.seeTurn().equals(controller.getPlayers().get(i).getName())) {
												controller.getPlayers().get(i).addTile(obj);
											}
										}
										
										for(int i=0;i<controller.board.getAmphoraArea().size();i++) {
											if(controller.board.getAmphoraArea().get(i).getColor()==AmphoraTileColor.BLUE) {
												controller.board.getAmphoraArea().remove(i);
												break;
											}
										}
										
										numPicked++;
										if(flagPro) {
											numPicked=1;
										}
										if(numPicked==1 || numPicked==2) {
											flagChoseTile=true;
										}
										if(flagArch) {
											flagAmp=false;
											flagMos=true;
											flagSke=true;
											flagSt=true;
										}
									
									}
								}
								
								
							});
							button.setBounds(posAmp_x, posAmp_y , 40 , 40);
							ImageIcon imageIconButtonAmp=new ImageIcon("images_2020\\amphora_blue.png");
							Image imageButtonAmp=imageIconButtonAmp.getImage();
							Image newimgAmp=imageButtonAmp.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
							imageIconButtonAmp=new ImageIcon(newimgAmp);
							button.setIcon(imageIconButtonAmp);
						}
						
						if(obj.getColor()==AmphoraTileColor.BROWN) {
							button.addActionListener(new ActionListener() {
								
								/**
								 * <b>Transformer:</b> Doing some action if a Brown AmphoraTile button is pressed.
								 * <b>Postcondition:</b> Doing some action if a Brown AmphoraTile button is pressed.
								 */
								public void actionPerformed(ActionEvent b) {
									if((!(flagMos||flagSke||flagSt)&&(numPicked<2 && (!flagArch && !flagPro)) || ((flagArch||flagPro)&&((!flagAmp)&&(numPicked<2)))) && flagDrawTiles) {
										EndTurn.setEnabled(true);
										flagAmp=true;
										JLabel label=new JLabel();
										Player player=controller.getPlayers().get(0);
										for(int i=0;i<4;i++) {
											if(controller.seeTurn().equals(controller.getPlayers().get(i).getName())) {
												player=controller.getPlayers().get(i);
												player.getLabels().add(label);
											}
										}
									
										for(int i=0;i<player.getLabels().size();i++) {
											panel_3.add(player.getLabels().get(i));
										}
										ImageIcon imageIconLabelAmp=new ImageIcon("images_2020\\amphora_brown.png");
										Image imageLabelAmp=imageIconLabelAmp.getImage();
										Image newimgLabelAmp=imageLabelAmp.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
										imageIconLabelAmp=new ImageIcon(newimgLabelAmp);
										label.setIcon(imageIconLabelAmp);
										button.setVisible(false);
										for(int i=0;i<4;i++) {
											if(controller.seeTurn().equals(controller.getPlayers().get(i).getName())) {
												controller.getPlayers().get(i).addTile(obj);
											}
										}
										
										for(int i=0;i<controller.board.getAmphoraArea().size();i++) {
											if(controller.board.getAmphoraArea().get(i).getColor()==AmphoraTileColor.BROWN) {
												controller.board.getAmphoraArea().remove(i);
												break;
											}
										}
									
									
										numPicked++;
										if(flagPro) {
											numPicked=1;
										}
										if(numPicked==1 || numPicked==2) {
											flagChoseTile=true;
										}
										if(flagArch) {
											flagAmp=false;
											flagMos=true;
											flagSke=true;
											flagSt=true;
										}
									}
								}
								
								
							});
							button.setBounds(posAmp_x, posAmp_y , 40 , 40);
							ImageIcon imageIconButtonAmp=new ImageIcon("images_2020\\amphora_brown.png");
							Image imageButtonAmp=imageIconButtonAmp.getImage();
							Image newimgAmp=imageButtonAmp.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
							imageIconButtonAmp=new ImageIcon(newimgAmp);
							button.setIcon(imageIconButtonAmp);
						}
						
						if(obj.getColor()==AmphoraTileColor.GREEN) {
							button.addActionListener(new ActionListener() {
								
								/**
								 * <b>Transformer:</b> Doing some action if a Green AmphoraTile button is pressed.
								 * <b>Postcondition:</b> Doing some action if a Green AmphoraTile button is pressed.
								 */
								public void actionPerformed(ActionEvent b) {
									if((!(flagMos||flagSke||flagSt)&&(numPicked<2 && (!flagArch && !flagPro)) || ((flagArch||flagPro)&&((!flagAmp)&&(numPicked<2)))) && flagDrawTiles) {
										EndTurn.setEnabled(true);
										flagAmp=true;
										JLabel label=new JLabel();
										Player player=controller.getPlayers().get(0);
										for(int i=0;i<4;i++) {
											if(controller.seeTurn().equals(controller.getPlayers().get(i).getName())) {
												player=controller.getPlayers().get(i);
												player.getLabels().add(label);
											}
										}
									
										for(int i=0;i<player.getLabels().size();i++) {
											panel_3.add(player.getLabels().get(i));
										}
										ImageIcon imageIconLabelAmp=new ImageIcon("images_2020\\amphora_green.png");
										Image imageLabelAmp=imageIconLabelAmp.getImage();
										Image newimgLabelAmp=imageLabelAmp.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
										imageIconLabelAmp=new ImageIcon(newimgLabelAmp);
										label.setIcon(imageIconLabelAmp);
										button.setVisible(false);
										for(int i=0;i<4;i++) {
											if(controller.seeTurn().equals(controller.getPlayers().get(i).getName())) {
												controller.getPlayers().get(i).addTile(obj);
											}
										}
										
										for(int i=0;i<controller.board.getAmphoraArea().size();i++) {
											if(controller.board.getAmphoraArea().get(i).getColor()==AmphoraTileColor.GREEN) {
												controller.board.getAmphoraArea().remove(i);
												break;
											}
										}
										
										numPicked++;
										if(flagPro) {
											numPicked=1;
										}
										if(numPicked==1 || numPicked==2) {
											flagChoseTile=true;
										}
										if(flagArch) {
											flagAmp=false;
											flagMos=true;
											flagSke=true;
											flagSt=true;
										}
								
									}
								}
								
								
							});
							button.setBounds(posAmp_x, posAmp_y , 40 , 40);
							ImageIcon imageIconButtonAmp=new ImageIcon("images_2020\\amphora_green.png");
							Image imageButtonAmp=imageIconButtonAmp.getImage();
							Image newimgAmp=imageButtonAmp.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
							imageIconButtonAmp=new ImageIcon(newimgAmp);
							button.setIcon(imageIconButtonAmp);
						}
						
						if(obj.getColor()==AmphoraTileColor.PURPLE) {
							button.addActionListener(new ActionListener() {
								
								/**
								 * <b>Transformer:</b> Doing some action if a Purple AmphoraTile button is pressed.
								 * <b>Postcondition:</b> Doing some action if a Purple AmphoraTile button is pressed.
								 */
								public void actionPerformed(ActionEvent b) {
									if((!(flagMos||flagSke||flagSt)&&(numPicked<2 && (!flagArch && !flagPro)) || ((flagArch||flagPro)&&((!flagAmp)&&(numPicked<2)))) && flagDrawTiles) {
										EndTurn.setEnabled(true);
										flagAmp=true;
										JLabel label=new JLabel();
										Player player=controller.getPlayers().get(0);
										for(int i=0;i<4;i++) {
											if(controller.seeTurn().equals(controller.getPlayers().get(i).getName())) {
												player=controller.getPlayers().get(i);
												player.getLabels().add(label);
											}
										}
									
										for(int i=0;i<player.getLabels().size();i++) {
											panel_3.add(player.getLabels().get(i));
										}
										ImageIcon imageIconLabelAmp=new ImageIcon("images_2020\\amphora_purple.png");
										Image imageLabelAmp=imageIconLabelAmp.getImage();
										Image newimgLabelAmp=imageLabelAmp.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
										imageIconLabelAmp=new ImageIcon(newimgLabelAmp);
										label.setIcon(imageIconLabelAmp);
										button.setVisible(false);
										for(int i=0;i<4;i++) {
											if(controller.seeTurn().equals(controller.getPlayers().get(i).getName())) {
												controller.getPlayers().get(i).addTile(obj);
											}
										}
										
										for(int i=0;i<controller.board.getAmphoraArea().size();i++) {
											if(controller.board.getAmphoraArea().get(i).getColor()==AmphoraTileColor.PURPLE) {
												controller.board.getAmphoraArea().remove(i);
												break;
											}
										}
								
										numPicked++;
										if(flagPro) {
											numPicked=1;
										}
										if(numPicked==1 || numPicked==2) {
											flagChoseTile=true;
										}
										if(flagArch) {
											flagAmp=false;
											flagMos=true;
											flagSke=true;
											flagSt=true;
										}
									}
								}
								
								
							});
							button.setBounds(posAmp_x, posAmp_y , 40 , 40);
							ImageIcon imageIconButtonAmp=new ImageIcon("images_2020\\amphora_purple.png");
							Image imageButtonAmp=imageIconButtonAmp.getImage();
							Image newimgAmp=imageButtonAmp.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
							imageIconButtonAmp=new ImageIcon(newimgAmp);
							button.setIcon(imageIconButtonAmp);
						}
						
						if(obj.getColor()==AmphoraTileColor.RED) {
							button.addActionListener(new ActionListener() {
								
								/**
								 * <b>Transformer:</b> Doing some action if a Red AmphoraTile button is pressed.
								 * <b>Postcondition:</b> Doing some action if a Red AmphoraTile button is pressed.
								 */
								public void actionPerformed(ActionEvent b) {
									if((!(flagMos||flagSke||flagSt)&&(numPicked<2 && (!flagArch && !flagPro)) || ((flagArch||flagPro)&&((!flagAmp)&&(numPicked<2)))) && flagDrawTiles) {
										EndTurn.setEnabled(true);
										flagAmp=true;
										JLabel label=new JLabel();
										Player player=controller.getPlayers().get(0);
										for(int i=0;i<4;i++) {
											if(controller.seeTurn().equals(controller.getPlayers().get(i).getName())) {
												player=controller.getPlayers().get(i);
												player.getLabels().add(label);
											}
										}
									
										for(int i=0;i<player.getLabels().size();i++) {
											panel_3.add(player.getLabels().get(i));
										}
										ImageIcon imageIconLabelAmp=new ImageIcon("images_2020\\amphora_red.png");
										Image imageLabelAmp=imageIconLabelAmp.getImage();
										Image newimgLabelAmp=imageLabelAmp.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
										imageIconLabelAmp=new ImageIcon(newimgLabelAmp);
										label.setIcon(imageIconLabelAmp);
										button.setVisible(false);
										for(int i=0;i<4;i++) {
											if(controller.seeTurn().equals(controller.getPlayers().get(i).getName())) {
												controller.getPlayers().get(i).addTile(obj);
											}
										}
										
										for(int i=0;i<controller.board.getAmphoraArea().size();i++) {
											if(controller.board.getAmphoraArea().get(i).getColor()==AmphoraTileColor.RED) {
												controller.board.getAmphoraArea().remove(i);
												break;
											}
										}
										
										numPicked++;
										if(flagPro) {
											numPicked=1;
										}
										if(numPicked==1 || numPicked==2) {
											flagChoseTile=true;
										}
										if(flagArch) {
											flagAmp=false;
											flagMos=true;
											flagSke=true;
											flagSt=true;
										}
									
									}
								}
								
								
							});
							button.setBounds(posAmp_x, posAmp_y , 40 , 40);
							ImageIcon imageIconButtonAmp=new ImageIcon("C:\\Users\\karag\\eclipse-workspace\\Project Phase A\\images_2020\\amphora_red.png");
							Image imageButtonAmp=imageIconButtonAmp.getImage();
							Image newimgAmp=imageButtonAmp.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
							imageIconButtonAmp=new ImageIcon(newimgAmp);
							button.setIcon(imageIconButtonAmp);
						}
						
						if(obj.getColor()==AmphoraTileColor.YELLOW) {
							button.addActionListener(new ActionListener() {
								
								/**
								 * <b>Transformer:</b> Doing some action if a Yellow AmphoraTile button is pressed.
								 * <b>Postcondition:</b> Doing some action if a Yellow AmphoraTile button is pressed.
								 */
								public void actionPerformed(ActionEvent b) {
									if((!(flagMos||flagSke||flagSt)&&(numPicked<2 && (!flagArch && !flagPro)) || ((flagArch||flagPro)&&((!flagAmp)&&(numPicked<2)))) && flagDrawTiles) {
										EndTurn.setEnabled(true);
										flagAmp=true;
										JLabel label=new JLabel();
										Player player=controller.getPlayers().get(0);
										for(int i=0;i<4;i++) {
											if(controller.seeTurn().equals(controller.getPlayers().get(i).getName())) {
												player=controller.getPlayers().get(i);
												player.getLabels().add(label);
											}
										}
										
										for(int i=0;i<player.getLabels().size();i++) {
											panel_3.add(player.getLabels().get(i));
										}
										ImageIcon imageIconLabelAmp=new ImageIcon("images_2020\\amphora_yellow.png");
										Image imageLabelAmp=imageIconLabelAmp.getImage();
										Image newimgLabelAmp=imageLabelAmp.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
										imageIconLabelAmp=new ImageIcon(newimgLabelAmp);
										label.setIcon(imageIconLabelAmp);
										button.setVisible(false);
										for(int i=0;i<4;i++) {
											if(controller.seeTurn().equals(controller.getPlayers().get(i).getName())) {
												controller.getPlayers().get(i).addTile(obj);
											}
										}
										
										for(int i=0;i<controller.board.getAmphoraArea().size();i++) {
											if(controller.board.getAmphoraArea().get(i).getColor()==AmphoraTileColor.YELLOW) {
												controller.board.getAmphoraArea().remove(i);
												break;
											}
										}
										
										numPicked++;
										if(flagPro) {
											numPicked=1;
										}
										if(numPicked==1 || numPicked==2) {
											flagChoseTile=true;
										}
										if(flagArch) {
											flagAmp=false;
											flagMos=true;
											flagSke=true;
											flagSt=true;
										}
									
									}
								}
								
								
							});
							button.setBounds(posAmp_x, posAmp_y , 40 , 40);
							ImageIcon imageIconButtonAmp=new ImageIcon("images_2020\\amphora_yellow.png");
							Image imageButtonAmp=imageIconButtonAmp.getImage();
							Image newimgAmp=imageButtonAmp.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
							imageIconButtonAmp=new ImageIcon(newimgAmp);
							button.setIcon(imageIconButtonAmp);
						}
						
					}
					
					if(controller.getAllTiles().getBag().get(i) instanceof SkeletonTile) {
						if(posSke_x<=505) {
							posSke_x=posSke_x+35;
							if(posSke_x==540) {
								posSke_y=posSke_y-40;
								posSke_x=365;
							}
						}
						JButton button=new JButton();
						SkeletonTile obj=(SkeletonTile)controller.getAllTiles().getBag().get(i);
						SkeTileButtons.add(button);
						panel.add(button);
						if(obj.getSize().equals("big") && obj.getBodyPart().equals("top")) {
							button.addActionListener(new ActionListener() {
								
								/**
								 * <b>Transformer:</b> Doing some action if a button of the top of a big Skeleton is pressed.
								 * <b>Postcondition:</b> Doing some action if a button of the top of a big Skeleton is pressed
								 */
								public void actionPerformed(ActionEvent b) {
									if((!(flagMos||flagAmp||flagSt)&&(numPicked<2 && (!flagArch && !flagPro)) || ((flagArch||flagPro)&&((!flagSke)&&(numPicked<2)))) && flagDrawTiles) {
										EndTurn.setEnabled(true);
										flagSke=true;
										JLabel label=new JLabel();
										Player player=controller.getPlayers().get(0);
										for(int i=0;i<4;i++) {
											if(controller.seeTurn().equals(controller.getPlayers().get(i).getName())) {
												player=controller.getPlayers().get(i);
												player.getLabels().add(label);
											}
										}
									
										for(int i=0;i<player.getLabels().size();i++) {
											panel_3.add(player.getLabels().get(i));
										}
										ImageIcon imageIconLabelSke=new ImageIcon("images_2020\\skeleton_big_top.png");
										Image imageLabelSke=imageIconLabelSke.getImage();
										Image newimgLabelSke=imageLabelSke.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
										imageIconLabelSke=new ImageIcon(newimgLabelSke);
										label.setIcon(imageIconLabelSke);
										button.setVisible(false);
										for(int i=0;i<4;i++) {
											if(controller.seeTurn().equals(controller.getPlayers().get(i).getName())) {
												controller.getPlayers().get(i).addTile(obj);
											}
										}
										
										for(int i=0;i<controller.board.getSkeletonArea().size();i++) {
											if(controller.board.getSkeletonArea().get(i).getSize().equals("big") && controller.board.getSkeletonArea().get(i).getBodyPart().equals("top")) {
												controller.board.getSkeletonArea().remove(i);
												break;
											}
										}
										
										numPicked++;
										if(flagPro) {
											numPicked=1;
										}
										if(numPicked==1 || numPicked==2) {
											flagChoseTile=true;
										}
										if(flagArch) {
											flagSke=false;
											flagMos=true;
											flagAmp=true;
											flagSt=true;
										}
									
									}
								}
								
								
							});
							button.setBounds(posSke_x, posSke_y , 40 , 40);
							ImageIcon imageIconButtonSke=new ImageIcon("images_2020\\skeleton_big_top.png");
							Image imageButtonSke=imageIconButtonSke.getImage();
							Image newimgSke=imageButtonSke.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
							imageIconButtonSke=new ImageIcon(newimgSke);
							button.setIcon(imageIconButtonSke);
						}
						
						if(obj.getSize().equals("big") && obj.getBodyPart().equals("bottom")) {
							button.addActionListener(new ActionListener() {
								
								/**
								 * <b>Transformer:</b> Doing some action if a button of the bottom of a big Skeleton is pressed.
								 * <b>Postcondition:</b> Doing some action if a button of the bottom of a big Skeleton is pressed
								 */
								public void actionPerformed(ActionEvent b) {
									if((!(flagMos||flagAmp||flagSt)&&(numPicked<2 && (!flagArch && !flagPro)) || ((flagArch||flagPro)&&((!flagSke)&&(numPicked<2)))) && flagDrawTiles) {
										EndTurn.setEnabled(true);
										flagSke=true;
										JLabel label=new JLabel();
										Player player=controller.getPlayers().get(0);
										for(int i=0;i<4;i++) {
											if(controller.seeTurn().equals(controller.getPlayers().get(i).getName())) {
												player=controller.getPlayers().get(i);
												player.getLabels().add(label);
											}
										}
									
										for(int i=0;i<player.getLabels().size();i++) {
											panel_3.add(player.getLabels().get(i));
										}
										ImageIcon imageIconLabelSke=new ImageIcon("images_2020\\skeleton_big_bottom.png");
										Image imageLabelSke=imageIconLabelSke.getImage();
										Image newimgLabelSke=imageLabelSke.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
										imageIconLabelSke=new ImageIcon(newimgLabelSke);
										label.setIcon(imageIconLabelSke);
										button.setVisible(false);
										for(int i=0;i<4;i++) {
											if(controller.seeTurn().equals(controller.getPlayers().get(i).getName())) {
												controller.getPlayers().get(i).addTile(obj);
											}
										}
										
										for(int i=0;i<controller.board.getSkeletonArea().size();i++) {
											if(controller.board.getSkeletonArea().get(i).getSize().equals("big") && controller.board.getSkeletonArea().get(i).getBodyPart().equals("bottom")) {
												controller.board.getSkeletonArea().remove(i);
												break;
											}
										}
										
										numPicked++;
										if(flagPro) {
											numPicked=1;
										}
										if(numPicked==1 || numPicked==2) {
											flagChoseTile=true;
										}
										if(flagArch) {
											flagSke=false;
											flagMos=true;
											flagAmp=true;
											flagSt=true;
										}
									
									}
								}
								
								
							});
							button.setBounds(posSke_x, posSke_y , 40 , 40);
							ImageIcon imageIconButtonSke=new ImageIcon("images_2020\\skeleton_big_bottom.png");
							Image imageButtonSke=imageIconButtonSke.getImage();
							Image newimgSke=imageButtonSke.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
							imageIconButtonSke=new ImageIcon(newimgSke);
							button.setIcon(imageIconButtonSke);
						}
						
						if(obj.getSize().equals("small") && obj.getBodyPart().equals("top")) {
							button.addActionListener(new ActionListener() {
								
								/**
								 * <b>Transformer:</b> Doing some action if a button of the top of a small Skeleton is pressed.
								 * <b>Postcondition:</b> Doing some action if a button of the top of a small Skeleton is pressed
								 */
								public void actionPerformed(ActionEvent b) {
									if((!(flagMos||flagAmp||flagSt)&&(numPicked<2 && (!flagArch && !flagPro)) || ((flagArch||flagPro)&&((!flagSke)&&(numPicked<2)))) && flagDrawTiles) {
										EndTurn.setEnabled(true);
										flagSke=true;
										JLabel label=new JLabel();
										Player player=controller.getPlayers().get(0);
										for(int i=0;i<4;i++) {
											if(controller.seeTurn().equals(controller.getPlayers().get(i).getName())) {
												player=controller.getPlayers().get(i);
												player.getLabels().add(label);
											}
										}
									
										for(int i=0;i<player.getLabels().size();i++) {
											panel_3.add(player.getLabels().get(i));
										}
										ImageIcon imageIconLabelSke=new ImageIcon("images_2020\\skeleton_small_top.png");
										Image imageLabelSke=imageIconLabelSke.getImage();
										Image newimgLabelSke=imageLabelSke.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
										imageIconLabelSke=new ImageIcon(newimgLabelSke);
										label.setIcon(imageIconLabelSke);
										button.setVisible(false);
										for(int i=0;i<4;i++) {
											if(controller.seeTurn().equals(controller.getPlayers().get(i).getName())) {
												controller.getPlayers().get(i).addTile(obj);
											}
										}
										
										for(int i=0;i<controller.board.getSkeletonArea().size();i++) {
											if(controller.board.getSkeletonArea().get(i).getSize().equals("small") && controller.board.getSkeletonArea().get(i).getBodyPart().equals("top")) {
												controller.board.getSkeletonArea().remove(i);
												break;
											}
										}
										
										numPicked++;
										if(flagPro) {
											numPicked=1;
										}
										if(numPicked==1 || numPicked==2) {
											flagChoseTile=true;
										}
										if(flagArch) {
											flagSke=false;
											flagMos=true;
											flagAmp=true;
											flagSt=true;
										}
									
									}
								}
								
								
							});
							button.setBounds(posSke_x, posSke_y , 40 , 40);
							ImageIcon imageIconButtonSke=new ImageIcon("images_2020\\skeleton_small_top.png");
							Image imageButtonSke=imageIconButtonSke.getImage();
							Image newimgSke=imageButtonSke.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
							imageIconButtonSke=new ImageIcon(newimgSke);
							button.setIcon(imageIconButtonSke);
						}
						
						if(obj.getSize().equals("small") && obj.getBodyPart().equals("bottom")) {
							button.addActionListener(new ActionListener() {
								
								/**
								 * <b>Transformer:</b> Doing some action if a button of the botttom of a small Skeleton is pressed.
								 * <b>Postcondition:</b> Doing some action if a button of the bottom of a small Skeleton is pressed
								 */
								public void actionPerformed(ActionEvent b) {
									if((!(flagMos||flagAmp||flagSt)&&(numPicked<2 && (!flagArch && !flagPro)) || ((flagArch||flagPro)&&((!flagSke)&&(numPicked<2)))) && flagDrawTiles) {
										EndTurn.setEnabled(true);
										flagSke=true;
										JLabel label=new JLabel();
										Player player=controller.getPlayers().get(0);
										for(int i=0;i<4;i++) {
											if(controller.seeTurn().equals(controller.getPlayers().get(i).getName())) {
												player=controller.getPlayers().get(i);
												player.getLabels().add(label);
											}
										}
									
										for(int i=0;i<player.getLabels().size();i++) {
											panel_3.add(player.getLabels().get(i));
										}
										ImageIcon imageIconLabelSke=new ImageIcon("images_2020\\skeleton_small_bottom.png");
										Image imageLabelSke=imageIconLabelSke.getImage();
										Image newimgLabelSke=imageLabelSke.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
										imageIconLabelSke=new ImageIcon(newimgLabelSke);
										label.setIcon(imageIconLabelSke);
										button.setVisible(false);
										for(int i=0;i<4;i++) {
											if(controller.seeTurn().equals(controller.getPlayers().get(i).getName())) {
												controller.getPlayers().get(i).addTile(obj);
											}
										}
										
										for(int i=0;i<controller.board.getSkeletonArea().size();i++) {
											if(controller.board.getSkeletonArea().get(i).getSize().equals("small") && controller.board.getSkeletonArea().get(i).getBodyPart().equals("bottom")) {
												controller.board.getSkeletonArea().remove(i);
												break;
											}
										}
										
										numPicked++;
										if(flagPro) {
											numPicked=1;
										}
										if(numPicked==1 || numPicked==2) {
											flagChoseTile=true;
										}
										if(flagArch) {
											flagSke=false;
											flagMos=true;
											flagAmp=true;
											flagSt=true;
										}
									
									}
								}
								
								
								
							});
							button.setBounds(posSke_x, posSke_y , 40 , 40);
							ImageIcon imageIconButtonSke=new ImageIcon("images_2020\\skeleton_small_bottom.png");
							Image imageButtonSke=imageIconButtonSke.getImage();
							Image newimgSke=imageButtonSke.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
							imageIconButtonSke=new ImageIcon(newimgSke);
							button.setIcon(imageIconButtonSke);
						}
						
					}
					
					if(controller.getAllTiles().getBag().get(i) instanceof CaryatidTile) {
						if(posSt_x<=540) {
							posSt_x=posSt_x+35;
							if(posSt_x==540) {
								posSt_y=posSt_y+40;
								posSt_x=365;
							}
						}
						JButton button=new JButton();
						CaryatidTile obj=(CaryatidTile) controller.getAllTiles().getBag().get(i);
						StTileButtons.add(button);
						button.addActionListener(new ActionListener() {
							
							/**
							 * <b>Transformer:</b> Doing some action if a CaryatidTile button is pressed.
							 * <b>Postcondition:</b> Doing some action if a CaryatidTile button is pressed.
							 */
							public void actionPerformed(ActionEvent b) {
								if((!(flagMos||flagAmp||flagSke)&&(numPicked<2 && (!flagArch && !flagPro)) || ((flagArch||flagPro)&&((!flagSt)&&(numPicked<2)))) && flagDrawTiles) {
									EndTurn.setEnabled(true);
									flagSt=true;
									JLabel label=new JLabel();
									Player player=controller.getPlayers().get(0);
									for(int i=0;i<4;i++) {
										if(controller.seeTurn().equals(controller.getPlayers().get(i).getName())) {
											player=controller.getPlayers().get(i);
											player.getLabels().add(label);
										}
									}
								
									for(int i=0;i<player.getLabels().size();i++) {
										panel_3.add(player.getLabels().get(i));
									}
									ImageIcon imageIconLabelCar=new ImageIcon("images_2020\\caryatid.png");
									Image imageLabelCar=imageIconLabelCar.getImage();
									Image newimgLabelCar=imageLabelCar.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
									imageIconLabelCar=new ImageIcon(newimgLabelCar);
									label.setIcon(imageIconLabelCar);
									button.setVisible(false);
									for(int i=0;i<4;i++) {
										if(controller.seeTurn().equals(controller.getPlayers().get(i).getName())) {
											controller.getPlayers().get(i).addTile(obj);
										}
									}
									
									for(int i=0;i<controller.board.getStatueArea().size();i++) {
										if(controller.board.getStatueArea().get(i) instanceof CaryatidTile) {
											controller.board.getStatueArea().remove(i);
											break;
										}
									}
									
									numPicked++;
									if(flagPro) {
										numPicked=1;
									}		
									if(numPicked==1 || numPicked==2) {
										flagChoseTile=true;
									}
									if(flagArch) {
										flagSt=false;
										flagMos=true;
										flagAmp=true;
										flagSke=true;
									}
								
								}
								
							}
							
						});
						panel.add(button);
						button.setBounds(posSt_x, posSt_y , 40, 40);
						ImageIcon imageIconButtonCar=new ImageIcon("images_2020\\caryatid.png");
						Image imageButtonCar=imageIconButtonCar.getImage();
						Image newimgCar=imageButtonCar.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
						imageIconButtonCar=new ImageIcon(newimgCar);
						button.setIcon(imageIconButtonCar);
					}
					
					if(controller.getAllTiles().getBag().get(i) instanceof SphinxTile) {
						if(posSt_x<=540) {
							posSt_x=posSt_x+35;
							if(posSt_x==540) {
								posSt_y=posSt_y+40;
								posSt_x=365;
							}
						}
						JButton button=new JButton();
						SphinxTile obj=(SphinxTile) controller.getAllTiles().getBag().get(i);
						StTileButtons.add(button);
						button.addActionListener(new ActionListener() {
							
							/**
							 * <b>Transformer:</b> Doing some action if a SphinxTile button is pressed.
							 * <b>Postcondition:</b> Doing some action if a SphinxTile button is pressed.
							 */
							public void actionPerformed(ActionEvent b) {
								if((!(flagMos||flagAmp||flagSke)&&(numPicked<2 && (!flagArch && !flagPro)) || ((flagArch||flagPro)&&((!flagSt)&&(numPicked<2)))) && flagDrawTiles) {
									EndTurn.setEnabled(true);
									flagSt=true;
									JLabel label=new JLabel();
									Player player=controller.getPlayers().get(0);
									for(int i=0;i<4;i++) {
										if(controller.seeTurn().equals(controller.getPlayers().get(i).getName())) {
											player=controller.getPlayers().get(i);
											player.getLabels().add(label);
										}
									}
								
									for(int i=0;i<player.getLabels().size();i++) {
										panel_3.add(player.getLabels().get(i));
									}
									ImageIcon imageIconLabelSph=new ImageIcon("images_2020\\sphinx.png");
									Image imageLabelSph=imageIconLabelSph.getImage();
									Image newimgLabelSph=imageLabelSph.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
									imageIconLabelSph=new ImageIcon(newimgLabelSph);
									label.setIcon(imageIconLabelSph);
									button.setVisible(false);
									for(int i=0;i<4;i++) {
										if(controller.seeTurn().equals(controller.getPlayers().get(i).getName())) {
											controller.getPlayers().get(i).addTile(obj);
										}
									}
									
									for(int i=0;i<controller.board.getStatueArea().size();i++) {
										if(controller.board.getStatueArea().get(i) instanceof SphinxTile) {
											controller.board.getStatueArea().remove(i);
											break;
										}
									}
									
									numPicked++;
									if(flagPro) {
										numPicked=1;
									}
									if(numPicked==1 || numPicked==2) {
										flagChoseTile=true;
									}
									if(flagArch) {
										flagSt=false;
										flagMos=true;
										flagAmp=true;
										flagSke=true;
									}
								
								}
							}
							
							
						});
						panel.add(button);
						button.setBounds(posSt_x, posSt_y , 40, 40);
						ImageIcon imageIconButtonSp=new ImageIcon("images_2020\\sphinx.png");
						Image imageButtonSp=imageIconButtonSp.getImage();
						Image newimgSp=imageButtonSp.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
						imageIconButtonSp=new ImageIcon(newimgSp);
						button.setIcon(imageIconButtonSp);
					}
					
					if(controller.getAllTiles().getBag().get(i) instanceof LandslideTile) {
						JButton button=new JButton();
						panel.add(button);
						button.setBounds(posLand_x , posLand_y , 39, 39);
						ImageIcon imageIconLand=new ImageIcon("images_2020\\landslide.png");
						Image imageLand=imageIconLand.getImage();
						Image newimgLand=imageLand.getScaledInstance(39, 39, java.awt.Image.SCALE_SMOOTH);
						imageIconLand=new ImageIcon(newimgLand);
						button.setIcon(imageIconLand);
						if(posLand_x<=348) {
							posLand_x=posLand_x+39;
							if(posLand_x==348) {
								posLand_y=posLand_y+39;
								posLand_x=192;
							}
						}
					}
				}
				for(int i=0;i<bagLength;i++) {
					controller.getAllTiles().getBag().remove(0);
				}
				
				
				DrawTiles.setEnabled(false);
				frmAmphipolis.setVisible(true);
				if(controller.board.End()) {
					frmAmphipolis.dispose();
					controller.Standings();
					controller.winnerFrame();
				}
			}
		
		});
		
		
		EndTurn.addActionListener(new ActionListener() {
			
			/**
			 * <b>Transformer:</b> Doing some action after pressing the "End Turn" button.
			 * <B>Postcondition:</b> Doing some action after pressing the "End Turn" button.
			 */
			public void actionPerformed(ActionEvent arg0) {
				flagChoseTile=false;
				flagDrawTiles=false;
				
				flagArch=false;
				flagAssist=false;
				flagDig=false;
				flagPro=false;
				numPicked=0;
				for(int j=0;j<MosTileButtons.size();j++) {
					MosTileButtons.get(j).setEnabled(true);
				}
				
				for(int j=0;j<AmpTileButtons.size();j++) {
					AmpTileButtons.get(j).setEnabled(true);
				}
				
				for(int j=0;j<SkeTileButtons.size();j++) {
					SkeTileButtons.get(j).setEnabled(true);
				}
				
				for(int j=0;j<StTileButtons.size();j++) {
					StTileButtons.get(j).setEnabled(true);
				}
				if(controller.getAllTiles().getBag().size()==0) {
					DrawTiles.setEnabled(false);
				}else {
					DrawTiles.setEnabled(true);
				}
				ArchaeologistButton.setEnabled(false);
				AssistantButton.setEnabled(false);
				DiggerButton.setEnabled(false);
				ProfessorButton.setEnabled(false);
				Player player=controller.getPlayers().get(0);
				controller.getSetTurn();
				lblNewLabel_6.setText("Player "+ controller.getBoardTurn());
				for(int i=0;i<4;i++) {
					player=controller.getPlayers().get(i);
					if(player.getName().equals(controller.getBoardTurn())) {
						for(int j=0;j<player.getCharacter().size();j++) {
							if(player.getCharacter().get(j) instanceof Archaeologist) {
								ArchaeologistButton.setEnabled(true);
							}
							if(player.getCharacter().get(j) instanceof Assistant) {
								AssistantButton.setEnabled(true);
							}
							if(player.getCharacter().get(j) instanceof Digger) {
								DiggerButton.setEnabled(true);
							}
							if(player.getCharacter().get(j) instanceof Professor) {
								ProfessorButton.setEnabled(true);
							}
						}
						break;
					}
				}
				
				panel_3.removeAll();
				panel_3.repaint();
				for(int i=0;i<4;i++) {
					if(controller.seeTurn().equals(controller.getPlayers().get(i).getName())) {
						Player cplayer=controller.getPlayers().get(i);
						for(int j=0;j<player.getLabels().size();j++) {
							panel_3.add(cplayer.getLabels().get(j));
						}
						break;
					}
				}
				flagMos=false;
				flagAmp=false;
				flagSke=false;
				flagSt=false;
				EndTurn.setEnabled(false);
			}
			
		});
		
		EndTurn.setBounds(0, 437, 205, 45);
		panel_1.add(EndTurn);
		
		
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(46, 58, 103, 14);
		panel_1.add(lblNewLabel_1);
		
		
		ImageIcon imageIcon2=new ImageIcon("images_2020\\archaeologist.png");
		Image image2=imageIcon2.getImage();
		Image newimg2=image2.getScaledInstance(83, 138, java.awt.Image.SCALE_SMOOTH);
		imageIcon2=new ImageIcon(newimg2);
		ImageIcon imageIcon3=new ImageIcon("images_2020\\assistant.png");
		Image image3=imageIcon3.getImage();
		Image newimg3=image3.getScaledInstance(83, 138, java.awt.Image.SCALE_SMOOTH);
		imageIcon3=new ImageIcon(newimg3);
		ImageIcon imageIcon4=new ImageIcon("images_2020\\digger.png");
		Image image4=imageIcon4.getImage();
		Image newimg4=image4.getScaledInstance(83, 138, java.awt.Image.SCALE_SMOOTH);
		imageIcon4=new ImageIcon(newimg4);
		ImageIcon imageIcon5=new ImageIcon("images_2020\\professor.png");
		Image image5=imageIcon5.getImage();
		Image newimg5=image5.getScaledInstance(83, 138, java.awt.Image.SCALE_SMOOTH);
		imageIcon5=new ImageIcon(newimg5);
			
		
		
		if(controller.hasStarted()==false) {
	
			for(int i=0;i<controller.getAllTiles().getBag().size();i++) {
				if(controller.getAllTiles().getBag().get(i) instanceof MosaicTile) {
					controller.board.getMosaicArea().add((MosaicTile)controller.getAllTiles().getBag().get(i));
					JButton button=new JButton();
					MosaicTile obj=(MosaicTile) controller.getAllTiles().getBag().get(i);
					MosTileButtons.add(button);
					if(obj.getColor()==MosaicTileColor.GREEN) {
						button.addActionListener(new ActionListener() {
							
							/**
							 * <b>Transformer:</b> Doing some action if the first  MosaicTile button(in case it is Green) is pressed.
							 * <b>Postcondition:</b> Doing some action if the first  MosaicTile button(in case it is Green) is pressed.
							 */
							public void actionPerformed(ActionEvent b) {
								if(((!(flagAmp||flagSke||flagSt)&&(numPicked<2)&&(!flagArch && !flagPro)) || ((flagArch||flagPro)&&((!flagMos)&&(numPicked<2)))) && flagDrawTiles) {
									EndTurn.setEnabled(true);
									flagMos=true;
									JLabel label=new JLabel();
									Player player=controller.getPlayers().get(0);
									for(int i=0;i<4;i++) {
										if(controller.seeTurn().equals(controller.getPlayers().get(i).getName())) {
											player=controller.getPlayers().get(i);
											player.getLabels().add(label);
										}
									}

									for(int i=0;i<player.getLabels().size();i++) {
										panel_3.add(player.getLabels().get(i));
									}
									ImageIcon imageIconLabelMos=new ImageIcon("images_2020\\mosaic_green.png");
									Image imageLabelMos=imageIconLabelMos.getImage();
									Image newimgLabelMos=imageLabelMos.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
									imageIconLabelMos=new ImageIcon(newimgLabelMos);
									label.setIcon(imageIconLabelMos);
									button.setVisible(false);
									for(int i=0;i<4;i++) {
										if(controller.seeTurn().equals(controller.getPlayers().get(i).getName())) {
											controller.getPlayers().get(i).addTile(obj);
										}
									}
									
									for(int i=0;i<controller.board.getMosaicArea().size();i++) {
										if(controller.board.getMosaicArea().get(i).getColor()==MosaicTileColor.GREEN) {
											controller.board.getMosaicArea().remove(i);
											break;
										}
									}
									
									numPicked++;
									if(flagPro) {
										numPicked=1;
									}
									if(numPicked==1 || numPicked==2) {
										flagChoseTile=true;
									}
									if(flagArch) {
										flagMos=false;
										flagAmp=true;
										flagSke=true;
										flagSt=true;
									}
								
								}
							}
							
							
						});
						button.setBounds(15, 10 , 40 , 40);
						ImageIcon imageIconButtonMos=new ImageIcon("images_2020\\mosaic_green.png");
						Image imageButtonMos=imageIconButtonMos.getImage();
						Image newimgMos=imageButtonMos.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
						imageIconButtonMos=new ImageIcon(newimgMos);
						button.setIcon(imageIconButtonMos);
						panel.add(button);
					}
					
					if(obj.getColor()==MosaicTileColor.RED) {
						button.addActionListener(new ActionListener() {
							
							/**
							 * <b>Transformer:</b> Doing some action if the first  MosaicTile button(in case it is Red) is pressed.
							 * <b>Postcondition:</b> Doing some action if the first  MosaicTile button(in case it is Red) is pressed.
							 */
							public void actionPerformed(ActionEvent b) {
								if(((!(flagAmp||flagSke||flagSt)&&(numPicked<2)&&(!flagArch && !flagPro)) || ((flagArch||flagPro)&&((!flagMos)&&(numPicked<2)))) && flagDrawTiles) {
									EndTurn.setEnabled(true);
									flagMos=true;
									JLabel label=new JLabel();
									Player player=controller.getPlayers().get(0);
									for(int i=0;i<4;i++) {
										if(controller.seeTurn().equals(controller.getPlayers().get(i).getName())) {
											player=controller.getPlayers().get(i);
											player.getLabels().add(label);
										}
									}
								
									for(int i=0;i<player.getLabels().size();i++) {
										panel_3.add(player.getLabels().get(i));
									}
									ImageIcon imageIconLabelMos=new ImageIcon("images_2020\\mosaic_red.png");
									Image imageLabelMos=imageIconLabelMos.getImage();
									Image newimgLabelMos=imageLabelMos.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
									imageIconLabelMos=new ImageIcon(newimgLabelMos);
									label.setIcon(imageIconLabelMos);
									button.setVisible(false);
									for(int i=0;i<4;i++) {
										if(controller.seeTurn().equals(controller.getPlayers().get(i).getName())) {
											controller.getPlayers().get(i).addTile(obj);
										}
									}
									
									for(int i=0;i<controller.board.getMosaicArea().size();i++) {
										if(controller.board.getMosaicArea().get(i).getColor()==MosaicTileColor.RED) {
											controller.board.getMosaicArea().remove(i);
											break;
										}
									}
									
									numPicked++;
									if(flagPro) {
										numPicked=1;
									}
									if(numPicked==1 || numPicked==2) {
										flagChoseTile=true;
									}
									if(flagArch) {
										flagMos=false;
										flagAmp=true;
										flagSke=true;
										flagSt=true;
									}
								
								}
							}
							
							
						});
						button.setBounds(15, 10 , 40 , 40);
						ImageIcon imageIconButtonMos=new ImageIcon("images_2020\\mosaic_red.png");
						Image imageButtonMos=imageIconButtonMos.getImage();
						Image newimgMos=imageButtonMos.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
						imageIconButtonMos=new ImageIcon(newimgMos);
						button.setIcon(imageIconButtonMos);
						panel.add(button);
					}
					
					if(obj.getColor()==MosaicTileColor.YELLOW) {
						button.addActionListener(new ActionListener() {
							
							/**
							 * <b>Transformer:</b> Doing some action if the first  MosaicTile button(in case it is Yellow) is pressed.
							 * <b>Postcondition:</b> Doing some action if the first  MosaicTile button(in case it is Yellow) is pressed.
							 */
							public void actionPerformed(ActionEvent b) {
								if(((!(flagAmp||flagSke||flagSt)&&(numPicked<2)&&(!flagArch && !flagPro)) || ((flagArch||flagPro)&&((!flagMos)&&(numPicked<2)))) && flagDrawTiles) {
									EndTurn.setEnabled(true);
									flagMos=true;
									JLabel label=new JLabel();
									Player player=controller.getPlayers().get(0);
									for(int i=0;i<4;i++) {
										if(controller.seeTurn().equals(controller.getPlayers().get(i).getName())) {
											player=controller.getPlayers().get(i);
											player.getLabels().add(label);
										}
									}
								
									for(int i=0;i<player.getLabels().size();i++) {
										panel_3.add(player.getLabels().get(i));
									}
									ImageIcon imageIconLabelMos=new ImageIcon("images_2020\\mosaic_yellow.png");
									Image imageLabelMos=imageIconLabelMos.getImage();
									Image newimgLabelMos=imageLabelMos.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
									imageIconLabelMos=new ImageIcon(newimgLabelMos);
									label.setIcon(imageIconLabelMos);
									button.setVisible(false);
									for(int i=0;i<4;i++) {
										if(controller.seeTurn().equals(controller.getPlayers().get(i).getName())) {
											controller.getPlayers().get(i).addTile(obj);
										}
									}
									
									for(int i=0;i<controller.board.getMosaicArea().size();i++) {
										if(controller.board.getMosaicArea().get(i).getColor()==MosaicTileColor.YELLOW) {
											controller.board.getMosaicArea().remove(i);
											break;
										}
									}
								
									numPicked++;
									if(flagPro) {
										numPicked=1;
									}
									if(numPicked==1 || numPicked==2) {
										flagChoseTile=true;
									}
									if(flagArch) {
										flagMos=false;
										flagAmp=true;
										flagSke=true;
										flagSt=true;
									}
								
								}
							}
							
						});
						button.setBounds(15, 10 , 40 , 40);
						ImageIcon imageIconButtonMos=new ImageIcon("images_2020\\mosaic_yellow.png");
						Image imageButtonMos=imageIconButtonMos.getImage();
						Image newimgMos=imageButtonMos.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
						imageIconButtonMos=new ImageIcon(newimgMos);
						button.setIcon(imageIconButtonMos);
						panel.add(button);
					}
					panel.add(button);
					
					controller.getAllTiles().getBag().remove(i);
					break;
				}
			}
			
			for(int i=0;i<controller.getAllTiles().getBag().size();i++) {
				if(controller.getAllTiles().getBag().get(i) instanceof CaryatidTile || controller.getAllTiles().getBag().get(i) instanceof SphinxTile) {
					if(controller.getAllTiles().getBag().get(i) instanceof CaryatidTile) {
						controller.board.getStatueArea().add((CaryatidTile)controller.getAllTiles().getBag().get(i));
						CaryatidTile obj=(CaryatidTile) controller.getAllTiles().getBag().get(i);
						JButton button=new JButton();
						StTileButtons.add(button);
						button.addActionListener(new ActionListener() {
							
							/**
							 * <b>Transformer:</b> Doing some action if the first StatueTile button(in case it is CaryatidTile) is pressed.
							 * <b>Postcondition:</b> Doing some action if the first StatueTile button(in case it is CaryatidTile) is pressed.
							 */
							public void actionPerformed(ActionEvent b) {
								if((!(flagMos||flagAmp||flagSke)&&(numPicked<2 && (!flagArch && !flagPro)) || ((flagArch||flagPro)&&((!flagSt)&&(numPicked<2)))) && flagDrawTiles) {
									EndTurn.setEnabled(true);
									flagSt=true;
									JLabel label=new JLabel();
									Player player=controller.getPlayers().get(0);
									for(int i=0;i<4;i++) {
										if(controller.seeTurn().equals(controller.getPlayers().get(i).getName())) {
											player=controller.getPlayers().get(i);
											player.getLabels().add(label);
										}
									}
								
									for(int i=0;i<player.getLabels().size();i++) {
										panel_3.add(player.getLabels().get(i));
									}
									ImageIcon imageIconLabelCar=new ImageIcon("images_2020\\caryatid.png");
									Image imageLabelCar=imageIconLabelCar.getImage();
									Image newimgLabelCar=imageLabelCar.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
									imageIconLabelCar=new ImageIcon(newimgLabelCar);
									label.setIcon(imageIconLabelCar);
									button.setVisible(false);
									for(int i=0;i<4;i++) {
										if(controller.seeTurn().equals(controller.getPlayers().get(i).getName())) {
											controller.getPlayers().get(i).addTile(obj);
										}
									}
									
									for(int i=0;i<controller.board.getStatueArea().size();i++) {
										if(controller.board.getStatueArea().get(i) instanceof CaryatidTile) {
											controller.board.getStatueArea().remove(i);
											break;
										}
									}
									
									numPicked++;
									if(flagPro) {
										numPicked=1;
									}
									if(numPicked==1 || numPicked==2) {
										flagChoseTile=true;
									}
									if(flagArch) {
										flagSt=false;
										flagMos=true;
										flagAmp=true;
										flagSke=true;
									}
								}
								
								
							}
						});
						button.setBounds(365, 10 , 40, 40);
						ImageIcon imageIconButtonCar=new ImageIcon("images_2020\\caryatid.png");
						Image imageButtonCar=imageIconButtonCar.getImage();
						Image newimgCar=imageButtonCar.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
						imageIconButtonCar=new ImageIcon(newimgCar);
						button.setIcon(imageIconButtonCar);
						panel.add(button);
					}else if(controller.getAllTiles().getBag().get(i) instanceof SphinxTile) {
						controller.board.getStatueArea().add((SphinxTile)controller.getAllTiles().getBag().get(i));
						JButton button=new JButton();
						SphinxTile obj=(SphinxTile) controller.getAllTiles().getBag().get(i);
						StTileButtons.add(button);
						button.addActionListener(new ActionListener() {
							
							/**
							 * <b>Transformer:</b> Doing some action if the first StatueTile button(in case it is SphinxTile) is pressed.
							 * <b>Postcondition:</b> Doing some action if the first StatueTile button(in case it is SphinxTile) is pressed.
							 */
							public void actionPerformed(ActionEvent b) {
								if((!(flagMos||flagAmp||flagSke)&&(numPicked<2 && (!flagArch && !flagPro)) || ((flagArch||flagPro)&&((!flagSt)&&(numPicked<2)))) && flagDrawTiles) {
									EndTurn.setEnabled(true);
									flagSt=true;
									JLabel label=new JLabel();
									Player player=controller.getPlayers().get(0);
									for(int i=0;i<4;i++) {
										if(controller.seeTurn().equals(controller.getPlayers().get(i).getName())) {
											player=controller.getPlayers().get(i);
											player.getLabels().add(label);
										}
									}
								
									for(int i=0;i<player.getLabels().size();i++) {
										panel_3.add(player.getLabels().get(i));
									}
									ImageIcon imageIconLabelSph=new ImageIcon("images_2020\\sphinx.png");
									Image imageLabelSph=imageIconLabelSph.getImage();
									Image newimgLabelSph=imageLabelSph.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
									imageIconLabelSph=new ImageIcon(newimgLabelSph);
									label.setIcon(imageIconLabelSph);
									button.setVisible(false);
									for(int i=0;i<4;i++) {
										if(controller.seeTurn().equals(controller.getPlayers().get(i).getName())) {
											controller.getPlayers().get(i).addTile(obj);
										}
									}
									
									for(int i=0;i<controller.board.getStatueArea().size();i++) {
										if(controller.board.getStatueArea().get(i) instanceof SphinxTile) {
											controller.board.getStatueArea().remove(i);
											break;
										}
									}
									
									numPicked++;
									if(flagPro) {
										numPicked=1;
									}
									if(numPicked==1 || numPicked==2) {
										flagChoseTile=true;
									}
									if(flagArch) {
										flagSt=false;
										flagMos=true;
										flagAmp=true;
										flagSke=true;
									}
								
								}
							}
							
							
						});
						button.setBounds(365, 10, 40, 40);
						ImageIcon imageIconButtonSp=new ImageIcon("images_2020\\sphinx.png");
						Image imageButtonSp=imageIconButtonSp.getImage();
						Image newimgSp=imageButtonSp.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
						imageIconButtonSp=new ImageIcon(newimgSp);
						button.setIcon(imageIconButtonSp);
						panel.add(button);
						StTileButtons.add(button);
					}
					controller.getAllTiles().getBag().remove(i);
					break;
				}
			}
			
			
			for(int i=0;i<controller.getAllTiles().getBag().size();i++) {
				 if(controller.getAllTiles().getBag().get(i) instanceof SkeletonTile) {
					controller.board.getSkeletonArea().add((SkeletonTile)controller.getAllTiles().getBag().get(i));
					JButton button=new JButton();
					SkeletonTile obj=(SkeletonTile)controller.getAllTiles().getBag().get(i);
					SkeTileButtons.add(button);
					
					if(obj.getSize().equals("big") && obj.getBodyPart().equals("top")) {
						button.addActionListener(new ActionListener() {
							
							/**
							 * <b>Transformer:</b> Doing some action if the first SkeletonTile button(in case it is "big" and "top") is pressed.
							 * <b>Postcondition:</b> Doing some action if the first SkeletonTile button(in case it is "big" and "top") is pressed.
							 */
							public void actionPerformed(ActionEvent b) {
								if((!(flagMos||flagAmp||flagSt)&&(numPicked<2 && (!flagArch && !flagPro)) || ((flagArch||flagPro)&&((!flagSke)&&(numPicked<2)))) && flagDrawTiles) {
									EndTurn.setEnabled(true);
									flagSke=true;
									JLabel label=new JLabel();
									Player player=controller.getPlayers().get(0);
									for(int i=0;i<4;i++) {
										if(controller.seeTurn().equals(controller.getPlayers().get(i).getName())) {
											player=controller.getPlayers().get(i);
											player.getLabels().add(label);
										}
									}
								
									for(int i=0;i<player.getLabels().size();i++) {
										panel_3.add(player.getLabels().get(i));
									}
									ImageIcon imageIconLabelSke=new ImageIcon("images_2020\\skeleton_big_top.png");
									Image imageLabelSke=imageIconLabelSke.getImage();
									Image newimgLabelSke=imageLabelSke.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
									imageIconLabelSke=new ImageIcon(newimgLabelSke);
									label.setIcon(imageIconLabelSke);
									button.setVisible(false);
									for(int i=0;i<4;i++) {
										if(controller.seeTurn().equals(controller.getPlayers().get(i).getName())) {
											controller.getPlayers().get(i).addTile(obj);
										}
									}
									
									for(int i=0;i<controller.board.getSkeletonArea().size();i++) {
										if(controller.board.getSkeletonArea().get(i).getSize().equals("big") && controller.board.getSkeletonArea().get(i).getBodyPart().equals("top")) {
											controller.board.getSkeletonArea().remove(i);
											break;
										}
									}
									
									numPicked++;
									if(flagPro) {
										numPicked=1;
									}
									if(numPicked==1 || numPicked==2) {
										flagChoseTile=true;
									}
									if(flagArch) {
										flagSke=false;
										flagMos=true;
										flagAmp=true;
										flagSt=true;
									}
								}
								
								
							}
							
							
						});
						button.setBounds(365, 429 , 40 , 40);
						ImageIcon imageIconButtonSke=new ImageIcon("images_2020\\skeleton_big_top.png");
						Image imageButtonSke=imageIconButtonSke.getImage();
						Image newimgSke=imageButtonSke.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
						imageIconButtonSke=new ImageIcon(newimgSke);
						button.setIcon(imageIconButtonSke);
						panel.add(button);
					}
					
					if(obj.getSize().equals("big") && obj.getBodyPart().equals("bottom")) {
						button.addActionListener(new ActionListener() {
							
							/**
							 * <b>Transformer:</b> Doing some action if the first SkeletonTile button(in case it is "big" and "bottom") is pressed.
							 * <b>Postcondition:</b> Doing some action if the first SkeletonTile button(in case it is "big" and "bottom") is pressed.
							 */
							public void actionPerformed(ActionEvent b) {
								if((!(flagMos||flagAmp||flagSt)&&(numPicked<2 && (!flagArch && !flagPro)) || ((flagArch||flagPro)&&((!flagSke)&&(numPicked<2)))) && flagDrawTiles) {
									EndTurn.setEnabled(true);
									flagSke=true;
									JLabel label=new JLabel();
									Player player=controller.getPlayers().get(0);
									for(int i=0;i<4;i++) {
										if(controller.seeTurn().equals(controller.getPlayers().get(i).getName())) {
											player=controller.getPlayers().get(i);
											player.getLabels().add(label);
										}
									}
								
									for(int i=0;i<player.getLabels().size();i++) {
										panel_3.add(player.getLabels().get(i));
									}
									ImageIcon imageIconLabelSke=new ImageIcon("images_2020\\skeleton_big_bottom.png");
									Image imageLabelSke=imageIconLabelSke.getImage();
									Image newimgLabelSke=imageLabelSke.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
									imageIconLabelSke=new ImageIcon(newimgLabelSke);
									label.setIcon(imageIconLabelSke);
									button.setVisible(false);
									for(int i=0;i<4;i++) {
										if(controller.seeTurn().equals(controller.getPlayers().get(i).getName())) {
											controller.getPlayers().get(i).addTile(obj);
										}
									}
									
									for(int i=0;i<controller.board.getSkeletonArea().size();i++) {
										if(controller.board.getSkeletonArea().get(i).getSize().equals("big") && controller.board.getSkeletonArea().get(i).getBodyPart().equals("bottom")) {
											controller.board.getSkeletonArea().remove(i);
											break;
										}
									}
									
									numPicked++;
									if(flagPro) {
										numPicked=1;
									}
									if(numPicked==1 || numPicked==2) {
										flagChoseTile=true;
									}
									if(flagArch) {
										flagSke=false;
										flagMos=true;
										flagAmp=true;
										flagSt=true;
									}
								}
								
							}
							
							
						});
						button.setBounds(365, 429 , 40 , 40);
						ImageIcon imageIconButtonSke=new ImageIcon("images_2020\\skeleton_big_bottom.png");
						Image imageButtonSke=imageIconButtonSke.getImage();
						Image newimgSke=imageButtonSke.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
						imageIconButtonSke=new ImageIcon(newimgSke);
						button.setIcon(imageIconButtonSke);
						panel.add(button);
					}
					
					if(obj.getSize().equals("small") && obj.getBodyPart().equals("top")) {
						button.addActionListener(new ActionListener() {
							
							/**
							 * <b>Transformer:</b> Doing some action if the first SkeletonTile button(in case it is "small" and "top") is pressed.
							 * <b>Postcondition:</b> Doing some action if the first SkeletonTile button(in case it is "small" and "top") is pressed.
							 */
							public void actionPerformed(ActionEvent b) {
								if((!(flagMos||flagAmp||flagSt)&&(numPicked<2 && (!flagArch && !flagPro)) || ((flagArch||flagPro)&&((!flagSke)&&(numPicked<2)))) && flagDrawTiles) {
									EndTurn.setEnabled(true);
									flagSke=true;
									JLabel label=new JLabel();
									Player player=controller.getPlayers().get(0);
									for(int i=0;i<4;i++) {
										if(controller.seeTurn().equals(controller.getPlayers().get(i).getName())) {
											player=controller.getPlayers().get(i);
											player.getLabels().add(label);
										}
									}
								
									for(int i=0;i<player.getLabels().size();i++) {
										panel_3.add(player.getLabels().get(i));
									}
									ImageIcon imageIconLabelSke=new ImageIcon("images_2020\\skeleton_small_top.png");
									Image imageLabelSke=imageIconLabelSke.getImage();
									Image newimgLabelSke=imageLabelSke.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
									imageIconLabelSke=new ImageIcon(newimgLabelSke);
									label.setIcon(imageIconLabelSke);
									button.setVisible(false);
									for(int i=0;i<4;i++) {
										if(controller.seeTurn().equals(controller.getPlayers().get(i).getName())) {
											controller.getPlayers().get(i).addTile(obj);
										}
									}
									
									for(int i=0;i<controller.board.getSkeletonArea().size();i++) {
										if(controller.board.getSkeletonArea().get(i).getSize().equals("small") && controller.board.getSkeletonArea().get(i).getBodyPart().equals("top")) {
											controller.board.getSkeletonArea().remove(i);
											break;
										}
									}
									
									numPicked++;
									if(flagPro) {
										numPicked=1;
									}
									if(numPicked==1 || numPicked==2) {
										flagChoseTile=true;
									}
									if(flagArch) {
										flagSke=false;
										flagMos=true;
										flagAmp=true;
										flagSt=true;
									}
								}
								
								
							}
							
							
						});
						button.setBounds(365, 429 , 40 , 40);
						ImageIcon imageIconButtonSke=new ImageIcon("images_2020\\skeleton_small_top.png");
						Image imageButtonSke=imageIconButtonSke.getImage();
						Image newimgSke=imageButtonSke.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
						imageIconButtonSke=new ImageIcon(newimgSke);
						button.setIcon(imageIconButtonSke);
						panel.add(button);
					}
					
					if(obj.getSize().equals("small") && obj.getBodyPart().equals("bottom")) {
						button.addActionListener(new ActionListener() {
							
							/**
							 * <b>Transformer:</b> Doing some action if the first SkeletonTile button(in case it is "small" and "bottom") is pressed.
							 * <b>Postcondition:</b> Doing some action if the first SkeletonTile button(in case it is "small" and "bottom") is pressed.
							 */
							public void actionPerformed(ActionEvent b) {
								if((!(flagMos||flagAmp||flagSt)&&(numPicked<2 && (!flagArch && !flagPro)) || ((flagArch||flagPro)&&((!flagSke)&&(numPicked<2)))) && flagDrawTiles) {
									EndTurn.setEnabled(true);
									flagSke=true;
									JLabel label=new JLabel();
									Player player=controller.getPlayers().get(0);
									for(int i=0;i<4;i++) {
										if(controller.seeTurn().equals(controller.getPlayers().get(i).getName())) {
											player=controller.getPlayers().get(i);
											player.getLabels().add(label);
										}
									}
								
									for(int i=0;i<player.getLabels().size();i++) {
										panel_3.add(player.getLabels().get(i));
									}
									ImageIcon imageIconLabelSke=new ImageIcon("images_2020\\skeleton_small_bottom.png");
									Image imageLabelSke=imageIconLabelSke.getImage();
									Image newimgLabelSke=imageLabelSke.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
									imageIconLabelSke=new ImageIcon(newimgLabelSke);
									label.setIcon(imageIconLabelSke);
									button.setVisible(false);
									for(int i=0;i<4;i++) {
										if(controller.seeTurn().equals(controller.getPlayers().get(i).getName())) {
											controller.getPlayers().get(i).addTile(obj);
										}
									}
									
									for(int i=0;i<controller.board.getSkeletonArea().size();i++) {
										if(controller.board.getSkeletonArea().get(i).getSize().equals("small") && controller.board.getSkeletonArea().get(i).getBodyPart().equals("bottom")) {
											controller.board.getSkeletonArea().remove(i);
											break;
										}
									}
									
									numPicked++;
									if(flagPro) {
										numPicked=1;
									}
									if(numPicked==1 || numPicked==2) {
										flagChoseTile=true;
									}
									if(flagArch) {
										flagSke=false;
										flagMos=true;
										flagAmp=true;
										flagSt=true;
									}
								}
								
								
							}
							
							
						});
						button.setBounds(365, 429 , 40 , 40);
						ImageIcon imageIconButtonSke=new ImageIcon("images_2020\\skeleton_small_bottom.png");
						Image imageButtonSke=imageIconButtonSke.getImage();
						Image newimgSke=imageButtonSke.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
						imageIconButtonSke=new ImageIcon(newimgSke);
						button.setIcon(imageIconButtonSke);
						panel.add(button);
					}
					
					controller.getAllTiles().getBag().remove(i);
					break;
				 }
			}
			
			for(int i=0;i<controller.getAllTiles().getBag().size();i++) {
				if(controller.getAllTiles().getBag().get(i) instanceof AmphoraTile) {
					controller.board.getAmphoraArea().add((AmphoraTile)controller.getAllTiles().getBag().get(i));
					JButton button=new JButton();
					AmphoraTile obj=(AmphoraTile) controller.getAllTiles().getBag().get(i);
					if(obj.getColor()==AmphoraTileColor.BLUE) {
						button.addActionListener(new ActionListener() {
							
							/**
							 * <b>Transformer:</b> Doing some action if the first AmphoraTile button(in case it is Blue) is pressed.
							 * <b>Postcondition:</b> Doing some action if the first AmphoraTile button(in case it is Blue) is pressed.
							 */
							public void actionPerformed(ActionEvent b) {
								if((!(flagMos||flagSke||flagSt)&&(numPicked<2 && (!flagArch && !flagPro)) || ((flagArch||flagPro)&&((!flagAmp)&&(numPicked<2)))) && flagDrawTiles) {
									EndTurn.setEnabled(true);
									flagAmp=true;
									JLabel label=new JLabel();
									Player player=controller.getPlayers().get(0);
									for(int i=0;i<4;i++) {
										if(controller.seeTurn().equals(controller.getPlayers().get(i).getName())) {
											player=controller.getPlayers().get(i);
											player.getLabels().add(label);
										}
									}
								
									for(int i=0;i<player.getLabels().size();i++) {
										panel_3.add(player.getLabels().get(i));
									}
									ImageIcon imageIconLabelAmp=new ImageIcon("images_2020\\amphora_blue.png");
									Image imageLabelAmp=imageIconLabelAmp.getImage();
									Image newimgLabelAmp=imageLabelAmp.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
									imageIconLabelAmp=new ImageIcon(newimgLabelAmp);
									label.setIcon(imageIconLabelAmp);
									button.setVisible(false);
									for(int i=0;i<4;i++) {
										if(controller.seeTurn().equals(controller.getPlayers().get(i).getName())) {
											controller.getPlayers().get(i).addTile(obj);
										}
									}
									
									for(int i=0;i<controller.board.getAmphoraArea().size();i++) {
										if(controller.board.getAmphoraArea().get(i).getColor()==AmphoraTileColor.BLUE) {
											controller.board.getAmphoraArea().remove(i);
											break;
										}
									}
									
									numPicked++;
									if(flagPro) {
										numPicked=1;
									}
									if(numPicked==1 || numPicked==2) {
										flagChoseTile=true;
									}
									if(flagArch) {
										flagAmp=false;
										flagMos=true;
										flagSke=true;
										flagSt=true;
									}
								}
							}
							
							
						});
						button.setBounds(133, 429 , 40 , 40);
						ImageIcon imageIconButtonAmp=new ImageIcon("images_2020\\amphora_blue.png");
						Image imageButtonAmp=imageIconButtonAmp.getImage();
						Image newimgAmp=imageButtonAmp.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
						imageIconButtonAmp=new ImageIcon(newimgAmp);
						button.setIcon(imageIconButtonAmp);
						panel.add(button);
					}
					
					if(obj.getColor()==AmphoraTileColor.BROWN) {
						button.addActionListener(new ActionListener() {
							
							/**
							 * <b>Transformer:</b> Doing some action if the first AmphoraTile button(in case it is Brown) is pressed.
							 * <b>Postcondition:</b> Doing some action if the first AmphoraTile button(in case it is Brown) is pressed.
							 */
							public void actionPerformed(ActionEvent b) {
								if((!(flagMos||flagSke||flagSt)&&(numPicked<2 && (!flagArch && !flagPro)) || ((flagArch||flagPro)&&((!flagAmp)&&(numPicked<2)))) && flagDrawTiles) {
									EndTurn.setEnabled(true);
									flagAmp=true;
									JLabel label=new JLabel();
									Player player=controller.getPlayers().get(0);
									for(int i=0;i<4;i++) {
										if(controller.seeTurn().equals(controller.getPlayers().get(i).getName())) {
											player=controller.getPlayers().get(i);
											player.getLabels().add(label);
										}
									}
								
									for(int i=0;i<player.getLabels().size();i++) {
										panel_3.add(player.getLabels().get(i));
									}
									ImageIcon imageIconLabelAmp=new ImageIcon("images_2020\\amphora_brown.png");
									Image imageLabelAmp=imageIconLabelAmp.getImage();
									Image newimgLabelAmp=imageLabelAmp.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
									imageIconLabelAmp=new ImageIcon(newimgLabelAmp);
									label.setIcon(imageIconLabelAmp);
									button.setVisible(false);
									for(int i=0;i<4;i++) {
										if(controller.seeTurn().equals(controller.getPlayers().get(i).getName())) {
											controller.getPlayers().get(i).addTile(obj);
										}
									}
								
									for(int i=0;i<controller.board.getAmphoraArea().size();i++) {
										if(controller.board.getAmphoraArea().get(i).getColor()==AmphoraTileColor.BROWN) {
											controller.board.getAmphoraArea().remove(i);
											break;
										}
									}
									
									numPicked++;
									if(flagPro) {
										numPicked=1;
									}
									if(numPicked==1 || numPicked==2) {
										flagChoseTile=true;
									}
									if(flagArch) {
										flagAmp=false;
										flagMos=true;
										flagSke=true;
										flagSt=true;
									}
								}
							}
									
							
							
						});
						button.setBounds(133, 429 , 40 , 40);
						ImageIcon imageIconButtonAmp=new ImageIcon("images_2020\\amphora_brown.png");
						Image imageButtonAmp=imageIconButtonAmp.getImage();
						Image newimgAmp=imageButtonAmp.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
						imageIconButtonAmp=new ImageIcon(newimgAmp);
						button.setIcon(imageIconButtonAmp);
						panel.add(button);
					}
					
					if(obj.getColor()==AmphoraTileColor.GREEN) {
						button.addActionListener(new ActionListener() {
							
							/**
							 * <b>Transformer:</b> Doing some action if the first AmphoraTile button(in case it is Green) is pressed.
							 * <b>Postcondition:</b> Doing some action if the first AmphoraTile button(in case it is Green) is pressed.
							 */
							public void actionPerformed(ActionEvent b) {
								if((!(flagMos||flagSke||flagSt)&&(numPicked<2 && (!flagArch && !flagPro)) || ((flagArch||flagPro)&&((!flagAmp)&&(numPicked<2)))) && flagDrawTiles) {
									EndTurn.setEnabled(true);
									flagAmp=true;
									JLabel label=new JLabel();
									Player player=controller.getPlayers().get(0);
									for(int i=0;i<4;i++) {
										if(controller.seeTurn().equals(controller.getPlayers().get(i).getName())) {
											player=controller.getPlayers().get(i);
											player.getLabels().add(label);
										}
									}
								
									for(int i=0;i<player.getLabels().size();i++) {
										panel_3.add(player.getLabels().get(i));
									}
									ImageIcon imageIconLabelAmp=new ImageIcon("images_2020\\amphora_green.png");
									Image imageLabelAmp=imageIconLabelAmp.getImage();
									Image newimgLabelAmp=imageLabelAmp.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
									imageIconLabelAmp=new ImageIcon(newimgLabelAmp);
									label.setIcon(imageIconLabelAmp);
									button.setVisible(false);
									for(int i=0;i<4;i++) {
										if(controller.seeTurn().equals(controller.getPlayers().get(i).getName())) {
											controller.getPlayers().get(i).addTile(obj);
										}
									}
									
									for(int i=0;i<controller.board.getAmphoraArea().size();i++) {
										if(controller.board.getAmphoraArea().get(i).getColor()==AmphoraTileColor.GREEN) {
											controller.board.getAmphoraArea().remove(i);
											break;
										}
									}
									
									numPicked++;
									if(flagPro) {
										numPicked=1;
									}
									if(numPicked==1 || numPicked==2) {
										flagChoseTile=true;
									}
									if(flagArch) {
										flagAmp=false;
										flagMos=true;
										flagSke=true;
										flagSt=true;
									}
								}
							}
							
							
						});
						button.setBounds(133, 429 , 40 , 40);
						ImageIcon imageIconButtonAmp=new ImageIcon("images_2020\\amphora_green.png");
						Image imageButtonAmp=imageIconButtonAmp.getImage();
						Image newimgAmp=imageButtonAmp.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
						imageIconButtonAmp=new ImageIcon(newimgAmp);
						button.setIcon(imageIconButtonAmp);
						panel.add(button);
					}
					
					if(obj.getColor()==AmphoraTileColor.PURPLE) {
						button.addActionListener(new ActionListener() {
							
							/**
							 * <b>Transformer:</b> Doing some action if the first AmphoraTile button(in case it is Purple) is pressed.
							 * <b>Postcondition:</b> Doing some action if the first AmphoraTile button(in case it is Purple) is pressed.
							 */
							public void actionPerformed(ActionEvent b) {
								if((!(flagMos||flagSke||flagSt)&&(numPicked<2 && (!flagArch && !flagPro)) || ((flagArch||flagPro)&&((!flagAmp)&&(numPicked<2)))) && flagDrawTiles) {
									EndTurn.setEnabled(true);
									flagAmp=true;
									JLabel label=new JLabel();
									Player player=controller.getPlayers().get(0);
									for(int i=0;i<4;i++) {
										if(controller.seeTurn().equals(controller.getPlayers().get(i).getName())) {
											player=controller.getPlayers().get(i);
											player.getLabels().add(label);
										}
									}
								
									for(int i=0;i<player.getLabels().size();i++) {
										panel_3.add(player.getLabels().get(i));
									}
									ImageIcon imageIconLabelAmp=new ImageIcon("images_2020\\amphora_purple.png");
									Image imageLabelAmp=imageIconLabelAmp.getImage();
									Image newimgLabelAmp=imageLabelAmp.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
									imageIconLabelAmp=new ImageIcon(newimgLabelAmp);
									label.setIcon(imageIconLabelAmp);
									button.setVisible(false);
									for(int i=0;i<4;i++) {
										if(controller.seeTurn().equals(controller.getPlayers().get(i).getName())) {
											controller.getPlayers().get(i).addTile(obj);
										}
									}
								
									for(int i=0;i<controller.board.getAmphoraArea().size();i++) {
										if(controller.board.getAmphoraArea().get(i).getColor()==AmphoraTileColor.PURPLE) {
											controller.board.getAmphoraArea().remove(i);
											break;
										}
									}
									
									numPicked++;
									if(flagPro) {
										numPicked=1;
									}
									if(numPicked==1 || numPicked==2) {
										flagChoseTile=true;
									}
									if(flagArch) {
										flagAmp=false;
										flagMos=true;
										flagSke=true;
										flagSt=true;
									}
								}
							}
							
							
						});
						button.setBounds(133, 429 , 40 , 40);
						ImageIcon imageIconButtonAmp=new ImageIcon("images_2020\\amphora_purple.png");
						Image imageButtonAmp=imageIconButtonAmp.getImage();
						Image newimgAmp=imageButtonAmp.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
						imageIconButtonAmp=new ImageIcon(newimgAmp);
						button.setIcon(imageIconButtonAmp);
						panel.add(button);
					}
					
					if(obj.getColor()==AmphoraTileColor.RED) {
						button.addActionListener(new ActionListener() {
							
							/**
							 * <b>Transformer:</b> Doing some action if the first AmphoraTile button(in case it is Red) is pressed.
							 * <b>Postcondition:</b> Doing some action if the first AmphoraTile button(in case it is Red) is pressed.
							 */
							public void actionPerformed(ActionEvent b) {
								if((!(flagMos||flagSke||flagSt)&&(numPicked<2 && (!flagArch && !flagPro)) || ((flagArch||flagPro)&&((!flagAmp)&&(numPicked<2)))) && flagDrawTiles) {
									EndTurn.setEnabled(true);
									flagAmp=true;
									JLabel label=new JLabel();
									Player player=controller.getPlayers().get(0);
									for(int i=0;i<4;i++) {
										if(controller.seeTurn().equals(controller.getPlayers().get(i).getName())) {
											player=controller.getPlayers().get(i);
											player.getLabels().add(label);
										}
									}
								
									for(int i=0;i<player.getLabels().size();i++) {
										panel_3.add(player.getLabels().get(i));
									}
									ImageIcon imageIconLabelAmp=new ImageIcon("images_2020\\amphora_red.png");
									Image imageLabelAmp=imageIconLabelAmp.getImage();
									Image newimgLabelAmp=imageLabelAmp.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
									imageIconLabelAmp=new ImageIcon(newimgLabelAmp);
									label.setIcon(imageIconLabelAmp);
									button.setVisible(false);
									for(int i=0;i<4;i++) {
										if(controller.seeTurn().equals(controller.getPlayers().get(i).getName())) {
											controller.getPlayers().get(i).addTile(obj);
										}
									}
									
									for(int i=0;i<controller.board.getAmphoraArea().size();i++) {
										if(controller.board.getAmphoraArea().get(i).getColor()==AmphoraTileColor.RED) {
											controller.board.getAmphoraArea().remove(i);
											break;
										}
									}
									
									numPicked++;
									if(flagPro) {
										numPicked=1;
									}
									if(numPicked==1 || numPicked==2) {
										flagChoseTile=true;
									}
									if(flagArch) {
										flagAmp=false;
										flagMos=true;
										flagSke=true;
										flagSt=true;
									}
								}
							}
							
							
						});
						button.setBounds(133, 429 , 40 , 40);
						ImageIcon imageIconButtonAmp=new ImageIcon("images_2020\\amphora_red.png");
						Image imageButtonAmp=imageIconButtonAmp.getImage();
						Image newimgAmp=imageButtonAmp.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
						imageIconButtonAmp=new ImageIcon(newimgAmp);
						button.setIcon(imageIconButtonAmp);
						panel.add(button);
					}
					
					if(obj.getColor()==AmphoraTileColor.YELLOW) {
						button.addActionListener(new ActionListener() {
							
							/**
							 * <b>Transformer:</b> Doing some action if the first AmphoraTile button(in case it is Yellow) is pressed.
							 * <b>Postcondition:</b> Doing some action if the first AmphoraTile button(in case it is Yellow) is pressed.
							 */
							public void actionPerformed(ActionEvent b) {
								if((!(flagMos||flagSke||flagSt)&&(numPicked<2 && (!flagArch && !flagPro)) || ((flagArch||flagPro)&&((!flagAmp)&&(numPicked<2)))) && flagDrawTiles) {
									EndTurn.setEnabled(true);
									flagAmp=true;
									JLabel label=new JLabel();
									Player player=controller.getPlayers().get(0);
									for(int i=0;i<4;i++) {
										if(controller.seeTurn().equals(controller.getPlayers().get(i).getName())) {
											player=controller.getPlayers().get(i);
											player.getLabels().add(label);
										}
									}
								
									for(int i=0;i<player.getLabels().size();i++) {
										panel_3.add(player.getLabels().get(i));
									}
									ImageIcon imageIconLabelAmp=new ImageIcon("images_2020\\amphora_yellow.png");
									Image imageLabelAmp=imageIconLabelAmp.getImage();
									Image newimgLabelAmp=imageLabelAmp.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
									imageIconLabelAmp=new ImageIcon(newimgLabelAmp);
									label.setIcon(imageIconLabelAmp);
									button.setVisible(false);
									for(int i=0;i<4;i++) {
										if(controller.seeTurn().equals(controller.getPlayers().get(i).getName())) {
											controller.getPlayers().get(i).addTile(obj);
										}
									}
									
									for(int i=0;i<controller.board.getAmphoraArea().size();i++) {
										if(controller.board.getAmphoraArea().get(i).getColor()==AmphoraTileColor.YELLOW) {
											controller.board.getAmphoraArea().remove(i);
											break;
										}
									}
									
									numPicked++;
									if(flagPro) {
										numPicked=1;
									}
									if(numPicked==1 || numPicked==2) {
										flagChoseTile=true;
									}
									if(flagArch) {
										flagAmp=false;
										flagMos=true;
										flagSke=true;
										flagSt=true;
									}
								}
							}
							
							
						});
						button.setBounds(133, 429 , 40 , 40);
						ImageIcon imageIconButtonAmp=new ImageIcon("images_2020\\amphora_yellow.png");
						Image imageButtonAmp=imageIconButtonAmp.getImage();
						Image newimgAmp=imageButtonAmp.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
						imageIconButtonAmp=new ImageIcon(newimgAmp);
						button.setIcon(imageIconButtonAmp);
						panel.add(button);
					}
					AmpTileButtons.add(button);
					
					controller.getAllTiles().getBag().remove(i);
					break;
				}
			}
			controller.setHasStarted(true);
		}
		panel.setLayout(null);
		lblNewLabel.setBounds(0, 0, 539, 482);
		lblNewLabel.setIcon(imageIcon);
		panel.add(lblNewLabel);
		frmAmphipolis.setVisible(true);
	}
	
	public void init_characterButtons() {
			ArchaeologistButton.setToolTipText("You can take up to 2 tiles from any sorting area than the one you chose earlier in your turn.");
			ArchaeologistButton.addActionListener(new ActionListener() {
				
				/**
				 * <b>Transformer:</b> Doing some action if the Archaeologist button is pressed.
				 * <b>Postcondition:</b> Doing some actiton if the Archaeologist button is pressed.
				 */
				public void actionPerformed(ActionEvent arg0) {
					if(!(flagAssist||flagDig||flagPro)&& flagChoseTile) {
						flagArch=true;
						numPicked=0;
						Player player_turn=controller.getPlayers().get(0);
						for(int i=0;i<4;i++) {
							if(controller.seeTurn().equals(controller.getPlayers().get(i).getName())) {
								player_turn=controller.getPlayers().get(i);
								break;
							}
						}
				
						for(int i=0;i<player_turn.getCharacter().size();i++) {
							if(player_turn.getCharacter().get(i) instanceof Archaeologist) {
								player_turn.removeCharacter(player_turn.getCharacter().get(i));
							}
						}
						ArchaeologistButton.setEnabled(false);
					}
				}
			});
			
			ArchaeologistButton.setBounds(0, 104, 80, 125);
			ImageIcon imageIcon6=new ImageIcon("images_2020\\archaeologist.png");
			Image image6=imageIcon6.getImage();
			Image newimg6=image6.getScaledInstance(80,125, java.awt.Image.SCALE_SMOOTH);
			imageIcon6=new ImageIcon(newimg6);
			ArchaeologistButton.setIcon(imageIcon6);
			panel_1.add(ArchaeologistButton);
			AssistantButton.setToolTipText("You can take 1 tile from any sorting area.");
			
			
			AssistantButton.addActionListener(new ActionListener() {
				
				/**
				 * <b>Transformer:</b> Doing some action if the Assistant button is pressed.
				 * <b>Postcondition:</b> Doing some actiton if the Assistant button is pressed.
				 */
				public void actionPerformed(ActionEvent arg0) {
					if(!(flagArch||flagDig||flagPro)&& flagChoseTile) {
						flagAssist=true;
						
						flagMos=false;
						flagAmp=false;
						flagSke=false;
						flagSt=false;
						numPicked=1;
					
						Player player_turn=controller.getPlayers().get(0);
						for(int i=0;i<4;i++) {
							if(controller.seeTurn().equals(controller.getPlayers().get(i).getName())) {
								player_turn=controller.getPlayers().get(i);
							}
						}
				
						for(int i=0;i<player_turn.getCharacter().size();i++) {
							if(player_turn.getCharacter().get(i) instanceof Assistant) {
								player_turn.removeCharacter(player_turn.getCharacter().get(i));
							}
						}
						AssistantButton.setEnabled(false);
					}
				}
			});
			
			AssistantButton.setBounds(106, 104, 80, 125);
			ImageIcon imageIcon7=new ImageIcon("images_2020\\assistant.png");
			Image image7=imageIcon7.getImage();
			Image newimg7=image7.getScaledInstance(80,125, java.awt.Image.SCALE_SMOOTH);
			imageIcon7=new ImageIcon(newimg7);
			AssistantButton.setIcon(imageIcon7);
			panel_1.add(AssistantButton);
			DiggerButton.setToolTipText("You can take up to 2 tiles from the sorting area you chose earlier in your turn.");
			
			
			DiggerButton.addActionListener(new ActionListener() {
				
				/**
				 * <b>Transformer:</b> Doing some action if the Digger button is pressed.
				 * <b>Postcondition:</b> Doing some actiton if the Digger button is pressed.
				 */
				public void actionPerformed(ActionEvent arg0) {
					if(!(flagArch||flagAssist||flagPro)&& flagChoseTile) {
						flagDig=true;
						numPicked=0;
						Player player_turn=controller.getPlayers().get(0);
						for(int i=0;i<4;i++) {
							if(controller.seeTurn().equals(controller.getPlayers().get(i).getName())) {
								player_turn=controller.getPlayers().get(i);
							}
						}
					
						for(int i=0;i<player_turn.getCharacter().size();i++) {
							if(player_turn.getCharacter().get(i) instanceof Digger) {
								player_turn.removeCharacter(player_turn.getCharacter().get(i));
							}
						}
						DiggerButton.setEnabled(false);
					}
				}
			});
			
			DiggerButton.setBounds(0, 251, 80, 125);
			ImageIcon imageIcon8=new ImageIcon("images_2020\\digger.png");
			Image image8=imageIcon8.getImage();
			Image newimg8=image8.getScaledInstance(80,125, java.awt.Image.SCALE_SMOOTH);
			imageIcon8=new ImageIcon(newimg8);
			DiggerButton.setIcon(imageIcon8);
			panel_1.add(DiggerButton);
			ProfessorButton.setToolTipText("Take 1 tile from each other sorting area other than the one you chose earlier in your turn.");
			
			
			ProfessorButton.addActionListener(new ActionListener() {
				
				/**
				 * <b>Transformer:</b> Doing some action if the Professor button is pressed.
				 * <b>Postcondition:</b> Doing some actiton if the Professor button is pressed.
				 */
				public void actionPerformed(ActionEvent arg0) {
					if(!(flagArch||flagAssist||flagDig)&& flagChoseTile) {
						flagPro=true;
						
						numPicked=1;
						Player player_turn=controller.getPlayers().get(0);
						for(int i=0;i<4;i++) {
							if(controller.seeTurn().equals(controller.getPlayers().get(i).getName())) {
								player_turn=controller.getPlayers().get(i);
							}
						}
					
						for(int i=0;i<player_turn.getCharacter().size();i++) {
							if(player_turn.getCharacter().get(i) instanceof Professor) {
								player_turn.removeCharacter(player_turn.getCharacter().get(i));
							}
						}
						ProfessorButton.setEnabled(false);
					}
				}
			});
			
			ProfessorButton.setBounds(106, 251, 80, 125);
			ImageIcon imageIcon9=new ImageIcon("images_2020\\professor.png");
			Image image9=imageIcon9.getImage();
			Image newimg9=image9.getScaledInstance(80,125, java.awt.Image.SCALE_SMOOTH);
			imageIcon9=new ImageIcon(newimg9);
			ProfessorButton.setIcon(imageIcon9);
			panel_1.add(ProfessorButton);
			
			
	}
}
