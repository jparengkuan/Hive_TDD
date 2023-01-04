package nl.hanze.hive;

import nl.hanze.hive.Hive;

/**
 * Deze klasse representeert de Hive Game
 */

public class HiveGame implements Hive {

    private Player currenPlayer;

    public HiveGame(){
        // Als eerst is wit aan de beurt
        currenPlayer = Player.WHITE;

    }

    @Override
    public void play(Tile tile, int q, int r) throws IllegalMove {

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

    public Player getCurrenPlayer() {
        return currenPlayer;
    }

    public void setCurrenPlayer(Player currenPlayer) {
        this.currenPlayer = currenPlayer;
    }
}
