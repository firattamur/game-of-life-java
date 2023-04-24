import printer.GridPrinter;
import updater.GridUpdater;
import updater.MultiThreadedGridUpdater;
import updater.SingleThreadGridUpdater;

public class GameOfLife {

    private boolean[][] grid;
    private int numGenerations;
    private GridUpdater gridUpdater;
    private GridPrinter gridPrinter;

    public GameOfLife(int numRows, int numCols, int numGenerations, boolean multiThreaded, int numThreads) {
        this.grid = new boolean[numRows][numCols];
        this.numGenerations = numGenerations;

        if (multiThreaded) {
            this.gridUpdater = new MultiThreadedGridUpdater(this.grid, numThreads);
        } else {
            this.gridUpdater = new SingleThreadGridUpdater(this.grid);
        }

        this.gridPrinter = new GridPrinter();
    }

    public void start(boolean printGrids) {
        this.initGrid();
        this.runSimulation(printGrids);
    }

    private void initGrid() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = Math.random() < 0.5;
            }
        }
    }

    private void runSimulation(boolean printGrids) {
        for (int i = 0; i < numGenerations; i++) {

            this.grid = this.gridUpdater.update();

            if (printGrids) {
                this.gridPrinter.print(this.grid, i);
            }

        }
    }

}
