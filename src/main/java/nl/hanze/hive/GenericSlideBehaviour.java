package nl.hanze.hive;


import java.util.Iterator;

abstract public class GenericSlideBehaviour implements MoveBehaviourStrategy {

    public boolean slideIsPossible(HiveBoard hiveBoard, Hexagon toPos, Hexagon fromPos) throws Hive.IllegalMove {

        int commonNeighboursWithTiles = getCommonNeighboursWithTiles(hiveBoard, toPos, fromPos);

        if(commonNeighboursWithTiles == 0)
        {
            throw new Hive.IllegalMove("Tijdens een verschuiving moet de steen continu in contact blijven met minstens een andere steen");
        }

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

