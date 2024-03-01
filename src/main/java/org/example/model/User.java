package org.example.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Getter
public class User {
    private final String name;
    private final String id;
    private final String ip;
    @Setter
    private int points;
    private int attempts;
    private final List<Integer> listPoints;

    public User(String ip, String name) {
        this.name = name;
        this.ip = ip;
        id = UUID.randomUUID().toString();
        points = 0;
        attempts = 1;
        listPoints = new ArrayList<>();
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
}
