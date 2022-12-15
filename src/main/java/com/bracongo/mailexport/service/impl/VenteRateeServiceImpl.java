package com.bracongo.mailexport.service.impl;

import com.bracongo.mailexport.dao.IRepVenteRateeDao;
import com.bracongo.mailexport.data.RepVenteRatee;
import com.bracongo.mailexport.data.dto.CircuitRateeProduit;
import com.bracongo.mailexport.data.dto.ErreurSaisieCdDto;
import com.bracongo.mailexport.data.dto.ErreurSaisieCircuitDto;
import com.bracongo.mailexport.data.dto.ProduitHectoRateeDto;
import com.bracongo.mailexport.data.dto.ProduitRatee;
import com.bracongo.mailexport.data.dto.VenteRateeCdDto;
import com.bracongo.mailexport.service.IVenteRateeService;
import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Path;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
public class VenteRateeServiceImpl implements IVenteRateeService {

    @Autowired
    private IRepVenteRateeDao repVenteRateeDao;

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

    @Override
    public List<RepVenteRatee> getAllVentesRatees() {
        Calendar cal = Calendar.getInstance();
        return repVenteRateeDao.getAllByMonthAnnee(cal.get(Calendar.MONTH) + 1, cal.get(Calendar.YEAR));
    }

    @Override
    public List<ProduitHectoRateeDto> getAllProduitRateeStat() {
        Calendar cal = Calendar.getInstance();
        return repVenteRateeDao.getVenteRateeStatByProduit(cal.get(Calendar.MONTH) + 1, cal.get(Calendar.YEAR));
    }

    @Override
    public List<VenteRateeCdDto> getAllRateeCdStat() {
        Date debut;
        Date fin;
        Calendar cal = Calendar.getInstance();
        if (cal.get(Calendar.DAY_OF_MONTH) == 1) {
            if (cal.get(Calendar.MONTH) == 0) {
                cal.add(Calendar.YEAR, -1);
            }
            cal.add(Calendar.MONTH, -1);
            cal.set(Calendar.DAY_OF_MONTH, 1);

            debut = cal.getTime();
            cal.set(Calendar.DAY_OF_MONTH, getNumberOfDaysInAGivenMonth(cal.get(Calendar.MONTH), cal.get(Calendar.YEAR)));
            fin = cal.getTime();
        } else {
            fin = cal.getTime();
            cal.set(Calendar.DAY_OF_MONTH, 1);
            debut = cal.getTime();
        }
        return repVenteRateeDao.getVenteRateeStatByCD(debut, fin);
    }

