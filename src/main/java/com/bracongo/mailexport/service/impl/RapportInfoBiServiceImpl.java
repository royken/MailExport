package com.bracongo.mailexport.service.impl;

import com.bracongo.mailexport.dao.IRepArticleInfoBiDao;
import com.bracongo.mailexport.dao.IRepObjectifProduitInfoBiDao;
import com.bracongo.mailexport.dao.IRepVentesGratuitCDDao;
import com.bracongo.mailexport.dao.ITbCentreDistributionDao;
import com.bracongo.mailexport.data.RepArticleInfoBi;
import com.bracongo.mailexport.data.TbCentreDistribution;
import com.bracongo.mailexport.data.dto.BudgetGlobalProduitByCd;
import com.bracongo.mailexport.data.dto.BudgetInfoBiByMoisAnneeDto;
import com.bracongo.mailexport.data.dto.BudgetInfoBiGlobalMoisAnneeDto;
import com.bracongo.mailexport.data.dto.VenteGratuitGlobalByMoisAnnee;
import com.bracongo.mailexport.data.dto.VenteGratuitProduitAnneeDto;
import com.bracongo.mailexport.service.IRapportInfoBiService;
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
public class RapportInfoBiServiceImpl implements IRapportInfoBiService {

    @Autowired
    private IRepArticleInfoBiDao articleInfoBiDao;

    @Autowired
    private ITbCentreDistributionDao centreDistributionDao;

    @Autowired
    private IRepObjectifProduitInfoBiDao objectifProduitInfoBiDao;

    @Autowired
    private IRepVentesGratuitCDDao ventesGratuitCDDao;

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
    public void produceRapportDataAndMail() {

        try {
            Date debut;
            Date fin;
            Calendar cal = Calendar.getInstance();
            
            // ventes n-1
            List<VenteGratuitProduitAnneeDto> ventesProduitNMUn =  ventesGratuitCDDao.getVentesByAnneeByProduit(cal.get(Calendar.YEAR) - 1);
            List<VenteGratuitGlobalByMoisAnnee> ventesGlobalNMUn =  ventesGratuitCDDao.getVentesGlobalByAnneeMois(cal.get(Calendar.YEAR) - 1);
            
            //objectifs
            List<BudgetInfoBiByMoisAnneeDto> budgetMoisProduit = objectifProduitInfoBiDao.getBudgetByProduitMoisAnnee(cal.get(Calendar.YEAR));
            List<BudgetGlobalProduitByCd> budgetGlobalProduitsByCD =  objectifProduitInfoBiDao.getBudgetGlobalByProduitByCdAnnee(Calendar.YEAR);
            List<BudgetInfoBiGlobalMoisAnneeDto> budgetGlobalMois = objectifProduitInfoBiDao.getBudgetGlobalByCdAnnee(Calendar.YEAR);
            
            // articles
            List<RepArticleInfoBi> articles = articleInfoBiDao.findAll();
            // CDs
            List<TbCentreDistribution> cds = centreDistributionDao.getAllCd();
            
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
            XSSFSheet recapSheet = workbook.createSheet("RECAP");
            XSSFSheet budgetSheet = workbook.createSheet("BUDGET");
            XSSFSheet ventesSheet = workbook.createSheet("Ventes + Gratuits");
            produceRecapSheet(recapSheet);
            produceBudgetSheet(budgetSheet);
            produceVentesSheet(ventesSheet);
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

            helper.setTo(new String[]{"rdsid@bracongo.cd", "valmy.roikenfack@castel-afrique.com"});
            helper.setSubject("InfoBI");
            helper.setFrom("rdsid@bracongo.cd");
            MimeBodyPart messageBodyPart1 = new MimeBodyPart();
            messageBodyPart1.setDataHandler(new DataHandler(fds));

//messageBodyPart1.setFileName(fds.getName());
            messageBodyPart1.setFileName("InfoBI.xlsx");
            Multipart mpart = new MimeMultipart();
            mpart.addBodyPart(messageBodyPart1);
            message.setContent(mpart);
            sender.send(message);
        } catch (IOException ex) {
            Logger.getLogger(RapportInfoBiServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(RapportInfoBiServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void produceRecapSheet(XSSFSheet recapSheet) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void produceBudgetSheet(XSSFSheet budgetSheet) {
        int rowId = 0;
        int colId = 0;
        Row row = budgetSheet.createRow(rowId);
        Cell cell;
    }

    private void produceVentesSheet(XSSFSheet ventesSheet) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
