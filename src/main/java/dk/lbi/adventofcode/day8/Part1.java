package dk.lbi.adventofcode.day8;

import dk.lbi.adventofcode.utils.GetResourceFile;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Part1 {
    public void doWork() throws IOException {
        GetResourceFile resourceFile = new GetResourceFile();
        List<String> lines = Files.readAllLines(resourceFile.getFile("day8input.txt").toPath());

        // Input all field into 2d array
        List<List<Character>> map = new ArrayList<List<Character>>();

        lines.forEach(line -> {
            List<Character> chars = line.chars().mapToObj(e->(char)e).collect(Collectors.toList());
            map.add(chars);
        });

        // Size of given 2d array
        int n = map.size();
        int m = map.get(0).size();

        int result = 0;

        // Run through 2d map and create nodes
        for (int row = 0; row < map.size(); row++) {
            for (int col = 0; col < map.get(row).size(); col++) {
                if (atEdge(row, col, n, m) || ){
                    result++;
                }

            }
        }

    }
    public boolean atEdge(int row, int column, int rowSize, int columnSize){
        return row == 0 || row == rowSize || column == 0 || column == columnSize;
    }
    public boolean isVisible(int row, int column, int rowSize, int columnSize, int treeheight, List<List<Character>> map){
        int upSteps = row-1;
        int downSteps = rowSize-row-1;
        int leftSteps = column-1;
        int rightSteps = columnSize-column-1;

        for (int i=0; i >= upSteps; i++){


        }

    }


}
