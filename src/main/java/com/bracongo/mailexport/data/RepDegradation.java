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
@Table(name = "REP_DEGRADATION", schema = "dbo")
public class RepDegradation implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    
    @Column(name = "STE")
    private String ste;
    
    @Column(name = "CODE_CD")
    private String codeCd;
    
    @Column(name = "RUBRIQUE")
    private String rubrique;
    
    @Column(name = "ARGT_DG")
    private String argtDg;
    
    @Column(name = "ARGA_DG")
    private String argaDg;
    
    @Column(name = "VALEUR_DEGRADATION")
    private double valeurDegradation;
    
    @Column(name = "PSEU_DG")
    private String pseudg;
    
    @Column(name = "NFIC_DG")
    private int nficdg;
    
    @Column(name = "ANNEE_DEBUT")
    private int anneeDebut;
    
    @Column(name = "MOIS_DEBUT")
    private int moisDebut;
    
    @Column(name = "JOUR_DEBUT")
    private int jourDebut;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "DATE_DEBUT")
    private Date dateDebut;
    
    @Column(name = "ANNEE_FIN")
    private int anneeFin;
    
    @Column(name = "MOIS_FIN")
    private int moisFin;
    
    @Column(name = "JOUR_FIN")
    private int jourFin;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "DATE_FIN")
    private Date dateFin;

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

    public String getCodeCd() {
        return codeCd;
    }

    public void setCodeCd(String codeCd) {
        this.codeCd = codeCd;
    }

    public String getRubrique() {
        return rubrique;
    }

    public void setRubrique(String rubrique) {
        this.rubrique = rubrique;
    }

    public String getArgtDg() {
        return argtDg;
    }

    public void setArgtDg(String argtDg) {
        this.argtDg = argtDg;
    }

    public String getArgaDg() {
        return argaDg;
    }

    public void setArgaDg(String argaDg) {
        this.argaDg = argaDg;
    }

    public double getValeurDegradation() {
        return valeurDegradation;
    }

    public void setValeurDegradation(double valeurDegradation) {
        this.valeurDegradation = valeurDegradation;
    }

    public String getPseudg() {
        return pseudg;
    }

    public void setPseudg(String pseudg) {
        this.pseudg = pseudg;
    }

    public int getAnneeDebut() {
        return anneeDebut;
    }

    public void setAnneeDebut(int anneeDebut) {
        this.anneeDebut = anneeDebut;
    }

    public int getMoisDebut() {
        return moisDebut;
    }

    public void setMoisDebut(int moisDebut) {
        this.moisDebut = moisDebut;
    }

    public int getJourDebut() {
        return jourDebut;
    }

    public void setJourDebut(int jourDebut) {
        this.jourDebut = jourDebut;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public int getAnneeFin() {
        return anneeFin;
    }

    public void setAnneeFin(int anneeFin) {
        this.anneeFin = anneeFin;
    }

    public int getMoisFin() {
        return moisFin;
    }

    public void setMoisFin(int moisFin) {
        this.moisFin = moisFin;
    }

    public int getJourFin() {
        return jourFin;
    }

    public void setJourFin(int jourFin) {
        this.jourFin = jourFin;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public int getNficdg() {
        return nficdg;
    }

    public void setNficdg(int nficdg) {
        this.nficdg = nficdg;
    }
    
    
    
    
    
}
