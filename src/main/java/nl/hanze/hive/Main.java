package nl.hanze.hive;

import java.util.*;

import static nl.hanze.hive.Hive.Player.BLACK;
import static nl.hanze.hive.Hive.Player.WHITE;

public class Main implements Hive {

    private Deck decks;
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
        this.decks = new Deck();
        this.turn = WHITE;
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
        Tile tileFromDeck = decks.getTilefromDeck(tile, player);
        Gametile gametile = new Gametile(player, tileFromDeck);
        if(tileFromDeck == null){
            throw new IllegalMove("Player has already played the tile.");
        }

        // Als de cell nog niet bestaat maak dan een nieuwe aan
        if(!board.cellExists(q, r)){
            board.getCells().add(new Cell(q, r));
        }

        Cell cell = board.getCell(q, r);

        // Check of er al een tile op de coordinaten is geplaatst
        if(!cell.isEmpty())
        {
            throw new IllegalMove("cell has already a tile");
        }
        else {
            ArrayList<Cell> neighbors = board.GetNeighboursFromCell(cell);
            boolean hasAdjacentTiles = false;
            for(Cell neighbor : neighbors){
                Cell actualNeighbor = board.getCell(neighbor.q, neighbor.r);
                if(actualNeighbor != null){
                    if(!actualNeighbor.isEmpty()){
                        if(actualNeighbor.getTiles().peek().getOwner() != player){
                            throw new IllegalMove("player cannot play tile next to opponent tile");
                        }
                        hasAdjacentTiles = true;
                    }
                }
            }
            if(decks.getDeck(player).size() < Deck.DECK_SIZE - 3 && decks.countTiles(Tile.QUEEN_BEE, player) > 0){
                throw new IllegalMove("after 3 tiles player must play queen bee");
            }
            if(board.getCells().size() <= 2 || hasAdjacentTiles){
                cell.getTiles().add(gametile);
                setTurn();
            }
            else{
                throw new IllegalMove("cell must be played next to another tile");
            }
        }

    }

    /**
     * Count the occurrences of a specific tile in a specific player's deck.
     * @param specificTile The tile that is being counted.
     * @param player The player whose deck is being iterated on.
     * @return the amount of occurrences of specificTile.
     */
    public int countTiles(Tile specificTile, Player player){
        ArrayList<Tile> deck = decks.getDeck(player);
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
        Player player = getTurn();

        // If cell does not exist or is empty
        if(moveFromCell == null || moveFromCell.isEmpty()){
            throw new IllegalMove("tried to move from this cell, but cell doesn't exist");
        }

        // if player has not played queen bee yet
        if(decks.getDeck(player).contains(Tile.QUEEN_BEE)){
            throw new IllegalMove("player needs to play queen bee before moving any tiles");
        }

        // If cell player tries to move is an opponent's tile
        if(moveFromCell.getTiles().peek().getOwner() != player){
            throw new IllegalMove("player can't move opponent's tiles");
        }

        Gametile toMove = moveFromCell.getTiles().pop();

        // If cell does not exist yet, add cell to board
        if(!board.cellExists(toQ, toR)){
            board.getCells().add(new Cell(toQ, toR));
        }

        // test if the cell has any neighbors containing tiles (later to be refactored into a different method)
        ArrayList<Cell> neighbors = board.GetNeighboursFromCell(board.getCell(toQ, toR));
        boolean hasAdjacentCells = false;
        for(Cell neighbor : neighbors){
            Cell actualNeighbor = board.getCell(neighbor.q, neighbor.r);
            if(actualNeighbor != null){
                if(!actualNeighbor.isEmpty()){
                    if(actualNeighbor.getTiles().peek().getOwner() != player){
                        throw new Hive.IllegalMove("can't move tile next to opponent");
                    }
                    hasAdjacentCells = true;
                }
            }
        }

        if(hasAdjacentCells){
            // Add tile to cell and change turn
            Cell moveToCell = board.getCell(toQ, toR);
            moveToCell.getTiles().push(toMove);
            setTurn();
        }
        else{
            throw new IllegalMove("the tile must be moved to a cell next to another of the player's tiles.");
        }
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
                        if(!nCell.isEmpty()){
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
        for(Player player: Player.values()){
            if(!isWinner(player)){
                return false;
            }
        }
        return true;
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
