package com.bracongo.mailexport.data.dto;

import java.io.Serializable;

/**
 *
 * @author vr.kenfack
 */
public class VenteEtGratuitCdMoisAnneeDto implements Serializable{
    
    private String codeInFoBiArticle;
    
    private double hecto;
    
    private int qte;
    
    private int mois;
    
    private int annee;
    
    private String codeCd;
    
    private String nomCd;

    public VenteEtGratuitCdMoisAnneeDto() {
    }

    public VenteEtGratuitCdMoisAnneeDto(String codeInFoBiArticle, double hecto, int qte, int mois, int annee, String codeCd, String nomCd) {
        this.codeInFoBiArticle = codeInFoBiArticle;
        this.hecto = hecto;
        this.qte = qte;
        this.mois = mois;
        this.annee = annee;
        this.codeCd = codeCd;
        this.nomCd = nomCd;
    }
    
    

    public VenteEtGratuitCdMoisAnneeDto(String codeInFoBiArticle, double hecto, int mois, int annee, String vodeCd, String nomCd) {
        this.codeInFoBiArticle = codeInFoBiArticle;
        this.hecto = hecto;
        this.mois = mois;
        this.annee = annee;
        this.codeCd = vodeCd;
        this.nomCd = nomCd;
    }
    
    

    public String getCodeInFoBiArticle() {
        return codeInFoBiArticle;
    }

    public void setCodeInFoBiArticle(String codeInFoBiArticle) {
        this.codeInFoBiArticle = codeInFoBiArticle;
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

    public double getHecto() {
        return hecto;
    }

    public void setHecto(double hecto) {
        this.hecto = hecto;
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    @Override
    public String toString() {
        return "VenteEtGratuitCdMoisAnneeDto{" + "codeInFoBiArticle=" + codeInFoBiArticle + ", hecto=" + hecto + ", qte=" + qte + ", mois=" + mois + ", annee=" + annee + ", vodeCd=" + codeCd + ", nomCd=" + nomCd + '}';
    }

    
    
}
