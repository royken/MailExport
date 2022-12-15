/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bracongo.mailexport.data;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *Equivalaent de la table RUBARTP de sigma
 * @author vr.kenfack
 */
@Entity
@Table(name = "REP_ARTICLE_RUBRIQUE", schema = "dbo")
public class RepArticleRubrique implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    
    @Column(name = "STE")
    private String ste;
    
    @Column(name = "ETB")
    private String etb;
    
    @Column(name = "CODARS")
    private String codars;
    
    @Column(name = "RUBRIQUE_ARTICLE")
    private String rubriqueArticle;
    
    @Column(name = "ART_RA")
    private String artRa;
    
    @Column(name = "ART_GRA")
    private String arGra;
    
    @Column(name = "CHRORA")
    private int chrora;
    
    @Column(name = "CUMRA")
    private String cumra;
    
    @Column(name = "ANNEE_CREATION")
    private int anneeCreation;
    
    @Column(name = "JOUR_CREATION")
    private int jourCreation;
    
    @Column(name = "MOIS_CREATION")
    private int moisCreation;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "DATE_CREATION")
    private Date date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSte() {
        return ste;
    }

    public void setSte(String ste) {
        this.ste = ste;
    }

    public String getEtb() {
        return etb;
    }

    public void setEtb(String etb) {
        this.etb = etb;
    }

    public String getCodars() {
        return codars;
    }

    public void setCodars(String codars) {
        this.codars = codars;
    }

    public String getRubriqueArticle() {
        return rubriqueArticle;
    }

    public void setRubriqueArticle(String rubriqueArticle) {
        this.rubriqueArticle = rubriqueArticle;
    }

    public String getArtRa() {
        return artRa;
    }

    public void setArtRa(String artRa) {
        this.artRa = artRa;
    }

    public String getArGra() {
        return arGra;
    }

    public void setArGra(String arGra) {
        this.arGra = arGra;
    }

    public int getChrora() {
        return chrora;
    }

    public void setChrora(int chrora) {
        this.chrora = chrora;
    }

    public String getCumra() {
        return cumra;
    }

    public void setCumra(String cumra) {
        this.cumra = cumra;
    }

    public int getAnneeCreation() {
        return anneeCreation;
    }

    public void setAnneeCreation(int anneeCreation) {
        this.anneeCreation = anneeCreation;
    }

    public int getJourCreation() {
        return jourCreation;
    }

    public void setJourCreation(int jourCreation) {
        this.jourCreation = jourCreation;
    }

    public int getMoisCreation() {
        return moisCreation;
    }

    public void setMoisCreation(int moisCreation) {
        this.moisCreation = moisCreation;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    
    
}
