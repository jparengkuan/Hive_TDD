package nl.hanze.hive;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

public class Hexagon {

    /**
     * Deze klasse representeert hexagon
     * Een hexagon is een positie op het speelveld
     */

    public int q;
    public int r;

    public Hexagon(int q, int r){
        this.q = q;
        this.r = r;
    }

    @Override
    public boolean equals(Object o){
        if (o == this) return true;

        else if (!(o instanceof Hexagon)) return false;

        return this.q == ((Hexagon) o).q && this.r == ((Hexagon) o).r;
    }

    @Override
    public int hashCode() {
        return Objects.hash(q, r);
    }

    public ArrayList<Hexagon> getAllNeighBours(){
        ArrayList<Hexagon> neighbours = new ArrayList<Hexagon>();
        neighbours.add(new Hexagon(q + 1, r));
        neighbours.add(new Hexagon(q, r + 1));
        neighbours.add(new Hexagon(q - 1, r + 1));
        neighbours.add(new Hexagon(q - 1, r));
        neighbours.add(new Hexagon(q, r - 1));
        neighbours.add(new Hexagon(q + 1, r - 1));
        return neighbours;
    }

    public ArrayList<Hexagon> getAllCommonNeighBours(Hexagon position){
        ArrayList<Hexagon> commonNeighBours = getAllNeighBours();
        commonNeighBours.retainAll(position.getAllNeighBours());
        return commonNeighBours;
    }

}
