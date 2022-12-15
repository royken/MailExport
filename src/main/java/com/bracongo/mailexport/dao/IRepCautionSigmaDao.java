package com.bracongo.mailexport.dao;

import com.bracongo.mailexport.data.RepCautionSigma;
import com.bracongo.mailexport.data.dto.CautionSigmaCircuitDto;
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
public interface IRepCautionSigmaDao extends JpaRepository<RepCautionSigma, Long>{
    
    @Modifying
    @Query("delete from RepCautionSigma cautions where cautions.mois = :mois and cautions.annee = :annee")
    public void deleteRowsByMonth(@Param("mois") int mois, @Param("annee") int annee);
    
    @Query("select new com.bracongo.mailexport.data.dto.CautionSigmaCircuitDto("
            + " caution.annee, "
            + " caution.mois, "
            + " caution.codeCircuit, "
            + " caution.codeCD,"
            + " sum(caution.mtn) ) "
            + " from RepCautionSigma caution "
            + " where caution.annee = :annee "
            + " and caution.mois = :mois "
            + " group by caution.annee, caution.mois, caution.codeCircuit, caution.codeCD ")
    public List<CautionSigmaCircuitDto> getCautionByCircuit(@Param("mois") int mois, @Param("annee") int annee);
    
}
