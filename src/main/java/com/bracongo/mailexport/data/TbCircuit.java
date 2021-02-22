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
@Table(name = "Tb_Circuit")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TbCircuit.findAll", query = "SELECT t FROM TbCircuit t"),
    @NamedQuery(name = "TbCircuit.findByCirCodcir", query = "SELECT t FROM TbCircuit t WHERE t.cirCodcir = :cirCodcir"),
    @NamedQuery(name = "TbCircuit.findByCirCodzon", query = "SELECT t FROM TbCircuit t WHERE t.cirCodzon = :cirCodzon"),
    @NamedQuery(name = "TbCircuit.findByCirCodsigma", query = "SELECT t FROM TbCircuit t WHERE t.cirCodsigma = :cirCodsigma"),
    @NamedQuery(name = "TbCircuit.findByCirMatpro", query = "SELECT t FROM TbCircuit t WHERE t.cirMatpro = :cirMatpro"),
    @NamedQuery(name = "TbCircuit.findByCirNomcir", query = "SELECT t FROM TbCircuit t WHERE t.cirNomcir = :cirNomcir"),
    @NamedQuery(name = "TbCircuit.findByCirIdvehi", query = "SELECT t FROM TbCircuit t WHERE t.cirIdvehi = :cirIdvehi")})
public class TbCircuit implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CIR_CODCIR")
    private String cirCodcir;
    @Column(name = "CIR_CODZON")
    private String cirCodzon;
    @Column(name = "CIR_CODSIGMA")
    private String cirCodsigma;
    @Column(name = "CIR_MATPRO")
    private String cirMatpro;
    @Column(name = "CIR_NOMCIR")
    private String cirNomcir;
    @Column(name = "CIR_IDVEHI")
    private String cirIdvehi;

    public TbCircuit() {
    }

    public TbCircuit(String cirCodcir) {
        this.cirCodcir = cirCodcir;
    }

    public String getCirCodcir() {
        return cirCodcir;
    }

    public void setCirCodcir(String cirCodcir) {
        this.cirCodcir = cirCodcir;
    }

    public String getCirCodzon() {
        return cirCodzon;
    }

    public void setCirCodzon(String cirCodzon) {
        this.cirCodzon = cirCodzon;
    }

    public String getCirCodsigma() {
        return cirCodsigma;
    }

    public void setCirCodsigma(String cirCodsigma) {
        this.cirCodsigma = cirCodsigma;
    }

    public String getCirMatpro() {
        return cirMatpro;
    }

    public void setCirMatpro(String cirMatpro) {
        this.cirMatpro = cirMatpro;
    }

    public String getCirNomcir() {
        return cirNomcir;
    }

    public void setCirNomcir(String cirNomcir) {
        this.cirNomcir = cirNomcir;
    }

    public String getCirIdvehi() {
        return cirIdvehi;
    }

    public void setCirIdvehi(String cirIdvehi) {
        this.cirIdvehi = cirIdvehi;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cirCodcir != null ? cirCodcir.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TbCircuit)) {
            return false;
        }
        TbCircuit other = (TbCircuit) object;
        if ((this.cirCodcir == null && other.cirCodcir != null) || (this.cirCodcir != null && !this.cirCodcir.equals(other.cirCodcir))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bracongo.mailexport.data.TbCircuit[ cirCodcir=" + cirCodcir + " ]";
    }
    
}
