package updater;

public abstract class GridUpdater {

    boolean[][] grid;

    public GridUpdater(boolean[][] grid) {
        this.grid = grid;
    }

    public abstract boolean[][] update();

    int getNumNeighbors(int row, int col) {
        int numRows = grid.length;
        int numCols = grid[0].length;
        int numNeighbors = 0;

        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                if (i >= 0 && i < numRows && j >= 0 && j < numCols && !(i == row && j == col)) {
                    if (grid[i][j]) {
                        numNeighbors++;
                    }
                }
            }
        }

        return numNeighbors;
    }

    boolean getNextGenState(boolean isAlive, int numNeighbors) {
        if (isAlive) {
            return numNeighbors == 2 || numNeighbors == 3;
        } else {
            return numNeighbors == 3;
        }
    }

}
