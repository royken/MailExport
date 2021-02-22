package com.bracongo.mailexport.data.dto;

import java.io.Serializable;

/**
 *
 * @author vr.kenfack
 */
public class CircuitRateeProduit implements Serializable{
    
    private String libelle;
    
    private String nomCd;
    
    private String codeVendeur;
    
    private String circuit;
    
    private String codart;
    
    private String codeCd;
    
    private double hecto;
    
    private long achetee;
    
    private long ratee;
    
    private long souhaitee;
    
    private long stock;

    public CircuitRateeProduit() {
    }
    
    

    public CircuitRateeProduit(String libelle, String nomCd, String codeVendeur, String circuit, String codart, String codeCd, double hecto, long achetee, long ratee, long souhaitee, long stock) {
        this.libelle = libelle;
        this.nomCd = nomCd;
        this.codeVendeur = codeVendeur;
        this.circuit = circuit;
        this.codart = codart;
        this.codeCd = codeCd;
        this.hecto = hecto;
        this.achetee = achetee;
        this.ratee = ratee;
        this.souhaitee = souhaitee;
        this.stock = stock;
    }
    
    

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getNomCd() {
        return nomCd;
    }

    public void setNomCd(String nomCd) {
        this.nomCd = nomCd;
    }

    public String getCodeVendeur() {
        return codeVendeur;
    }

    public void setCodeVendeur(String codeVendeur) {
        this.codeVendeur = codeVendeur;
    }

    public String getCircuit() {
        return circuit;
    }

    public void setCircuit(String circuit) {
        this.circuit = circuit;
    }

    public String getCodart() {
        return codart;
    }

    public void setCodart(String codart) {
        this.codart = codart;
    }

    public String getCodeCd() {
        return codeCd;
    }

    public void setCodeCd(String codeCd) {
        this.codeCd = codeCd;
    }

    public double getHecto() {
        return hecto;
    }

    public void setHecto(double hecto) {
        this.hecto = hecto;
    }

    public long getAchetee() {
        return achetee;
    }

    public void setAchetee(long achetee) {
        this.achetee = achetee;
    }

    public long getRatee() {
        return ratee;
    }

    public void setRatee(long ratee) {
        this.ratee = ratee;
    }

    public long getSouhaitee() {
        return souhaitee;
    }

    public void setSouhaitee(long souhaitee) {
        this.souhaitee = souhaitee;
    }

    public long getStock() {
        return stock;
    }

    public void setStock(long stock) {
        this.stock = stock;
    }
    
    
    
}
