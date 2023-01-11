package nl.hanze.hive;

import nl.hanze.hive.GenericSlideBehaviour;
import nl.hanze.hive.Hexagon;
import nl.hanze.hive.Hive;
import nl.hanze.hive.HiveBoard;

import java.util.HashSet;

public class GrassHopperMoveBehaviour extends GenericSlideBehaviour {
    @Override
    public boolean moveIsPossible(HiveBoard hiveBoard, Hexagon toPos, Hexagon fromPos) throws Hive.IllegalMove {

        if (isMovedToStartPosition(toPos, fromPos)) {
            throw new Hive.IllegalMove("Een sprinkhaan mag zich niet verplaatsen naar het veld waar hij al staat");
        }

        if (hiveBoard.givenCoordinateHasTiles(toPos.q, toPos.r)) {
            throw new Hive.IllegalMove("Een sprinkhaan mag zich niet verplaatsen naar een bezet veld");
        }

        return true;

    }

    private HashSet<Hexagon> findPath(HiveBoard hiveBoard, Hexagon position, HashSet<Hexagon> visitedSet) {

        return visitedSet;
    }

    public boolean isMovedToStartPosition(Hexagon toPos, Hexagon fromPos) {
        if (toPos.equals(fromPos)) return true;
        return false;
    }

    @Override
    public HashSet<Hexagon> getAllEndPositions(HiveBoard hiveBoard, Hexagon fromPos) {
        return null;
    }

}
