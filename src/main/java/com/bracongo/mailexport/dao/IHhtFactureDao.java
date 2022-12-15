package com.bracongo.mailexport.dao;

import com.bracongo.mailexport.data.HhtFacture;
import com.bracongo.mailexport.data.dto.CautionCaBlDto;
import com.bracongo.mailexport.data.dto.CautionCaBlJourDto;
import com.bracongo.mailexport.data.dto.HhtFactureCircuitDto;
import com.bracongo.mailexport.data.dto.RemiseCaCircuitTournesolDto;
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
            + " facture.codeVendeur, "
            + " facture.codeClt ) "
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
    public List<HhtFactureCircuitDto> getAllFacturesByCircuitForDay(@Param("year") int year, @Param("month") int month, @Param("day") int day);
    
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
            + " facture.codeVendeur, "
            + " facture.codeClt ) "
            + " from HhtFacture facture, TbCircuit circuit, TbCentreDistribution cd "
            + " where facture.totalNetAPayer > 0 "
            + " and facture.annule is NULL "
            + " and year(facture.dateFact) = :year "
            + " and month(facture.dateFact) = :month "
            + " and datepart(HOUR, facture.dateFact) >= 5 "
            + " and facture.codeRoute = circuit.cirCodcir "
            + " and substring(circuit.cirCodcir, 1, 2) = cd.cdiCodecd "
            + " order by cd.cdiNomcdi, facture.dateFact asc, circuit.cirCodsigma ")
    public List<HhtFactureCircuitDto> getAllFacturesByCircuitForMonth(@Param("year") int year, @Param("month") int month);
    
    /**
     * Retourne le chiffre d'affaire et la remise générée par circuit pour chaque jour d'un mois donné
     * @param year
     * @param month
     * @return 
     */
    @Query("select new com.bracongo.mailexport.data.dto.RemiseCaCircuitTournesolDto( "
            + " cd.cdiCodecd,"
            + " cd.cdiNomcdi, "
            + " circuit.cirCodsigma, "
            + " circuit.cirCodcir, "
            + " sum(facture.totalLiquide), "
            + " sum(facture.totalRistourneEncour), "
            + " year(facture.dateFact), "
            + " month(facture.dateFact)) "
            + " from HhtFacture facture, TbCircuit circuit, TbCentreDistribution cd "
            + " where facture.codeRoute = circuit.cirCodcir "
            + " and facture.hhtFacturePK.codeDevise = 1 "
            + " and facture.annule is NULL "
            + " and year(facture.dateFact) = :year "
            + " and month(facture.dateFact) = :month "
            + " and substring(circuit.cirCodcir, 1, 2) = cd.cdiCodecd "
            + " group by cd.cdiCodecd, cd.cdiNomcdi, circuit.cirCodsigma, circuit.cirCodcir , month(facture.dateFact), year(facture.dateFact) "
            + " order by  cd.cdiCodecd, circuit.cirCodsigma")
    public List<RemiseCaCircuitTournesolDto> getRemiseCaGenereeByCircuitByMonth(@Param("year") int year, @Param("month") int month);
    
    @Query("select new com.bracongo.mailexport.data.dto.CautionCaBlJourDto( "
            + " cd.cdiCodecd, "
            + " cd.cdiNomcdi, "
            + " circuit.cirCodsigma, "
            + " circuit.cirCodcir, "
            + " day(facture.dateFact),"
            + " month(facture.dateFact), "
            + " year(facture.dateFact), "
            + " sum(facture.totalLiquide), "
            + " sum(facture.totalRistourneEncour), "
            + " facture.bl) "
            + " from HhtFacture facture, TbCircuit circuit, TbCentreDistribution cd "
            + " where facture.codeRoute = circuit.cirCodcir "
            + " and facture.hhtFacturePK.codeDevise = 1 "
            + " and facture.annule is NULL "
            + " and year(facture.dateFact) = :year "
            + " and month(facture.dateFact) = :month"
            + " and substring(circuit.cirCodcir, 1, 2) = cd.cdiCodecd "
            + " group by cd.cdiCodecd, cd.cdiNomcdi, circuit.cirCodsigma, circuit.cirCodcir , month(facture.dateFact), year(facture.dateFact), day(facture.dateFact), facture.bl "
            + " order by  day(facture.dateFact), cd.cdiCodecd, circuit.cirCodsigma")
    public List<CautionCaBlJourDto> getCautionByBlMois(@Param("year") int year, @Param("month") int month);
    
    
    @Query("select new com.bracongo.mailexport.data.dto.CautionCaBlDto( "
            + " cd.cdiCodecd, "
            + " cd.cdiNomcdi, "
            + " circuit.cirCodsigma, "
            + " circuit.cirCodcir, "
            + " month(facture.dateFact), "
            + " year(facture.dateFact), "
            + " sum(facture.totalLiquide), "
            + " sum(facture.totalRistourneEncour), "
            + " facture.bl) "
            + " from HhtFacture facture, TbCircuit circuit, TbCentreDistribution cd "
            + " where facture.codeRoute = circuit.cirCodcir "
            + " and facture.hhtFacturePK.codeDevise = 1 "
            + " and facture.annule is NULL "
            + " and year(facture.dateFact) = :year "
            + " and month(facture.dateFact) = :month"
            + " and substring(circuit.cirCodcir, 1, 2) = cd.cdiCodecd "
            + " group by cd.cdiCodecd, cd.cdiNomcdi, circuit.cirCodsigma, circuit.cirCodcir , month(facture.dateFact), year(facture.dateFact), facture.bl "
            + " order by cd.cdiCodecd, circuit.cirCodsigma")
    public List<CautionCaBlDto> getSumCautionByBlMois(@Param("year") int year, @Param("month") int month);
}
