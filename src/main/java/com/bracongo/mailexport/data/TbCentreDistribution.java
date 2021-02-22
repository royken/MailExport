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
    @NamedQuery(name = "TbCentreDistribution.findByCdiCapacitecd", query = "SELECT t FROM TbCentreDistribution t WHERE t.cdiCapacitecd = :cdiCapacitecd")})
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
        return "com.bracongo.bracongoapi.entities.TbCentreDistribution[ cdiCodecd=" + cdiCodecd + " ]";
    }
    
}
