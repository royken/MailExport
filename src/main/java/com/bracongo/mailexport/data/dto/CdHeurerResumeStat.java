package com.bracongo.mailexport.data.dto;

import java.io.Serializable;

/**
 *
 * @author vr.kenfack
 */
public class CdHeurerResumeStat implements Serializable{
    
    private String nomCd;
    
    private String heurePremiereFacture;
    
    private String heureMoyenne;
    
    private String heureDerniereFacture;
    
    private int totalClientServis;
    
    private int totalClientCd;
    
    private int pourcentageServis;

    public String getNomCd() {
        return nomCd;
    }

    public void setNomCd(String nomCd) {
        this.nomCd = nomCd;
    }

    public String getHeurePremiereFacture() {
        return heurePremiereFacture;
    }

    public void setHeurePremiereFacture(String heurePremiereFacture) {
        this.heurePremiereFacture = heurePremiereFacture;
    }

    public String getHeureDerniereFacture() {
        return heureDerniereFacture;
    }

    public void setHeureDerniereFacture(String heureDerniereFacture) {
        this.heureDerniereFacture = heureDerniereFacture;
    }

    public int getTotalClientServis() {
        return totalClientServis;
    }

    public void setTotalClientServis(int totalClientServis) {
        this.totalClientServis = totalClientServis;
    }

    public int getTotalClientCd() {
        return totalClientCd;
    }

    public void setTotalClientCd(int totalClientCd) {
        this.totalClientCd = totalClientCd;
    }

    public int getPourcentageServis() {
        return pourcentageServis;
    }

    public void setPourcentageServis(int pourcentageServis) {
        this.pourcentageServis = pourcentageServis;
    }

    public String getHeureMoyenne() {
        return heureMoyenne;
    }

    public void setHeureMoyenne(String heureMoyenne) {
        this.heureMoyenne = heureMoyenne;
    }
    
    
    
}
