package com.bracongo.mailexport.service.impl;

import com.bracongo.mailexport.dao.IRepArticleInfoBiDao;
import com.bracongo.mailexport.dao.IRepObjectifProduitInfoBiDao;
import com.bracongo.mailexport.dao.IRepVentesGratuitCDDao;
import com.bracongo.mailexport.dao.ITbCentreDistributionDao;
import com.bracongo.mailexport.data.RepArticleInfoBi;
import com.bracongo.mailexport.data.RepObjectifProduitInfoBi;
import com.bracongo.mailexport.data.TbCentreDistribution;
import com.bracongo.mailexport.data.dto.BudgetGlobalProduitByCd;
import com.bracongo.mailexport.data.dto.BudgetInfoBiByCDMoisFamilleDto;
import com.bracongo.mailexport.data.dto.BudgetInfoBiByMoisAnneeDto;
import com.bracongo.mailexport.data.dto.BudgetInfoBiGlobalMoisAnneeDto;
import com.bracongo.mailexport.data.dto.VenteEtGratuitGammeCdMoisAnneeDto;
import com.bracongo.mailexport.data.dto.VenteEtGratuitInfoBiFamilleCDMois;
import com.bracongo.mailexport.data.dto.VenteGratuitFamilleCDData;
import com.bracongo.mailexport.data.dto.VenteGratuitGlobalByMoisAnnee;
import com.bracongo.mailexport.data.dto.VenteGratuitProduitAnneeDto;
import com.bracongo.mailexport.service.IRapportInfoBiService;
import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
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
import org.springframework.data.repository.query.Param;
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
    XSSFColor blackBorderColor = new XSSFColor(Color.BLACK);

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

    XSSFCellStyle tableHeader;

    XSSFCellStyle cdName;

    XSSFCellStyle leftRightThickBorder;
    XSSFCellStyle leftRightTopThickBorder;
    XSSFCellStyle leftRightBottomThickBorder;
    XSSFCellStyle leftThickBorder;
    XSSFCellStyle rightThickBorder;
    XSSFCellStyle leftTopThickBorder;
    XSSFCellStyle leftBottomThickBorder;
    XSSFCellStyle rightTopThickBorder;
    XSSFCellStyle rightBottomThickBorder;

    XSSFCellStyle topThickBorder;
    XSSFCellStyle bottomThickBorder;

    XSSFCellStyle totalFamilleLabel;
    XSSFCellStyle totalVenteStyle;
    XSSFCellStyle variationStyle;

    XSSFCellStyle simpleStyle;

    XSSFFont greenFont;
    XSSFFont redFont;

    @Override
    public void produceRapportDataAndMail() {

        try {
            Date debut;
            Date fin;
            Calendar cal = Calendar.getInstance();

            // ventes n-1
            List<VenteGratuitProduitAnneeDto> ventesProduitNMUn = ventesGratuitCDDao.getVentesByAnneeByProduit(cal.get(Calendar.YEAR) - 1);
            List<VenteGratuitGlobalByMoisAnnee> ventesGlobalNMUn = ventesGratuitCDDao.getVentesGlobalByAnneeMois(cal.get(Calendar.YEAR) - 1);
            List<VenteEtGratuitGammeCdMoisAnneeDto> ventesGlobalByMoisCdGammeNMUn = ventesGratuitCDDao.getVentesByCDGammeAnneeMoisBy(cal.get(Calendar.YEAR) - 1);
            List<VenteGratuitFamilleCDData> ventesGlobalByMoisCdFamilleNMUn = getListVenteData(ventesGlobalByMoisCdGammeNMUn);

            // ventes n
            List<VenteGratuitProduitAnneeDto> ventesProduit = ventesGratuitCDDao.getVentesByAnneeByProduit(cal.get(Calendar.YEAR));
            List<VenteGratuitGlobalByMoisAnnee> ventesGlobal = ventesGratuitCDDao.getVentesGlobalByAnneeMois(cal.get(Calendar.YEAR));
            List<VenteEtGratuitGammeCdMoisAnneeDto> ventesGlobalByMoisCdGamme = ventesGratuitCDDao.getVentesByCDGammeAnneeMoisBy(cal.get(Calendar.YEAR));
            List<VenteGratuitFamilleCDData> ventesGlobalByMoisCdFamille = getListVenteData(ventesGlobalByMoisCdGamme);
            List<VenteEtGratuitInfoBiFamilleCDMois> ventesFamilleProduitMCDMois = ventesGratuitCDDao.getVenteGratuitFamilleMoisProduit(cal.get(Calendar.YEAR));

            //objectifs
            List<BudgetInfoBiByMoisAnneeDto> budgetsMoisProduit = objectifProduitInfoBiDao.getBudgetByProduitMoisAnnee(cal.get(Calendar.YEAR));
            List<BudgetGlobalProduitByCd> budgetGlobalProduitsByCD = objectifProduitInfoBiDao.getBudgetGlobalByProduitByCdAnnee(cal.get(Calendar.YEAR));
            List<BudgetInfoBiGlobalMoisAnneeDto> budgetGlobalMois = objectifProduitInfoBiDao.getBudgetGlobalByCdAnnee(cal.get(Calendar.YEAR));
            List<BudgetInfoBiByCDMoisFamilleDto> budgetFamilles = objectifProduitInfoBiDao.getBudgetByCdFamilleAnnee(cal.get(Calendar.YEAR));
            // articles
            List<RepArticleInfoBi> articles = articleInfoBiDao.findAll();
            // CDs
            List<TbCentreDistribution> cds = centreDistributionDao.getAllCd();

            XSSFWorkbook workbook = new XSSFWorkbook();

            XSSFFont boldFont = workbook.createFont();
            boldFont.setBold(true);

            XSSFFont font = workbook.createFont();
            font.setFontHeightInPoints((short) 10);
            font.setFontName("Arial");
            font.setColor(IndexedColors.BLACK.getIndex());
            font.setBold(true);
            font.setItalic(false);

            XSSFFont cdNameFont = workbook.createFont();
            cdNameFont.setFontHeightInPoints((short) 10);
            cdNameFont.setFontName("Arial");
            cdNameFont.setColor(IndexedColors.RED.getIndex());
            cdNameFont.setBold(true);
            cdNameFont.setItalic(false);

            XSSFFont totalLabelFont = workbook.createFont();
            totalLabelFont.setFontHeightInPoints((short) 10);
            totalLabelFont.setFontName("Arial");
            totalLabelFont.setColor(IndexedColors.DARK_RED.getIndex());
            totalLabelFont.setBold(true);

            XSSFFont lastYearFont = workbook.createFont();
            lastYearFont.setFontHeightInPoints((short) 10);
            lastYearFont.setFontName("Arial");
            lastYearFont.setColor(IndexedColors.BLACK.getIndex());
            lastYearFont.setBold(true);

            XSSFFont variationFont = workbook.createFont();
            variationFont.setFontHeightInPoints((short) 10);
            variationFont.setFontName("Arial");
            variationFont.setColor(IndexedColors.BLACK.getIndex());
            variationFont.setBold(true);

            XSSFFont greenFont = workbook.createFont();
            greenFont.setFontHeightInPoints((short) 10);
            greenFont.setFontName("Arial");
            greenFont.setColor(IndexedColors.GREEN.getIndex());
            
            XSSFFont redFont = workbook.createFont();
            greenFont.setFontHeightInPoints((short) 10);
            greenFont.setFontName("Arial");
            greenFont.setColor(IndexedColors.RED.getIndex());

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

            tableHeader = workbook.createCellStyle();
            // tableHeader.setFillForegroundColor(HSSFColor.HSSFColorPredefined.LIGHT_YELLOW.getIndex());
            //tableHeader.setBorderColor(XSSFCellBorder.BorderSide.LEFT, b);
            tableHeader.setBottomBorderColor(blackBorderColor);
            tableHeader.setLeftBorderColor(blackBorderColor);
            tableHeader.setTopBorderColor(blackBorderColor);
            tableHeader.setRightBorderColor(blackBorderColor);
            tableHeader.setBorderTop(BorderStyle.THICK);
            tableHeader.setBorderLeft(BorderStyle.THICK);
            tableHeader.setBorderRight(BorderStyle.THICK);
            tableHeader.setBorderBottom(BorderStyle.THICK);
            //tableHeader.setBorderColor(XSSFCellBorder.BorderSide.BOTTOM, b);
            //tableHeader.setBorderColor(XSSFCellBorder.BorderSide.RIGHT, b);
            // tableHeader.setBorderColor(XSSFCellBorder.BorderSide.TOP, b);
            tableHeader.setAlignment(HorizontalAlignment.CENTER);
            //tableHeader.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            tableHeader.setFont(font);

            simpleStyle = workbook.createCellStyle();
            simpleStyle.setBottomBorderColor(blackBorderColor);
            simpleStyle.setLeftBorderColor(blackBorderColor);
            simpleStyle.setTopBorderColor(blackBorderColor);
            simpleStyle.setRightBorderColor(blackBorderColor);
            simpleStyle.setBorderTop(BorderStyle.THIN);
            simpleStyle.setBorderLeft(BorderStyle.THIN);
            simpleStyle.setBorderRight(BorderStyle.THIN);
            simpleStyle.setBorderBottom(BorderStyle.THIN);

            topThickBorder = workbook.createCellStyle();
            topThickBorder.setTopBorderColor(blackBorderColor);
            topThickBorder.setBorderTop(BorderStyle.THICK);
            
            bottomThickBorder = workbook.createCellStyle();
            bottomThickBorder.setBottomBorderColor(blackBorderColor);
            bottomThickBorder.setBorderBottom(BorderStyle.THICK);

            cdName = workbook.createCellStyle();
            cdName.setAlignment(HorizontalAlignment.CENTER);
            //cdName.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            cdName.setFont(cdNameFont);

            leftRightThickBorder = workbook.createCellStyle();
            leftRightThickBorder.setAlignment(HorizontalAlignment.CENTER);
            leftRightThickBorder.setLeftBorderColor(blackBorderColor);
            leftRightThickBorder.setRightBorderColor(blackBorderColor);
            leftRightThickBorder.setBorderLeft(BorderStyle.THICK);
            leftRightThickBorder.setBorderRight(BorderStyle.THICK);

            leftRightTopThickBorder = workbook.createCellStyle();
            leftRightTopThickBorder.setAlignment(HorizontalAlignment.CENTER);
            leftRightTopThickBorder.setLeftBorderColor(blackBorderColor);
            leftRightTopThickBorder.setTopBorderColor(blackBorderColor);
            leftRightTopThickBorder.setRightBorderColor(blackBorderColor);
            leftRightTopThickBorder.setBorderLeft(BorderStyle.THICK);
            leftRightTopThickBorder.setBorderRight(BorderStyle.THICK);
            leftRightTopThickBorder.setBorderTop(BorderStyle.THICK);

            leftRightBottomThickBorder = workbook.createCellStyle();
            leftRightBottomThickBorder.setBottomBorderColor(blackBorderColor);
            leftRightBottomThickBorder.setAlignment(HorizontalAlignment.CENTER);
            leftRightBottomThickBorder.setLeftBorderColor(blackBorderColor);
            leftRightBottomThickBorder.setRightBorderColor(blackBorderColor);
            leftRightBottomThickBorder.setBorderLeft(BorderStyle.THICK);
            leftRightBottomThickBorder.setBorderRight(BorderStyle.THICK);
            leftRightBottomThickBorder.setBorderBottom(BorderStyle.THICK);

            leftThickBorder = workbook.createCellStyle();
            leftThickBorder.setAlignment(HorizontalAlignment.CENTER);
            leftThickBorder.setLeftBorderColor(blackBorderColor);
            leftThickBorder.setBorderLeft(BorderStyle.THICK);

            rightThickBorder = workbook.createCellStyle();
            rightThickBorder.setAlignment(HorizontalAlignment.CENTER);
            rightThickBorder.setRightBorderColor(blackBorderColor);
            rightThickBorder.setBorderRight(BorderStyle.THICK);

            leftTopThickBorder = workbook.createCellStyle();
            leftTopThickBorder.setAlignment(HorizontalAlignment.CENTER);
            leftTopThickBorder.setLeftBorderColor(blackBorderColor);
            leftTopThickBorder.setBorderLeft(BorderStyle.THICK);
            leftTopThickBorder.setTopBorderColor(blackBorderColor);
            leftTopThickBorder.setBorderTop(BorderStyle.THICK);

            leftBottomThickBorder = workbook.createCellStyle();
            leftBottomThickBorder.setAlignment(HorizontalAlignment.CENTER);
            leftBottomThickBorder.setLeftBorderColor(blackBorderColor);
            leftBottomThickBorder.setBorderLeft(BorderStyle.THICK);
            leftBottomThickBorder.setBottomBorderColor(blackBorderColor);
            leftBottomThickBorder.setBorderBottom(BorderStyle.THICK);

            rightTopThickBorder = workbook.createCellStyle();
            rightTopThickBorder.setAlignment(HorizontalAlignment.CENTER);
            rightTopThickBorder.setRightBorderColor(blackBorderColor);
            rightTopThickBorder.setBorderRight(BorderStyle.THICK);
            rightTopThickBorder.setTopBorderColor(blackBorderColor);
            rightTopThickBorder.setBorderTop(BorderStyle.THICK);

            rightBottomThickBorder = workbook.createCellStyle();
            rightBottomThickBorder.setAlignment(HorizontalAlignment.CENTER);
            rightBottomThickBorder.setRightBorderColor(blackBorderColor);
            rightBottomThickBorder.setBorderRight(BorderStyle.THICK);
            rightBottomThickBorder.setBottomBorderColor(blackBorderColor);
            rightBottomThickBorder.setBorderBottom(BorderStyle.THICK);

            totalFamilleLabel = workbook.createCellStyle();
            totalFamilleLabel.setFont(totalLabelFont);
            totalFamilleLabel.setAlignment(HorizontalAlignment.CENTER);
            totalFamilleLabel.setLeftBorderColor(blackBorderColor);
            totalFamilleLabel.setTopBorderColor(blackBorderColor);
            totalFamilleLabel.setRightBorderColor(blackBorderColor);
            totalFamilleLabel.setBottomBorderColor(blackBorderColor);
            totalFamilleLabel.setBorderLeft(BorderStyle.THICK);
            totalFamilleLabel.setBorderRight(BorderStyle.THICK);
            totalFamilleLabel.setBorderTop(BorderStyle.THICK);
            totalFamilleLabel.setBorderBottom(BorderStyle.THICK);

            totalVenteStyle = workbook.createCellStyle();
            totalVenteStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            totalVenteStyle.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex());
            totalVenteStyle.setFont(lastYearFont);

            variationStyle = workbook.createCellStyle();
            variationStyle.setFillForegroundColor(IndexedColors.GREEN.getIndex());
            variationStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            variationStyle.setFont(boldFont);

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
            produceRecapSheet(recapSheet, budgetFamilles, cds, ventesGlobalByMoisCdFamille);
            produceBudgetSheet(budgetSheet, articles, cds, ventesProduitNMUn, ventesGlobalNMUn, budgetsMoisProduit, budgetGlobalProduitsByCD, budgetGlobalMois, budgetFamilles, ventesGlobalByMoisCdFamilleNMUn);
            produceVentesSheet(ventesSheet, articles, cds, ventesProduit, ventesGlobal, budgetsMoisProduit, budgetGlobalProduitsByCD, budgetGlobalMois, budgetFamilles, ventesFamilleProduitMCDMois);
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
        } catch (IOException | MessagingException ex) {
            Logger.getLogger(RapportInfoBiServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void produceRecapSheet(XSSFSheet recapSheet, List<BudgetInfoBiByCDMoisFamilleDto> budgetFamilles, List<TbCentreDistribution> cds, List<VenteGratuitFamilleCDData> ventesGlobalByMoisCdFamille) {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int lastYear = year - 1;
        int mois = cal.get(cal.get(Calendar.MONTH)) + 1;
        int rowId = 3;
        int colId = 0;
        Row row = recapSheet.createRow(rowId);
        Row venteRow;
        Row budgetRow;
        Row variationRow;
        Cell cell;
        Cell venteCell;
        Cell budgetCell;
        Cell variationCell;
        cell = row.createCell(colId++);
        cell.setCellValue("MENSUEL");
        cell.setCellStyle(tableHeader);
        cell = row.createCell(colId++);
        cell.setCellValue("VOLUMES");
        cell.setCellStyle(tableHeader);
        for (TbCentreDistribution cd : cds) {
            cell = row.createCell(colId++);
            cell.setCellValue(cd.getCdiNomcdi().trim());
            cell.setCellStyle(tableHeader);
        }
        rowId++;
        colId = 0;
        // filling data

        //Mois Data
        String[] familles = new String[]{"BI", "BG", "XXL", "EAUX"};
        int counter = 1; // to check first and last element : cellStyle
        for (String famille : familles) {
            //Budget Par Famille
            List<BudgetInfoBiByCDMoisFamilleDto> budgetFamilles_ = getBudgetByFamille(budgetFamilles, famille);
            // VENTES PAR FAMILLE ventesGlobalByMoisCdFamilleNMUn
            List<VenteGratuitFamilleCDData> ventesGlobalByMoisCdFamilleNMUn_ = getListVenteByFamille(ventesGlobalByMoisCdFamille, famille);

            row = recapSheet.createRow(rowId);
           // venteRow = recapSheet.createRow(rowId);
            budgetRow = recapSheet.createRow(rowId + 1);
            variationRow = recapSheet.createRow(rowId + 2);
            cell = row.createCell(colId);

            cell.setCellValue(getFamilleNameFromFamilleCode(famille));
            recapSheet.addMergedRegion(new CellRangeAddress(
                    rowId, //first row (0-based)
                    rowId + 2, //last row  (0-based)
                    colId, //first column (0-based)
                    colId //last column  (0-based)
            ));
            cell.setCellStyle(tableHeader);
            colId++;
            
            venteCell = row.createCell(colId);
            budgetCell = budgetRow.createCell(colId);
            variationCell = variationRow.createCell(colId);
            venteCell.setCellValue(year + " RL");
            venteCell.setCellStyle(leftRightTopThickBorder);
            budgetCell.setCellValue(year + " BU");
            budgetCell.setCellStyle(leftRightThickBorder);
            variationCell.setCellValue("Variation");
            variationCell.setCellStyle(leftRightBottomThickBorder);
            colId++;
            double variation = 0;
            double budgetMoisCd = 0;
            double venteMoisCd = 0;

            for (TbCentreDistribution cd : cds) {
                venteMoisCd = getTotalVenteByMonthByCd(ventesGlobalByMoisCdFamilleNMUn_, mois, cd.getCdiCodecd());
                budgetMoisCd = getTotalBudgetByMonthByCd(budgetFamilles_, mois, cd.getCdiCodecd());
                variation = (budgetMoisCd == 0) ? 100 : Math.round(((venteMoisCd / budgetMoisCd) - 1) * 100);
                venteCell = row.createCell(colId);
                budgetCell = budgetRow.createCell(colId);
                variationCell = variationRow.createCell(colId);
                venteCell.setCellValue(venteMoisCd);
                if (counter == 1) {
                    venteCell.setCellStyle(leftTopThickBorder);
                }
                else if (counter == cds.size()) {
                    venteCell.setCellStyle(rightTopThickBorder);
                } else {
                    venteCell.setCellStyle(topThickBorder);
                }

                budgetCell.setCellValue(budgetMoisCd);
                if (counter == 1) {
                    budgetCell.setCellStyle(leftThickBorder);
                }
                
                else if (counter == cds.size()) {
                    budgetCell.setCellStyle(rightThickBorder);
                }

                variationCell.setCellValue(variation + "%");
                if (counter == 1) {
                    variationCell.setCellStyle(leftBottomThickBorder);
                }
                else if (counter == cds.size()) {
                    variationCell.setCellStyle(rightBottomThickBorder);
                } else {
                    variationCell.setCellStyle(bottomThickBorder);
                }
                colId++;
                counter++;
            }
            rowId += 3;
            colId = 0;
            counter = 1;
        }

        rowId += 2;
        colId = 0;

        // Annee Data
        row = recapSheet.createRow(rowId);
        cell = row.createCell(colId++);
        cell.setCellValue("ANNUEL");
        cell.setCellStyle(tableHeader);
        cell = row.createCell(colId++);
        cell.setCellValue("VOLUMES");
        cell.setCellStyle(tableHeader);
        for (TbCentreDistribution cd : cds) {
            cell = row.createCell(colId++);
            cell.setCellValue(cd.getCdiNomcdi().trim());
            cell.setCellStyle(tableHeader);
        }
        rowId++;
        counter = 1; // to check first and last element : cellStyle
        colId = 0;
        for (String famille : familles) {
            //Budget Par Famille
            List<BudgetInfoBiByCDMoisFamilleDto> budgetFamilles_ = getBudgetByFamille(budgetFamilles, famille);
            // VENTES PAR FAMILLE ventesGlobalByMoisCdFamilleNMUn
            List<VenteGratuitFamilleCDData> ventesGlobalByMoisCdFamilleNMUn_ = getListVenteByFamille(ventesGlobalByMoisCdFamille, famille);

            row = recapSheet.createRow(rowId);
            //venteRow = recapSheet.createRow(rowId);
            budgetRow = recapSheet.createRow(rowId + 1);
            variationRow = recapSheet.createRow(rowId + 2);
            cell = row.createCell(colId);

            cell.setCellValue(getFamilleNameFromFamilleCode(famille));
            recapSheet.addMergedRegion(new CellRangeAddress(
                    rowId, //first row (0-based)
                    rowId + 2, //last row  (0-based)
                    colId, //first column (0-based)
                    colId //last column  (0-based)
            ));
            cell.setCellStyle(tableHeader);
            colId ++;
            venteCell = row.createCell(colId);
            budgetCell = budgetRow.createCell(colId);
            variationCell = variationRow.createCell(colId);
            venteCell.setCellValue( year + " RL");
            venteCell.setCellStyle(leftRightTopThickBorder);
            budgetCell.setCellValue( year + " BU");
            budgetCell.setCellStyle(leftRightThickBorder);
            variationCell.setCellValue("Variation");
            variationCell.setCellStyle(leftRightBottomThickBorder);
            colId++;
            double variation = 0;
            double totalBudgetCd = 0;
            double totalVenteCd = 0;

            for (TbCentreDistribution cd : cds) {
                totalVenteCd = getTotalVenteFamilleCd(ventesGlobalByMoisCdFamilleNMUn_, cd.getCdiCodecd());
                totalBudgetCd = getTotalBudgetFamilleByCd(budgetFamilles_, cd.getCdiCodecd());
                variation = (totalBudgetCd == 0) ? 100 : Math.round(((totalVenteCd / totalBudgetCd) - 1) * 100);
                venteCell = row.createCell(colId);
                budgetCell = budgetRow.createCell(colId);
                variationCell = variationRow.createCell(colId);
                venteCell.setCellValue(totalVenteCd);
                if (counter == 1) {
                    venteCell.setCellStyle(leftTopThickBorder);
                }
                if (counter == cds.size()) {
                    venteCell.setCellStyle(rightTopThickBorder);
                } else {
                    venteCell.setCellStyle(topThickBorder);
                }

                budgetCell.setCellValue(totalBudgetCd);
                if (counter == 1) {
                    budgetCell.setCellStyle(leftThickBorder);
                }
                if (counter == cds.size()) {
                    budgetCell.setCellStyle(rightThickBorder);
                }

                variationCell.setCellValue(variation + "%");
                if (counter == 1) {
                    variationCell.setCellStyle(leftBottomThickBorder);
                }
                if (counter == cds.size()) {
                    variationCell.setCellStyle(rightBottomThickBorder);
                } else {
                    variationCell.setCellStyle(bottomThickBorder);
                }
                colId++;
                counter++;
            }
            rowId += 3;
            colId = 0;
            counter = 1;
        }

        // Global data
        rowId += 2;
        colId = 1;
        row = recapSheet.createRow(rowId++);
        cell = row.createCell(colId++);
        cell.setCellValue("% ACTUEL");
        cell.setCellStyle(rightBottomThickBorder);
        for (TbCentreDistribution cd : cds) {
            cell = row.createCell(colId++);
            cell.setCellValue(cd.getCdiNomcdi().trim());
            cell.setCellStyle(tableHeader);
        }
        colId = 1;
        counter = 1; // to check first and last element : cellStyle
        int counter2 = 1; // to check first and last familly element for cellStyle
        for (String famille : familles) {
            List<VenteGratuitFamilleCDData> ventesGlobalByMoisCdFamilleNMUn_ = getListVenteByFamille(ventesGlobalByMoisCdFamille, famille);
            counter = 1;
            row = recapSheet.createRow(rowId++);
            cell = row.createCell(colId++);
            cell.setCellValue(getFamilleNameFromFamilleCode(famille));
            if (counter2 == 1) {
                cell.setCellStyle(leftRightTopThickBorder);
            }
            if (counter2 == familles.length) {
                cell.setCellStyle(leftRightBottomThickBorder);
            } else {
                cell.setCellStyle(leftRightThickBorder);
            }
            double totalVenteFamille = getTotalVenteFamille(ventesGlobalByMoisCdFamilleNMUn_);
            double pourcentage = 0;
            for (TbCentreDistribution cd : cds) {
                cell = row.createCell(colId++);
                double totalVenteCd = getTotalVenteFamilleCd(ventesGlobalByMoisCdFamilleNMUn_, cd.getCdiCodecd());
                pourcentage = (totalVenteFamille == 0) ? 0 : Math.round((totalVenteCd / totalVenteFamille) * 100);
                cell.setCellValue(pourcentage + "%");
                //cell.setCellStyle(tableHeader);
                counter++;
            }
            colId = 1;
            counter2++;
        }
        
    }

    private void produceBudgetSheet(XSSFSheet budgetSheet, List<RepArticleInfoBi> articles, List<TbCentreDistribution> cds, List<VenteGratuitProduitAnneeDto> ventesProduitNMUn, List<VenteGratuitGlobalByMoisAnnee> ventesGlobalNMUn, List<BudgetInfoBiByMoisAnneeDto> budgetsMoisProduit, List<BudgetGlobalProduitByCd> budgetGlobalProduitsByCD, List<BudgetInfoBiGlobalMoisAnneeDto> budgetGlobalMois, List<BudgetInfoBiByCDMoisFamilleDto> budgetFamilles, List<VenteGratuitFamilleCDData> ventesGlobalByMoisCdFamilleNMUn) {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int lastYear = year - 1;
        int rowId = 11;
        int colId = 0;
        Row row = budgetSheet.createRow(rowId);
        Row totalRow;
        Row venteRow;
        Row varaiationRow;
        Cell cell;
        cell = row.createCell(colId++);
        cell.setCellValue("Code Produit");
        cell.setCellStyle(tableHeader);
        cell = row.createCell(colId++);
        cell.setCellValue("PRODUITS");
        cell.setCellStyle(tableHeader);
        cell = row.createCell(colId++);
        cell.setCellValue("Format cd");
        cell.setCellStyle(tableHeader);
        cell = row.createCell(colId++);
        cell.setCellValue("");
        cell = row.createCell(colId++);
        cell.setCellValue("Cond");
        cell.setCellStyle(tableHeader);
        colId++;
        cell = row.createCell(colId++);
        cell.setCellValue("JANVIER");
        cell.setCellStyle(tableHeader);
        cell = row.createCell(colId++);
        cell.setCellValue("FEVRIER");
        cell.setCellStyle(tableHeader);
        cell = row.createCell(colId++);
        cell.setCellValue("MARS");
        cell.setCellStyle(tableHeader);
        cell = row.createCell(colId++);
        cell.setCellValue("AVRIL");
        cell.setCellStyle(tableHeader);
        cell = row.createCell(colId++);
        cell.setCellValue("MAI");
        cell.setCellStyle(tableHeader);
        cell = row.createCell(colId++);
        cell.setCellValue("JUIN");
        cell.setCellStyle(tableHeader);
        cell = row.createCell(colId++);
        cell.setCellValue("Juillet");
        cell.setCellStyle(tableHeader);
        cell = row.createCell(colId++);
        cell.setCellValue("AOUT");
        cell.setCellStyle(tableHeader);
        cell = row.createCell(colId++);
        cell.setCellValue("SEPTEMBRE");
        cell.setCellStyle(tableHeader);
        cell = row.createCell(colId++);
        cell.setCellValue("OCTOBRE");
        cell.setCellStyle(tableHeader);
        cell = row.createCell(colId++);
        cell.setCellValue("NOVEMBRE");
        cell.setCellStyle(tableHeader);
        cell = row.createCell(colId++);
        cell.setCellValue("DECEMBRE");
        cell.setCellStyle(tableHeader);
        colId++;
        cell = row.createCell(colId++);
        cell.setCellValue("TOTAL");
        cell.setCellStyle(tableHeader);
        colId++;
        cell = row.createCell(colId++);
        cell.setCellValue("Ventes N-1");
        cell.setCellStyle(tableHeader);
        cell = row.createCell(colId++);
        cell.setCellValue("Diff");
        cell.setCellStyle(tableHeader);
        colId++;
        for (TbCentreDistribution cd : cds) {
            cell = row.createCell(colId++);
            cell.setCellValue(cd.getCdiNomcdi().trim());
            cell.setCellStyle(tableHeader);
        }
        colId += 2;
        Row rowCD = budgetSheet.createRow(rowId - 1);
        for (TbCentreDistribution cd : cds) {
            cell = rowCD.createCell(colId);
            cell.setCellValue(cd.getCdiNomcdi());
            cell.setCellStyle(cdName);
            cell = row.createCell(colId++);
            cell.setCellValue("PRODUITS");
            cell.setCellStyle(tableHeader);
            cell = row.createCell(colId++);
            cell.setCellValue("Format en cl");
            cell.setCellStyle(tableHeader);
            cell = row.createCell(colId++);
            cell.setCellValue("");
            cell = row.createCell(colId++);
            cell.setCellValue("Cond.");
            cell.setCellStyle(tableHeader);
            colId++;
            for (int i = 1; i < 13; i++) {
                cell = row.createCell(colId++);
                cell.setCellValue(getMoisByNumber(i));
                cell.setCellStyle(tableHeader);
            }

            colId++;
            cell = row.createCell(colId++);
            cell.setCellValue("TOTAL");
            cell.setCellStyle(tableHeader);

            colId++;
        }
        rowId = 13;

        // filling data
        String[] familles = new String[]{"BI", "BG", "XXL", "EAUX"};

        for (String famille : familles) {
            int counter = 1;
            List<RepArticleInfoBi> familleArticles = getArticlesByFamille(articles, famille);
            List<BudgetInfoBiByCDMoisFamilleDto> budgetFamilles_ = getBudgetByFamille(budgetFamilles, famille);
            // VENTES PAR FAMILLE ventesGlobalByMoisCdFamilleNMUn
            List<VenteGratuitFamilleCDData> ventesGlobalByMoisCdFamilleNMUn_ = getListVenteByFamille(ventesGlobalByMoisCdFamilleNMUn, famille);
            row = budgetSheet.createRow(rowId);
            cell = row.createCell(1);
            cell.setCellValue(getFamilleNameFromFamilleCode(famille));
            budgetSheet.addMergedRegion(new CellRangeAddress(
                    rowId, //first row (0-based)
                    rowId, //last row  (0-based)
                    1, //first column (0-based)
                    4 //last column  (0-based)
            ));
            cell.setCellStyle(tableHeader);
            rowId++;
            colId = 0;
            for (RepArticleInfoBi article : familleArticles) {
                row = budgetSheet.createRow(rowId);
                cell = row.createCell(colId++);
                cell.setCellValue(article.getCodeInfoBi());
                cell = row.createCell(colId++);
                cell.setCellValue(article.getDesignation());
                if (counter == 1) {
                    cell.setCellStyle(leftTopThickBorder);
                }
                if (counter == familleArticles.size()) {
                    cell.setCellStyle(leftBottomThickBorder);
                } else {
                    cell.setCellStyle(leftThickBorder);
                }
                cell = row.createCell(colId++);
                cell.setCellValue(article.getTaille());
                if (counter == 1) {
                    cell.setCellStyle(topThickBorder);
                }
                if (counter == familleArticles.size()) {
                    cell.setCellStyle(bottomThickBorder);
                }
                cell = row.createCell(colId++);
                cell.setCellValue("par");
                if (counter == 1) {
                    cell.setCellStyle(topThickBorder);
                }
                if (counter == familleArticles.size()) {
                    cell.setCellStyle(bottomThickBorder);
                }
                cell = row.createCell(colId++);
                cell.setCellValue(article.getCodeEmballage());
                if (counter == 1) {
                    cell.setCellStyle(rightTopThickBorder);
                }
                if (counter == familleArticles.size()) {
                    cell.setCellStyle(rightBottomThickBorder);
                } else {
                    cell.setCellStyle(rightThickBorder);
                }
                colId++;
                double budgetTotalProduit = 0;
                double budgetMoisProduit = 0;
                double ventesTotalProduitNMoinsUn = getVenteTotaleProduitNMoinsUn(ventesProduitNMUn, article.getCodar().trim());
                for (int i = 1; i < 13; i++) {
                    cell = row.createCell(colId++);
                    budgetMoisProduit = getBudgetMoisProduit(budgetsMoisProduit, article.getCodeInfoBi().trim(), i);
                    budgetTotalProduit += budgetMoisProduit;
                    cell.setCellValue(budgetMoisProduit);
                    if (counter == 1) {
                        cell.setCellStyle(leftRightTopThickBorder);
                    }
                    if (counter == familleArticles.size()) {
                        cell.setCellStyle(leftRightBottomThickBorder);
                    } else {
                        cell.setCellStyle(leftRightThickBorder);
                    }
                }
                colId++;
                cell = row.createCell(colId++);
                cell.setCellValue(budgetTotalProduit);
                if (counter == 1) {
                    cell.setCellStyle(leftRightTopThickBorder);
                }
                if (counter == familleArticles.size()) {
                    cell.setCellStyle(leftRightBottomThickBorder);
                } else {
                    cell.setCellStyle(leftRightThickBorder);
                }
                colId++;
                cell = row.createCell(colId++);
                cell.setCellValue(ventesTotalProduitNMoinsUn);
                if (counter == 1) {
                    cell.setCellStyle(leftTopThickBorder);
                }
                if (counter == familleArticles.size()) {
                    cell.setCellStyle(leftBottomThickBorder);
                } else {
                    cell.setCellStyle(leftThickBorder);
                }
                cell = row.createCell(colId++);
                double diff = (ventesTotalProduitNMoinsUn == 0) ? 0 : Math.round(((budgetTotalProduit - ventesTotalProduitNMoinsUn) / ventesTotalProduitNMoinsUn) * 100);
                cell.setCellValue(diff + "%");
                if (counter == 1) {
                    cell.setCellStyle(rightTopThickBorder);
                }
                if (counter == familleArticles.size()) {
                    cell.setCellStyle(rightBottomThickBorder);
                } else {
                    cell.setCellStyle(rightThickBorder);
                }
                colId++;
                double budgetTotalProduitCd = 0;
                double pourcentageBudgetCd = 0;

                for (TbCentreDistribution cd : cds) {
                    cell = row.createCell(colId++);
                    budgetTotalProduitCd = getBudgetTotalProduitByCd(budgetGlobalProduitsByCD, cd.getCdiCodecd().trim(), article.getCodeInfoBi());
                    pourcentageBudgetCd = (budgetTotalProduit == 0) ? 0 : Math.round((budgetTotalProduitCd / budgetTotalProduit) * 100);
                    cell.setCellValue(pourcentageBudgetCd + "%");
                    cell.setCellStyle(simpleStyle);
                }
                colId += 2;
                for (TbCentreDistribution cd : cds) {
                    cell = row.createCell(colId++);
                    cell.setCellValue(article.getDesignation());
                    if (counter == 1) {
                        cell.setCellStyle(leftTopThickBorder);
                    }
                    if (counter == familleArticles.size()) {
                        cell.setCellStyle(leftBottomThickBorder);
                    } else {
                        cell.setCellStyle(leftThickBorder);
                    }
                    cell = row.createCell(colId++);
                    cell.setCellValue(article.getTaille());
                    if (counter == 1) {
                        cell.setCellStyle(topThickBorder);
                    }
                    if (counter == familleArticles.size()) {
                        cell.setCellStyle(bottomThickBorder);
                    }
                    cell = row.createCell(colId++);
                    cell.setCellValue("par");
                    cell = row.createCell(colId++);
                    cell.setCellValue(article.getCodeEmballage());
                    if (counter == 1) {
                        cell.setCellStyle(topThickBorder);
                    }
                    if (counter == familleArticles.size()) {
                        cell.setCellStyle(bottomThickBorder);
                    }
                    colId++;
                    List<RepObjectifProduitInfoBi> budgetsProduitMoisCd = objectifProduitInfoBiDao.getAllBudgetByAnneeByCdByProduit(cal.get(Calendar.YEAR), cd.getCdiCodecd(), article.getCodeInfoBi());
                    double totalBudgetProduitCd = 0;
                    double budgetProduitCdMois = 0;
                    for (int i = 1; i < 13; i++) {
                        cell = row.createCell(colId++);
                        budgetProduitCdMois = getBudgetProduitMoisByCd(budgetsProduitMoisCd, i);
                        cell.setCellValue(budgetProduitCdMois);
                        if (counter == 1) {
                            cell.setCellStyle(leftRightTopThickBorder);
                        }
                        if (counter == familleArticles.size()) {
                            cell.setCellStyle(leftRightBottomThickBorder);
                        } else {
                            cell.setCellStyle(leftRightThickBorder);
                        }
                        totalBudgetProduitCd += budgetProduitCdMois;
                    }

                    colId++;
                    cell = row.createCell(colId++);
                    cell.setCellValue(totalBudgetProduitCd);
                    if (counter == 1) {
                        cell.setCellStyle(leftRightTopThickBorder);
                    }
                    if (counter == familleArticles.size()) {
                        cell.setCellStyle(leftRightBottomThickBorder);
                    } else {
                        cell.setCellStyle(leftRightThickBorder);
                    }

                    colId++;
                }
                rowId++;
                colId = 0;
                ++counter;
            }
            colId = 1;
            totalRow = budgetSheet.createRow(rowId);
            venteRow = budgetSheet.createRow(rowId + 1);
            varaiationRow = budgetSheet.createRow(rowId + 2);
            Cell totalBudgetCell;
            Cell totalVenteCell;
            Cell variationCell;

            totalBudgetCell = totalRow.createCell(colId);
            totalBudgetCell.setCellValue("TOTAL " + getFamilleNameFromFamilleCode(famille) + " GROUPE");

            budgetSheet.addMergedRegion(new CellRangeAddress(
                    rowId, //first row (0-based)
                    rowId, //last row  (0-based)
                    colId, //first column (0-based)
                    4 //last column  (0-based)
            ));
            totalBudgetCell.setCellStyle(totalFamilleLabel);

            totalVenteCell = venteRow.createCell(colId);
            totalVenteCell.setCellValue(lastYear);

            budgetSheet.addMergedRegion(new CellRangeAddress(
                    rowId + 1, //first row (0-based)
                    rowId + 1, //last row  (0-based)
                    colId, //first column (0-based)
                    4 //last column  (0-based)
            ));
            totalVenteCell.setCellStyle(totalVenteStyle);

            variationCell = varaiationRow.createCell(colId);
            variationCell.setCellValue("Variation");

            budgetSheet.addMergedRegion(new CellRangeAddress(
                    rowId + 2, //first row (0-based)
                    rowId + 2, //last row  (0-based)
                    colId, //first column (0-based)
                    4 //last column  (0-based)
            ));
            variationCell.setCellStyle(variationStyle);

            double totalBudgetMois = 0;
            double totalVenteMois = 0;
            colId += 5;
            for (int i = 1; i < 13; i++) {
                totalBudgetCell = totalRow.createCell(colId);
                totalVenteCell = venteRow.createCell(colId);
                variationCell = varaiationRow.createCell(colId);
                double budgetFamilleMois = getTotalBudgetByMonth(budgetFamilles_, i);
                double ventFamilleMois = getTotalVenteByMonth(ventesGlobalByMoisCdFamilleNMUn_, i);
                totalBudgetMois += budgetFamilleMois;
                totalVenteMois += ventFamilleMois;

                totalBudgetCell.setCellValue(budgetFamilleMois);
                totalBudgetCell.setCellStyle(totalFamilleLabel);
                totalVenteCell.setCellValue(ventFamilleMois);
                totalVenteCell.setCellStyle(totalVenteStyle);
                double variation = (ventFamilleMois == 0) ? 100 : Math.round(((budgetFamilleMois / ventFamilleMois) - 1) * 100);
                variationCell.setCellValue(variation + "%");
                variationCell.setCellStyle(variationStyle);
                colId++;

            }
            colId++;
            totalBudgetCell = totalRow.createCell(colId);
            totalVenteCell = venteRow.createCell(colId);
            variationCell = varaiationRow.createCell(colId);
            totalBudgetCell.setCellValue(totalBudgetMois);
            totalBudgetCell.setCellStyle(totalFamilleLabel);
            totalVenteCell.setCellValue(totalVenteMois);
            totalVenteCell.setCellStyle(totalVenteStyle);
            double variation = (totalVenteMois == 0) ? 100 : Math.round(((totalBudgetMois / totalVenteMois) - 1) * 100);
            variationCell.setCellValue(variation + "%");
            variationCell.setCellStyle(variationStyle);

            //  += rowId++;
            colId += 4 + cds.size();
            colId += 8;
            for (TbCentreDistribution cd : cds) {

                double totalBudgetFamilleCd = 0;
                double totalVenteFamilleCd = 0;
                for (int i = 1; i < 13; i++) {
                    totalBudgetCell = totalRow.createCell(colId);
                    totalVenteCell = venteRow.createCell(colId);
                    variationCell = varaiationRow.createCell(colId);
                    double budgetFamilleCdMois = getTotalBudgetByMonthByCd(budgetFamilles_, i, cd.getCdiCodecd());
                    double venteFamilleCdMois = getTotalVenteByMonthByCd(ventesGlobalByMoisCdFamilleNMUn_, i, cd.getCdiCodecd());
                    totalBudgetCell.setCellValue(budgetFamilleCdMois);
                    totalBudgetCell.setCellStyle(totalFamilleLabel);
                    totalVenteCell.setCellValue(venteFamilleCdMois);
                    totalVenteCell.setCellStyle(totalVenteStyle);
                    variation = (venteFamilleCdMois == 0) ? 100 : Math.round(((budgetFamilleCdMois / venteFamilleCdMois) - 1) * 100);
                    variationCell.setCellValue(variation + "%");
                    variationCell.setCellStyle(variationStyle);
                    totalBudgetFamilleCd += budgetFamilleCdMois;
                    totalVenteFamilleCd += venteFamilleCdMois;
                    colId++;
                }

                colId++;
                totalBudgetCell = totalRow.createCell(colId);
                totalVenteCell = venteRow.createCell(colId);
                variationCell = varaiationRow.createCell(colId);
                //cell.setCellValue(totalBudgetFamilleCd);
                totalBudgetCell.setCellValue(totalBudgetFamilleCd);
                totalBudgetCell.setCellStyle(totalFamilleLabel);
                totalVenteCell.setCellValue(totalVenteFamilleCd);
                totalVenteCell.setCellStyle(totalVenteStyle);
                variation = (totalVenteFamilleCd == 0) ? 100 : Math.round(((totalBudgetFamilleCd / totalVenteFamilleCd) - 1) * 100);
                variationCell.setCellValue(variation + "%");
                variationCell.setCellStyle(variationStyle);

                colId += 7;
            }
            colId = 0;
            rowId += 5;

            // filling Total Value
        }

    }

    private void produceVentesSheet(XSSFSheet ventesSheet, List<RepArticleInfoBi> articles, List<TbCentreDistribution> cds, List<VenteGratuitProduitAnneeDto> ventesProduit, List<VenteGratuitGlobalByMoisAnnee> ventesGlobal, List<BudgetInfoBiByMoisAnneeDto> budgetsMoisProduit, List<BudgetGlobalProduitByCd> budgetGlobalProduitsByCD, List<BudgetInfoBiGlobalMoisAnneeDto> budgetGlobalMois, List<BudgetInfoBiByCDMoisFamilleDto> budgetFamilles, List<VenteEtGratuitInfoBiFamilleCDMois> ventesGlobalByMoisCdFamille) {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int lastYear = year - 1;
        int rowId = 11;
        int colId = 0;
        Row row = ventesSheet.createRow(rowId);
        Row totalRow;
        Row budgetRow;
        Row varaiationRow;
        Cell cell;
        cell = row.createCell(colId++);
        cell.setCellValue("Code Produit");
        cell.setCellStyle(tableHeader);
        cell = row.createCell(colId++);
        cell.setCellValue("PRODUITS");
        cell.setCellStyle(tableHeader);
        cell = row.createCell(colId++);
        cell.setCellValue("Format cd");
        cell.setCellStyle(tableHeader);
        cell = row.createCell(colId++);
        cell.setCellValue("");
        cell = row.createCell(colId++);
        cell.setCellValue("Cond");
        cell.setCellStyle(tableHeader);
        colId++;
        cell = row.createCell(colId++);
        cell.setCellValue("JANVIER");
        cell.setCellStyle(tableHeader);
        cell = row.createCell(colId++);
        cell.setCellValue("FEVRIER");
        cell.setCellStyle(tableHeader);
        cell = row.createCell(colId++);
        cell.setCellValue("MARS");
        cell.setCellStyle(tableHeader);
        cell = row.createCell(colId++);
        cell.setCellValue("AVRIL");
        cell.setCellStyle(tableHeader);
        cell = row.createCell(colId++);
        cell.setCellValue("MAI");
        cell.setCellStyle(tableHeader);
        cell = row.createCell(colId++);
        cell.setCellValue("JUIN");
        cell.setCellStyle(tableHeader);
        cell = row.createCell(colId++);
        cell.setCellValue("Juillet");
        cell.setCellStyle(tableHeader);
        cell = row.createCell(colId++);
        cell.setCellValue("AOUT");
        cell.setCellStyle(tableHeader);
        cell = row.createCell(colId++);
        cell.setCellValue("SEPTEMBRE");
        cell.setCellStyle(tableHeader);
        cell = row.createCell(colId++);
        cell.setCellValue("OCTOBRE");
        cell.setCellStyle(tableHeader);
        cell = row.createCell(colId++);
        cell.setCellValue("NOVEMBRE");
        cell.setCellStyle(tableHeader);
        cell = row.createCell(colId++);
        cell.setCellValue("DECEMBRE");
        cell.setCellStyle(tableHeader);
        colId++;
        cell = row.createCell(colId++);
        cell.setCellValue("TOTAL");
        cell.setCellStyle(tableHeader);
        colId++;
        cell = row.createCell(colId++);
        cell.setCellValue("BUDGET");
        cell.setCellStyle(tableHeader);
        cell = row.createCell(colId++);
        cell.setCellValue("Diff");
        cell.setCellStyle(tableHeader);
        colId++;
        for (TbCentreDistribution cd : cds) {
            cell = row.createCell(colId++);
            cell.setCellValue(cd.getCdiNomcdi().trim());
            cell.setCellStyle(tableHeader);
        }
        colId += 2;
        Row rowCD = ventesSheet.createRow(rowId - 1);
        for (TbCentreDistribution cd : cds) {
            cell = rowCD.createCell(colId);
            cell.setCellValue(cd.getCdiNomcdi());
            cell.setCellStyle(cdName);
            cell = row.createCell(colId++);
            cell.setCellValue("PRODUITS");
            cell.setCellStyle(tableHeader);
            cell = row.createCell(colId++);
            cell.setCellValue("Format en cl");
            cell.setCellStyle(tableHeader);
            cell = row.createCell(colId++);
            cell.setCellValue("");
            cell = row.createCell(colId++);
            cell.setCellValue("Cond.");
            cell.setCellStyle(tableHeader);
            colId++;
            for (int i = 1; i < 13; i++) {
                cell = row.createCell(colId++);
                cell.setCellValue(getMoisByNumber(i));
                cell.setCellStyle(tableHeader);
            }

            colId++;
            cell = row.createCell(colId++);
            cell.setCellValue("TOTAL");
            cell.setCellStyle(tableHeader);

            colId++;
        }
        rowId = 13;

        // filling data
        String[] familles = new String[]{"BI", "BG", "XXL", "EAUX"};

        for (String famille : familles) {
            int counter = 1;
            List<RepArticleInfoBi> familleArticles = getArticlesByFamille(articles, famille);
            List<BudgetInfoBiByCDMoisFamilleDto> budgetFamilles_ = getBudgetByFamille(budgetFamilles, famille);
            // VENTES PAR FAMILLE ventesGlobalByMoisCdFamilleNMUn
            //  List<VenteGratuitFamilleCDData> ventesGlobalByMoisCdFamille_ = getListVenteByFamille(ventesGlobalByMoisCdFamille, famille);
            List<VenteEtGratuitInfoBiFamilleCDMois> ventesGlobalByMoisCdFamille_ = getventesFamilleProduitMCDMoisByFamille(ventesGlobalByMoisCdFamille, famille);
            row = ventesSheet.createRow(rowId);
            cell = row.createCell(1);
            cell.setCellValue(getFamilleNameFromFamilleCode(famille));
            ventesSheet.addMergedRegion(new CellRangeAddress(
                    rowId, //first row (0-based)
                    rowId, //last row  (0-based)
                    1, //first column (0-based)
                    4 //last column  (0-based)
            ));
            cell.setCellStyle(tableHeader);
            rowId++;
            colId = 0;
            for (RepArticleInfoBi article : familleArticles) {
                row = ventesSheet.createRow(rowId);
                cell = row.createCell(colId++);
                cell.setCellValue(article.getCodeInfoBi());
                cell = row.createCell(colId++);
                cell.setCellValue(article.getDesignation());
                if (counter == 1) {
                    cell.setCellStyle(leftTopThickBorder);
                }
                if (counter == familleArticles.size()) {
                    cell.setCellStyle(leftBottomThickBorder);
                } else {
                    cell.setCellStyle(leftThickBorder);
                }
                cell = row.createCell(colId++);
                cell.setCellValue(article.getTaille());
                if (counter == 1) {
                    cell.setCellStyle(topThickBorder);
                }
                if (counter == familleArticles.size()) {
                    cell.setCellStyle(bottomThickBorder);
                }
                cell = row.createCell(colId++);
                cell.setCellValue("par");
                if (counter == 1) {
                    cell.setCellStyle(topThickBorder);
                }
                if (counter == familleArticles.size()) {
                    cell.setCellStyle(bottomThickBorder);
                }
                cell = row.createCell(colId++);
                cell.setCellValue(article.getCodeEmballage());
                if (counter == 1) {
                    cell.setCellStyle(rightTopThickBorder);
                }
                if (counter == familleArticles.size()) {
                    cell.setCellStyle(rightBottomThickBorder);
                } else {
                    cell.setCellStyle(rightThickBorder);
                }
                colId++;
                double venteTotaleProduit = 0;
                double venteMoisProduit = 0;

                double budgetTotalProduit = getBudgetTotalProduit(budgetsMoisProduit, article.getCodeInfoBi().trim());
                for (int i = 1; i < 13; i++) {
                    cell = row.createCell(colId++);
                    venteMoisProduit = getVenteMoisProduit(ventesGlobalByMoisCdFamille_, article.getCodeInfoBi().trim(), i);
                    venteTotaleProduit += venteMoisProduit;
                    cell.setCellValue(venteMoisProduit);
                    if (counter == 1) {
                        cell.setCellStyle(leftRightTopThickBorder);
                    }
                    if (counter == familleArticles.size()) {
                        cell.setCellStyle(leftRightBottomThickBorder);
                    } else {
                        cell.setCellStyle(leftRightThickBorder);
                    }
                }
                colId++;
                cell = row.createCell(colId++);
                cell.setCellValue(venteTotaleProduit);
                if (counter == 1) {
                    cell.setCellStyle(leftRightTopThickBorder);
                }
                if (counter == familleArticles.size()) {
                    cell.setCellStyle(leftRightBottomThickBorder);
                } else {
                    cell.setCellStyle(leftRightThickBorder);
                }
                colId++;
                cell = row.createCell(colId++);
                cell.setCellValue(budgetTotalProduit);
                if (counter == 1) {
                    cell.setCellStyle(leftTopThickBorder);
                }
                if (counter == familleArticles.size()) {
                    cell.setCellStyle(leftBottomThickBorder);
                } else {
                    cell.setCellStyle(leftThickBorder);
                }
                cell = row.createCell(colId++);
                double diff = (budgetTotalProduit == 0) ? 0 : Math.round(((venteTotaleProduit - budgetTotalProduit) / budgetTotalProduit) * 100);
                cell.setCellValue(diff + "%");
                if (counter == 1) {
                    cell.setCellStyle(rightTopThickBorder);
                }
                if (counter == familleArticles.size()) {
                    cell.setCellStyle(rightBottomThickBorder);
                } else {
                    cell.setCellStyle(rightThickBorder);
                }
                colId++;

                double venteTotalProduitCd = 0;
                double pourcentageVenteCd = 0;

                for (TbCentreDistribution cd : cds) {
                    cell = row.createCell(colId++);
                    venteTotalProduitCd = getTotalVenteProduitCd(ventesGlobalByMoisCdFamille_, article.getCodeInfoBi());
                    pourcentageVenteCd = (venteTotaleProduit == 0) ? 0 : Math.round((venteTotalProduitCd / venteTotaleProduit) * 100);
                    cell.setCellValue(pourcentageVenteCd + "%");
                    cell.setCellStyle(simpleStyle);
                }
                colId += 2;
                for (TbCentreDistribution cd : cds) {
                    cell = row.createCell(colId++);
                    cell.setCellValue(article.getDesignation());
                    if (counter == 1) {
                        cell.setCellStyle(leftTopThickBorder);
                    }
                    if (counter == familleArticles.size()) {
                        cell.setCellStyle(leftBottomThickBorder);
                    } else {
                        cell.setCellStyle(leftThickBorder);
                    }
                    cell = row.createCell(colId++);
                    cell.setCellValue(article.getTaille());
                    if (counter == 1) {
                        cell.setCellStyle(topThickBorder);
                    }
                    if (counter == familleArticles.size()) {
                        cell.setCellStyle(bottomThickBorder);
                    }
                    cell = row.createCell(colId++);
                    cell.setCellValue("par");
                    cell = row.createCell(colId++);
                    cell.setCellValue(article.getCodeEmballage());
                    if (counter == 1) {
                        cell.setCellStyle(topThickBorder);
                    }
                    if (counter == familleArticles.size()) {
                        cell.setCellStyle(bottomThickBorder);
                    }
                    colId++;
                    double totalVenteProduitCd = 0;
                    double venteProduitCdMois = 0;
                    for (int i = 1; i < 13; i++) {
                        cell = row.createCell(colId++);
                        venteProduitCdMois = getTotalVenteProduitCdMois(ventesGlobalByMoisCdFamille_, cd.getCdiCodecd(), i, article.getCodeInfoBi().trim());
                        cell.setCellValue(venteProduitCdMois);
                        if (counter == 1) {
                            cell.setCellStyle(leftRightTopThickBorder);
                        }
                        if (counter == familleArticles.size()) {
                            cell.setCellStyle(leftRightBottomThickBorder);
                        } else {
                            cell.setCellStyle(leftRightThickBorder);
                        }
                        totalVenteProduitCd += venteProduitCdMois;
                    }

                    colId++;
                    cell = row.createCell(colId++);
                    cell.setCellValue(totalVenteProduitCd);
                    if (counter == 1) {
                        cell.setCellStyle(leftRightTopThickBorder);
                    }
                    if (counter == familleArticles.size()) {
                        cell.setCellStyle(leftRightBottomThickBorder);
                    } else {
                        cell.setCellStyle(leftRightThickBorder);
                    }

                    colId++;
                }
                rowId++;
                colId = 0;
                ++counter;
            }
            colId = 1;
            totalRow = ventesSheet.createRow(rowId);
            budgetRow = ventesSheet.createRow(rowId + 1);
            varaiationRow = ventesSheet.createRow(rowId + 2);
            Cell totalBudgetCell;
            Cell totalVenteCell;
            Cell variationCell;

            totalVenteCell = totalRow.createCell(colId);
            totalVenteCell.setCellValue("TOTAL " + getFamilleNameFromFamilleCode(famille) + " GROUPE");

            ventesSheet.addMergedRegion(new CellRangeAddress(
                    rowId, //first row (0-based)
                    rowId, //last row  (0-based)
                    colId, //first column (0-based)
                    4 //last column  (0-based)
            ));
            totalVenteCell.setCellStyle(totalFamilleLabel);

            totalBudgetCell = budgetRow.createCell(colId);
            totalBudgetCell.setCellValue("BUDGET " + year);

            ventesSheet.addMergedRegion(new CellRangeAddress(
                    rowId + 1, //first row (0-based)
                    rowId + 1, //last row  (0-based)
                    colId, //first column (0-based)
                    4 //last column  (0-based)
            ));
            totalBudgetCell.setCellStyle(totalVenteStyle);

            variationCell = varaiationRow.createCell(colId);
            variationCell.setCellValue("Variation");

            ventesSheet.addMergedRegion(new CellRangeAddress(
                    rowId + 2, //first row (0-based)
                    rowId + 2, //last row  (0-based)
                    colId, //first column (0-based)
                    4 //last column  (0-based)
            ));
            variationCell.setCellStyle(variationStyle);

            double totalBudgetMois = 0;
            double totalVenteMois = 0;
            colId += 5;
            for (int i = 1; i < 13; i++) {
                totalVenteCell = totalRow.createCell(colId);
                totalBudgetCell = budgetRow.createCell(colId);
                variationCell = varaiationRow.createCell(colId);
                double budgetFamilleMois = getTotalBudgetByMonth(budgetFamilles_, i);
                double ventFamilleMois = getTotalVenteByProduitByMonth(ventesGlobalByMoisCdFamille_, i);
                totalBudgetMois += budgetFamilleMois;
                totalVenteMois += ventFamilleMois;

                totalBudgetCell.setCellValue(budgetFamilleMois);
                totalBudgetCell.setCellStyle(totalVenteStyle);
                totalVenteCell.setCellValue(ventFamilleMois);
                totalVenteCell.setCellStyle(totalFamilleLabel);
                double variation = (budgetFamilleMois == 0) ? 100 : Math.round(((ventFamilleMois / budgetFamilleMois) - 1) * 100);
                variationCell.setCellValue(variation + "%");
                variationCell.setCellStyle(variationStyle);
                colId++;

            }
            colId++;
            totalVenteCell = totalRow.createCell(colId);
            totalBudgetCell = budgetRow.createCell(colId);
            variationCell = varaiationRow.createCell(colId);
            totalBudgetCell.setCellValue(totalBudgetMois);
            totalBudgetCell.setCellStyle(totalVenteStyle);
            totalVenteCell.setCellValue(totalVenteMois);
            totalVenteCell.setCellStyle(totalFamilleLabel);
            double variation = (totalBudgetMois == 0) ? 100 : Math.round(((totalVenteMois / totalBudgetMois) - 1) * 100);
            variationCell.setCellValue(variation + "%");
            variationCell.setCellStyle(variationStyle);

            //  += rowId++;
            colId += 4 + cds.size();
            colId += 8;
            for (TbCentreDistribution cd : cds) {

                double totalBudgetFamilleCd = 0;
                double totalVenteFamilleCd = 0;
                for (int i = 1; i < 13; i++) {
                    totalVenteCell = totalRow.createCell(colId);
                    totalBudgetCell = budgetRow.createCell(colId);
                    variationCell = varaiationRow.createCell(colId);
                    double budgetFamilleCdMois = getTotalBudgetByMonthByCd(budgetFamilles_, i, cd.getCdiCodecd());
                    double venteFamilleCdMois = getTotalVenteByProduitByMonthByCd(ventesGlobalByMoisCdFamille_, i, cd.getCdiCodecd());
                    totalBudgetCell.setCellValue(budgetFamilleCdMois);
                    totalBudgetCell.setCellStyle(totalVenteStyle);
                    totalVenteCell.setCellValue(venteFamilleCdMois);
                    totalVenteCell.setCellStyle(totalFamilleLabel);
                    variation = (budgetFamilleCdMois == 0) ? 100 : Math.round(((venteFamilleCdMois / budgetFamilleCdMois) - 1) * 100);
                    variationCell.setCellValue(variation + "%");
                    variationCell.setCellStyle(variationStyle);
                    totalBudgetFamilleCd += budgetFamilleCdMois;
                    totalVenteFamilleCd += venteFamilleCdMois;
                    colId++;
                }

                colId++;
                totalVenteCell = totalRow.createCell(colId);
                totalBudgetCell = budgetRow.createCell(colId);
                variationCell = varaiationRow.createCell(colId);
                //cell.setCellValue(totalBudgetFamilleCd);
                totalBudgetCell.setCellValue(totalBudgetFamilleCd);
                totalBudgetCell.setCellStyle(totalVenteStyle);
                totalVenteCell.setCellValue(totalVenteFamilleCd);
                totalVenteCell.setCellStyle(totalFamilleLabel);
                variation = (totalBudgetFamilleCd == 0) ? 100 : Math.round(((totalVenteFamilleCd / totalBudgetFamilleCd) - 1) * 100);
                variationCell.setCellValue(variation + "%");
                variationCell.setCellStyle(variationStyle);

                colId += 7;
            }
            colId = 0;
            rowId += 5;

            // filling Total Value
        }

    }

    private String getMoisByNumber(int mois) {
        String[] monthNames = {"Janvier", "Février", "Mars", "Avril", "Mai", "Juin", "Juillet", "Aout", "Septembre", "Octobre", "Novembre", "Décembre"};
        return monthNames[mois - 1];

    }

    private double getBudgetMoisProduit(List<BudgetInfoBiByMoisAnneeDto> budgetsMoisProduit, String codeInfoBi, int mois) {
        for (BudgetInfoBiByMoisAnneeDto budgetInfoBiByMoisAnneeDto : budgetsMoisProduit) {
            if (budgetInfoBiByMoisAnneeDto.getCodeInfoBi().equalsIgnoreCase(codeInfoBi) && budgetInfoBiByMoisAnneeDto.getMois() == mois) {
                return Math.round(budgetInfoBiByMoisAnneeDto.getBudget());
            }
        }
        return 0;
    }

    private double getVenteMoisProduit(List<VenteEtGratuitInfoBiFamilleCDMois> ventesFamilleProduitMCDMois, String codeInfoBi, int mois) {
        double result = 0;
        for (VenteEtGratuitInfoBiFamilleCDMois vente : ventesFamilleProduitMCDMois) {
            if (vente.getCodeInfoBi().trim().equalsIgnoreCase(codeInfoBi) && vente.getMois() == mois) {
                result += vente.getHecto();
            }
        }
        return result;
    }

    private double getBudgetTotalProduit(List<BudgetInfoBiByMoisAnneeDto> budgetsMoisProduit, String codeInfoBi) {
        for (BudgetInfoBiByMoisAnneeDto budgetInfoBiByMoisAnneeDto : budgetsMoisProduit) {
            if (budgetInfoBiByMoisAnneeDto.getCodeInfoBi().equalsIgnoreCase(codeInfoBi)) {
                return Math.round(budgetInfoBiByMoisAnneeDto.getBudget());
            }
        }
        return 0;
    }

    private double getVenteTotaleProduitNMoinsUn(List<VenteGratuitProduitAnneeDto> ventesProduitNMUn, String codars) {
        for (VenteGratuitProduitAnneeDto venteGratuitProduitAnneeDto : ventesProduitNMUn) {
            if (venteGratuitProduitAnneeDto.getCodars().equalsIgnoreCase(codars)) {
                return Math.round(venteGratuitProduitAnneeDto.getHecto());
            }
        }
        return 0;
    }

    private double getBudgetTotalProduitByCd(List<BudgetGlobalProduitByCd> budgetGlobalProduitsByCD, String codeCd, String codeInfoBiProduit) {
        for (BudgetGlobalProduitByCd budgetGlobalProduitByCd : budgetGlobalProduitsByCD) {
            if ((budgetGlobalProduitByCd.getCodeCd().equalsIgnoreCase(codeCd)) && (budgetGlobalProduitByCd.getCodeInfoBi().equalsIgnoreCase(codeInfoBiProduit))) {
                return budgetGlobalProduitByCd.getBudget();
            }
        }
        return 0;
    }

    private double getBudgetProduitMoisByCd(List<RepObjectifProduitInfoBi> budgetsProduitMoisCd, int mois) {
        if (budgetsProduitMoisCd.isEmpty() || budgetsProduitMoisCd == null) {
            return 0;
        }
        for (RepObjectifProduitInfoBi repObjectifProduitInfoBi : budgetsProduitMoisCd) {
            if (repObjectifProduitInfoBi.getMois() == mois) {
                return repObjectifProduitInfoBi.getBudget();
            }
        }
        return 0;
    }

    private List<RepArticleInfoBi> getArticlesByFamille(List<RepArticleInfoBi> articles, String codeFamille) {
        List<RepArticleInfoBi> result = new ArrayList<>();
        for (RepArticleInfoBi article : articles) {
            if (article.getFamille().equalsIgnoreCase(codeFamille)) {
                result.add(article);
            }
        }
        return result;
    }

    private List<VenteGratuitFamilleCDData> getListVenteData(List<VenteEtGratuitGammeCdMoisAnneeDto> data) {
        List<VenteGratuitFamilleCDData> result = new ArrayList<>();
        data.forEach((venteEtGratuitGammeCdMoisAnneeDto) -> {
            result.add(new VenteGratuitFamilleCDData(venteEtGratuitGammeCdMoisAnneeDto));
        });
        return result;
    }

    private List<VenteGratuitFamilleCDData> getListVenteByFamille(List<VenteGratuitFamilleCDData> ventes, String famille) {
        List<VenteGratuitFamilleCDData> result = new ArrayList<>();
        for (VenteGratuitFamilleCDData vente : ventes) {
            if (vente.getFamille().equalsIgnoreCase(famille)) {
                result.add(vente);
            }
        }
        return result;
    }

    private double getTotalVenteByMonth(List<VenteGratuitFamilleCDData> ventes, int month) {
        double result = 0;
        for (VenteGratuitFamilleCDData vente : ventes) {
            if (vente.getMois() == month) {
                result += vente.getHecto();
            }
        }
        return result;
    }

    private double getTotalVenteByMonthByCd(List<VenteGratuitFamilleCDData> ventes, int month, String codeCd) {
        double result = 0;
        for (VenteGratuitFamilleCDData vente : ventes) {
            if (vente.getMois() == month && vente.getCd().equalsIgnoreCase(codeCd)) {
                result += vente.getHecto();
            }
        }
        return result;
    }

    private List<BudgetInfoBiByCDMoisFamilleDto> getBudgetByFamille(List<BudgetInfoBiByCDMoisFamilleDto> budgetFamilles, String famille) {
        List<BudgetInfoBiByCDMoisFamilleDto> result = new ArrayList<>();
        for (BudgetInfoBiByCDMoisFamilleDto budgetFamille : budgetFamilles) {
            if (budgetFamille.getFamille().equalsIgnoreCase(famille)) {
                result.add(budgetFamille);
            }
        }
        return result;
    }

    private double getTotalBudgetByMonth(List<BudgetInfoBiByCDMoisFamilleDto> budgetFamilles, int month) {
        double result = 0;
        for (BudgetInfoBiByCDMoisFamilleDto budgetFamille : budgetFamilles) {
            if (budgetFamille.getMois() == month) {
                result += budgetFamille.getBudget();
            }
        }
        return result;
    }

    private double getTotalBudgetByMonthByCd(List<BudgetInfoBiByCDMoisFamilleDto> budgetFamilles, int month, String codeCd) {
        double result = 0;
        for (BudgetInfoBiByCDMoisFamilleDto budgetFamille : budgetFamilles) {
            if (budgetFamille.getMois() == month && budgetFamille.getCodeCd().equalsIgnoreCase(codeCd)) {
                result += budgetFamille.getBudget();
            }
        }
        return result;
    }

    private List<VenteEtGratuitInfoBiFamilleCDMois> getventesFamilleProduitMCDMoisByFamille(List<VenteEtGratuitInfoBiFamilleCDMois> ventesFamilleProduitMCDMois, String famille) {
        List<VenteEtGratuitInfoBiFamilleCDMois> result = new ArrayList<>();
        for (VenteEtGratuitInfoBiFamilleCDMois vente : ventesFamilleProduitMCDMois) {
            if (vente.getFamilleBi().trim().equalsIgnoreCase(famille)) {
                result.add(vente);
            }
        }
        return result;
    }

    private double getTotalVenteByProduitByMonthByCd(List<VenteEtGratuitInfoBiFamilleCDMois> ventesFamilleProduitMCDMois, int month, String codeCd) {
        double result = 0;
        for (VenteEtGratuitInfoBiFamilleCDMois venteFamille : ventesFamilleProduitMCDMois) {
            if (venteFamille.getMois() == month && String.valueOf(venteFamille.getCodeCd()).equalsIgnoreCase(codeCd)) {
                result += venteFamille.getHecto();
            }
        }
        return result;
    }

    private double getTotalVenteByProduitByMonth(List<VenteEtGratuitInfoBiFamilleCDMois> ventesFamilleProduitMCDMois, int month) {
        double result = 0;
        for (VenteEtGratuitInfoBiFamilleCDMois venteFamille : ventesFamilleProduitMCDMois) {
            if (venteFamille.getMois() == month) {
                result += venteFamille.getHecto();
            }
        }
        return result;
    }

    private double getTotalVenteProduitCd(List<VenteEtGratuitInfoBiFamilleCDMois> ventesFamilleProduitMCDMois, String codeCd) {
        double result = 0;
        for (VenteEtGratuitInfoBiFamilleCDMois venteFamille : ventesFamilleProduitMCDMois) {
            if (String.valueOf(venteFamille.getCodeCd()).equalsIgnoreCase(codeCd)) {
                result += venteFamille.getHecto();
            }
        }
        return result;
    }

    private double getTotalVenteProduitCdMois(List<VenteEtGratuitInfoBiFamilleCDMois> ventesFamilleProduitMCDMois, String codeCd, int mois, String codeInfoBi) {

        for (VenteEtGratuitInfoBiFamilleCDMois venteFamille : ventesFamilleProduitMCDMois) {
            if (venteFamille.getMois() == mois && String.valueOf(venteFamille.getCodeCd()).trim().equalsIgnoreCase(codeCd) && venteFamille.getCodeInfoBi().trim().equalsIgnoreCase(codeInfoBi)) {
                return venteFamille.getHecto();
            }
        }
        return 0;
    }

    private String getFamilleNameFromFamilleCode(String familleCode) {
        switch (familleCode) {
            case "BI":
                return "BIERE";
            case "BG":
                return "BOISSONS GAZEUSES";
            case "EAUX":
                return "EAUX";
            case "XXL":
                return "XXL";
            default:
                return "BIERE";
        }
    }

    private double getTotalVenteFamille(List<VenteGratuitFamilleCDData> ventesGlobalByMoisCdFamilleNMUn_) {
        double result = 0;
        for (VenteGratuitFamilleCDData venteGratuitFamilleCDData : ventesGlobalByMoisCdFamilleNMUn_) {
            result += venteGratuitFamilleCDData.getHecto();
        }
        return result;
    }

    private double getTotalVenteFamilleCd(List<VenteGratuitFamilleCDData> ventesGlobalByMoisCdFamilleNMUn_, String cdiCodecd) {
        double result = 0;
        for (VenteGratuitFamilleCDData venteGratuitFamilleCDData : ventesGlobalByMoisCdFamilleNMUn_) {
            if (String.valueOf(venteGratuitFamilleCDData.getCd()).equalsIgnoreCase(cdiCodecd)) {
                result += venteGratuitFamilleCDData.getHecto();
            }
        }
        return result;
    }

    private double getTotalBudgetFamilleByCd(List<BudgetInfoBiByCDMoisFamilleDto> budgetFamilles_, String cdiCodecd) {
        double result = 0;
        for (BudgetInfoBiByCDMoisFamilleDto budgetInfoBiByCDMoisFamilleDto : budgetFamilles_) {
            if (budgetInfoBiByCDMoisFamilleDto.getCodeCd().equalsIgnoreCase(cdiCodecd)) {
                result += budgetInfoBiByCDMoisFamilleDto.getBudget();
            }
        }
        return result;
    }
}
