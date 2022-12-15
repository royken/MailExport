package com.bracongo.mailexport.data.dto;

import java.io.Serializable;

/**
 * Classe contenant les informations sur le chargement et le retour d'un produit
 * @author vr.kenfack
 */
public class ChargementProduitDto implements Serializable{
    
    private String codeProduit;
    
    private String nomProduit;
    
    private double quantiteChargee;
    
    private double quantiteRetour;
    
    private double quantiteVendue;
    
    private double hectoVendue;
    
    private double tauxRetour;
    
    private String gamme;
    
    private String codeCircuit;
    
    private int codeCd;
    
    private String famille;
    
    private int voyageId;
    
    private int jour;
    
    private int mois;
    
    private int annee;
    
    private int ntd;
    
    private String nomCd;

    public ChargementProduitDto(String codeProduit, String nomProduit, double quantiteChargee, double quantiteRetour, double quantiteVendue, double hectoVendue, double tauxRetour, String gamme) {
        this.codeProduit = codeProduit;
        this.nomProduit = nomProduit;
        this.quantiteChargee = quantiteChargee;
        this.quantiteRetour = quantiteRetour;
        this.quantiteVendue = quantiteVendue;
        this.hectoVendue = hectoVendue;
        this.tauxRetour = tauxRetour;
        this.gamme = gamme;
    }

    public ChargementProduitDto(String codeProduit, String nomProduit, double quantiteChargee, double quantiteRetour, double quantiteVendue, double hectoVendue, double tauxRetour, String gamme, String codeCircuit, int codeCd, int voyageId, int jour, int mois, int annee, int ntd, String nomCd) {
        this.codeProduit = codeProduit;
        this.nomProduit = nomProduit;
        this.quantiteChargee = quantiteChargee;
        this.quantiteRetour = quantiteRetour;
        this.quantiteVendue = quantiteVendue;
        this.hectoVendue = hectoVendue;
        this.tauxRetour = tauxRetour;
        this.gamme = gamme;
        this.codeCircuit = codeCircuit;
        this.codeCd = codeCd;
        this.voyageId = voyageId;
        this.jour = jour;
        this.mois = mois;
        this.annee = annee;
        this.ntd = ntd;
        this.nomCd = nomCd;
    }

    public ChargementProduitDto(String codeProduit, String nomProduit, double quantiteChargee, double quantiteRetour, double quantiteVendue, double hectoVendue, double tauxRetour, String gamme, String codeCircuit, int codeCd, int voyageId, int mois, int annee, String nomCd) {
        this.codeProduit = codeProduit;
        this.nomProduit = nomProduit;
        this.quantiteChargee = quantiteChargee;
        this.quantiteRetour = quantiteRetour;
        this.quantiteVendue = quantiteVendue;
        this.hectoVendue = hectoVendue;
        this.tauxRetour = tauxRetour;
        this.gamme = gamme;
        this.codeCircuit = codeCircuit;
        this.codeCd = codeCd;
        this.voyageId = voyageId;
        this.mois = mois;
        this.annee = annee;
        this.nomCd = nomCd;
    }

    public ChargementProduitDto(String codeProduit, String nomProduit, double quantiteChargee, double quantiteRetour, double quantiteVendue, double hectoVendue, double tauxRetour, String gamme, String codeCircuit, int codeCd, int mois, int annee, String nomCd) {
        this.codeProduit = codeProduit;
        this.nomProduit = nomProduit;
        this.quantiteChargee = quantiteChargee;
        this.quantiteRetour = quantiteRetour;
        this.quantiteVendue = quantiteVendue;
        this.hectoVendue = hectoVendue;
        this.tauxRetour = tauxRetour;
        this.gamme = gamme;
        this.codeCircuit = codeCircuit;
        this.codeCd = codeCd;
        this.mois = mois;
        this.annee = annee;
        this.nomCd = nomCd;
    }

    
    
    

    public ChargementProduitDto() {
    }
    
    

    public String getCodeProduit() {
        return codeProduit;
    }

    public void setCodeProduit(String codeProduit) {
        this.codeProduit = codeProduit;
    }

    public String getNomProduit() {
        return nomProduit;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    public double getQuantiteChargee() {
        return quantiteChargee;
    }

    public void setQuantiteChargee(double quantiteChargee) {
        this.quantiteChargee = quantiteChargee;
    }

    public double getQuantiteRetour() {
        return quantiteRetour;
    }

    public void setQuantiteRetour(double quantiteRetour) {
        this.quantiteRetour = quantiteRetour;
    }

    public double getQuantiteVendue() {
        return quantiteVendue;
    }

    public void setQuantiteVendue(double quantiteVendue) {
        this.quantiteVendue = quantiteVendue;
    }

    public double getHectoVendue() {
        return hectoVendue;
    }

    public void setHectoVendue(double hectoVendue) {
        this.hectoVendue = hectoVendue;
    }

    public double getTauxRetour() {
        return tauxRetour;
    }

    public void setTauxRetour(double tauxRetour) {
        this.tauxRetour = tauxRetour;
    }

    public String getGamme() {
        return gamme;
    }

    public void setGamme(String gamme) {
        this.gamme = gamme;
    }

    public String getCodeCircuit() {
        return codeCircuit;
    }

    public void setCodeCircuit(String codeCircuit) {
        this.codeCircuit = codeCircuit;
    }

    public int getCodeCd() {
        return codeCd;
    }

    public void setCodeCd(int codeCd) {
        this.codeCd = codeCd;
    }

    public String getFamille() {
        if("BLONDE".equals(gamme.trim()) || "BRUNE".equals(gamme.trim())){
            return "BI" ;
        }
        else if("XXL".equals(gamme.trim())){
            return "XXL" ;
        }
        else if(gamme.contains("EAU")){
            return "EAUX" ;
        }
        else if("VIN".equals(gamme.trim())){
            return "VIN";
        }
        
        return "BG";
    }

    public void setFamille(String famille) {
        this.famille = famille;
    }

    public int getVoyageId() {
        return voyageId;
    }

    public void setVoyageId(int voyageId) {
        this.voyageId = voyageId;
    }

    public int getJour() {
        return jour;
    }

    public void setJour(int jour) {
        this.jour = jour;
    }

    public int getMois() {
        return mois;
    }

    public void setMois(int mois) {
        this.mois = mois;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public int getNtd() {
        return ntd;
    }

    public void setNtd(int ntd) {
        this.ntd = ntd;
    }

    public String getNomCd() {
        return nomCd;
    }

    public void setNomCd(String nomCd) {
        this.nomCd = nomCd;
    }

    @Override
    public String toString() {
        return "ChargementProduitDto{" + "codeProduit=" + codeProduit + ", nomProduit=" + nomProduit + ", quantiteChargee=" + quantiteChargee + ", quantiteRetour=" + quantiteRetour + ", quantiteVendue=" + quantiteVendue + ", hectoVendue=" + hectoVendue + ", tauxRetour=" + tauxRetour + ", gamme=" + gamme + ", codeCircuit=" + codeCircuit + ", codeCd=" + codeCd + ", famille=" + famille + ", voyageId=" + voyageId + ", jour=" + jour + ", mois=" + mois + ", annee=" + annee + ", ntd=" + ntd + ", nomCd=" + nomCd + '}';
    }
    
   
    
}
