package com.socio.model.dto;

import com.socio.model.Question;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserAnswerRequest {

  private String text;
  private Question question;
}