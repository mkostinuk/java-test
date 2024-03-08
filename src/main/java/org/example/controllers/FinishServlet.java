package org.example.controllers;


import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.SneakyThrows;
import org.example.base.AllUsersSession;
import org.example.model.User;


@WebServlet(name = "FinishServlet", value = "/finish")
public class FinishServlet extends HttpServlet {
    private final AllUsersSession allUsers = AllUsersSession.getInstance();

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        allUsers.addUser(user);
        session.setAttribute("user", user);
        session.setAttribute("list_users", allUsers.getUsersFromList());
        resp.sendRedirect("finish_table.jsp");
    }

}
