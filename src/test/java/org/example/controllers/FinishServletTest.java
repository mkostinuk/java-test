package org.example.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.base.AllUsersSession;
import org.example.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

class FinishServletTest {
    private User user;
    private HttpSession session;
    private HttpServletRequest req;
    private HttpServletResponse resp;
    private FinishServlet finishServlet;

    @BeforeEach
    void setUp() {

        user = new User("123", "name");
        finishServlet = new FinishServlet();
        session = Mockito.mock(HttpSession.class);
        req = Mockito.mock(HttpServletRequest.class);
        resp = Mockito.mock(HttpServletResponse.class);
        Mockito.when(req.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute("user")).thenReturn(user);
    }

    @Test
    void test_adding_user() {
        AllUsersSession allUsersSession = AllUsersSession.getInstance();
        finishServlet.doGet(req, resp);
        Assertions.assertTrue(allUsersSession.getUsers().contains(user));
    }

    @Test
    void test_setting_attribute_user() {
        finishServlet.doGet(req, resp);
        Mockito.verify(session).setAttribute("user", user);
    }

    @Test
    void test_setting_attribute_list_user() {
        AllUsersSession allUsersSession = Mockito.spy(AllUsersSession.class);
        List<User> users = new ArrayList<>(List.of(user));
        Mockito.when(allUsersSession.getUsers()).thenReturn(users);
        finishServlet.doGet(req, resp);
        Mockito.verify(session).setAttribute("list_users", users);
    }
}