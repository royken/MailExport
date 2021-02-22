package com.bracongo.mailexport.data.dto;

import java.io.Serializable;

/**
 *
 * @author vr.kenfack
 */
public class ProduitRatee implements Serializable{
    
    private String libelle;
    
    private String codart;
    
    private long ratee;

    private double hectoRatee;
    
    
    public ProduitRatee(String libelle, String codart, long ratee, double hectoRatee) {
        this.libelle = libelle;
        this.codart = codart;
        this.ratee = ratee;
        this.hectoRatee = hectoRatee;
    }

    public ProduitRatee() {
    }
    
    

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getCodart() {
        return codart;
    }

    public void setCodart(String codart) {
        this.codart = codart;
    }

    public long getRatee() {
        return ratee;
    }

    public void setRatee(long ratee) {
        this.ratee = ratee;
    }

    public double getHectoRatee() {
        return hectoRatee;
    }

    public void setHectoRatee(double hectoRatee) {
        this.hectoRatee = hectoRatee;
    }
   
}
