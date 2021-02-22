package com.bracongo.mailexport.data.dto;

import java.io.Serializable;

/**
 *
 * @author vr.kenfack
 */
public class ErreurSaisieCdDto implements Serializable{
    
    private String nom;
    
    private String codeCd;
    
    private long nombreErreur;

    public ErreurSaisieCdDto(String nom, String codeCd, long nombreErreur) {
        this.nom = nom;
        this.codeCd = codeCd;
        this.nombreErreur = nombreErreur;
    }

    public ErreurSaisieCdDto() {
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCodeCd() {
        return codeCd;
    }

    public void setCodeCd(String codeCd) {
        this.codeCd = codeCd;
    }

    public long getNombreErreur() {
        return nombreErreur;
    }

    public void setNombreErreur(long nombreErreur) {
        this.nombreErreur = nombreErreur;
    }
    
    
    
}
