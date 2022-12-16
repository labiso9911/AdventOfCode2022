package dk.lbi.adventofcode.day9;

import java.util.Stack;

public class Player {
    private String name;
    private Position position;
    private Stack<Position> historicPositions;

    public Player(String name, Position position) {
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

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", position=" + position +
                '}';
    }
}
