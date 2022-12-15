/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bracongo.mailexport.data;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author vr.kenfack
 */
@Embeddable
public class RepArticleCorrespondanceInfoBiPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "CODE_INFO_BI")
    private String codeInfoBi;
    @Basic(optional = false)
    @Column(name = "codar")
    private String codar;

    public RepArticleCorrespondanceInfoBiPK() {
    }

    public RepArticleCorrespondanceInfoBiPK(String codeInfoBi, String codar) {
        this.codeInfoBi = codeInfoBi;
        this.codar = codar;
    }

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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codeInfoBi != null ? codeInfoBi.hashCode() : 0);
        hash += (codar != null ? codar.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RepArticleCorrespondanceInfoBiPK)) {
            return false;
        }
        RepArticleCorrespondanceInfoBiPK other = (RepArticleCorrespondanceInfoBiPK) object;
        if ((this.codeInfoBi == null && other.codeInfoBi != null) || (this.codeInfoBi != null && !this.codeInfoBi.equals(other.codeInfoBi))) {
            return false;
        }
        if ((this.codar == null && other.codar != null) || (this.codar != null && !this.codar.equals(other.codar))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bracongo.mailexport.data.RepArticleCorrespondanceInfoBiPK[ codeInfoBi=" + codeInfoBi + ", codar=" + codar + " ]";
    }
    
}
