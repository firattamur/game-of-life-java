package updater;

public class SingleThreadGridUpdater extends GridUpdater {

    public SingleThreadGridUpdater(boolean[][] grid) {
        super(grid);
    }

    @Override
    public boolean[][] update() {
        int numRows = grid.length;
        int numCols = grid[0].length;

        boolean[][] newGrid = new boolean[numRows][numCols];

        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                int numNeighbors = this.getNumNeighbors(row, col);
                boolean isAlive = grid[row][col];
                boolean isAliveNextGen = this.getNextGenState(isAlive, numNeighbors);
                newGrid[row][col] = isAliveNextGen;
            }
        }

        this.grid = newGrid;
        return grid;
    }

}