import java.io.IOException;
import java.util.Scanner;

public class Input {
    private Scanner scanner = new Scanner(System.in);

    public int[] getBoardSize() throws IOException {
        int[] boardSize = new int[2];

        System.out.println("Please enter grid size in format x,y\nYour input has to be in the range x <= y < 1000");
        String boardSizeInput = this.scanner.nextLine();
        String[] size = boardSizeInput.split(",");
        String x = size[0].trim();
        String y = size[1].trim();

        int cols = Integer.parseInt(x);
        int rows = Integer.parseInt(y);

        if (cols > rows || rows >= 1000 || cols <= 0){
            throw new IOException("Please enter valid data! x <= y < 1000!");
        }

        boardSize[0] = rows;
        boardSize[1] = cols;

        return boardSize;
    }

    public Board getGenerationZero(int y, int x) throws IOException {

        int[][] generationZeroGrid = new int[y][x];

        System.out.println("Please enter the grid for Generation Zero");

        for (int i = 0; i < y; i++) {
            String inputLine = this.scanner.nextLine();
            int numberOfLineElements = inputLine.length();

            for (int j = 0; j < x; j++) {
                char character = inputLine.charAt(j);
                int rowElement = Integer.parseInt(String.valueOf(character));
                if (numberOfLineElements > y || rowElement != 1 && rowElement != 0){
                    throw new IOException("Please enter valid data! \nA grid line should consist only of 1s and 0s and be as many characters as your input y value.");
                }
                generationZeroGrid[i][j] = rowElement;
            }
        }
        Board generationZero = new Board(generationZeroGrid);
        return generationZero;
    }

    public int[] getLastArguments(){

        int[] lastArguments = new int[3];

        System.out.println("Please enter coordinates of a chosen cell, and generation N.\nYou input should be in the format x,y,N.");
        String[] cellCoordinatesAndN = this.scanner.nextLine().split(",");

        String x1 = cellCoordinatesAndN[0].trim();
        String y1 = cellCoordinatesAndN[1].trim();
        String N = cellCoordinatesAndN[2].trim();

        lastArguments[0] = Integer.parseInt(x1);
        lastArguments[1] = Integer.parseInt(y1);
        lastArguments[2] = Integer.parseInt(N);

        return lastArguments;
    }
}
