package com.bracongo.mailexport.service.impl;

import com.bracongo.mailexport.dao.IHhtFactureDao;
import com.bracongo.mailexport.dao.INativeQueryDao;
import com.bracongo.mailexport.data.dto.CdHeurerResumeStat;
import com.bracongo.mailexport.data.dto.ClientServisCircuit;
import com.bracongo.mailexport.data.dto.HhtFactureCircuitDto;
import com.bracongo.mailexport.data.dto.ProduitRatee;
import com.bracongo.mailexport.service.IRapportFactureService;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
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
public class RapportFactureServiceImpl implements IRapportFactureService {

    @Autowired
    private INativeQueryDao nativeQueryDao;

    @Autowired
    private IHhtFactureDao factureDao;

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
    public void produceHeureVenteRapportAndMail() {
        try {
            Calendar cal_ = Calendar.getInstance();
            if (cal_.get(Calendar.DAY_OF_MONTH) == 1) {
                cal_.add(Calendar.MONTH, -1);
            }
            int annee = cal_.get(Calendar.YEAR);
            int mois = (cal_.get(Calendar.MONTH) + 1);
            Calendar cal = Calendar.getInstance();
            Calendar cal2 = Calendar.getInstance();

            cal.add(Calendar.MONTH, -1);
            cal2.add(Calendar.DATE, -1);

            List<ClientServisCircuit> clientServisCircuits = nativeQueryDao.getClientServiCircuitStat(cal2.get(Calendar.YEAR), cal2.get(Calendar.MONTH) + 1, cal2.get(Calendar.DATE), cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1);
            //List<ClientServisCircuit> clientServisCircuitsMois = nativeQueryDao.getClientServiCircuitStatMonth(cal2.get(Calendar.YEAR), cal2.get(Calendar.MONTH) + 1, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1);
            List<HhtFactureCircuitDto> factureCircuitDtos = factureDao.getAllFacturesByCircuitForDay(cal2.get(Calendar.YEAR), cal2.get(Calendar.MONTH) + 1, cal2.get(Calendar.DATE));
            //List<HhtFactureCircuitDto> factureCircuitMoisDtos = factureDao.getAllFacturesByCircuitForMonth(annee, mois);

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
            XSSFSheet raportResumeCdSheet = workbook.createSheet("Resume CD");
            XSSFSheet raportCircuitSheet = workbook.createSheet("Rapport Heures Ventes");
            XSSFSheet dataSheet = workbook.createSheet("Données J-1 Brutes");
           // XSSFSheet monthDataSheet = workbook.createSheet("Données Cumul Mois Brutes");

            List<ResumeData> resumes = produceRapportSheet(raportCircuitSheet, clientServisCircuits, factureCircuitDtos);
            produceMonthDataSheet(dataSheet, factureCircuitDtos);
            produceResumeSheet(raportResumeCdSheet, resumes);
           // produceMonthDataSheet(monthDataSheet, factureCircuitMoisDtos);
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

            helper.setTo(new String[]{"anaclet.lawaba@castel-afrique.com","guelor.nkulu@castel-afrique.com","herve.kiwanga@castel-afrique.com", "jeremie.lutunu@castel-afrique.com", "bangoma.dakwa@castel-afrique.com", "pierre.sanakiaku@castel-afrique.com", "jules.bolebe@castel-afrique.com", "Mathias.BEKANGBA@castel-afrique.com", "rosine.modiri@castel-afrique.com", "jason.djemo@castel-afrique.com", "gregoire.sombolayi@castel-afrique.com", "nadege.vundu@castel-afrique.com"});
            helper.setSubject("Rapport heures de vente premier client");
            helper.setCc("valmy.roikenfack@castel-afrique.com");
            helper.setFrom("BRACONGO.Reportbusiness@castel-afrique.com");
            MimeBodyPart messageBodyPart1 = new MimeBodyPart();
            messageBodyPart1.setDataHandler(new DataHandler(fds));

//messageBodyPart1.setFileName(fds.getName());
            messageBodyPart1.setFileName("Rapport_Heures_Ventes_SRD.xlsx");
            Multipart mpart = new MimeMultipart();
            mpart.addBodyPart(messageBodyPart1);
            message.setContent(mpart);
            sender.send(message);
        } catch (IOException | MessagingException ex) {
            Logger.getLogger(RapportFactureServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private List<ResumeData> produceRapportSheet(XSSFSheet rapportCircuitSheet, List<ClientServisCircuit> clientServisCircuits, List<HhtFactureCircuitDto> factureCircuitDtos) {
        List<ResumeData> result = new ArrayList<>();
        int rowId = 0;
        int colId = 0;
        Cell cell;
        Row row = rapportCircuitSheet.createRow(rowId);
        cell = row.createCell(colId);
        cell.setCellValue("RAPPORT HEURE VENTES");
        cell.setCellStyle(gold);
        rapportCircuitSheet.addMergedRegion(new CellRangeAddress(
                rowId, //first row (0-based)
                rowId, //last row  (0-based)
                colId, //first column (0-based)
                6 //last column  (0-based)
        ));

        rowId++;
        colId = 0;

        row = rapportCircuitSheet.createRow(rowId);
        cell = row.createCell(colId);
        cell.setCellValue("CIRCUIT/CD");
        cell.setCellStyle(grey);
        rapportCircuitSheet.addMergedRegion(new CellRangeAddress(
                rowId, //first row (0-based)
                rowId, //last row  (0-based)
                colId, //first column (0-based)
                colId + 1 //last column  (0-based)
        ));

        colId += 2;
        cell = row.createCell(colId++);
        cell.setCellValue("Heure Premier client");
        cell.setCellStyle(myStyle3);
        cell = row.createCell(colId++);
        cell.setCellValue("Heure Dernier client");
        cell.setCellStyle(myStyle3);
        cell = row.createCell(colId++);
        cell.setCellValue("Total Client Servis");
        cell.setCellStyle(myStyle3);
        cell = row.createCell(colId++);
        cell.setCellValue("Total Clients Actifs");
        cell.setCellStyle(myStyle3);

        cell = row.createCell(colId++);
        cell.setCellValue("Pourcentage Clients Servis");
        cell.setCellStyle(myStyle3);

        rowId = 2;
        colId = 0;

        HashMap<String, String> listCd = getListCd(clientServisCircuits);
        System.out.println("J'aI " + listCd.size() + " cd");
        ResumeData temp;
        for (Map.Entry<String, String> entry : listCd.entrySet()) {
            temp = new ResumeData();
            String key = entry.getKey();
            String value = entry.getValue();
            int totalClientServis = getTotalClientServisCD(key, clientServisCircuits);
            int totalClientsCD = getTotalClientCD(key, clientServisCircuits);
            temp.setTotalClient(totalClientsCD);
            temp.setTotalClientServis(totalClientServis);
            List<HhtFactureCircuitDto> factureCds = getAllByCd(key, factureCircuitDtos);
            row = rapportCircuitSheet.createRow(rowId);
            cell = row.createCell(colId);
            cell.setCellValue(key + " - " + value);
            cell.setCellStyle(cdHeader);
            rapportCircuitSheet.addMergedRegion(new CellRangeAddress(
                    rowId, //first row (0-based)
                    rowId, //last row  (0-based)
                    colId, //first column (0-based)
                    colId + 1 //last column  (0-based)
            ));
            temp.setNomCd(key + " - " + value);

            colId = 2;
            cell = row.createCell(colId++);
            String heurePremierClient = buildHourMinuteStringFromDate(factureCds.get(0).getDateFact());
            cell.setCellValue(heurePremierClient);
            temp.setHeurePremierClient(heurePremierClient);
            cell.setCellStyle(cdHeader);
            cell = row.createCell(colId++);
            String heureDernierClient = buildHourMinuteStringFromDate(factureCds.get(factureCds.size() - 1).getDateFact());
            cell.setCellValue(heureDernierClient);
            temp.setHeureDernierClient(heureDernierClient);
            cell.setCellStyle(cdHeader);
            cell = row.createCell(colId++);
            cell.setCellValue(totalClientServis);
            cell.setCellStyle(cdHeader);
            cell = row.createCell(colId++);
            cell.setCellValue(totalClientsCD);
            cell.setCellStyle(cdHeader);
            cell = row.createCell(colId++);
            float pourcentage = (totalClientsCD == 0 ? 0 : ((totalClientServis * 1.0f) / totalClientsCD) * 100);
            cell.setCellValue(Math.round(pourcentage));
            cell.setCellStyle(cdHeader);
            temp.setPorcentage(Math.round(pourcentage));
            result.add(temp);

            Set<String> circuits = getListCircuitByCdFromFacture(key, factureCircuitDtos);
            ++rowId;
            colId = 0;
            for (String circuit : circuits) {
                row = rapportCircuitSheet.createRow(rowId);
                cell = row.createCell(colId++);
                cell.setCellValue(getCircuitName(circuit, factureCds));
                cell = row.createCell(colId++);
                cell.setCellValue(circuit);
                List<HhtFactureCircuitDto> facturesCircuits = getFirstOfCircuit(circuit, factureCds);
                cell = row.createCell(colId++);
                cell.setCellValue(buildHourMinuteStringFromDate(facturesCircuits.get(0).getDateFact()));
                cell = row.createCell(colId++);
                cell.setCellValue(buildHourMinuteStringFromDate(facturesCircuits.get(facturesCircuits.size() - 1).getDateFact()));
                int clientServisCircuit = getClientServisCircuit(circuit, clientServisCircuits);
                int effectifCircuit = getClientCircuit(circuit, clientServisCircuits);
                cell = row.createCell(colId++);
                cell.setCellValue(clientServisCircuit);
                cell = row.createCell(colId++);
                cell.setCellValue(effectifCircuit);
                cell = row.createCell(colId++);
                float pourcentage2 = (effectifCircuit == 0 ? 0 : ((clientServisCircuit * 1.0f) / effectifCircuit) * 100);
                cell.setCellValue(Math.round(pourcentage2));
                ++rowId;
                colId = 0;

            }
        }
        return result;
    }

    private List<HhtFactureCircuitDto> getAllByCd(String cd, List<HhtFactureCircuitDto> factures) {
        List<HhtFactureCircuitDto> result = new ArrayList<>();
        factures.stream().filter((facture) -> (facture.getCodeCd().equalsIgnoreCase(cd))).forEachOrdered((facture) -> {
            result.add(facture);
        });
        return result;
    }

    private Set<String> getListCircuitByCd(String codeCd, List<ClientServisCircuit> clientServisCircuits) {
        Set<String> result = new HashSet<>();
        clientServisCircuits.stream().filter((clientServis) -> (clientServis.getCodeCd().equalsIgnoreCase(codeCd))).forEachOrdered((clientServis) -> {
            result.add(clientServis.getCircuit());
        });
        return result;
    }

    private Set<String> getListCircuitByCdFromFacture(String codeCd, List<HhtFactureCircuitDto> factures) {
        Set<String> result = new HashSet<>();
        factures.stream().filter((facture) -> (facture.getCodeCd().equalsIgnoreCase(codeCd))).forEachOrdered((facture) -> {
            result.add(facture.getCodeCircuit());
        });
        return result;
    }

    private int getTotalClientServisCD(String codeCd, List<ClientServisCircuit> clientServisCircuits) {
        int result = 0;
        result = clientServisCircuits.stream().filter((clientServisCircuit) -> (clientServisCircuit.getCodeCd().equalsIgnoreCase(codeCd))).map((clientServisCircuit) -> clientServisCircuit.getClientAcheteurs()).reduce(result, Integer::sum);
        return result;
    }

    private int getTotalClientCD(String codeCd, List<ClientServisCircuit> clientServisCircuits) {
        int result = 0;
        result = clientServisCircuits.stream().filter((clientServisCircuit) -> (clientServisCircuit.getCodeCd().equalsIgnoreCase(codeCd))).map((clientServisCircuit) -> clientServisCircuit.getEffectifCircuit()).reduce(result, Integer::sum);
        return result;
    }

    private int getClientServisCircuit(String circuit, List<ClientServisCircuit> clientServisCircuits) {
        for (ClientServisCircuit clientServisCircuit : clientServisCircuits) {
            if (clientServisCircuit.getCircuit().equalsIgnoreCase(circuit)) {
                return clientServisCircuit.getClientAcheteurs();
            }
        }
        return 0;
    }

    private int getClientCircuit(String circuit, List<ClientServisCircuit> clientServisCircuits) {
        for (ClientServisCircuit clientServisCircuit : clientServisCircuits) {
            if (clientServisCircuit.getCircuit().equalsIgnoreCase(circuit)) {
                return clientServisCircuit.getEffectifCircuit();
            }
        }
        return 0;
    }

    private HashMap<String, String> getListCd(List<ClientServisCircuit> clientServisCircuits) {
        HashMap<String, String> result = new HashMap<>();
        clientServisCircuits.forEach((clientServisCircuit) -> {
            result.put(clientServisCircuit.getCodeCd(), clientServisCircuit.getNomCd());
        });
        return result;
    }

    private List<HhtFactureCircuitDto> getFirstOfCircuit(String circuit, List<HhtFactureCircuitDto> factures) {
        List<HhtFactureCircuitDto> result = new ArrayList<>();
        for (HhtFactureCircuitDto facture : factures) {
            if (facture.getCodeCircuit().equalsIgnoreCase(circuit)) {
                result.add(facture);
            }
        }
        return result;
    }

    private String buildHourMinuteStringFromDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minutes = cal.get(Calendar.MINUTE);
        return String.format("%02d", hour) + ":" + String.format("%02d", minutes);
    }

    private String getAverageHourString(List<HhtFactureCircuitDto> factures) {
        long seconds = 0;
        for (HhtFactureCircuitDto facture : factures) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(facture.getDateFact());
            seconds += cal.get(Calendar.HOUR_OF_DAY) * 60 * 60 + cal.get(Calendar.MINUTE) * 60 + cal.get(Calendar.SECOND);
        }
        seconds /= factures.size();
        long hh = seconds / 60 / 60;
        long mm = (seconds / 60) % 60;
        return String.format("%02d", hh) + ":" + String.format("%02d", mm);
    }

    private Date getAverageDateFromListFacture(List<HhtFactureCircuitDto> factures) {
        BigInteger total = BigInteger.ZERO;
        for (HhtFactureCircuitDto facture : factures) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(facture.getDateFact());
            total = total.add(BigInteger.valueOf(facture.getDateFact().getTime()));
        }
        BigInteger averageMillis = total.divide(BigInteger.valueOf(factures.size()));
        Date averageDate = new Date(averageMillis.longValue());
        return averageDate;
    }

