package dk.lbi.adventofcode.day9;

import dk.lbi.adventofcode.utils.GetResourceFile;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Part1 {
    public void doWork() throws IOException {
        System.out.println("Hello");

        GetResourceFile resourceFile = new GetResourceFile();
        List<String> lines = Files.readAllLines(resourceFile.getFile("day9input.txt").toPath());

        // Set up "Board" and place head and tail in the middle
        GameBoard gameBoard = new GameBoard(lines);
        Player head = new Player("H", new Position(gameBoard.getRows()/2,gameBoard.getColumns()/2));
        Player tail = new Player("T", new Position(gameBoard.getRows()/2,gameBoard.getColumns()/2));

        gameBoard.addPlayer(head);
        gameBoard.addPlayer(tail);

        HashSet<String> result = new HashSet<>();

        for (String line : lines) {
            String[] splitLine = line.split(" ");

            for (int i = 0; i < Integer.parseInt(splitLine[1]); i++) {
                head.setPosition(getHeadNewPosition(head.getPosition(), splitLine[0]));

                System.out.println("Head moves + " + splitLine[0] + " : " + head.getPosition().toString());

                // If tail is not within allowed position, move to heads previous position
                if (!isTailPositionWithinRange(head.getPosition(), tail.getPosition())){

                    Position headPreviousPosition = head.getHistoricPositions().get(head.getHistoricPositions().size()-2);

                    tail.setPosition(new Position(headPreviousPosition.getRow(), headPreviousPosition.getColumn()));
                    result.add("r"+tail.getPosition().getRow()+"c"+tail.getPosition().getColumn());
                }
            }
        }
        System.out.println("Result: " + result.size());
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

    private boolean isTailPositionWithinRange(Position headPosition, Position currentPosition) {

        boolean isTailWithinRange = false;

        List<Position> allowedTailPositions = new ArrayList<>();

        // Is the head position within the allowed tail (3 X 3 ) space
        for (int i = currentPosition.getRow()-1; i <= currentPosition.getRow()+1; i++) {
            for (int y = currentPosition.getColumn()-1; y <= currentPosition.getColumn()+1; ++y){
                allowedTailPositions.add(new Position(i, y));
            }
        }

        for (Position p: allowedTailPositions) {
            if (p.getColumn() == headPosition.getColumn() && p.getRow() == headPosition.getRow()) isTailWithinRange = true;
        }
        return isTailWithinRange;
    }
}
