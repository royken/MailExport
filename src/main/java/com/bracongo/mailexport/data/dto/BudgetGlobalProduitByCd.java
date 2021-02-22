package com.bracongo.mailexport.data.dto;

import java.io.Serializable;

/**
 *
 * @author vr.kenfack
 */
public class BudgetGlobalProduitByCd implements Serializable{
    
    private String codeInfoBi;
    
    private String codars;
    
    private double budget;
    
    private int annee;
    
    private String codeCd;

    public BudgetGlobalProduitByCd() {
    }

    public BudgetGlobalProduitByCd(String codeInfoBi, String codars, double budget, int annee, String codeCd) {
        this.codeInfoBi = codeInfoBi;
        this.codars = codars;
        this.budget = budget;
        this.annee = annee;
        this.codeCd = codeCd;
    }

    public String getCodeInfoBi() {
        return codeInfoBi;
    }

    public void setCodeInfoBi(String codeInfoBi) {
        this.codeInfoBi = codeInfoBi;
    }

    public String getCodars() {
        return codars;
    }

    public void setCodars(String codars) {
        this.codars = codars;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
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

    @Override
    public String toString() {
        return "BudgetGlobalProduitByCd{" + "codeInfoBi=" + codeInfoBi + ", codars=" + codars + ", budget=" + budget + ", annee=" + annee + ", codeCd=" + codeCd + '}';
    }
    
    
    
}
