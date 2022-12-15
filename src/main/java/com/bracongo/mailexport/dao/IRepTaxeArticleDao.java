package com.bracongo.mailexport.dao;

import com.bracongo.mailexport.data.RepTaxeArticle;
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
public interface IRepTaxeArticleDao extends JpaRepository<RepTaxeArticle, Long>{
    
    @Query("select taxe from RepTaxeArticle taxe where taxe.annee = :annee and taxe.mois = :mois and taxe.jour = :jour")
    public List<RepTaxeArticle> getAllTaxesByDate(@Param("annee") int annee, @Param("mois") int mois, @Param("jour") int jour);
    
}
