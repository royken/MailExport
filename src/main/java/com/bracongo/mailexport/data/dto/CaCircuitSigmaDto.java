package com.bracongo.mailexport.data.dto;

import java.io.Serializable;

/**
 * Chiffre d'affaire Sigma pour un circuit
 * @author vr.kenfack
 */
public class CaCircuitSigmaDto implements Serializable{
    
    private int annee;
    
    private int mois;
    
    private int jour;
    
    private int codeCd;
    
    private String codeCircuit;
    
    private double chiffreAffaire;

    public CaCircuitSigmaDto(int annee, int mois, int jour, int codeCd, String codeCircuit, double chiffreAffaire) {
        this.annee = annee;
        this.mois = mois;
        this.jour = jour;
        this.codeCd = codeCd;
        this.codeCircuit = codeCircuit;
        this.chiffreAffaire = chiffreAffaire;
    }

    public CaCircuitSigmaDto() {
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

    public int getCodeCd() {
        return codeCd;
    }

    public void setCodeCd(int codeCd) {
        this.codeCd = codeCd;
    }

    public String getCodeCircuit() {
        return codeCircuit;
    }

    public void setCodeCircuit(String codeCircuit) {
        this.codeCircuit = codeCircuit;
    }

    public double getChiffreAffaire() {
        return chiffreAffaire;
    }

    public void setChiffreAffaire(double chiffreAffaire) {
        this.chiffreAffaire = chiffreAffaire;
    }

    @Override
    public String toString() {
        return "CaCircuitSigmaDto{" + "annee=" + annee + ", mois=" + mois + ", jour=" + jour + ", codeCd=" + codeCd + ", codeCircuit=" + codeCircuit + ", chiffreAffaire=" + chiffreAffaire + '}';
    }
    
        
}