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
@Table(name = "REP_CHARGEMENT_SRD")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RepChargementSrd.findAll", query = "SELECT r FROM RepChargementSrd r"),
    @NamedQuery(name = "RepChargementSrd.findById", query = "SELECT r FROM RepChargementSrd r WHERE r.id = :id"),
    @NamedQuery(name = "RepChargementSrd.findByAnnee", query = "SELECT r FROM RepChargementSrd r WHERE r.annee = :annee"),
    @NamedQuery(name = "RepChargementSrd.findByCircuit", query = "SELECT r FROM RepChargementSrd r WHERE r.circuit = :circuit"),
    @NamedQuery(name = "RepChargementSrd.findByCodars", query = "SELECT r FROM RepChargementSrd r WHERE r.codars = :codars"),
    @NamedQuery(name = "RepChargementSrd.findByCodeCd", query = "SELECT r FROM RepChargementSrd r WHERE r.codeCd = :codeCd"),
    @NamedQuery(name = "RepChargementSrd.findByCodeCircuit", query = "SELECT r FROM RepChargementSrd r WHERE r.codeCircuit = :codeCircuit"),
    @NamedQuery(name = "RepChargementSrd.findByDateLongue", query = "SELECT r FROM RepChargementSrd r WHERE r.dateLongue = :dateLongue"),
    @NamedQuery(name = "RepChargementSrd.findByFamille", query = "SELECT r FROM RepChargementSrd r WHERE r.famille = :famille"),
    @NamedQuery(name = "RepChargementSrd.findByHecto", query = "SELECT r FROM RepChargementSrd r WHERE r.hecto = :hecto"),
    @NamedQuery(name = "RepChargementSrd.findByJour", query = "SELECT r FROM RepChargementSrd r WHERE r.jour = :jour"),
    @NamedQuery(name = "RepChargementSrd.findByMois", query = "SELECT r FROM RepChargementSrd r WHERE r.mois = :mois"),
    @NamedQuery(name = "RepChargementSrd.findByNomProduit", query = "SELECT r FROM RepChargementSrd r WHERE r.nomProduit = :nomProduit"),
    @NamedQuery(name = "RepChargementSrd.findByNtd", query = "SELECT r FROM RepChargementSrd r WHERE r.ntd = :ntd"),
    @NamedQuery(name = "RepChargementSrd.findByQc", query = "SELECT r FROM RepChargementSrd r WHERE r.qc = :qc"),
    @NamedQuery(name = "RepChargementSrd.findByQi", query = "SELECT r FROM RepChargementSrd r WHERE r.qi = :qi"),
    @NamedQuery(name = "RepChargementSrd.findByQuantiteChargee", query = "SELECT r FROM RepChargementSrd r WHERE r.quantiteChargee = :quantiteChargee"),
    @NamedQuery(name = "RepChargementSrd.findByQuantiteFacturee", query = "SELECT r FROM RepChargementSrd r WHERE r.quantiteFacturee = :quantiteFacturee"),
    @NamedQuery(name = "RepChargementSrd.findByQuantiteRetournee", query = "SELECT r FROM RepChargementSrd r WHERE r.quantiteRetournee = :quantiteRetournee"),
    @NamedQuery(name = "RepChargementSrd.findByQuantiteSortie", query = "SELECT r FROM RepChargementSrd r WHERE r.quantiteSortie = :quantiteSortie"),
    @NamedQuery(name = "RepChargementSrd.findByVoyageId", query = "SELECT r FROM RepChargementSrd r WHERE r.voyageId = :voyageId")})
public class RepChargementSrd implements Serializable {

