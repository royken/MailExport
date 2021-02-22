package com.bracongo.mailexport.data.dto;

import java.io.Serializable;

/**
 *
 * @author vr.kenfack
 */
public class ErreurSaisieCircuitDto implements Serializable{
    
    private String nomCd;
    
    private String codeCd;
    
    private String codeCircuit;
    
    private String circuit;
    
    private long nombreErreur;

    public ErreurSaisieCircuitDto(String nomCd, String codeCd, String codeCircuit, String circuit, long nombreErreur) {
        this.nomCd = nomCd;
        this.codeCd = codeCd;
        this.codeCircuit = codeCircuit;
        this.circuit = circuit;
        this.nombreErreur = nombreErreur;
    }

    public ErreurSaisieCircuitDto() {
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

    public long getNombreErreur() {
        return nombreErreur;
    }

    public void setNombreErreur(long nombreErreur) {
        this.nombreErreur = nombreErreur;
    }
    
    
    
}
