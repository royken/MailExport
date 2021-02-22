/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bracongo.mailexport.data;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Valmy Roi Kenfack <vr.kenfack at bracongo.cd>
 */
@Entity
@Table(name = "HHT_DETAIL_FACTURE_ART", catalog = "HHT_BRACONGO", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HhtDetailFactureArt.findAll", query = "SELECT h FROM HhtDetailFactureArt h")
    , @NamedQuery(name = "HhtDetailFactureArt.findByCodeFact", query = "SELECT h FROM HhtDetailFactureArt h WHERE h.hhtDetailFactureArtPK.codeFact = :codeFact")
    , @NamedQuery(name = "HhtDetailFactureArt.findByCodeArt", query = "SELECT h FROM HhtDetailFactureArt h WHERE h.hhtDetailFactureArtPK.codeArt = :codeArt")
    , @NamedQuery(name = "HhtDetailFactureArt.findByQte", query = "SELECT h FROM HhtDetailFactureArt h WHERE h.qte = :qte")
    , @NamedQuery(name = "HhtDetailFactureArt.findByMontantTaxe2", query = "SELECT h FROM HhtDetailFactureArt h WHERE h.montantTaxe2 = :montantTaxe2")
    , @NamedQuery(name = "HhtDetailFactureArt.findByTotal", query = "SELECT h FROM HhtDetailFactureArt h WHERE h.total = :total")
    , @NamedQuery(name = "HhtDetailFactureArt.findByCodeTypeDocument", query = "SELECT h FROM HhtDetailFactureArt h WHERE h.hhtDetailFactureArtPK.codeTypeDocument = :codeTypeDocument")
    , @NamedQuery(name = "HhtDetailFactureArt.findByPu", query = "SELECT h FROM HhtDetailFactureArt h WHERE h.pu = :pu")
    , @NamedQuery(name = "HhtDetailFactureArt.findByNumPu", query = "SELECT h FROM HhtDetailFactureArt h WHERE h.hhtDetailFactureArtPK.numPu = :numPu")
    , @NamedQuery(name = "HhtDetailFactureArt.findByQteCaisses", query = "SELECT h FROM HhtDetailFactureArt h WHERE h.qteCaisses = :qteCaisses")
    , @NamedQuery(name = "HhtDetailFactureArt.findByCbBiglox", query = "SELECT h FROM HhtDetailFactureArt h WHERE h.hhtDetailFactureArtPK.cbBiglox = :cbBiglox")
    , @NamedQuery(name = "HhtDetailFactureArt.findByNumero", query = "SELECT h FROM HhtDetailFactureArt h WHERE h.hhtDetailFactureArtPK.numero = :numero")
    , @NamedQuery(name = "HhtDetailFactureArt.findByBl", query = "SELECT h FROM HhtDetailFactureArt h WHERE h.bl = :bl")
    , @NamedQuery(name = "HhtDetailFactureArt.findByCodeDevise", query = "SELECT h FROM HhtDetailFactureArt h WHERE h.hhtDetailFactureArtPK.codeDevise = :codeDevise")})
public class HhtDetailFactureArt extends BaseClass{

    @EmbeddedId
    protected HhtDetailFactureArtPK hhtDetailFactureArtPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(precision = 53)
    private Double qte;
    @Column(name = "MONTANT_TAXE2", precision = 53)
    private Double montantTaxe2;
    @Column(precision = 53)
    private Double total;
    @Column(precision = 53)
    private Double pu;
    @Column(name = "QTE_CAISSES", precision = 53)
    private Double qteCaisses;
    @Column(length = 50)
    private String bl;

    public HhtDetailFactureArt() {
    }

    public HhtDetailFactureArt(HhtDetailFactureArtPK hhtDetailFactureArtPK) {
        this.hhtDetailFactureArtPK = hhtDetailFactureArtPK;
    }

    public HhtDetailFactureArtPK getHhtDetailFactureArtPK() {
        return hhtDetailFactureArtPK;
    }

    public void setHhtDetailFactureArtPK(HhtDetailFactureArtPK hhtDetailFactureArtPK) {
        this.hhtDetailFactureArtPK = hhtDetailFactureArtPK;
    }

    public Double getQte() {
        return qte;
    }

    public void setQte(Double qte) {
        this.qte = qte;
    }

    public Double getMontantTaxe2() {
        return montantTaxe2;
    }

    public void setMontantTaxe2(Double montantTaxe2) {
        this.montantTaxe2 = montantTaxe2;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getPu() {
        return pu;
    }

    public void setPu(Double pu) {
        this.pu = pu;
    }

    public Double getQteCaisses() {
        return qteCaisses;
    }

    public void setQteCaisses(Double qteCaisses) {
        this.qteCaisses = qteCaisses;
    }

    public String getBl() {
        return bl;
    }

    public void setBl(String bl) {
        this.bl = bl;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (hhtDetailFactureArtPK != null ? hhtDetailFactureArtPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HhtDetailFactureArt)) {
            return false;
        }
        HhtDetailFactureArt other = (HhtDetailFactureArt) object;
        return !((this.hhtDetailFactureArtPK == null && other.hhtDetailFactureArtPK != null) || (this.hhtDetailFactureArtPK != null && !this.hhtDetailFactureArtPK.equals(other.hhtDetailFactureArtPK)));
    }

    @Override
    public String toString() {
        return "com.example.demo.HhtDetailFactureArt[ hhtDetailFactureArtPK=" + hhtDetailFactureArtPK + " ]";
    }

}
