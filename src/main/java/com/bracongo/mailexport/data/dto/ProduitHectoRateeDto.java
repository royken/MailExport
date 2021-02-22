package com.bracongo.mailexport.data.dto;

import java.io.Serializable;

/**
 *
 * @author vr.kenfack
 */
public class ProduitHectoRateeDto implements Serializable{
    
    private String produit;
    
    private long nombreRate;
    
    private long qteTotaleSouhaitee;
    
    private long qteTotaleAchetee;
    
    private long qteTotaleRatee;
    
    private double hectoRatee;

    public ProduitHectoRateeDto(String produit, long nombreRate, long qteTotaleSouhaitee, long qteTotaleAchetee, long qteTotaleRatee, double hectoRatee) {
        this.produit = produit;
        this.nombreRate = nombreRate;
        this.qteTotaleSouhaitee = qteTotaleSouhaitee;
        this.qteTotaleAchetee = qteTotaleAchetee;
        this.qteTotaleRatee = qteTotaleRatee;
        this.hectoRatee = hectoRatee;
    }

    public ProduitHectoRateeDto() {
    }
    
    

    public String getProduit() {
        return produit;
    }

    public void setProduit(String produit) {
        this.produit = produit;
    }

    public long getNombreRate() {
        return nombreRate;
    }

    public void setNombreRate(int nombreRate) {
        this.nombreRate = nombreRate;
    }

    public long getQteTotaleSouhaitee() {
        return qteTotaleSouhaitee;
    }

    public void setQteTotaleSouhaitee(int qteTotaleSouhaitee) {
        this.qteTotaleSouhaitee = qteTotaleSouhaitee;
    }

    public long getQteTotaleAchetee() {
        return qteTotaleAchetee;
    }

    public void setQteTotaleAchetee(int qteTotaleAchetee) {
        this.qteTotaleAchetee = qteTotaleAchetee;
    }

    public long getQteTotaleRatee() {
        return qteTotaleRatee;
    }

    public void setQteTotaleRatee(int qteTotaleRatee) {
        this.qteTotaleRatee = qteTotaleRatee;
    }

    public double getHectoRatee() {
        return hectoRatee;
    }

    public void setHectoRatee(double hectoRatee) {
        this.hectoRatee = hectoRatee;
    }
    
    
    
}
