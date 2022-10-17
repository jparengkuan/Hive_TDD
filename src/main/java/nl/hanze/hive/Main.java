package nl.hanze.hive;

import java.util.ArrayList;
import java.util.HashMap;

import static nl.hanze.hive.Hive.Player.BLACK;
import static nl.hanze.hive.Hive.Player.WHITE;

public class Main implements Hive {

    private HashMap<Player, ArrayList<Tile>> decks;

    public Main(){
        this.decks = new HashMap<>();
        setDeck(BLACK);
        setDeck(WHITE);
    }

    @Override
    public void play(Tile tile, int q, int r) throws IllegalMove {

    }

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

    public ArrayList<Tile> getDeck(Player player){
        return decks.get(player);
    }

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
}
