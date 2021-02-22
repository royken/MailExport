package com.bracongo.mailexport.data.dto;

import java.io.Serializable;

/**
 *
 * @author vr.kenfack
 */
public class BudgetInfoBiByMoisAnneeDto implements Serializable{
    
    private String codeInfoBi;
    
    private String codars;
    
    private double budget;
    
    private int mois;
    
    private int annee;

    public BudgetInfoBiByMoisAnneeDto() {
    }

    public BudgetInfoBiByMoisAnneeDto(String codeInfoBi, double budget, int mois, int annee) {
        this.codeInfoBi = codeInfoBi;
        this.budget = budget;
        this.mois = mois;
        this.annee = annee;
    }

    public BudgetInfoBiByMoisAnneeDto(String codeInfoBi, String codars, double budget, int mois, int annee) {
        this.codeInfoBi = codeInfoBi;
        this.codars = codars;
        this.budget = budget;
        this.mois = mois;
        this.annee = annee;
    }
    
    

    public String getCodeInfoBi() {
        return codeInfoBi;
    }

    public void setCodeInfoBi(String codeInfoBi) {
        this.codeInfoBi = codeInfoBi;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
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
    
    

    @Override
    public String toString() {
        return "BudgetInfoBiByMoisAnnee{" + "codeInfoBi=" + codeInfoBi + ", budget=" + budget + ", mois=" + mois + ", annee=" + annee + '}';
    }
    
    
    
}
