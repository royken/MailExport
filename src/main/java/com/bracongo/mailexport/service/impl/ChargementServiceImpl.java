package com.bracongo.mailexport.service.impl;

import com.bracongo.mailexport.dao.IChargementSRDDao;
import com.bracongo.mailexport.data.RepChargementSrd;
import com.bracongo.mailexport.data.dto.ChargementGlobalCDDto;
import com.bracongo.mailexport.data.dto.ChargementGlobalProduitDto;
import com.bracongo.mailexport.data.dto.ChargementProduitDto;
import com.bracongo.mailexport.service.IChargementService;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
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
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
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
public class ChargementServiceImpl implements IChargementService {

    @Autowired
    private IChargementSRDDao chargementSRDDao;

    @Autowired
    private JavaMailSender sender;

    float llx;
    float lly;
    float urx;
    float ury;

    XSSFColor b = new XSSFColor(new java.awt.Color(255, 255, 255));

    XSSFCellStyle grey;

    XSSFCellStyle black;

    XSSFCellStyle gold;

    XSSFCellStyle blue;

    XSSFCellStyle yellow;

    XSSFCellStyle myStyle;

    XSSFCellStyle myStyle2;

    XSSFCellStyle myStyle3;

    XSSFCellStyle cdHeader;

    XSSFCellStyle footer;

