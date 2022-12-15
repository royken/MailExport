package com.bracongo.mailexport.data.dto;

import java.io.Serializable;

/**
 *
 * @author vr.kenfack
 */
public class CautionSigmaBlDto implements Serializable{
    
    private int annee;
    
    private int mois;
    
    private int jour;
    
    private String codeCd;
    
    /* circuit à 5 chiffres*/
    private String circuit;
    
    private String ntdCaution;
    
    private String ntdTransaction;
    
    private String numBl;
    
    private double cation;
    
    private double ca;

    public CautionSigmaBlDto() {
        this.ca = 0;
        this.cation = 0;
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

    public String getCodeCd() {
        return codeCd;
    }

    public void setCodeCd(String codeCd) {
        this.codeCd = codeCd;
    }

    public String getCircuit() {
        return circuit;
    }

    public void setCircuit(String circuit) {
        this.circuit = circuit;
    }

    public String getNtdCaution() {
        return ntdCaution;
    }

    public void setNtdCaution(String ntdCaution) {
        this.ntdCaution = ntdCaution;
    }

    public String getNtdTransaction() {
        return ntdTransaction;
    }

    public void setNtdTransaction(String ntdTransaction) {
        this.ntdTransaction = ntdTransaction;
    }

    public String getNumBl() {
        return numBl;
    }

    public void setNumBl(String numBl) {
        this.numBl = numBl;
    }

    public double getCation() {
        return cation;
    }

    public void setCation(double cation) {
        this.cation = cation;
    }

    public double getCa() {
        return ca;
    }

    public void setCa(double ca) {
        this.ca = ca;
    }

    @Override
    public String toString() {
        return "CautionSigmaBlDto{" + "annee=" + annee + ", mois=" + mois + ", jour=" + jour + ", codeCd=" + codeCd + ", circuit=" + circuit + ", ntdCaution=" + ntdCaution + ", ntdTransaction=" + ntdTransaction + ", numBl=" + numBl + ", cation=" + cation + ", ca=" + ca + '}';
    }
    
    
    
}
