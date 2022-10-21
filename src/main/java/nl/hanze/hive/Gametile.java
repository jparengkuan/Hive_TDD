package nl.hanze.hive;

public class Gametile {

    private Hive.Player owner;
    private Hive.Tile tileName;

    public Gametile(Hive.Player owner, Hive.Tile tile) {
        this.owner = owner;
        this.tileName = tile;
    }

    public Hive.Player getOwner() {
        return owner;
    }

    public Hive.Tile getTileName() {
        return tileName;
    }
}
