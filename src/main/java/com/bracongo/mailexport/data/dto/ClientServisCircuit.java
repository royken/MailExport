package com.bracongo.mailexport.data.dto;

import java.io.Serializable;

/**
 * Classe qui stocke la les clients ayant achetés dans un circuit
 * @author vr.kenfack
 */
public class ClientServisCircuit implements Serializable{
    
    private int clientAcheteurs;
    
    private int effectifCircuit;
    
    private String codeCd;
    
    private String nomCd;
    
    private String circuit;

    public ClientServisCircuit(int clientAcheteurs, int effectifCircuit, String codeCd, String nomCd, String circuit) {
        this.clientAcheteurs = clientAcheteurs;
        this.effectifCircuit = effectifCircuit;
        this.codeCd = codeCd;
        this.nomCd = nomCd;
        this.circuit = circuit;
    }

    public ClientServisCircuit() {
    }
    
    

    public int getClientAcheteurs() {
        return clientAcheteurs;
    }

    public void setClientAcheteurs(int clientAcheteurs) {
        this.clientAcheteurs = clientAcheteurs;
    }

    public int getEffectifCircuit() {
        return effectifCircuit;
    }

    public void setEffectifCircuit(int effectifCircuit) {
        this.effectifCircuit = effectifCircuit;
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

    public String getCircuit() {
        return circuit;
    }

    public void setCircuit(String circuit) {
        this.circuit = circuit;
    }

    @Override
    public String toString() {
        return "ClientServisCircuit{" + "clientAcheteurs=" + clientAcheteurs + ", effectifCircuit=" + effectifCircuit + ", codeCd=" + codeCd + ", nomCd=" + nomCd + ", circuit=" + circuit + '}';
    }
    
    
    
}
