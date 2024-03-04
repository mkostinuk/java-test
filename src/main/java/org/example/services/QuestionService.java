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


public class QuestionService {
    private final static Logger logger = LogManager.getLogger(QuestionService.class);
    private final QuestionsReader questionsReader = new QuestionsReader("C:\\Users\\mkost\\Desktop\\module3.max.kostyniuk\\src\\main\\resources\\questions.json");


    private boolean isSurrender(Object next)
    {
        return next != null;
    }

    @SneakyThrows
    private boolean checkAnswer(HttpServletRequest req, User user) {
        if (req.getParameter("answer") != null) {
            if (Boolean.parseBoolean(req.getParameter("answer"))) {
                logger.info("user [{}] has correctly answered on question", user.getId());
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
        if (id < 4) {
            session.setAttribute("id", ++id);
            resp.sendRedirect("/questions");
            logger.info("User[{}] redirected to next question", user.getId());
        } else {
            logger.info("User[{}] has answered on all questions", user.getId());
            resp.sendRedirect("/finish");
        }
    }

    @SneakyThrows
    public void questionHandler(HttpServletRequest req, HttpServletResponse resp, HttpSession session, User user) {
        if (isSurrender(req.getParameter("next"))) {
            logger.info("User [{}] has surrendered", user.getId());
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
