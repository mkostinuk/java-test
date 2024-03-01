package org.example.controllers;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.SneakyThrows;
import org.example.model.Question;
import org.example.services.QuestionsReader;
import org.example.model.User;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "QuestionServlet", value = "/questions")
public class QuestionServlet extends HttpServlet {
    private final QuestionsReader questionsReader = new QuestionsReader("C:\\Users\\mkost\\Desktop\\module3.max.kostyniuk\\src\\main\\resources\\questions.json");

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if (surrender(req)) {
            resp.sendRedirect("/finish");
        } else {
            int id = (int) session.getAttribute("id");
            if (checkAnswer(req, resp, user, id, session)){
                return;}
            redirectingToView(resp, id, session);
        }
    }

    private boolean checkAnswer(HttpServletRequest req, HttpServletResponse resp, User user, int id, HttpSession session) throws IOException {
        if (req.getParameter("answer") != null) {
            if (Boolean.parseBoolean(req.getParameter("answer"))) {
                user.addPoint();
            }
            redirectingToNextQuestion(resp, id, session, user);
            return true;
        }
        return false;
    }


    private void redirectingToView(HttpServletResponse resp, int id, HttpSession session) throws IOException {
        Question question = questionsReader.getQuestionAnswerById(id);
        List<Question.Answer> answers = question.getAnswers();
        session.setAttribute("question", question);
        session.setAttribute("answers", answers);
        resp.sendRedirect("/questions.jsp");
    }

    private void redirectingToNextQuestion(HttpServletResponse resp, int id, HttpSession session, User user) throws IOException {
        if (id < 4) {
            session.setAttribute("id", ++id);
            resp.sendRedirect("/questions");
        } else {
            resp.sendRedirect("/finish");
        }
    }

    private boolean surrender(HttpServletRequest req) {
        return req.getParameter("next") != null;
    }

}
