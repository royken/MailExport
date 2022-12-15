package com.bracongo.mailexport.data;

import java.io.Serializable;
import java.util.Objects;
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
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.codeInfoBi);
        hash = 31 * hash + this.mois;
        hash = 31 * hash + Objects.hashCode(this.codeCd);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final RepObjectifProduitInfoBiPK other = (RepObjectifProduitInfoBiPK) obj;
        if (this.mois != other.mois) {
            return false;
        }
        if (this.annee != other.annee) {
            return false;
        }
        if (!Objects.equals(this.codeInfoBi, other.codeInfoBi)) {
            return false;
        }
        if (!Objects.equals(this.codeCd, other.codeCd)) {
            return false;
        }
        return true;
    }
    
    

    @Override
    public String toString() {
        return "RepObjectifProduitInfoBiPK{" + "codeInfoBi=" + codeInfoBi + ", mois=" + mois + ", annee=" + annee + ", codeCd=" + codeCd + '}';
    }
    
    
}
