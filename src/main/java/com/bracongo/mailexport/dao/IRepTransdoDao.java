package com.bracongo.mailexport.dao;

import com.bracongo.mailexport.data.RepTransdo;
import com.bracongo.mailexport.data.dto.CautionSigmaMoisDto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author vr.kenfack
 */
@Repository
public interface IRepTransdoDao extends JpaRepository<RepTransdo, Long>{
    
    @Modifying
    @Query("delete from RepTransdo transdo where transdo.mois = :mois and transdo.annee = :annee")
    public void deleteRowsByMonth(@Param("mois") int mois, @Param("annee") int annee);
    
    @Query("select new com.bracongo.mailexport.data.dto.CautionSigmaMoisDto("
            + " caution.annee, "
            + " caution.mois, "
            + " caution.cccde,"
            + " caution.codeCd, "
            + " cd.cdiNomcdi,"
            + " sum(caution.ttc)) "
            + " from RepTransdo caution, TbCentreDistribution cd"
            + " where caution.tts = 'CAU' and caution.mois = :mois and caution.annee = :annee and caution.codeCd = cd.cdiCodecd "
            + " group by caution.annee, caution.mois, caution.cccde,  caution.codeCd, cd.cdiNomcdi")
    public List<CautionSigmaMoisDto> getCautionMois(@Param("mois") int mois, @Param("annee") int annee);
    
}
