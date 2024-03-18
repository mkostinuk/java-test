package org.example.model;


import lombok.Getter;

import java.util.*;

@Getter
public class User {
    private final String name;
    private final String id;
    private final String ip;
    private int points;
    private int attempts;
    private final Set<Integer> listPoints;

    public User(String ip, String name) {
        this.name = name;
        this.ip = ip;
        id = UUID.randomUUID().toString();
        points = 0;
        attempts = 0;
        listPoints = new HashSet<>();
    }

    public void resetPoints() {
        points = 0;
    }

    public void addPoint() {
        points++;
    }

    public void addAttempt() {
        attempts++;
    }

    public void addPointToList() {
        listPoints.add(points);
    }

    public int bestResult() {
        if (!listPoints.isEmpty()) {
            return listPoints.stream().max(Comparator.comparingInt(o -> o)).get();
        } else return points;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return Objects.equals(name, user.name) && Objects.equals(ip, user.ip);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, ip);
    }
}
