package com.bracongo.mailexport.data.dto;

import java.io.Serializable;

/**
 *
 * @author vr.kenfack
 */
public class VenteEtGratuitGammeCdMoisAnneeDto implements Serializable{
    
    private double hecto;
    
    private int mois;
    
    private int codeCd;
    
    private String gamme;

    public VenteEtGratuitGammeCdMoisAnneeDto() {
    }

    public VenteEtGratuitGammeCdMoisAnneeDto(double hecto, int mois, int codeCd, String gamme) {
        this.hecto = hecto;
        this.mois = mois;
        this.codeCd = codeCd;
        this.gamme = gamme;
    }

    public double getHecto() {
        return hecto;
    }

    public void setHecto(double hecto) {
        this.hecto = hecto;
    }

    public int getMois() {
        return mois;
    }

    public void setMois(int mois) {
        this.mois = mois;
    }

    public int getCodeCd() {
        return codeCd;
    }

    public void setCodeCd(int codeCd) {
        this.codeCd = codeCd;
    }

    public String getGamme() {
        return gamme;
    }

    public void setGamme(String gamme) {
        this.gamme = gamme;
    }

    @Override
    public String toString() {
        return "VenteEtGratuitGammeCdMoisAnneeDto{" + "hecto=" + hecto + ", mois=" + mois + ", codeCd=" + codeCd + ", gamme=" + gamme + '}';
    }
    
    
    
}
