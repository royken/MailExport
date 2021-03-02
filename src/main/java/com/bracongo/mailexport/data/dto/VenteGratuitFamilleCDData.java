package com.bracongo.mailexport.data.dto;

/**
 *
 * @author vr.kenfack
 */
public class VenteGratuitFamilleCDData {
    private String famille;
    
    private String cd;
    
    private int mois;
    
    private double hecto;

    public VenteGratuitFamilleCDData(String famille, String cd, int mois, double hecto) {
        this.famille = famille;
        this.cd = cd;
        this.mois = mois;
        this.hecto = hecto;
    }
    
    

    public VenteGratuitFamilleCDData(VenteEtGratuitGammeCdMoisAnneeDto vente) {
        this.hecto = vente.getHecto();
        this.cd = String.valueOf(vente.getCodeCd());
        this.mois = vente.getMois();
        this.famille = getNomMarque(vente);
    }

    public String getFamille() {
        return famille;
    }

    public void setFamille(String famille) {
        this.famille = famille;
    }

    public String getCd() {
        return cd;
    }

    public void setCd(String cd) {
        this.cd = cd;
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
    
    private String getNomMarque(VenteEtGratuitGammeCdMoisAnneeDto vente){
     
        if("BLONDE".equals(vente.getGamme()) || "BRUNE".equals(vente.getGamme())){
            return "BI" ;
        }
        else if("XXL".equals(vente.getGamme())){
            return "XXL" ;
        }
        else if(vente.getGamme().contains("EAU")){
            return "EAU" ;
        }
        else if("VIN".equals(vente.getGamme())){
            return "VIN";
        }
        
        return "BG";
    }
    
    
    
}
