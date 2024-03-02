package org.example.services;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.example.model.Question;
import org.example.model.User;

import java.io.IOException;
import java.util.List;
@AllArgsConstructor
public class QuestionService {

    private final QuestionsReader questionsReader;
    private final HttpServletRequest req;
    private final HttpServletResponse resp;
    private final HttpSession session;
    private final User user;
    private int id;

    private boolean isSurrender(){
        return req.getParameter("next") != null;
    }
    @SneakyThrows
    private boolean checkAnswer()  {
        if (req.getParameter("answer") != null) {
            if (Boolean.parseBoolean(req.getParameter("answer"))) {
                user.addPoint();
            }
            redirectingToNextQuestion(resp, id, session, user);
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

    private void redirectingToNextQuestion(HttpServletResponse resp, int id, HttpSession session, User user) throws IOException {
        if (id < 4) {
            session.setAttribute("id", ++id);
            resp.sendRedirect("/questions");
        } else {
            resp.sendRedirect("/finish");
        }
    }
    @SneakyThrows
    public void method(){
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if (isSurrender()) {
            resp.sendRedirect("/finish");
        } else {
            int id = (int) session.getAttribute("id");
            if (checkAnswer()){
                return;}
            redirectingToView(resp, id, session);
        }
    }
}
