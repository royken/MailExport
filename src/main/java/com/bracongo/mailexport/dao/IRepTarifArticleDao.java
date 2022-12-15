package com.bracongo.mailexport.dao;

import com.bracongo.mailexport.data.RepTarifArticles;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author vr.kenfack
 */
@Repository
public interface IRepTarifArticleDao extends JpaRepository<RepTarifArticles, Long>{
    
    @Query("select tarif from RepTarifArticles tarif order by dateTarif desc")
    public List<RepTarifArticles> getAllTarif();
    
}
