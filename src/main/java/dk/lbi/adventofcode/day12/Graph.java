package dk.lbi.adventofcode.day12;

import java.util.HashMap;
import java.util.Map;

public class Graph {

    private Map<String, Node> nodes = new HashMap<>();

    public Map<String, Node> getNodes() {
        return nodes;
    }

    public void setNodes(Map<String, Node> nodes) {
        this.nodes = nodes;
    }

    public void addNode(String key,Node nodeA) {
        nodes.put(key, nodeA);
    }

}
