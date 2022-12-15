package com.bracongo.mailexport.dao;

import com.bracongo.mailexport.data.RepVenteJourCDCircuitGratuitCA;
import com.bracongo.mailexport.data.dto.ResumeCADateDto;
import com.bracongo.mailexport.data.dto.VenteEtGratuitGammeCdMoisAnneeDto;
import com.bracongo.mailexport.data.dto.VenteEtGratuitInfoBiFamilleCDMois;
import com.bracongo.mailexport.data.dto.VenteGratuitGlobalByMoisAnnee;
import com.bracongo.mailexport.data.dto.VenteGratuitProduitAnneeDto;
import com.bracongo.mailexport.data.dto.VenteGratuitProduitAnneeMoisDto;
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
public interface IRepVentesGratuitCDDao extends JpaRepository<RepVenteJourCDCircuitGratuitCA, Long>{
 
    
    @Query("select new com.bracongo.mailexport.data.dto.VenteGratuitProduitAnneeDto("
            + " ventes.annee, "
            + " sum(ventes.hecto), "
            + " ventes.codars, "
            + " ventes.famille, "
            + " ventes.nomProduit) "
            + " from RepVenteJourCDCircuitGratuitCA ventes "
            + " where ventes.annee = :annee "
            + " group by ventes.codars, ventes.annee, ventes.famille, ventes.nomProduit")
    public List<VenteGratuitProduitAnneeDto> getVentesByAnneeByProduit(@Param("annee") int annee);
    
    @Query("select new com.bracongo.mailexport.data.dto.VenteGratuitProduitAnneeMoisDto("
            + " ventes.annee, "
             + " ventes.mois, "
            + " sum(ventes.hecto), "
            + " ventes.codars, "
            + " ventes.famille, "
            + " ventes.nomProduit) "
            + " from RepVenteJourCDCircuitGratuitCA ventes "
            + " where ventes.annee = :annee "
            + " group by ventes.codars, ventes.annee, ventes.mois, ventes.famille, ventes.nomProduit")
    public List<VenteGratuitProduitAnneeMoisDto> getVentesByAnneeMoisByProduit(@Param("annee") int annee);
    
    @Query("select new com.bracongo.mailexport.data.dto.VenteGratuitGlobalByMoisAnnee("
            + " ventes.annee, "
             + " ventes.mois, "
            + " sum(ventes.hecto))  "
            + " from RepVenteJourCDCircuitGratuitCA ventes "
            + " where ventes.annee = :annee "
            + " group by ventes.annee, ventes.mois")
    public List<VenteGratuitGlobalByMoisAnnee> getVentesGlobalByAnneeMois(@Param("annee") int annee);
    
    @Query("select new com.bracongo.mailexport.data.dto.VenteEtGratuitGammeCdMoisAnneeDto("
            + " sum(ventes.hecto), "
            + " ventes.mois, "
            + " ventes.codeCD, "
            + " article.artGamme) "
            + " from RepVenteJourCDCircuitGratuitCA ventes, TbArticle article "
            + " where ventes.annee = :annee "
            + " and ventes.codars = article.artCodars"
            + " group by  ventes.mois, ventes.codeCD, article.artGamme")
    public List<VenteEtGratuitGammeCdMoisAnneeDto> getVentesByCDGammeAnneeMoisBy(@Param("annee") int annee);
    
    @Query("select new com.bracongo.mailexport.data.dto.VenteEtGratuitInfoBiFamilleCDMois("
            + " ventes.codeCD, "           
            + " ventes.mois, "
            + " ventes.annee, "
            + " article.codar, "
            + " article.famille, "
            + " article.codeInfoBi, "
            + " sum(ventes.hecto) ) "          
            + " from RepVenteJourCDCircuitGratuitCA ventes, RepArticleInfoBi article, RepArticleCorrespondanceInfoBi articleCorr "
            + " where ventes.annee = :annee "
            + " and ventes.codars = articleCorr.repArticleCorrespondanceInfoBiPK.codar "
            + " and article.codeInfoBi = articleCorr.repArticleCorrespondanceInfoBiPK.codeInfoBi"
            + " group by  ventes.mois, ventes.codeCD, article.codar, article.famille, article.codeInfoBi, ventes.annee")
    public List<VenteEtGratuitInfoBiFamilleCDMois> getVenteGratuitFamilleMoisProduit(@Param("annee") int annee);
    
  /*  @Query("select new com.bracongo.mailexport.data.dto.ResumeCADateDto("
            + " ventes.annee, "
            + " ventes.mois, "
            + " sum(ventes.casier), "
            + " sum(ventes.hecto), "
            + " ventes.codars, "
            + " ventes.nomProduit, "
            + " sum( case when ventes.casier < 0 then ventes.prixHt * -1 else ventes.prixHt end), "
            + " sum( case when ventes.casier < 0 then ventes.prixTtc * -1 else ventes.prixTtc end) "
            + "from RepVenteJourCDCircuitGratuitCA ventes "
            + " where ventes.annee = :annee "
            + " and ventes.mois = :mois "
            + " and ventes.codars like 'V%' and ventes.ss = 'F' and ventes.tts not in ('GRV', 'AGV', 'CFI', 'RFI', 'CFL', 'RFL')"
            + " group by ventes.annee, ventes.mois, ventes.codars, ventes.nomProduit")
    public List<NegoceResumeCADataDto> getNegoceCaResumeDataByMonthByYear(@Param("mois") int mois, @Param("annee") int annee);
  */  
    @Query("select ventes "
            + "from RepVenteJourCDCircuitGratuitCA ventes "
            + " where ventes.annee = :annee "
            + " and ventes.mois = :mois "
            + " and ventes.codars like 'V%' and ventes.ss = 'F' and ventes.tts not in ('GRV', 'AGV', 'CFI', 'RFI', 'CFL', 'RFL')")
    public List<RepVenteJourCDCircuitGratuitCA> getNegoceCaVenteDataByMonthByYear(@Param("mois") int mois, @Param("annee") int annee);
    
    @Query("select ventes "
            + "from RepVenteJourCDCircuitGratuitCA ventes "
            + " where ventes.annee = :annee "
            + " and ventes.mois = :mois "
            + " and ventes.ss = 'F' and ventes.famille != '' and ventes.famille != 'VIN' and ventes.famille != 'GL' and ventes.tts not in ('GRA', 'ESG')")
    public List<RepVenteJourCDCircuitGratuitCA> getCaVenteDataByMonthByYear(@Param("mois") int mois, @Param("annee") int annee);
    
    
    @Query("select ventes "
            + "from RepVenteJourCDCircuitGratuitCA ventes "
            + " where ventes.annee = :annee "
            + " and ventes.mois = :mois "
            + " and ventes.ss = 'F' and ventes.famille != '' and ventes.famille != 'VIN' and ventes.famille != 'GL'")
    public List<RepVenteJourCDCircuitGratuitCA> getCaVenteAndGratuitsDataByMonthByYear(@Param("mois") int mois, @Param("annee") int annee);
    
   
}
