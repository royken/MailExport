/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bracongo.mailexport.data;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author vr.kenfack
 */
@Entity
@Table(name = "Tb_Article")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TbArticle.findAll", query = "SELECT t FROM TbArticle t"),
    @NamedQuery(name = "TbArticle.findByArtCodart", query = "SELECT t FROM TbArticle t WHERE t.artCodart = :artCodart"),
    @NamedQuery(name = "TbArticle.findByArtCodars", query = "SELECT t FROM TbArticle t WHERE t.artCodars = :artCodars"),
    @NamedQuery(name = "TbArticle.findByArtDesign", query = "SELECT t FROM TbArticle t WHERE t.artDesign = :artDesign"),
    @NamedQuery(name = "TbArticle.findByArtGamme", query = "SELECT t FROM TbArticle t WHERE t.artGamme = :artGamme"),
    @NamedQuery(name = "TbArticle.findByArtFamille", query = "SELECT t FROM TbArticle t WHERE t.artFamille = :artFamille"),
    @NamedQuery(name = "TbArticle.findByArtTaille", query = "SELECT t FROM TbArticle t WHERE t.artTaille = :artTaille"),
    @NamedQuery(name = "TbArticle.findByArtTyprod", query = "SELECT t FROM TbArticle t WHERE t.artTyprod = :artTyprod"),
    @NamedQuery(name = "TbArticle.findByArtCodemb", query = "SELECT t FROM TbArticle t WHERE t.artCodemb = :artCodemb"),
    @NamedQuery(name = "TbArticle.findByArtHectol", query = "SELECT t FROM TbArticle t WHERE t.artHectol = :artHectol"),
    @NamedQuery(name = "TbArticle.findByCalculDN", query = "SELECT t FROM TbArticle t WHERE t.calculDN = :calculDN"),
    @NamedQuery(name = "TbArticle.findByPu", query = "SELECT t FROM TbArticle t WHERE t.pu = :pu")})
public class TbArticle implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ART_CODART")
    private String artCodart;
    @Column(name = "ART_CODARS")
    private String artCodars;
    @Column(name = "ART_DESIGN")
    private String artDesign;
    @Column(name = "ART_GAMME")
    private String artGamme;
    @Column(name = "ART_FAMILLE")
    private String artFamille;
    @Column(name = "ART_TAILLE")
    private Short artTaille;
    @Column(name = "ART_TYPROD")
    private String artTyprod;
    @Column(name = "ART_CODEMB")
    private Short artCodemb;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "ART_HECTOL")
    private Float artHectol;
    @Column(name = "Calcul_DN")
    private Boolean calculDN;
    @Column(name = "PU")
    private BigInteger pu;

    public TbArticle() {
    }

    public TbArticle(String artCodart) {
        this.artCodart = artCodart;
    }

    public String getArtCodart() {
        return artCodart;
    }

    public void setArtCodart(String artCodart) {
        this.artCodart = artCodart;
    }

    public String getArtCodars() {
        return artCodars;
    }

    public void setArtCodars(String artCodars) {
        this.artCodars = artCodars;
    }

    public String getArtDesign() {
        return artDesign;
    }

    public void setArtDesign(String artDesign) {
        this.artDesign = artDesign;
    }

    public String getArtGamme() {
        return artGamme;
    }

    public void setArtGamme(String artGamme) {
        this.artGamme = artGamme;
    }

    public String getArtFamille() {
        return artFamille;
    }

    public void setArtFamille(String artFamille) {
        this.artFamille = artFamille;
    }

    public Short getArtTaille() {
        return artTaille;
    }

    public void setArtTaille(Short artTaille) {
        this.artTaille = artTaille;
    }

    public String getArtTyprod() {
        return artTyprod;
    }

    public void setArtTyprod(String artTyprod) {
        this.artTyprod = artTyprod;
    }

    public Short getArtCodemb() {
        return artCodemb;
    }

    public void setArtCodemb(Short artCodemb) {
        this.artCodemb = artCodemb;
    }

    public Float getArtHectol() {
        return artHectol;
    }

    public void setArtHectol(Float artHectol) {
        this.artHectol = artHectol;
    }

    public Boolean getCalculDN() {
        return calculDN;
    }

    public void setCalculDN(Boolean calculDN) {
        this.calculDN = calculDN;
    }

    public BigInteger getPu() {
        return pu;
    }

    public void setPu(BigInteger pu) {
        this.pu = pu;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (artCodart != null ? artCodart.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TbArticle)) {
            return false;
        }
        TbArticle other = (TbArticle) object;
        if ((this.artCodart == null && other.artCodart != null) || (this.artCodart != null && !this.artCodart.equals(other.artCodart))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bracongo.mailexport.data.TbArticle[ artCodart=" + artCodart + " ]";
    }
    
}
