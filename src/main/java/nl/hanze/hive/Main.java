package nl.hanze.hive;

public class Main implements Hive {

    /**
     * Default constructor for the game
     */
    public Main() {

    }

    /**
     * Play a new tile.
     * @param tile Tile to play
     * @param q Q coordinate of hexagon to play to
     * @param r R coordinate of hexagon to play to
     * @throws IllegalMove if tile can't be played.
     */
    @Override
    public void play(Tile tile, int q, int r) throws IllegalMove {

    }

    /**
     * Move an existing tile.
     * @param fromQ Q coordinate of the tile to move
     * @param fromR R coordinate of the tile to move
     * @param toQ Q coordinate of the hexagon to move to
     * @param toR R coordinare of the hexagon to move to
     * @throws IllegalMove if tile can't be moved.
     */
    @Override
    public void move(int fromQ, int fromR, int toQ, int toR) throws IllegalMove {

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
