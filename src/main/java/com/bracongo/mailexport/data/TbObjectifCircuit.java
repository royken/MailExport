/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bracongo.mailexport.data;

import java.io.Serializable;
import javax.persistence.Basic;
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
@Table(name = "Tb_ObjectifCircuit")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TbObjectifCircuit.findAll", query = "SELECT t FROM TbObjectifCircuit t")
    , @NamedQuery(name = "TbObjectifCircuit.findByObjCirc", query = "SELECT t FROM TbObjectifCircuit t WHERE t.tbObjectifCircuitPK.objCirc = :objCirc")
    , @NamedQuery(name = "TbObjectifCircuit.findByObjArro", query = "SELECT t FROM TbObjectifCircuit t WHERE t.objArro = :objArro")
    , @NamedQuery(name = "TbObjectifCircuit.findByObjCodart", query = "SELECT t FROM TbObjectifCircuit t WHERE t.tbObjectifCircuitPK.objCodart = :objCodart")
    , @NamedQuery(name = "TbObjectifCircuit.findByObjCodars", query = "SELECT t FROM TbObjectifCircuit t WHERE t.objCodars = :objCodars")
    , @NamedQuery(name = "TbObjectifCircuit.findByObjVolu", query = "SELECT t FROM TbObjectifCircuit t WHERE t.objVolu = :objVolu")
    , @NamedQuery(name = "TbObjectifCircuit.findByObjMois", query = "SELECT t FROM TbObjectifCircuit t WHERE t.tbObjectifCircuitPK.objMois = :objMois")
    , @NamedQuery(name = "TbObjectifCircuit.findByObjAnnee", query = "SELECT t FROM TbObjectifCircuit t WHERE t.tbObjectifCircuitPK.objAnnee = :objAnnee")})
public class TbObjectifCircuit implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TbObjectifCircuitPK tbObjectifCircuitPK;
    @Basic(optional = false)
    @Column(name = "OBJ_ARRO")
    private String objArro;
    @Basic(optional = false)
    @Column(name = "OBJ_CODARS")
    private String objCodars;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "OBJ_VOLU")
    private Float objVolu;

    public TbObjectifCircuit() {
    }

    public TbObjectifCircuit(TbObjectifCircuitPK tbObjectifCircuitPK) {
        this.tbObjectifCircuitPK = tbObjectifCircuitPK;
    }

    public TbObjectifCircuit(TbObjectifCircuitPK tbObjectifCircuitPK, String objArro, String objCodars) {
        this.tbObjectifCircuitPK = tbObjectifCircuitPK;
        this.objArro = objArro;
        this.objCodars = objCodars;
    }

    public TbObjectifCircuit(String objCirc, String objCodart, short objMois, short objAnnee) {
        this.tbObjectifCircuitPK = new TbObjectifCircuitPK(objCirc, objCodart, objMois, objAnnee);
    }

    public TbObjectifCircuitPK getTbObjectifCircuitPK() {
        return tbObjectifCircuitPK;
    }

    public void setTbObjectifCircuitPK(TbObjectifCircuitPK tbObjectifCircuitPK) {
        this.tbObjectifCircuitPK = tbObjectifCircuitPK;
    }

    public String getObjArro() {
        return objArro;
    }

    public void setObjArro(String objArro) {
        this.objArro = objArro;
    }

    public String getObjCodars() {
        return objCodars;
    }

    public void setObjCodars(String objCodars) {
        this.objCodars = objCodars;
    }

    public Float getObjVolu() {
        return objVolu;
    }

    public void setObjVolu(Float objVolu) {
        this.objVolu = objVolu;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tbObjectifCircuitPK != null ? tbObjectifCircuitPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TbObjectifCircuit)) {
            return false;
        }
        TbObjectifCircuit other = (TbObjectifCircuit) object;
        if ((this.tbObjectifCircuitPK == null && other.tbObjectifCircuitPK != null) || (this.tbObjectifCircuitPK != null && !this.tbObjectifCircuitPK.equals(other.tbObjectifCircuitPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bracongo.bracongoapi.entities.TbObjectifCircuit[ tbObjectifCircuitPK=" + tbObjectifCircuitPK + " ]";
    }
    
}
