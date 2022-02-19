package com.socio.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "answer")
public class Answer {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "text")
    private String text;

    @ManyToOne
    @JoinColumn(name = "question_id", referencedColumnName = "id")
    private Question question;

    @OneToOne
    @JoinColumn(name = "sociotype_id", referencedColumnName = "id")
    private Sociotype sociotype;

    @CreatedDate
    @Column(name = "creation_date")
    private LocalDateTime creationDate;
}