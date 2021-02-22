package com.bracongo.mailexport.data;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author vr.kenfack
 */
@Entity
@Table(name = "REP_TAUX_CHANGE")
public class RepTauxChange implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    
    @Column(name = "DATE")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date date;
    
    @Column(name = "USD_CDF")
    private double usdCdf;
    
    @Column(name = "EURO_CDF")
    private double euroCdf;
    
    @Column(name = "EURO_USD")
    private double euroUsd;
    
    @Column(name = "USD_CDF_INFORMEL")
    private double usdCdfInformel;
    
    @Column(name = "USD_CDF_VENDEUR")
    private double usdCdfVendeur;
    
    @Column(name = "USD_CDF_ACHETEUR")
    private double ussdCdfAcheteur;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getUsdCdf() {
        return usdCdf;
    }

    public void setUsdCdf(double usdCdf) {
        this.usdCdf = usdCdf;
    }

    public double getEuroCdf() {
        return euroCdf;
    }

    public void setEuroCdf(double euroCdf) {
        this.euroCdf = euroCdf;
    }

    public double getEuroUsd() {
        return euroUsd;
    }

    public void setEuroUsd(double euroUsd) {
        this.euroUsd = euroUsd;
    }

    public double getUsdCdfInformel() {
        return usdCdfInformel;
    }

    public void setUsdCdfInformel(double usdCdfInformel) {
        this.usdCdfInformel = usdCdfInformel;
    }

    public double getUsdCdfVendeur() {
        return usdCdfVendeur;
    }

    public void setUsdCdfVendeur(double usdCdfVendeur) {
        this.usdCdfVendeur = usdCdfVendeur;
    }

    public double getUssdCdfAcheteur() {
        return ussdCdfAcheteur;
    }

    public void setUssdCdfAcheteur(double ussdCdfAcheteur) {
        this.ussdCdfAcheteur = ussdCdfAcheteur;
    }
    
    
    
}
