package dk.lbi.adventofcode.day9;

import dk.lbi.adventofcode.utils.GetResourceFile;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Part2 {

    public void doWork() throws IOException {
        GetResourceFile resourceFile = new GetResourceFile();
        List<String> lines = Files.readAllLines(resourceFile.getFile("day9input.txt").toPath());

        // Set up "Board" and place head and tail in the middle
        GameBoard gameBoard = new GameBoard(lines);

        List<Knot> ropes = new ArrayList<>();


        // Initialize knots
        ropes.add(new Knot("H", new Position(gameBoard.getRows()/2,gameBoard.getColumns()/2)));
        for(int i = 1; i<9; i++) {
            ropes.add(new Knot(""+i, new Position(gameBoard.getRows()/2, gameBoard.getColumns()/2)));
        }
        ropes.add(new Knot("T", new Position(gameBoard.getRows()/2,gameBoard.getColumns()/2)));

        HashSet<String> result = new HashSet<>();

        for (String line : lines) {
            String[] splitLine = line.split(" ");

            for (int i = 0; i < Integer.parseInt(splitLine[1]); i++) {
                // Set head position
                ropes.get(0).setPosition(getHeadNewPosition(ropes.get(0).getPosition(), splitLine[0]));
                System.out.println("Head moved to: " + ropes.get(0).getPosition().toString());
                for (int y=1; y < ropes.size(); y++) {

                    // set each position of the knot accordingly to the previous knot
                    // If the position is not within the previous move to the previous last position
                    if (!isPositionWithinRange(ropes.get(y-1).getPosition(), ropes.get(y).getPosition())){

                        Position headPreviousPosition = ropes.get(y-1).getHistoricPositions().get(ropes.get(y-1).getHistoricPositions().size()-2);

                        ropes.get(y).setPosition(new Position(headPreviousPosition.getRow(), headPreviousPosition.getColumn()));

                        if(y == 9) {
                            System.out.println("Knot: " + ropes.get(y).getName() + " moved to " + ropes.get(y).getPosition().toString());
                        }
                    }
                    result.add("r"+ropes.get(9).getPosition().getRow()+"c"+ropes.get(9).getPosition().getColumn());
                }
            }
        }
        System.out.println("Result Part 2: " + result.size());
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

    private boolean isPositionWithinRange(Position headPosition, Position currentPosition) {

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
