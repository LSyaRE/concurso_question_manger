package com.luminesway.concurso.dtos;

import com.luminesway.concurso.enums.Difficulty;
import com.luminesway.concurso.enums.QuestionType;
import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class QuestionResDto {
    private Long id;
    private String title;
    private String directory;
    private Difficulty difficulty;
    private QuestionType type;
    private String description;
}
