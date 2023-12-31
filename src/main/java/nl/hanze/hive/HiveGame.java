package nl.hanze.hive;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Deze klasse representeert de Hive Game
 */

public class HiveGame implements Hive {

    public HiveBoard hiveBoard;
    private Player currenPlayer;
    private HashMap<Player, HashMap<Hive.Tile, Integer>> playerDeck;
    private int turnCounter;

    public HiveGame() {

        // Decks aanmaken met de Hive tiles voor beide spelers
        setupPlayersDeck();

        // Als eerst is wit aan de beurt
        currenPlayer = Player.WHITE;

        // Zet de counter op 0
        this.turnCounter = 0;

        // Maak een nieuwe hiveboard aan
        this.hiveBoard = new HiveBoard();
    }

    @Override
    public void play(Tile tile, int q, int r) throws IllegalMove {

        if (isValidPlay(tile, q, r)) {
            // Plaats de steen vanuit de spelers deck op het bord
            this.playTileFromDeck(tile);
            this.hiveBoard.placeTile(tile, this.getCurrenPlayer(), q, r);

            // Wissel van speler na een zet
            this.switchTurn();

            // Increment de turnCounter
            this.turnCounter++;
        }

    }

    @Override
    public void move(int fromQ, int fromR, int toQ, int toR) throws IllegalMove {

        // Eerst kijken we of we een steen mogen verplaatsen volgens de basis regels
        if (isValidMove(fromQ, fromR, toQ, toR)) {

            // Haal het type insect op, van de locatie
            HiveTile tile = hiveBoard.getHiveboard().get(new Hexagon(fromQ, fromR)).getTiles().peek();

            // Op basis van het insect maken we de juiste behaviour strategie aan
            MoveBehaviourStrategy moveBehaviour = MoveBehaviourFactory.createMoveBehaviour(tile.getInsect());

            // Vervolgens kijken we op basis van de steen, of de zet geldig is
            if (moveBehaviour.moveIsPossible(this.hiveBoard, new Hexagon(toQ, toR), new Hexagon(fromQ, fromR))) {

                // Steen verplaatsen
                this.hiveBoard.moveTile(fromQ, fromR, toQ, toR);

                // Wissel van speler
                this.switchTurn();
            }


        }

    }

    @Override
    public void pass() throws IllegalMove {

    }

