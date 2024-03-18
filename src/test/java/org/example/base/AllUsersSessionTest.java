package org.example.base;

import org.example.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AllUsersSessionTest {
    AllUsersSession allUsersSession;

    @BeforeEach
    void setUp() {
        allUsersSession = AllUsersSession.getInstance();
    }

    @Test
    void test_get_users_sorting() {
        User user1 = Mockito.mock(User.class);
        Mockito.when(user1.bestResult()).thenReturn(3);
        User user2 = Mockito.mock(User.class);
        Mockito.when(user2.bestResult()).thenReturn(1);
        User user3 = Mockito.mock(User.class);
        Mockito.when(user3.bestResult()).thenReturn(5);
        for (User user : Arrays.asList(user1, user2, user3)) {
            allUsersSession.addUser(user);
        }
        List<User> expected = new ArrayList<>(List.of(user3, user1, user2));
        List<User> actual = allUsersSession.getUsers();
        assertEquals(expected, actual);
    }

    @Test
    void test_get_user_if_user_in_list() {
        String ip = "111", name = "test_name";
        User expected = new User(ip, name);
        allUsersSession.addUser(expected);
        User actual = allUsersSession.getUser(ip, name);
        assertTrue(allUsersSession.getUsers().contains(expected));
        assertEquals(expected, actual);
    }

    @Test
    void test_get_user_if_user_not_in_list() {
        String ip = "123", name = "name";
        User expected = new User(ip, name);
        User actual = allUsersSession.getUser(ip, name);
        assertFalse(allUsersSession.getUsers().contains(expected));
        assertEquals(expected, actual);
    }

}