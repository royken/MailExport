/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bracongo.mailexport.data;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author vr.kenfack
 */
@Entity
@Table(name = "REP_ARTICLE_CORRESPONDANCE_INFO_BI")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RepArticleCorrespondanceInfoBi.findAll", query = "SELECT r FROM RepArticleCorrespondanceInfoBi r"),
    @NamedQuery(name = "RepArticleCorrespondanceInfoBi.findByCodeInfoBi", query = "SELECT r FROM RepArticleCorrespondanceInfoBi r WHERE r.repArticleCorrespondanceInfoBiPK.codeInfoBi = :codeInfoBi"),
    @NamedQuery(name = "RepArticleCorrespondanceInfoBi.findByCodar", query = "SELECT r FROM RepArticleCorrespondanceInfoBi r WHERE r.repArticleCorrespondanceInfoBiPK.codar = :codar")})
public class RepArticleCorrespondanceInfoBi implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RepArticleCorrespondanceInfoBiPK repArticleCorrespondanceInfoBiPK;

    public RepArticleCorrespondanceInfoBi() {
    }

    public RepArticleCorrespondanceInfoBi(RepArticleCorrespondanceInfoBiPK repArticleCorrespondanceInfoBiPK) {
        this.repArticleCorrespondanceInfoBiPK = repArticleCorrespondanceInfoBiPK;
    }

    public RepArticleCorrespondanceInfoBi(String codeInfoBi, String codar) {
        this.repArticleCorrespondanceInfoBiPK = new RepArticleCorrespondanceInfoBiPK(codeInfoBi, codar);
    }

    public RepArticleCorrespondanceInfoBiPK getRepArticleCorrespondanceInfoBiPK() {
        return repArticleCorrespondanceInfoBiPK;
    }

    public void setRepArticleCorrespondanceInfoBiPK(RepArticleCorrespondanceInfoBiPK repArticleCorrespondanceInfoBiPK) {
        this.repArticleCorrespondanceInfoBiPK = repArticleCorrespondanceInfoBiPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (repArticleCorrespondanceInfoBiPK != null ? repArticleCorrespondanceInfoBiPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RepArticleCorrespondanceInfoBi)) {
            return false;
        }
        RepArticleCorrespondanceInfoBi other = (RepArticleCorrespondanceInfoBi) object;
        if ((this.repArticleCorrespondanceInfoBiPK == null && other.repArticleCorrespondanceInfoBiPK != null) || (this.repArticleCorrespondanceInfoBiPK != null && !this.repArticleCorrespondanceInfoBiPK.equals(other.repArticleCorrespondanceInfoBiPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bracongo.mailexport.data.RepArticleCorrespondanceInfoBi[ repArticleCorrespondanceInfoBiPK=" + repArticleCorrespondanceInfoBiPK + " ]";
    }
    
}
