package com.bracongo.mailexport.dao;

import com.bracongo.mailexport.data.dto.CaCircuitSigmaDto;
import com.bracongo.mailexport.data.dto.CautionSigmaBlDto;
import com.bracongo.mailexport.data.dto.ClientServisCircuit;
import com.bracongo.mailexport.data.dto.ResumeCADateDto;
import com.bracongo.mailexport.data.dto.ResumeCADateDto;
import java.util.List;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author vr.kenfack
 */
@Repository
public interface INativeQueryDao {
    
    /**
     * Retourne la DN globale d'achat J-1 en prenant pour base client ceux du mois dernier
     * @param year l'année
     * @param month
     * @param day
     * @param lastYear
     * @param lastMonth le mois dernier
     * @return 
     */
    public List<ClientServisCircuit> getClientServiCircuitStat(int year, int month, int day, int lastYear, int lastMonth);
    
    /**
     * Retourne la DN globale d'achat du mois en prenant pour base client ceux du mois dernier
     * @param year l'année
     * @param month
     * @param lastYear
     * @param lastMonth le mois dernier
     * @return 
     */
    public List<ClientServisCircuit> getClientServiCircuitStatMonth(int year, int month, int lastYear, int lastMonth);
    
    /*
    
    /**
    * Retourne les ventes et le chiffre d'affaire des ventes négoces pour un mois donnée d'une année
    */
    public List<ResumeCADateDto> getNegoceCaResumeDataByMonthByYear(int mois, int annee);
    
    /**
    * Retourne les ventes et le chiffre d'affaire  pour un mois donnée d'une année
     * @param mois
     * @param annee
     * @return 
    */
    public List<ResumeCADateDto> getCaResumeDataByMonthByYear(int mois, int annee);
    
    /**
     * Retourne le chiffre d'affaire cumulé par circuit pour un mois donné
     * @param mois
     * @param annee
     * @return 
     */
    public List<CaCircuitSigmaDto> getChiffreDaffaireByCircuit(int mois, int annee);
    
    /**
     * Retoune laliste des cations sigma pour un mois donnée avec numéro de BL
     * @param mois
     * @param annee
     * @return 
     */
    public List<CautionSigmaBlDto> getCautionSigmaAvecBl(int mois, int annee);
    
}
