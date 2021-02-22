package com.bracongo.mailexport.schedule;

import com.bracongo.mailexport.service.IChargementService;
import com.bracongo.mailexport.service.IRapportFactureService;
import com.bracongo.mailexport.service.IVenteRateeService;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author vr.kenfack
 */
@Component
@Service
@Transactional
public class ScheduledTasks {
    
    @Autowired
    private IVenteRateeService rateeService;
    
    @Autowired
    private IRapportFactureService factureService;
    
    @Autowired
    private IChargementService chargementService;
    
    private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    
    @Scheduled(cron = "${cron.expression}")
    public void produceVenteRateeReportAndMail() {
        logger.info("Début exportation pour mail " );
        rateeService.exportReportBetweenDates();
        logger.info("Fin exportation ");
    }
    
    @Scheduled(cron = "${cron.heure.facturation}")
    public void produceHeureVenteReportAndMail() {
        logger.info("Début exportation pour mail " );
        factureService.produceHeureVenteRapportAndMail();
        logger.info("Fin exportation ");
    }
    
    @Scheduled(cron = "${cron.heure.chargement}")
    public void produceChargementReportAndMail() {
        logger.info("Début exportation chargement pour mail " );
        chargementService.produceChargementDataAndMail();
        logger.info("Fin exportation ");
    }
    
}
