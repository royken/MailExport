package com.bracongo.mailexport.service;

import com.bracongo.mailexport.data.dto.CdHeurerResumeStat;
import java.io.OutputStream;
import java.util.List;

/**
 *
 * @author vr.kenfack
 */
public interface IRapportFactureService {
    
    public void produceHeureVenteRapportAndMail();
    
    public List<CdHeurerResumeStat> getListHeureCd();
    
    public byte[] exportHeureVenteRapport(OutputStream out);
}
