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
public class HhtFacturePK implements Serializable {

    @Basic(optional = false)
    @Column(name = "CODE_FACT")
    private String codeFact;
    @Basic(optional = false)
    @Column(name = "CODE_TYPE_DOCUMENT")
    private String codeTypeDocument;
    @Basic(optional = false)
    @Column(name = "CODE_DEVISE")
    private String codeDevise;

    public HhtFacturePK() {
    }

    public HhtFacturePK(String codeFact, String codeTypeDocument, String codeDevise) {
        this.codeFact = codeFact;
        this.codeTypeDocument = codeTypeDocument;
        this.codeDevise = codeDevise;
    }

    public String getCodeFact() {
        return codeFact;
    }

    public void setCodeFact(String codeFact) {
        this.codeFact = codeFact;
    }

    public String getCodeTypeDocument() {
        return codeTypeDocument;
    }

    public void setCodeTypeDocument(String codeTypeDocument) {
        this.codeTypeDocument = codeTypeDocument;
    }

    public String getCodeDevise() {
        return codeDevise;
    }

    public void setCodeDevise(String codeDevise) {
        this.codeDevise = codeDevise;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codeFact != null ? codeFact.hashCode() : 0);
        hash += (codeTypeDocument != null ? codeTypeDocument.hashCode() : 0);
        hash += (codeDevise != null ? codeDevise.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HhtFacturePK)) {
            return false;
        }
        HhtFacturePK other = (HhtFacturePK) object;
        if ((this.codeFact == null && other.codeFact != null) || (this.codeFact != null && !this.codeFact.equals(other.codeFact))) {
            return false;
        }
        if ((this.codeTypeDocument == null && other.codeTypeDocument != null) || (this.codeTypeDocument != null && !this.codeTypeDocument.equals(other.codeTypeDocument))) {
            return false;
        }
        if ((this.codeDevise == null && other.codeDevise != null) || (this.codeDevise != null && !this.codeDevise.equals(other.codeDevise))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bracongo.mailexport.data.HhtFacturePK[ codeFact=" + codeFact + ", codeTypeDocument=" + codeTypeDocument + ", codeDevise=" + codeDevise + " ]";
    }
    
}
