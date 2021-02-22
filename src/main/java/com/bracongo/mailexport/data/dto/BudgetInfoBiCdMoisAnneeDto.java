package com.bracongo.mailexport.data.dto;

/**
 *
 * @author vr.kenfack
 */
public class BudgetInfoBiCdMoisAnneeDto {
    
    private String codeInFoBiArticle;
    
    private double budget;
    
    private int mois;
    
    private int annee;
    
    private String codeCd;
    
    private String nomCd;

    public BudgetInfoBiCdMoisAnneeDto(String codeInFoBiArticle, double budget, int mois, int annee, String vodeCd, String nomCd) {
        this.codeInFoBiArticle = codeInFoBiArticle;
        this.budget = budget;
        this.mois = mois;
        this.annee = annee;
        this.codeCd = vodeCd;
        this.nomCd = nomCd;
    }

    public BudgetInfoBiCdMoisAnneeDto() {
    }
    

    public String getCodeInFoBiArticle() {
        return codeInFoBiArticle;
    }

    public void setCodeInFoBiArticle(String codeInFoBiArticle) {
        this.codeInFoBiArticle = codeInFoBiArticle;
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

    @Override
    public String toString() {
        return "BudgetInfoBiCdMoisAnneeDto{" + "codeInFoBiArticle=" + codeInFoBiArticle + ", budget=" + budget + ", mois=" + mois + ", annee=" + annee + ", codeCd=" + codeCd + ", nomCd=" + nomCd + '}';
    }
    
    
}
