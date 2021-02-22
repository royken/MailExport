package com.bracongo.mailexport.dao;

import com.bracongo.mailexport.data.RepArticleInfoBi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author vr.kenfack
 */
@Repository
public interface IRepArticleInfoBiDao extends JpaRepository<RepArticleInfoBi, String>{
    
}
