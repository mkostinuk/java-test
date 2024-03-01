package org.example.base;

import lombok.Getter;
import org.example.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
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
