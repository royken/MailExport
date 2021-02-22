package com.bracongo.mailexport.data.dto;

import java.io.Serializable;

/**
 * Classe contenant les informations sur le chargement et le retour d'un produit
 * @author vr.kenfack
 */
public class ChargementProduitDto implements Serializable{
    
    private String codeProduit;
    
    private String nomProduit;
    
    private double quantiteChargee;
    
    private double quantiteRetour;
    
    private double quantiteVendue;
    
    private double hectoVendue;
    
    private double tauxRetour;
    
    private String gamme;

    public ChargementProduitDto(String codeProduit, String nomProduit, double quantiteChargee, double quantiteRetour, double quantiteVendue, double hectoVendue, double tauxRetour, String gamme) {
        this.codeProduit = codeProduit;
        this.nomProduit = nomProduit;
        this.quantiteChargee = quantiteChargee;
        this.quantiteRetour = quantiteRetour;
        this.quantiteVendue = quantiteVendue;
        this.hectoVendue = hectoVendue;
        this.tauxRetour = tauxRetour;
        this.gamme = gamme;
    }

    public ChargementProduitDto() {
    }
    
    

    public String getCodeProduit() {
        return codeProduit;
    }

    public void setCodeProduit(String codeProduit) {
        this.codeProduit = codeProduit;
    }

    public String getNomProduit() {
        return nomProduit;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    public double getQuantiteChargee() {
        return quantiteChargee;
    }

    public void setQuantiteChargee(double quantiteChargee) {
        this.quantiteChargee = quantiteChargee;
    }

    public double getQuantiteRetour() {
        return quantiteRetour;
    }

    public void setQuantiteRetour(double quantiteRetour) {
        this.quantiteRetour = quantiteRetour;
    }

    public double getQuantiteVendue() {
        return quantiteVendue;
    }

    public void setQuantiteVendue(double quantiteVendue) {
        this.quantiteVendue = quantiteVendue;
    }

    public double getHectoVendue() {
        return hectoVendue;
    }

    public void setHectoVendue(double hectoVendue) {
        this.hectoVendue = hectoVendue;
    }

    public double getTauxRetour() {
        return tauxRetour;
    }

    public void setTauxRetour(double tauxRetour) {
        this.tauxRetour = tauxRetour;
    }

    public String getGamme() {
        return gamme;
    }

    public void setGamme(String gamme) {
        this.gamme = gamme;
    }

    @Override
    public String toString() {
        return "ChargementProduitDto{" + "codeProduit=" + codeProduit + ", nomProduit=" + nomProduit + ", quantiteChargee=" + quantiteChargee + ", quantiteRetour=" + quantiteRetour + ", quantiteVendue=" + quantiteVendue + ", hectoVendue=" + hectoVendue + ", tauxRetour=" + tauxRetour + ", gamme=" + gamme + '}';
    }
    
    
    
}
