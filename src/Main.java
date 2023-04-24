public class Main {

    public static void main(String[] args) {

        int numGenerations = 100;
        int numRows = 2000;
        int numCols = 2000;

        runSimulationSingleThreaded(numGenerations, numRows, numCols);
        runSimulationMultiThreaded(numGenerations, numRows, numCols, 2);
        runSimulationMultiThreaded(numGenerations, numRows, numCols, 4);
        runSimulationMultiThreaded(numGenerations, numRows, numCols, 8);
        runSimulationMultiThreaded(numGenerations, numRows, numCols, 16);

    }

    private static void runSimulationSingleThreaded(int numGenerations, int numRows, int numCols) {

        System.out.println("Running simulation single threaded...");

        long startTime = System.nanoTime();

        GameOfLife gameOfLife = new GameOfLife(numRows, numCols, numGenerations, false, 0);
        gameOfLife.start(false);

        long endTime = System.nanoTime();

        long durationInNano = (endTime - startTime);
        long durationInMillis = durationInNano / 1000000;

        System.out.println("Execution time in nanoseconds : " + durationInNano   + "ns");
        System.out.println("Execution time in milliseconds: " + durationInMillis + "ms");

    }

    private static void runSimulationMultiThreaded(int numGenerations, int numRows, int numCols, int numThreads) {

        System.out.println("Running simulation multi threaded with " + numThreads + " threads...");

        long startTime = System.nanoTime();

        GameOfLife gameOfLife = new GameOfLife(numRows, numCols, numGenerations, true, numThreads);
        gameOfLife.start(false);

        long endTime = System.nanoTime();

        long durationInNano = (endTime - startTime);
        long durationInMillis = durationInNano / 1000000;

        System.out.println("Execution time in nanoseconds : " + durationInNano   + "ns");
        System.out.println("Execution time in milliseconds: " + durationInMillis + "ms");

    }



}