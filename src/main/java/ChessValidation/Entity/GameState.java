package ChessValidation.Entity;

public class GameState {
    String[][] board;
    int turn;
    public GameState(String[][] board, int turn) {
        this.board = board;
    }
}
