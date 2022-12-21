package dk.lbi.adventofcode.day9;

import java.util.Stack;

public class Knot {
    private String name;
    private Position position;
    private Stack<Position> historicPositions;

    public Knot(String name, Position position) {
        this.name = name;
        this.position = position;
        historicPositions = new Stack<>();
        historicPositions.add(position);
    }

    public Stack<Position> getHistoricPositions() {
        return historicPositions;
    }

    public void setHistoricPositions(Stack<Position> historicPositions) {
        this.historicPositions = historicPositions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {

        this.position = position;
        historicPositions.add(position);
    }

    public void move(Direction direction){
        switch (direction) {
            case UP : this.position.setRow(this.position.getRow() -1 );
            case DOWN: this.position.setRow(this.position.getRow() +1 );
            case LEFT: this.position.setColumn(this.position.getColumn() -1 );
            case RIGHT: this.position.setColumn(this.position.getColumn() +1 );
        }
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", position=" + position +
                '}';
    }
}
