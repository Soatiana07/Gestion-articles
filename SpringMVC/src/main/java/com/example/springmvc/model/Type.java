package com.example.springmvc.model;

import javax.persistence.*;

@Entity
public class Type {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "nomtype")
    private String nomtype;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomtype() {
        return nomtype;
    }

    public void setNomtype(String nomtype) {
        this.nomtype = nomtype;
    }
}
