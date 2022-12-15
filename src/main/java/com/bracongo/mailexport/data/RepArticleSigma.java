package com.bracongo.mailexport.data;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author vr.kenfack
 */
@Entity
@Table(name = "REP_ARTICLE_SIGMA", schema = "dbo")
public class RepArticleSigma implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    
    @Column(name = "STATU")
    private String statu;
    
    @Column(name = "CODAR")
    private String codar;
    
    @Column(name = "CODAS")
    private String codas;
    
    @Column(name = "DESAR")
    private String desar;
    
    @Column(name = "CTART")
    private String ctart;
    
    @Column(name = "TYPAR")
    private int typar;
    
    @Column(name = "CPVTE")
    private String cpvte;
    
    @Column(name = "TAXAR")
    private String taxar;
    
    @Column(name = "FAMILLE")
    private String famille;
    
    @Column(name = "UB")
    private String ub;
    
    @Column(name = "US")
    private String us;
    
    @Column(name = "COLUES")
    private double colues;
    
    @Column(name = "USTAT")
    private String ustat;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "DATE_CREATION")
    private Date dateCreation;
    
    @Column(name = "USTAQ")
    private double ustaq;

    public RepArticleSigma() {
    }

    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatu() {
        return statu;
    }

    public void setStatu(String statu) {
        this.statu = statu;
    }

    public String getCodar() {
        return codar;
    }

    public void setCodar(String codar) {
        this.codar = codar;
    }

    public String getCodas() {
        return codas;
    }

    public void setCodas(String codas) {
        this.codas = codas;
    }

    public String getDesar() {
        return desar;
    }

    public void setDesar(String desar) {
        this.desar = desar;
    }

    public String getCtart() {
        return ctart;
    }

    public void setCtart(String ctart) {
        this.ctart = ctart;
    }

    public int getTypar() {
        return typar;
    }

    public void setTypar(int typar) {
        this.typar = typar;
    }

    public String getCpvte() {
        return cpvte;
    }

    public void setCpvte(String cpvte) {
        this.cpvte = cpvte;
    }

    public String getTaxar() {
        return taxar;
    }

    public void setTaxar(String taxar) {
        this.taxar = taxar;
    }

    public String getFamille() {
        return famille;
    }

    public void setFamille(String famille) {
        this.famille = famille;
    }

    public String getUb() {
        return ub;
    }

    public void setUb(String ub) {
        this.ub = ub;
    }

    public String getUs() {
        return us;
    }

    public void setUs(String us) {
        this.us = us;
    }

    public double getColues() {
        return colues;
    }

    public void setColues(int colues) {
        this.colues = colues;
    }

    public String getUstat() {
        return ustat;
    }

    public void setUstat(String ustat) {
        this.ustat = ustat;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public double getUstaq() {
        return ustaq;
    }

    public void setUstaq(double ustaq) {
        this.ustaq = ustaq;
    }
    
    private Date buildDate(int jour, int mois, int annee){
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, annee);
        cal.set(Calendar.MONTH, mois-1);
        cal.set(Calendar.DAY_OF_MONTH, jour);
        
        return cal.getTime();
    }
}
