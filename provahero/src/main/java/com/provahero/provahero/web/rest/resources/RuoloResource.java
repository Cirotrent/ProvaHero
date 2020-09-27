package com.provahero.provahero.web.rest.resources;

import com.provahero.provahero.model.Ruolo;
import com.provahero.provahero.service.ruolo.RuoloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("provahero")
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
