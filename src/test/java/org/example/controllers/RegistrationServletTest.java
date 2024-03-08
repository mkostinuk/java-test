package org.example.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.base.AllUsersSession;
import org.example.model.User;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class RegistrationServletTest {
    @Test
    void test_new_user_with_empty_name() {
        String ip = "123", name = "";
        User user = new User(ip, "DEFAULT");
        RegistrationServlet registrationServlet = new RegistrationServlet();
        HttpServletRequest req = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse resp = Mockito.mock(HttpServletResponse.class);
        Mockito.when(req.getRemoteAddr()).thenReturn(ip);
        Mockito.when(req.getParameter("username")).thenReturn(name);
        AllUsersSession allUsersSession = Mockito.mock(AllUsersSession.class);
        Mockito.when(allUsersSession.getUser(ip, "DEFAULT")).thenReturn(user);
        registrationServlet.doPost(req, resp);
        assertTrue(allUsersSession.getUsersFromList().contains(user));
    }
}