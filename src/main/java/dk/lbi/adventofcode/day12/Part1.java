package dk.lbi.adventofcode.day12;

import dk.lbi.adventofcode.utils.ArrayHelper;
import dk.lbi.adventofcode.utils.GetResourceFile;

import java.io.IOException;
import java.nio.file.Files;
import java.sql.SQLOutput;
import java.util.*;
import java.util.stream.Collectors;

public class Part1 {

    public void doWork() throws IOException {
        System.out.println("Hello");

        GetResourceFile resourceFile = new GetResourceFile();
        List<String> lines = Files.readAllLines(resourceFile.getFile("day12input.txt").toPath());
        ArrayHelper arrHelper = new ArrayHelper();
        List<String> sourceNodeKeys = new ArrayList<>();
        String targetNodeKey = null;

        // Input all field into 2d array
        List<List<Character>> map = new ArrayList<List<Character>>();

        lines.forEach(line -> {
            List<Character> chars = line.chars().mapToObj(e->(char)e).collect(Collectors.toList());
            map.add(chars);
        });

        // Size of given 2d array
        int n = map.size();
        int m = map.get(0).size();

        System.out.println("hej");

        Graph graph = new Graph();
        // Run through 2d map and create nodes
        for (int row = 0; row < map.size(); row++) {
            for (int col = 0; col < map.get(row).size(); col++) {
                Node node = new Node(map.get(row).get(col));
                String key = "r"+row+"c"+col;
                graph.addNode(key,node );
                if (node.getName().equals('E')) {
                    targetNodeKey = key;
                }
                if (node.getName().equals('S') || node.getName().equals('a')) {
                    sourceNodeKeys.add(key);
                }
            }
        }
        // Add destinations for each node
        for (int row = 0; row < map.size(); row++) {
            for (int col = 0; col < map.get(row).size(); col++) {
                Node currentNode = null;
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

       // System.out.println(result.getNodes().get(targetNodeKey).getShortestPath().size());

        List<Integer> part2Results = new ArrayList<>();

        for (String source: sourceNodeKeys) {
            Map<String, Node> cleanNodes = new HashMap<>();
            List<Node> emptyShortestPath = new LinkedList<>();
            graph.getNodes().entrySet().forEach(e -> e.getValue().setShortestPath(emptyShortestPath));

            Node sourceNode = graph.getNodes().get(source);
            new Graph();
            Graph result;
            result = calculateShortestPathFromSource(graph, sourceNode);
            Node targetNode = result.getNodes().get(targetNodeKey);

            part2Results.add(targetNode.getShortestPath().size());
        }

        Collections.sort(part2Results);
        System.out.println(part2Results);

    }

    public Graph calculateShortestPathFromSource(Graph graph, Node source) {
        source.setDistance(0);

        Set<Node> settledNodes = new HashSet<>();
        Set<Node> unsettledNodes = new HashSet<>();

        unsettledNodes.add(source);

        while (unsettledNodes.size() != 0) {
            Node currentNode = getLowestDistanceNode(unsettledNodes);
            unsettledNodes.remove(currentNode);
            for (Map.Entry< Node, Integer> adjacencyPair:
                    currentNode.getAdjacentNodes().entrySet()) {
                Node adjacentNode = adjacencyPair.getKey();
                Integer edgeWeight = adjacencyPair.getValue();
                if (!settledNodes.contains(adjacentNode)) {
                    calculateMinimumDistance(adjacentNode, edgeWeight, currentNode);
                    unsettledNodes.add(adjacentNode);
                }
            }
            settledNodes.add(currentNode);
        }
        return graph;
    }

    private Node getLowestDistanceNode(Set < Node > unsettledNodes) {
        Node lowestDistanceNode = null;
        int lowestDistance = Integer.MAX_VALUE;
        for (Node node: unsettledNodes) {
            int nodeDistance = node.getDistance();
            if (nodeDistance < lowestDistance) {
                lowestDistance = nodeDistance;
                lowestDistanceNode = node;
            }
        }
        return lowestDistanceNode;
    }

    private void calculateMinimumDistance(Node evaluationNode,
                                                 Integer edgeWeight, Node sourceNode) {
        Integer sourceDistance = sourceNode.getDistance();
        if (sourceDistance + edgeWeight < evaluationNode.getDistance()) {
            evaluationNode.setDistance(sourceDistance + edgeWeight);
            LinkedList<Node> shortestPath = new LinkedList<>(sourceNode.getShortestPath());
            shortestPath.add(sourceNode);
            evaluationNode.setShortestPath(shortestPath);
        }
    }


    private boolean isAdjecent(Character currentNode, Character adjecentNode) {
        boolean result = false;

        Character c1 = currentNode;
        Character c2 = adjecentNode;

        //Check for start and end
        if (currentNode.equals('S')) {
            c1 = 'a';
        }
        if (adjecentNode.equals('E')) {
            c2 = 'z';
        }
        if (currentNode.equals('E')) {
            c1 = 'z';
        }
        if (adjecentNode.equals('S')) {
            c2 = 'a';
        }

        if(c2-c1 == 0 ||
                c2-c1 == 1 ||
        c2-c1 < 0 ) result = true;
        return result;
    }



    private int getDistance(Character currentNode, Character adjecentNode) {

        Character c1 = currentNode;
        Character c2 = adjecentNode;

        //Check for start and end
        if (currentNode.equals('S')) {
            c1 = 'a';
        }
        if (adjecentNode.equals('E')) {
            c2 = 'z';
        }
        if (currentNode.equals('E')) {
            c1 = 'z';
        }
        if (adjecentNode.equals('S')) {
            c2 = 'a';
        }

        // If the elevation is the same, set distance to 1
        if (c2-c1 == 0) return 1;
        // If the elevation is one, set distance to 2
        if (c2-c1 == 1) return 2;
        // if the elevation is minus one, set distance to 0
        if (c2-c1 < 0) return 0;

        return 0;
    }

    static boolean isValidPos(int i, int j, int n, int m) {
        if (i < 0 || j < 0 || i > n - 1 || j > m - 1) {
            return false;
        }
        return true;
    }
}
