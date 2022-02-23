package com.socio.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "sociotype")
public class Sociotype {
  @Id
  @Column(name = "id")
  private Integer id;

  @Column(name = "abbreviation")
  private String abbreviation;

  @Column(name = "full_name")
  private String fullName;
}