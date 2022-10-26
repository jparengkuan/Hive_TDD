package nl.hanze.hive;

public class Gametile {

    // Information about the gametile
    private Hive.Player owner;
    private Hive.Tile tileName;
    private boolean played;

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

    public boolean isPlayed(){
        return played;
    }

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
