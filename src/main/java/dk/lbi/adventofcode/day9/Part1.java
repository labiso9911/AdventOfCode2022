package dk.lbi.adventofcode.day9;

import dk.lbi.adventofcode.utils.GetResourceFile;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class Part1 {
    public void doWork() throws IOException {
        System.out.println("Hello");

        GetResourceFile resourceFile = new GetResourceFile();
        List<String> lines = Files.readAllLines(resourceFile.getFile("day9input.txt").toPath());

        // Set up "Board" and place head and tail in the middle
        GameBoard gameBoard = new GameBoard(lines);
        String[][] board = gameBoard.getBoard();
        Player head = new Player("H", new Position(gameBoard.getRows()/2,gameBoard.getColumns()/2));
        Player tail = new Player("T", new Position(gameBoard.getRows()/2,gameBoard.getColumns()/2));

        gameBoard.addPlayer(head);
        gameBoard.addPlayer(tail);

        System.out.println(gameBoard.printBoard());
        System.out.println("Rows: " + gameBoard.getRows());
        System.out.println("Columns: " + gameBoard.getColumns());


        for (String line : lines) {
            String[] splitLine = line.split(" ");

            for (int i = 0; i < Integer.parseInt(splitLine[1]); i++) {
                head.setPosition(getHeadNewPosition(head.getPosition(), splitLine[0]));
                System.out.println(head.getPosition().toString());




            }



        }
    }

    private Position getHeadNewPosition(Position currentPosition, String direction) {

        Position newPosition = new Position(currentPosition.getRow(), currentPosition.getColumn());

        switch (direction){
            case "U" : {
                newPosition.setRow(currentPosition.getRow() - 1);
                break;
            }
            case "D" : {
                newPosition.setRow(currentPosition.getRow() + 1);
                break;
            }
            case "R" : {
                newPosition.setColumn(currentPosition.getColumn() + 1);
                break;
            }
            case "L" : {
                newPosition.setColumn(currentPosition.getColumn() - 1);
                break;
            }
        }
        return newPosition;
    }

    private Position getTailNewPosition(Position headPosition, Position currentPosition) {

        Position newPosition = new Position(currentPosition.getRow(), currentPosition.getColumn());

        // Standing in same position
        if (headPosition.getRow() == currentPosition.getRow() && headPosition.getColumn() == currentPosition.getColumn()) {
            // Do nothing
        }
        // If same row but different column, left or right
        if (headPosition.getRow() == currentPosition.getRow() && headPosition.getColumn() != currentPosition.getColumn()) {
            // If position is to the right of tail move right
            if (headPosition.getColumn() > currentPosition.getColumn()+1 ) {
                newPosition.setColumn(currentPosition.getColumn() + 1);
                // If position is to the left of tail move left
            } else if(headPosition.getColumn() > currentPosition.getColumn() -1 ) {
                newPosition.setColumn(currentPosition.getColumn() - 1);
            }
        }
        // If same column but different column, up or down
        if (headPosition.getRow() != currentPosition.getRow() && headPosition.getColumn() == currentPosition.getColumn()) {
            // If position is to the right of tail move right
            if (headPosition.getRow() > currentPosition.getRow()+1 ) {
                newPosition.setRow(currentPosition.getRow() + 1);
                // If position is to the left of tail move left
            } else if(headPosition.getRow() > currentPosition.getRow() -1 ) {
                newPosition.setRow(currentPosition.getRow() - 1);
            }
        }




        return newPosition;
    }
}
