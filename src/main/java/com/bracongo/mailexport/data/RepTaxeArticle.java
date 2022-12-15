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
 *
 * @author vr.kenfack
 */
@Entity
@Table(name = "REP_TAXES_ARTICLES", schema = "dbo")
public class RepTaxeArticle implements Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "JOUR")
    private int jour;

    @Column(name = "MOIS")
    private int mois;

    @Column(name = "ANNEE")
    private int annee;

    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "DATE")
    private Date date;

    @Column(name = "CODE_TAXE")
    private String codeTaxe;

    @Column(name = "T1_BASE")
    private String t1Base;

    @Column(name = "T1_COPR")
    private double t1Copr;

    @Column(name = "T1_CODE")
    private String t1Code;

    @Column(name = "T2_BASE")
    private String t2Base;

    @Column(name = "T2_COPR")
    private double t2Copr;

    @Column(name = "T2_CODE")
    private String t2Code;

    @Column(name = "T3_BASE")
    private String t3Base;

    @Column(name = "T3_COPR")
    private double t3Copr;

    @Column(name = "T3_CODE")
    private String t3Code;

    @Column(name = "T4_BASE")
    private String t4Base;

    @Column(name = "T4_COPR")
    private double t4Copr;

    @Column(name = "T4_CODE")
    private String t4Code;

    public RepTaxeArticle() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getJour() {
        return jour;
    }

    public void setJour(int jour) {
        this.jour = jour;
    }

    public int getMois() {
        return mois;
    }

    public void setMois(int mois) {
        this.mois = mois;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCodeTaxe() {
        return codeTaxe;
    }

    public void setCodeTaxe(String codeTaxe) {
        this.codeTaxe = codeTaxe;
    }

    public String getT1Base() {
        return t1Base;
    }

    public void setT1Base(String t1Base) {
        this.t1Base = t1Base;
    }

    public double getT1Copr() {
        return t1Copr;
    }

    public void setT1Copr(double t1Copr) {
        this.t1Copr = t1Copr;
    }

    public String getT1Code() {
        return t1Code;
    }

    public void setT1Code(String t1Code) {
        this.t1Code = t1Code;
    }

    public String getT2Base() {
        return t2Base;
    }

    public void setT2Base(String t2Base) {
        this.t2Base = t2Base;
    }

    public double getT2Copr() {
        return t2Copr;
    }

    public void setT2Copr(double t2Copr) {
        this.t2Copr = t2Copr;
    }

    public String getT2Code() {
        return t2Code;
    }

    public void setT2Code(String t2Code) {
        this.t2Code = t2Code;
    }

    public String getT3Base() {
        return t3Base;
    }

    public void setT3Base(String t3Base) {
        this.t3Base = t3Base;
    }

    public double getT3Copr() {
        return t3Copr;
    }

    public void setT3Copr(double t3Copr) {
        this.t3Copr = t3Copr;
    }

    public String getT3Code() {
        return t3Code;
    }

    public void setT3Code(String t3Code) {
        this.t3Code = t3Code;
    }

    public String getT4Base() {
        return t4Base;
    }

    public void setT4Base(String t4Base) {
        this.t4Base = t4Base;
    }

    public double getT4Copr() {
        return t4Copr;
    }

    public void setT4Copr(double t4Copr) {
        this.t4Copr = t4Copr;
    }

    public String getT4Code() {
        return t4Code;
    }

    public void setT4Code(String t4Code) {
        this.t4Code = t4Code;
    }

}
