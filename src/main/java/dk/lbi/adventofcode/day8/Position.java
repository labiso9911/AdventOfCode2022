package dk.lbi.adventofcode.day8;

public class Position {
    int row;
    int column;

    public boolean atEdge(int rowSize, int columnSize){
        return this.row == 0 || this.row == rowSize || this.column == 0 || this.column == columnSize;
    }
}
