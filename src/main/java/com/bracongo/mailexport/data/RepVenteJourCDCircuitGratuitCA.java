package com.bracongo.mailexport.data;

import java.io.Serializable;
import java.util.Date;
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
@Table(name = "REP_VENTE_CD_CIRCUIT_JOUR_GRATUIT_CA", schema = "dbo")
public class RepVenteJourCDCircuitGratuitCA implements Serializable{
    
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "HECTO")
    private double hecto;

    @Column(name = "CASIER")
    private double casier;

    @Column(name = "FAMILLE")
    private String famille;

    @Column(name = "MOIS")
    private int mois;

    @Column(name = "JOUR")
    private int jour;

    @Column(name = "ANNEE")
    private int annee;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "DATE_LONGUE")
    private Date dateLongue;
    
    @Column(name = "CODE_CD")
    private int codeCD;
    
    @Column(name = "CODE_CIRCUIT")
    private String codeCircuit;
    
    @Column(name = "NOM_PRODUIT")
    private String nomProduit;
    
    @Column(name = "CODARS")
    private String codars;
    
    @Column(name = "PRIX_HT")
    private double prixHt;
    
    @Column(name = "PRIX_TTC")
    private double prixTtc;
    
    @Column(name = "TTS")
    private String tts;
    
    @Column(name = "QUANTITE_RP2LI")
    private double quantiteRp2li;
    
    @Column(name = "HECTO_RP2LI")
    private double hectoRp2li;
    
    @Column(name = "SENS")
    private String sens;
    
    @Column(name = "TAXAR")
    private String taxar;
    
    @Column(name = "NSCHTX")
    private String nschtx;
    
    @Column(name = "NTD")
    private int ntd;
    
    @Column(name = "TYPST2")
    private String typst2;
    
    @Column(name = "SS")
    private String ss;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getHecto() {
        return hecto;
    }

    public void setHecto(double hecto) {
        this.hecto = hecto;
    }

    public double getCasier() {
        return casier;
    }

    public void setCasier(double casier) {
        this.casier = casier;
    }

    public String getFamille() {
        return famille;
    }

    public void setFamille(String famille) {
        this.famille = famille;
    }

    public int getMois() {
        return mois;
    }

    public void setMois(int mois) {
        this.mois = mois;
    }

    public int getJour() {
        return jour;
    }

    public void setJour(int jour) {
        this.jour = jour;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public Date getDateLongue() {
        return dateLongue;
    }

    public void setDateLongue(Date dateLongue) {
        this.dateLongue = dateLongue;
    }

    public int getCodeCD() {
        return codeCD;
    }

    public void setCodeCD(int codeCD) {
        this.codeCD = codeCD;
    }

    public String getCodeCircuit() {
        return codeCircuit;
    }

    public void setCodeCircuit(String codeCircuit) {
        this.codeCircuit = codeCircuit;
    }

    public String getNomProduit() {
        return nomProduit;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    public String getCodars() {
        return codars;
    }

    public void setCodars(String codars) {
        this.codars = codars;
    }

    public double getPrixHt() {
        return prixHt;
    }

    public void setPrixHt(double prixHt) {
        this.prixHt = prixHt;
    }

    public double getPrixTtc() {
        return prixTtc;
    }

    public void setPrixTtc(double prixTtc) {
        this.prixTtc = prixTtc;
    }

    public String getTts() {
        return tts;
    }

    public void setTts(String tts) {
        this.tts = tts;
    }

    public double getQuantiteRp2li() {
        return quantiteRp2li;
    }

    public void setQuantiteRp2li(double quantiteRp2li) {
        this.quantiteRp2li = quantiteRp2li;
    }

    public double getHectoRp2li() {
        return hectoRp2li;
    }

    public void setHectoRp2li(double hectoRp2li) {
        this.hectoRp2li = hectoRp2li;
    }

    public String getSens() {
        return sens;
    }

    public void setSens(String sens) {
        this.sens = sens;
    }

    public String getTaxar() {
        return taxar;
    }

    public void setTaxar(String taxar) {
        this.taxar = taxar;
    }

    public String getNschtx() {
        return nschtx;
    }

    public void setNschtx(String nschtx) {
        this.nschtx = nschtx;
    }

    public int getNtd() {
        return ntd;
    }

    public void setNtd(int ntd) {
        this.ntd = ntd;
    }

    public String getTypst2() {
        return typst2;
    }

    public void setTypst2(String typst2) {
        this.typst2 = typst2;
    }

    public String getSs() {
        return ss;
    }

    public void setSs(String ss) {
        this.ss = ss;
    }
    

    @Override
    public String toString() {
        return "VenteJourCDCircuitGratuitCA{" + "id=" + id + ", hecto=" + hecto + ", casier=" + casier + ", famille=" + famille + ", mois=" + mois + ", jour=" + jour + ", annee=" + annee + ", dateLongue=" + dateLongue + ", codeCD=" + codeCD + ", codeCircuit=" + codeCircuit + ", nomProduit=" + nomProduit + ", codars=" + codars + ", prixHt=" + prixHt + ", prixTtc=" + prixTtc + ", tts=" + tts + '}';
    }
    
}
