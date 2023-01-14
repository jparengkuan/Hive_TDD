package nl.hanze.hive;

import java.util.HashSet;

public class SpiderMoveBehaviour extends GenericSlideBehaviour {

    private int moveLimit = 3;

    private  HashSet<Hexagon> endPositions = new HashSet<>();



    @Override
    public boolean moveIsPossible(HiveBoard hiveBoard, Hexagon toPos, Hexagon fromPos) throws Hive.IllegalMove {

        if (isMovedToStartPosition(toPos, fromPos)) {
            throw new Hive.IllegalMove("Een spin mag zich niet verplaatsen naar het veld waar hij al staat");
        }

        endPositions = new HashSet<>();
        findPath(hiveBoard, fromPos, new HashSet<>(), 1);

        if (endPositions.contains(toPos)) {
            return true;
        } else {
            throw new Hive.IllegalMove("De spin kan de eindpositie niet bereiken");
        }

    }

    private HashSet<Hexagon> findPath(HiveBoard hiveBoard, Hexagon fromPos, HashSet<Hexagon> visitedSet, int depth) {
        visitedSet.add(fromPos);

        for (Hexagon toPos : fromPos.getAllNeighBours()) {
            if (!visitedSet.contains(toPos)
                    && slideIsPossible(hiveBoard, toPos, fromPos)
                    && hiveBoard.givenCoordinateHasNeighbours(toPos.q, toPos.r)
                    && !hiveBoard.givenCoordinateHasTiles(toPos.q, toPos.r)
            ) {

                if (depth >= this.moveLimit) {
                    this.endPositions.add(toPos);
                    continue;
                }
                HashSet<Hexagon> depthVisited = findPath(hiveBoard, toPos, visitedSet, depth + 1);
                visitedSet.addAll(depthVisited);

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
        endPositions = new HashSet<>();
        findPath(hiveBoard, fromPos, new HashSet<>(), 1);
        return this.endPositions;
    }

}
