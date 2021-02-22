package com.bracongo.mailexport.data;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author vr.kenfack
 */
@Entity
@Table(name = "REP_ARTICLE_INFOBI")
public class RepArticleInfoBi implements Serializable{
    
    @Id
    @Column(name = "CODE_INFO_BI")
    private String codeInfoBi;
    
    @Column(name = "CODAR")
    private String codar;
    
    @Column(name = "DESIGNATION")
    private String designation;
    
    @Column(name = "FAMILLE")
    private String famille;
    
    @Column(name = "TAILLE")
    private int taille;
    
    @Column(name = "CODE_EMBALLAGE")
    private int codeEmballage;

    public String getCodeInfoBi() {
        return codeInfoBi;
    }

    public void setCodeInfoBi(String codeInfoBi) {
        this.codeInfoBi = codeInfoBi;
    }

    public String getCodar() {
        return codar;
    }

    public void setCodar(String codar) {
        this.codar = codar;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getFamille() {
        return famille;
    }

    public void setFamille(String famille) {
        this.famille = famille;
    }

    public int getTaille() {
        return taille;
    }

    public void setTaille(int taille) {
        this.taille = taille;
    }

    public int getCodeEmballage() {
        return codeEmballage;
    }

    public void setCodeEmballage(int codeEmballage) {
        this.codeEmballage = codeEmballage;
    }
    
    
    
}