    @Override
    public boolean isWinner(Player player) {

        for (Map.Entry<Hexagon, TileStack> pos : this.hiveBoard.getHiveboard().entrySet()) {
            if (!this.hiveBoard.givenCoordinateHasTiles(pos.getKey().q, pos.getKey().r)
                    || pos.getValue().getTiles().peek().getOwner() == player) {
                continue;
            }

            for (HiveTile tile : pos.getValue().getTiles()) {
                if (tile.getInsect() == Tile.QUEEN_BEE) {
                    for (Hexagon neighbour : pos.getKey().getAllNeighBours()) {
                        if (!hiveBoard.givenCoordinateHasTiles(neighbour.q, neighbour.r)) return false;
                    }
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean isDraw() {
        return isWinner(Player.WHITE) && isWinner(Player.BLACK);
    }

    public void switchTurn() {
        if (getCurrenPlayer() == Player.WHITE) {
            this.setCurrenPlayer(Player.BLACK);
        } else {
            this.setCurrenPlayer(Player.WHITE);
        }
    }

    public Player getCurrenPlayer() {
        return currenPlayer;
    }

    public void setCurrenPlayer(Player currenPlayer) {
        this.currenPlayer = currenPlayer;
    }

    private void setupPlayersDeck() {
        this.playerDeck = new HashMap<>();
        for (Player player : new Player[]{Player.WHITE, Player.BLACK}) {
            playerDeck.put(player, new HashMap<Hive.Tile, Integer>() {{
                put(Tile.QUEEN_BEE, 1);
                put(Tile.SPIDER, 2);
                put(Tile.BEETLE, 2);
                put(Tile.SOLDIER_ANT, 3);
                put(Tile.GRASSHOPPER, 3);
            }});
        }
    }

    public HashMap<Tile, Integer> getPlayersDeck(Player player) {
        return this.playerDeck.get(player);
    }

    public void playTileFromDeck(Hive.Tile tile) throws IllegalMove {
        if (getPlayersDeck(this.getCurrenPlayer()).get(tile) > 0) {
            int aantal = playerDeck.get(this.getCurrenPlayer()).get(tile);
            this.getPlayersDeck(this.getCurrenPlayer()).put(tile, aantal - 1);
        } else {
            throw new IllegalMove("De speler heeft het gegeven tile type niet meer in zijn deck");
        }
    }

    public boolean playerMustPlayQueenBee(Hive.Tile tile) {
        if (getPlayersDeck(getCurrenPlayer()).get(Tile.QUEEN_BEE) == 1) {
            int tilesInDeck = 0;

            if (tile != Tile.QUEEN_BEE) {
                tilesInDeck = getPlayersDeck(getCurrenPlayer()).values().stream().reduce(0, Integer::sum);

                return tilesInDeck <= 8;
            }
        }
        return false;
    }

    public boolean hiveWouldSplitAfterMove(int fromQ, int fromR, int toQ, int toR) {

        if (hiveBoard.givenCoordinateHasTiles(toQ, toR)) return false; //edge case

        int chainCountBefore, chainCountAfter;

        chainCountBefore = this.hiveBoard.getConnectedTilesCount(new Hexagon(fromQ, fromR));

        this.hiveBoard.moveTile(fromQ, fromR, toQ, toR);

        chainCountAfter = this.hiveBoard.getConnectedTilesCount(new Hexagon(toQ, toQ));

        this.hiveBoard.moveTile(toQ, toR, fromQ, fromR);

        return chainCountBefore > chainCountAfter;


    }


    public boolean stoneIsPlacedNextToOpponent(int q, int r) {
        Iterator neighbours = new Hexagon(q, r).getAllNeighBours().iterator();

        while (neighbours.hasNext()) {
            TileStack neighbour = hiveBoard.getHiveboard().get(neighbours.next());

            if (neighbour != null &&
                    !neighbour.getTiles().isEmpty() &&
                    neighbour.getTiles().peek() != null &&
                    neighbour.getTiles().peek().getOwner() != this.getCurrenPlayer()
            )
                return true;

        }

        return false;
    }

    public boolean playerTriesToMoveOpponentsTile(int fromQ, int fromR) {
        TileStack tileStack = this.hiveBoard.getHiveboard().get(new Hexagon(fromQ, fromR));
        return tileStack.getTiles().peek().getOwner() != getCurrenPlayer();
    }

    public boolean playerTriesToMoveATileThatDoesntExist(int fromQ, int fromR) {
        TileStack tileStack = this.hiveBoard.getHiveboard().get(new Hexagon(fromQ, fromR));
        return tileStack == null || tileStack.getTiles().isEmpty();
    }

    public boolean playerTriesToMoveATileToLocationWithNoNeighbours(int toQ, int toR) {
        return !hiveBoard.givenCoordinateHasNeighbours(toQ, toR);
    }

    public boolean playerHasNotPlayedQueenBee() {
        return getPlayersDeck(getCurrenPlayer()).get(Tile.QUEEN_BEE) == 1;
    }

    public boolean playerMustPlayNextToAnotherTile(int q, int r) {
        return turnCounter > 0 && !this.hiveBoard.givenCoordinateHasNeighbours(q, r);
    }

    public boolean playerCannotPlayNextToOpponentTile(int q, int r) {
        return turnCounter >= 2 && stoneIsPlacedNextToOpponent(q, r);
    }

    public boolean playerCannotPlayStoneOnOccupiedField(int q, int r){
        return this.hiveBoard.givenCoordinateHasTiles(q, r);
    }

    public boolean isValidPlay(Tile tile, int q, int r) throws IllegalMove {
        if (playerMustPlayQueenBee(tile)) {
            throw new IllegalMove("Je hebt al drie stenen gespeeld, je kan momenteel alleen de bijenkoningnin spelen");
        }
        if (playerMustPlayNextToAnotherTile(q, r)) {
            throw new IllegalMove("De steen kan alleen gespeeld worden, naast een steen op het bord");
        }
        if (playerCannotPlayNextToOpponentTile(q, r)) {
            throw new IllegalMove("De steen kan niet naast en tegenstander worden gespeeld");
        }
        if (playerCannotPlayStoneOnOccupiedField(q, r)){
            throw new IllegalMove("Een steen kan niet gespeeld worden op een locatie waar al een steen ligt");
        }
        return true;
    }

    public boolean isValidMove(int fromQ, int fromR, int toQ, int toR) throws IllegalMove {
        if (playerTriesToMoveATileThatDoesntExist(fromQ, fromR)) {
            throw new IllegalMove("Je probeert een steen te verplaatsen vanaf een locatie, dat niet bestaat");
        }
        if (playerTriesToMoveOpponentsTile(fromQ, fromR)) {
            throw new IllegalMove("Het is niet toegestaan om een steen van de tegenstander te verplaatsen");
        }
        if (playerHasNotPlayedQueenBee()) {
            throw new IllegalMove("Voordat je een verplaatsing kan doen, moet je eerst de QueenBee spelen");
        }
        if (playerTriesToMoveATileToLocationWithNoNeighbours(toQ, toR)) {
            throw new IllegalMove("Een steen kan alleen verplaatst worden naar een locatie met buren");
        }
//        if (hiveWouldSplitAfterMove(fromQ, fromR, toQ, toR)) {
//            //throw new IllegalMove("Door het verplaatsen van de steen zijn er twee niet onderling verbonden groepen stenen ontstaan");
//        }
        return true;
    }

}


