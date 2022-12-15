package com.bracongo.mailexport.dao;

import com.bracongo.mailexport.data.TbArticle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author vr.kenfack
 */
@Repository
public interface ITbArticleDao extends JpaRepository<TbArticle, String>{
    
}
