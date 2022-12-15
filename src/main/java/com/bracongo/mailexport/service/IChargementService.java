package com.bracongo.mailexport.service;

import com.bracongo.mailexport.data.dto.ChargementGlobalCDDto;
import com.bracongo.mailexport.data.dto.ChargementGlobalProduitDto;
import com.bracongo.mailexport.data.dto.ChargementProduitDto;
import java.util.List;

/**
 *
 * @author vr.kenfack
 */
public interface IChargementService {
    
    public List<ChargementGlobalCDDto> getAllChargementGlobalByCd();
    
    public List<ChargementProduitDto> getAllChargementGlobalByProduit();
    
    public List<ChargementProduitDto> getAllChargementGlobalByProduitByVoyageId(int voyageId);
    
    
  
    
    public void produceChargementResumeAndMail();
}
