package com.socio.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class UserStatistics {

  @Id
  private String id;
}