package nl.hanze.hive;

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

}
