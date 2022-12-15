/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bracongo.mailexport.data;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
@Table(name = "HHT_FACTURE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HhtFacture.findAll", query = "SELECT h FROM HhtFacture h"),
    @NamedQuery(name = "HhtFacture.findByCodeFact", query = "SELECT h FROM HhtFacture h WHERE h.hhtFacturePK.codeFact = :codeFact"),
    @NamedQuery(name = "HhtFacture.findByDateFact", query = "SELECT h FROM HhtFacture h WHERE h.dateFact = :dateFact"),
    @NamedQuery(name = "HhtFacture.findByCodeRoute", query = "SELECT h FROM HhtFacture h WHERE h.codeRoute = :codeRoute"),
    @NamedQuery(name = "HhtFacture.findByCodeTour", query = "SELECT h FROM HhtFacture h WHERE h.codeTour = :codeTour"),
    @NamedQuery(name = "HhtFacture.findByCodeClt", query = "SELECT h FROM HhtFacture h WHERE h.codeClt = :codeClt"),
    @NamedQuery(name = "HhtFacture.findByEspece", query = "SELECT h FROM HhtFacture h WHERE h.espece = :espece"),
    @NamedQuery(name = "HhtFacture.findByCheque", query = "SELECT h FROM HhtFacture h WHERE h.cheque = :cheque"),
    @NamedQuery(name = "HhtFacture.findByCb", query = "SELECT h FROM HhtFacture h WHERE h.cb = :cb"),
    @NamedQuery(name = "HhtFacture.findByRistourneCumule", query = "SELECT h FROM HhtFacture h WHERE h.ristourneCumule = :ristourneCumule"),
    @NamedQuery(name = "HhtFacture.findByCredit", query = "SELECT h FROM HhtFacture h WHERE h.credit = :credit"),
    @NamedQuery(name = "HhtFacture.findByTotalLiquide", query = "SELECT h FROM HhtFacture h WHERE h.totalLiquide = :totalLiquide"),
    @NamedQuery(name = "HhtFacture.findByTotalTva", query = "SELECT h FROM HhtFacture h WHERE h.totalTva = :totalTva"),
    @NamedQuery(name = "HhtFacture.findByTotalEmb", query = "SELECT h FROM HhtFacture h WHERE h.totalEmb = :totalEmb"),
    @NamedQuery(name = "HhtFacture.findByTotalRistourneEncour", query = "SELECT h FROM HhtFacture h WHERE h.totalRistourneEncour = :totalRistourneEncour"),
    @NamedQuery(name = "HhtFacture.findByTotalPointsFid", query = "SELECT h FROM HhtFacture h WHERE h.totalPointsFid = :totalPointsFid"),
    @NamedQuery(name = "HhtFacture.findByCodeHit", query = "SELECT h FROM HhtFacture h WHERE h.codeHit = :codeHit"),
    @NamedQuery(name = "HhtFacture.findByTaxePrelevement", query = "SELECT h FROM HhtFacture h WHERE h.taxePrelevement = :taxePrelevement"),
    @NamedQuery(name = "HhtFacture.findByTaxeTimbreFisc", query = "SELECT h FROM HhtFacture h WHERE h.taxeTimbreFisc = :taxeTimbreFisc"),
    @NamedQuery(name = "HhtFacture.findByFraisLivraison", query = "SELECT h FROM HhtFacture h WHERE h.fraisLivraison = :fraisLivraison"),
    @NamedQuery(name = "HhtFacture.findByTotalNetAPayer", query = "SELECT h FROM HhtFacture h WHERE h.totalNetAPayer = :totalNetAPayer"),
    @NamedQuery(name = "HhtFacture.findByAnnule", query = "SELECT h FROM HhtFacture h WHERE h.annule = :annule"),
    @NamedQuery(name = "HhtFacture.findByAcompte", query = "SELECT h FROM HhtFacture h WHERE h.acompte = :acompte"),
    @NamedQuery(name = "HhtFacture.findByTauxPrelevement", query = "SELECT h FROM HhtFacture h WHERE h.tauxPrelevement = :tauxPrelevement"),
    @NamedQuery(name = "HhtFacture.findByDateDebut", query = "SELECT h FROM HhtFacture h WHERE h.dateDebut = :dateDebut"),
    @NamedQuery(name = "HhtFacture.findByDateFin", query = "SELECT h FROM HhtFacture h WHERE h.dateFin = :dateFin"),
    @NamedQuery(name = "HhtFacture.findByNumeroSociete", query = "SELECT h FROM HhtFacture h WHERE h.numeroSociete = :numeroSociete"),
    @NamedQuery(name = "HhtFacture.findByTotalRemises", query = "SELECT h FROM HhtFacture h WHERE h.totalRemises = :totalRemises"),
    @NamedQuery(name = "HhtFacture.findByCodeTypeDocument", query = "SELECT h FROM HhtFacture h WHERE h.hhtFacturePK.codeTypeDocument = :codeTypeDocument"),
    @NamedQuery(name = "HhtFacture.findByCodeLivraison", query = "SELECT h FROM HhtFacture h WHERE h.codeLivraison = :codeLivraison"),
    @NamedQuery(name = "HhtFacture.findByDuAjoute", query = "SELECT h FROM HhtFacture h WHERE h.duAjoute = :duAjoute"),
    @NamedQuery(name = "HhtFacture.findByDateTour", query = "SELECT h FROM HhtFacture h WHERE h.dateTour = :dateTour"),
    @NamedQuery(name = "HhtFacture.findByScanClient", query = "SELECT h FROM HhtFacture h WHERE h.scanClient = :scanClient"),
    @NamedQuery(name = "HhtFacture.findByCodeDevise", query = "SELECT h FROM HhtFacture h WHERE h.hhtFacturePK.codeDevise = :codeDevise"),
    @NamedQuery(name = "HhtFacture.findByBl", query = "SELECT h FROM HhtFacture h WHERE h.bl = :bl"),
    @NamedQuery(name = "HhtFacture.findByCodeVendeur", query = "SELECT h FROM HhtFacture h WHERE h.codeVendeur = :codeVendeur"),
    @NamedQuery(name = "HhtFacture.findByScanClientFin", query = "SELECT h FROM HhtFacture h WHERE h.scanClientFin = :scanClientFin"),
    @NamedQuery(name = "HhtFacture.findByDateAnn", query = "SELECT h FROM HhtFacture h WHERE h.dateAnn = :dateAnn"),
    @NamedQuery(name = "HhtFacture.findBySignature", query = "SELECT h FROM HhtFacture h WHERE h.signature = :signature"),
    @NamedQuery(name = "HhtFacture.findByCodeChineur", query = "SELECT h FROM HhtFacture h WHERE h.codeChineur = :codeChineur"),
    @NamedQuery(name = "HhtFacture.findByAffecteLivreur", query = "SELECT h FROM HhtFacture h WHERE h.affecteLivreur = :affecteLivreur"),
    @NamedQuery(name = "HhtFacture.findByLivrer", query = "SELECT h FROM HhtFacture h WHERE h.livrer = :livrer")})
