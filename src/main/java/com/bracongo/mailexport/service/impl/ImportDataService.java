package com.bracongo.mailexport.service.impl;

import com.bracongo.mailexport.dao.IRepArticleCorrInfoBiDao;
import com.bracongo.mailexport.dao.IRepArticleInfoBiDao;
import com.bracongo.mailexport.dao.IRepObjectifProduitInfoBiDao;
import com.bracongo.mailexport.dao.IRepTauxChangeDao;
import com.bracongo.mailexport.dao.IRepTaxeArticleDao;
import com.bracongo.mailexport.data.RepArticleCorrespondanceInfoBi;
import com.bracongo.mailexport.data.RepArticleCorrespondanceInfoBiPK;
import com.bracongo.mailexport.data.RepArticleInfoBi;
import com.bracongo.mailexport.data.RepObjectifProduitInfoBi;
import com.bracongo.mailexport.data.RepObjectifProduitInfoBiPK;
import com.bracongo.mailexport.data.RepTauxChange;
import com.bracongo.mailexport.data.RepTaxeArticle;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author vr.kenfack
 */
@Service
@Transactional
public class ImportDataService {

    @Autowired
    private IRepArticleInfoBiDao articleInfoBiDao;

    @Autowired
    private IRepObjectifProduitInfoBiDao repObjectifProduitInfoBiDao;

    @Autowired
    private IRepArticleCorrInfoBiDao articleCorrInfoBiDao;

    @Autowired
    private IRepTauxChangeDao tauxChangeDao;
    
    @Autowired
    private IRepTaxeArticleDao repTaxeArticleDao;

    @Value("${app.upload.file:${user.home}}")
    public String EXCEL_FILE_PATH;

