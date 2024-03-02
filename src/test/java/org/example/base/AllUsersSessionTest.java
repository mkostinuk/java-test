package org.example.base;


import org.example.model.User;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;

import org.junit.jupiter.params.provider.CsvSource;


class AllUsersSessionTest {
    @Test
    void testNoUnique() {
        AllUsersSession allUsersSession = AllUsersSession.getInstance();
        for (int i = 0; i < 10; i++) {
            allUsersSession.addUser(new User(i + "", "user" + i));
        }
        boolean expected = false;
        boolean actual = allUsersSession.isUnique("5", "user5");
        Assertions.assertEquals(expected, actual);

    }

    @Test
    void testIsUnique() {
        AllUsersSession allUsersSession = AllUsersSession.getInstance();
        for (int i = 0; i < 10; i++) {
            allUsersSession.addUser(new User(i + "", "user" + i));
        }
        boolean expected = true;
        boolean actual = allUsersSession.isUnique("99", "user99");
        Assertions.assertEquals(expected, actual);
    }

    @ParameterizedTest()
    @CsvSource({
            "1, user1",
            "5, user5",
            "9, user9"
    })
    void getUser(String ip, String name) {
        AllUsersSession allUsersSession = AllUsersSession.getInstance();
        User expected = new User(ip, name);
        allUsersSession.addUser(expected);
        User actual = allUsersSession.getUser(ip, name);
        Assertions.assertEquals(expected, actual);
    }
}