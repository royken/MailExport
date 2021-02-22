/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bracongo.mailexport.data;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author vr.kenfack
 */
@Entity
@Table(name = "REP_VENTE_RATEE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RepVenteRatee.findAll", query = "SELECT r FROM RepVenteRatee r"),
    @NamedQuery(name = "RepVenteRatee.findById", query = "SELECT r FROM RepVenteRatee r WHERE r.id = :id"),
    @NamedQuery(name = "RepVenteRatee.findByLibelle", query = "SELECT r FROM RepVenteRatee r WHERE r.libelle = :libelle"),
    @NamedQuery(name = "RepVenteRatee.findByNomCd", query = "SELECT r FROM RepVenteRatee r WHERE r.nomCd = :nomCd"),
    @NamedQuery(name = "RepVenteRatee.findByCircuit", query = "SELECT r FROM RepVenteRatee r WHERE r.circuit = :circuit"),
    @NamedQuery(name = "RepVenteRatee.findByCodeArt", query = "SELECT r FROM RepVenteRatee r WHERE r.codeArt = :codeArt"),
    @NamedQuery(name = "RepVenteRatee.findByCodeCd", query = "SELECT r FROM RepVenteRatee r WHERE r.codeCd = :codeCd"),
    @NamedQuery(name = "RepVenteRatee.findByCodeClt", query = "SELECT r FROM RepVenteRatee r WHERE r.codeClt = :codeClt"),
    @NamedQuery(name = "RepVenteRatee.findByCodeFact", query = "SELECT r FROM RepVenteRatee r WHERE r.codeFact = :codeFact"),
    @NamedQuery(name = "RepVenteRatee.findByCodeRoute", query = "SELECT r FROM RepVenteRatee r WHERE r.codeRoute = :codeRoute"),
    @NamedQuery(name = "RepVenteRatee.findByCodeTour", query = "SELECT r FROM RepVenteRatee r WHERE r.codeTour = :codeTour"),
    @NamedQuery(name = "RepVenteRatee.findByCodeVendeur", query = "SELECT r FROM RepVenteRatee r WHERE r.codeVendeur = :codeVendeur"),
    @NamedQuery(name = "RepVenteRatee.findByDate", query = "SELECT r FROM RepVenteRatee r WHERE r.date = :date"),
    @NamedQuery(name = "RepVenteRatee.findByHectoRatee", query = "SELECT r FROM RepVenteRatee r WHERE r.hectoRatee = :hectoRatee"),
    @NamedQuery(name = "RepVenteRatee.findByQteAchetee", query = "SELECT r FROM RepVenteRatee r WHERE r.qteAchetee = :qteAchetee"),
    @NamedQuery(name = "RepVenteRatee.findByQteRatee", query = "SELECT r FROM RepVenteRatee r WHERE r.qteRatee = :qteRatee"),
    @NamedQuery(name = "RepVenteRatee.findByQteSouhaitee", query = "SELECT r FROM RepVenteRatee r WHERE r.qteSouhaitee = :qteSouhaitee"),
    @NamedQuery(name = "RepVenteRatee.findByQteStock", query = "SELECT r FROM RepVenteRatee r WHERE r.qteStock = :qteStock"),
    @NamedQuery(name = "RepVenteRatee.findByVille", query = "SELECT r FROM RepVenteRatee r WHERE r.ville = :ville")})
public class RepVenteRatee implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "LIBELLE")
    private String libelle;
    @Column(name = "NOM_CD")
    private String nomCd;
    @Column(name = "CIRCUIT")
    private String circuit;
    @Column(name = "CODE_ART")
    private String codeArt;
    @Column(name = "CODE_CD")
    private String codeCd;
    @Column(name = "CODE_CLT")
    private String codeClt;
    @Column(name = "CODE_FACT")
    private String codeFact;
    @Column(name = "CODE_ROUTE")
    private String codeRoute;
    @Column(name = "CODE_TOUR")
    private String codeTour;
    @Column(name = "CODE_VENDEUR")
    private String codeVendeur;
    @Column(name = "DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "HECTO_RATEE")
    private Double hectoRatee;
    @Column(name = "QTE_ACHETEE")
    private Integer qteAchetee;
    @Column(name = "QTE_RATEE")
    private Integer qteRatee;
    @Column(name = "QTE_SOUHAITEE")
    private Integer qteSouhaitee;
    @Column(name = "QTE_STOCK")
    private Integer qteStock;
    @Column(name = "VILLE")
    private String ville;
    @Column(name = "BL")
    private String bl;

    public RepVenteRatee() {
    }

    public RepVenteRatee(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getNomCd() {
        return nomCd;
    }

    public void setNomCd(String nomCd) {
        this.nomCd = nomCd;
    }

    public String getCircuit() {
        return circuit;
    }

    public void setCircuit(String circuit) {
        this.circuit = circuit;
    }

    public String getCodeArt() {
        return codeArt;
    }

    public void setCodeArt(String codeArt) {
        this.codeArt = codeArt;
    }

    public String getCodeCd() {
        return codeCd;
    }

    public void setCodeCd(String codeCd) {
        this.codeCd = codeCd;
    }

    public String getCodeClt() {
        return codeClt;
    }

    public void setCodeClt(String codeClt) {
        this.codeClt = codeClt;
    }

    public String getCodeFact() {
        return codeFact;
    }

    public void setCodeFact(String codeFact) {
        this.codeFact = codeFact;
    }

    public String getCodeRoute() {
        return codeRoute;
    }

    public void setCodeRoute(String codeRoute) {
        this.codeRoute = codeRoute;
    }

    public String getCodeTour() {
        return codeTour;
    }

    public void setCodeTour(String codeTour) {
        this.codeTour = codeTour;
    }

    public String getCodeVendeur() {
        return codeVendeur;
    }

    public void setCodeVendeur(String codeVendeur) {
        this.codeVendeur = codeVendeur;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getHectoRatee() {
        return hectoRatee;
    }

    public void setHectoRatee(Double hectoRatee) {
        this.hectoRatee = hectoRatee;
    }

    public Integer getQteAchetee() {
        return qteAchetee;
    }

    public void setQteAchetee(Integer qteAchetee) {
        this.qteAchetee = qteAchetee;
    }

    public Integer getQteRatee() {
        return qteRatee;
    }

    public void setQteRatee(Integer qteRatee) {
        this.qteRatee = qteRatee;
    }

    public Integer getQteSouhaitee() {
        return qteSouhaitee;
    }

    public void setQteSouhaitee(Integer qteSouhaitee) {
        this.qteSouhaitee = qteSouhaitee;
    }

    public Integer getQteStock() {
        return qteStock;
    }

    public void setQteStock(Integer qteStock) {
        this.qteStock = qteStock;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
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
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RepVenteRatee)) {
            return false;
        }
        RepVenteRatee other = (RepVenteRatee) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "RepVenteRatee{" + "libelle=" + libelle + ", nomCd=" + nomCd + ", circuit=" + circuit + ", codeArt=" + codeArt + ", codeCd=" + codeCd + ", codeClt=" + codeClt + ", codeFact=" + codeFact + ", codeRoute=" + codeRoute + ", codeTour=" + codeTour + ", codeVendeur=" + codeVendeur + ", date=" + date + ", hectoRatee=" + hectoRatee + ", qteAchetee=" + qteAchetee + ", qteRatee=" + qteRatee + ", qteSouhaitee=" + qteSouhaitee + ", qteStock=" + qteStock + ", ville=" + ville + ", bl=" + bl + '}';
    }

    
    
}
