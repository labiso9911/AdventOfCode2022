package dk.lbi.adventofcode.day12;

import dk.lbi.adventofcode.utils.ArrayHelper;
import dk.lbi.adventofcode.utils.GetResourceFile;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Part1 {

    public void doWork() throws IOException {
        System.out.println("Hello");

        GetResourceFile resourceFile = new GetResourceFile();
        List<String> lines = Files.readAllLines(resourceFile.getFile("day12input.txt").toPath());
        ArrayHelper arrHelper = new ArrayHelper();


        // Input all field into 2d array
        List<List<Character>> map = new ArrayList<List<Character>>();
        //char[][] map = new char[5][8];

        // Size of given 2d array
        int n = 5;
        int m = 8;

        lines.forEach(line -> {
            List<Character> chars = line.chars().mapToObj(e->(char)e).collect(Collectors.toList());
            map.add(chars);
        });

        System.out.println("hej");

/*        for (int i = 0; i < n; i++) {
            for (int y = 0; y < m; y++) {
                map.get(i).set(y, lines.get(i).charAt(y));
            }
        }*/




        Graph graph = new Graph();
        // Run through 2d map and create nodes
        for (int row = 0; row < map.size(); row++) {
            for (int col = 0; col < map.get(row).size(); col++) {
                Node node = new Node(map.get(row).get(col));
                graph.addNode("r"+row+"c"+col,node );
            }
        }
        // Add destinations for each node
        for (int row = 0; row < map.size(); row++) {
            for (int col = 0; col < map.get(row).size(); col++) {
                Node currentNode;
                Node adjecentNode;
                if (isValidPos(row - 1, col, n, m) && isAdjecent(map.get(row).get(col), map.get(row-1).get(col))) {
                    currentNode = graph.getNodes().get("r"+row+"c"+col);
                    int adjRow = row-1;
                    adjecentNode = graph.getNodes().get("r"+adjRow+"c"+col);
                    currentNode.addDestination(adjecentNode, getDistance(currentNode.getName(), adjecentNode.getName()));
                }
                if (isValidPos(row, col - 1, n, m) && isAdjecent(map.get(row).get(col), map.get(row).get(col-1))) {
                    currentNode = graph.getNodes().get("r"+row+"c"+col);
                    int adjCol = col-1;
                    adjecentNode = graph.getNodes().get("r"+row+"c"+adjCol);
                    currentNode.addDestination(adjecentNode, getDistance(currentNode.getName(), adjecentNode.getName()));
                }
                if (isValidPos(row, col + 1, n, m) && isAdjecent(map.get(row).get(col), map.get(row).get(col+1))) {
                    currentNode = graph.getNodes().get("r"+row+"c"+col);
                    int adjCol = col+1;
                    adjecentNode = graph.getNodes().get("r"+row+"c"+adjCol);
                    currentNode.addDestination(adjecentNode, getDistance(currentNode.getName(), adjecentNode.getName()));
                }
                if (isValidPos(row + 1, col, n, m) && isAdjecent(map.get(row).get(col), map.get(row+1).get(col))) {
                    currentNode = graph.getNodes().get("r"+row+"c"+col);
                    int adjRow = row+1;
                    adjecentNode = graph.getNodes().get("r"+adjRow+"c"+col);
                    currentNode.addDestination(adjecentNode, getDistance(currentNode.getName(), adjecentNode.getName()));
                }

            }
        }
        System.out.println("Yeehaw");
    }

    private boolean isAdjecent(char currentNode, char adjecentNode) {
        boolean result = false;

        if(adjecentNode-currentNode == 0 ||
                adjecentNode-currentNode == 1 ||
        adjecentNode-currentNode < 0 ) result = true;
        return result;
    }



    private int getDistance(char currentNode, char adjecentNode) {
        // If the elevation is the same, set distance to 1
        if (adjecentNode-currentNode == 0) return 1;
        // If the elevation is one, set distance to 2
        if (adjecentNode-currentNode == 1) return 2;
        // if the elevation is minus one, set distance to 0
        if (adjecentNode-currentNode < 0) return 0;

        return 0;
    }

    static boolean isValidPos(int i, int j, int n, int m) {
        if (i < 0 || j < 0 || i > n - 1 || j > m - 1) {
            return false;
        }
        return true;
    }
}
