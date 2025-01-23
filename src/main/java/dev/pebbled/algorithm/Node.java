package dev.pebbled.algorithm;

import java.util.*;

public class Node implements Comparable<Node> {

    private final String name;

    private Integer distance = Integer.MAX_VALUE;

    private List<Node> shortestPath = new LinkedList<>();

    private final Map<Node, Integer> adjacentNodes = new HashMap<>();

    public void addAdjacentNode(Node node, int weight) {
        adjacentNodes.put(node, weight);
    }

    public Map<Node, Integer> getAdjacentNodes() {
        return adjacentNodes;
    }

    @Override
    public int compareTo(Node node) {
        return Integer.compare(this.distance, node.getDistance());
    }

    public Node(String name) {
        this.name = name;
    }


    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public String getName() {
        return name;
    }

    public List<Node> getShortestPath() {
        return shortestPath;
    }

    public void setShortestPath(List<Node> shortestPath) {
        this.shortestPath = shortestPath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Node node = (Node) o;
        return Objects.equals(name, node.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
