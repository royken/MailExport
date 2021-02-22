package com.bracongo.mailexport.dao;

import com.bracongo.mailexport.data.RepChargementSrd;
import com.bracongo.mailexport.data.dto.ChargementGlobalCDDto;
import com.bracongo.mailexport.data.dto.ChargementGlobalProduitDto;
import com.bracongo.mailexport.data.dto.ChargementProduitDto;
import java.util.Date;
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
public interface IChargementSRDDao extends JpaRepository<RepChargementSrd, Long>{
    
    @Query("select new com.bracongo.mailexport.data.dto.ChargementProduitDto("
            + " chargement.codars, "
            + " chargement.nomProduit, "
            + " sum(chargement.quantiteChargee), "
            + " sum(chargement.quantiteRetournee), "
            + " sum(chargement.quantiteSortie) * -1, "
            + " sum(chargement.hecto) as hecto, "
            + " (sum(chargement.quantiteRetournee) / sum(chargement.quantiteChargee) * 100) as taux_retour, "
            + " article.artGamme) "
            + " from RepChargementSrd  chargement, TbArticle article "
            + " where chargement.codeCircuit != '' "
            + " and chargement.dateLongue between :debut and :fin "
            + " and chargement.codars = article.artCodars "
            + " group by chargement.codars, chargement.nomProduit, article.artGamme "
            + " order by taux_retour desc" )
    public List<ChargementProduitDto> getChargementResumeBetweenDates(Date debut, Date fin);
    
    @Query("select new com.bracongo.mailexport.data.dto.ChargementProduitDto("
            + " chargement.codars, "
            + " chargement.nomProduit, "
            + " sum(chargement.quantiteChargee), "
            + " sum(chargement.quantiteRetournee), "
            + " sum(chargement.quantiteSortie) * -1, "
            + " sum(chargement.hecto) as hecto, "
            + " (sum(chargement.quantiteRetournee) / sum(chargement.quantiteChargee) * 100) as taux_retour, "
            + " article.artGamme) "
            + " from RepChargementSrd  chargement, TbArticle article "
            + " where chargement.codeCircuit != '' "
            + " and chargement.dateLongue between :debut and :fin "
            + " and chargement.codars = article.artCodars "
            + " and chargement.voyageId = :voyageId "
            + " group by chargement.codars, chargement.nomProduit, article.artGamme "
            + " order by taux_retour desc" )
    public List<ChargementProduitDto> getChargementResumeBetweenDatesByVoyageId(@Param("debut") Date debut, @Param("fin") Date fin, @Param("voyageId") int voyageId);
    
   /* public List<RepChargementSrd> getAllChargementDataBetweenDates(Date debut, Date fin);
    
    public List<RepChargementSrd> getAllChargementForOnDay(int annee, int mois, int jour);
    
    public List<ChargementGlobalCDDto> getAllChargementGlobalByCodeCd(int annee, int mois, int jour);*/
    
    
    @Query("select chargement from RepChargementSrd chargement where chargement.annee = :annee and chargement.mois = :mois and chargement.jour = :jour")
     public List<RepChargementSrd> getAllChargementByDay(@Param("annee") int annee, @Param("mois") int mois, @Param("jour") int jour);
    
    
}
