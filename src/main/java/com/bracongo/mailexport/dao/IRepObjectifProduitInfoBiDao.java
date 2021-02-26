package com.bracongo.mailexport.dao;

import com.bracongo.mailexport.data.RepObjectifProduitInfoBi;
import com.bracongo.mailexport.data.dto.BudgetGlobalProduitByCd;
import com.bracongo.mailexport.data.dto.BudgetInfoBiByCDMoisFamilleDto;
import com.bracongo.mailexport.data.dto.BudgetInfoBiByMoisAnneeDto;
import com.bracongo.mailexport.data.dto.BudgetInfoBiCdMoisAnneeDto;
import com.bracongo.mailexport.data.dto.BudgetInfoBiGlobalMoisAnneeDto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author vr.kenfack
 */
@Repository
public interface IRepObjectifProduitInfoBiDao extends JpaRepository<RepObjectifProduitInfoBi, String>{
    
    
    @Query("select objectif from RepObjectifProduitInfoBi objectif where objectif.repObjectifProduitInfoBiPK.annee = :annee")
    public List<RepObjectifProduitInfoBi> getAllBudgetByAnnee(@Param("annee") int annee);
    
    @Query("select objectif from RepObjectifProduitInfoBi objectif where objectif.repObjectifProduitInfoBiPK.annee = :annee and objectif.repObjectifProduitInfoBiPK.codeCd = :codecd")
    public List<RepObjectifProduitInfoBi> getAllBudgetByAnneeByCd(@Param("annee") int annee, @Param("codecd") String codecd);
    
    @Query("select objectif from RepObjectifProduitInfoBi objectif where objectif.repObjectifProduitInfoBiPK.annee = :annee and objectif.repObjectifProduitInfoBiPK.codeCd = :codecd and objectif.repObjectifProduitInfoBiPK.codeInfoBi = :codeProduit")
    public List<RepObjectifProduitInfoBi> getAllBudgetByAnneeByCdByProduit(@Param("annee") int annee, @Param("codecd") String codecd, @Param("codeProduit") String codeProduit);
    
    @Query("select new com.bracongo.mailexport.data.dto.BudgetInfoBiByMoisAnneeDto("
            + " objectif.repObjectifProduitInfoBiPK.codeInfoBi, "
            + " objectif.codar, "
            + " sum(objectif.budget), "
            + " objectif.repObjectifProduitInfoBiPK.mois, "
            + " objectif.repObjectifProduitInfoBiPK.annee)"
            + " from RepObjectifProduitInfoBi objectif"
            + " where objectif.repObjectifProduitInfoBiPK.annee = :annee "
            + " group by objectif.repObjectifProduitInfoBiPK.codeInfoBi, objectif.codar, objectif.repObjectifProduitInfoBiPK.mois, objectif.repObjectifProduitInfoBiPK.annee")
    public List<BudgetInfoBiByMoisAnneeDto> getBudgetByProduitMoisAnnee(@Param("annee") int annee);
    
    @Query("select new com.bracongo.mailexport.data.dto.BudgetGlobalProduitByCd("
            + " objectif.repObjectifProduitInfoBiPK.codeInfoBi, "
            + " objectif.codar, "
            + " sum(objectif.budget), "
            + " objectif.repObjectifProduitInfoBiPK.annee, "
            + " objectif.repObjectifProduitInfoBiPK.codeCd)"
            + " from RepObjectifProduitInfoBi objectif"
            + " where objectif.repObjectifProduitInfoBiPK.annee = :annee "
            + " group by objectif.repObjectifProduitInfoBiPK.codeInfoBi, objectif.codar, objectif.repObjectifProduitInfoBiPK.annee, objectif.repObjectifProduitInfoBiPK.codeCd")
    public List<BudgetGlobalProduitByCd> getBudgetGlobalByProduitByCdAnnee(@Param("annee") int annee);
    
    @Query("select new com.bracongo.mailexport.data.dto.BudgetInfoBiGlobalMoisAnneeDto("
            + " objectif.repObjectifProduitInfoBiPK.annee, "
             + " objectif.repObjectifProduitInfoBiPK.mois, "
            + " sum(objectif.budget) ) "
            + " from RepObjectifProduitInfoBi objectif"
            + " where objectif.repObjectifProduitInfoBiPK.annee = :annee "
            + " group by  objectif.repObjectifProduitInfoBiPK.mois, objectif.repObjectifProduitInfoBiPK.annee")
    public List<BudgetInfoBiGlobalMoisAnneeDto> getBudgetGlobalByCdAnnee(@Param("annee") int annee);
    
    
    @Query("select new com.bracongo.mailexport.data.dto.BudgetInfoBiByCDMoisFamilleDto("
            + " objectif.repObjectifProduitInfoBiPK.codeCd, "
             + " objectif.repObjectifProduitInfoBiPK.mois, "
            + " sum(objectif.budget),  "
            + " article.famille ) "
            + " from RepObjectifProduitInfoBi objectif, RepArticleInfoBi article"
            + " where objectif.repObjectifProduitInfoBiPK.annee = :annee "
            + " and article.codeInfoBi = objectif.repObjectifProduitInfoBiPK.codeInfoBi "
            + " group by objectif.repObjectifProduitInfoBiPK.codeCd,  objectif.repObjectifProduitInfoBiPK.mois, article.famille")
    public List<BudgetInfoBiByCDMoisFamilleDto> getBudgetByCdFamilleAnnee(@Param("annee") int annee);
}
