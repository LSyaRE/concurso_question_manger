package com.luminesway.concurso.utils;

import com.luminesway.concurso.entities.Question;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;


public class QuestionUtil {
    public static List<Question> generateQuestions(Question answer, List<Question> options) {
        List<Question> usedQuestions = new ArrayList<>(List.of(answer));

        while (usedQuestions.size() < 4) {
            List<Question> filteredOptions = options.stream()
                    .filter(option -> !usedQuestions.contains(option))
                    .toList();

            int randomIndex = ThreadLocalRandom.current().nextInt(0, filteredOptions.size());
            usedQuestions.add(options.get(randomIndex));
        }

        Collections.shuffle(usedQuestions);
        return usedQuestions;
    }
}