    @Override
    public OutputStream exportReportBetweenDates() {
        try {
            Date debut;
            Date fin;
            Calendar cal = Calendar.getInstance();
            if (cal.get(Calendar.DAY_OF_MONTH) == 1) {
                if (cal.get(Calendar.MONTH) == 0) {
                    cal.add(Calendar.YEAR, -1);
                }
                cal.add(Calendar.MONTH, -1);
                cal.set(Calendar.DAY_OF_MONTH, 1);

                debut = cal.getTime();
                cal.set(Calendar.DAY_OF_MONTH, getNumberOfDaysInAGivenMonth(cal.get(Calendar.MONTH), cal.get(Calendar.YEAR)));
                fin = cal.getTime();
            } else {
                fin = cal.getTime();
                cal.set(Calendar.DAY_OF_MONTH, 1);
                debut = cal.getTime();
            }

            List<RepVenteRatee> ventesRatee = repVenteRateeDao.findRepVenteRateeBetweenDate(debut, fin);
            List<ProduitRatee> produitsRates = repVenteRateeDao.getAllProduitRateeBetweenDates(debut, fin);
            List<CircuitRateeProduit> circuitProduits = repVenteRateeDao.getAllCdCircuitRateeBetweenDates(debut, fin);

            List<VenteRateeCdDto> rateeCd = repVenteRateeDao.getVenteRateeStatByCD(debut, fin);

            List<RepVenteRatee> erreursSaisies = repVenteRateeDao.findErreurSaisieBetweenDate(debut, fin);
            List<ErreurSaisieCdDto> erreursCd = repVenteRateeDao.getErreurSaisieCdStatBetweenDates(debut, fin);
            List<ErreurSaisieCircuitDto> erreusCircuits = repVenteRateeDao.getErreurSaisieCircuitStatBetweenDates(debut, fin);
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
            XSSFSheet raportCircuitDataSheet = workbook.createSheet("Rapport Circuit");
            XSSFSheet rateesDataSheet = workbook.createSheet("Ventes Ratées Data");
            XSSFSheet erreursRapportSheet = workbook.createSheet("Rapport Erreurs Saisie");
            XSSFSheet erreursDataSheet = workbook.createSheet("Erreurs Saisie Data");
            produceRapportCircuitSheet(raportCircuitDataSheet, ventesRatee, produitsRates, circuitProduits, rateeCd);
            produceDataSheet(rateesDataSheet, ventesRatee);
            produceRapportErreurCircuitSheet(erreursRapportSheet, erreursCd, erreusCircuits);
            produceErreurDataSheet(erreursDataSheet, erreursSaisies);
            File file = File.createTempFile("Report", "xlsx");
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

            //helper.setTo("ddis@bracongo.cd");
            helper.setTo(new String[]{"jason.djemo@castel-afrique.com", "gregoire.sombolayi@castel-afrique.com", "nadege.vundu@castel-afrique.com", "anaclet.lawaba@castel-afrique.com", "guelor.nkulu@castel-afrique.com", "jeremie.lutunu@castel-afrique.com", "bangoma.dakwa@castel-afrique.com", "rosine.modiri@castel-afrique.com"});
            helper.setSubject("Rapport Ventes ratées et Erreurs cumulées du mois");
            helper.setCc("valmy.roikenfack@castel-afrique.com");
            helper.setFrom("BRACONGO.Reportbusiness@castel-afrique.com");
            MimeBodyPart messageBodyPart1 = new MimeBodyPart();
            messageBodyPart1.setDataHandler(new DataHandler(fds));

            //messageBodyPart1.setFileName(fds.getName());
            messageBodyPart1.setFileName("Rapport_Ventes_Ratees.xlsx");
            Multipart mpart = new MimeMultipart();
            mpart.addBodyPart(messageBodyPart1);
            message.setContent(mpart);
            sender.send(message);
            return null;
        } catch (IOException | MessagingException ex) {
            Logger.getLogger(VenteRateeServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private void produceDataSheet(XSSFSheet rateesDataSheet, List<RepVenteRatee> ventesRatee) {
        int rowId = 0;
        int colId = 0;
        Row row = rateesDataSheet.createRow(rowId);
        Cell cell;
        cell = row.createCell(colId++);
        cell.setCellValue("CODE_FACT");
        cell = row.createCell(colId++);
        cell.setCellValue("CD");
        cell = row.createCell(colId++);
        cell.setCellValue("CODE_ROUTE");
        cell = row.createCell(colId++);
        cell.setCellValue("BL");
        cell = row.createCell(colId++);
        cell.setCellValue("DATE");
        cell = row.createCell(colId++);
        cell.setCellValue("CODE_CLT");
        cell = row.createCell(colId++);
        cell.setCellValue("CODE_ART");
        cell = row.createCell(colId++);
        cell.setCellValue("LIBELLE");
        cell = row.createCell(colId++);
        cell.setCellValue("QTE_SOUHAITEE");
        cell = row.createCell(colId++);
        cell.setCellValue("QTE_STOCK");
        cell = row.createCell(colId++);
        cell.setCellValue("QTE_VENDUE");
        cell = row.createCell(colId++);
        cell.setCellValue("QTE_RATEE");

        rowId++;
        colId = 0;
        for (RepVenteRatee ratee : ventesRatee) {
            row = rateesDataSheet.createRow(rowId);
            cell = row.createCell(colId);
            cell.setCellValue(ratee.getCodeFact().trim());
            colId++;

            cell = row.createCell(colId);
            cell.setCellValue(ratee.getCodeCd() + " - " + ratee.getNomCd().trim());
            colId++;

            cell = row.createCell(colId);
            cell.setCellValue(ratee.getCodeRoute().trim());
            colId++;

            cell = row.createCell(colId);
            cell.setCellValue(ratee.getBl().trim());
            colId++;

            cell = row.createCell(colId);
            cell.setCellValue(getStringFromDate(ratee.getDate()));
            colId++;

            cell = row.createCell(colId);
            cell.setCellValue(ratee.getCodeClt().trim());
            colId++;

            cell = row.createCell(colId);
            cell.setCellValue(ratee.getCodeArt().trim());
            colId++;

            cell = row.createCell(colId);
            cell.setCellValue(ratee.getLibelle().trim());
            colId++;

            cell = row.createCell(colId);
            cell.setCellValue(ratee.getQteSouhaitee());
            colId++;

            cell = row.createCell(colId);
            cell.setCellValue(ratee.getQteStock());
            colId++;

            cell = row.createCell(colId);
            cell.setCellValue(ratee.getQteAchetee());
            colId++;

            cell = row.createCell(colId);
            cell.setCellValue(ratee.getQteRatee());
            colId++;

            rowId++;
            colId = 0;
        }
    }

    private String getStringFromDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_MONTH) + "/" + (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.YEAR);
    }

    private void sendMail() {

    }

    private void produceRapportCircuitSheet(XSSFSheet raportCircuitDataSheet, List<RepVenteRatee> ventesRatee, List<ProduitRatee> produitsRates, List<CircuitRateeProduit> circuitProduits, List<VenteRateeCdDto> rateeCds) {
        int produitsSize = produitsRates.size();
        int rowId = 0;
        int colId = 0;
        Cell cell;
        Row row = raportCircuitDataSheet.createRow(rowId);
        cell = row.createCell(colId);
        cell.setCellValue("RAPPORT VENTES RATEES");
        cell.setCellStyle(gold);
        raportCircuitDataSheet.addMergedRegion(new CellRangeAddress(
                rowId, //first row (0-based)
                rowId, //last row  (0-based)
                colId, //first column (0-based)
                colId + 1 + produitsSize + 1 //last column  (0-based)
        ));

        rowId++;
        colId = 0;

        row = raportCircuitDataSheet.createRow(rowId);
        cell = row.createCell(colId);
        cell.setCellValue("Circuit/CD");
        cell.setCellStyle(grey);
        raportCircuitDataSheet.addMergedRegion(new CellRangeAddress(
                rowId, //first row (0-based)
                rowId, //last row  (0-based)
                colId, //first column (0-based)
                colId + 1 //last column  (0-based)
        ));

        colId += 2;

        for (ProduitRatee produitRatee : produitsRates) {
            cell = row.createCell(colId++);
            cell.setCellValue(produitRatee.getLibelle().trim());
            cell.setCellStyle(myStyle3);
        }
        cell = row.createCell(colId++);
        cell.setCellValue("Total Général");
        cell.setCellStyle(myStyle);

        rowId = 2;
        colId = 0;

        for (VenteRateeCdDto rateeCd : rateeCds) {
            row = raportCircuitDataSheet.createRow(rowId);
            cell = row.createCell(colId);
            cell.setCellValue(rateeCd.getCodeCd() + " - " + rateeCd.getNomCd().trim());
            cell.setCellStyle(cdHeader);
            raportCircuitDataSheet.addMergedRegion(new CellRangeAddress(
                    rowId, //first row (0-based)
                    rowId, //last row  (0-based)
                    colId, //first column (0-based)
                    colId + 1 //last column  (0-based)
            ));

            colId = 2;
            // Les produits
            for (ProduitRatee produitRatee : produitsRates) {
                long ratee = getVenteRateeProduitCd(rateeCd.getCodeCd(), produitRatee.getCodart(), circuitProduits);
                cell = row.createCell(colId++);
                cell.setCellValue(ratee == 0 ? "" : ratee + "");
                cell.setCellStyle(cdHeader);
            }
            //Total CD
            cell = row.createCell(colId++);
            cell.setCellValue(rateeCd.getQteRatee());
            cell.setCellStyle(cdHeader);

            // La liste des circuits du CD
            ++rowId;
            colId = 0;
            Set<String> circuits = getListCircuitCd(rateeCd.getCodeCd(), circuitProduits);
            for (String circuit : circuits) {
                row = raportCircuitDataSheet.createRow(rowId);
                cell = row.createCell(colId++);
                cell.setCellValue(getCircuitCode(circuit, circuitProduits));
                cell = row.createCell(colId++);
                cell.setCellValue(circuit);
                for (ProduitRatee produitRatee : produitsRates) {
                    long ratee = getVenteRateeProduitCircuit(circuit, produitRatee.getCodart(), circuitProduits);
                    cell = row.createCell(colId++);
                    cell.setCellValue(ratee == 0 ? "" : ratee + "");
                }
                cell = row.createCell(colId++);
                cell.setCellValue(getAllVenteRateeCircuit(circuit, circuitProduits));
                rowId++;
                colId = 0;
            }

        }

        // TOTAL GENERAL
        row = raportCircuitDataSheet.createRow(rowId);
        cell = row.createCell(colId);
        cell.setCellValue("Total Général");
        cell.setCellStyle(footer);
        raportCircuitDataSheet.addMergedRegion(new CellRangeAddress(
                rowId, //first row (0-based)
                rowId, //last row  (0-based)
                colId, //first column (0-based)
                colId + 1 //last column  (0-based)
        ));

        colId = 2;
        long totalRatee = 0;
        for (ProduitRatee produitRatee : produitsRates) {
            cell = row.createCell(colId++);
            cell.setCellValue(produitRatee.getRatee());
            cell.setCellStyle(footer);
            totalRatee += produitRatee.getRatee();
        }
        cell = row.createCell(colId++);
        cell.setCellValue(totalRatee);
        cell.setCellStyle(footer);
        
        ++rowId;
        colId = 0;
        // TOTAL GENERAL
        row = raportCircuitDataSheet.createRow(rowId);
        cell = row.createCell(colId);
        cell.setCellValue("Total Général Hecto");
        cell.setCellStyle(footer);
        raportCircuitDataSheet.addMergedRegion(new CellRangeAddress(
                rowId, //first row (0-based)
                rowId, //last row  (0-based)
                colId, //first column (0-based)
                colId + 1 //last column  (0-based)
        ));

        colId = 2;
        double totalRateeHecto = 0;
        for (ProduitRatee produitRatee : produitsRates) {
            cell = row.createCell(colId++);
            cell.setCellValue(produitRatee.getHectoRatee());
            cell.setCellStyle(footer);
            totalRateeHecto += produitRatee.getHectoRatee();
        }
        cell = row.createCell(colId++);
        cell.setCellValue((int)totalRateeHecto);
        cell.setCellStyle(footer);
    }

    /**
     * Retourne les ventes ratées d'un circuit donéne pour un produit donné
     *
     * @param circuit
     * @param codart
     * @param circuitRateeProduits
     * @return
     */
    private long getVenteRateeProduitCircuit(String circuit, String codart, List<CircuitRateeProduit> circuitRateeProduits) {
        for (CircuitRateeProduit circuitRateeProduit : circuitRateeProduits) {
            if (circuit.equalsIgnoreCase(circuitRateeProduit.getCodeVendeur().trim()) && codart.equalsIgnoreCase(circuitRateeProduit.getCodart().trim())) {
                return circuitRateeProduit.getRatee();
            }
        }
        return 0;
    }

    /**
     * Retourne les ventes ratées d'un CD pour un produit
     *
     * @param codeCd
     * @param codart
     * @param circuitRateeProduits
     * @return
     */
    private long getVenteRateeProduitCd(String codeCd, String codart, List<CircuitRateeProduit> circuitRateeProduits) {
        long result = 0;
        result = circuitRateeProduits.stream().filter((circuitRateeProduit) -> (codeCd.equalsIgnoreCase(circuitRateeProduit.getCodeCd().trim()) && codart.equalsIgnoreCase(circuitRateeProduit.getCodart().trim()))).map((circuitRateeProduit) -> circuitRateeProduit.getRatee()).reduce(result, (accumulator, _item) -> accumulator + _item);
        return result;
    }

    /**
     * Retourne le total des ventes ratées d'un circuit
     *
     * @param circuit
     * @param circuitRateeProduits
     * @return
     */
    private long getAllVenteRateeCircuit(String circuit, List<CircuitRateeProduit> circuitRateeProduits) {
        long result = 0;
        result = circuitRateeProduits.stream().filter((circuitRateeProduit) -> (circuit.trim().equalsIgnoreCase(circuitRateeProduit.getCodeVendeur().trim()))).map((circuitRateeProduit) -> circuitRateeProduit.getRatee()).reduce(result, (accumulator, _item) -> accumulator + _item);
        return result;
    }

    /**
     * Retourne la liste des circuit d'un cd
     *
     * @param codeCd
     * @param circuitRateeProduits
     * @return
     */
    private Set<String> getListCircuitCd(String codeCd, List<CircuitRateeProduit> circuitRateeProduits) {
        Set<String> result = new HashSet<>();
        circuitRateeProduits.stream().filter((circuitRateeProduit) -> (codeCd.equalsIgnoreCase(circuitRateeProduit.getCodeCd().trim()))).forEachOrdered((circuitRateeProduit) -> {
            result.add(circuitRateeProduit.getCodeVendeur());
        });
        return result;
    }

    private Set<String> getListCircuitCdFromErreur(String codeCd, List<ErreurSaisieCircuitDto> erreursCircuitd) {
        Set<String> result = new HashSet<>();
        erreursCircuitd.stream().filter((erreurCircuit) -> (codeCd.equalsIgnoreCase(erreurCircuit.getCodeCd().trim()))).forEachOrdered((erreurCircuit) -> {
            result.add(erreurCircuit.getCircuit());
        });
        return result;
    }

    private String getCircuitCode(String circuit, List<CircuitRateeProduit> circuitRateeProduits) {
        for (CircuitRateeProduit circuitRateeProduit : circuitRateeProduits) {
            if (circuit.equalsIgnoreCase(circuitRateeProduit.getCodeVendeur().trim())) {
                return circuitRateeProduit.getCircuit();
            }
        }
        return "";
    }

    private String getCircuitCodeFromErreur(String circuit, List<ErreurSaisieCircuitDto> erreursCircuitd) {
        for (ErreurSaisieCircuitDto erreurCircuit : erreursCircuitd) {
            if (circuit.equalsIgnoreCase(erreurCircuit.getCircuit().trim())) {
                return erreurCircuit.getCodeCircuit();
            }
        }
        return "";
    }

    private void produceErreurDataSheet(XSSFSheet erreursDataSheet, List<RepVenteRatee> erreursSaisies) {
        int rowId = 0;
        int colId = 0;
        Row row = erreursDataSheet.createRow(rowId);
        Cell cell;
        cell = row.createCell(colId++);
        cell.setCellValue("CODE_FACT");
        cell = row.createCell(colId++);
        cell.setCellValue("CD");
        cell = row.createCell(colId++);
        cell.setCellValue("CODE_ROUTE");
        cell = row.createCell(colId++);
        cell.setCellValue("BL");
        cell = row.createCell(colId++);
        cell.setCellValue("DATE");
        cell = row.createCell(colId++);
        cell.setCellValue("CODE_CLT");
        cell = row.createCell(colId++);
        cell.setCellValue("CODE_ART");
        cell = row.createCell(colId++);
        cell.setCellValue("LIBELLE");
        cell = row.createCell(colId++);
        cell.setCellValue("QTE_SOUHAITEE");
        cell = row.createCell(colId++);
        cell.setCellValue("QTE_STOCK");
        cell = row.createCell(colId++);
        cell.setCellValue("QTE_VENDUE");
        cell = row.createCell(colId++);
        cell.setCellValue("QTE_RATEE");

        rowId++;
        colId = 0;
        for (RepVenteRatee erreur : erreursSaisies) {
            row = erreursDataSheet.createRow(rowId);
            cell = row.createCell(colId);
            cell.setCellValue(erreur.getCodeFact().trim());
            colId++;

            cell = row.createCell(colId);
            cell.setCellValue(erreur.getCodeCd() + " - " + erreur.getNomCd().trim());
            colId++;

            cell = row.createCell(colId);
            cell.setCellValue(erreur.getCodeRoute().trim());
            colId++;

            cell = row.createCell(colId);
            cell.setCellValue(erreur.getBl().trim());
            colId++;

            cell = row.createCell(colId);
            cell.setCellValue(getStringFromDate(erreur.getDate()));
            colId++;

            cell = row.createCell(colId);
            cell.setCellValue(erreur.getCodeClt().trim());
            colId++;

            cell = row.createCell(colId);
            cell.setCellValue(erreur.getCodeArt().trim());
            colId++;

            cell = row.createCell(colId);
            cell.setCellValue(erreur.getLibelle().trim());
            colId++;

            cell = row.createCell(colId);
            cell.setCellValue(erreur.getQteSouhaitee());
            colId++;

            cell = row.createCell(colId);
            cell.setCellValue(erreur.getQteStock());
            colId++;

            cell = row.createCell(colId);
            cell.setCellValue(erreur.getQteAchetee());
            colId++;

            cell = row.createCell(colId);
            cell.setCellValue(erreur.getQteRatee());
            colId++;

            rowId++;
            colId = 0;
        }
    }

    private void produceRapportErreurCircuitSheet(XSSFSheet erreursRapportSheet, List<ErreurSaisieCdDto> erreursCd, List<ErreurSaisieCircuitDto> erreusCircuits) {
        int rowId = 0;
        int colId = 0;
        Cell cell;
        Row row = erreursRapportSheet.createRow(rowId);
        cell = row.createCell(colId);
        cell.setCellValue("RAPPORT ERREURS SAISIE");
        cell.setCellStyle(gold);
        erreursRapportSheet.addMergedRegion(new CellRangeAddress(
                rowId, //first row (0-based)
                rowId, //last row  (0-based)
                colId, //first column (0-based)
                colId + 2 //last column  (0-based)
        ));

        rowId++;
        colId = 0;

        row = erreursRapportSheet.createRow(rowId);
        cell = row.createCell(colId);
        cell.setCellValue("Circuit/CD");
        cell.setCellStyle(grey);
        erreursRapportSheet.addMergedRegion(new CellRangeAddress(
                rowId, //first row (0-based)
                rowId, //last row  (0-based)
                colId, //first column (0-based)
                colId + 1 //last column  (0-based)
        ));
        colId = 2;
        cell = row.createCell(colId);
        cell.setCellValue("Nombre Erreurs");
        cell.setCellStyle(grey);

        rowId++;
        colId = 0;

        for (ErreurSaisieCdDto erreurCd : erreursCd) {
            row = erreursRapportSheet.createRow(rowId);
            cell = row.createCell(colId);
            cell.setCellValue(erreurCd.getCodeCd() + " - " + erreurCd.getNom().trim());
            cell.setCellStyle(cdHeader);
            erreursRapportSheet.addMergedRegion(new CellRangeAddress(
                    rowId, //first row (0-based)
                    rowId, //last row  (0-based)
                    colId, //first column (0-based)
                    colId + 1 //last column  (0-based)
            ));

            colId = 2;
            cell = row.createCell(colId++);
            cell.setCellValue(erreurCd.getNombreErreur());
            cell.setCellStyle(cdHeader);

            // La liste des circuits du CD
            ++rowId;
            colId = 0;
            Set<String> circuits = getListCircuitCdFromErreur(erreurCd.getCodeCd().trim(), erreusCircuits);
            for (String circuit : circuits) {
                row = erreursRapportSheet.createRow(rowId++);
                cell = row.createCell(colId++);
                cell.setCellValue(getCircuitCodeFromErreur(circuit, erreusCircuits));
                cell = row.createCell(colId++);
                cell.setCellValue(circuit);
                long erreur = getNombreErreurCircuit(circuit, erreusCircuits);
                cell = row.createCell(colId++);
                cell.setCellValue(erreur == 0 ? "" : erreur + "");
                colId = 0;
            }
        }

        row = erreursRapportSheet.createRow(rowId);
        cell = row.createCell(colId);
        cell.setCellValue("Total Général");
        cell.setCellStyle(footer);
        erreursRapportSheet.addMergedRegion(new CellRangeAddress(
                rowId, //first row (0-based)
                rowId, //last row  (0-based)
                colId, //first column (0-based)
                colId + 1 //last column  (0-based)
        ));

        colId = 2;
        long totalRatee = getTotalErreur(erreursCd);
        cell = row.createCell(colId++);
        cell.setCellValue(totalRatee);
        cell.setCellStyle(footer);

    }

    private long getNombreErreurCircuit(String circuit, List<ErreurSaisieCircuitDto> erreursCircuits) {
        for (ErreurSaisieCircuitDto erreur : erreursCircuits) {
            if (erreur.getCircuit().equalsIgnoreCase(circuit)) {
                return erreur.getNombreErreur();
            }
        }
        return 0;
    }

    private long getTotalErreur(List<ErreurSaisieCdDto> erreursCd) {
        long result = 0;

        result = erreursCd.stream().map((erreurSaisieCdDto) -> erreurSaisieCdDto.getNombreErreur()).reduce(result, (accumulator, _item) -> accumulator + _item);
        return result;
    }

    private int getNumberOfDaysInAGivenMonth(int mois, int annee) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(annee, mois, 1);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

}
