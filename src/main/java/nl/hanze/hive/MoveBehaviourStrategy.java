package nl.hanze.hive;

import java.util.HashSet;

public interface MoveBehaviourStrategy {

    boolean moveIsPossible(HiveBoard hiveBoard, Hexagon toPos, Hexagon fromPos) throws Hive.IllegalMove;
    HashSet<Hexagon> getAllEndPositions(HiveBoard hiveBoard, Hexagon fromPos);
}
