package nl.hanze.hive;

import java.util.HashMap;

/**
 * Deze klasse representeert de Hive Game
 */

public class HiveGame implements Hive {

    private Player currenPlayer;
    private HashMap<Player, HashMap<Hive.Tile, Integer>> playerDeck;

    private HiveBoard hiveBoard;

    private int turnCounter;

    public HiveGame(){

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
            // Wissel van speler na een zet
            this.switchTurn();

            // Plaatst de steen vanuit de spelers deck op het bord
            this.playTileFromDeck(tile);

            // Increment de turnCounter
            this.turnCounter++;
        }

    }

    @Override
    public void move(int fromQ, int fromR, int toQ, int toR) throws IllegalMove {

    }

    @Override
    public void pass() throws IllegalMove {

    }

    @Override
    public boolean isWinner(Player player) {
        return false;
    }

    @Override
    public boolean isDraw() {
        return false;
    }

    public void switchTurn() {
        if(getCurrenPlayer() == Player.WHITE){
            this.setCurrenPlayer(Player.BLACK);
        }
        else{
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

    public HashMap<Tile, Integer> getPlayersDeck(Player player){
        return this.playerDeck.get(player);
    }

    public void playTileFromDeck(Hive.Tile tile) throws IllegalMove {
        if(getPlayersDeck(this.getCurrenPlayer()).get(tile) > 0){
            int aantal = playerDeck.get(this.getCurrenPlayer()).get(tile);
            this.getPlayersDeck(this.getCurrenPlayer()).put(tile, aantal-1);
        }
        else {
            throw new IllegalMove("De speler heeft het gegeven tile type niet meer in zijn deck");
        }
    }

    public boolean playerMustPlayQueenBee(Hive.Tile tile)  {
        if (getPlayersDeck(getCurrenPlayer()).get(Tile.QUEEN_BEE) == 1)
        {
            int tilesInDeck = 0;

            if (tile != Tile.QUEEN_BEE){
                tilesInDeck = getPlayersDeck(getCurrenPlayer()).values().stream().reduce(0, Integer::sum);

                if (tilesInDeck <= 8) {
                   return true;
                }
            }
        }
        return false;
    }

    public boolean playerMustPlayNextToAnotherTile(int q, int r){
        if (turnCounter > 0 && !this.hiveBoard.givenCoordinateHasNeighbours(q, r)) return true;
        else return false;
    }

    public boolean isValidPlay(Tile tile, int q, int r) throws IllegalMove {
        if (playerMustPlayQueenBee(tile))
        {
            throw new IllegalMove("Je hebt al drie stenen gespeeld, je kan momenteel alleen de bijenkoningnin spelen");
        }
        if (playerMustPlayNextToAnotherTile(q, r))
        {
            throw new IllegalMove("De steen kan alleen gespeeld worden, naast een steen op het bord");
        }
        return true;
    }
}


