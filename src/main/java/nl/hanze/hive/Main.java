package nl.hanze.hive;

import java.util.*;

import static nl.hanze.hive.Hive.Player.BLACK;
import static nl.hanze.hive.Hive.Player.WHITE;

public class Main implements Hive {

    private HashMap<Player, ArrayList<Tile>> decks;
    private Board board;
    private Player turn;
    public static final int[] NORTH_WEST = new int[]{0, -1};
    public static final int[] WEST = new int[]{-1, 0};
    public static final int[] SOUTH_WEST = new int[]{-1, 1};
    public static final int[] NORTH_EAST = new int[]{1, -1};
    public static final int[] EAST = new int[]{1, 0};
    public static final int[] SOUTH_EAST = new int[]{0, 1};
    public int[][] directions = new int[][]{NORTH_WEST, NORTH_EAST, WEST, SOUTH_WEST, EAST, SOUTH_EAST};

    // Default constructor, used to set decks of both players.
    // Decks will be saved in a HashMap: key = player; value = deck.
    public Main(){
        this.board = new Board();
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

        // Get the player
        Player player = getTurn();

        //TODO De gametile moet nog uit de playersdeck klasse komen
        // Create a gametile object with the player and tile information
        Gametile gametile = new Gametile(player, tile);
        if(!board.cellExists(q, r)){
            board.getCells().add(new Cell(q, r));
        }
        Cell cell = board.getCell(q, r);
        cell.getTiles().add(gametile);
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
        Cell moveFromCell = board.getCell(fromQ, fromR);
        if(moveFromCell == null || moveFromCell.getTiles().isEmpty()){
            throw new IllegalMove();
        }
        Gametile toMove = moveFromCell.getTiles().pop();
        if(!board.cellExists(toQ, toR)){
            board.getCells().add(new Cell(toQ, toR));
        }
        Cell moveToCell = board.getCell(toQ, toR);
        moveToCell.getTiles().push(toMove);
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
        // haal eerst QUEEN BEE op van tegenstander
        int tiles_with_contents_count = 0;
        Cell queen_bee = null;
        for(Cell cell : board.getCells()){
            Gametile tile = cell.getTiles().peek();
            if(tile.getTileName() == Tile.QUEEN_BEE && tile.getOwner() != player){
                queen_bee = cell;
                for(int[] direction : directions){
                    Cell nCell = board.getCell(queen_bee.q + direction[0], queen_bee.r + direction[1]);
                    if(nCell != null){
                        if(!nCell.getTiles().isEmpty()){
                            tiles_with_contents_count++;
                        }
                    }
                }
            }
        }
        // check of alle aangrenzende velden van de QUEEN_BEE van de tegenstander gevuld zijn met stenen.
        return tiles_with_contents_count == 6;
    }

    /**
     * Check whether the game is a draw.
     * @return true if game is a draw, false if game is not a draw.
     */
    @Override
    public boolean isDraw() {
        return false;
    }

    /**
     * Return the board that is used in this game.
     * @return the gameboard.
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Change the turn in the game.
     */
    public void setTurn() {
        this.turn = turn == WHITE ? BLACK : WHITE;
    }

    /**
     * Returns whose turn it is.
     * @return BLACK if it's the black player's turn, WHITE if it's the white player's turn.
     */
    public Player getTurn() {
        return turn;
    }
}
