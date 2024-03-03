package org.example.controllers;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.SneakyThrows;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.model.Question;
import org.example.services.QuestionService;
import org.example.services.QuestionsReader;
import org.example.model.User;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "QuestionServlet", value = "/questions")
public class QuestionServlet extends HttpServlet {
    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        QuestionService questionService = new QuestionService();
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        questionService.questionHandler(req, resp, session, user);

    }
}
