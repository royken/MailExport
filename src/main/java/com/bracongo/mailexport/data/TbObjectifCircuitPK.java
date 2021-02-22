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
 * @author Valmy Roi Kenfack <vr.kenfack at bracongo.cd>
 */
@Embeddable
public class TbObjectifCircuitPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "OBJ_CIRC")
    private String objCirc;
    @Basic(optional = false)
    @Column(name = "OBJ_CODART")
    private String objCodart;
    @Basic(optional = false)
    @Column(name = "OBJ_MOIS")
    private short objMois;
    @Basic(optional = false)
    @Column(name = "OBJ_ANNEE")
    private short objAnnee;

    public TbObjectifCircuitPK() {
    }

    public TbObjectifCircuitPK(String objCirc, String objCodart, short objMois, short objAnnee) {
        this.objCirc = objCirc;
        this.objCodart = objCodart;
        this.objMois = objMois;
        this.objAnnee = objAnnee;
    }

    public String getObjCirc() {
        return objCirc;
    }

    public void setObjCirc(String objCirc) {
        this.objCirc = objCirc;
    }

    public String getObjCodart() {
        return objCodart;
    }

    public void setObjCodart(String objCodart) {
        this.objCodart = objCodart;
    }

    public short getObjMois() {
        return objMois;
    }

    public void setObjMois(short objMois) {
        this.objMois = objMois;
    }

    public short getObjAnnee() {
        return objAnnee;
    }

    public void setObjAnnee(short objAnnee) {
        this.objAnnee = objAnnee;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (objCirc != null ? objCirc.hashCode() : 0);
        hash += (objCodart != null ? objCodart.hashCode() : 0);
        hash += (int) objMois;
        hash += (int) objAnnee;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TbObjectifCircuitPK)) {
            return false;
        }
        TbObjectifCircuitPK other = (TbObjectifCircuitPK) object;
        if ((this.objCirc == null && other.objCirc != null) || (this.objCirc != null && !this.objCirc.equals(other.objCirc))) {
            return false;
        }
        if ((this.objCodart == null && other.objCodart != null) || (this.objCodart != null && !this.objCodart.equals(other.objCodart))) {
            return false;
        }
        if (this.objMois != other.objMois) {
            return false;
        }
        if (this.objAnnee != other.objAnnee) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bracongo.bracongoapi.entities.TbObjectifCircuitPK[ objCirc=" + objCirc + ", objCodart=" + objCodart + ", objMois=" + objMois + ", objAnnee=" + objAnnee + " ]";
    }
    
}
