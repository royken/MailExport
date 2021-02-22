package com.bracongo.mailexport.dao;

import com.bracongo.mailexport.data.RepTauxChange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author vr.kenfack
 */
@Repository
public interface IRepTauxChangeDao extends JpaRepository<RepTauxChange, Long>{
    
}
