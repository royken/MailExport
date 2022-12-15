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
@Table(name = "REP_TRANSDO", schema = "dbo")
public class RepTransdo implements Serializable{
    
    
    @Column(name = "ID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "STE")
    private String ste;
    
    @Column(name = "CODE_CD")
    private String codeCd;
    
    @Column(name = "AGET")
    private String aget;
    
    @Column(name = "LCCDE")
    private String lccde;
    
    @Column(name = "CCCDE")
    private String cccde;
    
    @Column(name = "NTD")
    private int ntd;
    
    @Column(name = "SS")
    private String ss;
    
    @Column(name = "TTS")
    private String tts;
    
    @Column(name = "SENS")
    private String sens;
    
    @Column(name = "TARIFT")
    private String tarift;
    
    @Column(name = "NUMBL")
    private int numbl;
    
    @Column(name = "NUMFAC")
    private String numfac;
    
    @Column(name = "TTC")
    private double ttc;
    
    @Column(name = "ANNEE")
    private int annee;
    
    @Column(name = "MOIS")
    private int mois;
    
    @Column(name = "JOUR")
    private int jour;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "DATE_LONGUE")
    private Date dateLongue;

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

    public String getAget() {
        return aget;
    }

    public void setAget(String aget) {
        this.aget = aget;
    }

    public String getLccde() {
        return lccde;
    }

    public void setLccde(String lccde) {
        this.lccde = lccde;
    }

    public String getCccde() {
        return cccde;
    }

    public void setCccde(String cccde) {
        this.cccde = cccde;
    }

    public int getNtd() {
        return ntd;
    }

    public void setNtd(int ntd) {
        this.ntd = ntd;
    }

    public String getSs() {
        return ss;
    }

    public void setSs(String ss) {
        this.ss = ss;
    }

    public String getTts() {
        return tts;
    }

    public void setTts(String tts) {
        this.tts = tts;
    }

    public String getSens() {
        return sens;
    }

    public void setSens(String sens) {
        this.sens = sens;
    }

    public String getTarift() {
        return tarift;
    }

    public void setTarift(String tarift) {
        this.tarift = tarift;
    }

    public int getNumbl() {
        return numbl;
    }

    public void setNumbl(int numbl) {
        this.numbl = numbl;
    }

    public String getNumfac() {
        return numfac;
    }

    public void setNumfac(String numfac) {
        this.numfac = numfac;
    }

    public double getTtc() {
        return ttc;
    }

    public void setTtc(double ttc) {
        this.ttc = ttc;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
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

    public Date getDateLongue() {
        return dateLongue;
    }

    public void setDateLongue(Date dateLongue) {
        this.dateLongue = dateLongue;
    }

    @Override
    public String toString() {
        return "RepTransdo{" + "id=" + id + ", ste=" + ste + ", codeCd=" + codeCd + ", aget=" + aget + ", lccde=" + lccde + ", cccde=" + cccde + ", ntd=" + ntd + ", ss=" + ss + ", tts=" + tts + ", sens=" + sens + ", tarift=" + tarift + ", numbl=" + numbl + ", numfac=" + numfac + ", ttc=" + ttc + ", annee=" + annee + ", mois=" + mois + ", jour=" + jour + ", dateLongue=" + dateLongue + '}';
    }
    
    
    
}
