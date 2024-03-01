package org.example.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.Question;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class QuestionsReader {


    private List<Question> questionAnswers;

    public QuestionsReader(String filePath) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            questionAnswers = List.of(objectMapper.readValue(new File(filePath), Question[].class));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Question getQuestionAnswerById(int id) {
        return questionAnswers.stream()
                .filter(qa -> qa.getId() == id)
                .findFirst()
                .orElse(null);
    }
}
