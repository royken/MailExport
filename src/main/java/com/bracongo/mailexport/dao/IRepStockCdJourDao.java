package com.bracongo.mailexport.dao;

import com.bracongo.mailexport.data.RepStockCdJour;
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
public interface IRepStockCdJourDao extends JpaRepository<RepStockCdJour, Long>{
    
    @Query("select stock from RepStockCdJour stock where stock.annee = :annee and stock.mois = :mois order by stock.jour")
    public List<RepStockCdJour> getAllStockByAnneeByMois(@Param("annee") int annee, @Param("mois") int mois);
    
}
