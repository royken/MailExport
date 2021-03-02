package com.bracongo.mailexport.data.dto;

import java.io.Serializable;

/**
 *
 * @author vr.kenfack
 */
public class VenteEtGratuitInfoBiFamilleCDMois implements Serializable{
    
    private int codeCd;
    
    private int mois;
    
    private int annee;
    
    private String codars;
    
    private String familleBi;
    
    private String codeInfoBi;
    
    private double hecto;

    public VenteEtGratuitInfoBiFamilleCDMois(int codeCd, int mois, int annee, String codars, String familleBi, String codeInfoBi, double hecto) {
        this.codeCd = codeCd;
        this.mois = mois;
        this.annee = annee;
        this.codars = codars;
        this.familleBi = familleBi;
        this.codeInfoBi = codeInfoBi;
        this.hecto = hecto;
    }

    

    public VenteEtGratuitInfoBiFamilleCDMois() {
    }
    
    

    public int getCodeCd() {
        return codeCd;
    }

    public void setCodeCd(int codeCd) {
        this.codeCd = codeCd;
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

    public String getCodars() {
        return codars;
    }

    public void setCodars(String codars) {
        this.codars = codars;
    }

    public String getFamilleBi() {
        return familleBi;
    }

    public void setFamilleBi(String familleBi) {
        this.familleBi = familleBi;
    }

    public String getCodeInfoBi() {
        return codeInfoBi;
    }

    public void setCodeInfoBi(String codeInfoBi) {
        this.codeInfoBi = codeInfoBi;
    }

    public double getHecto() {
        return hecto;
    }

    public void setHecto(double hecto) {
        this.hecto = hecto;
    }
    
    
}
