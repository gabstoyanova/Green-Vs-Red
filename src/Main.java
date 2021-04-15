import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Input userInput = new Input();
        int[] boardSize = userInput.getBoardSize();
        int gridRows = boardSize[0];
        int gridColumns = boardSize[1];

        Board generationZeroGrid = userInput.getGenerationZero(gridRows, gridColumns);

        int[] lastLineArguments = userInput.getLastArguments();
        int rowCoordinate = lastLineArguments[0];
        int columnCoordinate = lastLineArguments[1];
        int n = lastLineArguments[2];

        int generationsTheCellWasGreen = generationZeroGrid.getGenerationsTheCellWasGreen(generationZeroGrid, rowCoordinate, columnCoordinate, n);

        System.out.println("This cell was green in " + generationsTheCellWasGreen + " generations from 0 to " + n);
    }

}
