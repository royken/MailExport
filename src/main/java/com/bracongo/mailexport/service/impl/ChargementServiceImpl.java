package com.bracongo.mailexport.service.impl;

import com.bracongo.mailexport.dao.IChargementSRDDao;
import com.bracongo.mailexport.dao.ITbCentreDistributionDao;
import com.bracongo.mailexport.data.RepChargementSrd;
import com.bracongo.mailexport.data.TbCentreDistribution;
import com.bracongo.mailexport.data.dto.ChargementGlobalCDDto;
import com.bracongo.mailexport.data.dto.ChargementGlobalProduitDto;
import com.bracongo.mailexport.data.dto.ChargementProduitDto;
import com.bracongo.mailexport.service.IChargementService;
import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.Calendar;
import java.util.Date;
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
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.extensions.XSSFCellBorder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
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
    private ITbCentreDistributionDao centreDistributionDao;

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

    XSSFCellStyle chargementTitleStyle;
    XSSFCellStyle chargementProduitStyle;
    XSSFCellStyle chargementTotalStyle;
    XSSFCellStyle chargementTotalGlobalStyle;
    XSSFCellStyle simpleStyle;

    XSSFFont greenFont;
    XSSFFont redFont;
    XSSFFont whiteFont;
    XSSFFont blackFont;
    XSSFFont simpleFont;

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
    public void produceChargementResumeAndMail() {
        try {
            Calendar cal = Calendar.getInstance();
            if (cal.get(Calendar.DAY_OF_MONTH) == 1) {
                cal.add(Calendar.MONTH, -1);
            }
            int annee = cal.get(Calendar.YEAR);
            int mois = (cal.get(Calendar.MONTH) + 1);

            XSSFWorkbook workbook = new XSSFWorkbook();
            greenFont = workbook.createFont();

            redFont = workbook.createFont();

            whiteFont = workbook.createFont();
            whiteFont.setFontHeightInPoints((short) 11);
            whiteFont.setFontName("Calibri");
            whiteFont.setColor(IndexedColors.WHITE.getIndex());
            whiteFont.setBold(true);
            whiteFont.setItalic(false);

            blackFont = workbook.createFont();
            blackFont.setFontHeightInPoints((short) 11);
            blackFont.setFontName("Calibri");
            blackFont.setColor(IndexedColors.BLACK.getIndex());
            blackFont.setBold(true);
            blackFont.setItalic(false);

            simpleFont = workbook.createFont();
            simpleFont.setFontHeightInPoints((short) 11);
            simpleFont.setFontName("Calibri");
            simpleFont.setColor(IndexedColors.BLACK.getIndex());
            simpleFont.setBold(false);
            simpleFont.setItalic(false);

            chargementTitleStyle = workbook.createCellStyle();
            chargementTitleStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            chargementTitleStyle.setFillForegroundColor(IndexedColors.RED.getIndex());
            chargementTitleStyle.setAlignment(HorizontalAlignment.CENTER);
            chargementTitleStyle.setLeftBorderColor(blackBorderColor);
            chargementTitleStyle.setRightBorderColor(blackBorderColor);
            chargementTitleStyle.setTopBorderColor(blackBorderColor);
            chargementTitleStyle.setBottomBorderColor(blackBorderColor);
            chargementTitleStyle.setBorderTop(BorderStyle.THIN);
            chargementTitleStyle.setBorderLeft(BorderStyle.THIN);
            chargementTitleStyle.setBorderRight(BorderStyle.THIN);
            chargementTitleStyle.setBorderBottom(BorderStyle.THIN);
            chargementTitleStyle.setFont(whiteFont);

            chargementProduitStyle = workbook.createCellStyle();
            chargementProduitStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            chargementProduitStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
            chargementProduitStyle.setAlignment(HorizontalAlignment.CENTER);
            chargementProduitStyle.setLeftBorderColor(blackBorderColor);
            chargementProduitStyle.setRightBorderColor(blackBorderColor);
            chargementProduitStyle.setTopBorderColor(blackBorderColor);
            chargementProduitStyle.setBottomBorderColor(blackBorderColor);
            chargementProduitStyle.setBorderTop(BorderStyle.THIN);
            chargementProduitStyle.setBorderLeft(BorderStyle.THIN);
            chargementProduitStyle.setBorderRight(BorderStyle.THIN);
            chargementProduitStyle.setBorderBottom(BorderStyle.THIN);
            chargementProduitStyle.setFont(blackFont);

            chargementTotalStyle = workbook.createCellStyle();
            chargementTotalStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            chargementTotalStyle.setFillForegroundColor(IndexedColors.LIGHT_ORANGE.getIndex());
            chargementTotalStyle.setAlignment(HorizontalAlignment.CENTER);
            chargementTotalStyle.setLeftBorderColor(blackBorderColor);
            chargementTotalStyle.setRightBorderColor(blackBorderColor);
            chargementTotalStyle.setTopBorderColor(blackBorderColor);
            chargementTotalStyle.setBottomBorderColor(blackBorderColor);
            chargementTotalStyle.setBorderTop(BorderStyle.THIN);
            chargementTotalStyle.setBorderLeft(BorderStyle.THIN);
            chargementTotalStyle.setBorderRight(BorderStyle.THIN);
            chargementTotalStyle.setBorderBottom(BorderStyle.THIN);
            chargementTotalStyle.setFont(blackFont);

            chargementTotalGlobalStyle = workbook.createCellStyle();
            chargementTotalGlobalStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            chargementTotalGlobalStyle.setFillForegroundColor(IndexedColors.PALE_BLUE.getIndex());
            chargementTotalGlobalStyle.setAlignment(HorizontalAlignment.CENTER);
            chargementTotalGlobalStyle.setLeftBorderColor(blackBorderColor);
            chargementTotalGlobalStyle.setRightBorderColor(blackBorderColor);
            chargementTotalGlobalStyle.setTopBorderColor(blackBorderColor);
            chargementTotalGlobalStyle.setBottomBorderColor(blackBorderColor);
            chargementTotalGlobalStyle.setBorderTop(BorderStyle.THIN);
            chargementTotalGlobalStyle.setBorderLeft(BorderStyle.THIN);
            chargementTotalGlobalStyle.setBorderRight(BorderStyle.THIN);
            chargementTotalGlobalStyle.setBorderBottom(BorderStyle.THIN);
            chargementTotalGlobalStyle.setFont(blackFont);

            simpleStyle = workbook.createCellStyle();
            simpleStyle.setAlignment(HorizontalAlignment.CENTER);
            simpleStyle.setLeftBorderColor(blackBorderColor);
            simpleStyle.setRightBorderColor(blackBorderColor);
            simpleStyle.setTopBorderColor(blackBorderColor);
            simpleStyle.setBottomBorderColor(blackBorderColor);
            simpleStyle.setFont(simpleFont);
            simpleStyle.setBorderTop(BorderStyle.THIN);
            simpleStyle.setBorderLeft(BorderStyle.THIN);
            simpleStyle.setBorderRight(BorderStyle.THIN);
            simpleStyle.setBorderBottom(BorderStyle.THIN);

            XSSFSheet recapSheet = workbook.createSheet("GLOBAL");
            XSSFSheet voyage1Sheet = workbook.createSheet("VOYAGE 1");
            XSSFSheet voyage2sSheet = workbook.createSheet("VOYAGE 2");
            XSSFSheet bruteDatasSheet = workbook.createSheet("DONNEES BRUTES");

            List<ChargementProduitDto> chargementRecap = chargementSRDDao.getAllChargementDetailByMonth(mois, annee);
            List<ChargementProduitDto> chargementRecapVoyage1 = chargementSRDDao.getAllChargementDetailByMonthVoyageId(mois, annee, 1);
            List<ChargementProduitDto> chargementRecapVoyage2 = chargementSRDDao.getAllChargementDetailByMonthVoyageId(mois, annee, 2);
            List<ChargementProduitDto> chargementDetail = chargementSRDDao.getAllChargementDetailByMonthVoyageId(mois, annee);
            List<TbCentreDistribution> cds = centreDistributionDao.getAllCdSigma();

            produceRecapSheet(recapSheet, chargementRecap, cds, mois, annee, workbook);
            produceRecapVoyage1Sheet(voyage1Sheet, chargementRecapVoyage1, cds, mois, annee);
            produceRecapVoyage2Sheet(voyage2sSheet, chargementRecapVoyage2, cds, mois, annee);
            produceBruteDataSheet(bruteDatasSheet, chargementDetail);
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

            helper.setTo(new String[]{"guelor.nkulu@castel-afrique.com", "rosine.modiri@castel-afrique.com", "yves.menemene@castel-afrique.com", "bangoma.dakwa@castel-afrique.com", "jeremie.lutunu@castel-afrique.com", "tiago.godinho@castel-afrique.com", "pierre.sanakiaku@castel-afrique.com", "anselme.pero@castel-afrique.com", "patty.nsaraza@castel-afrique.com", "helene.kitenge@castel-afrique.com", "anaclet.lawaba@castel-afrique.com", "Mathias.BEKANGBA@castel-afrique.com", "jules.bolebe@castel-afrique.com", "gregoire.sombolayi@castel-afrique.com", "jason.djemo@castel-afrique.com", "nadege.vundu@castel-afrique.com"});
            helper.setCc("valmy.roikenfack@castel-afrique.com");
            helper.setSubject("Rapport Automatique Chargement vs Retour vs Ventes - " + getMonthYearString(mois, annee));
            helper.setFrom("BRACONGO.Reportbusiness@castel-afrique.com");
            MimeBodyPart messageBodyPart1 = new MimeBodyPart();
            messageBodyPart1.setDataHandler(new DataHandler(fds));

//messageBodyPart1.setFileName(fds.getName());
            messageBodyPart1.setFileName("Rapport_Chargement_Ventes_Retour.xlsx");
            Multipart mpart = new MimeMultipart();
            mpart.addBodyPart(messageBodyPart1);
            message.setContent(mpart);
            sender.send(message);
        } catch (IOException | MessagingException ex) {
            Logger.getLogger(ChargementServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void produceRecapSheet(XSSFSheet recapSheet, List<ChargementProduitDto> chargementRecap, List<TbCentreDistribution> cds, int mois, int annee, Workbook workbook) throws IOException {

        InputStream inputStream = null;
        try {
            HashMap<String, Produit> produits = getListProduits(chargementRecap);
            int rowId = 0;
            int colId = 1;
            Cell cell;
            Row row = recapSheet.createRow(rowId);
            cell = row.createCell(colId);
            cell.setCellValue("RAPPORT CHARGEMENT SRD VS VENTES VS RETOURS Global- " + getMonthYearString(mois, annee));
            cell.setCellStyle(chargementTitleStyle);
            recapSheet.addMergedRegion(new CellRangeAddress(
                    rowId, //first row (0-based)
                    rowId, //last row  (0-based)
                    colId, //first column (0-based)
                    produits.size() * 4 + 4 //last column  (0-based)
            ));
            rowId++;
            colId = 0;
            row = recapSheet.createRow(rowId);
            File resource = new ClassPathResource("logo_bracongo.png").getFile();
            System.out.println("FILE = " + resource.toString());
            inputStream = new FileInputStream(resource);
            byte[] imageBytes = IOUtils.toByteArray(inputStream);
            int pictureureIdx = workbook.addPicture(imageBytes, Workbook.PICTURE_TYPE_PNG);
            inputStream.close();
            CreationHelper helper = workbook.getCreationHelper();
            Drawing drawing = recapSheet.createDrawingPatriarch();
            ClientAnchor anchor = helper.createClientAnchor();
            anchor.setCol1(0);
            anchor.setRow1(0);
            anchor.setCol2(0); //Column C
            anchor.setRow2(2);
            Picture pict = drawing.createPicture(anchor, pictureureIdx);
            pict.resize();
            colId = 1;
            for (Map.Entry<String, Produit> entry : produits.entrySet()) {
                Produit produit = entry.getValue();
                cell = row.createCell(colId);
                cell.setCellValue(produit.getNom().trim());
                cell.setCellStyle(chargementProduitStyle);
                recapSheet.addMergedRegion(new CellRangeAddress(
                        rowId, //first row (0-based)
                        rowId, //last row  (0-based)
                        colId, //first column (0-based)
                        colId + 3 //last column  (0-based)
                ));
                colId += 4;
            }
            cell = row.createCell(colId);
            cell.setCellValue("TOTAL CD");
            cell.setCellStyle(chargementTotalGlobalStyle);
            recapSheet.addMergedRegion(new CellRangeAddress(
                    rowId, //first row (0-based)
                    rowId, //last row  (0-based)
                    colId, //first column (0-based)
                    colId + 3 //last column  (0-based)
            ));
            rowId++;
            row = recapSheet.createRow(rowId);
            colId = 1;
            for (Map.Entry<String, Produit> entry : produits.entrySet()) {
                cell = row.createCell(colId++);
                cell.setCellValue("Chargée");
                cell.setCellStyle(simpleStyle);
                cell = row.createCell(colId++);
                cell.setCellValue("Vendue");
                cell.setCellStyle(simpleStyle);
                cell = row.createCell(colId++);
                cell.setCellValue("Retour");
                cell.setCellStyle(simpleStyle);
                cell = row.createCell(colId++);
                cell.setCellValue("% Retour");
                cell.setCellStyle(simpleStyle);
            }
            cell = row.createCell(colId++);
            cell.setCellValue("Chargée");
            cell.setCellStyle(simpleStyle);
            cell = row.createCell(colId++);
            cell.setCellValue("Vendue");
            cell.setCellStyle(simpleStyle);
            cell = row.createCell(colId++);
            cell.setCellValue("Retour");
            cell.setCellStyle(simpleStyle);
            cell = row.createCell(colId++);
            cell.setCellValue("% Retour");
            cell.setCellStyle(simpleStyle);
            rowId++;
            colId = 0;
            ChargementData data;
            for (TbCentreDistribution cd : cds) {
                row = recapSheet.createRow(rowId);
                cell = row.createCell(colId++);
                cell.setCellValue(cd.getCdiNomcdi().trim());
                cell.setCellStyle(chargementProduitStyle);
                for (Map.Entry<String, Produit> entry : produits.entrySet()) {
                    Produit produit = entry.getValue();
                    data = getChargementDataForCdByProduit(chargementRecap, cd.getCdiCodecd().trim(), produit.getCode().trim());
                    cell = row.createCell(colId++);
                    cell.setCellValue(data.getCharge());
                    cell.setCellStyle(simpleStyle);
                    cell = row.createCell(colId++);
                    cell.setCellValue(data.getVendu());
                    cell.setCellStyle(simpleStyle);
                    cell = row.createCell(colId++);
                    cell.setCellValue(data.getRetour());
                    cell.setCellStyle(simpleStyle);
                    cell = row.createCell(colId++);
                    cell.setCellValue(data.charge == 0 ? "" : Math.round((data.getRetour() / data.getCharge()) * 100) + "%");
                    cell.setCellStyle(simpleStyle);
                }
                data = getChargementDataForCd(chargementRecap, cd.getCdiCodecd().trim());
                cell = row.createCell(colId++);
                cell.setCellValue(data.getCharge());
                cell.setCellStyle(chargementTotalGlobalStyle);
                cell = row.createCell(colId++);
                cell.setCellValue(data.getVendu());
                cell.setCellStyle(chargementTotalGlobalStyle);
                cell = row.createCell(colId++);
                cell.setCellValue(data.getRetour());
                cell.setCellStyle(chargementTotalGlobalStyle);
                cell = row.createCell(colId++);
                cell.setCellValue(data.charge == 0 ? "" : Math.round((data.getRetour() / data.getCharge()) * 100) + "%");
                cell.setCellStyle(chargementTotalGlobalStyle);
                rowId++;
                colId = 0;
            }
            rowId++;
            colId = 0;
            row = recapSheet.createRow(rowId);
            cell = row.createCell(colId++);
            cell.setCellValue("TOTAL");
            cell.setCellStyle(chargementTotalStyle);
            for (Map.Entry<String, Produit> entry : produits.entrySet()) {
                Produit produit = entry.getValue();
                data = getChargementDataForProduit(chargementRecap, produit.getCode().trim());
                cell = row.createCell(colId++);
                cell.setCellValue(data.getCharge());
                cell.setCellStyle(chargementTotalStyle);
                cell = row.createCell(colId++);
                cell.setCellValue(data.getVendu());
                cell.setCellStyle(chargementTotalStyle);
                cell = row.createCell(colId++);
                cell.setCellValue(data.getRetour());
                cell.setCellStyle(chargementTotalStyle);
                cell = row.createCell(colId++);
                cell.setCellValue(data.charge == 0 ? "" : Math.round((data.getRetour() / data.getCharge()) * 100) + "%");
                cell.setCellStyle(chargementTotalStyle);
            }
            data = getChargementDataTotal(chargementRecap);
            cell = row.createCell(colId++);
            cell.setCellValue(data.getCharge());
            cell.setCellStyle(chargementTotalGlobalStyle);
            cell = row.createCell(colId++);
            cell.setCellValue(data.getVendu());
            cell.setCellStyle(chargementTotalGlobalStyle);
            cell = row.createCell(colId++);
            cell.setCellValue(data.getRetour());
            cell.setCellStyle(chargementTotalGlobalStyle);
            cell = row.createCell(colId++);
            cell.setCellValue(data.charge == 0 ? "" : Math.round((data.getRetour() / data.getCharge()) * 100) + "%");
            cell.setCellStyle(chargementTotalGlobalStyle);
            rowId += 2;
            colId = 1;
            row = recapSheet.createRow(rowId);
            String[] familles = new String[]{"BI", "BG", "XXL", "EAUX", "VIN"};
            for (String famille : familles) {
                cell = row.createCell(colId);
                cell.setCellValue(getFamilleNameFromFamilleCode(famille.trim()));
                cell.setCellStyle(chargementProduitStyle);
                recapSheet.addMergedRegion(new CellRangeAddress(
                        rowId, //first row (0-based)
                        rowId, //last row  (0-based)
                        colId, //first column (0-based)
                        colId + 3 //last column  (0-based)
                ));
                colId += 4;
            }
            rowId++;
            colId = 0;
            for (TbCentreDistribution cd : cds) {
                row = recapSheet.createRow(rowId++);
                cell = row.createCell(colId++);
                cell.setCellValue(cd.getCdiNomcdi().trim());
                cell.setCellStyle(chargementProduitStyle);
                for (String famille : familles) {
                    data = getChargementDataForCdByFamille(chargementRecap, cd.getCdiCodecd().trim(), famille.trim());
                    cell = row.createCell(colId++);
                    cell.setCellValue(data.getCharge());
                    cell.setCellStyle(simpleStyle);
                    cell = row.createCell(colId++);
                    cell.setCellValue(data.getVendu());
                    cell.setCellStyle(simpleStyle);
                    cell = row.createCell(colId++);
                    cell.setCellValue(data.getRetour());
                    cell.setCellStyle(simpleStyle);
                    cell = row.createCell(colId++);
                    cell.setCellValue(data.charge == 0 ? "" : Math.round((data.getRetour() / data.getCharge()) * 100) + "%");
                    cell.setCellStyle(simpleStyle);
                }
                colId = 0;
            }
            row = recapSheet.createRow(rowId++);
            cell = row.createCell(colId++);
            cell.setCellValue("TOTAL");
            cell.setCellStyle(chargementTotalStyle);
            for (String famille : familles) {
                data = getChargementDataForFamille(chargementRecap, famille.trim());
                cell = row.createCell(colId++);
                cell.setCellValue(data.getCharge());
                cell.setCellStyle(chargementTotalStyle);
                cell = row.createCell(colId++);
                cell.setCellValue(data.getVendu());
                cell.setCellStyle(chargementTotalStyle);
                cell = row.createCell(colId++);
                cell.setCellValue(data.getRetour());
                cell.setCellStyle(chargementTotalStyle);
                cell = row.createCell(colId++);
                cell.setCellValue(data.charge == 0 ? "" : Math.round((data.getRetour() / data.getCharge()) * 100) + "%");
                cell.setCellStyle(chargementTotalStyle);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ChargementServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                inputStream.close();
            } catch (IOException ex) {
                Logger.getLogger(ChargementServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    private void produceRecapVoyage1Sheet(XSSFSheet recapSheet, List<ChargementProduitDto> chargementRecap, List<TbCentreDistribution> cds, int mois, int annee) {
        HashMap<String, Produit> produits = getListProduits(chargementRecap);
        int rowId = 0;
        int colId = 1;
        Cell cell;
        Row row = recapSheet.createRow(rowId);
        cell = row.createCell(colId);
        cell.setCellValue("RAPPORT CHARGEMENT SRD VS VENTES VS RETOURS " + getMonthYearString(mois, annee) + " : PREMIER VOYAGE");
        cell.setCellStyle(chargementTitleStyle);
        recapSheet.addMergedRegion(new CellRangeAddress(
                rowId, //first row (0-based)
                rowId, //last row  (0-based)
                colId, //first column (0-based)
                produits.size() * 4 + 4 //last column  (0-based)
        ));

        rowId++;
        row = recapSheet.createRow(rowId);
        colId = 1;
        for (Map.Entry<String, Produit> entry : produits.entrySet()) {
            Produit produit = entry.getValue();
            cell = row.createCell(colId);
            cell.setCellValue(produit.getNom().trim());
            cell.setCellStyle(chargementProduitStyle);
            recapSheet.addMergedRegion(new CellRangeAddress(
                    rowId, //first row (0-based)
                    rowId, //last row  (0-based)
                    colId, //first column (0-based)
                    colId + 3 //last column  (0-based)
            ));
            colId += 4;
        }
        cell = row.createCell(colId);
        cell.setCellValue("TOTAL CD");
        cell.setCellStyle(chargementTotalGlobalStyle);
        recapSheet.addMergedRegion(new CellRangeAddress(
                rowId, //first row (0-based)
                rowId, //last row  (0-based)
                colId, //first column (0-based)
                colId + 4 //last column  (0-based)
        ));

        rowId++;
        row = recapSheet.createRow(rowId);
        colId = 1;
        for (Map.Entry<String, Produit> entry : produits.entrySet()) {
            cell = row.createCell(colId++);
            cell.setCellValue("Chargée");
            cell.setCellStyle(simpleStyle);
            cell = row.createCell(colId++);
            cell.setCellValue("Vendue");
            cell.setCellStyle(simpleStyle);
            cell = row.createCell(colId++);
            cell.setCellValue("Retour");
            cell.setCellStyle(simpleStyle);
            cell = row.createCell(colId++);
            cell.setCellValue("% Retour");
            cell.setCellStyle(simpleStyle);
        }
        cell = row.createCell(colId++);
        cell.setCellValue("Chargée");
        cell.setCellStyle(simpleStyle);
        cell = row.createCell(colId++);
        cell.setCellValue("Vendue");
        cell.setCellStyle(simpleStyle);
        cell = row.createCell(colId++);
        cell.setCellValue("Retour");
        cell.setCellStyle(simpleStyle);
        cell = row.createCell(colId++);
        cell.setCellValue("% Retour");
        cell.setCellStyle(simpleStyle);

        rowId++;
        colId = 0;

        ChargementData data;
        for (TbCentreDistribution cd : cds) {
            row = recapSheet.createRow(rowId);
            cell = row.createCell(colId++);
            cell.setCellValue(cd.getCdiNomcdi().trim());
            cell.setCellStyle(chargementProduitStyle);
            for (Map.Entry<String, Produit> entry : produits.entrySet()) {
                Produit produit = entry.getValue();
                data = getChargementDataForCdByProduit(chargementRecap, cd.getCdiCodecd().trim(), produit.getCode().trim());
                cell = row.createCell(colId++);
                cell.setCellValue(data.getCharge());
                cell.setCellStyle(simpleStyle);
                cell = row.createCell(colId++);
                cell.setCellValue(data.getVendu());
                cell.setCellStyle(simpleStyle);
                cell = row.createCell(colId++);
                cell.setCellValue(data.getRetour());
                cell.setCellStyle(simpleStyle);
                cell = row.createCell(colId++);
                cell.setCellValue(data.charge == 0 ? "" : Math.round((data.getRetour() / data.getCharge()) * 100) + "%");
                cell.setCellStyle(simpleStyle);
            }
            data = getChargementDataForCd(chargementRecap, cd.getCdiCodecd().trim());
            cell = row.createCell(colId++);
            cell.setCellValue(data.getCharge());
            cell.setCellStyle(chargementTotalGlobalStyle);
            cell = row.createCell(colId++);
            cell.setCellValue(data.getVendu());
            cell.setCellStyle(chargementTotalGlobalStyle);
            cell = row.createCell(colId++);
            cell.setCellValue(data.getRetour());
            cell.setCellStyle(chargementTotalGlobalStyle);
            cell = row.createCell(colId++);
            cell.setCellValue(data.charge == 0 ? "" : Math.round((data.getRetour() / data.getCharge()) * 100) + "%");
            cell.setCellStyle(chargementTotalGlobalStyle);
            rowId++;
            colId = 0;
        }

        rowId++;
        colId = 0;
        row = recapSheet.createRow(rowId);
        cell = row.createCell(colId++);
        cell.setCellValue("TOTAL");
        cell.setCellStyle(chargementTotalStyle);
        for (Map.Entry<String, Produit> entry : produits.entrySet()) {
            Produit produit = entry.getValue();
            data = getChargementDataForProduit(chargementRecap, produit.getCode().trim());
            cell = row.createCell(colId++);
            cell.setCellValue(data.getCharge());
            cell.setCellStyle(chargementTotalStyle);
            cell = row.createCell(colId++);
            cell.setCellValue(data.getVendu());
            cell.setCellStyle(chargementTotalStyle);
            cell = row.createCell(colId++);
            cell.setCellValue(data.getRetour());
            cell.setCellStyle(chargementTotalStyle);
            cell = row.createCell(colId++);
            cell.setCellValue(data.charge == 0 ? "" : Math.round((data.getRetour() / data.getCharge()) * 100) + "%");
            cell.setCellStyle(chargementTotalStyle);
        }
        data = getChargementDataTotal(chargementRecap);
        cell = row.createCell(colId++);
        cell.setCellValue(data.getCharge());
        cell.setCellStyle(chargementTotalGlobalStyle);
        cell = row.createCell(colId++);
        cell.setCellValue(data.getVendu());
        cell.setCellStyle(chargementTotalGlobalStyle);
        cell = row.createCell(colId++);
        cell.setCellValue(data.getRetour());
        cell.setCellStyle(chargementTotalGlobalStyle);
        cell = row.createCell(colId++);
        cell.setCellValue(data.charge == 0 ? "" : Math.round((data.getRetour() / data.getCharge()) * 100) + "%");
        cell.setCellStyle(chargementTotalGlobalStyle);

        rowId += 2;
        colId = 1;
        row = recapSheet.createRow(rowId);
        String[] familles = new String[]{"BI", "BG", "XXL", "EAUX", "VIN"};
        for (String famille : familles) {
            cell = row.createCell(colId);
            cell.setCellValue(getFamilleNameFromFamilleCode(famille.trim()));
            cell.setCellStyle(chargementProduitStyle);
            recapSheet.addMergedRegion(new CellRangeAddress(
                    rowId, //first row (0-based)
                    rowId, //last row  (0-based)
                    colId, //first column (0-based)
                    colId + 3 //last column  (0-based)
            ));
            colId += 4;
        }

        rowId++;
        colId = 0;
        for (TbCentreDistribution cd : cds) {
            row = recapSheet.createRow(rowId);
            cell = row.createCell(colId++);
            cell.setCellValue(cd.getCdiNomcdi().trim());
            cell.setCellStyle(chargementProduitStyle);
            for (String famille : familles) {
                data = getChargementDataForCdByFamille(chargementRecap, cd.getCdiCodecd().trim(), famille.trim());
                cell = row.createCell(colId++);
                cell.setCellValue(data.getCharge());
                cell.setCellStyle(simpleStyle);
                cell = row.createCell(colId++);
                cell.setCellValue(data.getVendu());
                cell.setCellStyle(simpleStyle);
                cell = row.createCell(colId++);
                cell.setCellValue(data.getRetour());
                cell.setCellStyle(simpleStyle);
                cell = row.createCell(colId++);
                cell.setCellValue(data.charge == 0 ? "" : Math.round((data.getRetour() / data.getCharge()) * 100) + "%");
                cell.setCellStyle(simpleStyle);
            }
            rowId++;
            colId = 0;
        }
        row = recapSheet.createRow(rowId++);
        cell = row.createCell(colId++);
        cell.setCellValue("TOTAL");
        cell.setCellStyle(chargementTotalStyle);
        for (String famille : familles) {
            data = getChargementDataForFamille(chargementRecap, famille.trim());
            cell = row.createCell(colId++);
            cell.setCellValue(data.getCharge());
            cell.setCellStyle(chargementTotalStyle);
            cell = row.createCell(colId++);
            cell.setCellValue(data.getVendu());
            cell.setCellStyle(chargementTotalStyle);
            cell = row.createCell(colId++);
            cell.setCellValue(data.getRetour());
            cell.setCellStyle(chargementTotalStyle);
            cell = row.createCell(colId++);
            cell.setCellValue(data.charge == 0 ? "" : Math.round((data.getRetour() / data.getCharge()) * 100) + "%");
            cell.setCellStyle(chargementTotalStyle);
        }
    }

    private void produceRecapVoyage2Sheet(XSSFSheet recapSheet, List<ChargementProduitDto> chargementRecap, List<TbCentreDistribution> cds, int mois, int annee) {
        HashMap<String, Produit> produits = getListProduits(chargementRecap);
        int rowId = 0;
        int colId = 1;
        Cell cell;
        Row row = recapSheet.createRow(rowId);
        cell = row.createCell(colId);
        cell.setCellValue("RAPPORT CHARGEMENT SRD VS VENTES VS RETOURS " + getMonthYearString(mois, annee) + " : SECOND VOYAGE");
        cell.setCellStyle(chargementTitleStyle);
        recapSheet.addMergedRegion(new CellRangeAddress(
                rowId, //first row (0-based)
                rowId, //last row  (0-based)
                colId, //first column (0-based)
                produits.size() * 4 + 4 //last column  (0-based)
        ));

        rowId++;
        row = recapSheet.createRow(rowId);
        colId = 1;
        for (Map.Entry<String, Produit> entry : produits.entrySet()) {
            Produit produit = entry.getValue();
            cell = row.createCell(colId);
            cell.setCellValue(produit.getNom().trim());
            cell.setCellStyle(chargementProduitStyle);
            recapSheet.addMergedRegion(new CellRangeAddress(
                    rowId, //first row (0-based)
                    rowId, //last row  (0-based)
                    colId, //first column (0-based)
                    colId + 3 //last column  (0-based)
            ));
            colId += 4;
        }
        cell = row.createCell(colId);
        cell.setCellValue("TOTAL CD");
        cell.setCellStyle(chargementTotalGlobalStyle);
        recapSheet.addMergedRegion(new CellRangeAddress(
                rowId, //first row (0-based)
                rowId, //last row  (0-based)
                colId, //first column (0-based)
                colId + 4 //last column  (0-based)
        ));

        rowId++;
        row = recapSheet.createRow(rowId);
        colId = 1;
        for (Map.Entry<String, Produit> entry : produits.entrySet()) {
            cell = row.createCell(colId++);
            cell.setCellValue("Chargée");
            cell.setCellStyle(simpleStyle);
            cell = row.createCell(colId++);
            cell.setCellValue("Vendue");
            cell.setCellStyle(simpleStyle);
            cell = row.createCell(colId++);
            cell.setCellValue("Retour");
            cell.setCellStyle(simpleStyle);
            cell = row.createCell(colId++);
            cell.setCellValue("% Retour");
            cell.setCellStyle(simpleStyle);
        }
        cell = row.createCell(colId++);
        cell.setCellValue("Chargée");
        cell.setCellStyle(simpleStyle);
        cell = row.createCell(colId++);
        cell.setCellValue("Vendue");
        cell.setCellStyle(simpleStyle);
        cell = row.createCell(colId++);
        cell.setCellValue("Retour");
        cell.setCellStyle(simpleStyle);
        cell = row.createCell(colId++);
        cell.setCellValue("% Retour");
        cell.setCellStyle(simpleStyle);

        rowId++;
        colId = 0;

        ChargementData data;
        for (TbCentreDistribution cd : cds) {
            row = recapSheet.createRow(rowId);
            cell = row.createCell(colId++);
            cell.setCellValue(cd.getCdiNomcdi().trim());
            cell.setCellStyle(chargementProduitStyle);
            for (Map.Entry<String, Produit> entry : produits.entrySet()) {
                Produit produit = entry.getValue();
                data = getChargementDataForCdByProduit(chargementRecap, cd.getCdiCodecd().trim(), produit.getCode().trim());
                cell = row.createCell(colId++);
                cell.setCellValue(data.getCharge());
                cell.setCellStyle(simpleStyle);
                cell = row.createCell(colId++);
                cell.setCellValue(data.getVendu());
                cell.setCellStyle(simpleStyle);
                cell = row.createCell(colId++);
                cell.setCellValue(data.getRetour());
                cell.setCellStyle(simpleStyle);
                cell = row.createCell(colId++);
                cell.setCellValue(data.charge == 0 ? "" : Math.round((data.getRetour() / data.getCharge()) * 100) + "%");
                cell.setCellStyle(simpleStyle);
            }
            data = getChargementDataForCd(chargementRecap, cd.getCdiCodecd().trim());
            cell = row.createCell(colId++);
            cell.setCellValue(data.getCharge());
            cell.setCellStyle(chargementTotalGlobalStyle);
            cell = row.createCell(colId++);
            cell.setCellValue(data.getVendu());
            cell.setCellStyle(chargementTotalGlobalStyle);
            cell = row.createCell(colId++);
            cell.setCellValue(data.getRetour());
            cell.setCellStyle(chargementTotalGlobalStyle);
            cell = row.createCell(colId++);
            cell.setCellValue(data.charge == 0 ? "" : Math.round((data.getRetour() / data.getCharge()) * 100) + "%");
            cell.setCellStyle(chargementTotalGlobalStyle);
            rowId++;
            colId = 0;
        }

        rowId++;
        colId = 0;
        row = recapSheet.createRow(rowId);
        cell = row.createCell(colId++);
        cell.setCellValue("TOTAL");
        cell.setCellStyle(chargementTotalStyle);
        for (Map.Entry<String, Produit> entry : produits.entrySet()) {
            Produit produit = entry.getValue();
            data = getChargementDataForProduit(chargementRecap, produit.getCode().trim());
            cell = row.createCell(colId++);
            cell.setCellValue(data.getCharge());
            cell.setCellStyle(chargementTotalStyle);
            cell = row.createCell(colId++);
            cell.setCellValue(data.getVendu());
            cell.setCellStyle(chargementTotalStyle);
            cell = row.createCell(colId++);
            cell.setCellValue(data.getRetour());
            cell.setCellStyle(chargementTotalStyle);
            cell = row.createCell(colId++);
            cell.setCellValue(data.charge == 0 ? "" : Math.round((data.getRetour() / data.getCharge()) * 100) + "%");
            cell.setCellStyle(chargementTotalStyle);
        }
        data = getChargementDataTotal(chargementRecap);
        cell = row.createCell(colId++);
        cell.setCellValue(data.getCharge());
        cell.setCellStyle(chargementTotalGlobalStyle);
        cell = row.createCell(colId++);
        cell.setCellValue(data.getVendu());
        cell.setCellStyle(chargementTotalGlobalStyle);
        cell = row.createCell(colId++);
        cell.setCellValue(data.getRetour());
        cell.setCellStyle(chargementTotalGlobalStyle);
        cell = row.createCell(colId++);
        cell.setCellValue(data.charge == 0 ? "" : Math.round((data.getRetour() / data.getCharge()) * 100) + "%");
        cell.setCellStyle(chargementTotalGlobalStyle);

        rowId += 4;
        colId = 1;
        row = recapSheet.createRow(rowId);
        String[] familles = new String[]{"BI", "BG", "XXL", "EAUX", "VIN"};
        for (String famille : familles) {
            cell = row.createCell(colId);
            cell.setCellValue(getFamilleNameFromFamilleCode(famille.trim()));
            cell.setCellStyle(chargementProduitStyle);
            recapSheet.addMergedRegion(new CellRangeAddress(
                    rowId, //first row (0-based)
                    rowId, //last row  (0-based)
                    colId, //first column (0-based)
                    colId + 3 //last column  (0-based)
            ));
            colId += 4;
        }

        rowId++;
        colId = 0;
        for (TbCentreDistribution cd : cds) {
            row = recapSheet.createRow(rowId);
            cell = row.createCell(colId++);
            cell.setCellValue(cd.getCdiNomcdi().trim());
            cell.setCellStyle(chargementProduitStyle);
            for (String famille : familles) {
                data = getChargementDataForCdByFamille(chargementRecap, cd.getCdiCodecd().trim(), famille.trim());
                cell = row.createCell(colId++);
                cell.setCellValue(data.getCharge());
                cell.setCellStyle(simpleStyle);
                cell = row.createCell(colId++);
                cell.setCellValue(data.getVendu());
                cell.setCellStyle(simpleStyle);
                cell = row.createCell(colId++);
                cell.setCellValue(data.getRetour());
                cell.setCellStyle(simpleStyle);
                cell = row.createCell(colId++);
                cell.setCellValue(data.charge == 0 ? "" : Math.round((data.getRetour() / data.getCharge()) * 100) + "%");
                cell.setCellStyle(simpleStyle);
            }
            rowId++;
            colId = 0;
        }
        row = recapSheet.createRow(rowId++);
        cell = row.createCell(colId++);
        cell.setCellValue("TOTAL");
        cell.setCellStyle(chargementTotalStyle);
        for (String famille : familles) {
            data = getChargementDataForFamille(chargementRecap, famille.trim());
            cell = row.createCell(colId++);
            cell.setCellValue(data.getCharge());
            cell.setCellStyle(chargementTotalStyle);
            cell = row.createCell(colId++);
            cell.setCellValue(data.getVendu());
            cell.setCellStyle(chargementTotalStyle);
            cell = row.createCell(colId++);
            cell.setCellValue(data.getRetour());
            cell.setCellStyle(chargementTotalStyle);
            cell = row.createCell(colId++);
            cell.setCellValue(data.charge == 0 ? "" : Math.round((data.getRetour() / data.getCharge()) * 100) + "%");
            cell.setCellStyle(chargementTotalStyle);
        }
    }

    /**
     * Retourne les données de chargement d'un produit pour un CD
     *
     * @param chargements
     * @param codeCd
     * @param codars
     * @return
     */
    private ChargementData getChargementDataForCdByProduit(List<ChargementProduitDto> chargements, String codeCd, String codars) {
        ChargementData result = new ChargementData(0, 0, 0);
        int charge = 0;
        int vendu = 0;
        int retour = 0;
        for (ChargementProduitDto chargement : chargements) {
            if (chargement.getCodeCd() == Integer.parseInt(codeCd.trim()) && chargement.getCodeProduit().trim().equalsIgnoreCase(codars.trim())) {
                charge += chargement.getQuantiteChargee();
                vendu += chargement.getQuantiteVendue();
                retour += chargement.getQuantiteRetour();
            }

        }
        result.setCharge(charge);
        result.setRetour(retour);
        result.setVendu(vendu);
        return result;
    }

    /**
     * Retourne les données de chargement d'une famille de produits pour un CD
     *
     * @param chargements
     * @param codeCd
     * @param codars
     * @return
     */
    private ChargementData getChargementDataForCdByFamille(List<ChargementProduitDto> chargements, String codeCd, String famille) {
        ChargementData result = new ChargementData(0, 0, 0);
        int charge = 0;
        int vendu = 0;
        int retour = 0;
        for (ChargementProduitDto chargement : chargements) {
            if (chargement.getCodeCd() == Integer.parseInt(codeCd) && chargement.getFamille().trim().equalsIgnoreCase(famille.trim())) {
                charge += chargement.getQuantiteChargee();
                vendu += chargement.getQuantiteVendue();
                retour += chargement.getQuantiteRetour();
            }

        }
        result.setCharge(charge);
        result.setRetour(retour);
        result.setVendu(vendu);
        return result;
    }

    /**
     * Retourne les données de chargement global pour un CD
     *
     * @param chargements
     * @param codeCd
     * @return
     */
    private ChargementData getChargementDataForCd(List<ChargementProduitDto> chargements, String codeCd) {
        ChargementData result = new ChargementData(0, 0, 0);
        int charge = 0;
        int vendu = 0;
        int retour = 0;
        for (ChargementProduitDto chargement : chargements) {
            if (chargement.getCodeCd() == Integer.parseInt(codeCd)) {
                charge += chargement.getQuantiteChargee();
                vendu += chargement.getQuantiteVendue();
                retour += chargement.getQuantiteRetour();
            }

        }
        result.setCharge(charge);
        result.setRetour(retour);
        result.setVendu(vendu);
        return result;
    }

    /**
     * Retourne les données de chargement global pour un produit
     *
     * @param chargements
     * @param codars
     * @return
     */
    private ChargementData getChargementDataForProduit(List<ChargementProduitDto> chargements, String codars) {
        ChargementData result = new ChargementData(0, 0, 0);
        int charge = 0;
        int vendu = 0;
        int retour = 0;
        for (ChargementProduitDto chargement : chargements) {
            if (chargement.getCodeProduit().trim().equalsIgnoreCase(codars.trim())) {
                charge += chargement.getQuantiteChargee();
                vendu += chargement.getQuantiteVendue();
                retour += chargement.getQuantiteRetour();
            }

        }
        result.setCharge(charge);
        result.setRetour(retour);
        result.setVendu(vendu);
        return result;
    }

    /**
     * Retourne les données de chargement global
     *
     * @param chargements
     * @return
     */
    private ChargementData getChargementDataTotal(List<ChargementProduitDto> chargements) {
        ChargementData result = new ChargementData(0, 0, 0);
        int charge = 0;
        int vendu = 0;
        int retour = 0;
        for (ChargementProduitDto chargement : chargements) {
            charge += chargement.getQuantiteChargee();
            vendu += chargement.getQuantiteVendue();
            retour += chargement.getQuantiteRetour();

        }
        result.setCharge(charge);
        result.setRetour(retour);
        result.setVendu(vendu);
        return result;
    }

    private HashMap<String, Produit> getListProduits(List<ChargementProduitDto> chargements) {
        HashMap<String, Produit> result = new HashMap<>();
        for (ChargementProduitDto chargement : chargements) {
            result.put(chargement.getCodeProduit().trim(), new Produit(chargement.getNomProduit().trim(), chargement.getCodeProduit().trim(), chargement.getFamille().trim()));
        }
        return result;
    }

    /**
     * Retourne les données de chargement global pour une famille
     *
     * @param chargements
     * @param codars
     * @return
     */
    private ChargementData getChargementDataForFamille(List<ChargementProduitDto> chargements, String famille) {
        ChargementData result = new ChargementData(0, 0, 0);
        int charge = 0;
        int vendu = 0;
        int retour = 0;
        for (ChargementProduitDto chargement : chargements) {
            if (chargement.getFamille().trim().equalsIgnoreCase(famille.trim())) {
                charge += chargement.getQuantiteChargee();
                vendu += chargement.getQuantiteVendue();
                retour += chargement.getQuantiteRetour();
            }

        }
        result.setCharge(charge);
        result.setRetour(retour);
        result.setVendu(vendu);
        return result;
    }

    private void produceBruteDataSheet(XSSFSheet bruteDatasSheet, List<ChargementProduitDto> chargementDetail) {
        int rowId = 0;
        int colId = 0;
        Cell cell;
        Row row = bruteDatasSheet.createRow(rowId);
        cell = row.createCell(colId++);
        cell.setCellValue("JOUR");
        cell.setCellStyle(chargementProduitStyle);
        cell = row.createCell(colId++);
        cell.setCellValue("MOIS");
        cell.setCellStyle(chargementProduitStyle);
        cell = row.createCell(colId++);
        cell.setCellValue("ANNEE");
        cell.setCellStyle(chargementProduitStyle);
        cell = row.createCell(colId++);
        cell.setCellValue("CIRCUIT");
        cell.setCellStyle(chargementProduitStyle);
        cell = row.createCell(colId++);
        cell.setCellValue("CODE_CD");
        cell.setCellStyle(chargementProduitStyle);
        cell = row.createCell(colId++);
        cell.setCellValue("NOM_CD");
        cell.setCellStyle(chargementProduitStyle);
        cell = row.createCell(colId++);
        cell.setCellValue("NTD");
        cell.setCellStyle(chargementProduitStyle);
        cell = row.createCell(colId++);
        cell.setCellValue("NO_VOYAGE");
        cell.setCellStyle(chargementProduitStyle);
        cell = row.createCell(colId++);
        cell.setCellValue("CODE_PRODUIT");
        cell.setCellStyle(chargementProduitStyle);
        cell = row.createCell(colId++);
        cell.setCellValue("NOM_PRODUIT");
        cell.setCellStyle(chargementProduitStyle);
        cell = row.createCell(colId++);
        cell.setCellValue("FAMILLE");
        cell.setCellStyle(chargementProduitStyle);
        cell = row.createCell(colId++);
        cell.setCellValue("QUANTITE_CHARGEE");
        cell.setCellStyle(chargementProduitStyle);
        cell = row.createCell(colId++);
        cell.setCellValue("QUANTITE_VENDUE");
        cell.setCellStyle(chargementProduitStyle);
        cell = row.createCell(colId++);
        cell.setCellValue("QUANTITE_RETOURNEE");
        cell.setCellStyle(chargementProduitStyle);
        cell = row.createCell(colId++);
        cell.setCellValue("TAUX_RETOUR");
        cell.setCellStyle(chargementProduitStyle);

        rowId++;
        colId = 0;
        for (ChargementProduitDto chargementProduitDto : chargementDetail) {
            row = bruteDatasSheet.createRow(rowId);
            cell = row.createCell(colId++);
            cell.setCellValue(chargementProduitDto.getJour());
            cell.setCellStyle(simpleStyle);
            cell = row.createCell(colId++);
            cell.setCellValue(chargementProduitDto.getMois());
            cell.setCellStyle(simpleStyle);
            cell = row.createCell(colId++);
            cell.setCellValue(chargementProduitDto.getAnnee());
            cell.setCellStyle(simpleStyle);
            cell = row.createCell(colId++);
            cell.setCellValue(chargementProduitDto.getCodeCircuit().trim());
            cell.setCellStyle(simpleStyle);
            cell = row.createCell(colId++);
            cell.setCellValue(chargementProduitDto.getCodeCd());
            cell.setCellStyle(simpleStyle);
            cell = row.createCell(colId++);
            cell.setCellValue(chargementProduitDto.getNomCd().trim());
            cell.setCellStyle(simpleStyle);
            cell = row.createCell(colId++);
            cell.setCellValue(chargementProduitDto.getNtd());
            cell.setCellStyle(simpleStyle);
            cell = row.createCell(colId++);
            cell.setCellValue(chargementProduitDto.getVoyageId());
            cell.setCellStyle(simpleStyle);
            cell = row.createCell(colId++);
            cell.setCellValue(chargementProduitDto.getCodeProduit().trim());
            cell.setCellStyle(simpleStyle);
            cell = row.createCell(colId++);
            cell.setCellValue(chargementProduitDto.getNomProduit().trim());
            cell.setCellStyle(simpleStyle);
            cell = row.createCell(colId++);
            cell.setCellValue(chargementProduitDto.getFamille().trim());
            cell.setCellStyle(simpleStyle);
            cell = row.createCell(colId++);
            cell.setCellValue(chargementProduitDto.getQuantiteChargee());
            cell.setCellStyle(simpleStyle);
            cell = row.createCell(colId++);
            cell.setCellValue(chargementProduitDto.getQuantiteVendue());
            cell.setCellStyle(simpleStyle);
            cell = row.createCell(colId++);
            cell.setCellValue(chargementProduitDto.getQuantiteRetour());
            cell.setCellStyle(simpleStyle);
            cell = row.createCell(colId++);
            cell.setCellValue(chargementProduitDto.getQuantiteChargee() == 0 ? "" : Math.round((chargementProduitDto.getQuantiteRetour() / chargementProduitDto.getQuantiteChargee()) * 100) + "%");
            cell.setCellStyle(chargementProduitStyle);
            rowId++;
            colId = 0;
        }
    }

    class ChargementData {

        private double charge;
        private double vendu;
        private double retour;

        public ChargementData(double charge, double vendu, double retour) {
            this.charge = charge;
            this.vendu = vendu;
            this.retour = retour;
        }

        public double getCharge() {
            return charge;
        }

        public void setCharge(double charge) {
            this.charge = charge;
        }

        public double getVendu() {
            return vendu;
        }

        public void setVendu(double vendu) {
            this.vendu = vendu;
        }

        public double getRetour() {
            return retour;
        }

        public void setRetour(double retour) {
            this.retour = retour;
        }

        @Override
        public String toString() {
            return "ChargementData{" + "charge=" + charge + ", vendu=" + vendu + ", retour=" + retour + '}';
        }

    }

    class Produit {

        private String nom;

        private String code;

        private String famille;

        public Produit() {
        }

        public Produit(String nom, String code, String famille) {
            this.nom = nom;
            this.code = code;
            this.famille = famille;
        }

        public String getNom() {
            return nom;
        }

        public void setNom(String nom) {
            this.nom = nom;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getFamille() {
            return famille;
        }

        public void setFamille(String famille) {
            this.famille = famille;
        }

        @Override
        public String toString() {
            return "Produit{" + "nom=" + nom + ", code=" + code + ", famille=" + famille + '}';
        }

    }

    private String getFamilleNameFromFamilleCode(String familleCode) {
        switch (familleCode) {
            case "BI":
                return "BIERES";
            case "BG":
                return "BOISSONS GAZEUSES (VC & PET)";
            case "EAUX":
                return "EAUX";
            case "XXL":
                return "XXL";
            case "VIN":
                return "VIN";
            default:
                return "BIERES";
        }
    }

    private String getMonthYearString(int mois, int annee) {
        return getMonthName(mois) + " " + annee;
    }

    private String getMonthName(int mois) {
        String[] months = {"JANVIER", "FEVRIER", "MARS", "AVRIL", "MAI", "JUIN", "JUILLET", "AOUT", "SEPTEMBRE", "OCTOBRE", "NOVEMBRE", "DECEMBRE"};
        return months[mois - 1];
    }

}
