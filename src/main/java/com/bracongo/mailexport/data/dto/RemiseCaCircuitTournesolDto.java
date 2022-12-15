package com.bracongo.mailexport.data.dto;

import java.io.Serializable;

/**
 *
 * @author vr.kenfack
 */
public class RemiseCaCircuitTournesolDto implements Serializable{
    
    private String codeCd;
    
    private String nomCd;
    
    private String codeCircuitSigma;
    
    private String codeCircuit;
    
    private double ca;
    
    private double remise;
    
    private int annee;
    
    private int mois;
    
    private int jour;

    public RemiseCaCircuitTournesolDto() {
        remise = 0;
        ca = 0;
    }

    public RemiseCaCircuitTournesolDto(String codeCd, String nomCd, String codeCircuitSigma, String codeCircuit, double ca, double remise, int annee, int mois, int jour) {
        this.codeCd = codeCd;
        this.nomCd = nomCd;
        this.codeCircuitSigma = codeCircuitSigma;
        this.codeCircuit = codeCircuit;
        this.ca = ca;
        this.remise = remise;
        this.annee = annee;
        this.mois = mois;
        this.jour = jour;
    }
    
    
    public RemiseCaCircuitTournesolDto(String codeCd, String nomCd, String codeCircuitSigma, String codeCircuit, double ca, double remise, int annee, int mois) {
        this.codeCd = codeCd;
        this.nomCd = nomCd;
        this.codeCircuitSigma = codeCircuitSigma;
        this.codeCircuit = codeCircuit;
        this.ca = ca;
        this.remise = remise;
        this.annee = annee;
        this.mois = mois;
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

    public String getCodeCircuitSigma() {
        return codeCircuitSigma;
    }

    public void setCodeCircuitSigma(String codeCircuitSigma) {
        this.codeCircuitSigma = codeCircuitSigma;
    }

    public String getCodeCircuit() {
        return codeCircuit;
    }

    public void setCodeCircuit(String codeCircuit) {
        this.codeCircuit = codeCircuit;
    }

    public double getCa() {
        return ca;
    }

    public void setCa(double ca) {
        this.ca = ca;
    }

    public double getRemise() {
        return remise;
    }

    public void setRemise(double remise) {
        this.remise = remise;
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

    @Override
    public String toString() {
        return "RemiseCaCircuitTournesolDto{" + "codeCd=" + codeCd + ", nomCd=" + nomCd + ", codeCircuitSigma=" + codeCircuitSigma + ", codeCircuit=" + codeCircuit + ", ca=" + ca + ", remise=" + remise + ", annee=" + annee + ", mois=" + mois + ", jour=" + jour + '}';
    }
    
    
    
}
