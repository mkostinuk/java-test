package org.example.services;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.SneakyThrows;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.model.Question;
import org.example.model.User;

import java.util.List;
import java.util.Objects;


public class QuestionService {
    private static final Logger LOGGER = LogManager.getLogger(QuestionService.class);
    private final QuestionsReader questionsReader = new QuestionsReader("/questions.json");


    @SneakyThrows
    private boolean checkAnswer(HttpServletRequest req, User user) {
        if (req.getParameter("answer") != null) {
            if (Boolean.parseBoolean(req.getParameter("answer"))) {
                LOGGER.info("user [{}] has correctly answered on question", user.getId());
                user.addPoint();
            }
            return true;
        }
        return false;
    }

    @SneakyThrows
    private void redirectingToView(HttpServletResponse resp, int id, HttpSession session) {
        Question question = questionsReader.getQuestionAnswerById(id);
        List<Question.Answer> answers = question.getAnswers();
        session.setAttribute("question", question);
        session.setAttribute("answers", answers);
        resp.sendRedirect("/questions.jsp");
    }

    @SneakyThrows
    private void redirectingToNextQuestion(HttpServletResponse resp, int id, HttpSession session, User user) {
        if (id < questionsReader.getCountOfQuestions()-1) {
            session.setAttribute("id", ++id);
            resp.sendRedirect("/questions");
            LOGGER.info("User[{}] redirected to next question", user.getId());
        } else {
            LOGGER.info("User[{}] has answered on all questions", user.getId());
            resp.sendRedirect("/finish");
        }
    }

    @SneakyThrows
    public void questionHandler(HttpServletRequest req, HttpServletResponse resp, HttpSession session, User user) {
        if (Objects.nonNull(req.getParameter("surrender"))) {
            LOGGER.info("User [{}] has surrendered", user.getId());
            resp.sendRedirect("/finish");
        } else {
            int id = (int) session.getAttribute("id");
            if (checkAnswer(req, user)) {
                redirectingToNextQuestion(resp, id, session, user);
                return;
            }
            redirectingToView(resp, id, session);
        }
    }
}
