package com.bracongo.mailexport.service.impl;

import com.bracongo.mailexport.dao.IHhtFactureDao;
import com.bracongo.mailexport.dao.INativeQueryDao;
import com.bracongo.mailexport.dao.IRepCautionSigmaDao;
import com.bracongo.mailexport.dao.IRepTransdoDao;
import com.bracongo.mailexport.dao.IRepVentesGratuitCDDao;
import com.bracongo.mailexport.data.RepVenteJourCDCircuitGratuitCA;
import com.bracongo.mailexport.data.dto.CaCircuitSigmaDto;
import com.bracongo.mailexport.data.dto.CautionCaBlDto;
import com.bracongo.mailexport.data.dto.CautionCaBlJourDto;
import com.bracongo.mailexport.data.dto.CautionSigmaBlDto;
import com.bracongo.mailexport.data.dto.CautionSigmaCircuitDto;
import com.bracongo.mailexport.data.dto.CautionSigmaMoisDto;
import com.bracongo.mailexport.data.dto.RemiseCaCircuitTournesolDto;
import com.bracongo.mailexport.data.dto.ResumeCADateDto;
import com.bracongo.mailexport.service.IRapportChiffreAffaireService;
import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.extensions.XSSFCellBorder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author vr.kenfack
 */
@Service
@Transactional
public class RapportChiffreAffaireServiceImpl implements IRapportChiffreAffaireService {

    @Autowired
    private IRepVentesGratuitCDDao ventesGratuitCDDao;

    @Autowired
    private INativeQueryDao nativeQueryDao;

    @Autowired
    private JavaMailSender sender;

    @Autowired
    private IHhtFactureDao factureDao;

    @Autowired
    private IRepCautionSigmaDao cautionSigmaDao;

    @Autowired
    private IRepTransdoDao repTransdoDao;

    XSSFCellStyle header;

    XSSFCellStyle simpleStyle;

    XSSFCellStyle titleStyle;

    XSSFColor b = new XSSFColor(new java.awt.Color(255, 255, 255));
    XSSFColor blackBorderColor = new XSSFColor(Color.BLACK);

