package com.bracongo.mailexport.dao;

import com.bracongo.mailexport.data.RepVenteRatee;
import com.bracongo.mailexport.data.dto.CircuitRateeProduit;
import com.bracongo.mailexport.data.dto.ErreurSaisieCdDto;
import com.bracongo.mailexport.data.dto.ErreurSaisieCircuitDto;
import com.bracongo.mailexport.data.dto.ProduitHectoRateeDto;
import com.bracongo.mailexport.data.dto.ProduitRatee;
import com.bracongo.mailexport.data.dto.VenteRateeCdDto;
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
public interface IRepVenteRateeDao extends JpaRepository<RepVenteRatee, Long>{
    
    @Query("select venteRatee from RepVenteRatee venteRatee where year(venteRatee.date) = :annee and month(venteRatee.date) = :mois and venteRatee.qteSouhaitee < 100")
    List<RepVenteRatee> getAllByMonthAnnee(@Param("mois")int mois, @Param("annee")int annee);
    
    @Query("select venteRatee from RepVenteRatee venteRatee where venteRatee.date between :debut and :fin and venteRatee.qteSouhaitee < 100 ")
    List<RepVenteRatee> findRepVenteRateeBetweenDate(@Param("debut")Date debut, @Param("fin")Date fin);
    
    @Query("select new com.bracongo.mailexport.data.dto.ProduitHectoRateeDto("
            + " venteRatee.libelle, "
            + " count(venteRatee), "
            + " sum(venteRatee.qteSouhaitee) as totalSouhaite, "
            + " sum(venteRatee.qteAchetee) as totalAchete  , "
            + " sum(venteRatee.qteRatee) as totalRatee, "
            + " sum(venteRatee.hectoRatee) AS totalHecto"
            + ") "
            + "from RepVenteRatee venteRatee where year(venteRatee.date) = :annee and month(venteRatee.date) = :mois "
            + " and venteRatee.qteSouhaitee < 100"
            + " group by venteRatee.libelle "
            + "order by totalHecto desc")
    List<ProduitHectoRateeDto> getVenteRateeStatByProduit(@Param("mois")int mois, @Param("annee")int annee);
    
    @Query("select new com.bracongo.mailexport.data.dto.VenteRateeCdDto("
            + " venteRatee.nomCd, "
            + " venteRatee.codeCd, "
            + " count(venteRatee), "
            + " sum(venteRatee.qteRatee) as totalRatee, "
            + " sum(venteRatee.hectoRatee) as totalHectoRatee"
            + ") "
            + "from RepVenteRatee venteRatee where venteRatee.date between :debut and :fin "
            + " and venteRatee.qteSouhaitee < 100"
            + " group by venteRatee.nomCd, venteRatee.codeCd "
            + "order by totalRatee desc")
    List<VenteRateeCdDto> getVenteRateeStatByCD(@Param("debut")Date debut, @Param("fin")Date fin);
    
    @Query("select new com.bracongo.mailexport.data.dto.ProduitRatee("
            + " venteRatee.libelle, "
            + " venteRatee.codeArt, "
            + " sum(venteRatee.qteRatee) as totalRatee, "
            + " sum(venteRatee.hectoRatee) as hectoRatee "
            + ") "
            + " from RepVenteRatee venteRatee where venteRatee.date between :debut and :fin "
            + " and venteRatee.qteSouhaitee < 100"
            + " group by venteRatee.libelle,  venteRatee.codeArt"
            + " order by totalRatee desc")
    List<ProduitRatee> getAllProduitRateeBetweenDates(@Param("debut")Date debut, @Param("fin")Date fin);
    
    @Query("select new com.bracongo.mailexport.data.dto.CircuitRateeProduit("
            + " venteRatee.libelle, "
            + " venteRatee.nomCd, "
            + " venteRatee.codeVendeur, "
            + " venteRatee.circuit, "
            + " venteRatee.codeArt, "
            + " venteRatee.codeCd, "
            + " sum(venteRatee.hectoRatee), "
             + " sum(venteRatee.qteAchetee), "
            + " sum(venteRatee.qteRatee) as totalRatee, "
             + " sum(venteRatee.qteSouhaitee), "
            + " sum(venteRatee.qteStock) "
            + ") "
            + "from RepVenteRatee venteRatee where venteRatee.date between :debut and :fin "
            + " and venteRatee.qteSouhaitee < 100"
            + " group by venteRatee.libelle,  venteRatee.nomCd, venteRatee.codeVendeur, venteRatee.circuit, venteRatee.codeArt, venteRatee.codeCd "
            + " order by totalRatee desc")
    List<CircuitRateeProduit> getAllCdCircuitRateeBetweenDates(@Param("debut")Date debut, @Param("fin")Date fin);
    
    @Query("select new com.bracongo.mailexport.data.dto.ErreurSaisieCdDto("
            + " venteRatee.nomCd, "
            + " venteRatee.codeCd, "
            + " count(venteRatee) as erreur "
            + ") "
            + " from RepVenteRatee venteRatee where venteRatee.date between :debut and :fin "
            + " and venteRatee.qteSouhaitee >= 100"
            + " group by venteRatee.nomCd,  venteRatee.codeCd"
            + " order by erreur desc")
    List<ErreurSaisieCdDto> getErreurSaisieCdStatBetweenDates(@Param("debut")Date debut, @Param("fin")Date fin);
    
    @Query("select new com.bracongo.mailexport.data.dto.ErreurSaisieCircuitDto("
            + " venteRatee.nomCd, "
            + " venteRatee.codeCd, "
            + " venteRatee.circuit, "
            + " venteRatee.codeVendeur, "
            + " count(venteRatee) as erreur "
            + ") "
            + " from RepVenteRatee venteRatee where venteRatee.date between :debut and :fin "
            + " and venteRatee.qteSouhaitee >= 100"
            + " group by venteRatee.nomCd,  venteRatee.codeCd, venteRatee.circuit, venteRatee.codeVendeur"
            + " order by erreur desc")
    List<ErreurSaisieCircuitDto> getErreurSaisieCircuitStatBetweenDates(@Param("debut")Date debut, @Param("fin")Date fin);
    
    @Query("select venteRatee from RepVenteRatee venteRatee where venteRatee.date between :debut and :fin and venteRatee.qteSouhaitee >= 100 ")
    List<RepVenteRatee> findErreurSaisieBetweenDate(@Param("debut")Date debut, @Param("fin")Date fin);
    
}
