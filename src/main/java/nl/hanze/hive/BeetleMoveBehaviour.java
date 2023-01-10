package nl.hanze.hive;

import java.util.HashSet;

public class BeetleMoveBehaviour extends GenericSlideBehaviour {
    @Override
    public boolean moveIsPossible(HiveBoard hiveBoard, Hexagon toPos, Hexagon fromPos) throws Hive.IllegalMove {


        if(!fromPos.getAllNeighBours().contains(toPos))
        {
            throw new Hive.IllegalMove("De kever verplaatst zich alleen door precies een keer te verschuiven");
        }

        if(hiveBoard.getHiveboard().get(toPos) != null
                && hiveBoard.getHiveboard().get(toPos).getTiles().size() >= 4)
        {
            throw new Hive.IllegalMove("De beetle kan zich niet verplaatsen er staan al vier tiles op deze positie");
        }


        if(!slideIsPossible(hiveBoard, toPos, fromPos))
        {
            throw new Hive.IllegalMove("De kever kan zich niet verplaatsen");
        }
        return true;

    }

    @Override
    public HashSet<Hexagon> getAllEndPositions(HiveBoard hiveBoard, Hexagon fromPos) {
        return null;
    }
}
