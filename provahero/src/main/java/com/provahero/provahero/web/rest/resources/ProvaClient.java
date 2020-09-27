package com.provahero.provahero.web.rest.resources;


import com.provahero.provahero.web.dto.utente.UtenteCheAccede;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface ProvaClient {

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(path = "/accedi", consumes = "application/x-www-form-urlencoded", produces = "application/x-www-form-urlencoded")
    ResponseEntity<?> findUtenteByUserAndPassword(@RequestBody UtenteCheAccede utente);
}
