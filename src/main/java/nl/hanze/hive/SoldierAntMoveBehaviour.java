package nl.hanze.hive;

public class SoldierAntMoveBehaviour extends GenericSlideBehaviour {
    @Override
    public boolean moveIsPossible(HiveBoard hiveBoard, Hexagon toPos, Hexagon fromPos) throws Hive.IllegalMove {

        if(slideIsPossible(hiveBoard, toPos, fromPos))
        {
            if (isMovedToStartPosition(toPos, fromPos))
            {
                throw new Hive.IllegalMove("Een soldatenmier mag zich niet verplaatsen naar het veld waar hij al staat");
            }

        }
        return false;

    }

    public boolean isMovedToStartPosition(Hexagon toPos, Hexagon fromPos)
    {
        if (toPos.equals(fromPos)) return true;
        return false;
    }
}
