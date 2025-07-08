package com.luminesway.concurso.services;

import com.luminesway.concurso.utils.SpringResult;
import com.luminesway.concurso.utils.ResultParameters;
import com.luminesway.concurso.dtos.QuestionResDto;
import com.luminesway.concurso.entities.Question;
import com.luminesway.concurso.repositories.QuestionRepository;
import com.luminesway.concurso.utils.QuestionUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
@Log4j2

public class QuestionService {
    private final QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public SpringResult<?> createQuestions() {
        try {
            List<QuestionResDto> response = new ArrayList<>();
            List<Question> usedQuestions = new ArrayList<>();
            log.info("Getting questions from database");
            List<Question> questions = questionRepository.findAll();
            log.info("Validating size of questions");
            if (questions.isEmpty() || questions.size() < 4) {
                return SpringResult.error(ResultParameters.builder()
                        .message("No existen los suficientes audios para crear las preguntas.")
                        .build(), 404);
            }

            log.info("Creating questions");
            while (response.size() < questions.size() - 2) {
                List<Question> filteredOptions = questions.stream()
                        .filter(option -> !usedQuestions.contains(option))
                        .toList();

                int randomIndex = ThreadLocalRandom.current().nextInt(0, filteredOptions.size());
                Question answer = questions.get(randomIndex);
                usedQuestions.add(answer);
                List<Question> otherOptions = QuestionUtil.generateQuestions(answer, questions);
                response.add(QuestionResDto.builder()
                        .question(answer.getDescription())
                        .answer(answer)
                        .otherOptions(otherOptions)
                        .build());
            }

            log.info("Questions created");
            log.info("Returning questions");
            return SpringResult.success(
                    ResultParameters.builder()
                            .message("Se han creado las preguntas")
                            .result(response)
                            .build(),
                     200);
        } catch (Exception e) {
            log.error("ERROR:{}", e.getMessage());
            log.error(e);
            return SpringResult.error(ResultParameters.builder()
                            .message("Algo malo ha ocurrido por favor informar a sistemas.")
                            .build(),
                     500);
        }
    }
}
