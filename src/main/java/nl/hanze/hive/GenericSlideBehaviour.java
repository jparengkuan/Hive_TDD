package nl.hanze.hive;


import java.util.ArrayList;
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
        if (cannotSlideBetweenNeighbours(hiveBoard, toPos, fromPos))
        {
            return false;
        }

        return true;

    }

    public boolean isAdjacent(Hexagon toPos, Hexagon fromPos) {
        if (fromPos.getAllCommonNeighBours(toPos).size() < 2) return false;
        return true;
    }

    public boolean cannotSlideBetweenNeighbours(HiveBoard hiveBoard, Hexagon toPos, Hexagon fromPos) {
        ArrayList<Hexagon> commonNeighbours = fromPos.getAllCommonNeighBours(toPos);

        Integer n1, n2, a, b;
        n1 = n2 = a = b = 0;

        TileStack t1 = hiveBoard.getHiveboard().get(commonNeighbours.get(0));
        TileStack t2 = hiveBoard.getHiveboard().get(commonNeighbours.get(1));

        TileStack ta = hiveBoard.getHiveboard().get(fromPos);
        TileStack tb = hiveBoard.getHiveboard().get(toPos);

        if(t1 != null && t1.getTiles() != null) n1 = t1.getTiles().size();
        if(t2 != null && t2.getTiles() != null) n2 = t2.getTiles().size();
        if(ta != null && ta.getTiles() != null) a = ta.getTiles().size();
        if(tb != null && tb.getTiles() != null) b = tb.getTiles().size();

        return !(Math.min(n1, n2) <= Math.max(a -1, b));

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

