package com.bracongo.mailexport.data.dto;

import java.io.Serializable;

/**
 *
 * @author vr.kenfack
 */
public class VenteGratuitProduitAnneeMoisDto implements Serializable{
    
    private int annee;
    
    private int mois;
    
    private double hecto;
    
    private String codars;
    
    private String famille;
    
    private String nomProduit;

    public VenteGratuitProduitAnneeMoisDto() {
    }

    public VenteGratuitProduitAnneeMoisDto(int annee, int mois, double hecto, String codars, String famille, String nomProduit) {
        this.annee = annee;
        this.mois = mois;
        this.hecto = hecto;
        this.codars = codars;
        this.famille = famille;
        this.nomProduit = nomProduit;
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

    public String getFamille() {
        return famille;
    }

    public void setFamille(String famille) {
        this.famille = famille;
    }

    public String getNomProduit() {
        return nomProduit;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    @Override
    public String toString() {
        return "VenteGratuitProduitAnneeMoisDto{" + "annee=" + annee + ", mois=" + mois + ", hecto=" + hecto + ", codars=" + codars + ", famille=" + famille + ", nomProduit=" + nomProduit + '}';
    }   
    
}
