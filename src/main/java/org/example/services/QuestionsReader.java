package org.example.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.example.model.Question;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class QuestionsReader {

    private final List<Question> questionAnswers;



   public int getCountOfQuestions() {
        return questionAnswers.size();
    }

    @SneakyThrows
    public QuestionsReader(String filePath) {

        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(filePath);

        if (inputStream != null) {
            ObjectMapper objectMapper = new ObjectMapper();
            questionAnswers = List.of(objectMapper.readValue(inputStream, Question[].class));
            inputStream.close();
        } else {
            throw new FileNotFoundException("File not found: " + filePath);
        }

    }

    public Question getQuestionAnswerById(int id) {
        return questionAnswers.stream()
                .filter(qa -> qa.getId() == id)
                .findFirst()
                .orElse(null);
    }
}
