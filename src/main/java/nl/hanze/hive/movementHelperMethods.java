package nl.hanze.hive;

import java.util.HashMap;

public class movementHelperMethods {

    public static boolean lostContactDuringMove(Board board, int fromQ, int fromR, int toQ, int toR) {

        String direction = board.getMoveDirections(fromQ, fromR, toQ, toR);
        HashMap<String, Cell> directions = new Cell(fromQ, fromR).getCoordinatesHashmap();

        switch (direction) {
            case "EAST":
                // NE
                Cell ne = directions.get("NORTH_EAST");
                // SE
                Cell se = directions.get("SOUTH_EAST");

                if (board.getCell(ne.q, ne.r) == null) {
                    board.addCell(ne.q, ne.r);
                }
                if (board.getCell(se.q, se.r) == null) {
                    board.addCell(se.q, se.r);
                }

                Boolean southEastHasTiles = !board.getCell(se.q, se.r).isEmpty();
                Boolean northEasyHasTiles = !board.getCell(ne.q, ne.r).isEmpty();

                if (!northEasyHasTiles && !southEastHasTiles) {
                    return true;
                }

            case "WEST":
                // NW
                Cell nw = directions.get("NORTH_WEST");
                // SW
                Cell sw = directions.get("SOUTH_WEST");

                if (board.getCell(nw.q, nw.r) == null) {
                    board.addCell(nw.q, nw.r);
                }
                if (board.getCell(sw.q, sw.r) == null) {
                    board.addCell(sw.q, sw.r);
                }

                Boolean southWestHasTiles = !board.getCell(sw.q, sw.r).isEmpty();
                Boolean northWestHasTiles = !board.getCell(nw.q, nw.r).isEmpty();

                if (!northWestHasTiles && !southWestHasTiles) {
                    return true;
                }


        }

        return false;


    }
}

