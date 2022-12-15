package com.bracongo.mailexport.data.dto;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author vr.kenfack
 */
public class DegradationArticleDto implements Serializable{
    
    private String codars;
    
    private String desrat;
    
    private String argaDg;
    
    private String argtDg;
    
    private String codeCd;
    
    private Date dateDebut;
    
    private Date dateFin;
    
    private String rubrique;
    
    private double valeurDegradation;

    public DegradationArticleDto(String codars, String desrat, String argaDg, String argtDg, String codeCd, Date dateDebut, Date dateFin, String rubrique, double valeurDegradation) {
        this.codars = codars;
        this.desrat = desrat;
        this.argaDg = argaDg;
        this.codeCd = codeCd;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.rubrique = rubrique;
        this.valeurDegradation = valeurDegradation;
        this.argtDg = argtDg;
    }

    public DegradationArticleDto() {
    }
    
    
    
    

    public String getCodars() {
        return codars;
    }

    public void setCodars(String codars) {
        this.codars = codars;
    }

    public String getDesrat() {
        return desrat;
    }

    public void setDesrat(String desrat) {
        this.desrat = desrat;
    }

    public String getArgaDg() {
        return argaDg;
    }

    public void setArgaDg(String argaDg) {
        this.argaDg = argaDg;
    }

    public String getCodeCd() {
        return codeCd;
    }

    public void setCodeCd(String codeCd) {
        this.codeCd = codeCd;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public String getRubrique() {
        return rubrique;
    }

    public void setRubrique(String rubrique) {
        this.rubrique = rubrique;
    }

    public double getValeurDegradation() {
        return valeurDegradation;
    }

    public void setValeurDegradation(double valeurDegradation) {
        this.valeurDegradation = valeurDegradation;
    }

    public String getArgtDg() {
        return argtDg;
    }

    public void setArgtDg(String argtDg) {
        this.argtDg = argtDg;
    }
    
    

    @Override
    public String toString() {
        return "DegradationArticleDto{" + "codars=" + codars + ", desrat=" + desrat + ", argaDg=" + argaDg + ", codeCd=" + codeCd + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin + ", rubrique=" + rubrique + ", valeurDegradation=" + valeurDegradation + '}';
    }
    
    
    
}
