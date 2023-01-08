package nl.hanze.hive;

public class MoveBehaviourFactory {

    public static MoveBehaviourStrategy createMoveBehaviour(Hive.Tile insect)
    {
        switch (insect) {
            case QUEEN_BEE:
                return new QueenBeeMoveBehaviour();
            case SPIDER:
                break;
            case BEETLE:
                break;
            case GRASSHOPPER:
                break;
            case SOLDIER_ANT:
                break;
        }
        return null;
    }
}