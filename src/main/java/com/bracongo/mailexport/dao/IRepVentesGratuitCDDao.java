package com.bracongo.mailexport.dao;

import com.bracongo.mailexport.data.RepVenteJourCDCircuitGratuitCA;
import com.bracongo.mailexport.data.dto.VenteEtGratuitGammeCdMoisAnneeDto;
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
}
