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
@Table(name = "Tb_CentreDistribution")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TbCentreDistribution.findAll", query = "SELECT t FROM TbCentreDistribution t"),
    @NamedQuery(name = "TbCentreDistribution.findByCdiCodecd", query = "SELECT t FROM TbCentreDistribution t WHERE t.cdiCodecd = :cdiCodecd"),
    @NamedQuery(name = "TbCentreDistribution.findByCdiNomcdi", query = "SELECT t FROM TbCentreDistribution t WHERE t.cdiNomcdi = :cdiNomcdi"),
    @NamedQuery(name = "TbCentreDistribution.findByCdiPosgeo", query = "SELECT t FROM TbCentreDistribution t WHERE t.cdiPosgeo = :cdiPosgeo"),
    @NamedQuery(name = "TbCentreDistribution.findByCdiVille", query = "SELECT t FROM TbCentreDistribution t WHERE t.cdiVille = :cdiVille"),
    @NamedQuery(name = "TbCentreDistribution.findByCdiJourcouv", query = "SELECT t FROM TbCentreDistribution t WHERE t.cdiJourcouv = :cdiJourcouv"),
    @NamedQuery(name = "TbCentreDistribution.findByCdiCapacitecd", query = "SELECT t FROM TbCentreDistribution t WHERE t.cdiCapacitecd = :cdiCapacitecd"),
    @NamedQuery(name = "TbCentreDistribution.findByCdiCdsigma", query = "SELECT t FROM TbCentreDistribution t WHERE t.cdiCdsigma = :cdiCdsigma"),
    @NamedQuery(name = "TbCentreDistribution.findByCdiCdtournesol", query = "SELECT t FROM TbCentreDistribution t WHERE t.cdiCdtournesol = :cdiCdtournesol"),
    @NamedQuery(name = "TbCentreDistribution.findByCdiStatut", query = "SELECT t FROM TbCentreDistribution t WHERE t.cdiStatut = :cdiStatut"),
    @NamedQuery(name = "TbCentreDistribution.findByCdiLettreTiers", query = "SELECT t FROM TbCentreDistribution t WHERE t.cdiLettreTiers = :cdiLettreTiers"),
    @NamedQuery(name = "TbCentreDistribution.findByCdiTiers", query = "SELECT t FROM TbCentreDistribution t WHERE t.cdiTiers = :cdiTiers"),
    @NamedQuery(name = "TbCentreDistribution.findByCdiCodetarif", query = "SELECT t FROM TbCentreDistribution t WHERE t.cdiCodetarif = :cdiCodetarif")})
public class TbCentreDistribution implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CDI_CODECD")
    private String cdiCodecd;
    @Column(name = "CDI_NOMCDI")
    private String cdiNomcdi;
    @Column(name = "CDI_POSGEO")
    private String cdiPosgeo;
    @Column(name = "CDI_VILLE")
    private String cdiVille;
    @Column(name = "CDI_JOURCOUV")
    private Short cdiJourcouv;
    @Column(name = "CDI_CAPACITECD")
    private Integer cdiCapacitecd;
    @Column(name = "CDI_CDSIGMA")
    private Boolean cdiCdsigma;
    @Column(name = "CDI_CDTOURNESOL")
    private Boolean cdiCdtournesol;
    @Column(name = "CDI_STATUT")
    private String cdiStatut;
    @Column(name = "CDI_LETTRE_TIERS")
    private String cdiLettreTiers;
    @Column(name = "CDI_TIERS")
    private String cdiTiers;
    @Column(name = "CDI_CODETARIF")
    private String cdiCodetarif;

    public TbCentreDistribution() {
    }

    public TbCentreDistribution(String cdiCodecd) {
        this.cdiCodecd = cdiCodecd;
    }

    public String getCdiCodecd() {
        return cdiCodecd;
    }

    public void setCdiCodecd(String cdiCodecd) {
        this.cdiCodecd = cdiCodecd;
    }

    public String getCdiNomcdi() {
        return cdiNomcdi;
    }

    public void setCdiNomcdi(String cdiNomcdi) {
        this.cdiNomcdi = cdiNomcdi;
    }

    public String getCdiPosgeo() {
        return cdiPosgeo;
    }

    public void setCdiPosgeo(String cdiPosgeo) {
        this.cdiPosgeo = cdiPosgeo;
    }

    public String getCdiVille() {
        return cdiVille;
    }

    public void setCdiVille(String cdiVille) {
        this.cdiVille = cdiVille;
    }

    public Short getCdiJourcouv() {
        return cdiJourcouv;
    }

    public void setCdiJourcouv(Short cdiJourcouv) {
        this.cdiJourcouv = cdiJourcouv;
    }

    public Integer getCdiCapacitecd() {
        return cdiCapacitecd;
    }

    public void setCdiCapacitecd(Integer cdiCapacitecd) {
        this.cdiCapacitecd = cdiCapacitecd;
    }

    public Boolean getCdiCdsigma() {
        return cdiCdsigma;
    }

    public void setCdiCdsigma(Boolean cdiCdsigma) {
        this.cdiCdsigma = cdiCdsigma;
    }

    public Boolean getCdiCdtournesol() {
        return cdiCdtournesol;
    }

    public void setCdiCdtournesol(Boolean cdiCdtournesol) {
        this.cdiCdtournesol = cdiCdtournesol;
    }

    public String getCdiStatut() {
        return cdiStatut;
    }

    public void setCdiStatut(String cdiStatut) {
        this.cdiStatut = cdiStatut;
    }

    public String getCdiLettreTiers() {
        return cdiLettreTiers;
    }

    public void setCdiLettreTiers(String cdiLettreTiers) {
        this.cdiLettreTiers = cdiLettreTiers;
    }

    public String getCdiTiers() {
        return cdiTiers;
    }

    public void setCdiTiers(String cdiTiers) {
        this.cdiTiers = cdiTiers;
    }

    public String getCdiCodetarif() {
        return cdiCodetarif;
    }

    public void setCdiCodetarif(String cdiCodetarif) {
        this.cdiCodetarif = cdiCodetarif;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cdiCodecd != null ? cdiCodecd.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TbCentreDistribution)) {
            return false;
        }
        TbCentreDistribution other = (TbCentreDistribution) object;
        if ((this.cdiCodecd == null && other.cdiCodecd != null) || (this.cdiCodecd != null && !this.cdiCodecd.equals(other.cdiCodecd))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TbCentreDistribution{" + "cdiCodecd=" + cdiCodecd + ", cdiNomcdi=" + cdiNomcdi + ", cdiPosgeo=" + cdiPosgeo + ", cdiVille=" + cdiVille + ", cdiJourcouv=" + cdiJourcouv + ", cdiCapacitecd=" + cdiCapacitecd + ", cdiCdsigma=" + cdiCdsigma + ", cdiCdtournesol=" + cdiCdtournesol + ", cdiStatut=" + cdiStatut + ", cdiLettreTiers=" + cdiLettreTiers + ", cdiTiers=" + cdiTiers + ", cdiCodetarif=" + cdiCodetarif + '}';
    }

    
    
}
