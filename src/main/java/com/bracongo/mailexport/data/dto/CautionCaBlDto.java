/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bracongo.mailexport.data.dto;

import java.io.Serializable;

/**
 *
 * @author vr.kenfack
 */
public class CautionCaBlDto implements Serializable{
    
    private String codeCd;
    
    private String nomCd;
    
    private String codeCircuit;
    
    private String circuit;
    
    private int jour;
    
    private int mois;
    
    private int annee;
    
    private double ca;
    
    private double caution;
    
    private String bl;



    public CautionCaBlDto(String codeCd, String nomCd, String codeCircuit, String circuit, int mois, int annee, double ca, double caution, String bl) {
        this.codeCd = codeCd;
        this.nomCd = nomCd;
        this.codeCircuit = codeCircuit;
        this.circuit = circuit;
        this.mois = mois;
        this.annee = annee;
        this.ca = ca;
        this.caution = caution;
        this.bl = bl;
    }
    
    

    public CautionCaBlDto() {
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

    public String getCircuit() {
        return circuit;
    }

    public void setCircuit(String circuit) {
        this.circuit = circuit;
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

    public double getCa() {
        return ca;
    }

    public void setCa(double ca) {
        this.ca = ca;
    }

    public double getCaution() {
        return caution;
    }

    public void setCaution(double caution) {
        this.caution = caution;
    }

    public String getBl() {
        return bl;
    }

    public void setBl(String bl) {
        this.bl = bl;
    }
    
}
