package nl.hanze.hive;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

import static nl.hanze.hive.Hive.Player.BLACK;
import static nl.hanze.hive.Hive.Player.WHITE;

public class Main implements Hive {

    private HashMap<Player, ArrayList<Tile>> decks;
    private Board board;
    private Player turn;

    // Default constructor, used to set decks of both players.
    // Decks will be saved in a HashMap: key = player; value = deck.
    public Main(){
        this.board = new Board(100);
        this.decks = new HashMap<>();
        this.turn = WHITE;
        setDeck(BLACK);
        setDeck(WHITE);
    }

    /**
     * Play a new tile, and change turn after.
     * @param tile Tile to play
     * @param q Q coordinate of hexagon to play to
     * @param r R coordinate of hexagon to play to
     * @throws IllegalMove if tile can't be played.
     */
    @Override
    public void play(Tile tile, int q, int r) throws IllegalMove {
        Stack<Tile> cell = board.getCell(q, r);
        cell.add(tile);
        setTurn();
    }

    /**
     * Add the game tiles to a specific player's deck.
     * @param player The player whose deck is being set
     */
    public void setDeck(Player player){
        ArrayList<Tile> deck = new ArrayList<>();
        deck.add(Tile.QUEEN_BEE);
        for(int i=0; i<2; i++){
            deck.add(Tile.SPIDER);
            deck.add(Tile.BEETLE);
        }
        for(int i=0; i<3; i++){
            deck.add(Tile.SOLDIER_ANT);
            deck.add(Tile.GRASSHOPPER);
        }
        decks.put(player, deck);
    }

    /**
     * Get the deck from a player.
     * @param player The player whose deck is being returned
     * @return the deck from the player.
     */
    public ArrayList<Tile> getDeck(Player player){
        return decks.get(player);
    }

    /**
     * Count the occurrences of a specific tile in a specific player's deck.
     * @param specificTile The tile that is being counted.
     * @param player The player whose deck is being iterated on.
     * @return the amount of occurrences of specificTile.
     */
    public int countTiles(Tile specificTile, Player player){
        ArrayList<Tile> deck = getDeck(player);
        int count = 0;
        for(Tile tile : deck){
            if(tile == specificTile){
                count++;
            }
        }
        return count;
    }

    /**
     * Move an existing tile, and change turn after.
     * @param fromQ Q coordinate of the tile to move
     * @param fromR R coordinate of the tile to move
     * @param toQ Q coordinate of the hexagon to move to
     * @param toR R coordinare of the hexagon to move to
     * @throws IllegalMove if tile can't be moved.
     */
    @Override
    public void move(int fromQ, int fromR, int toQ, int toR) throws IllegalMove {
        Stack<Tile> moveFromCell = board.getCell(fromQ, fromR);
        if(moveFromCell.isEmpty()){
            throw new IllegalMove();
        }
        Tile toMove = moveFromCell.pop();
        Stack<Tile> moveToCell = board.getCell(toQ, toR);
        moveToCell.push(toMove);
        setTurn();
    }

    /**
     * Pass the turn.
     * @throws IllegalMove if turn could not be passed.
     */
    @Override
    public void pass() throws IllegalMove {

    }

    /**
     * Check whether given player is the winner.
     * @param player Player to check
     * @return true if player is winner, false if player is not winner.
     */
    @Override
    public boolean isWinner(Player player) {
        return false;
    }

    /**
     * Check whether the game is a draw.
     * @return true if game is a draw, false if game is not a draw.
     */
    @Override
    public boolean isDraw() {
        return false;
    }
}
