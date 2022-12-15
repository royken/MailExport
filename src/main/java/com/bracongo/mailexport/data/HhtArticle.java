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
 * @author Valmy Roi Kenfack <vr.kenfack at bracongo.cd>
 */
@Entity
@Table(name = "HHT_ARTICLE",  schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HhtArticle.findAll", query = "SELECT h FROM HhtArticle h")
    , @NamedQuery(name = "HhtArticle.findByCodeArt", query = "SELECT h FROM HhtArticle h WHERE h.codeArt = :codeArt")
    , @NamedQuery(name = "HhtArticle.findByLibelle", query = "SELECT h FROM HhtArticle h WHERE h.libelle = :libelle")
    , @NamedQuery(name = "HhtArticle.findByCodeParfum", query = "SELECT h FROM HhtArticle h WHERE h.codeParfum = :codeParfum")
    , @NamedQuery(name = "HhtArticle.findByCodeSousFamille", query = "SELECT h FROM HhtArticle h WHERE h.codeSousFamille = :codeSousFamille")
    , @NamedQuery(name = "HhtArticle.findByNonActif", query = "SELECT h FROM HhtArticle h WHERE h.nonActif = :nonActif")
    , @NamedQuery(name = "HhtArticle.findByCodeSi", query = "SELECT h FROM HhtArticle h WHERE h.codeSi = :codeSi")
    })
