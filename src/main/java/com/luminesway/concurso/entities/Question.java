package com.luminesway.concurso.entities;

import com.luminesway.concurso.enums.Difficulty;
import com.luminesway.concurso.enums.QuestionType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
public class Question {
        @Id
        @GeneratedValue(strategy = GenerationType.UUID)
        private String id = UUID.randomUUID().toString();
        private String directory;
        private String title;
        private Difficulty difficulty;
        private QuestionType type;
        private String description;
}
