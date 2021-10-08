package Model;

public class MosaicTile extends FindingTile {
		private MosaicTileColor color;
		
		/**
		 * <b>Constructor:</b> Constructs a new instance of MosaicTile,
		 * sets the color and via the superclass(FindingTile)
		 * sets the category=MosaicTile.
		 * <b>Postcondition</b>  Tile's category and color have been set.
		 * @param color
		 */
		public MosaicTile(MosaicTileColor color) {
			super("MosaicTile");
			this.color=color;
		}
		
		/**
		 * <b>Accessor:</b> Returns the MosaicTile's color.
		 * <b>Postcondition:</b> The color of a MosaicTile has been returned.
		 * @return color
		 */
		public MosaicTileColor getColor() {
			return this.color;
		}
		
		/**
		 * <b>Transformer:</b> Sets the MosaicTile's color.
		 * <b>Postcondition:</b> MosaicTile's color has been set.
		 * @param color
		 */
		public void setColor(MosaicTileColor color) {
			this.color=color;
		}
}
