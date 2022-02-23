package com.socio.model;

import com.socio.model.dto.UserAnswerRequest;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "user_answer")
@NoArgsConstructor
public class UserAnswer {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @ManyToOne
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  private User user;

  @ManyToOne
  @JoinColumn(name = "question_id", referencedColumnName = "id")
  private Question question;

  @Column(name = "text")
  private String text;

  @CreatedDate
  @Column(name = "creation_date")
  private LocalDateTime creationDate;

  public UserAnswer(UserAnswerRequest userAnswerRequest, User user) {
    this.user = user;
    this.question = userAnswerRequest.getQuestion();
    this.text = userAnswerRequest.getText();
  }
}