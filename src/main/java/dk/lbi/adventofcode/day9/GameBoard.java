package dk.lbi.adventofcode.day9;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameBoard {
    int rows;
    int columns;
    String[][] board;
    List<Knot> knots;

    public GameBoard(List<String> lines) {
        board = getDimensions(lines);
        for (String[] row : board) {
            Arrays.fill(row, ".");
        }
        this.knots = new ArrayList<>();
    }

    public GameBoard(List<String> lines, List<Knot> knots) {
        board = getDimensions(lines);
        for (String[] row : board) {
            Arrays.fill(row, ".");
        }
        this.knots = knots;
    }

    private String[][] getDimensions(List<String> lines) {
        int totalUp = 0;
        int totalDown = 0;
        int totalLeft = 0;
        int totalRight = 0;

        // Get array dimensions
        for (String line : lines) {
            String[] splitLine = line.split(" ");

            switch (splitLine[0]) {
                case "U":
                    totalUp+= Integer.parseInt(splitLine[1]);
                case "D":
                    totalDown+= Integer.parseInt(splitLine[1]);
                case "L":
                    totalLeft += Integer.parseInt(splitLine[1]);
                case "R":
                    totalRight += Integer.parseInt(splitLine[1]);
            }
        }
        this.rows = (totalDown + totalUp);
        this.columns = (totalLeft + totalRight);

        return new String[rows][columns];
    }

    public void addPlayer(Knot knot) {
        this.knots.add(knot);
    }

    public String printBoard() {
        for (Knot p: this.knots) {
            board[p.getPosition().getRow()][p.getPosition().getColumn()] = p.getName();
        }
        return Arrays.deepToString(board).replace("], ", "]\n");
    }

    public List<Knot> getPlayers() {
        return knots;
    }

    public void setPlayers(List<Knot> knots) {
        this.knots = knots;
    }

    public String[][] getBoard() {
        return board;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }
}
