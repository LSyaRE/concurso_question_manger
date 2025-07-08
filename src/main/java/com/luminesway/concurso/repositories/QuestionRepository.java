package com.luminesway.concurso.repositories;

import com.luminesway.concurso.entities.Question;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends CrudRepository<Question,String> {
    List<Question> findAll();
}
