package com.bracongo.mailexport.dao;

import com.bracongo.mailexport.data.RepDegradation;
import com.bracongo.mailexport.data.dto.DegradationArticleDto;
import java.util.Date;
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
public interface IRepDegradationDao extends JpaRepository<RepDegradation, Long>{
    
    @Query("select new com.bracongo.mailexport.data.dto.DegradationArticleDto("
            + " article.codar, "
            + " article.desar, "
            + " degradation.argaDg, "
            + " degradation.argtDg, "
            + " degradation.codeCd, "
            + " degradation.dateDebut, "
            + " degradation.dateFin, "
            + " degradation.rubrique, "
            + " degradation.valeurDegradation)"
            + " from RepDegradation degradation, RepArticleSigma article, RepArticleRubrique articleRubrique"
            + " where article.codar = articleRubrique.codars "
            + " and articleRubrique.rubriqueArticle in ('DIT', 'CFC', 'DIS', 'TIN', 'RSF', 'DTS') "
            + " and articleRubrique.rubriqueArticle = degradation.rubrique "
            + " and articleRubrique.arGra = degradation.argaDg "
            + " and :date between degradation.dateDebut and degradation.dateFin ")
    public List<DegradationArticleDto> getAllValidDegration(@Param("date") Date date);
 
    
}
