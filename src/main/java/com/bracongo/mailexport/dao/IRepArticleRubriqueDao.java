package com.bracongo.mailexport.dao;

import com.bracongo.mailexport.data.RepArticleRubrique;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author vr.kenfack
 */
@Repository
public interface IRepArticleRubriqueDao extends JpaRepository<RepArticleRubrique, Long>{
    
}
