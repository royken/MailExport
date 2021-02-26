package com.bracongo.mailexport.ctrl;

import com.bracongo.mailexport.data.RepVenteRatee;
import com.bracongo.mailexport.data.dto.ProduitHectoRateeDto;
import com.bracongo.mailexport.data.dto.VenteRateeCdDto;
import com.bracongo.mailexport.service.IRapportFactureService;
import com.bracongo.mailexport.service.IRapportInfoBiService;
import com.bracongo.mailexport.service.IVenteRateeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;




/**
 *
 * @author vr.kenfack
 */

@RestController
@RequestMapping("/venteratees")
public class VenteRateeCtrl {
    
    @Autowired
    private IVenteRateeService venteRateeService;
    
    @Autowired
    private IRapportFactureService factureService;
    
    @Autowired
    private IRapportInfoBiService infoBiService;
    
    @RequestMapping(method = RequestMethod.GET)
    @CrossOrigin(origins = "*")
    public ResponseEntity<List<RepVenteRatee>> getAllVentesRatees(){
        List<RepVenteRatee> result = venteRateeService.getAllVentesRatees();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "statproduit")
    @CrossOrigin(origins = "*")
    public ResponseEntity<List<ProduitHectoRateeDto>> getAllVentesRateesProduit(){
        List<ProduitHectoRateeDto> result = venteRateeService.getAllProduitRateeStat();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "statcd")
    @CrossOrigin(origins = "*")
    public ResponseEntity<List<VenteRateeCdDto>> getAllVentesRateesCd(){
        List<VenteRateeCdDto> result = venteRateeService.getAllRateeCdStat();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "mail")
    @CrossOrigin(origins = "*")
    public void testMail(){
         factureService.produceHeureVenteRapportAndMail();
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "mail2")
    @CrossOrigin(origins = "*")
    public void testMail2(){
         infoBiService.produceRapportDataAndMail();
    }
    
}
