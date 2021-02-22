package com.bracongo.mailexport.dao;

import com.bracongo.mailexport.data.dto.ClientServisCircuit;
import java.util.List;
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
    
}
