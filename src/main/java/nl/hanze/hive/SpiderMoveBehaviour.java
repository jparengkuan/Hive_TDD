package nl.hanze.hive;

import java.util.HashSet;

public class SpiderMoveBehaviour extends GenericSlideBehaviour {

    private int moveCounter = 0;
    private int moveLimit = 3;

    private  HashSet<Hexagon> visitedSet = new HashSet<>();



    @Override
    public boolean moveIsPossible(HiveBoard hiveBoard, Hexagon toPos, Hexagon fromPos) throws Hive.IllegalMove {

        if (isMovedToStartPosition(toPos, fromPos)) {
            throw new Hive.IllegalMove("Een spin mag zich niet verplaatsen naar het veld waar hij al staat");
        }

        findPath(hiveBoard, fromPos, new HashSet<>());

        if (visitedSet.contains(toPos)) {
            return true;
        } else {
            throw new Hive.IllegalMove("De spin kan de eindpositie niet bereiken");
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
                this.moveCounter++;

                if (this.moveCounter >= this.moveLimit) {
                    this.visitedSet.add(neighbour);
                    this.moveCounter = 0;
                    continue;
                }
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
        findPath(hiveBoard, fromPos, new HashSet<>());
        return this.visitedSet;
    }

}