    private String getCircuitName(String circuit, List<HhtFactureCircuitDto> factures) {
        String result = "";
        for (HhtFactureCircuitDto facture : factures) {
            //System.out.println(facture);
            if (facture.getCodeCircuit().equalsIgnoreCase(circuit)) {
                return facture.getCodeRoute();
            }
        }
        return result;
    }

    @Override
    public List<CdHeurerResumeStat> getListHeureCd() {
        List<CdHeurerResumeStat> result = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();

        cal.add(Calendar.MONTH, -1);
        cal2.add(Calendar.DATE, -1);

        List<ClientServisCircuit> clientServisCircuits = nativeQueryDao.getClientServiCircuitStat(cal2.get(Calendar.YEAR), cal2.get(Calendar.MONTH) + 1, cal2.get(Calendar.DATE), cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1);
        List<HhtFactureCircuitDto> factureCircuitDtos = factureDao.getAllFacturesByCircuitForDay(cal2.get(Calendar.YEAR), cal2.get(Calendar.MONTH) + 1, cal2.get(Calendar.DATE));
        HashMap<String, String> listCd = getListCd(clientServisCircuits);
        for (Map.Entry<String, String> entry : listCd.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            int totalClientServis = getTotalClientServisCD(key, clientServisCircuits);
            int totalClientsCD = getTotalClientCD(key, clientServisCircuits);
            List<HhtFactureCircuitDto> factureCds = getAllByCd(key, factureCircuitDtos);
            CdHeurerResumeStat temp = new CdHeurerResumeStat();
            temp.setNomCd(key + " - " + value);
            temp.setHeurePremiereFacture(buildHourMinuteStringFromDate(factureCds.get(0).getDateFact()));
            temp.setHeureDerniereFacture(buildHourMinuteStringFromDate(factureCds.get(factureCds.size() - 1).getDateFact()));
            temp.setTotalClientCd(totalClientsCD);
            temp.setTotalClientServis(totalClientServis);
            float pourcentage = (totalClientsCD == 0 ? 0 : ((totalClientServis * 1.0f) / totalClientsCD) * 100);
            temp.setPourcentageServis(Math.round(pourcentage));

            List<HhtFactureCircuitDto> factureCircuit = getFirstClientOfCircuit(key, factureCds);
            temp.setHeureMoyenne(getAverageHourString(factureCircuit));

            result.add(temp);

        }

        return result;
    }

