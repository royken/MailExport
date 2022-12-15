/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bracongo.mailexport.data;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "REP_STOCK_CD_JOUR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RepStockCdJour.findAll", query = "SELECT r FROM RepStockCdJour r"),
    @NamedQuery(name = "RepStockCdJour.findById", query = "SELECT r FROM RepStockCdJour r WHERE r.id = :id"),
    @NamedQuery(name = "RepStockCdJour.findByAnnee", query = "SELECT r FROM RepStockCdJour r WHERE r.annee = :annee"),
    @NamedQuery(name = "RepStockCdJour.findByArticle", query = "SELECT r FROM RepStockCdJour r WHERE r.article = :article"),
    @NamedQuery(name = "RepStockCdJour.findByCodeCd", query = "SELECT r FROM RepStockCdJour r WHERE r.codeCd = :codeCd"),
    @NamedQuery(name = "RepStockCdJour.findByJour", query = "SELECT r FROM RepStockCdJour r WHERE r.jour = :jour"),
    @NamedQuery(name = "RepStockCdJour.findByLibelle", query = "SELECT r FROM RepStockCdJour r WHERE r.libelle = :libelle"),
    @NamedQuery(name = "RepStockCdJour.findByMois", query = "SELECT r FROM RepStockCdJour r WHERE r.mois = :mois"),
    @NamedQuery(name = "RepStockCdJour.findByNomCd", query = "SELECT r FROM RepStockCdJour r WHERE r.nomCd = :nomCd"),
    @NamedQuery(name = "RepStockCdJour.findByIsPlein", query = "SELECT r FROM RepStockCdJour r WHERE r.isPlein = :isPlein"),
    @NamedQuery(name = "RepStockCdJour.findByQuantiteMagasin", query = "SELECT r FROM RepStockCdJour r WHERE r.quantiteMagasin = :quantiteMagasin"),
    @NamedQuery(name = "RepStockCdJour.findByQuantiteVehicule", query = "SELECT r FROM RepStockCdJour r WHERE r.quantiteVehicule = :quantiteVehicule"),
    @NamedQuery(name = "RepStockCdJour.findByCodars", query = "SELECT r FROM RepStockCdJour r WHERE r.codars = :codars")})
public class RepStockCdJour implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "ANNEE")
    private Integer annee;
    @Column(name = "ARTICLE")
    private String article;
    @Column(name = "CODE_CD")
    private Integer codeCd;
    @Column(name = "JOUR")
    private Integer jour;
    @Column(name = "LIBELLE")
    private String libelle;
    @Column(name = "MOIS")
    private Integer mois;
    @Column(name = "NOM_CD")
    private String nomCd;
    @Column(name = "IS_PLEIN")
    private Boolean isPlein;
    @Column(name = "QUANTITE_MAGASIN")
    private Integer quantiteMagasin;
    @Column(name = "QUANTITE_VEHICULE")
    private Integer quantiteVehicule;
    @Column(name = "CODARS")
    private String codars;

    public RepStockCdJour() {
    }

    public RepStockCdJour(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAnnee() {
        return annee;
    }

    public void setAnnee(Integer annee) {
        this.annee = annee;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public Integer getCodeCd() {
        return codeCd;
    }

    public void setCodeCd(Integer codeCd) {
        this.codeCd = codeCd;
    }

    public Integer getJour() {
        return jour;
    }

    public void setJour(Integer jour) {
        this.jour = jour;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Integer getMois() {
        return mois;
    }

    public void setMois(Integer mois) {
        this.mois = mois;
    }

    public String getNomCd() {
        return nomCd;
    }

    public void setNomCd(String nomCd) {
        this.nomCd = nomCd;
    }

    public Boolean getIsPlein() {
        return isPlein;
    }

    public void setIsPlein(Boolean isPlein) {
        this.isPlein = isPlein;
    }

    public Integer getQuantiteMagasin() {
        return quantiteMagasin;
    }

    public void setQuantiteMagasin(Integer quantiteMagasin) {
        this.quantiteMagasin = quantiteMagasin;
    }

    public Integer getQuantiteVehicule() {
        return quantiteVehicule;
    }

    public void setQuantiteVehicule(Integer quantiteVehicule) {
        this.quantiteVehicule = quantiteVehicule;
    }

    public String getCodars() {
        return codars;
    }

    public void setCodars(String codars) {
        this.codars = codars;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RepStockCdJour)) {
            return false;
        }
        RepStockCdJour other = (RepStockCdJour) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bracongo.mailexport.data.RepStockCdJour[ id=" + id + " ]";
    }
    
}