    @Override
    public void produceRapportCaNegoceAndMail() {
        try {
            Calendar cal = Calendar.getInstance();
            if (cal.get(Calendar.DAY_OF_MONTH) == 1) {
                cal.add(Calendar.MONTH, -1);
            }
            int annee = cal.get(Calendar.YEAR);
            int mois = (cal.get(Calendar.MONTH) + 1);
            //List<NegoceResumeCADataDto> ventesNegoceResumes = ventesGratuitCDDao.getNegoceCaResumeDataByMonthByYear(mois, annee);
            List<ResumeCADateDto> ventesNegoceResumes = nativeQueryDao.getNegoceCaResumeDataByMonthByYear(mois, annee);
            List<RepVenteJourCDCircuitGratuitCA> ventesNegoceDatas = ventesGratuitCDDao.getNegoceCaVenteDataByMonthByYear(mois, annee);

            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFFont boldFont = workbook.createFont();
            boldFont.setBold(true);

            XSSFFont whiteFont = workbook.createFont();
            whiteFont = workbook.createFont();
            whiteFont.setFontHeightInPoints((short) 11);
            whiteFont.setFontName("Calibri");
            whiteFont.setColor(IndexedColors.WHITE.getIndex());
            whiteFont.setBold(true);
            whiteFont.setItalic(false);
            header = workbook.createCellStyle();
            header.setFillForegroundColor(HSSFColor.HSSFColorPredefined.GOLD.getIndex());
            header.setBorderColor(XSSFCellBorder.BorderSide.LEFT, b);
            header.setBorderColor(XSSFCellBorder.BorderSide.BOTTOM, b);
            header.setBorderColor(XSSFCellBorder.BorderSide.RIGHT, b);
            header.setBorderColor(XSSFCellBorder.BorderSide.TOP, b);
            header.setAlignment(HorizontalAlignment.CENTER);
            header.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            header.setBorderTop(BorderStyle.THIN);
            header.setBorderLeft(BorderStyle.THIN);
            header.setBorderRight(BorderStyle.THIN);
            header.setBorderBottom(BorderStyle.THIN);

            simpleStyle = workbook.createCellStyle();
            simpleStyle.setBottomBorderColor(blackBorderColor);
            simpleStyle.setLeftBorderColor(blackBorderColor);
            simpleStyle.setTopBorderColor(blackBorderColor);
            simpleStyle.setRightBorderColor(blackBorderColor);
            simpleStyle.setBorderTop(BorderStyle.THIN);
            simpleStyle.setBorderLeft(BorderStyle.THIN);
            simpleStyle.setBorderRight(BorderStyle.THIN);
            simpleStyle.setBorderBottom(BorderStyle.THIN);

            titleStyle = workbook.createCellStyle();
            titleStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            titleStyle.setFillForegroundColor(IndexedColors.RED.getIndex());
            titleStyle.setAlignment(HorizontalAlignment.CENTER);
            titleStyle.setLeftBorderColor(blackBorderColor);
            titleStyle.setRightBorderColor(blackBorderColor);
            titleStyle.setTopBorderColor(blackBorderColor);
            titleStyle.setBottomBorderColor(blackBorderColor);
            titleStyle.setBorderTop(BorderStyle.THIN);
            titleStyle.setBorderLeft(BorderStyle.THIN);
            titleStyle.setBorderRight(BorderStyle.THIN);
            titleStyle.setBorderBottom(BorderStyle.THIN);
            titleStyle.setFont(whiteFont);

            XSSFSheet rapportCaResumeSheet = workbook.createSheet("CA RESUME");
            XSSFSheet rapportCaDataSheet = workbook.createSheet("CA DATA");
            produceRapportResumeSheet(rapportCaResumeSheet, ventesNegoceResumes);
            produceRapportDataSheet(rapportCaDataSheet, ventesNegoceDatas);
            File file = File.createTempFile("Rapport", "xlsx");
            file.deleteOnExit();
            Path path = file.toPath();
            FileOutputStream fileOut = new FileOutputStream(file);
            /* workbook.write(fileOut);
            fileOut.close();
            DataSource fds = new FileDataSource("temp.xls");
             */
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            workbook.write(bos); // write excel data to a byte array
            fileOut.close();

// Now use your ByteArrayDataSource as
            DataSource fds = new ByteArrayDataSource(bos.toByteArray(), "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            MimeMessage message = sender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);

            helper.setTo(new String[]{"mamadou.salam@castel-afrique.com", "karine.ngondo@castel-afrique.com", "freddy.kalombo@castel-afrique.com", "christian.dikasa@castel-afrique.com", "stanislas.delattre@castel-afrique.com", "chantal.mbomba@castel-afrique.com", "pierre.wemaere@castel-afrique.com"});
            helper.setCc("valmy.roikenfack@castel-afrique.com");
            helper.setSubject("Rapport Automatique CA NEGOCE - " + getMonthName(mois) + " " + annee);
            helper.setFrom("valmy.roikenfack@castel-afrique.com");
            MimeBodyPart messageBodyPart1 = new MimeBodyPart();
            messageBodyPart1.setDataHandler(new DataHandler(fds));

//messageBodyPart1.setFileName(fds.getName());
            messageBodyPart1.setFileName("Rapport_Automatique_CA_NEGOCE.xlsx");
            Multipart mpart = new MimeMultipart();
            mpart.addBodyPart(messageBodyPart1);
            message.setContent(mpart);
            sender.send(message);
        } catch (IOException | MessagingException ex) {
            Logger.getLogger(ChargementServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void produceRapportResumeSheet(XSSFSheet rapportCaResumeSheet, List<ResumeCADateDto> ventesNegoceResumes) {
        int rowId = 0;
        int colId = 0;
        Row row = rapportCaResumeSheet.createRow(rowId++);
        Cell cell;
        cell = row.createCell(colId++);
        cell.setCellValue("CODE_PRODUIT");
        cell.setCellStyle(header);
        cell = row.createCell(colId++);
        cell.setCellValue("NOM_PRODUIT");
        cell.setCellStyle(header);
        cell = row.createCell(colId++);
        cell.setCellValue("QTE_SIGNEE");
        cell.setCellStyle(header);
        cell = row.createCell(colId++);
        cell.setCellValue("CA_HT_SIGNE");
        cell.setCellStyle(header);
        cell = row.createCell(colId++);
        cell.setCellValue("FAMILLE");
        cell.setCellStyle(header);
        cell = row.createCell(colId++);
        cell.setCellValue("CODE_CD");
        cell.setCellStyle(header);
        colId = 0;
        int qteTotale = 0;
        double montantTotal = 0;
        for (ResumeCADateDto ventesNegoceResume : ventesNegoceResumes) {
            row = rapportCaResumeSheet.createRow(rowId++);
            cell = row.createCell(colId++);
            cell.setCellValue(ventesNegoceResume.getCodars().trim());
            cell.setCellStyle(simpleStyle);
            cell = row.createCell(colId++);
            cell.setCellValue(ventesNegoceResume.getNomProduit().trim());
            cell.setCellStyle(simpleStyle);
            cell = row.createCell(colId++);
            cell.setCellValue(ventesNegoceResume.getQuantite());
            cell.setCellStyle(simpleStyle);
            qteTotale += ventesNegoceResume.getQuantite();
            cell = row.createCell(colId++);
            cell.setCellValue(ventesNegoceResume.getPrixHt());
            cell.setCellStyle(simpleStyle);
            cell = row.createCell(colId++);
            cell.setCellValue(ventesNegoceResume.getFamille() == null ? "" : ventesNegoceResume.getFamille().trim());
            cell.setCellStyle(simpleStyle);
            cell = row.createCell(colId++);
            cell.setCellValue(ventesNegoceResume.getCodeCd());
            cell.setCellStyle(simpleStyle);
            montantTotal += ventesNegoceResume.getPrixHt();
            colId = 0;
        }
        colId = 0;
        row = rapportCaResumeSheet.createRow(rowId++);
        cell = row.createCell(colId);
        cell.setCellValue("TOTAL");
        cell.setCellStyle(header);
        rapportCaResumeSheet.addMergedRegion(new CellRangeAddress(
                rowId, //first row (0-based)
                rowId, //last row  (0-based)
                colId, //first column (0-based)
                1 //last column  (0-based)
        ));
        colId = 2;
        cell = row.createCell(colId++);
        cell.setCellValue(qteTotale);
        cell.setCellStyle(header);
        cell = row.createCell(colId++);
        cell.setCellValue(montantTotal);
        cell.setCellStyle(header);

    }

    private void produceRapportDataSheet(XSSFSheet rapportCaDataSheet, List<RepVenteJourCDCircuitGratuitCA> ventesNegoceDatas) {
        int rowId = 0;
        int colId = 0;
        Row row = rapportCaDataSheet.createRow(rowId++);
        Cell cell;
        cell = row.createCell(colId++);
        cell.setCellValue("ANNEE");
        cell.setCellStyle(header);
        cell = row.createCell(colId++);
        cell.setCellValue("MOIS");
        cell.setCellStyle(header);
        cell = row.createCell(colId++);
        cell.setCellValue("JOUR");
        cell.setCellStyle(header);
        cell = row.createCell(colId++);
        cell.setCellValue("CODE_PRODUIT");
        cell.setCellStyle(header);
        cell = row.createCell(colId++);
        cell.setCellValue("NOM_PRODUIT");
        cell.setCellStyle(header);
        cell = row.createCell(colId++);
        cell.setCellValue("QTE_SIGNEE");
        cell.setCellStyle(header);
        cell = row.createCell(colId++);
        cell.setCellValue("PRIX_HT");
        cell.setCellStyle(header);
        cell = row.createCell(colId++);
        cell.setCellValue("TTS");
        cell.setCellStyle(header);
        cell = row.createCell(colId++);
        cell.setCellValue("CODE_CD");
        cell.setCellStyle(header);
        cell = row.createCell(colId++);
        cell.setCellValue("SENS");
        cell.setCellStyle(header);
        cell = row.createCell(colId++);
        cell.setCellValue("TAXE");
        cell.setCellStyle(header);
        cell = row.createCell(colId++);
        cell.setCellValue("NTD");
        cell.setCellStyle(header);
        colId = 0;
        for (RepVenteJourCDCircuitGratuitCA ventesNegoceData : ventesNegoceDatas) {
            row = rapportCaDataSheet.createRow(rowId++);
            cell = row.createCell(colId++);
            cell.setCellValue(ventesNegoceData.getAnnee());
            cell.setCellStyle(simpleStyle);
            cell = row.createCell(colId++);
            cell.setCellValue(ventesNegoceData.getMois());
            cell.setCellStyle(simpleStyle);
            cell = row.createCell(colId++);
            cell.setCellValue(ventesNegoceData.getJour());
            cell.setCellStyle(simpleStyle);
            cell = row.createCell(colId++);
            cell.setCellValue(ventesNegoceData.getCodars().trim());
            cell.setCellStyle(simpleStyle);
            cell = row.createCell(colId++);
            cell.setCellValue(ventesNegoceData.getNomProduit().trim());
            cell.setCellStyle(simpleStyle);
            cell = row.createCell(colId++);
            cell.setCellValue(ventesNegoceData.getCasier());
            cell.setCellStyle(simpleStyle);
            cell = row.createCell(colId++);
            cell.setCellValue(ventesNegoceData.getPrixHt());
            cell.setCellStyle(simpleStyle);
            cell = row.createCell(colId++);
            cell.setCellValue(ventesNegoceData.getTts().trim());
            cell.setCellStyle(simpleStyle);
            cell = row.createCell(colId++);
            cell.setCellValue(ventesNegoceData.getCodeCD());
            cell.setCellStyle(simpleStyle);
            cell = row.createCell(colId++);
            cell.setCellValue(ventesNegoceData.getSens().trim());
            cell.setCellStyle(simpleStyle);
            cell = row.createCell(colId++);
            cell.setCellValue(ventesNegoceData.getTaxar().trim());
            cell.setCellStyle(simpleStyle);
            cell = row.createCell(colId++);
            cell.setCellValue(ventesNegoceData.getNtd());
            cell.setCellStyle(simpleStyle);

            colId = 0;
        }
    }

    private String getMonthName(int mois) {
        String[] months = {"JANVIER", "FEVRIER", "MARS", "AVRIL", "MAI", "JUIN", "JUILLET", "AOUT", "SEPTEMBRE", "OCTOBRE", "NOVEMBRE", "DECEMBRE"};
        return months[mois - 1];
    }

    @Override
    public void produceRapportCaAndMail() {
        try {
            Calendar cal = Calendar.getInstance();
            if (cal.get(Calendar.DAY_OF_MONTH) == 1) {
                cal.add(Calendar.MONTH, -1);
            }
            int annee = cal.get(Calendar.YEAR);
            int mois = (cal.get(Calendar.MONTH) + 1);
            List<ResumeCADateDto> ventesResumes = nativeQueryDao.getCaResumeDataByMonthByYear(mois, annee);
            List<RepVenteJourCDCircuitGratuitCA> ventesDatas = ventesGratuitCDDao.getCaVenteDataByMonthByYear(mois, annee);

            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFFont boldFont = workbook.createFont();
            boldFont.setBold(true);
            header = workbook.createCellStyle();
            header.setFillForegroundColor(HSSFColor.HSSFColorPredefined.GOLD.getIndex());
            header.setBorderColor(XSSFCellBorder.BorderSide.LEFT, b);
            header.setBorderColor(XSSFCellBorder.BorderSide.BOTTOM, b);
            header.setBorderColor(XSSFCellBorder.BorderSide.RIGHT, b);
            header.setBorderColor(XSSFCellBorder.BorderSide.TOP, b);
            header.setAlignment(HorizontalAlignment.CENTER);
            header.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            header.setBorderTop(BorderStyle.THIN);
            header.setBorderLeft(BorderStyle.THIN);
            header.setBorderRight(BorderStyle.THIN);
            header.setBorderBottom(BorderStyle.THIN);

            simpleStyle = workbook.createCellStyle();
            simpleStyle.setBottomBorderColor(blackBorderColor);
            simpleStyle.setLeftBorderColor(blackBorderColor);
            simpleStyle.setTopBorderColor(blackBorderColor);
            simpleStyle.setRightBorderColor(blackBorderColor);
            simpleStyle.setBorderTop(BorderStyle.THIN);
            simpleStyle.setBorderLeft(BorderStyle.THIN);
            simpleStyle.setBorderRight(BorderStyle.THIN);
            simpleStyle.setBorderBottom(BorderStyle.THIN);

            XSSFSheet rapportCaResumeSheet = workbook.createSheet("CA RESUME");
            XSSFSheet rapportCaDataSheet = workbook.createSheet("CA DATA");
            produceRapportResumeSheet(rapportCaResumeSheet, ventesResumes);
            produceRapportDataSheet(rapportCaDataSheet, ventesDatas);
            File file = File.createTempFile("Rapport", "xlsx");
            file.deleteOnExit();
            Path path = file.toPath();
            FileOutputStream fileOut = new FileOutputStream(file);
            /* workbook.write(fileOut);
            fileOut.close();
            DataSource fds = new FileDataSource("temp.xls");
             */
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            workbook.write(bos); // write excel data to a byte array
            fileOut.close();

// Now use your ByteArrayDataSource as
            DataSource fds = new ByteArrayDataSource(bos.toByteArray(), "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            MimeMessage message = sender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);

            helper.setTo(new String[]{"mamadou.salam@castel-afrique.com", "karine.ngondo@castel-afrique.com", "freddy.kalombo@castel-afrique.com", "christian.dikasa@castel-afrique.com"});
            helper.setCc("valmy.roikenfack@castel-afrique.com");
            helper.setSubject("Rapport Automatique CA - " + getMonthName(mois) + " " + annee);
            helper.setFrom("valmy.roikenfack@castel-afrique.com");
            MimeBodyPart messageBodyPart1 = new MimeBodyPart();
            messageBodyPart1.setDataHandler(new DataHandler(fds));

//messageBodyPart1.setFileName(fds.getName());
            String fileName = "Rapport_Automatique_CA__" + getMonthName(mois) + "_" + annee + ".xlsx";
            messageBodyPart1.setFileName(fileName);
            Multipart mpart = new MimeMultipart();
            mpart.addBodyPart(messageBodyPart1);
            message.setContent(mpart);
            sender.send(message);
        } catch (IOException | MessagingException ex) {
            Logger.getLogger(ChargementServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void produceRapportCaAndCautionTournesolSigmaAndMail() {
        try {
            Calendar cal = Calendar.getInstance();
            if (cal.get(Calendar.DAY_OF_MONTH) == 1) {
                cal.add(Calendar.MONTH, -1);
            }
            int annee = cal.get(Calendar.YEAR);
            int mois = (cal.get(Calendar.MONTH) + 1);
            List<RemiseCaCircuitTournesolDto> remisesTournesols = factureDao.getRemiseCaGenereeByCircuitByMonth(annee, mois);
            //List<CaCircuitSigmaDto> caCircuitSigmas = nativeQueryDao.getChiffreDaffaireByCircuit(mois, annee);
            List<CautionSigmaMoisDto> cautionsSigmas = repTransdoDao.getCautionMois(mois, annee);
            //List<CautionSigmaCircuitDto> cautionSigmas = cautionSigmaDao.getCautionByCircuit(mois, annee);
            List<CautionCaBlJourDto> cautionsBlJours = factureDao.getCautionByBlMois(annee, mois);
            List<CautionCaBlDto> cautionsBl = factureDao.getSumCautionByBlMois(annee, mois);
            List<CautionSigmaBlDto> cautionSigmaBl= nativeQueryDao.getCautionSigmaAvecBl(mois, annee);

            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFFont boldFont = workbook.createFont();
            boldFont.setBold(true);
            XSSFFont whiteFont = workbook.createFont();
            whiteFont = workbook.createFont();
            whiteFont.setFontHeightInPoints((short) 11);
            whiteFont.setFontName("Calibri");
            whiteFont.setColor(IndexedColors.WHITE.getIndex());
            whiteFont.setBold(true);
            whiteFont.setItalic(false);
            header = workbook.createCellStyle();
            header.setFillForegroundColor(HSSFColor.HSSFColorPredefined.GOLD.getIndex());
            header.setBorderColor(XSSFCellBorder.BorderSide.LEFT, b);
            header.setBorderColor(XSSFCellBorder.BorderSide.BOTTOM, b);
            header.setBorderColor(XSSFCellBorder.BorderSide.RIGHT, b);
            header.setBorderColor(XSSFCellBorder.BorderSide.TOP, b);
            header.setAlignment(HorizontalAlignment.CENTER);
            header.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            header.setBorderTop(BorderStyle.THIN);
            header.setBorderLeft(BorderStyle.THIN);
            header.setBorderRight(BorderStyle.THIN);
            header.setBorderBottom(BorderStyle.THIN);

            simpleStyle = workbook.createCellStyle();
            simpleStyle.setBottomBorderColor(blackBorderColor);
            simpleStyle.setLeftBorderColor(blackBorderColor);
            simpleStyle.setTopBorderColor(blackBorderColor);
            simpleStyle.setRightBorderColor(blackBorderColor);
            simpleStyle.setBorderTop(BorderStyle.THIN);
            simpleStyle.setBorderLeft(BorderStyle.THIN);
            simpleStyle.setBorderRight(BorderStyle.THIN);
            simpleStyle.setBorderBottom(BorderStyle.THIN);
            
            titleStyle = workbook.createCellStyle();
            titleStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            titleStyle.setFillForegroundColor(IndexedColors.RED.getIndex());
            titleStyle.setAlignment(HorizontalAlignment.CENTER);
            titleStyle.setLeftBorderColor(blackBorderColor);
            titleStyle.setRightBorderColor(blackBorderColor);
            titleStyle.setTopBorderColor(blackBorderColor);
            titleStyle.setBottomBorderColor(blackBorderColor);
            titleStyle.setBorderTop(BorderStyle.THIN);
            titleStyle.setBorderLeft(BorderStyle.THIN);
            titleStyle.setBorderRight(BorderStyle.THIN);
            titleStyle.setBorderBottom(BorderStyle.THIN);
            titleStyle.setFont(whiteFont);
            
            XSSFSheet rapportCationBLDiffSheet = workbook.createSheet("CAUTION BL TOURN VS BL SIGMA");
            XSSFSheet rapportResumeSheet = workbook.createSheet("CAUTION TOURN VS SIGMA");
           
            XSSFSheet cautionBlSheet = workbook.createSheet("CAUTION PAR BL TOURNESOL");
            XSSFSheet cautionBlSigmaSheet = workbook.createSheet("CAUTION PAR BL SIGMA");
            //produceRapportResumeTourSigmaSheet(rapportResumeSheet, remisesTournesols, caCircuitSigmas, cautionSigmas);
            produceRapportDiffCautionBlSheet(rapportCationBLDiffSheet, cautionsBl, cautionSigmaBl);
            produceRapportResumeTourSigmaSheet(rapportResumeSheet, remisesTournesols, cautionsSigmas);            
            produceCautionBlSheet(cautionBlSheet, cautionsBlJours);
            produceCautionBlSigmaSheet(cautionBlSigmaSheet, cautionSigmaBl);
            File file = File.createTempFile("Rapport", "xlsx");
            file.deleteOnExit();
            Path path = file.toPath();
            FileOutputStream fileOut = new FileOutputStream(file);
            /* workbook.write(fileOut);
            fileOut.close();
            DataSource fds = new FileDataSource("temp.xls");
             */
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            workbook.write(bos); // write excel data to a byte array
            fileOut.close();

// Now use your ByteArrayDataSource as
            DataSource fds = new ByteArrayDataSource(bos.toByteArray(), "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            MimeMessage message = sender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);

            helper.setTo(new String[]{"bellarmin.gatembo@castel-afrique.com", "pablo.lusala@castel-afrique.com", "miller.ngidiki@castel-afrique.com", "kennedy.mukendi@castel-afrique.com", "gina.mputela@castel-afrique.com", "christelle.kika@castel-afrique.com", "innocent.gbala@castel-afrique.com", "marlon.mahwamawesi@castel-afrique.com", "timothee.moko@castel-afrique.com", "melina.biselele@castel-afrique.com", "gildas.diasilua@castel-afrique.com", "ben.kamayi@castel-afrique.com", "alain.mutombo@castel-afrique.com", "roger.ngiembo@castel-afrique.com", "joseph.lutete@castel-afrique.com", "van.kasombo@castel-afrique.com", "mamie.lukoki@castel-afrique.com", "alexis.biondomfundu@castel-afrique.com", "justin.bokole@castel-afrique.com", "felix.kabenawakabena@castel-afrique.com", "christian.kotshi@castel-afrique.com", "japhet.mangidi@castel-afrique.com"});
            helper.setCc(new String[]{"anaclet.lawaba@castel-afrique.com","guelor.nkulu@castel-afrique.com","anselme.pero@castel-afrique.com", "mamadou.salam@castel-afrique.com","frederic.kenge@castel-afrique.com", "passy.lufukona@castel-afrique.com", "moise.mbaya@castel-afrique.com", "karine.ngondo@castel-afrique.com",  "eric.goulongana@castel-afrique.com",  "valmy.roikenfack@castel-afrique.com"});
            helper.setSubject("Rapport Automatique CAUTION TOURN VS SIGMA - " + getMonthName(mois) + " " + annee);
            helper.setFrom("BRACONGO.Reportbusiness@castel-afrique.com");
            //helper.setText("Bonjour \n En PJ, le rapprochement caution tournesol - caution sigma " + getMonthName(mois) + " - " + annee + " pour analyse et recherche des justifications d'écarts. \n"+ " Vous avez le comparatif au cumul mois par circuit (feuille 1) et le comparatif par BL (feuille 2)" + "Cordialement" , true);
            //helper.
            message.setText("Bonjour \n En PJ, le rapprochement caution tournesol - caution sigma " + getMonthName(mois) + " - " + annee + " pour analyse et recherche des justifications d'écarts. \n"+ " Vous avez le comparatif au cumul mois par circuit (feuille 1) et le comparatif par BL (feuille 2)" + "Cordialement"  , "UTF-8", "html");
            MimeBodyPart messageBodyPart1 = new MimeBodyPart();
            messageBodyPart1.setDataHandler(new DataHandler(fds));

//messageBodyPart1.setFileName(fds.getName());
            String fileName = "Rapport_Automatique_CAUTION_TOURN_VS_SIGMA__" + getMonthName(mois) + "_" + annee + ".xlsx";
            messageBodyPart1.setFileName(fileName);
            Multipart mpart = new MimeMultipart();
            mpart.addBodyPart(messageBodyPart1);
            message.setContent(mpart);
            sender.send(message);
        } catch (IOException | MessagingException ex) {
            Logger.getLogger(ChargementServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
/*
    private void produceRapportResumeTourSigmaSheet(XSSFSheet rapportResumeSheet, List<RemiseCaCircuitTournesolDto> remisesTournesols, List<CaCircuitSigmaDto> caCircuitSigmas, List<CautionSigmaCircuitDto> cautionSigmas) {
        Calendar cal = Calendar.getInstance();
        int maxDay = 0;
        if (cal.get(Calendar.DAY_OF_MONTH) == 1) {
            cal.add(Calendar.MONTH, -1);
        } else {
            cal.add(Calendar.DAY_OF_MONTH, -1);
            maxDay = cal.get(Calendar.DAY_OF_MONTH);
        }

        HashMap<String, String> circuits = getListCircuit(remisesTournesols);

        int rowId = 0;
        int colId = 0;
        Cell cell;
        Row row = rapportResumeSheet.createRow(rowId);
        cell = row.createCell(colId);
        cell.setCellValue("RAPPORT CA TTC ET CAUTION TOURNESOL VS SIGMA - ");
        cell.setCellStyle(titleStyle);
        rapportResumeSheet.addMergedRegion(new CellRangeAddress(
                rowId, //first row (0-based)
                rowId, //last row  (0-based)
                colId, //first column (0-based)
                8 //last column  (0-based)
        ));

        colId = 0;
        rowId++;

        row = rapportResumeSheet.createRow(rowId);
        cell = row.createCell(colId++);
        cell.setCellValue("CD");
        cell.setCellStyle(header);

        cell = row.createCell(colId++);
        cell.setCellValue("CIRCUIT");
        cell.setCellStyle(header);

        cell = row.createCell(colId);
        cell.setCellValue("CHIFFRE AFFAIRE");
        cell.setCellStyle(header);
        rapportResumeSheet.addMergedRegion(new CellRangeAddress(
                rowId, //first row (0-based)
                rowId, //last row  (0-based)
                colId, //first column (0-based)
                colId + 2 //last column  (0-based)
        ));
        colId += 3;
        cell = row.createCell(colId);
        cell.setCellValue("CAUTION");
        cell.setCellStyle(header);
        rapportResumeSheet.addMergedRegion(new CellRangeAddress(
                rowId, //first row (0-based)
                rowId, //last row  (0-based)
                colId, //first column (0-based)
                colId + 2 //last column  (0-based)
        ));
        rowId++;
        row = rapportResumeSheet.createRow(rowId);
        colId = 2;
        cell = row.createCell(colId++);
        cell.setCellValue("TOURN");
        cell.setCellStyle(simpleStyle);
        cell = row.createCell(colId++);
        cell.setCellValue("SIGMA");
        cell.setCellStyle(simpleStyle);
        cell = row.createCell(colId++);
        cell.setCellValue("DIFF");
        cell.setCellStyle(simpleStyle);
        cell = row.createCell(colId++);
        cell.setCellValue("TOURN");
        cell.setCellStyle(simpleStyle);
        cell = row.createCell(colId++);
        cell.setCellValue("SIGMA");
        cell.setCellStyle(simpleStyle);
        cell = row.createCell(colId++);
        cell.setCellValue("DIFF");
        cell.setCellStyle(simpleStyle);

        rowId++;
        colId = 0;

        double caTotalTourn = 0;
        double remiseTotaleTourn = 0;
        double caTotalSigma = 0;
        double remiseTotaleSigma = 0;
        for (Map.Entry<String, String> entry : circuits.entrySet()) {
            String nomCd = entry.getValue();
            row = rapportResumeSheet.createRow(rowId);
            cell = row.createCell(colId++);
            cell.setCellValue(nomCd);
            cell.setCellStyle(simpleStyle);

            cell = row.createCell(colId++);
            cell.setCellValue(entry.getKey().trim());
            cell.setCellStyle(simpleStyle);

            RemiseCaCircuitTournesolDto remiseCaTourn = getRemiseCaTournesolByCircuit(remisesTournesols, entry.getKey().trim());
            caTotalTourn += remiseCaTourn.getCa();
            remiseTotaleTourn += remiseCaTourn.getRemise();
            double remiseSigma = getCautionSigmaByCircuit(cautionSigmas, entry.getKey().trim());
            remiseTotaleSigma += remiseSigma;
            double caSigma = getCASigmaByCircuit(caCircuitSigmas, entry.getKey().trim());
            caTotalSigma += caSigma;

            //System.out.println("jour = "+ jour + " ca tourn = " + remiseCaTourn.getCa() + " ca sigma = " + caSigma + " remise tourn = " + remiseCaTourn.getRemise() + " remise sigma = " + remiseSigma );
            cell = row.createCell(colId++);
            cell.setCellValue(remiseCaTourn.getCa());
            cell.setCellStyle(simpleStyle);

            cell = row.createCell(colId++);
            cell.setCellValue(caSigma);
            cell.setCellStyle(simpleStyle);

            cell = row.createCell(colId++);
            cell.setCellValue(remiseCaTourn.getCa() + caSigma);
            cell.setCellStyle(simpleStyle);

            cell = row.createCell(colId++);
            cell.setCellValue(remiseCaTourn.getRemise());
            cell.setCellStyle(simpleStyle);

            cell = row.createCell(colId++);
            cell.setCellValue(remiseSigma);
            cell.setCellStyle(simpleStyle);

            cell = row.createCell(colId++);
            cell.setCellValue(remiseCaTourn.getRemise() - remiseSigma);
            cell.setCellStyle(simpleStyle);

            rowId++;
            colId = 0;

        }

        colId = 0;
        row = rapportResumeSheet.createRow(rowId);
        cell = row.createCell(colId);
        cell.setCellValue("TOTAL");
        cell.setCellStyle(header);
        rapportResumeSheet.addMergedRegion(new CellRangeAddress(
                rowId, //first row (0-based)
                rowId, //last row  (0-based)
                colId, //first column (0-based)
                colId + 2 //last column  (0-based)
        ));
        colId = 3;
        cell = row.createCell(colId++);
        cell.setCellValue(caTotalTourn);
        cell.setCellStyle(header);
        cell = row.createCell(colId++);
        cell.setCellValue(caTotalSigma);
        cell.setCellStyle(header);
        cell = row.createCell(colId++);
        cell.setCellValue(caTotalTourn + caTotalSigma);
        cell.setCellStyle(header);

        cell = row.createCell(colId++);
        cell.setCellValue(remiseTotaleTourn);
        cell.setCellStyle(header);
        cell = row.createCell(colId++);
        cell.setCellValue(remiseTotaleSigma);
        cell.setCellStyle(header);
        cell = row.createCell(colId++);
        cell.setCellValue(remiseTotaleTourn - remiseTotaleSigma);
        cell.setCellStyle(header);

    }
*/
    /*
    @Override
    public void produceRapportCaAndCautionTournesolSigmaAndMail() {
        try {
            Calendar cal = Calendar.getInstance();
            if (cal.get(Calendar.DAY_OF_MONTH) == 1) {
                cal.add(Calendar.MONTH, -1);
            }
            int annee = cal.get(Calendar.YEAR);
            int mois = (cal.get(Calendar.MONTH) + 1);
            List<RemiseCaCircuitTournesolDto> remisesTournesols = factureDao.getRemiseCaGenereeByCircuitByMonth(annee, mois);
            List<CaCircuitSigmaDto> caCircuitSigmas = nativeQueryDao.getChiffreDaffaireByCircuit(mois, annee);
            List<CautionSigmaCircuitDto> cautionSigmas = cautionSigmaDao.getCautionByCircuit(mois, annee);

            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFFont boldFont = workbook.createFont();
            boldFont.setBold(true);
            header = workbook.createCellStyle();
            header.setFillForegroundColor(HSSFColor.HSSFColorPredefined.GOLD.getIndex());
            header.setBorderColor(XSSFCellBorder.BorderSide.LEFT, b);
            header.setBorderColor(XSSFCellBorder.BorderSide.BOTTOM, b);
            header.setBorderColor(XSSFCellBorder.BorderSide.RIGHT, b);
            header.setBorderColor(XSSFCellBorder.BorderSide.TOP, b);
            header.setAlignment(HorizontalAlignment.CENTER);
            header.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            header.setBorderTop(BorderStyle.THIN);
            header.setBorderLeft(BorderStyle.THIN);
            header.setBorderRight(BorderStyle.THIN);
            header.setBorderBottom(BorderStyle.THIN);

            simpleStyle = workbook.createCellStyle();
            simpleStyle.setBottomBorderColor(blackBorderColor);
            simpleStyle.setLeftBorderColor(blackBorderColor);
            simpleStyle.setTopBorderColor(blackBorderColor);
            simpleStyle.setRightBorderColor(blackBorderColor);
            simpleStyle.setBorderTop(BorderStyle.THIN);
            simpleStyle.setBorderLeft(BorderStyle.THIN);
            simpleStyle.setBorderRight(BorderStyle.THIN);
            simpleStyle.setBorderBottom(BorderStyle.THIN);

            XSSFSheet rapportResumeSheet = workbook.createSheet("RESUME");
            produceRapportResumeTourSigmaSheet(rapportResumeSheet, remisesTournesols, caCircuitSigmas, cautionSigmas);
            File file = File.createTempFile("Rapport", "xlsx");
            file.deleteOnExit();
            Path path = file.toPath();
            FileOutputStream fileOut = new FileOutputStream(file);
            /* workbook.write(fileOut);
            fileOut.close();
            DataSource fds = new FileDataSource("temp.xls");
     */
 /*          ByteArrayOutputStream bos = new ByteArrayOutputStream();
            workbook.write(bos); // write excel data to a byte array
            fileOut.close();

// Now use your ByteArrayDataSource as
            DataSource fds = new ByteArrayDataSource(bos.toByteArray(), "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            MimeMessage message = sender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);

            helper.setTo(new String[]{"valmy.roikenfack@castel-afrique.com"});
            helper.setCc("valmy.roikenfack@castel-afrique.com");
            helper.setSubject("Rapport Automatique CA CAUTION TOURN VS SIGMA - " + getMonthName(mois) + " " + annee);
            helper.setFrom("valmy.roikenfack@castel-afrique.com");
            MimeBodyPart messageBodyPart1 = new MimeBodyPart();
            messageBodyPart1.setDataHandler(new DataHandler(fds));

//messageBodyPart1.setFileName(fds.getName());
            String fileName = "Rapport_Automatique_CA_CAUTION_TOURN_VS_SIGMA__" + getMonthName(mois) + "_" + annee + ".xlsx";
            messageBodyPart1.setFileName(fileName);
            Multipart mpart = new MimeMultipart();
            mpart.addBodyPart(messageBodyPart1);
            message.setContent(mpart);
            sender.send(message);
        } catch (IOException | MessagingException ex) {
            Logger.getLogger(ChargementServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     */

 /*private void produceRapportResumeTourSigmaSheet(XSSFSheet rapportResumeSheet, List<RemiseCaCircuitTournesolDto> remisesTournesols, List<CaCircuitSigmaDto> caCircuitSigmas, List<CautionSigmaCircuitDto> cautionSigmas) {
        Calendar cal = Calendar.getInstance();
        int maxDay = 0;
        if (cal.get(Calendar.DAY_OF_MONTH) == 1) {
            cal.add(Calendar.MONTH, -1);
        } else {
            cal.add(Calendar.DAY_OF_MONTH, -1);
            maxDay = cal.get(Calendar.DAY_OF_MONTH);
        }

        HashMap<String, String> circuits = getListCircuit(remisesTournesols);

        int rowId = 0;
        int colId = 0;
        Cell cell;
        Row row = rapportResumeSheet.createRow(rowId);
        cell = row.createCell(colId);
        cell.setCellValue("RAPPORT CA ET CAUTION TOURNESOL VS SIGMA - ");
        cell.setCellStyle(titleStyle);
        rapportResumeSheet.addMergedRegion(new CellRangeAddress(
                rowId, //first row (0-based)
                rowId, //last row  (0-based)
                colId, //first column (0-based)
                8 //last column  (0-based)
        ));
        
        colId = 0;
        rowId++;

        row = rapportResumeSheet.createRow(rowId);
        cell = row.createCell(colId++);
        cell.setCellValue("CD");
        cell.setCellStyle(header);

        cell = row.createCell(colId++);
        cell.setCellValue("CIRCUIT");
        cell.setCellStyle(header);

        cell = row.createCell(colId++);
        cell.setCellValue("JOUR");
        cell.setCellStyle(header);

        cell = row.createCell(colId);
        cell.setCellValue("CHIFFRE AFFAIRE");
        cell.setCellStyle(header);
        rapportResumeSheet.addMergedRegion(new CellRangeAddress(
                rowId, //first row (0-based)
                rowId, //last row  (0-based)
                colId, //first column (0-based)
                colId + 2 //last column  (0-based)
        ));
        colId += 3;
        cell = row.createCell(colId);
        cell.setCellValue("CAUTION");
        cell.setCellStyle(header);
        rapportResumeSheet.addMergedRegion(new CellRangeAddress(
                rowId, //first row (0-based)
                rowId, //last row  (0-based)
                colId, //first column (0-based)
                colId + 2 //last column  (0-based)
        ));
        rowId++;
        row = rapportResumeSheet.createRow(rowId);
        colId = 3;
        cell = row.createCell(colId++);
        cell.setCellValue("TOURN");
        cell.setCellStyle(simpleStyle);
        cell = row.createCell(colId++);
        cell.setCellValue("SIGMA");
        cell.setCellStyle(simpleStyle);
        cell = row.createCell(colId++);
        cell.setCellValue("DIFF");
        cell.setCellStyle(simpleStyle);
        cell = row.createCell(colId++);
        cell.setCellValue("TOURN");
        cell.setCellStyle(simpleStyle);
        cell = row.createCell(colId++);
        cell.setCellValue("SIGMA");
        cell.setCellStyle(simpleStyle);
        cell = row.createCell(colId++);
        cell.setCellValue("DIFF");
        cell.setCellStyle(simpleStyle);

        rowId++;
        colId = 0;

        double caTotalTourn = 0;
        double remiseTotaleTourn = 0;
        double caTotalSigma = 0;
        double remiseTotaleSigma = 0;
        for (int jour = 1; jour <= maxDay; jour++) {
            for (Map.Entry<String, String> entry : circuits.entrySet()) {
                String nomCd = entry.getValue();
                row = rapportResumeSheet.createRow(rowId);
                cell = row.createCell(colId++);
                cell.setCellValue(nomCd);
                cell.setCellStyle(simpleStyle);

                cell = row.createCell(colId++);
                cell.setCellValue(entry.getKey().trim());
                cell.setCellStyle(simpleStyle);
                
                cell = row.createCell(colId++);
                cell.setCellValue(jour);
                cell.setCellStyle(simpleStyle);

                RemiseCaCircuitTournesolDto remiseCaTourn = getRemiseCaTournesolByCircuitForDay(remisesTournesols, entry.getKey().trim(), jour);
                caTotalTourn += remiseCaTourn.getCa();
                remiseTotaleTourn += remiseCaTourn.getRemise();
                double remiseSigma = getCautionSigmaByCircuitForDay(cautionSigmas, entry.getKey().trim(), jour);
                remiseTotaleSigma += remiseSigma;
                double caSigma = getCASigmaByCircuitForDay(caCircuitSigmas, entry.getKey().trim(), jour);
                caTotalSigma += caSigma;

                //System.out.println("jour = "+ jour + " ca tourn = " + remiseCaTourn.getCa() + " ca sigma = " + caSigma + " remise tourn = " + remiseCaTourn.getRemise() + " remise sigma = " + remiseSigma );
                cell = row.createCell(colId++);
                cell.setCellValue(remiseCaTourn.getCa());
                cell.setCellStyle(simpleStyle);
                
                cell = row.createCell(colId++);
                cell.setCellValue(caSigma);
                cell.setCellStyle(simpleStyle);
                
                cell = row.createCell(colId++);
                cell.setCellValue(remiseCaTourn.getCa() + caSigma);
                cell.setCellStyle(simpleStyle);

                cell = row.createCell(colId++);
                cell.setCellValue(remiseCaTourn.getRemise());
                cell.setCellStyle(simpleStyle);
                
                cell = row.createCell(colId++);
                cell.setCellValue(remiseSigma);
                cell.setCellStyle(simpleStyle);
                
                cell = row.createCell(colId++);
                cell.setCellValue(remiseCaTourn.getRemise() - remiseSigma);
                cell.setCellStyle(simpleStyle);

                rowId++;
                colId = 0;

            }
        }

        colId = 0;
        row = rapportResumeSheet.createRow(rowId);
        cell = row.createCell(colId);
        cell.setCellValue("TOTAL");
        cell.setCellStyle(header);
        rapportResumeSheet.addMergedRegion(new CellRangeAddress(
                rowId, //first row (0-based)
                rowId, //last row  (0-based)
                colId, //first column (0-based)
                colId + 2 //last column  (0-based)
        ));
        colId = 3;
        cell = row.createCell(colId++);
        cell.setCellValue(caTotalTourn);
        cell.setCellStyle(header);
        cell = row.createCell(colId++);
        cell.setCellValue(caTotalSigma);
        cell.setCellStyle(header);
        cell = row.createCell(colId++);
        cell.setCellValue(caTotalTourn + caTotalSigma);
        cell.setCellStyle(header);

        cell = row.createCell(colId++);
        cell.setCellValue(remiseTotaleTourn);
        cell.setCellStyle(header);
        cell = row.createCell(colId++);
        cell.setCellValue(remiseTotaleSigma);
        cell.setCellStyle(header);
        cell = row.createCell(colId++);
        cell.setCellValue(remiseTotaleTourn - remiseTotaleSigma);
        cell.setCellStyle(header);

    }
     */
    private HashMap<String, String> getListCircuit(List<RemiseCaCircuitTournesolDto> remisesTournesols) {
        HashMap<String, String> result = new HashMap<>();
        remisesTournesols.forEach((remisesTournesol) -> {
            result.put(remisesTournesol.getCodeCircuitSigma(), remisesTournesol.getNomCd());
        });
        return result;
    }

 /*   private double getCASigmaByCircuit(List<CaCircuitSigmaDto> caCircuitSigmas, String codeCircuit) {

        for (CaCircuitSigmaDto caCircuitSigma : caCircuitSigmas) {
            if ((caCircuitSigma.getCodeCircuit().trim()).equalsIgnoreCase(codeCircuit)) {
                return caCircuitSigma.getChiffreAffaire();
            }
        }
        return 0;
    }*/

    private double getCASigmaByCircuitForDay(List<CaCircuitSigmaDto> caCircuitSigmas, String codeCircuit, int jour) {

        for (CaCircuitSigmaDto caCircuitSigma : caCircuitSigmas) {
            if ((caCircuitSigma.getCodeCircuit().trim()).equalsIgnoreCase(codeCircuit) && caCircuitSigma.getJour() == jour) {
                return caCircuitSigma.getChiffreAffaire();
            }
        }
        return 0;
    }

  /*  private double getCautionSigmaByCircuit(List<CautionSigmaCircuitDto> cautionSigmas, String codeCircuit) {

        for (CautionSigmaCircuitDto cautionSigma : cautionSigmas) {
            if ((cautionSigma.getCodeCircuit().trim()).equalsIgnoreCase(codeCircuit)) {
                return cautionSigma.getCaution();
            }
        }
        return 0;
    }
    */

    private double getCautionSigmaByCircuitForDay(List<CautionSigmaCircuitDto> cautionSigmas, String codeCircuit, int jour) {

        for (CautionSigmaCircuitDto cautionSigma : cautionSigmas) {
            if ((cautionSigma.getCodeCircuit().trim()).equalsIgnoreCase(codeCircuit) && cautionSigma.getJour() == jour) {
                return cautionSigma.getCaution();
            }
        }
        return 0;
    }

    private RemiseCaCircuitTournesolDto getRemiseCaTournesolByCircuit(List<RemiseCaCircuitTournesolDto> remisesTournesols, String codeCircuit) {

        for (RemiseCaCircuitTournesolDto remisesTournesol : remisesTournesols) {
            if ((remisesTournesol.getCodeCircuitSigma().trim()).equalsIgnoreCase(codeCircuit)) {
                return remisesTournesol;
            }
        }
        return new RemiseCaCircuitTournesolDto();
    }

    private RemiseCaCircuitTournesolDto getRemiseCaTournesolByCircuitForDay(List<RemiseCaCircuitTournesolDto> remisesTournesols, String codeCircuit, int jour) {

        for (RemiseCaCircuitTournesolDto remisesTournesol : remisesTournesols) {
            if ((remisesTournesol.getCodeCircuitSigma().trim()).equalsIgnoreCase(codeCircuit) && remisesTournesol.getJour() == jour) {
                return remisesTournesol;
            }
        }
        return new RemiseCaCircuitTournesolDto();
    }

    private void produceRapportResumeTourSigmaSheet(XSSFSheet rapportResumeSheet, List<RemiseCaCircuitTournesolDto> remisesTournesols, List<CautionSigmaMoisDto> cautionsSigmas) {
        Calendar cal = Calendar.getInstance();
        int maxDay = 0;
        if (cal.get(Calendar.DAY_OF_MONTH) == 1) {
            cal.add(Calendar.MONTH, -1);
        } else {
            cal.add(Calendar.DAY_OF_MONTH, -1);
            maxDay = cal.get(Calendar.DAY_OF_MONTH);
        }

        HashMap<String, String> circuits = getListCircuit(remisesTournesols);

        int rowId = 0;
        int colId = 0;
        Cell cell;
        Row row = rapportResumeSheet.createRow(rowId);
        cell = row.createCell(colId);
        cell.setCellValue("RAPPORT CAUTION TOURNESOL VS SIGMA ");
        cell.setCellStyle(titleStyle);
        rapportResumeSheet.addMergedRegion(new CellRangeAddress(
                rowId, //first row (0-based)
                rowId, //last row  (0-based)
                colId, //first column (0-based)
                7 //last column  (0-based)
        ));

        colId = 0;
        rowId++;

        row = rapportResumeSheet.createRow(rowId);

        cell = row.createCell(colId++);
        cell.setCellValue("CODE_CD");
        cell.setCellStyle(header);

        cell = row.createCell(colId++);
        cell.setCellValue("CD");
        cell.setCellStyle(header);

        cell = row.createCell(colId++);
        cell.setCellValue("CIRCUIT");
        cell.setCellStyle(header);

        cell = row.createCell(colId++);
        cell.setCellValue("CODE_CIRCUIT");
        cell.setCellStyle(header);

        cell = row.createCell(colId++);
        cell.setCellValue("CAUTION_TOURNESOL");
        cell.setCellStyle(header);

        cell = row.createCell(colId++);
        cell.setCellValue("CAUTION_SIGMA");
        cell.setCellStyle(header);

        cell = row.createCell(colId++);
        cell.setCellValue("DIFFERENCE");
        cell.setCellStyle(header);

        rowId++;
        colId = 0;

        double caTotalTourn = 0;
        double remiseTotaleTourn = 0;
        double caTotalSigma = 0;
        double remiseTotaleSigma = 0;
        for (Map.Entry<String, String> entry : circuits.entrySet()) {
            RemiseCaCircuitTournesolDto remiseCaTourn = getRemiseCaTournesolByCircuit(remisesTournesols, entry.getKey().trim());
            double cautionSigma = getCutionSigmaByCircuit(cautionsSigmas, remiseCaTourn.getCodeCircuit().trim());
            double remiseTourn = remiseCaTourn.getRemise();
            String nomCd = entry.getValue();
            row = rapportResumeSheet.createRow(rowId ++);

            cell = row.createCell(colId++);
            cell.setCellValue(remiseCaTourn.getCodeCd());
            cell.setCellStyle(simpleStyle);

            cell = row.createCell(colId++);
            cell.setCellValue(remiseCaTourn.getNomCd().trim());
            cell.setCellStyle(simpleStyle);

            cell = row.createCell(colId++);
            cell.setCellValue(remiseCaTourn.getCodeCircuitSigma().trim());
            cell.setCellStyle(simpleStyle);

            cell = row.createCell(colId++);
            cell.setCellValue(remiseCaTourn.getCodeCircuit().trim());
            cell.setCellStyle(simpleStyle);

            cell = row.createCell(colId++);
            cell.setCellValue(remiseTourn);
            cell.setCellStyle(simpleStyle);

            cell = row.createCell(colId++);
            cell.setCellValue(cautionSigma);
            cell.setCellStyle(simpleStyle);

            cell = row.createCell(colId++);
            cell.setCellValue(remiseTourn - cautionSigma);
            cell.setCellStyle(simpleStyle);
            colId = 0;
            
        }

    }

    private void produceCautionBlSheet(XSSFSheet cautionBlSheet, List<CautionCaBlJourDto> cautionsBl) {
        int rowId = 0;
        int colId = 0;
        Cell cell;
        Row row = cautionBlSheet.createRow(rowId);

        cell = row.createCell(colId++);
        cell.setCellValue("CODE_CD");
        cell.setCellStyle(header);
        cell = row.createCell(colId++);
        cell.setCellValue("NOM_CD");
        cell.setCellStyle(header);
        cell = row.createCell(colId++);
        cell.setCellValue("CIRCUIT");
        cell.setCellStyle(header);
        cell = row.createCell(colId++);
        cell.setCellValue("CODE_CIRCUIT");
        cell.setCellStyle(header);
        cell = row.createCell(colId++);
        cell.setCellValue("JOUR");
        cell.setCellStyle(header);
        cell = row.createCell(colId++);
        cell.setCellValue("MOIS");
        cell.setCellStyle(header);
        cell = row.createCell(colId++);
        cell.setCellValue("BL");
        cell.setCellStyle(header);
        cell = row.createCell(colId++);
        cell.setCellValue("CA");
        cell.setCellStyle(header);
        cell = row.createCell(colId++);
        cell.setCellValue("CAUTION");
        cell.setCellStyle(header);

        rowId++;
        colId = 0;

        for (CautionCaBlJourDto cautionCaBlDto : cautionsBl) {
            row = cautionBlSheet.createRow(rowId++);
            cell = row.createCell(colId++);
            cell.setCellValue(cautionCaBlDto.getCodeCd().trim());
            cell.setCellStyle(simpleStyle);
            cell = row.createCell(colId++);
            cell.setCellValue(cautionCaBlDto.getNomCd().trim());
            cell.setCellStyle(simpleStyle);
            cell = row.createCell(colId++);
            cell.setCellValue(cautionCaBlDto.getCircuit().trim());
            cell.setCellStyle(simpleStyle);
            cell = row.createCell(colId++);
            cell.setCellValue(cautionCaBlDto.getCodeCircuit().trim());
            cell.setCellStyle(simpleStyle);
            cell = row.createCell(colId++);
            cell.setCellValue(cautionCaBlDto.getJour());
            cell.setCellStyle(simpleStyle);
            cell = row.createCell(colId++);
            cell.setCellValue(cautionCaBlDto.getMois());
            cell.setCellStyle(simpleStyle);
            cell = row.createCell(colId++);
            cell.setCellValue(cautionCaBlDto.getBl().trim());
            cell.setCellStyle(simpleStyle);
            cell = row.createCell(colId++);
            cell.setCellValue(cautionCaBlDto.getCa());
            cell.setCellStyle(simpleStyle);
            cell = row.createCell(colId++);
            cell.setCellValue(cautionCaBlDto.getCaution());
            cell.setCellStyle(simpleStyle);
            colId = 0;
        }
    }

    private double getCutionSigmaByCircuit(List<CautionSigmaMoisDto> cautionsSigmas, String trim) {
       for (CautionSigmaMoisDto cautionSigma : cautionsSigmas) {
            if ((cautionSigma.getCodeCircuit().trim()).equalsIgnoreCase(trim)) {
                return cautionSigma.getCaution();
            }
        }
        return 0;
    }

    private void produceRapportDiffCautionBlSheet(XSSFSheet rapportCationBLDiffSheet, List<CautionCaBlDto> cautionsBl, List<CautionSigmaBlDto> cautionSigmaBl) {
        int rowId = 0;
        int colId = 0;
        Cell cell;
        Row row = rapportCationBLDiffSheet.createRow(rowId);

        cell = row.createCell(colId++);
        cell.setCellValue("CODE_CD");
        cell.setCellStyle(header);
        cell = row.createCell(colId++);
        cell.setCellValue("NOM_CD");
        cell.setCellStyle(header);
        cell = row.createCell(colId++);
        cell.setCellValue("CIRCUIT");
        cell.setCellStyle(header);
        cell = row.createCell(colId++);
        cell.setCellValue("CODE_CIRCUIT");
        cell.setCellStyle(header);
        cell = row.createCell(colId++);
        cell.setCellValue("JOUR");
        cell.setCellStyle(header);
        cell = row.createCell(colId++);
        cell.setCellValue("MOIS");
        cell.setCellStyle(header);
        cell = row.createCell(colId++);
        cell.setCellValue("BL");
        cell.setCellStyle(header);
        cell = row.createCell(colId++);
        cell.setCellValue("CAUTION_TOURNESOL");
        cell.setCellStyle(header);
        cell = row.createCell(colId++);
        cell.setCellValue("CAUTION_SIGMA");
        cell.setCellStyle(header);
        cell = row.createCell(colId++);
        cell.setCellValue("DIFF");
        cell.setCellStyle(header);

        rowId++;
        colId = 0;

        for (CautionCaBlDto cautionCaBlDto : cautionsBl) {
            row = rapportCationBLDiffSheet.createRow(rowId++);
            CautionSigmaBlDto cautionSigma  =  getCautionSimaByBlCd(cautionSigmaBl, cautionCaBlDto.getBl().trim(), cautionCaBlDto.getCircuit().trim());
            cell = row.createCell(colId++);
            cell.setCellValue(cautionCaBlDto.getCodeCd().trim());
            cell.setCellStyle(simpleStyle);
            cell = row.createCell(colId++);
            cell.setCellValue(cautionCaBlDto.getNomCd().trim());
            cell.setCellStyle(simpleStyle);
            cell = row.createCell(colId++);
            cell.setCellValue(cautionCaBlDto.getCircuit().trim());
            cell.setCellStyle(simpleStyle);
            cell = row.createCell(colId++);
            cell.setCellValue(cautionCaBlDto.getCodeCircuit().trim());
            cell.setCellStyle(simpleStyle);
            cell = row.createCell(colId++);
            cell.setCellValue(cautionSigma.getJour());
            cell.setCellStyle(simpleStyle);
            cell = row.createCell(colId++);
            cell.setCellValue(cautionCaBlDto.getMois());
            cell.setCellStyle(simpleStyle);
            cell = row.createCell(colId++);
            cell.setCellValue(cautionCaBlDto.getBl().trim());
            cell.setCellStyle(simpleStyle);
            cell = row.createCell(colId++);
            cell.setCellValue(cautionCaBlDto.getCaution());
            cell.setCellStyle(simpleStyle);
            cell = row.createCell(colId++);
            cell.setCellValue(cautionSigma.getCation());
            cell.setCellStyle(simpleStyle);
            cell = row.createCell(colId++);
            cell.setCellValue(cautionCaBlDto.getCaution() - cautionSigma.getCation());
            cell.setCellStyle(simpleStyle);
            colId = 0;
        }
    }
    
    private CautionSigmaBlDto getCautionSimaByBlCd(List<CautionSigmaBlDto> cautionSigmaBl, String bl, String codeCd){
        for (CautionSigmaBlDto cautionSigmaBlDto : cautionSigmaBl) {
            if((cautionSigmaBlDto.getNtdTransaction().trim()).equalsIgnoreCase(bl) && (cautionSigmaBlDto.getCircuit().trim()).equalsIgnoreCase(codeCd))
                return cautionSigmaBlDto;
        }
        return new CautionSigmaBlDto();
    }

    private void produceCautionBlSigmaSheet(XSSFSheet cautionBlSigmaSheet, List<CautionSigmaBlDto> cautionSigmaBl) {
        int rowId = 0;
        int colId = 0;
        Cell cell;
        Row row = cautionBlSigmaSheet.createRow(rowId);

        cell = row.createCell(colId++);
        cell.setCellValue("CODE_CD");
        cell.setCellStyle(header);
        cell = row.createCell(colId++);
        cell.setCellValue("CIRCUIT");
        cell.setCellStyle(header);
        cell = row.createCell(colId++);
        cell.setCellValue("JOUR");
        cell.setCellStyle(header);
        cell = row.createCell(colId++);
        cell.setCellValue("MOIS");
        cell.setCellStyle(header);
        cell = row.createCell(colId++);
        cell.setCellValue("BL");
        cell.setCellStyle(header);
        cell = row.createCell(colId++);
        cell.setCellValue("CA");
        cell.setCellStyle(header);
        cell = row.createCell(colId++);
        cell.setCellValue("CAUTION");
        cell.setCellStyle(header);
        cell = row.createCell(colId++);
        cell.setCellValue("CA TTC");
        cell.setCellStyle(header);

        rowId++;
        colId = 0;

        for (CautionSigmaBlDto cautionCaBlDto : cautionSigmaBl) {
            row = cautionBlSigmaSheet.createRow(rowId++);
            cell = row.createCell(colId++);
            cell.setCellValue(cautionCaBlDto.getCodeCd().trim());
            cell.setCellStyle(simpleStyle);
            cell = row.createCell(colId++);
            cell.setCellValue(cautionCaBlDto.getCircuit().trim());
            cell.setCellStyle(simpleStyle);
            cell = row.createCell(colId++);
            cell.setCellValue(cautionCaBlDto.getJour());
            cell.setCellStyle(simpleStyle);
            cell = row.createCell(colId++);
            cell.setCellValue(cautionCaBlDto.getMois());
            cell.setCellStyle(simpleStyle);
            cell = row.createCell(colId++);
            cell.setCellValue(cautionCaBlDto.getNtdTransaction().trim());
            cell.setCellStyle(simpleStyle);
            cell = row.createCell(colId++);
            cell.setCellValue(cautionCaBlDto.getCa());
            cell.setCellStyle(simpleStyle);
            cell = row.createCell(colId++);
            cell.setCellValue(cautionCaBlDto.getCation());
            cell.setCellStyle(simpleStyle);
            cell = row.createCell(colId++);
            cell.setCellValue((cautionCaBlDto.getCa() +  cautionCaBlDto.getCation()));
            cell.setCellStyle(simpleStyle);
            colId = 0;
        }
    }

   

}
