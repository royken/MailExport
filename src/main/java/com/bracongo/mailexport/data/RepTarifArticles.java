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
@Table(name = "REP_TARIF_ARTICLES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RepTarifArticles.findAll", query = "SELECT r FROM RepTarifArticles r"),
    @NamedQuery(name = "RepTarifArticles.findById", query = "SELECT r FROM RepTarifArticles r WHERE r.id = :id"),
    @NamedQuery(name = "RepTarifArticles.findByAnneeTarif", query = "SELECT r FROM RepTarifArticles r WHERE r.anneeTarif = :anneeTarif"),
    @NamedQuery(name = "RepTarifArticles.findByCodars", query = "SELECT r FROM RepTarifArticles r WHERE r.codars = :codars"),
    @NamedQuery(name = "RepTarifArticles.findByCodeTarif", query = "SELECT r FROM RepTarifArticles r WHERE r.codeTarif = :codeTarif"),
    @NamedQuery(name = "RepTarifArticles.findByDateTarif", query = "SELECT r FROM RepTarifArticles r WHERE r.dateTarif = :dateTarif"),
    @NamedQuery(name = "RepTarifArticles.findByJourTarif", query = "SELECT r FROM RepTarifArticles r WHERE r.jourTarif = :jourTarif"),
    @NamedQuery(name = "RepTarifArticles.findByMoisTarif", query = "SELECT r FROM RepTarifArticles r WHERE r.moisTarif = :moisTarif"),
    @NamedQuery(name = "RepTarifArticles.findByTarif", query = "SELECT r FROM RepTarifArticles r WHERE r.tarif = :tarif")})
public class RepTarifArticles implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "ANNEE_TARIF")
    private Integer anneeTarif;
    @Column(name = "CODARS")
    private String codars;
    @Column(name = "CODE_TARIF")
    private String codeTarif;
    @Column(name = "DATE_TARIF")
    @Temporal(TemporalType.DATE)
    private Date dateTarif;
    @Column(name = "JOUR_TARIF")
    private Integer jourTarif;
    @Column(name = "MOIS_TARIF")
    private Integer moisTarif;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "TARIF")
    private Double tarif;

    public RepTarifArticles() {
    }

    public RepTarifArticles(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAnneeTarif() {
        return anneeTarif;
    }

    public void setAnneeTarif(Integer anneeTarif) {
        this.anneeTarif = anneeTarif;
    }

    public String getCodars() {
        return codars;
    }

    public void setCodars(String codars) {
        this.codars = codars;
    }

    public String getCodeTarif() {
        return codeTarif;
    }

    public void setCodeTarif(String codeTarif) {
        this.codeTarif = codeTarif;
    }

    public Date getDateTarif() {
        return dateTarif;
    }

    public void setDateTarif(Date dateTarif) {
        this.dateTarif = dateTarif;
    }

    public Integer getJourTarif() {
        return jourTarif;
    }

    public void setJourTarif(Integer jourTarif) {
        this.jourTarif = jourTarif;
    }

    public Integer getMoisTarif() {
        return moisTarif;
    }

    public void setMoisTarif(Integer moisTarif) {
        this.moisTarif = moisTarif;
    }

    public Double getTarif() {
        return tarif;
    }

    public void setTarif(Double tarif) {
        this.tarif = tarif;
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
        if (!(object instanceof RepTarifArticles)) {
            return false;
        }
        RepTarifArticles other = (RepTarifArticles) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "RepTarifArticles{" + "id=" + id + ", anneeTarif=" + anneeTarif + ", codars=" + codars + ", codeTarif=" + codeTarif + ", dateTarif=" + dateTarif + ", jourTarif=" + jourTarif + ", moisTarif=" + moisTarif + ", tarif=" + tarif + '}';
    }

    
    
}
