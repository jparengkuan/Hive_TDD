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
        System.out.printf("Checking if neighbour has tiles (%d, %d)%n", neighbour.q, neighbour.r);
        if (!hiveBoard.givenCoordinateHasTiles(neighbour.q, neighbour.r)){
            return neighbour;
        }
        else {
            System.out.printf("Has Tiles!%n");
            return findPath(hiveBoard, neighbour, toPos);
        }
    }

    public boolean isMovedToStartPosition(Hexagon toPos, Hexagon fromPos) {
        if (toPos.equals(fromPos)) return true;
        return false;
    }

    @Override
    public HashSet<Hexagon> getAllEndPositions(HiveBoard hiveBoard, Hexagon fromPos) {
        HashSet<Hexagon> endPositions = new HashSet<>();

        Iterator directions = new Hexagon(0,0).getAllNeighBours().iterator();
        System.out.printf("Moving from (%d, %d)%n",fromPos.q, fromPos.r);

        while (directions.hasNext()){
            Hexagon toPos = (Hexagon) directions.next();
            System.out.printf("Direction (%d, %d) %n",toPos.q, toPos.r);
            if (!hiveBoard.givenCoordinateHasTiles(fromPos.q + toPos.q, fromPos.r + toPos.r))
                continue;
            System.out.printf("Coordinate (%d, %d) has Tiles!%n", fromPos.q + toPos.q, fromPos.r + toPos.r);
            System.out.printf("Finding path from (%d, %d) to (%d, %d)%n", fromPos.q, fromPos.r, toPos.q, toPos.r);
            endPositions.add(findPath(hiveBoard, fromPos, toPos));
        }

        return endPositions;
    }

}
