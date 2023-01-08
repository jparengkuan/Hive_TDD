package nl.hanze.hive;

public class QueenBeeMoveBehaviour extends GenericSlideBehaviour {
    @Override
    public boolean moveIsPossible(HiveBoard hiveBoard, Hexagon toPos, Hexagon fromPos) throws Hive.IllegalMove {

        if(!fromPos.getAllNeighBours().contains(fromPos))
        {
            throw new Hive.IllegalMove("De bijenkoningin verplaatst zich alleen door precies een keer te verschuiven");
        }

        if(slideIsPossible(hiveBoard, toPos, fromPos))
        {

        }
        return false;

    }
}
