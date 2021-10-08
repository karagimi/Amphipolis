package Model;
import java.util.ArrayList;
import java.util.Collections;

public class Bag {
	
	private ArrayList<Tile> tiles;
	
	/**
	 * <b>Constructor:</b> Creates an ArrayList  that contains all the Tiles
	 * and with that way the Bag is created.
	 * <b>Postcondition:</b> The Bag is created.
	 */
	public Bag() {
		tiles= new ArrayList<Tile>();
	}
	
	/**
	 * <b>Transformer:</b> Initializes the tiles.
	 * <b>Postcondition:</b> The tiles have been initialized.
	 */
	public void init_tiles() {
		
		for(int i=0;i<9;i++) {
			tiles.add(new MosaicTile(MosaicTileColor.GREEN));
		}
		
		for(int i=0;i<9;i++) {
			tiles.add(new MosaicTile(MosaicTileColor.RED));
		}
		
		for(int i=0;i<9;i++) {
			tiles.add(new MosaicTile(MosaicTileColor.YELLOW));
		}
		
		for(int i=0;i<12;i++) {
			tiles.add(new CaryatidTile());
		}
		
		for(int i=0;i<12;i++) {
			tiles.add(new SphinxTile());
		}
		
		for(int i=0;i<24;i++) {
			tiles.add(new LandslideTile());
		}
		
		for(int i=0;i<10;i++) {
			tiles.add(new SkeletonTile("big","top"));
		}
		
		for(int i=0;i<10;i++) {
			tiles.add(new SkeletonTile("big","bottom"));
		}
		
		for(int i=0;i<5;i++) {
			tiles.add(new SkeletonTile("small","top"));
		}
		
		for(int i=0;i<5;i++) {
			tiles.add(new SkeletonTile("small","bottom"));
		}
		
		for(int i=0;i<5;i++) {
			tiles.add(new AmphoraTile(AmphoraTileColor.BLUE));
		}
		
		for(int i=0;i<5;i++) {
			tiles.add(new AmphoraTile(AmphoraTileColor.BROWN));
		}
		
		for(int i=0;i<5;i++) {
			tiles.add(new AmphoraTile(AmphoraTileColor.RED));
		}
		
		for(int i=0;i<5;i++) {
			tiles.add(new AmphoraTile(AmphoraTileColor.GREEN));
		}
		
		for(int i=0;i<5;i++) {
			tiles.add(new AmphoraTile(AmphoraTileColor.YELLOW));
		}
		
		for(int i=0;i<5;i++) {
			tiles.add(new AmphoraTile(AmphoraTileColor.PURPLE));
		}
		
		
		Collections.shuffle(tiles);
	}
	
	/**
	 * <b>Observer:</b> Returns true if this list contains no elements.
	 * <b>Postcondition:</b> Checked  if the list is empty.
	 * @return true if this list contains no elements.
	 */
	public boolean isEmpty() {
		if(tiles.isEmpty()) {
			return true;
		}else {
			return  false;
		}
	}

	
	/**
	 * <b>Transformer:</b> Removes a tile from the list.
	 * <b>Postcondition:</b> The tile has been removed from the list.
	 * @param tile
	 */
	public void removeTile(Tile tile) {
		tiles.remove(tile);
	}
	
	/**
	 * <b>Accessor:</b> Returns the whole bag( the ArrayList that consists of the Tiles).
	 * <b>PostCondition:</b> The ArrayList that consists of Tiles is returned.
	 * @return the bag with the tiles.
	 */
	public ArrayList<Tile> getBag() {
		return this.tiles;
	}
	
}