public class HhtArticle extends BaseClass{

   
    @Column(name = "LIBELLE")
    private String libelle;
    @Column(name = "CODE_EMBALLAGE")
    private String codeEmballage;
    @Column(name = "CODE_TAILLE")
    private String codeTaille;
    @Column(name = "CODE_TVA")
    private String codeTva;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "REMISE_UNITAIRE")
    private Double remiseUnitaire;
    @Column(name = "SEUIL_REMISE")
    private Double seuilRemise;
    @Column(name = "RISTOURNE_UNITAIRE")
    private Double ristourneUnitaire;
    @Column(name = "CODE_BARRE")
    private String codeBarre;
    @Column(name = "TAXE1")
    private Double taxe1;
    @Column(name = "TAXE2")
    private Double taxe2;
    @Column(name = "RUPTURE")
    private Boolean rupture;
    @Column(name = "ACC_PRIX_MANUEL")
    private Boolean accPrixManuel;
    @Column(name = "POIDS")
    private Double poids;
    @Column(name = "NUMERO_SOCIETE")
    private String numeroSociete;
    @Column(name = "CONDITIONNEMENT")
    private Integer conditionnement;
    @Column(name = "ACC_REPRISE")
    private Boolean accReprise;
    @Column(name = "QTE_EN_STOCK")
    private Integer qteEnStock;
    @Column(name = "CODE_DEVISE")
    private String codeDevise;
    @Column(name = "CODE_DECOMPOSITION_ARTICLE")
    private String codeDecompositionArticle;
    @Basic(optional = false)
    @Column(name = "QTE_DECOMPOSITION_ARTICLE")
    private double qteDecompositionArticle;
    @Column(name = "CODE_DECOMPOSITION_EMBALLAGE")
    private String codeDecompositionEmballage;
    @Basic(optional = false)
    @Column(name = "QTE_DECOMPOSITION_EMBALLAGE")
    private double qteDecompositionEmballage;
    @Column(name = "CODE_COMPOSITION_ARTICLE")
    private String codeCompositionArticle;
    @Basic(optional = false)
    @Column(name = "QTE_COMPOSITION_ARTICLE")
    private double qteCompositionArticle;
    @Column(name = "TYPE_CONTENANT")
    private String typeContenant;

    @Id
    @Basic(optional = false)
    @Column(name = "CODE_ART", nullable = false, length = 20)
    private String codeArt;
    @Column(name = "CODE_PARFUM", length = 20)
    private String codeParfum;
    @Column(name = "CODE_SOUS_FAMILLE", length = 20)
    private String codeSousFamille;
    @Column(name = "NON_ACTIF")
    private Boolean nonActif;
    @Column(name = "CODE_SI", length = 20)
    private String codeSi;
    

    public HhtArticle() {
    }

    public HhtArticle(String codeArt) {
        this.codeArt = codeArt;
    }

    public HhtArticle(String codeArt, String codeSi) {
        this.codeArt = codeArt;
        this.codeSi = codeSi;
    }

    public String getCodeArt() {
        return codeArt;
    }

    public void setCodeArt(String codeArt) {
        this.codeArt = codeArt;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getCodeParfum() {
        return codeParfum;
    }

    public void setCodeParfum(String codeParfum) {
        this.codeParfum = codeParfum;
    }

    
    public String getCodeSousFamille() {
        return codeSousFamille;
    }

    public void setCodeSousFamille(String codeSousFamille) {
        this.codeSousFamille = codeSousFamille;
    }

        public Boolean getNonActif() {
        return nonActif;
    }

    public void setNonActif(Boolean nonActif) {
        this.nonActif = nonActif;
    }

    
    public String getCodeSi() {
        return codeSi;
    }

    public void setCodeSi(String codeSi) {
        this.codeSi = codeSi;
    }

    public String getCodeEmballage() {
        return codeEmballage;
    }

    public void setCodeEmballage(String codeEmballage) {
        this.codeEmballage = codeEmballage;
    }

    public String getCodeTaille() {
        return codeTaille;
    }

    public void setCodeTaille(String codeTaille) {
        this.codeTaille = codeTaille;
    }

    public String getCodeTva() {
        return codeTva;
    }

    public void setCodeTva(String codeTva) {
        this.codeTva = codeTva;
    }

    public Double getRemiseUnitaire() {
        return remiseUnitaire;
    }

    public void setRemiseUnitaire(Double remiseUnitaire) {
        this.remiseUnitaire = remiseUnitaire;
    }

    public Double getSeuilRemise() {
        return seuilRemise;
    }

    public void setSeuilRemise(Double seuilRemise) {
        this.seuilRemise = seuilRemise;
    }

    public Double getRistourneUnitaire() {
        return ristourneUnitaire;
    }

    public void setRistourneUnitaire(Double ristourneUnitaire) {
        this.ristourneUnitaire = ristourneUnitaire;
    }

    public String getCodeBarre() {
        return codeBarre;
    }

    public void setCodeBarre(String codeBarre) {
        this.codeBarre = codeBarre;
    }

    public Double getTaxe1() {
        return taxe1;
    }

    public void setTaxe1(Double taxe1) {
        this.taxe1 = taxe1;
    }

    public Double getTaxe2() {
        return taxe2;
    }

    public void setTaxe2(Double taxe2) {
        this.taxe2 = taxe2;
    }

    public Boolean getRupture() {
        return rupture;
    }

    public void setRupture(Boolean rupture) {
        this.rupture = rupture;
    }

    public Boolean getAccPrixManuel() {
        return accPrixManuel;
    }

    public void setAccPrixManuel(Boolean accPrixManuel) {
        this.accPrixManuel = accPrixManuel;
    }

    public Double getPoids() {
        return poids;
    }

    public void setPoids(Double poids) {
        this.poids = poids;
    }

    public String getNumeroSociete() {
        return numeroSociete;
    }

    public void setNumeroSociete(String numeroSociete) {
        this.numeroSociete = numeroSociete;
    }

    public Integer getConditionnement() {
        return conditionnement;
    }

    public void setConditionnement(Integer conditionnement) {
        this.conditionnement = conditionnement;
    }

    public Boolean getAccReprise() {
        return accReprise;
    }

    public void setAccReprise(Boolean accReprise) {
        this.accReprise = accReprise;
    }

    public Integer getQteEnStock() {
        return qteEnStock;
    }

    public void setQteEnStock(Integer qteEnStock) {
        this.qteEnStock = qteEnStock;
    }

    public String getCodeDevise() {
        return codeDevise;
    }

    public void setCodeDevise(String codeDevise) {
        this.codeDevise = codeDevise;
    }

    public String getCodeDecompositionArticle() {
        return codeDecompositionArticle;
    }

    public void setCodeDecompositionArticle(String codeDecompositionArticle) {
        this.codeDecompositionArticle = codeDecompositionArticle;
    }

    public double getQteDecompositionArticle() {
        return qteDecompositionArticle;
    }

    public void setQteDecompositionArticle(double qteDecompositionArticle) {
        this.qteDecompositionArticle = qteDecompositionArticle;
    }

    public String getCodeDecompositionEmballage() {
        return codeDecompositionEmballage;
    }

    public void setCodeDecompositionEmballage(String codeDecompositionEmballage) {
        this.codeDecompositionEmballage = codeDecompositionEmballage;
    }

    public double getQteDecompositionEmballage() {
        return qteDecompositionEmballage;
    }

    public void setQteDecompositionEmballage(double qteDecompositionEmballage) {
        this.qteDecompositionEmballage = qteDecompositionEmballage;
    }

    public String getCodeCompositionArticle() {
        return codeCompositionArticle;
    }

    public void setCodeCompositionArticle(String codeCompositionArticle) {
        this.codeCompositionArticle = codeCompositionArticle;
    }

    public double getQteCompositionArticle() {
        return qteCompositionArticle;
    }

    public void setQteCompositionArticle(double qteCompositionArticle) {
        this.qteCompositionArticle = qteCompositionArticle;
    }

    public String getTypeContenant() {
        return typeContenant;
    }

    public void setTypeContenant(String typeContenant) {
        this.typeContenant = typeContenant;
    }
    
}
