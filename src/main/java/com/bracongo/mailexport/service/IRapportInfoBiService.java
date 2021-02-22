package com.bracongo.mailexport.service;

import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author vr.kenfack
 */
@Transactional
public interface IRapportInfoBiService {
    
    public void produceRapportDataAndMail();
}
