package com.luminesway.concurso.dtos;

import com.luminesway.concurso.entities.Question;
import lombok.Builder;
import lombok.Getter;

import java.util.List;


@Builder
@Getter
public class QuestionResDto {
    private String question;
    private Question answer;
    private List<Question> otherOptions;
}
