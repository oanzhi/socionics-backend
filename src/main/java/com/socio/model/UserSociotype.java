package com.socio.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "user_sociotype")
public class UserSociotype {

  @Id
  @Column(name = "id")
  private Integer id;

  @Column(name = "probability")
  private double probability;

  @ManyToOne
  @JoinColumns({
      @JoinColumn(name = "sociotype_id", referencedColumnName = "id"),
      @JoinColumn(name = "sociotype_name", referencedColumnName = "full_name")
  })
  private Sociotype sociotype;
}