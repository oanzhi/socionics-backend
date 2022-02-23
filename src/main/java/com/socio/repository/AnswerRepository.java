package com.socio.repository;

import com.socio.model.UserAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<UserAnswer, Integer> {
}