package com.bracongo.mailexport.dao.impl;

import com.bracongo.mailexport.dao.INativeQueryDao;
import com.bracongo.mailexport.data.dto.ClientServisCircuit;
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
                + " FROM HHT_BRACONGO.dbo.HHT_FACTURE,  HHT_BRACONGO.dbo.Tb_Circuit, HHT_BRACONGO.dbo.Tb_CentreDistribution\n"
                + " WHERE  HHT_FACTURE.CODE_DEVISE = 1 \n"
                + " AND          HHT_FACTURE.ANNULE IS NULL\n"
                + " AND			year(HHT_FACTURE.DATE_FACT) = " + year +"\n"
                + " AND			month(HHT_FACTURE.DATE_FACT) = " + month +"\n"
                + " AND			day(HHT_FACTURE.DATE_FACT) = " + day +"\n"
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
                + " FROM HHT_BRACONGO.dbo.HHT_FACTURE, HHT_BRACONGO.dbo.HHT_CLIENT, HHT_BRACONGO.dbo.Tb_CentreDistribution, HHT_BRACONGO.dbo.Tb_Circuit\n"
                + " WHERE HHT_FACTURE.CODE_CLT = HHT_CLIENT.CODE_CLT \n"
                + " AND          HHT_FACTURE.CODE_DEVISE = 1 \n"
                + " AND          HHT_FACTURE.ANNULE IS NULL\n"
                + " AND          year (HHT_FACTURE.DATE_FACT) = " + lastYear + "\n"
                + " AND          MONTH (HHT_FACTURE.DATE_FACT) = " + lastMonth +"\n"
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

}