    public void importArticleInfoBi() {
        System.out.println("importing...");
        try {
            ClassPathResource res = new ClassPathResource("C:\\Users\\vr.kenfack\\Documents\\NetBeansProjects\\MailExport\\src\\main\\resources\\ProduitsInfoBi.xlsx");
            File file = new File(res.getPath());
            //File file = new File(getClass().getResource("C:\\Users\\vr.kenfack\\Documents\\NetBeansProjects\\MailExport\\src\\main\\resources\\ProduitsInfoBi.xlsx").getFile());
            Workbook workbook = WorkbookFactory.create(file);
            final Sheet sheet = workbook.getSheetAt(0);
            int index = 1;
            Row row = sheet.getRow(index++);
            while (row != null) {
                System.out.println("index = " + index);
                RepArticleInfoBi article = new RepArticleInfoBi();
                row.getCell(0).setCellType(CellType.STRING);
                String codar = row.getCell(0).getStringCellValue().trim();
                row.getCell(1).setCellType(CellType.STRING);
                String desar = row.getCell(1).getStringCellValue().trim();
                row.getCell(2).setCellType(CellType.STRING);
                String famille = row.getCell(2).getStringCellValue().trim();
                row.getCell(3).setCellType(CellType.STRING);
                String codarInfoBi = row.getCell(3).getStringCellValue().trim();
                row.getCell(4).setCellType(CellType.STRING);
                String taille = row.getCell(4).getStringCellValue().trim();
                row.getCell(5).setCellType(CellType.STRING);
                String emballage = row.getCell(5).getStringCellValue().trim();
                article.setCodar(codar);
                article.setCodeEmballage(Integer.parseInt(emballage));
                article.setCodeInfoBi(codarInfoBi);
                article.setDesignation(desar);
                article.setFamille(famille);
                article.setTaille(Integer.parseInt(taille));
                System.out.println("article = " + article.toString());
                articleInfoBiDao.save(article);
                row = sheet.getRow(index++);
            }
        } catch (IOException | EncryptedDocumentException ex) {
            Logger.getLogger(ImportDataService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void importArticleCorrInfoBi() {
        System.out.println("importing...");
        try {
            ClassPathResource res = new ClassPathResource("C:\\Users\\vr.kenfack\\Documents\\NetBeansProjects\\MailExport\\src\\main\\resources\\ProduitsInfoBi.xlsx");
            File file = new File(res.getPath());
            //File file = new File(getClass().getResource("C:\\Users\\vr.kenfack\\Documents\\NetBeansProjects\\MailExport\\src\\main\\resources\\ProduitsInfoBi.xlsx").getFile());
            Workbook workbook = WorkbookFactory.create(file);
            final Sheet sheet = workbook.getSheetAt(1);
            int index = 1;
            Row row = sheet.getRow(index++);
            while (row != null) {
                System.out.println("index = " + index);
                RepArticleCorrespondanceInfoBi article = new RepArticleCorrespondanceInfoBi();
                RepArticleCorrespondanceInfoBiPK temp = new RepArticleCorrespondanceInfoBiPK();

                row.getCell(0).setCellType(CellType.STRING);
                String codeInfo = row.getCell(0).getStringCellValue().trim();
                row.getCell(1).setCellType(CellType.STRING);
                String codar = row.getCell(1).getStringCellValue().trim();
                temp.setCodar(codar.trim());
                temp.setCodeInfoBi(codeInfo.trim());
                article.setRepArticleCorrespondanceInfoBiPK(temp);
                articleCorrInfoBiDao.save(article);
                row = sheet.getRow(index++);
            }
        } catch (IOException | EncryptedDocumentException ex) {
            Logger.getLogger(ImportDataService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void importBudgetInfoBi() {
        System.out.println("importing...");
        try {
            ClassPathResource res = new ClassPathResource("C:\\Users\\vr.kenfack\\Documents\\NetBeansProjects\\MailExport\\src\\main\\resources\\BudgetInfoBi.xlsx");
            File file = new File(res.getPath());
            //File file = new File(getClass().getResource("C:\\Users\\vr.kenfack\\Documents\\NetBeansProjects\\MailExport\\src\\main\\resources\\ProduitsInfoBi.xlsx").getFile());
            Workbook workbook = WorkbookFactory.create(file);
            final Sheet sheet = workbook.getSheetAt(0);
            int index = 1;
            Row row = sheet.getRow(index++);
            while (row != null) {
                System.out.println("index = " + index);
                RepObjectifProduitInfoBi objectif = new RepObjectifProduitInfoBi();
                RepObjectifProduitInfoBiPK key = new RepObjectifProduitInfoBiPK();
                row.getCell(0).setCellType(CellType.STRING);
                String codarInfoBi = row.getCell(0).getStringCellValue().trim();
                if (row.getCell(1) != null) {
                    row.getCell(1).setCellType(CellType.STRING);
                    String codar = row.getCell(1).getStringCellValue().trim();
                    objectif.setCodar(codar);
                }
                if (row.getCell(3) != null) {
                    row.getCell(3).setCellType(CellType.STRING);
                    String mois = row.getCell(3).getStringCellValue().trim();
                    key.setMois(Integer.parseInt(mois));
                }
                if (row.getCell(4) != null) {
                    row.getCell(4).setCellType(CellType.STRING);
                    String annee = row.getCell(4).getStringCellValue().trim();
                    key.setAnnee(Integer.parseInt(annee));
                }
                if (row.getCell(6) != null) {
                    row.getCell(6).setCellType(CellType.STRING);
                    String codeCd = row.getCell(6).getStringCellValue().trim();
                    key.setCodeCd(codeCd);
                }

                if (row.getCell(9) != null) {
                    row.getCell(9).setCellType(CellType.NUMERIC);
                    double budget = row.getCell(9).getNumericCellValue();
                    objectif.setBudget(budget);
                }

                key.setCodeInfoBi(codarInfoBi);

                objectif.setRepObjectifProduitInfoBiPK(key);
                System.out.println("objectif = " + objectif);
                if (key.getCodeCd() != null && key.getCodeInfoBi() != null) {
                    repObjectifProduitInfoBiDao.save(objectif);
                }
                row = sheet.getRow(index++);
            }
        } catch (IOException | EncryptedDocumentException ex) {
            Logger.getLogger(ImportDataService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void importTauxChange() {
        System.out.println("importing...");
        try {
            ClassPathResource res = new ClassPathResource("C:\\Users\\vr.kenfack\\Documents\\NetBeansProjects\\MailExport\\src\\main\\resources\\taux_change.xlsx");
            File file = new File(res.getPath());
            String jour = null;
            String mois = null;
            String annee = null;
            //File file = new File(getClass().getResource("C:\\Users\\vr.kenfack\\Documents\\NetBeansProjects\\MailExport\\src\\main\\resources\\ProduitsInfoBi.xlsx").getFile());
            Workbook workbook = WorkbookFactory.create(file);
            final Sheet sheet = workbook.getSheetAt(0);
            int index = 1;
            Row row = sheet.getRow(index++);
            while (row != null) {
                System.out.println("index = " + index);
                RepTauxChange taux = new RepTauxChange();
                row.getCell(1).setCellType(CellType.NUMERIC);
                double usdCdf = row.getCell(0).getNumericCellValue();
                taux.setUsdCdf(usdCdf);
                if (row.getCell(2) != null) {
                    row.getCell(2).setCellType(CellType.NUMERIC);
                    double euroCdf = row.getCell(1).getNumericCellValue();
                    taux.setEuroCdf(euroCdf);
                }
                if (row.getCell(3) != null) {
                    row.getCell(3).setCellType(CellType.NUMERIC);
                    double eurUsd = row.getCell(3).getNumericCellValue();
                    taux.setEuroUsd(eurUsd);
                }
                if (row.getCell(5) != null) {
                    row.getCell(5).setCellType(CellType.STRING);
                    jour = row.getCell(5).getStringCellValue().trim();
                }
                if (row.getCell(6) != null) {
                    row.getCell(6).setCellType(CellType.STRING);
                    mois = row.getCell(6).getStringCellValue().trim();
                }

                if (row.getCell(7) != null) {
                    row.getCell(7).setCellType(CellType.NUMERIC);
                    annee = row.getCell(9).getStringCellValue().trim();
                }

                taux.setDate(buildDate(Integer.parseInt(jour), Integer.parseInt(mois), Integer.parseInt(annee)));
                tauxChangeDao.save(taux);
                row = sheet.getRow(index++);
            }
        } catch (IOException | EncryptedDocumentException ex) {
            Logger.getLogger(ImportDataService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void importTaxes() {
        System.out.println("importing...");
        try {
            ClassPathResource res = new ClassPathResource("C:\\Users\\vr.kenfack\\Documents\\NetBeansProjects\\MailExport\\src\\main\\resources\\taxes.xlsx");
            File file = new File(res.getPath());
            //File file = new File(getClass().getResource("C:\\Users\\vr.kenfack\\Documents\\NetBeansProjects\\MailExport\\src\\main\\resources\\ProduitsInfoBi.xlsx").getFile());
            Workbook workbook = WorkbookFactory.create(file);
            final Sheet sheet = workbook.getSheetAt(0);
            int index = 1;
            Row row = sheet.getRow(index++);
            while (row != null) {
                //System.out.println("index = " + index);
                RepTaxeArticle taxeArticle = new RepTaxeArticle();
                String t1base = "";
                String t1code = "";
                String t2base = "";
                String t2code = "";
                String t3base = "";
                String t3code = "";
                String t4base = "";
                String t4code = "";
                int annee = 0;
                int mois = 0;
                int jour  = 0;
                if (row.getCell(0) != null) {
                    row.getCell(0).setCellType(CellType.STRING);
                    annee = Integer.parseInt(row.getCell(0).getStringCellValue().trim());
                    taxeArticle.setAnnee(annee);
                }
                if (row.getCell(1) != null) {
                    row.getCell(1).setCellType(CellType.STRING);
                    String coTaxe = row.getCell(1).getStringCellValue().trim();
                    taxeArticle.setCodeTaxe(coTaxe);
                }
                if (row.getCell(2) != null) {
                    row.getCell(2).setCellType(CellType.STRING);
                    jour = Integer.parseInt(row.getCell(2).getStringCellValue().trim());
                    taxeArticle.setJour(jour);
                }
                if (row.getCell(3) != null) {
                    row.getCell(3).setCellType(CellType.STRING);
                    mois = Integer.parseInt(row.getCell(3).getStringCellValue().trim());
                    taxeArticle.setMois(mois);
                }
                if (row.getCell(4) != null) {
                    t1base = row.getCell(4).getStringCellValue().trim();
                    taxeArticle.setT1Base(t1base);
                }
                taxeArticle.setT1Base(t1base);
                if (row.getCell(5) != null) {
                    t1code = row.getCell(5).getStringCellValue().trim();

                }
                taxeArticle.setT1Code(t1code);
                if (row.getCell(6) != null) {
                    double t1cop = row.getCell(6).getNumericCellValue();
                    taxeArticle.setT1Copr(t1cop);
                }

                if (row.getCell(7) != null) {
                    t2base = row.getCell(7).getStringCellValue().trim();
                    taxeArticle.setT1Base(t2base);
                }
                taxeArticle.setT2Base(t2base);
                if (row.getCell(8) != null) {
                    t2code = row.getCell(8).getStringCellValue().trim();

                }
                taxeArticle.setT2Code(t2code);
                if (row.getCell(9) != null) {
                    double t2cop = row.getCell(9).getNumericCellValue();
                    taxeArticle.setT2Copr(t2cop);
                }

                if (row.getCell(10) != null) {
                    t3base = row.getCell(10).getStringCellValue().trim();
                    taxeArticle.setT3Base(t3base);
                }
                taxeArticle.setT3Base(t3base);
                if (row.getCell(11) != null) {
                    t3code = row.getCell(11).getStringCellValue().trim();

                }
                taxeArticle.setT3Code(t3code);
                if (row.getCell(12) != null) {
                    double t3cop = row.getCell(12).getNumericCellValue();
                    taxeArticle.setT3Copr(t3cop);
                }
                
                if (row.getCell(13) != null) {
                    t4base = row.getCell(13).getStringCellValue().trim();
                    taxeArticle.setT4Base(t4base);
                }
                taxeArticle.setT4Base(t4base);
                if (row.getCell(14) != null) {
                    t4code = row.getCell(14).getStringCellValue().trim();

                }
                taxeArticle.setT4Code(t4code);
                if (row.getCell(15) != null) {
                    double t4cop = row.getCell(15).getNumericCellValue();
                    taxeArticle.setT4Copr(t4cop);
                }

               taxeArticle.setDate(buildDate(jour, mois, annee));
               repTaxeArticleDao.save(taxeArticle);
                row = sheet.getRow(index++);
            }
        } catch (IOException | EncryptedDocumentException ex) {
            Logger.getLogger(ImportDataService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Date buildDate(int jour, int mois, int annee) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, annee);
        cal.set(Calendar.MONTH, mois - 1);
        cal.set(Calendar.DAY_OF_MONTH, jour);

        return cal.getTime();
    }
}
