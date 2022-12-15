package com.bracongo.mailexport.data;

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
@Table(name = "REP_CAUTION_SIGMA", schema = "dbo")
public class RepCautionSigma extends BaseClass{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    
    @Column(name = "MOIS")
    private int mois;

    @Column(name = "JOUR")
    private int jour;

    @Column(name = "ANNEE")
    private int annee;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "DATE_LONGUE")
    private Date dateLongue;
    
    @Column(name = "CODE_CD")
    private String codeCD;
    
    @Column(name = "CODE_CIRCUIT")
    private String codeCircuit;
    
    @Column(name = "SENS")
    private String sens;
    
    @Column(name = "NTD")
    private int ntd;
    
    @Column(name = "TLIMCP")
    private double tlimcp;
    
    @Column(name = "TLIMLO")
    private double tlimlo;
    
    @Column(name = "MTNP")
    private double mtnp;
    
    @Column(name = "MTN")
    private double mtn;
    
    @Column(name = "NBDDEV")
    private int nbddev;
    
    @Column(name = "PUNP")
    private int punp;
    
    @Column(name = "PUN")
    private double pun;
    
    @Column(name = "PUD")
    private double pud;

    public RepCautionSigma(Long id, int mois, int jour, int annee, Date dateLongue, String codeCD, String codeCircuit, String sens, int ntd, double tlimcp, double tlimlo, double mtnp, double mtn, int nbddev, int punp, double pun, double pud) {
        this.id = id;
        this.mois = mois;
        this.jour = jour;
        this.annee = annee;
        this.dateLongue = dateLongue;
        this.codeCD = codeCD;
        this.codeCircuit = codeCircuit;
        this.sens = sens;
        this.ntd = ntd;
        this.tlimcp = tlimcp;
        this.tlimlo = tlimlo;
        this.mtnp = mtnp;
        this.mtn = mtn;
        this.nbddev = nbddev;
        this.punp = punp;
        this.pun = pun;
        this.pud = pud;
    }

    public RepCautionSigma() {
    }
      

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getMois() {
        return mois;
    }

    public void setMois(int mois) {
        this.mois = mois;
    }

    public int getJour() {
        return jour;
    }

    public void setJour(int jour) {
        this.jour = jour;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public Date getDateLongue() {
        return dateLongue;
    }

    public void setDateLongue(Date dateLongue) {
        this.dateLongue = dateLongue;
    }

    public String getCodeCD() {
        return codeCD;
    }

    public void setCodeCD(String codeCD) {
        this.codeCD = codeCD;
    }

    public String getCodeCircuit() {
        return codeCircuit;
    }

    public void setCodeCircuit(String codeCircuit) {
        this.codeCircuit = codeCircuit;
    }

    public String getSens() {
        return sens;
    }

    public void setSens(String sens) {
        this.sens = sens;
    }

    public int getNtd() {
        return ntd;
    }

    public void setNtd(int ntd) {
        this.ntd = ntd;
    }

    public double getTlimcp() {
        return tlimcp;
    }

    public void setTlimcp(double tlimcp) {
        this.tlimcp = tlimcp;
    }

    public double getTlimlo() {
        return tlimlo;
    }

    public void setTlimlo(double tlimlo) {
        this.tlimlo = tlimlo;
    }

    public double getMtnp() {
        return mtnp;
    }

    public void setMtnp(double mtnp) {
        this.mtnp = mtnp;
    }

    public double getMtn() {
        return mtn;
    }

    public void setMtn(double mtn) {
        this.mtn = mtn;
    }

    public int getNbddev() {
        return nbddev;
    }

    public void setNbddev(int nbddev) {
        this.nbddev = nbddev;
    }

    public int getPunp() {
        return punp;
    }

    public void setPunp(int punp) {
        this.punp = punp;
    }

    public double getPun() {
        return pun;
    }

    public void setPun(double pun) {
        this.pun = pun;
    }

    public double getPud() {
        return pud;
    }

    public void setPud(double pud) {
        this.pud = pud;
    }

    @Override
    public String toString() {
        return "RepCautionSigma{" + "id=" + id + ", mois=" + mois + ", jour=" + jour + ", annee=" + annee + ", dateLongue=" + dateLongue + ", codeCD=" + codeCD + ", codeCircuit=" + codeCircuit + ", sens=" + sens + ", ntd=" + ntd + ", tlimcp=" + tlimcp + ", tlimlo=" + tlimlo + ", mtnp=" + mtnp + ", mtn=" + mtn + ", nbddev=" + nbddev + ", punp=" + punp + ", pun=" + pun + ", pud=" + pud + '}';
    }
    
    
    
}
