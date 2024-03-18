package org.example.base;

import org.example.model.User;

import java.util.*;


public class AllUsersSession {
    private static AllUsersSession instance;
    private final Set<User> users = new HashSet<>();

    public static AllUsersSession getInstance() {
        if (instance == null) {
            instance = new AllUsersSession();
        }
        return instance;
    }

    private AllUsersSession() {

    }

    public List<User> getUsers() {
        return users.stream()
                .sorted((o1, o2) -> o2.bestResult() - o1.bestResult()).toList();
    }

    public void addUser(User user) {
        user.addPointToList();
        users.add(user);
    }

    public User getUser(String ip, String name) {
        if (users.stream().anyMatch(s -> s.getIp().equals(ip) && s.getName().equals(name))) {
            return users.stream().filter(s -> s.getIp().equals(ip) && s.getName().equals(name)).
                    findFirst().
                    orElseThrow(NoSuchElementException::new);
        } else {
            return new User(ip, name);
        }

    }
}
