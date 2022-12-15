package com.bracongo.mailexport.ctrl;

import com.bracongo.mailexport.data.dto.CdHeurerResumeStat;
import com.bracongo.mailexport.data.dto.ChargementProduitDto;
import com.bracongo.mailexport.service.IChargementService;
import com.bracongo.mailexport.service.impl.ImportDataService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author vr.kenfack
 */
@RestController
@RequestMapping("/chargement")
public class ChargementCtrl {
    
    @Autowired
    private IChargementService chargementService;
    
    @Autowired
    private ImportDataService dataService;
    
    @RequestMapping(method = RequestMethod.GET, value = "globalproduit")
    @CrossOrigin(origins = "*")
    public ResponseEntity<List<ChargementProduitDto>> getAllChargementGlobalByProduit() {
        List<ChargementProduitDto> result = chargementService.getAllChargementGlobalByProduit();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "globalproduit/voyage/{voyageId}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<List<ChargementProduitDto>> getAllChargementGlobalByProduitByVoyage(@PathVariable("voyageId")int voyageId) {
        List<ChargementProduitDto> result = chargementService.getAllChargementGlobalByProduitByVoyageId(voyageId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "test")
    @CrossOrigin(origins = "*")
    public void test() {
        System.out.println("ctrl import");
        dataService.importArticleCorrInfoBi();
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "test2")
    @CrossOrigin(origins = "*")
    public void test2() {
        System.out.println("ctrl import2");
        dataService.importBudgetInfoBi();
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "test3")
    @CrossOrigin(origins = "*")
    public void test3() {
        System.out.println("ctrl import3");
        dataService.importTauxChange();
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "maile")
    @CrossOrigin(origins = "*")
    public void mail() {
        System.out.println("ctrl import3");
        chargementService.produceChargementResumeAndMail();
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "test4")
    @CrossOrigin(origins = "*")
    public void test4() {
        System.out.println("ctrl import4");
        dataService.importTaxes();
    }
    
}
