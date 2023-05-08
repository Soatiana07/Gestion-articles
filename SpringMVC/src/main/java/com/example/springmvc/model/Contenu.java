package com.example.springmvc.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class Contenu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "titre")
    private String titre;

    @Column(name = "idtype")
    private Integer type;

    @Column(name = "visuel")
    private String visuel;

    @Column(name="description")
    private String description;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "date1")
    private Date date1;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "date2")
    private Date date2;

    @Column(name = "lieu")
    private String lieu;

    @Column(name = "status")
    private Integer status;

    @ManyToOne
    @JoinColumn(name = "idauteur")
    private Auteur auteur;
    @Column(name = "priorite")
    private Integer priorite;
//    @Temporal(TemporalType.DATE)
//    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "datecreation", insertable = false, updatable = false)
    private LocalDateTime datecreation;
//    @Temporal(TemporalType.DATE)
//    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
//    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @Column(name = "datepublication")
    private LocalDateTime datepublication;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getVisuel() {
        return visuel;
    }

    public void setVisuel(String visuel) {
        this.visuel = visuel;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate1() {
        return date1;
    }

    public void setDate1(Date date1) {
        this.date1 = date1;
    }

    public Date getDate2() {
        return date2;
    }

    public Auteur getAuteur() {
        return auteur;
    }

    public void setAuteur(Auteur auteur) {
        this.auteur = auteur;
    }

    public Integer getPriorite() {
        return priorite;
    }

    public void setPriorite(Integer priorite) {
        this.priorite = priorite;
    }

    public void setDate2(Date date2) {
        this.date2 = date2;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public LocalDateTime getDatecreation() {
        return datecreation;
    }

    public void setDatecreation(LocalDateTime datecreation) {
        this.datecreation = datecreation;
    }

    public LocalDateTime getDatepublication() {
        return datepublication;
    }

    public void setDatepublication(LocalDateTime datepublication) {
        this.datepublication = datepublication;
    }
}
