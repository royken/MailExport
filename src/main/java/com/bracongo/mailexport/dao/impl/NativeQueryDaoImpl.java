package com.bracongo.mailexport.dao.impl;

import com.bracongo.mailexport.dao.INativeQueryDao;
import com.bracongo.mailexport.data.dto.CaCircuitSigmaDto;
import com.bracongo.mailexport.data.dto.CautionSigmaBlDto;
import com.bracongo.mailexport.data.dto.ClientServisCircuit;
import com.bracongo.mailexport.data.dto.ResumeCADateDto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author vr.kenfack
 */
@Repository
public class NativeQueryDaoImpl implements INativeQueryDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<ClientServisCircuit> getClientServiCircuitStat(int year, int month, int day, int lastYear, int lastMonth) {
        List<ClientServisCircuit> result = new ArrayList<>();
        String request = "select n1.effectif as effectifAcheteur, n2.effectif as effectifCircuit, n2.CODE_CD as CODE_CD,  n2.NOM_CD as CD, n2.CIRCUIT as CIRCUIT\n"
                + " from\n"
                + " (select COUNT( distinct HHT_FACTURE.CODE_CLT) as effectif,\n"
                + " Tb_CentreDistribution.CDI_CODECD as CODE_CD,\n"
                + " Tb_CentreDistribution.CDI_NOMCDI as NOM_CD,\n"
                + " Tb_Circuit.CIR_CODSIGMA as CIRCUIT \n"
                + " FROM BD_APP_BRACONGO.dbo.HHT_FACTURE,  BD_APP_BRACONGO.dbo.Tb_Circuit, BD_APP_BRACONGO.dbo.Tb_CentreDistribution\n"
                + " WHERE  HHT_FACTURE.CODE_DEVISE = 1 \n"
                + " AND          HHT_FACTURE.ANNULE IS NULL\n"
                + " AND			year(HHT_FACTURE.DATE_FACT) = " + year + "\n"
                + " AND			month(HHT_FACTURE.DATE_FACT) = " + month + "\n"
                + " AND			day(HHT_FACTURE.DATE_FACT) = " + day + "\n"
                + " AND			DATEPART(HOUR, (HHT_FACTURE.DATE_FACT)) >= 5 \n"
                + " AND HHT_FACTURE.CODE_ROUTE = Tb_Circuit.CIR_CODCIR\n"
                + " AND substring (Tb_Circuit.CIR_CODCIR,1,2) = Tb_CentreDistribution.CDI_CODECD\n"
                + " group by Tb_CentreDistribution.CDI_CODECD, Tb_CentreDistribution.CDI_NOMCDI, Tb_Circuit.CIR_CODSIGMA\n"
                + " ) as n1,\n"
                + " ( \n"
                + " select count(distinct HHT_FACTURE.CODE_CLT) as effectif,\n"
                + " Tb_CentreDistribution.CDI_CODECD as CODE_CD,\n"
                + " Tb_CentreDistribution.CDI_NOMCDI as NOM_CD,\n"
                + " Tb_Circuit.CIR_CODSIGMA as CIRCUIT \n"
                + " FROM BD_APP_BRACONGO.dbo.HHT_FACTURE, BD_APP_BRACONGO.dbo.HHT_CLIENT, BD_APP_BRACONGO.dbo.Tb_CentreDistribution, BD_APP_BRACONGO.dbo.Tb_Circuit\n"
                + " WHERE HHT_FACTURE.CODE_CLT = HHT_CLIENT.CODE_CLT \n"
                + " AND          HHT_FACTURE.CODE_DEVISE = 1 \n"
                + " AND          HHT_FACTURE.ANNULE IS NULL\n"
                + " AND          year (HHT_FACTURE.DATE_FACT) = " + lastYear + "\n"
                + " AND          MONTH (HHT_FACTURE.DATE_FACT) = " + lastMonth + "\n"
                + " AND HHT_FACTURE.CODE_ROUTE = Tb_Circuit.CIR_CODCIR\n"
                + " AND substring (HHT_FACTURE.CODE_CLT,1,2) = Tb_CentreDistribution.CDI_CODECD\n"
                + " group by Tb_CentreDistribution.CDI_CODECD, Tb_CentreDistribution.CDI_NOMCDI, Tb_Circuit.CIR_CODSIGMA\n"
                + " )as n2\n"
                + " \n"
                + " where n1.CODE_CD = n2.CODE_CD and n1.CIRCUIT = n2.CIRCUIT\n"
                + " \n"
                + " order by CD, CIRCUIT";
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(request);
        rows.stream().map((row) -> {
            ClientServisCircuit data = new ClientServisCircuit();
            data.setClientAcheteurs((Integer) row.get("effectifAcheteur"));
            data.setEffectifCircuit((Integer) row.get("effectifCircuit"));
            data.setCircuit((String) row.get("CIRCUIT"));
            data.setCodeCd((String) row.get("CODE_CD"));
            data.setNomCd((String) row.get("CD"));
            return data;
        }).forEachOrdered((data) -> {
            result.add(data);
        });
        // System.out.println("RESULT " + result);
        return result;
    }

    @Override
    public List<ResumeCADateDto> getNegoceCaResumeDataByMonthByYear(int mois, int annee) {
        List<ResumeCADateDto> result = new ArrayList<>();
        String request = "SELECT [ANNEE] as annee\n"
                + "      ,SUM([CASIER]) as casiers\n"
                + "      ,[CODARS] as codars\n"
                + "      ,[CODE_CD] as codeCd\n"
                + "      ,[FAMILLE] as famille\n"
                + "      ,sum([HECTO]) as hecto\n"
                + "      ,[MOIS] as mois\n"
                + "      ,[NOM_PRODUIT] as nomProduit\n"
                + "      ,[NSCHTX] as taxe\n"
                + "      ,sum( CASE WHEN CASIER < 0 THEN PRIX_HT * -1 else PRIX_HT END ) as prixHt\n"
                + "      ,sum( CASE WHEN CASIER < 0 THEN PRIX_TTC * -1 else PRIX_TTC END ) as prixTtc\n"
                + "  FROM [BD_APP_BRACONGO].[dbo].[REP_VENTE_CD_CIRCUIT_JOUR_GRATUIT_CA]\n"
                + "\n"
                + "  where ANNEE = " + annee + " and MOIS = " + mois + " and CODARS like 'V%' and SS = 'F' and TTS not in ('GRV', 'AGV', 'CFI', 'RFI', 'CFL', 'RFL')\n"
                + "  group by [ANNEE]\n"
                + "      ,[CODARS]\n"
                + "      ,[CODE_CD]\n"
                + "      ,[FAMILLE]\n"
                + "      ,[MOIS]\n"
                + "      ,[NOM_PRODUIT]\n"
                + "      ,[NSCHTX]";
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(request);
        rows.stream().map((row) -> {
            ResumeCADateDto data = new ResumeCADateDto();
            data.setAnnee((Integer) row.get("annee"));
            data.setCodars((String) row.get("codars"));
            data.setHecto((Double) row.get("hecto"));
            data.setMois((Integer) row.get("mois"));
            data.setNomProduit((String) row.get("nomProduit"));
            data.setPrixHt((Double) row.get("prixHt"));
            data.setPrixTtc((Double) row.get("prixTtc"));
            data.setQuantite(((Double) row.get("casiers")).intValue());
            data.setFamille((String) row.get("famille"));
            data.setCodeCd((Integer) row.get("codeCd"));
            return data;
        }).forEachOrdered((data) -> {
            result.add(data);
        });
        // System.out.println("RESULT " + result);
        return result;
    }

    @Override
    public List<ClientServisCircuit> getClientServiCircuitStatMonth(int year, int month, int lastYear, int lastMonth) {
        List<ClientServisCircuit> result = new ArrayList<>();
        String request = "select n1.effectif as effectifAcheteur, n2.effectif as effectifCircuit, n2.CODE_CD as CODE_CD,  n2.NOM_CD as CD, n2.CIRCUIT as CIRCUIT\n"
                + " from\n"
                + " (select COUNT( distinct HHT_FACTURE.CODE_CLT) as effectif,\n"
                + " Tb_CentreDistribution.CDI_CODECD as CODE_CD,\n"
                + " Tb_CentreDistribution.CDI_NOMCDI as NOM_CD,\n"
                + " Tb_Circuit.CIR_CODSIGMA as CIRCUIT \n"
                + " FROM BD_APP_BRACONGO.dbo.HHT_FACTURE,  BD_APP_BRACONGO.dbo.Tb_Circuit, BD_APP_BRACONGO.dbo.Tb_CentreDistribution\n"
                + " WHERE  HHT_FACTURE.CODE_DEVISE = 1 \n"
                + " AND          HHT_FACTURE.ANNULE IS NULL\n"
                + " AND			year(HHT_FACTURE.DATE_FACT) = " + year + "\n"
                + " AND			month(HHT_FACTURE.DATE_FACT) = " + month + "\n"
                + " AND			DATEPART(HOUR, (HHT_FACTURE.DATE_FACT)) >= 5 \n"
                + " AND HHT_FACTURE.CODE_ROUTE = Tb_Circuit.CIR_CODCIR\n"
                + " AND substring (Tb_Circuit.CIR_CODCIR,1,2) = Tb_CentreDistribution.CDI_CODECD\n"
                + " group by Tb_CentreDistribution.CDI_CODECD, Tb_CentreDistribution.CDI_NOMCDI, Tb_Circuit.CIR_CODSIGMA\n"
                + " ) as n1,\n"
                + " ( \n"
                + " select count(distinct HHT_FACTURE.CODE_CLT) as effectif,\n"
                + " Tb_CentreDistribution.CDI_CODECD as CODE_CD,\n"
                + " Tb_CentreDistribution.CDI_NOMCDI as NOM_CD,\n"
                + " Tb_Circuit.CIR_CODSIGMA as CIRCUIT \n"
                + " FROM BD_APP_BRACONGO.dbo.HHT_FACTURE, BD_APP_BRACONGO.dbo.HHT_CLIENT, BD_APP_BRACONGO.dbo.Tb_CentreDistribution, BD_APP_BRACONGO.dbo.Tb_Circuit\n"
                + " WHERE HHT_FACTURE.CODE_CLT = HHT_CLIENT.CODE_CLT \n"
                + " AND          HHT_FACTURE.CODE_DEVISE = 1 \n"
                + " AND          HHT_FACTURE.ANNULE IS NULL\n"
                + " AND          year (HHT_FACTURE.DATE_FACT) = " + lastYear + "\n"
                + " AND          MONTH (HHT_FACTURE.DATE_FACT) = " + lastMonth + "\n"
                + " AND HHT_FACTURE.CODE_ROUTE = Tb_Circuit.CIR_CODCIR\n"
                + " AND substring (HHT_FACTURE.CODE_CLT,1,2) = Tb_CentreDistribution.CDI_CODECD\n"
                + " group by Tb_CentreDistribution.CDI_CODECD, Tb_CentreDistribution.CDI_NOMCDI, Tb_Circuit.CIR_CODSIGMA\n"
                + " )as n2\n"
                + " \n"
                + " where n1.CODE_CD = n2.CODE_CD and n1.CIRCUIT = n2.CIRCUIT\n"
                + " \n"
                + " order by CD, CIRCUIT";
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(request);
        rows.stream().map((row) -> {
            ClientServisCircuit data = new ClientServisCircuit();
            data.setClientAcheteurs((Integer) row.get("effectifAcheteur"));
            data.setEffectifCircuit((Integer) row.get("effectifCircuit"));
            data.setCircuit((String) row.get("CIRCUIT"));
            data.setCodeCd((String) row.get("CODE_CD"));
            data.setNomCd((String) row.get("CD"));
            return data;
        }).forEachOrdered((data) -> {
            result.add(data);
        });
        // System.out.println("RESULT " + result);
        return result;
    }

    @Override
    public List<ResumeCADateDto> getCaResumeDataByMonthByYear(int mois, int annee) {
        List<ResumeCADateDto> result = new ArrayList<>();
        String request = "SELECT [ANNEE] as annee\n"
                + "      ,SUM([CASIER]) as casiers\n"
                + "      ,[CODARS] as codars\n"
                + "      ,[CODE_CD] as codeCd\n"
                + "      ,[FAMILLE] as famille\n"
                + "      ,sum([HECTO]) as hecto\n"
                + "      ,[MOIS] as mois\n"
                + "      ,[NOM_PRODUIT] as nomProduit\n"
                + "      ,[NSCHTX] as taxe\n"
                + "      ,sum( CASE WHEN CASIER < 0 THEN PRIX_HT * -1 else PRIX_HT END ) as prixHt\n"
                + "      ,sum( CASE WHEN CASIER < 0 THEN PRIX_TTC * -1 else PRIX_TTC END ) as prixTtc\n"
                + "  FROM BD_APP_BRACONGO.[dbo].[REP_VENTE_CD_CIRCUIT_JOUR_GRATUIT_CA]\n"
                + "\n"
                + "  where ANNEE = " + annee + " and MOIS = " + mois + " and SS = 'F' and [FAMILLE] != '' and  FAMILLE != 'VIN' and FAMILLE != 'GL' and TTS not in ('GRA', 'ESG')\n"
                + "  group by [ANNEE]\n"
                + "      ,[CODARS]\n"
                + "      ,[CODE_CD]\n"
                + "      ,[FAMILLE]\n"
                + "      ,[MOIS]\n"
                + "      ,[NOM_PRODUIT]\n"
                + "      ,[NSCHTX]";
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(request);
        rows.stream().map((row) -> {
            ResumeCADateDto data = new ResumeCADateDto();
            data.setAnnee((Integer) row.get("annee"));
            data.setCodars((String) row.get("codars"));
            data.setHecto((Double) row.get("hecto"));
            data.setMois((Integer) row.get("mois"));
            data.setNomProduit((String) row.get("nomProduit"));
            data.setPrixHt((Double) row.get("prixHt"));
            data.setPrixTtc((Double) row.get("prixTtc"));
            data.setQuantite(((Double) row.get("casiers")).intValue());
            data.setFamille((String) row.get("famille"));
            data.setCodeCd((Integer) row.get("codeCd"));
            return data;
        }).forEachOrdered((data) -> {
            result.add(data);
        });
        // System.out.println("RESULT " + result);
        return result;
    }

    @Override
    public List<CaCircuitSigmaDto> getChiffreDaffaireByCircuit(int mois, int annee) {
        List<CaCircuitSigmaDto> result = new ArrayList<>();
        String request = "SELECT [ANNEE]\n"
                + "      ,[CODE_CD] as codeCd\n"
                + "	  , CODE_CIRCUIT as codeCircuit\n"
                + "      ,[MOIS]\n"
                + "	  ,SUM(( CASE WHEN CASIER < 0 THEN PRIX_HT * -1 else PRIX_HT END )) as PRIX_HT_SIGNE\n"
                + "  FROM BD_APP_BRACONGO.[dbo].[REP_VENTE_CD_CIRCUIT_JOUR_GRATUIT_CA]\n"
                + "\n"
                + "  where ANNEE = " + annee + " and MOIS = " + mois + "  and SS = 'F' and [FAMILLE] != '' and  FAMILLE != 'VIN' and FAMILLE != 'GL' and TTS not in ('GRA', 'ESG')\n"
                + "  group by [ANNEE]\n"
                + "      ,[CODE_CD]\n"
                + "	  , CODE_CIRCUIT\n"
                + "      ,[MOIS]\n"
                + "\n";
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(request);
        rows.stream().map((row) -> {
            CaCircuitSigmaDto data = new CaCircuitSigmaDto();
            data.setAnnee((Integer) row.get("annee"));
            data.setMois((Integer) row.get("mois"));
            data.setCodeCd((Integer) row.get("codeCd"));
            data.setCodeCircuit((String) row.get("codeCircuit"));
            data.setChiffreAffaire((Double) row.get("PRIX_HT_SIGNE"));
            return data;
        }).forEachOrdered((data) -> {
            result.add(data);
        });
        // System.out.println("RESULT " + result);
        return result;
    }

    @Override
    public List<CautionSigmaBlDto> getCautionSigmaAvecBl(int mois, int annee) {
        List<CautionSigmaBlDto> result = new ArrayList<>();
        String request = "select n2.[ANNEE], n2.[CCCDE], n2.[JOUR], n2.[TTC], n2.[NTD], n2.[NUMBL], n2.[NUMFAC], n1.[NTD] as ntdTrans, n1.TTC as ttcTrans, n1.JOUR, n1.MOIS, n1.ANNEE, n1.CODE_CD \n"
                + "  from \n"
                + " \n"
                + " (select * \n"
                + " FROM BD_APP_BRACONGO.[dbo].[REP_TRANSDO] where CODE_CD != 20 AND TTS = 'CCV' and MOIS = " + mois + " and ANNEE = " + annee + " and SS = 'F') as n1, \n"
                + " (select *\n"
                + "  FROM BD_APP_BRACONGO.[dbo].[REP_TRANSDO] where CODE_CD != 20 AND TTS = 'CAU' and MOIS = " + mois + " and ANNEE = " + annee + ")  as n2 \n"
                + " \n"
                + " where n1.[NUMBL] = n2.[NUMBL] and n1.[CODE_CD] =n2.[CODE_CD] ";
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(request);
        rows.stream().map((row) -> {
            CautionSigmaBlDto data = new CautionSigmaBlDto();
            data.setAnnee((Integer) row.get("ANNEE"));
            data.setMois((Integer) row.get("MOIS"));
            data.setCa((Double) row.get("ttcTrans"));
            data.setCation((Double) row.get("TTC"));
            data.setCircuit((String) row.get("CCCDE"));
            data.setCodeCd(((Integer) row.get("CODE_CD")).toString());
            data.setJour((Integer) row.get("JOUR"));
            data.setNtdCaution(((Integer) row.get("NTD")).toString());
            data.setNtdTransaction(((Integer) row.get("ntdTrans")).toString());
            data.setNumBl(((Integer) row.get("NUMBL")).toString());
            return data;
        }).forEachOrdered((data) -> {
            result.add(data);
        });
        // System.out.println("RESULT " + result);
        return result;
    }

}
