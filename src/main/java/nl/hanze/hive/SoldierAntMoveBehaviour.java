package nl.hanze.hive;

import java.util.HashSet;

public class SoldierAntMoveBehaviour extends GenericSlideBehaviour {
    @Override
    public boolean moveIsPossible(HiveBoard hiveBoard, Hexagon toPos, Hexagon fromPos) throws Hive.IllegalMove {

        if (isMovedToStartPosition(toPos, fromPos)) {
            throw new Hive.IllegalMove("Een soldatenmier mag zich niet verplaatsen naar het veld waar hij al staat");
        }

        if (findPath(hiveBoard, fromPos, new HashSet<>()).contains(toPos)) {
            return true;
        } else {
            throw new Hive.IllegalMove("De SoldierAnt kan de eindpositie niet bereiken");
        }

    }

    private HashSet<Hexagon> findPath(HiveBoard hiveBoard, Hexagon position, HashSet<Hexagon> visitedSet) {

        visitedSet.add(position);

        for (Hexagon neighbour : position.getAllNeighBours()) {
            if (!visitedSet.contains(neighbour)
                    && slideIsPossible(hiveBoard, neighbour, position)
                    && hiveBoard.givenCoordinateHasNeighbours(neighbour.q, neighbour.r)
                    && !hiveBoard.givenCoordinateHasTiles(neighbour.q, neighbour.r)
            ) {
                visitedSet.addAll(findPath(hiveBoard, neighbour, visitedSet));
            }
        }
        return visitedSet;
    }

    public boolean isMovedToStartPosition(Hexagon toPos, Hexagon fromPos) {
        if (toPos.equals(fromPos)) return true;
        return false;
    }

    @Override
    public HashSet<Hexagon> getAllEndPositions(HiveBoard hiveBoard, Hexagon fromPos) {
        HashSet<Hexagon> endPositions = findPath(hiveBoard, fromPos, new HashSet<>());
        endPositions.remove(fromPos);
        return endPositions;
    }

}
