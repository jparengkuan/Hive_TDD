package nl.hanze.hive;

import java.util.ArrayList;
import java.util.HashMap;
import nl.hanze.hive.Hive.Player;

/**
 * This class represents a players deck
 * The decks consists of titles, that the player can move
 */

public class Deck {

    // Hashmap to keep track of the players titles
    private HashMap<Hive.Player, ArrayList<Hive.Tile>> decks;


    /**
     * Constructor
     */
    public Deck() {
        // Create the hashmap
        this.decks = new HashMap<>();

        // Create the starters deck for player white
        createDeck(Player.WHITE);
    }

    /**
     * Add the game tiles to a specific player's deck.
     * @param player The player whose deck is being created
     */
    public void createDeck(Player player) {
        // Create an arraylist that holds the titles for the players deck
        ArrayList<Hive.Tile> deck = new ArrayList<>();

        // Add one queen bee title to the deck
        deck.add(Hive.Tile.QUEEN_BEE);

        // Add two spider and beetle titles to the deck
        for(int i=0; i<2; i++){
            deck.add(Hive.Tile.SPIDER);
            deck.add(Hive.Tile.BEETLE);
        }

        // Add three soldiers ants and grasshoppers to the deck
        for(int i=0; i<3; i++){
            deck.add(Hive.Tile.SOLDIER_ANT);
            deck.add(Hive.Tile.GRASSHOPPER);
        }

        // Add the deck to the player
        decks.put(player, deck);
    }

    /**
     * Get the deck from a player.
     * @param player The player whose deck is being returned
     * @return the deck from the player.
     */
    private ArrayList<Hive.Tile> getDeck(Player player){
        return decks.get(player);
    }

    /**
     * Count the occurrences of a specific tile in a specific player's deck.
     * @param specificTile The tile that is being counted.
     * @param player The player whose deck is being iterated on.
     * @return the amount of occurrences of specificTile.
     */
    public int countTiles(Hive.Tile specificTile, Player player){

        // Get the deck from the player
        ArrayList<Hive.Tile> deck = getDeck(player);

        // Init titles count set to zero
        int count = 0;

        // Loop through the tiles in the deck
        for(Hive.Tile tile : deck){

            // If the tile is in the players deck increment the count
            if(tile == specificTile){
                count++;
            }
        }
        return count;
    }






}
