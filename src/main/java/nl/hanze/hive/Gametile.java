package nl.hanze.hive;

public class Gametile {

    // Information about the gametile
    private Hive.Player owner;
    private Hive.Tile tileName;
    private boolean played;
    private Strategy strategy;

    /**
     * Constructor of a Gametile
     * @param owner the player that is the owner of the tile
     * @param tile The type of title
     */
    public Gametile(Hive.Player owner, Hive.Tile tile) {
        this.owner = owner;
        this.tileName = tile;
        this.played = false;
    }

    /**
     * Getter for the players the owns the tile
     * @return Hive.Player the player
     */
    public Hive.Player getOwner() {
        return owner;
    }

    /**
     * Getter that returns if tile has been played already.
     * @return true if tile is already played, false if not.
     */
    public boolean isPlayed(){
        return played;
    }

    /**
     * Setter that sets the state of the tile.
     * @param status if the tile has been played or not.
     */
    public void setTileState(boolean status){
        this.played = status;
    }

    /**
     * Getter for type of title
     * @return Hive.Tile the tilename
     */
    public Hive.Tile getTileName() {
        return tileName;
    }
}
