package com.bracongo.mailexport.data.dto;

import java.io.Serializable;

/**
 *
 * @author vr.kenfack
 */
public class BudgetInfoBiByCDMoisFamilleDto implements Serializable{
    
    private String codeCd;
    
    private int mois;
    
    private double budget;
    
    private String famille;

    public BudgetInfoBiByCDMoisFamilleDto() {
    }

    public BudgetInfoBiByCDMoisFamilleDto(String codeCd, int mois, double budget, String famille) {
        this.codeCd = codeCd;
        this.mois = mois;
        this.budget = budget;
        this.famille = famille;
    }

    public String getCodeCd() {
        return codeCd;
    }

    public void setCodeCd(String codeCd) {
        this.codeCd = codeCd;
    }

    public int getMois() {
        return mois;
    }

    public void setMois(int mois) {
        this.mois = mois;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public String getFamille() {
        return famille;
    }

    public void setFamille(String famille) {
        this.famille = famille;
    }

    @Override
    public String toString() {
        return "BudgetInfoBiByCDMoisFamilleDto{" + "codeCd=" + codeCd + ", mois=" + mois + ", budget=" + budget + ", famille=" + famille + '}';
    } 
    
}