    private List<HhtFactureCircuitDto> getFirstClientOfCircuit(String cd, List<HhtFactureCircuitDto> factureCds) {
        List<HhtFactureCircuitDto> result = new ArrayList<>();
        Set<String> circuits = getListCircuitByCdFromFacture(cd, factureCds);
        for (String circuit : circuits) {
            List<HhtFactureCircuitDto> facturesCircuits = getFirstOfCircuit(circuit, factureCds);
            result.add(facturesCircuits.get(0));

        }
        return result;
    }

    @Override
    public byte[] exportHeureVenteRapport(OutputStream out) {
        try {
            Calendar cal = Calendar.getInstance();
            Calendar cal2 = Calendar.getInstance();

            cal.add(Calendar.MONTH, -1);
            cal2.add(Calendar.DATE, -1);

            List<ClientServisCircuit> clientServisCircuits = nativeQueryDao.getClientServiCircuitStat(cal2.get(Calendar.YEAR), cal2.get(Calendar.MONTH) + 1, cal2.get(Calendar.DATE), cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1);
          //  System.out.println("IL Y A  " + clientServisCircuits.size() + " circuits");
            List<HhtFactureCircuitDto> factureCircuitDtos = factureDao.getAllFacturesByCircuitForDay(cal2.get(Calendar.YEAR), cal2.get(Calendar.MONTH) + 1, cal2.get(Calendar.DATE));
           // System.out.println("IL Y A " + factureCircuitDtos.size() + " factures");

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
            XSSFSheet raportCircuitSheet = workbook.createSheet("Rapport Heures Ventes");
            produceRapportSheet(raportCircuitSheet, clientServisCircuits, factureCircuitDtos);
            File file = File.createTempFile("Rapport", "xlsx");
            file.deleteOnExit();
            Path path = file.toPath();
            FileOutputStream fileOut = new FileOutputStream(file);
            /* workbook.write(fileOut);
            fileOut.close();
            DataSource fds = new FileDataSource("temp.xls");
             */
            workbook.write(out);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            workbook.write(bos); // write excel data to a byte array
            fileOut.close();
            byte[] result = bos.toByteArray();
            return result;

            // DataSource fds = new ByteArrayDataSource(bos.toByteArray(), "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        } catch (IOException ioe) {
            Logger.getLogger(RapportFactureServiceImpl.class.getName()).log(Level.SEVERE, null, ioe);
        }
        return null;
    }

