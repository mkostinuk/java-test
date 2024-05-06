package org.example.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

class RegistrationServletTest {
    private HttpServletRequest req;
    private HttpServletResponse resp;
    private HttpSession session;
    private RegistrationServlet registrationServlet;

    @BeforeEach
    void setUp() {
        req = Mockito.mock(HttpServletRequest.class);
        resp = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);
        registrationServlet = new RegistrationServlet();
    }

    @Test
    void test_setting_attributes() {
        Mockito.when(req.getSession()).thenReturn(session);
        registrationServlet.doPost(req, resp);
        Mockito.verify(session).setAttribute(ArgumentMatchers.eq("user"), ArgumentMatchers.any());
        Mockito.verify(session).setAttribute("id", 0);
    }

    @SneakyThrows
    @Test
    void test_redirecting() {
        Mockito.when(req.getSession()).thenReturn(session);
        registrationServlet.doPost(req, resp);
        Mockito.verify(resp).sendRedirect("/questions");
    }

}