package com.bracongo.mailexport.dao;

import com.bracongo.mailexport.data.RepArticleSigma;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author vr.kenfack
 */
@Repository
public interface IRepArticleSigmaDao extends JpaRepository<RepArticleSigma, Long>{
    
    @Query("select article from RepArticleSigma article where article.famille in ('BI', 'BG', 'VIN', 'EAU') and article.statu != 'S'")
    public List<RepArticleSigma> getAllProduit();
}