    @Column(name = "NTD")
    private Integer ntd;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "ANNEE")
    private Integer annee;
    @Column(name = "CIRCUIT")
    private String circuit;
    @Column(name = "CODARS")
    private String codars;
    @Column(name = "CODE_CD")
    private Integer codeCd;
    @Column(name = "CODE_CIRCUIT")
    private String codeCircuit;
    @Column(name = "DATE_LONGUE")
    @Temporal(TemporalType.DATE)
    private Date dateLongue;
    @Column(name = "FAMILLE")
    private String famille;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "HECTO")
    private Double hecto;
    @Column(name = "JOUR")
    private Integer jour;
    @Column(name = "MOIS")
    private Integer mois;
    @Column(name = "NOM_PRODUIT")
    private String nomProduit;
    @Column(name = "QC")
    private Double qc;
    @Column(name = "QI")
    private Double qi;
    @Column(name = "QUANTITE_CHARGEE")
    private Double quantiteChargee;
    @Column(name = "QUANTITE_FACTUREE")
    private Double quantiteFacturee;
    @Column(name = "QUANTITE_RETOURNEE")
    private Double quantiteRetournee;
    @Column(name = "QUANTITE_SORTIE")
    private Double quantiteSortie;
    @Column(name = "VOYAGE_ID")
    private Integer voyageId;

    public RepChargementSrd() {
    }

    public RepChargementSrd(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAnnee() {
        return annee;
    }

    public void setAnnee(Integer annee) {
        this.annee = annee;
    }

    public String getCircuit() {
        return circuit;
    }

    public void setCircuit(String circuit) {
        this.circuit = circuit;
    }

    public String getCodars() {
        return codars;
    }

    public void setCodars(String codars) {
        this.codars = codars;
    }

    public Integer getCodeCd() {
        return codeCd;
    }

    public void setCodeCd(Integer codeCd) {
        this.codeCd = codeCd;
    }

    public String getCodeCircuit() {
        return codeCircuit;
    }

    public void setCodeCircuit(String codeCircuit) {
        this.codeCircuit = codeCircuit;
    }

    public Date getDateLongue() {
        return dateLongue;
    }

    public void setDateLongue(Date dateLongue) {
        this.dateLongue = dateLongue;
    }

    public String getFamille() {
        return famille;
    }

    public void setFamille(String famille) {
        this.famille = famille;
    }

    public Double getHecto() {
        return hecto;
    }

    public void setHecto(Double hecto) {
        this.hecto = hecto;
    }

    public Integer getJour() {
        return jour;
    }

    public void setJour(Integer jour) {
        this.jour = jour;
    }

    public Integer getMois() {
        return mois;
    }

    public void setMois(Integer mois) {
        this.mois = mois;
    }

    public String getNomProduit() {
        return nomProduit;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }


    public Double getQc() {
        return qc;
    }

    public void setQc(Double qc) {
        this.qc = qc;
    }

    public Double getQi() {
        return qi;
    }

    public void setQi(Double qi) {
        this.qi = qi;
    }

    public Double getQuantiteChargee() {
        return quantiteChargee;
    }

    public void setQuantiteChargee(Double quantiteChargee) {
        this.quantiteChargee = quantiteChargee;
    }

    public Double getQuantiteFacturee() {
        return quantiteFacturee;
    }

    public void setQuantiteFacturee(Double quantiteFacturee) {
        this.quantiteFacturee = quantiteFacturee;
    }

    public Double getQuantiteRetournee() {
        return quantiteRetournee;
    }

    public void setQuantiteRetournee(Double quantiteRetournee) {
        this.quantiteRetournee = quantiteRetournee;
    }

    public Double getQuantiteSortie() {
        return quantiteSortie;
    }

    public void setQuantiteSortie(Double quantiteSortie) {
        this.quantiteSortie = quantiteSortie;
    }

    public Integer getVoyageId() {
        return voyageId;
    }

    public void setVoyageId(Integer voyageId) {
        this.voyageId = voyageId;
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
        if (!(object instanceof RepChargementSrd)) {
            return false;
        }
        RepChargementSrd other = (RepChargementSrd) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bracongo.mailexport.data.RepChargementSrd[ id=" + id + " ]";
    }

    public Integer getNtd() {
        return ntd;
    }

    public void setNtd(Integer ntd) {
        this.ntd = ntd;
    }
    
}
