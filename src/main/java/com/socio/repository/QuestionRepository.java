package com.socio.repository;

import com.socio.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
  List<Question> getAllByIdContaining(Set<Integer> ids);
}