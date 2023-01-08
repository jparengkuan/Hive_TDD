package nl.hanze.hive;

public class QueenBeeMoveBehaviour extends GenericSlideBehaviour {
    @Override
    public boolean moveIsPossible(HiveBoard hiveBoard, Hexagon toPos, Hexagon fromPos) throws Hive.IllegalMove {

        if(slideIsPossible(hiveBoard, toPos, fromPos))
        {

        }
        return false;

    }
}
