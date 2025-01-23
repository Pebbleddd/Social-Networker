package dev.pebbled.network;

import dev.pebbled.algorithm.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConnectionManager {
    private final Map<String, Node> friendsMap = new HashMap<>();
    private final List<Node> friends = new ArrayList<>();

    public Node getOrAddFriend(String name) {
        Node connection = friendsMap.get(name);

        if (connection == null) {
            connection = new Node(name);
            friendsMap.put(name, connection);
            friends.add(connection);
        }

        return connection;
    }

    public List<Node> getAllFriends() {
        return friends;
    }
}
