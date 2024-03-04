package org.example.model;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Question {
    private int id;
    private String question;
    private List<Answer> answers;

    @Getter
    @Setter
    public static class Answer {
        private int id;
        private String text;
        private boolean isCorrect;


    }
}
