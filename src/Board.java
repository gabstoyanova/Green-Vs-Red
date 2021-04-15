public class Board {

    private int x, y;
    private int[][] grid;

    public Board(int[][] grid) {
        this.grid = grid;
        this.x = grid[0].length;
        this.y = grid.length;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int[][] getGrid() {
        return this.grid;
    }

    public int getGenerationsTheCellWasGreen(Board generationZero, int y1, int x1, int n){
        Board current = generationZero;
        int wantedCell = generationZero.getGrid()[x1][y1];
        int numberOfGenerationsTheCellWasGreen = 0;

        if (wantedCell == 1) {
            numberOfGenerationsTheCellWasGreen++;
        }

        // for each of the next generations
        for (int i = 0; i < n; i++) {
//            Board nextGeneration =
            current = getNextGeneration(current);
            wantedCell = current.getGrid()[y1][x1];

            if (wantedCell == 1) {
                numberOfGenerationsTheCellWasGreen++;
            }
        }

        return numberOfGenerationsTheCellWasGreen;
    }

    private Board getNextGeneration(Board board){
        int[][] grid = board.getGrid();
        int y = grid.length;
        int x = grid[0].length;

        int[][] nextGenerationGrid = new int[y][x];

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {

                int currentColor = grid[i][j];
                int greenNeighbours = getGreenNeighbours(board, i, j);

                int newCellColor = changeColor(currentColor, greenNeighbours);
                nextGenerationGrid[i][j] = newCellColor;
            }
        }
        Board nextGeneration = new Board(nextGenerationGrid);
        return nextGeneration;
    }

    private int getGreenNeighbours(Board board, int i, int j){
        int y = board.getY();
        int x = board.getX();
        int greenCount = 0;

        for (int row = i - 1; row <= i + 1; row++){
            for (int col = j - 1; col <= j + 1; col++) {
                if (row >= 0 && row < x && col >= 0 && col < y){
                    if (row == i && col == j){
                        continue;
                    }
                    if (board.getGrid()[row][col] == 1){
                        greenCount++;
                    }
                }
            }
        }
        return greenCount;
    }

    //  Each green cell surrounded by 0, 1, 4, 5, 7 or 8 green neighbours will become red in the next generation.
    //  and will stay green in the next generation if it has either 2, 3 or 6 green neighbours.
    //  Similarly, each red cell that is surrounded by exactly 3 or 6 green cells will become green.

    private int changeColor(int current, int greenNeighboursCount){
        int newColor = 0;
        if (current == 0){
            if (greenNeighboursCount == 3 || greenNeighboursCount == 6) {
                newColor = 1;
            }
        } else if (current == 1){
            if (greenNeighboursCount == 2 || greenNeighboursCount == 3 || greenNeighboursCount == 6) {
                newColor = 1;
            }
        }
        return newColor;
    }

}
