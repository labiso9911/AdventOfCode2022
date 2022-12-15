package dk.lbi.adventofcode.day9;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameBoard {
    int rows;
    int columns;
    String[][] board;
    List<Player> players;

    public GameBoard(List<String> lines) {
        board = getDimensions(lines);
        for (String[] row : board) {
            Arrays.fill(row, ".");
        }
        this.players = new ArrayList<>();
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
                    totalUp++;
                case "D":
                    totalDown++;
                case "L":
                    totalLeft++;
                case "R":
                    totalRight++;
            }
        }
        this.rows = totalDown + totalUp;
        this.columns = totalLeft + totalRight;

        return new String[rows][columns];
    }

    public void addPlayer(Player player) {
        this.players.add(player);
    }

    public String printBoard() {
        for (Player p: this.players) {
            board[p.getPosition().getRow()][p.getPosition().getColumn()] = p.getName();
        }
        return Arrays.deepToString(board).replace("], ", "]\n");
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
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