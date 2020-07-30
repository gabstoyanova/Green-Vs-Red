public class Board {

    private int x, y;
    public int[][] grid;

    public Board(int[][] grid){
        this.grid = grid;
        this.x = grid[0].length;
        this.y = grid.length;
    }

    public int getGenerationsTheCellWasGreen(Board generationZero, int y1, int x1, int n){

        Board current = generationZero;
        int wantedCell = generationZero.grid[x1][y1];
        int numberOfGenerationsTheCellWasGreen = 0;

        if (wantedCell == 1) {
            numberOfGenerationsTheCellWasGreen++;
        }

        // for each of the next generations
        for (int i = 0; i < n; i++) {
            Board nextGeneration = getNextGeneration(current);
            current = nextGeneration;
            wantedCell = current.grid[y1][x1];

            if (wantedCell == 1) {
                numberOfGenerationsTheCellWasGreen++;
            }
        }

        return numberOfGenerationsTheCellWasGreen;
    }

    private Board getNextGeneration(Board board){
        int y = board.grid.length;
        int x = board.grid[0].length;

        int[][] nextGenerationGrid = new int[y][x];

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {

                int currentColor = board.grid[i][j];
                int greenNeighbours = getGreenNeighbours(board, i, j);

                int newCellColor = changeColor(currentColor, greenNeighbours);
                nextGenerationGrid[i][j] = newCellColor;
            }
        }
        Board nextGeneration = new Board(nextGenerationGrid);
        return nextGeneration ;
    }

    private int getGreenNeighbours(Board board, int i, int j){
        int y = board.grid.length;
        int x = board.grid[0].length;
        int greenCount = 0;

                // upper left cell
                if (i - 1 >= 0 && j - 1 >= 0) {
                    if (board.grid[i - 1][j - 1]  == 1) {
                        greenCount++;
                    }
                }
                // upper center cell
                if (i - 1 >= 0) {
                    if (board.grid[i - 1][j] == 1) {
                        greenCount++;
                    }
                }
                // upper right cell
                if (i - 1 >= 0 && j + 1 < y) {
                    if (board.grid[i - 1][j + 1] == 1) {
                        greenCount++;
                    }
                }
                // lower left cell
                if (i + 1 < x && j - 1 >= 0) {
                    if (board.grid[i + 1][j - 1] == 1) {
                        greenCount++;
                    }
                }
                // lower center cell
                if (i + 1 < x) {
                    if (board.grid[i + 1][j] == 1) {
                        greenCount++;
                    }
                }
                // lower right cell
                if (i + 1 < x && j + 1 < y) {
                    if (board.grid[i + 1][j + 1] == 1) {
                        greenCount++;
                    }
                }
                // left cell
                if (j - 1 >= 0) {
                    if (board.grid[i][j - 1] == 1) {
                        greenCount++;
                    }
                }
                // right cell
                if (j + 1 < y) {
                    if (board.grid[i][j + 1] == 1) {
                        greenCount++;
                    }
                }
        return greenCount;
    }

    private int redCellsChange(int neighbourCount) {

        int cellColor = 0;
        if (neighbourCount == 3 || neighbourCount == 6){
            cellColor = 1;
        } else if (neighbourCount == 0 || neighbourCount == 1 ||
                neighbourCount == 2 || neighbourCount == 4 ||
                neighbourCount == 5 || neighbourCount == 7 ||
                neighbourCount == 8){
            cellColor = 0;
        }
        return cellColor;
    }

    //  3. Each green cell surrounded by 0, 1, 4, 5, 7 or 8 green neighbours will become red in the next generation.
    //  4. A green cell will stay green in the next generation if it has either 2, 3 or 6 green neighbours.
    private int greenCellsChange(int neighbourCount) {

        int cellColor = 0;
        if (neighbourCount == 2 || neighbourCount == 3 || neighbourCount == 6) {
            cellColor = 1;
        } else if (neighbourCount == 0 || neighbourCount == 1 ||
                neighbourCount == 4 || neighbourCount == 5 ||
                neighbourCount == 7 || neighbourCount == 8) {
            cellColor = 0;
        }
        return cellColor;
    }

    public int changeColor(int currentColor, int greenNeighbours) {

        int newColor = 0;

        switch (currentColor) {
            //if current color is red:
            case 0:
                newColor = redCellsChange(greenNeighbours);
                break;

            // if current color is green:
            case 1:
                newColor = greenCellsChange(greenNeighbours);
                break;
        }
        return newColor;
    }
}