    @Override
    public List<ChargementGlobalCDDto> getAllChargementGlobalByCd() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        int annee = cal.get(Calendar.YEAR);
        int jour = cal.get(Calendar.DAY_OF_MONTH);
        // List<ChargementGlobalCDDto> result = chargementSRDDao.getAllChargementGlobalByCodeCd(annee, cal.get(Calendar.MONTH) + 1, jour);
        return null;
    }

    @Override
    public List<ChargementProduitDto> getAllChargementGlobalByProduit() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        Date debut = cal.getTime();
        cal.set(Calendar.HOUR_OF_DAY, 23);
        Date fin = cal.getTime();

        List<ChargementProduitDto> result = chargementSRDDao.getChargementResumeBetweenDates(debut, fin);
        return result;
    }

    @Override
    public List<ChargementProduitDto> getAllChargementGlobalByProduitByVoyageId(int voyageId) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        Date debut = cal.getTime();
        cal.set(Calendar.HOUR_OF_DAY, 23);
        Date fin = cal.getTime();

        List<ChargementProduitDto> result = chargementSRDDao.getChargementResumeBetweenDatesByVoyageId(debut, fin, voyageId);
        return result;
    }

    @Override
    public void produceChargementDataAndMail() {
        try {
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE, -1);
            int annee = cal.get(Calendar.YEAR);
            int mois = (cal.get(Calendar.MONTH) + 1);
            int jour = cal.get(Calendar.DAY_OF_MONTH);
            List<RepChargementSrd> chargement = chargementSRDDao.getAllChargementByDay(annee, mois, jour);
            XSSFWorkbook workbook = new XSSFWorkbook();

            XSSFFont boldFont = workbook.createFont();
            boldFont.setBold(true);

            myStyle = workbook.createCellStyle();
            myStyle.setRotation((short) 90);
            myStyle.setFillForegroundColor(HSSFColor.HSSFColorPredefined.GOLD.getIndex());
            myStyle.setBorderColor(XSSFCellBorder.BorderSide.LEFT, b);
            myStyle.setBorderColor(XSSFCellBorder.BorderSide.BOTTOM, b);
            myStyle.setBorderColor(XSSFCellBorder.BorderSide.RIGHT, b);
            myStyle.setBorderColor(XSSFCellBorder.BorderSide.TOP, b);
            myStyle3 = workbook.createCellStyle();
            myStyle3.setRotation((short) 90);
            myStyle3.setFillForegroundColor(HSSFColor.HSSFColorPredefined.LIGHT_YELLOW.getIndex());
            myStyle3.setBorderColor(XSSFCellBorder.BorderSide.LEFT, b);
            myStyle3.setBorderColor(XSSFCellBorder.BorderSide.BOTTOM, b);
            myStyle3.setBorderColor(XSSFCellBorder.BorderSide.RIGHT, b);
            myStyle3.setBorderColor(XSSFCellBorder.BorderSide.TOP, b);
            gold = workbook.createCellStyle();
            gold.setFillForegroundColor(HSSFColor.HSSFColorPredefined.GOLD.getIndex());
            gold.setBorderColor(XSSFCellBorder.BorderSide.LEFT, b);
            gold.setBorderColor(XSSFCellBorder.BorderSide.BOTTOM, b);
            gold.setBorderColor(XSSFCellBorder.BorderSide.RIGHT, b);
            gold.setBorderColor(XSSFCellBorder.BorderSide.TOP, b);
            gold.setAlignment(HorizontalAlignment.CENTER);
            gold.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            grey = workbook.createCellStyle();
            grey.setFillForegroundColor(HSSFColor.HSSFColorPredefined.GREY_50_PERCENT.getIndex());
            grey.setBorderColor(XSSFCellBorder.BorderSide.LEFT, b);
            grey.setBorderColor(XSSFCellBorder.BorderSide.BOTTOM, b);
            grey.setBorderColor(XSSFCellBorder.BorderSide.RIGHT, b);
            grey.setBorderColor(XSSFCellBorder.BorderSide.TOP, b);
            grey.setAlignment(HorizontalAlignment.CENTER);
            grey.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            cdHeader = workbook.createCellStyle();
            cdHeader.setFillForegroundColor(HSSFColor.HSSFColorPredefined.LIGHT_YELLOW.getIndex());
            cdHeader.setBorderColor(XSSFCellBorder.BorderSide.LEFT, b);
            cdHeader.setBorderColor(XSSFCellBorder.BorderSide.BOTTOM, b);
            cdHeader.setBorderColor(XSSFCellBorder.BorderSide.RIGHT, b);
            cdHeader.setBorderColor(XSSFCellBorder.BorderSide.TOP, b);
            cdHeader.setAlignment(HorizontalAlignment.CENTER);
            cdHeader.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            cdHeader.setFont(boldFont);

            footer = workbook.createCellStyle();
            footer.setFillForegroundColor(HSSFColor.HSSFColorPredefined.LIGHT_ORANGE.getIndex());
            footer.setBorderColor(XSSFCellBorder.BorderSide.LEFT, b);
            footer.setBorderColor(XSSFCellBorder.BorderSide.BOTTOM, b);
            footer.setBorderColor(XSSFCellBorder.BorderSide.RIGHT, b);
            footer.setBorderColor(XSSFCellBorder.BorderSide.TOP, b);
            footer.setAlignment(HorizontalAlignment.CENTER);
            footer.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            footer.setFont(boldFont);
            XSSFSheet raportChargementSheet = workbook.createSheet("Rapport Chargement vs Retour vs Ventes");
            produceRapportSheet(raportChargementSheet, chargement);
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

            helper.setTo(new String[]{"tabord@bracongo.cd", "g.nkulu@bracongo.cd", "h.onandjeka@bracongo.cd"});
            helper.setSubject("Rapport Chargement vs Retour vs Ventes");
            helper.setFrom("rdsid@bracongo.cd");
            MimeBodyPart messageBodyPart1 = new MimeBodyPart();
            messageBodyPart1.setDataHandler(new DataHandler(fds));

//messageBodyPart1.setFileName(fds.getName());
            messageBodyPart1.setFileName("Rapport_Chargement_Ventes_SRD.xlsx");
            Multipart mpart = new MimeMultipart();
            mpart.addBodyPart(messageBodyPart1);
            message.setContent(mpart);
            sender.send(message);
        } catch (IOException | MessagingException ex) {
            Logger.getLogger(ChargementServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void produceRapportSheet(XSSFSheet raportChargementSheet, List<RepChargementSrd> chargement) {
         int rowId = 0;
        int colId = 0;
        Cell cell;
        Row row = raportChargementSheet.createRow(rowId);
        cell = row.createCell(colId);
        cell.setCellValue("RAPPORT CHARGEMENT J-1");
        cell.setCellStyle(gold);
        raportChargementSheet.addMergedRegion(new CellRangeAddress(
                rowId, //first row (0-based)
                rowId, //last row  (0-based)
                colId, //first column (0-based)
                10 //last column  (0-based)
        ));

        rowId++;
        colId = 0;

        row = raportChargementSheet.createRow(rowId);
        cell = row.createCell(colId ++);
        cell.setCellValue("CD");
        cell = row.createCell(colId++);
        cell.setCellValue("CIRCUIT");
        cell = row.createCell(colId++);
        cell.setCellValue("CIRCUIT_CODE");
        cell = row.createCell(colId++);
        cell.setCellValue("NTD");
        cell = row.createCell(colId++);
        cell.setCellValue("VOYAGE");
        cell = row.createCell(colId++);
        cell.setCellValue("CODARS");
        cell = row.createCell(colId++);
        cell.setCellValue("ARTICLE");

        cell = row.createCell(colId++);
        cell.setCellValue("FAMILLLE");
        
        cell = row.createCell(colId++);
        cell.setCellValue("CHARGEE");
        cell = row.createCell(colId++);
        cell.setCellValue("VENDUE");
        cell = row.createCell(colId++);
        cell.setCellValue("RETOUR");

        rowId = 2;
        colId = 0;
        for (RepChargementSrd temp : chargement) {
        row = raportChargementSheet.createRow(rowId);
        cell = row.createCell(colId ++);
        cell.setCellValue(temp.getCodeCd());
        cell = row.createCell(colId++);
        cell.setCellValue(temp.getCircuit().trim());
        cell = row.createCell(colId++);
        cell.setCellValue(temp.getCodeCircuit().trim());
        cell = row.createCell(colId++);
        cell.setCellValue(temp.getNtd());
        cell = row.createCell(colId++);
        cell.setCellValue(temp.getVoyageId());
        cell = row.createCell(colId++);
        cell.setCellValue(temp.getCodars());
        cell = row.createCell(colId++);
        cell.setCellValue(temp.getNomProduit());

        cell = row.createCell(colId++);
        cell.setCellValue(temp.getFamille());
        
        cell = row.createCell(colId++);
        cell.setCellValue(temp.getQuantiteChargee());
        cell = row.createCell(colId++);
        cell.setCellValue(temp.getQuantiteFacturee());
        cell = row.createCell(colId++);
        cell.setCellValue(temp.getQuantiteRetournee());
        
        rowId++;
        colId = 0;
        }
    }

}