   /* private void produceDataSheet(XSSFSheet dataSheet, List<HhtFactureCircuitDto> factureCircuitDtos) {
        int rowId = 0;
        int colId = 0;
        Cell cell;
        Row row = dataSheet.createRow(rowId);
        cell = row.createCell(colId++);
        cell.setCellValue("CODE_ROUTE");
        cell.setCellStyle(gold);

        cell = row.createCell(colId++);
        cell.setCellValue("CODE_CD");
        cell.setCellStyle(gold);
        cell = row.createCell(colId++);
        cell.setCellValue("NOM_CD");
        cell.setCellStyle(gold);
        cell = row.createCell(colId++);
        cell.setCellValue("CODE_CIRCUIT");
        cell.setCellStyle(gold);
        cell = row.createCell(colId++);
        cell.setCellValue("CODE_FACTURE");
        cell.setCellStyle(gold);
        cell = row.createCell(colId++);
        cell.setCellValue("CODE_CLIENT");
        cell.setCellStyle(gold);
        cell = row.createCell(colId++);
        cell.setCellValue("DATE_FACTURE");
        cell.setCellStyle(gold);
        cell = row.createCell(colId++);
        cell.setCellValue("DATE_DEBUT");
        cell.setCellStyle(gold);
        cell = row.createCell(colId++);
        cell.setCellValue("DATE_FIN");
        cell.setCellStyle(gold);
        cell = row.createCell(colId++);
        cell.setCellValue("BL");
        cell.setCellStyle(gold);
        cell = row.createCell(colId++);
        cell.setCellValue("CODE_VENDEUR");
        cell.setCellStyle(gold);

        rowId++;
        colId = 0;

        for (HhtFactureCircuitDto factureCircuitDto : factureCircuitDtos) {
            row = dataSheet.createRow(rowId);
            cell = row.createCell(colId++);
            cell.setCellValue(factureCircuitDto.getCodeRoute().trim());
            cell = row.createCell(colId++);
            cell.setCellValue(factureCircuitDto.getCodeCd().trim());
            cell = row.createCell(colId++);
            cell.setCellValue(factureCircuitDto.getNomCd().trim());
            cell = row.createCell(colId++);
            cell.setCellValue(factureCircuitDto.getCodeCircuit().trim());
            cell = row.createCell(colId++);
            cell.setCellValue(factureCircuitDto.getCodeFact().trim());
            cell = row.createCell(colId++);
            cell.setCellValue(factureCircuitDto.getCodeClient().trim());
            cell = row.createCell(colId++);
            cell.setCellValue(getDateStringFromDate(factureCircuitDto.getDateFact()));
            cell = row.createCell(colId++);
            cell.setCellValue(getDateStringFromDate(factureCircuitDto.getDateDebutFact()));
            cell = row.createCell(colId++);
            cell.setCellValue(getDateStringFromDate(factureCircuitDto.getDateFinFact()));
            cell = row.createCell(colId++);
            cell.setCellValue(factureCircuitDto.getBL().trim());
            cell = row.createCell(colId++);
            cell.setCellValue(factureCircuitDto.getCodeVendeur().trim());
            rowId++;
            colId = 0;
                    
        }
    }*/
    
