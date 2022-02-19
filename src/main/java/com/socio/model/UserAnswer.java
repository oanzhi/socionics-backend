package com.socio.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user_answer")
public class UserAnswer {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToOne
    @JoinColumn(name = "question_id", referencedColumnName = "id")
    private Question question;

    @Column(name = "text")
    private String text;

    @CreatedDate
    @Column(name = "creation_date")
    private String creationDate;
}