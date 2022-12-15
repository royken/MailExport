package com.bracongo.mailexport.service.impl;

import com.bracongo.mailexport.dao.IRepStockCdJourDao;
import com.bracongo.mailexport.data.RepStockCdJour;
import com.bracongo.mailexport.service.IRapportStockService;
import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Calendar;
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
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
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
public class RapportStockServiceImpl implements IRapportStockService {

    @Autowired
    private IRepStockCdJourDao stockCdJourDao;

    XSSFCellStyle header;

    XSSFCellStyle simpleStyle;

    XSSFCellStyle titleStyle;

    @Autowired
    private JavaMailSender sender;

    XSSFColor b = new XSSFColor(new java.awt.Color(255, 255, 255));
    XSSFColor blackBorderColor = new XSSFColor(Color.BLACK);

    @Override
    public void produceStockDataAndMail() {
        try {
            Calendar cal = Calendar.getInstance();
            List<RepStockCdJour> stocks = stockCdJourDao.getAllStockByAnneeByMois(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1);

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

            XSSFSheet rapportStockDataSheet = workbook.createSheet("STOCK DATA");
            produceDataSheet(rapportStockDataSheet, stocks);
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

            helper.setTo(new String[]{"bangoma.dakwa@castel-afrique.com"});
            helper.setCc("valmy.roikenfack@castel-afrique.com");
            helper.setSubject("Rapport Stock CD Pleins et Vides ");
            helper.setFrom("BRACONGO.Reportbusiness@castel-afrique.com");
            MimeBodyPart messageBodyPart1 = new MimeBodyPart();
            messageBodyPart1.setDataHandler(new DataHandler(fds));

//messageBodyPart1.setFileName(fds.getName());
            messageBodyPart1.setFileName("Rapport_Stock_CD_Pleins_et_Vides.xlsx");
            Multipart mpart = new MimeMultipart();
            mpart.addBodyPart(messageBodyPart1);
            message.setContent(mpart);
            sender.send(message);
        } catch (MessagingException ex) {
            Logger.getLogger(RapportStockServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RapportStockServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void produceDataSheet(XSSFSheet rapportStockDataSheet, List<RepStockCdJour> stocks) {
        int rowId = 0;
        int colId = 0;
        Row row = rapportStockDataSheet.createRow(rowId++);
        Cell cell;
        cell = row.createCell(colId++);
        cell.setCellValue("JOUR");
        cell.setCellStyle(header);
        cell = row.createCell(colId++);
        cell.setCellValue("MOIS");
        cell.setCellStyle(header);
        cell = row.createCell(colId++);
        cell.setCellValue("ANNEE");
        cell.setCellStyle(header);
        cell = row.createCell(colId++);
        cell.setCellValue("CODE_CD");
        cell.setCellStyle(header);
        cell = row.createCell(colId++);
        cell.setCellValue("NOM_CD");
        cell.setCellStyle(header);
        cell = row.createCell(colId++);
        cell.setCellValue("CODE_PRODUIT");
        cell.setCellStyle(header);
        cell = row.createCell(colId++);
        cell.setCellValue("NOM_PRODUIT");
        cell.setCellStyle(header);        
        cell = row.createCell(colId++);
        cell.setCellValue("QUANTITE_STOCK");
        cell.setCellStyle(header);      
        cell = row.createCell(colId++);
        cell.setCellValue("QUANTITE_CAMION");
        cell.setCellStyle(header);
        cell = row.createCell(colId++);
        cell.setCellValue("TYPE_STOCK");
        cell.setCellStyle(header);
        
        colId = 0;
        for (RepStockCdJour stock : stocks) {
            row = rapportStockDataSheet.createRow(rowId++);
            cell = row.createCell(colId++);
            cell.setCellValue(stock.getJour());
            cell.setCellStyle(simpleStyle);
            cell = row.createCell(colId++);
            cell.setCellValue(stock.getMois());
            cell.setCellStyle(simpleStyle);
            cell = row.createCell(colId++);
            cell.setCellValue(stock.getAnnee());
            cell.setCellStyle(simpleStyle);
            cell = row.createCell(colId++);
            cell.setCellValue(stock.getCodeCd());
            cell.setCellStyle(simpleStyle);
            cell = row.createCell(colId++);
            cell.setCellValue(stock.getNomCd().trim());
            cell.setCellStyle(simpleStyle);
            cell = row.createCell(colId++);
            cell.setCellValue(stock.getCodars().trim());
            cell.setCellStyle(simpleStyle);
            cell = row.createCell(colId++);
            cell.setCellValue(stock.getArticle().trim());
            cell.setCellStyle(simpleStyle);
            cell = row.createCell(colId++);
            cell.setCellValue(stock.getQuantiteMagasin());
            cell.setCellStyle(simpleStyle);
            cell = row.createCell(colId++);
            cell.setCellValue(stock.getQuantiteVehicule());
            cell.setCellStyle(simpleStyle);
            cell = row.createCell(colId++);
            cell.setCellValue(stock.getLibelle().contains("PLEIN") ? "PLEIN":"VIDE");
            cell.setCellStyle(simpleStyle);
            colId = 0;
        }
    }

}
