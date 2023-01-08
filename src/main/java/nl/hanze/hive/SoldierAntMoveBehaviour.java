package nl.hanze.hive;

public class SoldierAntMoveBehaviour extends GenericSlideBehaviour {
    @Override
    public boolean moveIsPossible(HiveBoard hiveBoard, Hexagon toPos, Hexagon fromPos) throws Hive.IllegalMove {

        if(slideIsPossible(hiveBoard, toPos, fromPos))
        {

        }
        return false;

    }
}
