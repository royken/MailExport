package com.bracongo.mailexport.dao;

import com.bracongo.mailexport.data.RepVenteJourCDCircuitGratuitCA;
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
 
    
    @Query("select com.bracongo.mailexport.data.dto.VenteGratuitProduitAnneeDto("
            + " ventes.annee, "
            + " sum(ventes.hecto), "
            + " ventes.codars, "
            + " ventes.famille, "
            + " ventes.nomProduit) "
            + " from RepVenteJourCDCircuitGratuitCA ventes "
            + " where ventes.annee = :annee "
            + " group by ventes.codars, ventes.annee, ventes.famille")
    public List<VenteGratuitProduitAnneeDto> getVentesByAnneeByProduit(@Param("annee") int annee);
    
    @Query("select com.bracongo.mailexport.data.dto.VenteGratuitProduitAnneeMoisDto("
            + " ventes.annee, "
             + " ventes.mois, "
            + " sum(ventes.hecto), "
            + " ventes.codars, "
            + " ventes.famille, "
            + " ventes.nomProduit) "
            + " from RepVenteJourCDCircuitGratuitCA ventes "
            + " where ventes.annee = :annee "
            + " group by ventes.codars, ventes.annee, ventes.mois, ventes.famille")
    public List<VenteGratuitProduitAnneeMoisDto> getVentesByAnneeMoisByProduit(@Param("annee") int annee);
    
    @Query("select com.bracongo.mailexport.data.dto.VenteGratuitGlobalByMoisAnnee("
            + " ventes.annee, "
             + " ventes.mois, "
            + " sum(ventes.hecto)  "
            + " from RepVenteJourCDCircuitGratuitCA ventes "
            + " where ventes.annee = :annee "
            + " group by ventes.annee, ventes.mois")
    public List<VenteGratuitGlobalByMoisAnnee> getVentesGlobalByAnneeMois(@Param("annee") int annee);
}
