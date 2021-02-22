package com.bracongo.mailexport.data.dto;

import java.io.Serializable;

/**
 *
 * @author vr.kenfack
 */
public class BudgetInfoBiGlobalMoisAnneeDto implements Serializable{
    
    private int annee;
    
    private int mois;
    
    private double budget;

    public BudgetInfoBiGlobalMoisAnneeDto() {
    }

    public BudgetInfoBiGlobalMoisAnneeDto(int annee, int mois, double budget) {
        this.annee = annee;
        this.mois = mois;
        this.budget = budget;
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

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    @Override
    public String toString() {
        return "BudgetInfoBiGlobalMoisAnneeDto{" + "annee=" + annee + ", mois=" + mois + ", budget=" + budget + '}';
    }
    
    
    
}
