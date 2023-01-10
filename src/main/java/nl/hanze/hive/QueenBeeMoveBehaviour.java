package nl.hanze.hive;

import java.util.HashSet;

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

        if(slideIsPossible(hiveBoard, toPos, fromPos))
        {

        }
        return false;

    }

    @Override
    public HashSet<Hexagon> getAllEndPositions(HiveBoard hiveBoard, Hexagon toPos) {
        return null;
    }
}
