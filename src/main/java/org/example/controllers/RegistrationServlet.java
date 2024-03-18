package org.example.controllers;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.services.RegistrationService;

import org.example.model.User;


@WebServlet(name = "RegistrationServlet", value = "/registration")
public class RegistrationServlet extends HttpServlet {
    private final RegistrationService registrationService = new RegistrationService();
    private final static Logger LOGGER = LogManager.getLogger(RegistrationServlet.class);


    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        User user = createUser(req);
        LOGGER.info("User [{}] successfully registered with name : {} , attempts : {}", user.getId(), user.getName(), user.getAttempts());
        session.setAttribute("user", user);
        session.setAttribute("id", 0);
        resp.sendRedirect("/questions");
    }

    private User createUser(HttpServletRequest req) {
        String ip = req.getRemoteAddr();
        String name = StringUtils.isEmpty(req.getParameter("username")) ? "DEFAULT" : req.getParameter("username");
        return registrationService.register(ip, name);
    }


}
