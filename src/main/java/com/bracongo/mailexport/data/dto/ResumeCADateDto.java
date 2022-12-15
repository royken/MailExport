package com.bracongo.mailexport.data.dto;

import java.io.Serializable;

/**
 *
 * @author vr.kenfack
 */
public class ResumeCADateDto implements Serializable{
    
    private int annee;
    
     private int mois;
    
    private int quantite;
    
    private double hecto;
    
    private String codars;
    
    private String nomProduit;
    
    private double prixHt;
    
    private double prixTtc;
    
    private String famille;
    
    private int codeCd;

    public ResumeCADateDto(int annee, int mois, int quantite, double hecto, String codars, String nomProduit, double prixHt, double prixTtc) {
        this.annee = annee;
        this.mois = mois;
        this.quantite = quantite;
        this.hecto = hecto;
        this.codars = codars;
        this.nomProduit = nomProduit;
        this.prixHt = prixHt;
        this.prixTtc = prixTtc;
    }

    public ResumeCADateDto() {
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

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public double getHecto() {
        return hecto;
    }

    public void setHecto(double hecto) {
        this.hecto = hecto;
    }

    public String getCodars() {
        return codars;
    }

    public void setCodars(String codars) {
        this.codars = codars;
    }

    public String getNomProduit() {
        return nomProduit;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    public double getPrixHt() {
        return prixHt;
    }

    public void setPrixHt(double prixHt) {
        this.prixHt = prixHt;
    }

    public double getPrixTtc() {
        return prixTtc;
    }

    public void setPrixTtc(double prixTtc) {
        this.prixTtc = prixTtc;
    }

    public String getFamille() {
        return famille;
    }

    public void setFamille(String famille) {
        this.famille = famille;
    }

    public int getCodeCd() {
        return codeCd;
    }

    public void setCodeCd(int codeCd) {
        this.codeCd = codeCd;
    }
    
    

    @Override
    public String toString() {
        return "NegoceResumeCADataDto{" + "annee=" + annee + ", mois=" + mois + ", quantite=" + quantite + ", hecto=" + hecto + ", codars=" + codars + ", nomProduit=" + nomProduit + ", prixHt=" + prixHt + ", prixTtc=" + prixTtc + '}';
    }
    
    
    
   
}