    private String getDateStringFromDate(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_MONTH) + "/" + (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.YEAR) + " " + String.format("%02d", cal.get(Calendar.HOUR_OF_DAY)) + ":" + String.format("%02d", cal.get(Calendar.MINUTE));
    }

    private void produceMonthDataSheet(XSSFSheet monthDataSheet, List<HhtFactureCircuitDto> factureCircuitMoisDtos) {
        int rowId = 0;
        int colId = 0;
        Cell cell;
        Row row = monthDataSheet.createRow(rowId);
        
        cell = row.createCell(colId++);
        cell.setCellValue("JOUR");
        cell.setCellStyle(gold);
        
        cell = row.createCell(colId++);
        cell.setCellValue("MOIS");
        cell.setCellStyle(gold);
        
        cell = row.createCell(colId++);
        cell.setCellValue("CODE_ROUTE");
        cell.setCellStyle(gold);

        cell = row.createCell(colId++);
        cell.setCellValue("CODE_CD");
        cell.setCellStyle(gold);
        cell = row.createCell(colId++);
        cell.setCellValue("NOM_CD");
        cell.setCellStyle(gold);
        cell = row.createCell(colId++);
        cell.setCellValue("CODE_CIRCUIT");
        cell.setCellStyle(gold);
        cell = row.createCell(colId++);
        cell.setCellValue("CODE_FACTURE");
        cell.setCellStyle(gold);
        cell = row.createCell(colId++);
        cell.setCellValue("CODE_CLIENT");
        cell.setCellStyle(gold);
        cell = row.createCell(colId++);
        cell.setCellValue("DATE_FACTURE");
        cell.setCellStyle(gold);
        cell = row.createCell(colId++);
        cell.setCellValue("DATE_DEBUT");
        cell.setCellStyle(gold);
        cell = row.createCell(colId++);
        cell.setCellValue("DATE_FIN");
        cell.setCellStyle(gold);
        cell = row.createCell(colId++);
        cell.setCellValue("BL");
        cell.setCellStyle(gold);
        cell = row.createCell(colId++);
        cell.setCellValue("CODE_VENDEUR");
        cell.setCellStyle(gold);

        rowId++;
        colId = 0;
        Calendar cal = Calendar.getInstance();
        for (HhtFactureCircuitDto factureCircuitDto : factureCircuitMoisDtos) {
            row = monthDataSheet.createRow(rowId);
            cal.setTime(factureCircuitDto.getDateFact());
            cell = row.createCell(colId++);
            cell.setCellValue(cal.get(Calendar.DAY_OF_MONTH));
            cell = row.createCell(colId++);
            cell.setCellValue(cal.get(Calendar.MONTH) + 1);
            cell = row.createCell(colId++);
            cell.setCellValue(factureCircuitDto.getCodeRoute().trim());
            cell = row.createCell(colId++);
            cell.setCellValue(factureCircuitDto.getCodeCd().trim());
            cell = row.createCell(colId++);
            cell.setCellValue(factureCircuitDto.getNomCd().trim());
            cell = row.createCell(colId++);
            cell.setCellValue(factureCircuitDto.getCodeCircuit().trim());
            cell = row.createCell(colId++);
            cell.setCellValue(factureCircuitDto.getCodeFact().trim());
            cell = row.createCell(colId++);
            cell.setCellValue(factureCircuitDto.getCodeClient().trim());
            cell = row.createCell(colId++);
            cell.setCellValue(getDateStringFromDate(factureCircuitDto.getDateFact()));
            cell = row.createCell(colId++);
            cell.setCellValue(getDateStringFromDate(factureCircuitDto.getDateDebutFact()));
            cell = row.createCell(colId++);
            cell.setCellValue(getDateStringFromDate(factureCircuitDto.getDateFinFact()));
            cell = row.createCell(colId++);
            cell.setCellValue(factureCircuitDto.getBL().trim());
            cell = row.createCell(colId++);
            cell.setCellValue(factureCircuitDto.getCodeVendeur().trim());
            rowId++;
            colId = 0;
                    
        }
    }

    private void produceResumeSheet(XSSFSheet raportResumeCdSheet, List<ResumeData> resumes) {
        int rowId = 0;
        int colId = 0;
        Cell cell;
        Row row = raportResumeCdSheet.createRow(rowId);
        cell = row.createCell(colId);
        cell.setCellValue("RAPPORT HEURE VENTES - RESUME CD");
        cell.setCellStyle(gold);
        raportResumeCdSheet.addMergedRegion(new CellRangeAddress(
                rowId, //first row (0-based)
                rowId, //last row  (0-based)
                colId, //first column (0-based)
                5 //last column  (0-based)
        ));

        rowId++;
        colId = 0;

        row = raportResumeCdSheet.createRow(rowId);
        cell = row.createCell(colId);
        cell.setCellValue("CD");
        cell.setCellStyle(grey);

        colId += 1;
        cell = row.createCell(colId++);
        cell.setCellValue("Heure Premier client");
        cell.setCellStyle(myStyle3);
        cell = row.createCell(colId++);
        cell.setCellValue("Heure Dernier client");
        cell.setCellStyle(myStyle3);
        cell = row.createCell(colId++);
        cell.setCellValue("Total Client Servis");
        cell.setCellStyle(myStyle3);
        cell = row.createCell(colId++);
        cell.setCellValue("Total Clients Actifs");
        cell.setCellStyle(myStyle3);

        cell = row.createCell(colId++);
        cell.setCellValue("Pourcentage Clients Servis (%)");
        cell.setCellStyle(myStyle3);

        rowId = 2;
        colId = 0;
        for (ResumeData resume : resumes) {
            row = raportResumeCdSheet.createRow(rowId ++);
            cell = row.createCell(colId++);
            cell.setCellValue(resume.getNomCd().trim());
            cell.setCellStyle(cdHeader);
            cell = row.createCell(colId++);
            cell.setCellValue(resume.getHeurePremierClient());
            cell.setCellStyle(cdHeader);
            cell = row.createCell(colId++);
            cell.setCellValue(resume.getHeureDernierClient());
            cell.setCellStyle(cdHeader);
            cell = row.createCell(colId++);
            cell.setCellValue(resume.getTotalClientServis());
            cell.setCellStyle(cdHeader);
            cell = row.createCell(colId++);
            cell.setCellValue(resume.getTotalClient());
            cell.setCellStyle(cdHeader);
            cell = row.createCell(colId++);
            cell.setCellValue(resume.getPorcentage());
            cell.setCellStyle(cdHeader);
            colId = 0;
        }
    }
    
    class ResumeData{
        private String nomCd;
        
        private String heurePremierClient;
        
        private String heureDernierClient;
        
        private int totalClientServis;
        
        private int totalClient;
        
        private double porcentage;

        public ResumeData(String nomCd, String heurePremierClient, String heureDernierClient, int totalClientServis, int totalClient, double porcentage) {
            this.nomCd = nomCd;
            this.heurePremierClient = heurePremierClient;
            this.heureDernierClient = heureDernierClient;
            this.totalClientServis = totalClientServis;
            this.totalClient = totalClient;
            this.porcentage = porcentage;
        }

        public ResumeData() {
        }

        public String getNomCd() {
            return nomCd;
        }

        public void setNomCd(String nomCd) {
            this.nomCd = nomCd;
        }

        public String getHeurePremierClient() {
            return heurePremierClient;
        }

        public void setHeurePremierClient(String heurePremierClient) {
            this.heurePremierClient = heurePremierClient;
        }

        public String getHeureDernierClient() {
            return heureDernierClient;
        }

        public void setHeureDernierClient(String heureDernierClient) {
            this.heureDernierClient = heureDernierClient;
        }

        public int getTotalClientServis() {
            return totalClientServis;
        }

        public void setTotalClientServis(int totalClientServis) {
            this.totalClientServis = totalClientServis;
        }

        public int getTotalClient() {
            return totalClient;
        }

        public void setTotalClient(int totalClient) {
            this.totalClient = totalClient;
        }

        public double getPorcentage() {
            return porcentage;
        }

        public void setPorcentage(double porcentage) {
            this.porcentage = porcentage;
        }
        
        

        @Override
        public String toString() {
            return "ResumeData{" + "nomCd=" + nomCd + ", heurePremierClient=" + heurePremierClient + ", heureDernierClient=" + heureDernierClient + ", totalClientServis=" + totalClientServis + ", totalClient=" + totalClient + ", porcentage=" + porcentage + '}';
        }
        
        
    }

}
