package com.provahero.provahero.web.rest.resources;

import com.provahero.provahero.model.Utente;
import com.provahero.provahero.service.utente.UtenteService;
import com.provahero.provahero.web.dto.utente.UtenteCheAccede;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UtenteResources {

    @Autowired
    UtenteService utenteService;

    @PostMapping(path = "/accedi", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> findUtenteByUserAndPassword(@RequestBody UtenteCheAccede utente) {
        Utente result;
        try {
            result = utenteService.findByUsernameAndPassword(utente.getUser(), utente.getPassword());
            if (result == null) {
                return new ResponseEntity<>("not found!", null, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<String>("Errore", null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        System.out.println(result);
        return ResponseEntity.ok(result);
    }
}
