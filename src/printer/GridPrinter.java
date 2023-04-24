package printer;

import java.util.Collections;

public class GridPrinter {

    /**
     * Prints the grid to the console.
     */
    public void print(boolean[][] grid, int generation) {
        int numRows = grid.length;
        int numCols = grid[0].length;

        StringBuilder gridString = new StringBuilder();
        gridString.append("Generation: ").append(generation).append(System.lineSeparator());

        String horizontalLine = createHorizontalLine(numCols);
        gridString.append(horizontalLine).append(System.lineSeparator());

        for (boolean[] row : grid) {
            gridString.append(createRowString(row, numCols));
            gridString.append(horizontalLine).append(System.lineSeparator());
        }

        System.out.println(gridString.toString());
    }

    private String createHorizontalLine(int numCols) {
        return String.join("", Collections.nCopies((numCols / 2) * 4 + 1, "——"));
    }

    private String createRowString(boolean[] row, int numCols) {
        StringBuilder rowString = new StringBuilder();
        rowString.append('|');
        for (int col = 0; col < numCols; col++) {
            char cell = row[col] ? 'X' : ' ';
            rowString.append(" ").append(cell).append(" |");
        }
        rowString.append(System.lineSeparator());
        return rowString.toString();
    }

}
