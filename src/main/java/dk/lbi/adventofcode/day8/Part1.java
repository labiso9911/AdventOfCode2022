package dk.lbi.adventofcode.day8;

import dk.lbi.adventofcode.utils.GetResourceFile;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Part1 {
    int scienicResult = 0;
    public void doWork() throws IOException {
        GetResourceFile resourceFile = new GetResourceFile();
        List<String> lines = Files.readAllLines(resourceFile.getFile("day8input.txt").toPath());

        // Input all field into 2d array
        List<List<Integer>> map = new ArrayList<List<Integer>>();

        lines.forEach(line -> {
            List<Integer> treeHeights =  Arrays.stream(line.split("")).map(Integer::parseInt).collect(Collectors.toList());
            map.add(treeHeights);
        });

        // Size of given 2d array
        int n = map.size()-1;
        int m = map.get(0).size()-1;

        int result = 0;



        // Run through 2d map and create nodes
        for (int row = 0; row < map.size(); row++) {
            for (int col = 0; col < map.get(row).size(); col++) {
                if (atEdge(row, col, n, m) ||
                        isVisible(row, col, n, m, map.get(row).get(col), map)) {
                    result++;
                }
            }
        }
        System.out.println("Result part 1: " + result);
        System.out.println("Result part 2: " + scienicResult);
    }

    public boolean atEdge(int row, int column, int rowSize, int columnSize) {
        return row == 0 || row == rowSize || column == 0 || column == columnSize;
    }

    public boolean isVisible(int row, int column, int rowSize, int columnSize, int treeheight, List<List<Integer>> map) {
        int upSteps = 0;
        int downSteps = 0;
        int leftSteps = 0;
        int rightSteps = 0;

        boolean upVisible = true;
        boolean downVisible = true;
        boolean leftVisible = true;
        boolean rightVisible = true;

        for (int i = row-1; i >= 0; i--) {
            upSteps++;
            if (map.get(i).get(column) >= treeheight) {
                upVisible = false;

                break;
            }
        }
        for (int i = row+1; i <= rowSize; i++) {
            downSteps++;
            if (map.get(i).get(column) >= treeheight) {
                downVisible = false;

                break;
            }
        }
        for (int i = column-1; i >= 0; i--) {
            leftSteps++;
            if (map.get(row).get(i) >= treeheight) {
                leftVisible = false;

                break;
            }
        }
        for (int i = column+1; i <= columnSize; i++) {
            rightSteps++;
            if (map.get(row).get(i) >= treeheight) {
                rightVisible = false;
                break;
            }
        }
        int result = upSteps * downSteps * leftSteps * rightSteps;
        if (result > scienicResult) scienicResult = result;

        return upVisible || downVisible || leftVisible || rightVisible;
    }
}
