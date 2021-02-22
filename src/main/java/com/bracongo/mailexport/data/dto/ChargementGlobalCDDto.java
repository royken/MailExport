package com.bracongo.mailexport.data.dto;

import java.io.Serializable;

/**
 *
 * @author vr.kenfack
 */
public class ChargementGlobalCDDto implements Serializable{
    
    private String nomCd;
    
    private String codeCd;
    
    private Double quantiteChargee;
    
    private Double quantiteVendue;
    
    private Double quantiteRetour;
    
    private Double tauxRetour;
    
    private int voyage;

    public ChargementGlobalCDDto(String nomCd, String codeCd, Double quantiteChargee, Double quantiteVendue, Double quantiteRetour, Double tauxRetour, int voyage) {
        this.nomCd = nomCd;
        this.codeCd = codeCd;
        this.quantiteChargee = quantiteChargee;
        this.quantiteVendue = quantiteVendue;
        this.quantiteRetour = quantiteRetour;
        this.tauxRetour = tauxRetour;
        this.voyage = voyage;
    }

    public ChargementGlobalCDDto() {
    }
    
    

    public String getNomCd() {
        return nomCd;
    }

    public void setNomCd(String nomCd) {
        this.nomCd = nomCd;
    }

    public String getCodeCd() {
        return codeCd;
    }

    public void setCodeCd(String codeCd) {
        this.codeCd = codeCd;
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

    public int getVoyage() {
        return voyage;
    }

    public void setVoyage(int voyage) {
        this.voyage = voyage;
    }
    
    
}
