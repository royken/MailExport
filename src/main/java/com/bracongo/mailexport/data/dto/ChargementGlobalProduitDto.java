package com.bracongo.mailexport.data.dto;

import java.io.Serializable;

/**
 *
 * @author vr.kenfack
 */
public class ChargementGlobalProduitDto implements Serializable{
    
    private String codars;
    
    private String nomProduit;
    
    private Double quantiteChargee;
    
    private Double quantiteVendue;
    
    private Double quantiteRetour;
    
    private Double tauxRetour;
    
    private double voyage;
    
    private String gamme;

    public ChargementGlobalProduitDto(String codars, String nomProduit, Double quantiteChargee, Double quantiteVendue, Double quantiteRetour, Double tauxRetour, double voyage, String gamme) {
        this.codars = codars;
        this.nomProduit = nomProduit;
        this.quantiteChargee = quantiteChargee;
        this.quantiteVendue = quantiteVendue;
        this.quantiteRetour = quantiteRetour;
        this.tauxRetour = tauxRetour;
        this.voyage = voyage;
        this.gamme = gamme;
    }

    public ChargementGlobalProduitDto() {
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

    public Double getQuantiteChargee() {
        return quantiteChargee;
    }

    public void setQuantiteChargee(Double quantiteChargee) {
        this.quantiteChargee = quantiteChargee;
    }

    public Double getQuantiteVendue() {
        return quantiteVendue;
    }

    public void setQuantiteVendue(Double quantiteVendue) {
        this.quantiteVendue = quantiteVendue;
    }

    public Double getQuantiteRetour() {
        return quantiteRetour;
    }

    public void setQuantiteRetour(Double quantiteRetour) {
        this.quantiteRetour = quantiteRetour;
    }

    public Double getTauxRetour() {
        return tauxRetour;
    }

    public void setTauxRetour(Double tauxRetour) {
        this.tauxRetour = tauxRetour;
    }

    public double getVoyage() {
        return voyage;
    }

    public void setVoyage(double voyage) {
        this.voyage = voyage;
    }

    public String getGamme() {
        return gamme;
    }

    public void setGamme(String gamme) {
        this.gamme = gamme;
    }
    
    
    
}
