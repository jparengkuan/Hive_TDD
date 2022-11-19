package nl.hanze.hive;

public interface MoveBehavior {

    boolean isLegalMove(int fromQ, int fromR, int toQ, int toR);
}
