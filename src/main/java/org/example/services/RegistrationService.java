package org.example.services;

import lombok.AllArgsConstructor;
import org.example.base.AllUsersSession;
import org.example.model.User;

@AllArgsConstructor
public class RegistrationService {
    private final AllUsersSession allUsersSession = AllUsersSession.getInstance();

    public User register(String ip, String name) {
        User user = allUsersSession.getUser(ip, name);
        user.resetPoints();
        user.addAttempt();
        return user;
    }
}
