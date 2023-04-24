package updater;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MultiThreadedGridUpdater extends GridUpdater {

    int numThreads;

    public MultiThreadedGridUpdater(boolean[][] grid, int numThreads) {
        super(grid);
        this.numThreads = numThreads;
    }

    @Override
    public boolean[][] update() {

        int numRows = grid.length;
        int numCols = grid[0].length;

        boolean[][] newGrid = new boolean[numRows][numCols];

        ExecutorService executor = Executors.newFixedThreadPool(numThreads);
        int rowsPerThread = numRows / numThreads;

        for (int threadId = 0; threadId < numThreads; threadId++) {
            int startRow = threadId * rowsPerThread;
            int endRow   = (threadId == numThreads - 1) ? numRows : (threadId + 1) * rowsPerThread;

            executor.submit(() -> {
                for (int row = startRow; row < endRow; row++) {
                    for (int col = 0; col < numCols; col++) {
                        int numNeighbors = this.getNumNeighbors(row, col);
                        boolean isAlive = grid[row][col];
                        boolean isAliveNextGen = this.getNextGenState(isAlive, numNeighbors);
                        newGrid[row][col] = isAliveNextGen;
                    }
                }
            });

        }

        executor.shutdown();

        try {
            executor.awaitTermination(5, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        this.grid = newGrid;
        return grid;

    }

}
