package nl.hanze.hive;

import nl.hanze.hive.GenericSlideBehaviour;
import nl.hanze.hive.Hexagon;
import nl.hanze.hive.Hive;
import nl.hanze.hive.HiveBoard;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class GrassHopperMoveBehaviour extends GenericSlideBehaviour {
    @Override
    public boolean moveIsPossible(HiveBoard hiveBoard, Hexagon toPos, Hexagon fromPos) throws Hive.IllegalMove {

        if (isMovedToStartPosition(toPos, fromPos)) {
            throw new Hive.IllegalMove("Een sprinkhaan mag zich niet verplaatsen naar het veld waar hij al staat");
        }

        if (hiveBoard.givenCoordinateHasTiles(toPos.q, toPos.r)) {
            throw new Hive.IllegalMove("Een sprinkhaan mag zich niet verplaatsen naar een bezet veld");
        }

        if (!getAllEndPositions(hiveBoard, fromPos).contains(toPos)) {
            throw new Hive.IllegalMove("De sprinkhaan kan niet springen naar de eindpositie");
        }


        return true;

    }

    private Hexagon findPath(HiveBoard hiveBoard, Hexagon fromPos, Hexagon toPos) {
        Hexagon neighbour = new Hexagon(fromPos.q + toPos.q, fromPos.r + toPos.r);
        if (!hiveBoard.givenCoordinateHasTiles(neighbour.q, neighbour.r)) return neighbour;
        return findPath(hiveBoard, neighbour, toPos);
    }

    public boolean isMovedToStartPosition(Hexagon toPos, Hexagon fromPos) {
        return toPos.equals(fromPos);
    }

    @Override
    public HashSet<Hexagon> getAllEndPositions(HiveBoard hiveBoard, Hexagon fromPos) {
        HashSet<Hexagon> endPositions = new HashSet<>();

        Iterator directions = new Hexagon(0, 0).getAllNeighBours().iterator();

        while (directions.hasNext()) {
            Hexagon toPos = (Hexagon) directions.next();
            if (!hiveBoard.givenCoordinateHasTiles(fromPos.q + toPos.q, fromPos.r + toPos.r)) continue;
            endPositions.add(findPath(hiveBoard, fromPos, toPos));
        }

        return endPositions;
    }

}
