package com.bracongo.mailexport.data;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author vr.kenfack
 */
@Entity
@Table(name = "REP_OBJECTIF_PRODUIT_INFOBI")
public class RepObjectifProduitInfoBi implements Serializable{
    
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RepObjectifProduitInfoBiPK repObjectifProduitInfoBiPK;
    
    @Column(name = "CODAR")
    private String codar;
    
    @Column(name = "BUDGET")
    private double budget;
    

    public String getCodar() {
        return codar;
    }

    public void setCodar(String codar) {
        this.codar = codar;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public int getMois(){
        return repObjectifProduitInfoBiPK.getMois();
    }
    
    public String getCodeInfoBi(){
        return repObjectifProduitInfoBiPK.getCodeInfoBi();
    }
    
    public String getCodeCd(){
        return repObjectifProduitInfoBiPK.getCodeCd();
    }
    
    public int getAnne(){
        return repObjectifProduitInfoBiPK.getAnnee();
    }
    
    public void setRepObjectifProduitInfoBiPK(RepObjectifProduitInfoBiPK repObjectifProduitInfoBiPK) {
        this.repObjectifProduitInfoBiPK = repObjectifProduitInfoBiPK;
    }

    @Override
    public String toString() {
        return "RepObjectifProduitInfoBi{" + "repObjectifProduitInfoBiPK=" + repObjectifProduitInfoBiPK + ", codar=" + codar + ", budget=" + budget + '}';
    }
   
    
}
