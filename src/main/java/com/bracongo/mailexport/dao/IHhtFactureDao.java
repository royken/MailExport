package com.bracongo.mailexport.dao;

import com.bracongo.mailexport.data.HhtFacture;
import com.bracongo.mailexport.data.dto.HhtFactureCircuitDto;
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
public interface IHhtFactureDao extends JpaRepository<HhtFacture, String>{
    
    @Query("select new com.bracongo.mailexport.data.dto.HhtFactureCircuitDto( "
            + " facture.codeRoute, "
            + " cd.cdiCodecd, "
            + " cd.cdiNomcdi, "
            + " circuit.cirCodsigma, "
            + " facture.hhtFacturePK.codeFact, "
            + " facture.dateFact, "
            + " facture.codeClt, "
            + " facture.dateDebut, "
            + " facture.dateFin, "
            + " facture.bl, "
            + " facture.codeVendeur ) "
            + " from HhtFacture facture, TbCircuit circuit, TbCentreDistribution cd "
            + " where facture.totalNetAPayer > 0 "
            + " and facture.annule is NULL "
            + " and year(facture.dateFact) = :year "
            + " and month(facture.dateFact) = :month "
            + " and day(facture.dateFact) = :day "
            + " and datepart(HOUR, facture.dateFact) >= 5 "
            + " and facture.codeRoute = circuit.cirCodcir "
            + " and substring(circuit.cirCodcir, 1, 2) = cd.cdiCodecd "
            + " order by cd.cdiNomcdi, facture.dateFact asc, circuit.cirCodsigma ")
    public List<HhtFactureCircuitDto> getAllFacturesByCircuit(@Param("year") int year, @Param("month") int month, @Param("day") int day);
}
