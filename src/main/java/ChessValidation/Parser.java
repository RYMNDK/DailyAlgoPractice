package ChessValidation;

import ChessValidation.Entity.GameState;

public class Parser {
    public static GameState parseFEN(String gameFEN){
        String[] fen = gameFEN.split(" ");

        boolean isWhiteTurn = fen[0].equals("w");

        return new GameState(new String[0][0], 0);
    }
}
