package nl.hanze.hive;

public class HiveTile {

    private Hive.Player owner;
    private Hive.Tile insect;

    public HiveTile(Hive.Player owner, Hive.Tile insect){
        this.owner = owner;
        this.insect = insect;
    }

    public Hive.Player getOwner() {
        return owner;
    }

    public Hive.Tile getInsect() {
        return insect;
    }
    
}
