import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Input userInput = new Input();
        int[] boardSize = userInput.getBoardSize();
        int gridRows = boardSize[0];
        int gridColumns = boardSize[1];

        int[][] generationZeroGrid = userInput.getGenerationZero(gridRows, gridColumns);

        int[] lastLineArguments = userInput.getLastArguments();
        int rowCoordinate = lastLineArguments[0];
        int columnCoordinate = lastLineArguments[1];
        int n = lastLineArguments[2];

        Board board = new Board();
        int generationsTheCellWasGreen = board.getGenerationsTheCellWasGreen(generationZeroGrid, rowCoordinate, columnCoordinate, n);

        System.out.println(generationsTheCellWasGreen);
    }

}
