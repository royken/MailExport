package com.bracongo.mailexport.data.dto;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author vr.kenfack
 */
public class HhtFactureCircuitDto implements Serializable{
    
    private String codeRoute;
    
    private String codeCd;
    
    private String nomCd;
    
    private String codeCircuit;
    
    private String codeFact;
    
    private Date dateFact;
    
    private String codeClient;
    
    private Date dateDebutFact;
    
    private Date dateFinFact;
    
    private String BL;
    
    private String codeVendeur;

    public HhtFactureCircuitDto(String codeRoute, String codeCd, String nomCd, String codeCircuit, String codeFact, Date dateFact, String codeClient, Date dateDebutFact, Date dateFinFact, String BL, String codeVendeur) {
        this.codeRoute = codeRoute;
        this.codeCd = codeCd;
        this.nomCd = nomCd;
        this.codeCircuit = codeCircuit;
        this.codeFact = codeFact;
        this.dateFact = dateFact;
        this.codeClient = codeClient;
        this.dateDebutFact = dateDebutFact;
        this.dateFinFact = dateFinFact;
        this.BL = BL;
        this.codeVendeur = codeVendeur;
    }

    

    public HhtFactureCircuitDto() {
    }

    public String getCodeClient() {
        return codeClient;
    }

    public void setCodeClient(String codeClient) {
        this.codeClient = codeClient;
    }
    
    

    public String getCodeRoute() {
        return codeRoute;
    }

    public void setCodeRoute(String codeRoute) {
        this.codeRoute = codeRoute;
    }

    public String getCodeCd() {
        return codeCd;
    }

    public void setCodeCd(String codeCd) {
        this.codeCd = codeCd;
    }

    public String getNomCd() {
        return nomCd;
    }

    public void setNomCd(String nomCd) {
        this.nomCd = nomCd;
    }

    public String getCodeCircuit() {
        return codeCircuit;
    }

    public void setCodeCircuit(String codeCircuit) {
        this.codeCircuit = codeCircuit;
    }

    public String getCodeFact() {
        return codeFact;
    }

    public void setCodeFact(String codeFact) {
        this.codeFact = codeFact;
    }

    public Date getDateFact() {
        return dateFact;
    }

    public void setDateFact(Date dateFact) {
        this.dateFact = dateFact;
    }

    public Date getDateDebutFact() {
        return dateDebutFact;
    }

    public void setDateDebutFact(Date dateDebutFact) {
        this.dateDebutFact = dateDebutFact;
    }

    public Date getDateFinFact() {
        return dateFinFact;
    }

    public void setDateFinFact(Date dateFinFact) {
        this.dateFinFact = dateFinFact;
    }

    public String getBL() {
        return BL;
    }

    public void setBL(String BL) {
        this.BL = BL;
    }

    public String getCodeVendeur() {
        return codeVendeur;
    }

    public void setCodeVendeur(String codeVendeur) {
        this.codeVendeur = codeVendeur;
    }

    @Override
    public String toString() {
        return "HhtFactureCircuitDto{" + "codeRoute=" + codeRoute + ", codeCd=" + codeCd + ", nomCd=" + nomCd + ", codeCircuit=" + codeCircuit + ", codeFact=" + codeFact + ", dateFact=" + dateFact + ", dateDebutFact=" + dateDebutFact + ", dateFinFact=" + dateFinFact + ", BL=" + BL + ", codeVendeur=" + codeVendeur + '}';
    }
    
    
    
}
