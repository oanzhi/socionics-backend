package com.socio.model;

import lombok.Data;

import javax.persistence.*;

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