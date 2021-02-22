package com.bracongo.mailexport.ctrl;

import com.bracongo.mailexport.data.dto.CdHeurerResumeStat;
import com.bracongo.mailexport.service.IRapportFactureService;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author vr.kenfack
 */
@RestController
@RequestMapping("/distribution")
public class DistributionCtrl {

    @Autowired
    private IRapportFactureService factureService;

    @RequestMapping(method = RequestMethod.GET, value = "heuresortie")
    @CrossOrigin(origins = "*")
    public ResponseEntity<List<CdHeurerResumeStat>> heureSortie() {
        List<CdHeurerResumeStat> result = factureService.getListHeureCd();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "exportExcel")
    @ResponseBody
    @CrossOrigin(origins = "*")
    public ResponseEntity<?> exportReport(HttpServletRequest request, HttpServletResponse response) {
        try {
            System.out.println("travail");
            OutputStream out;
            LocalDateTime localDate = LocalDateTime.now();
            localDate = localDate.minusDays(1);
            String fileName = "Rapport_Heures_Ventes_SRD" + localDate.getDayOfMonth() + "_" + localDate.getMonthValue() + "_" + localDate.getYear() + ".xlsx";
            response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
            out = response.getOutputStream();
            byte[] contentReturn = factureService.exportHeureVenteRapport(out);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
            return new ResponseEntity<byte[]>(contentReturn, headers, HttpStatus.OK);
        } catch (IOException ex) {
            Logger.getLogger(DistributionCtrl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
