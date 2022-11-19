package nl.hanze.hive;

public interface MoveBehavior {

    void move(int fromQ, int fromR, int toQ, int toR) throws Hive.IllegalMove;
}

