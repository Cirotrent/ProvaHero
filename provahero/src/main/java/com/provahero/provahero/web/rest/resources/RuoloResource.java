package com.provahero.provahero.web.rest.resources;

import com.provahero.provahero.model.Ruolo;
import com.provahero.provahero.service.ruolo.RuoloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class RuoloResource {
    @Autowired
    private RuoloService ruoloService;

    @GetMapping("/allRuoli")
    @ResponseBody
    public ResponseEntity<?> listAll()  {
        List<Ruolo> ruoli;
        try {
            ruoli = ruoloService.findAllRuoli();
        } catch (Exception e) {
            return new ResponseEntity<>("Errore", null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.ok(ruoli);
    }
}
