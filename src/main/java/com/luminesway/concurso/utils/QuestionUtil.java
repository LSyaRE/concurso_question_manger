package com.luminesway.concurso.utils;

import com.luminesway.concurso.dtos.QuestionResDto;
import com.luminesway.concurso.enums.Difficulty;
import org.aspectj.weaver.patterns.TypePatternQuestions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class QuestionUtil {

    public static List<QuestionResDto> generateQuestions(QuestionResDto answer, List<QuestionResDto> options) {
        List<QuestionResDto> usedQuestions = new ArrayList<>(List.of(answer));

        while (usedQuestions.size() < 4) {
            List<QuestionResDto> filteredOptions = options.stream()
                    .filter(option -> !usedQuestions.contains(option))
                    .toList();

            int randomIndex = ThreadLocalRandom.current().nextInt(0, filteredOptions.size());
            usedQuestions.add(options.get(randomIndex));
        }

        Collections.shuffle(usedQuestions);
        return usedQuestions;
    }

}
