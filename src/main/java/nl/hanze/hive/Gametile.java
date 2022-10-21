package nl.hanze.hive;

public class Gametile {

    // Information about the gametile
    private Hive.Player owner;
    private Hive.Tile tileName;

    /**
     * Constructor of a Gametile
     * @param Hive.Player the player that is the owner of the tile
     * @param Hive.Tile The type of title
     */
    public Gametile(Hive.Player owner, Hive.Tile tile) {
        this.owner = owner;
        this.tileName = tile;
    }

    /**
     * Getter for the players the owns the tile
     * @return Hive.Player the player
     */
    public Hive.Player getOwner() {
        return owner;
    }

    /**
     * Getter for type of title
     * @return Hive.Tile the tilename
     */
    public Hive.Tile getTileName() {
        return tileName;
    }
}
