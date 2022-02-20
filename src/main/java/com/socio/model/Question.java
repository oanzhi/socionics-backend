package com.socio.model;

import com.socio.model.dto.QuestionRequest;
import com.socio.model.dto.QuestionResponse;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "question")
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Question {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "text")
    private String text;

    @LastModifiedDate
    @Column(name = "modified_date")
    private LocalDateTime modifiedDate;

    @CreatedDate
    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    public Question(QuestionRequest questionRequest) {
        this.text = questionRequest.getText();
    }

    public QuestionResponse toResponseDto() {
        return new QuestionResponse(this);
    }
}