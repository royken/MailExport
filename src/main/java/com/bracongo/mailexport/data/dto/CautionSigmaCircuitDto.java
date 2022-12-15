package com.bracongo.mailexport.data.dto;

import java.io.Serializable;

/**
 *
 * @author vr.kenfack
 */
public class CautionSigmaCircuitDto implements Serializable{
    
    private int annee;
    
    private int mois;
    
    private int jour;
    
    private String codeCircuit;
    
    private String codeCd;
    
    private double caution;

    public CautionSigmaCircuitDto(int annee, int mois, int jour, String codeCircuit, String codeCd, double caution) {
        this.annee = annee;
        this.mois = mois;
        this.jour = jour;
        this.codeCircuit = codeCircuit;
        this.codeCd = codeCd;
        this.caution = caution;
    }
    
    public CautionSigmaCircuitDto(int annee, int mois, String codeCircuit, String codeCd, double caution) {
        this.annee = annee;
        this.mois = mois;
        this.codeCircuit = codeCircuit;
        this.codeCd = codeCd;
        this.caution = caution;
    }

    public CautionSigmaCircuitDto() {
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

    public double getCaution() {
        return caution;
    }

    public void setCaution(double caution) {
        this.caution = caution;
    }

    @Override
    public String toString() {
        return "CautionSigmaCircuitDto{" + "annee=" + annee + ", mois=" + mois + ", jour=" + jour + ", codeCircuit=" + codeCircuit + ", codeCd=" + codeCd + ", caution=" + caution + '}';
    }
   
}
