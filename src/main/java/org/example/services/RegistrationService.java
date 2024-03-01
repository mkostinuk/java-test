package org.example.services;

import lombok.AllArgsConstructor;
import org.example.base.AllUsersSession;
import org.example.model.User;
@AllArgsConstructor
public class RegistrationService {
    private final AllUsersSession allUsersSession = AllUsersSession.getInstance();


    private User ifUnique(String ip, String name){
        return new User(ip, name);
    }
    private User ifNoUnique(String ip, String name){
        User user = allUsersSession.getUser(ip, name);
        user.resetPoints();
        user.addAttempt();
        return user;
    }
    public User register(String ip, String name){
        return allUsersSession.isUnique(ip, name)?ifUnique(ip, name):ifNoUnique(ip, name);
    }
}
