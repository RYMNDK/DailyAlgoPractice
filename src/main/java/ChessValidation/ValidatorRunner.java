package ChessValidation;

public class ValidatorRunner {
    public static void main(String[] args) {

        String currentFEN = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
        String move = "e2e4";

        try
        {
            // boolean isValid = ValidateMove(currentFEN, move);
            // System.out.println(isValid ? "Valid move." : "Invalid move.");
        }
        catch (Exception ex)
        {
            System.out.println("An error occurred: " + ex.getMessage());
        }

    }
}
