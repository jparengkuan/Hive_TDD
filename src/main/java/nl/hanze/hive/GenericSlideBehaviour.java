package nl.hanze.hive;


import java.util.Iterator;

abstract public class GenericSlideBehaviour implements MoveBehaviourStrategy {

    public boolean slideIsPossible(HiveBoard hiveBoard, Hexagon toPos, Hexagon fromPos) {

        if(!isAdjacent(toPos, fromPos))
        {
            return false;
        }

        int commonNeighboursWithTiles = getCommonNeighboursWithTiles(hiveBoard, toPos, fromPos);

        if(commonNeighboursWithTiles == 0)
        {
            return false;
        }

        return true;

    }

    public boolean isAdjacent(Hexagon toPos, Hexagon fromPos) {
        if (fromPos.getAllCommonNeighBours(toPos).size() < 2) return false;
        return true;
    }

    public int getCommonNeighboursWithTiles(HiveBoard hiveBoard, Hexagon toPos, Hexagon fromPos) {

        int commonNeighboursWithTiles = 0;
        Iterator commonNeighbours = fromPos.getAllCommonNeighBours(toPos).iterator();

        while (commonNeighbours.hasNext()) {
            TileStack tiles = hiveBoard.getHiveboard().get(commonNeighbours.next());
            if (tiles != null && tiles.getTiles() != null && !tiles.getTiles().isEmpty())
            {
                commonNeighboursWithTiles++;
            }

        }

        return commonNeighboursWithTiles;
    }
}

