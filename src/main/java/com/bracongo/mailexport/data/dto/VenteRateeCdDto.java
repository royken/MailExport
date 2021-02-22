package com.bracongo.mailexport.data.dto;

/**
 *
 * @author vr.kenfack
 */
public class VenteRateeCdDto {
    
    private String nomCd;
    
    private String codeCd;
    
    private long nombreRatee;
    
    private long qteRatee;
    
    private double hectoRatee;

    public VenteRateeCdDto(String nomCd, String codeCd, long nombreRatee, long qteRatee,double hectoRatee) {
        this.nomCd = nomCd;
        this.codeCd = codeCd;
        this.nombreRatee = nombreRatee;
        this.qteRatee = qteRatee;
        this.hectoRatee = hectoRatee;
    }
    
    

    public VenteRateeCdDto(String nomCd, long nombreRatee, double hectoRatee) {
        this.nomCd = nomCd;
        this.nombreRatee = nombreRatee;
        this.hectoRatee = hectoRatee;
    }

    public VenteRateeCdDto() {
    }
    

    public String getNomCd() {
        return nomCd;
    }

    public void setNomCd(String nomCd) {
        this.nomCd = nomCd;
    }

    public long getNombreRatee() {
        return nombreRatee;
    }

    public void setNombreRatee(int nombreRatee) {
        this.nombreRatee = nombreRatee;
    }

    public double getHectoRatee() {
        return hectoRatee;
    }

    public void setHectoRatee(double hectoRatee) {
        this.hectoRatee = hectoRatee;
    }

    public String getCodeCd() {
        return codeCd;
    }

    public void setCodeCd(String codeCd) {
        this.codeCd = codeCd;
    }

    public long getQteRatee() {
        return qteRatee;
    }

    public void setQteRatee(long qteRatee) {
        this.qteRatee = qteRatee;
    }
    
    
    
}
