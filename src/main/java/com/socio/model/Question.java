package com.socio.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "question")
public class Question {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "text")
    private String text;
}