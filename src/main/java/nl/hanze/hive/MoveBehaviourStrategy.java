package nl.hanze.hive;

public interface MoveBehaviourStrategy {

    boolean moveIsPossible(HiveBoard hiveBoard, Hexagon toPos, Hexagon fromPos) throws Hive.IllegalMove;
}
