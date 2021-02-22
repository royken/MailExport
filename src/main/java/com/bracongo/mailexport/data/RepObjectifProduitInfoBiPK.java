package com.bracongo.mailexport.data;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author vr.kenfack
 */
@Embeddable
public class RepObjectifProduitInfoBiPK implements Serializable{
    
    @Column(name = "CODE_INFO_BI")
    private String codeInfoBi;
    
    @Column(name = "MOIS")
    private int mois;
    
    @Column(name = "ANNEE")
    private int annee;
    
    @Column(name = "CODE_CD")
    private String codeCd;

    public String getCodeInfoBi() {
        return codeInfoBi;
    }

    public void setCodeInfoBi(String codeInfoBi) {
        this.codeInfoBi = codeInfoBi;
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

    public String getCodeCd() {
        return codeCd;
    }

    public void setCodeCd(String codeCd) {
        this.codeCd = codeCd;
    }

    @Override
    public String toString() {
        return "RepObjectifProduitInfoBiPK{" + "codeInfoBi=" + codeInfoBi + ", mois=" + mois + ", annee=" + annee + ", codeCd=" + codeCd + '}';
    }
    
    
}
