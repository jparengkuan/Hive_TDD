package nl.hanze.hive;

public class BeetleMoveBehaviour extends GenericSlideBehaviour {
    @Override
    public boolean moveIsPossible(HiveBoard hiveBoard, Hexagon toPos, Hexagon fromPos) throws Hive.IllegalMove {


        if(!fromPos.getAllNeighBours().contains(toPos))
        {
            throw new Hive.IllegalMove("De kever verplaatst zich alleen door precies een keer te verschuiven");
        }


        if(slideIsPossible(hiveBoard, toPos, fromPos))
        {

        }
        return false;

    }
}
