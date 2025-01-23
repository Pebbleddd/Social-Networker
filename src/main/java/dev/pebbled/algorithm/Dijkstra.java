package dev.pebbled.algorithm;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Dijkstra {

    private final Node startNode;

    private final Node endNode;

    public Dijkstra(Node startNode, Node endNode) {
        this.startNode = startNode;
        this.endNode = endNode;
    }

    public void calculateShortestPath() {
        this.startNode.setDistance(0);
        Set<Node> settledNodes = new HashSet<>();
        Queue<Node> unsettledNodes = new PriorityQueue<>(Collections.singletonList(this.startNode));

        while (!unsettledNodes.isEmpty()) {
            Node currentNode = unsettledNodes.poll();
            currentNode.getAdjacentNodes()
                    .entrySet()
                    .stream()
                    .filter(nodeIntegerEntry -> !settledNodes.contains(nodeIntegerEntry.getKey()))
                    .forEach(nodeIntegerEntry -> {
                        evaluateDistanceAndPath(nodeIntegerEntry.getKey(), nodeIntegerEntry.getValue(), currentNode);
                        unsettledNodes.add(nodeIntegerEntry.getKey());
                    });
            settledNodes.add(currentNode);
        }
    }

    public String getShortestPath() {
        String path = this.endNode.getShortestPath().stream()
                .map(Node::getName)
                .collect(Collectors.joining(" -> "));
        return "%s -> %s : %s".formatted(path, this.endNode.getName(), this.endNode.getDistance());
    }

    private void evaluateDistanceAndPath(Node adjacentNode, int edgeWeight, Node sourceNode) {
        Integer newDistance = sourceNode.getDistance() + edgeWeight;
        if (newDistance < adjacentNode.getDistance()) {
            adjacentNode.setDistance(newDistance);
            adjacentNode.setShortestPath(
                    Stream.concat(sourceNode.getShortestPath().stream(), Stream.of(sourceNode)).toList()
            );
        }
    }
}

