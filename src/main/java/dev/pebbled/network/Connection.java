package dev.pebbled.network;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dev.pebbled.algorithm.Dijkstra;
import dev.pebbled.algorithm.Node;

import java.nio.file.Files;
import java.nio.file.Path;

public class Connection {

    private final Path dataPath;

    private final ConnectionManager connectionManager = new ConnectionManager();

    public Connection(String path) {
        this.dataPath = Path.of(path);
    }

    public void loadNodes() {
        try {
            String jsonString = new String(Files.readAllBytes(dataPath));
            JsonObject jsonObject = new JsonParser().parse(jsonString).getAsJsonObject();

            jsonObject.asMap().forEach((s, jsonElement) -> {

                Node node = connectionManager.getOrAddFriend(s);

                for (JsonElement element : jsonElement.getAsJsonArray()) {
                    Node otherNode = connectionManager.getOrAddFriend(element.getAsString());
                    node.addAdjacentNode(otherNode, 1);
                }
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public String getShortestConnectionPath(String nameOne, String nameTwo) {


        Node nodeOne = connectionManager.getAllFriends().stream().filter(node1 -> node1.getName().equals(nameOne)).findFirst().orElse(null);
        Node nodeTwo = connectionManager.getAllFriends().stream().filter(node2 -> node2.getName().equals(nameTwo)).findFirst().orElse(null);

        if (nodeOne == null || nodeTwo == null) {
            return "Null";
        }

        Dijkstra dijkstraSorting = new Dijkstra(nodeOne, nodeTwo);

        dijkstraSorting.calculateShortestPath();

        return dijkstraSorting.getShortestPath();

    }
}
