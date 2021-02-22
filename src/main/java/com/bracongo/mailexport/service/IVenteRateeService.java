package com.bracongo.mailexport.service;

import com.bracongo.mailexport.data.RepVenteRatee;
import com.bracongo.mailexport.data.dto.ProduitHectoRateeDto;
import com.bracongo.mailexport.data.dto.VenteRateeCdDto;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

/**
 *
 * @author vr.kenfack
 */
public interface IVenteRateeService {
    
    public List<RepVenteRatee>  getAllVentesRatees();
    
    public List<ProduitHectoRateeDto> getAllProduitRateeStat();
    
    public List<VenteRateeCdDto> getAllRateeCdStat();
    
    public OutputStream exportReportBetweenDates();
}
