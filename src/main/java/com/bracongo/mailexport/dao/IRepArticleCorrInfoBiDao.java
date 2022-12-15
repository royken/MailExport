package com.bracongo.mailexport.dao;

import com.bracongo.mailexport.data.RepArticleCorrespondanceInfoBi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author vr.kenfack
 */
@Repository
public interface IRepArticleCorrInfoBiDao extends JpaRepository<RepArticleCorrespondanceInfoBi, String>{
    
}
