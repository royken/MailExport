package com.bracongo.mailexport.data.dto;

import java.io.Serializable;

/**
 *
 * @author vr.kenfack
 */
public class CautionSigmaMoisDto implements Serializable{
    
    private int annee;
    
    private int mois;
    
    private String codeCircuit;
    
    private String codeCd;
    
    private String nomCd;
    
    private double caution;

    public CautionSigmaMoisDto(int annee, int mois, String codeCircuit, String codeCd, String nomCd, double caution) {
        this.annee = annee;
        this.mois = mois;
        this.codeCircuit = codeCircuit;
        this.codeCd = codeCd;
        this.nomCd = nomCd;
        this.caution = caution;
    }

    public CautionSigmaMoisDto() {
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

    public String getCodeCircuit() {
        return codeCircuit;
    }

    public void setCodeCircuit(String codeCircuit) {
        this.codeCircuit = codeCircuit;
    }

    public String getCodeCd() {
        return codeCd;
    }

    public void setCodeCd(String codeCd) {
        this.codeCd = codeCd;
    }

    public String getNomCd() {
        return nomCd;
    }

    public void setNomCd(String nomCd) {
        this.nomCd = nomCd;
    }

    public double getCaution() {
        return caution;
    }

    public void setCaution(double caution) {
        this.caution = caution;
    }
       
    
}
