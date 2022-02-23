package com.socio.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "dictionary")
public class Dictionary {

  @Id
  private String word;

  @Column(name = "count")
  private long count;

  @OneToOne
  @JoinColumn(name = "sociotype_id", referencedColumnName = "id")
  private Sociotype sociotype;
}