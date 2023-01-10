package nl.hanze.hive;

import java.util.HashSet;
import java.util.Iterator;

public class QueenBeeMoveBehaviour extends GenericSlideBehaviour {
    @Override
    public boolean moveIsPossible(HiveBoard hiveBoard, Hexagon toPos, Hexagon fromPos) throws Hive.IllegalMove {


        if(!fromPos.getAllNeighBours().contains(toPos))
        {
            throw new Hive.IllegalMove("De bijenkoningin verplaatst zich alleen door precies een keer te verschuiven");
        }

        if(hiveBoard.getHiveboard().get(toPos) != null
            && !hiveBoard.getHiveboard().get(toPos).getTiles().isEmpty())
        {
            throw new Hive.IllegalMove("De bijenkoningin kan zich alleen verplaatsen naar een onbezet veld");
        }

        if(!slideIsPossible(hiveBoard, toPos, fromPos))
        {
            throw new Hive.IllegalMove("De bijenkoningin kan zich niet verplaatsen");
        }
        return true;

    }

    @Override
    public HashSet<Hexagon> getAllEndPositions(HiveBoard hiveBoard, Hexagon fromPos) {

        HashSet<Hexagon> endPositions = new HashSet<>();

        for (Hexagon neighbour : fromPos.getAllNeighBours()) {
            if (slideIsPossible(hiveBoard, neighbour, fromPos) && !hiveBoard.givenCoordinateHasTiles(neighbour.q, neighbour.r))
                endPositions.add(neighbour);
        }
        return endPositions;

    }
}
