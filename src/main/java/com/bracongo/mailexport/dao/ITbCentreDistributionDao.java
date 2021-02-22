package com.bracongo.mailexport.dao;

import com.bracongo.mailexport.data.TbCentreDistribution;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author vr.kenfack
 */
@Repository
public interface ITbCentreDistributionDao extends JpaRepository<TbCentreDistribution, Long>{
    @Query("select cd from TbCentreDistribution cd where cd.cdiCapacitecd > 0")
    public List<TbCentreDistribution> getAllCd();
}
