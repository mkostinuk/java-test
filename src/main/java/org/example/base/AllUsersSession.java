package org.example.base;

import org.example.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class AllUsersSession {
    private static AllUsersSession instance;
    private final List<User> users = new ArrayList<>();

    public static AllUsersSession getInstance() {
        if (instance == null) {
            instance = new AllUsersSession();
        }
        return instance;
    }

    private AllUsersSession() {

    }

    public List<User> getUsers() {
        List<User> sortedUsers = new ArrayList<>(users);
        sortedUsers.sort(((o1, o2) -> o2.bestResult()-o1.bestResult()));
        return sortedUsers;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public User getUser(String ip, String name) {
        return users.stream().filter(s -> s.getIp().equals(ip) && s.getName().equals(name)).findFirst().get();
    }

    public boolean isUnique(String ip, String name) {
        if (users.isEmpty()) {
            return true;
        }
        return users.stream().noneMatch(s -> Objects.equals(s.getName(), name) && Objects.equals(s.getIp(), ip));
    }
}