public class HhtFacture implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected HhtFacturePK hhtFacturePK;
    @Column(name = "DATE_FACT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateFact;
    @Basic(optional = false)
    @Column(name = "CODE_ROUTE")
    private String codeRoute;
    @Basic(optional = false)
    @Column(name = "CODE_TOUR")
    private String codeTour;
    @Basic(optional = false)
    @Column(name = "CODE_CLT")
    private String codeClt;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "ESPECE")
    private Double espece;
    @Column(name = "CHEQUE")
    private Double cheque;
    @Column(name = "CB")
    private Double cb;
    @Column(name = "RISTOURNE_CUMULE")
    private Double ristourneCumule;
    @Column(name = "CREDIT")
    private Double credit;
    @Column(name = "TOTAL_LIQUIDE")
    private Double totalLiquide;
    @Column(name = "TOTAL_TVA")
    private Double totalTva;
    @Column(name = "TOTAL_EMB")
    private Double totalEmb;
    @Column(name = "TOTAL_RISTOURNE_ENCOUR")
    private Double totalRistourneEncour;
    @Column(name = "TOTAL_POINTS_FID")
    private Integer totalPointsFid;
    @Column(name = "CODE_HIT")
    private String codeHit;
    @Column(name = "TAXE_PRELEVEMENT")
    private Double taxePrelevement;
    @Column(name = "TAXE_TIMBRE_FISC")
    private Double taxeTimbreFisc;
    @Column(name = "FRAIS_LIVRAISON")
    private Double fraisLivraison;
    @Column(name = "TOTAL_NET_A_PAYER")
    private Double totalNetAPayer;
    @Column(name = "ANNULE")
    private Boolean annule;
    @Column(name = "ACOMPTE")
    private Double acompte;
    @Column(name = "TAUX_PRELEVEMENT")
    private Double tauxPrelevement;
    @Column(name = "DATE_DEBUT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateDebut;
    @Column(name = "DATE_FIN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateFin;
    @Column(name = "NUMERO_SOCIETE")
    private String numeroSociete;
    @Column(name = "TOTAL_REMISES")
    private Double totalRemises;
    @Column(name = "CODE_LIVRAISON")
    private BigInteger codeLivraison;
    @Column(name = "DU_AJOUTE")
    private Double duAjoute;
    @Column(name = "DATE_TOUR")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTour;
    @Column(name = "SCAN_CLIENT")
    private Boolean scanClient;
    @Column(name = "BL")
    private String bl;
    @Column(name = "CODE_VENDEUR")
    private String codeVendeur;
    @Column(name = "SCAN_CLIENT_FIN")
    private Boolean scanClientFin;
    @Column(name = "DATE_ANN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateAnn;
    @Column(name = "SIGNATURE")
    private String signature;
    @Column(name = "CODE_CHINEUR")
    private String codeChineur;
    @Column(name = "AFFECTE_LIVREUR")
    private Boolean affecteLivreur;
    @Column(name = "LIVRER")
    private Boolean livrer;

    public HhtFacture() {
    }

    public HhtFacture(HhtFacturePK hhtFacturePK) {
        this.hhtFacturePK = hhtFacturePK;
    }

    public HhtFacture(HhtFacturePK hhtFacturePK, String codeRoute, String codeTour, String codeClt) {
        this.hhtFacturePK = hhtFacturePK;
        this.codeRoute = codeRoute;
        this.codeTour = codeTour;
        this.codeClt = codeClt;
    }

    public HhtFacture(String codeFact, String codeTypeDocument, String codeDevise) {
        this.hhtFacturePK = new HhtFacturePK(codeFact, codeTypeDocument, codeDevise);
    }

    public HhtFacturePK getHhtFacturePK() {
        return hhtFacturePK;
    }

    public void setHhtFacturePK(HhtFacturePK hhtFacturePK) {
        this.hhtFacturePK = hhtFacturePK;
    }

    public Date getDateFact() {
        return dateFact;
    }

    public void setDateFact(Date dateFact) {
        this.dateFact = dateFact;
    }

    public String getCodeRoute() {
        return codeRoute;
    }

    public void setCodeRoute(String codeRoute) {
        this.codeRoute = codeRoute;
    }

    public String getCodeTour() {
        return codeTour;
    }

    public void setCodeTour(String codeTour) {
        this.codeTour = codeTour;
    }

    public String getCodeClt() {
        return codeClt;
    }

    public void setCodeClt(String codeClt) {
        this.codeClt = codeClt;
    }

    public Double getEspece() {
        return espece;
    }

    public void setEspece(Double espece) {
        this.espece = espece;
    }

    public Double getCheque() {
        return cheque;
    }

    public void setCheque(Double cheque) {
        this.cheque = cheque;
    }

    public Double getCb() {
        return cb;
    }

    public void setCb(Double cb) {
        this.cb = cb;
    }

    public Double getRistourneCumule() {
        return ristourneCumule;
    }

    public void setRistourneCumule(Double ristourneCumule) {
        this.ristourneCumule = ristourneCumule;
    }

    public Double getCredit() {
        return credit;
    }

    public void setCredit(Double credit) {
        this.credit = credit;
    }

    public Double getTotalLiquide() {
        return totalLiquide;
    }

    public void setTotalLiquide(Double totalLiquide) {
        this.totalLiquide = totalLiquide;
    }

    public Double getTotalTva() {
        return totalTva;
    }

    public void setTotalTva(Double totalTva) {
        this.totalTva = totalTva;
    }

    public Double getTotalEmb() {
        return totalEmb;
    }

    public void setTotalEmb(Double totalEmb) {
        this.totalEmb = totalEmb;
    }

    public Double getTotalRistourneEncour() {
        return totalRistourneEncour;
    }

    public void setTotalRistourneEncour(Double totalRistourneEncour) {
        this.totalRistourneEncour = totalRistourneEncour;
    }

    public Integer getTotalPointsFid() {
        return totalPointsFid;
    }

    public void setTotalPointsFid(Integer totalPointsFid) {
        this.totalPointsFid = totalPointsFid;
    }

    public String getCodeHit() {
        return codeHit;
    }

    public void setCodeHit(String codeHit) {
        this.codeHit = codeHit;
    }

    public Double getTaxePrelevement() {
        return taxePrelevement;
    }

    public void setTaxePrelevement(Double taxePrelevement) {
        this.taxePrelevement = taxePrelevement;
    }

    public Double getTaxeTimbreFisc() {
        return taxeTimbreFisc;
    }

    public void setTaxeTimbreFisc(Double taxeTimbreFisc) {
        this.taxeTimbreFisc = taxeTimbreFisc;
    }

    public Double getFraisLivraison() {
        return fraisLivraison;
    }

    public void setFraisLivraison(Double fraisLivraison) {
        this.fraisLivraison = fraisLivraison;
    }

    public Double getTotalNetAPayer() {
        return totalNetAPayer;
    }

    public void setTotalNetAPayer(Double totalNetAPayer) {
        this.totalNetAPayer = totalNetAPayer;
    }

    public Boolean getAnnule() {
        return annule;
    }

    public void setAnnule(Boolean annule) {
        this.annule = annule;
    }

    public Double getAcompte() {
        return acompte;
    }

    public void setAcompte(Double acompte) {
        this.acompte = acompte;
    }

    public Double getTauxPrelevement() {
        return tauxPrelevement;
    }

    public void setTauxPrelevement(Double tauxPrelevement) {
        this.tauxPrelevement = tauxPrelevement;
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

    public String getNumeroSociete() {
        return numeroSociete;
    }

    public void setNumeroSociete(String numeroSociete) {
        this.numeroSociete = numeroSociete;
    }

    public Double getTotalRemises() {
        return totalRemises;
    }

    public void setTotalRemises(Double totalRemises) {
        this.totalRemises = totalRemises;
    }

    public BigInteger getCodeLivraison() {
        return codeLivraison;
    }

    public void setCodeLivraison(BigInteger codeLivraison) {
        this.codeLivraison = codeLivraison;
    }

    public Double getDuAjoute() {
        return duAjoute;
    }

    public void setDuAjoute(Double duAjoute) {
        this.duAjoute = duAjoute;
    }

    public Date getDateTour() {
        return dateTour;
    }

    public void setDateTour(Date dateTour) {
        this.dateTour = dateTour;
    }

    public Boolean getScanClient() {
        return scanClient;
    }

    public void setScanClient(Boolean scanClient) {
        this.scanClient = scanClient;
    }

    public String getBl() {
        return bl;
    }

    public void setBl(String bl) {
        this.bl = bl;
    }

    public String getCodeVendeur() {
        return codeVendeur;
    }

    public void setCodeVendeur(String codeVendeur) {
        this.codeVendeur = codeVendeur;
    }

    public Boolean getScanClientFin() {
        return scanClientFin;
    }

    public void setScanClientFin(Boolean scanClientFin) {
        this.scanClientFin = scanClientFin;
    }

    public Date getDateAnn() {
        return dateAnn;
    }

    public void setDateAnn(Date dateAnn) {
        this.dateAnn = dateAnn;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getCodeChineur() {
        return codeChineur;
    }

    public void setCodeChineur(String codeChineur) {
        this.codeChineur = codeChineur;
    }

    public Boolean getAffecteLivreur() {
        return affecteLivreur;
    }

    public void setAffecteLivreur(Boolean affecteLivreur) {
        this.affecteLivreur = affecteLivreur;
    }

    public Boolean getLivrer() {
        return livrer;
    }

    public void setLivrer(Boolean livrer) {
        this.livrer = livrer;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (hhtFacturePK != null ? hhtFacturePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HhtFacture)) {
            return false;
        }
        HhtFacture other = (HhtFacture) object;
        if ((this.hhtFacturePK == null && other.hhtFacturePK != null) || (this.hhtFacturePK != null && !this.hhtFacturePK.equals(other.hhtFacturePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HhtFacture{" + "hhtFacturePK=" + hhtFacturePK + ", dateFact=" + dateFact + ", codeRoute=" + codeRoute + ", codeTour=" + codeTour + ", codeClt=" + codeClt + ", espece=" + espece + ", cheque=" + cheque + ", cb=" + cb + ", ristourneCumule=" + ristourneCumule + ", credit=" + credit + ", totalLiquide=" + totalLiquide + ", totalTva=" + totalTva + ", totalEmb=" + totalEmb + ", totalRistourneEncour=" + totalRistourneEncour + ", totalPointsFid=" + totalPointsFid + ", codeHit=" + codeHit + ", taxePrelevement=" + taxePrelevement + ", taxeTimbreFisc=" + taxeTimbreFisc + ", fraisLivraison=" + fraisLivraison + ", totalNetAPayer=" + totalNetAPayer + ", annule=" + annule + ", acompte=" + acompte + ", tauxPrelevement=" + tauxPrelevement + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin + ", numeroSociete=" + numeroSociete + ", totalRemises=" + totalRemises + ", codeLivraison=" + codeLivraison + ", duAjoute=" + duAjoute + ", dateTour=" + dateTour + ", scanClient=" + scanClient + ", bl=" + bl + ", codeVendeur=" + codeVendeur + ", scanClientFin=" + scanClientFin + ", dateAnn=" + dateAnn + ", signature=" + signature + ", codeChineur=" + codeChineur + ", affecteLivreur=" + affecteLivreur + ", livrer=" + livrer + '}';
    }

   
    
}
