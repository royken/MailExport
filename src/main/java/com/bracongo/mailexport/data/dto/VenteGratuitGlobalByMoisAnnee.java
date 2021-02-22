package com.bracongo.mailexport.data.dto;

import java.io.Serializable;

/**
 *
 * @author vr.kenfack
 */
public class VenteGratuitGlobalByMoisAnnee implements Serializable{
    
    private int annee;
    
    private int mois;
    
    private double hecto;

    public VenteGratuitGlobalByMoisAnnee() {
    }

    public VenteGratuitGlobalByMoisAnnee(int annee, int mois, double hecto) {
        this.annee = annee;
        this.mois = mois;
        this.hecto = hecto;
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

    @Override
    public String toString() {
        return "VenteGratuitGlobalByMoisAnnee{" + "annee=" + annee + ", mois=" + mois + ", hecto=" + hecto + '}';
    }
    
    
    
}
