package com.provahero.provahero.web.rest.resources;

import com.provahero.provahero.model.Hero;
import com.provahero.provahero.model.Ruolo;
import com.provahero.provahero.model.Utente;
import com.provahero.provahero.service.hero.HeroService;
import com.provahero.provahero.service.utente.UtenteService;
import com.provahero.provahero.web.dto.utente.UtenteCheAccede;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("provahero")
public class UtenteResources {//implements ProvaClient

    @Autowired
    UtenteService utenteService;

    @Autowired
    HeroService heroService;

//    @Autowired
//    @Lazy
//    private ProvaClient provaClient;

    @Value("${spring.application.name}")
    private String appName;


    //####################################################################################


    @RequestMapping(path = "/prova")
    public ResponseEntity<String> myMethod() {
        return ResponseEntity.ok("va bene");

     //   httpResponse.;//sendRedirect("http://localhost:4200/login");

    }


//    @Override
//    public ResponseEntity<?> findUtenteByUserAndPassword(UtenteCheAccede utente) {
//
//                Utente result;
//        try {
//            result = utenteService.findByUsernameAndPassword(utente.getUser(), utente.getPassword());
//            if (result == null) {
//                return new ResponseEntity<>("not found!", null, HttpStatus.NOT_FOUND);
//            }
//        } catch (Exception e) {
//            return new ResponseEntity<String>("Errore", null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//        return ResponseEntity.ok(result);
//
//    }

//###########################################################################################

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
        return ResponseEntity.ok(result);
    }

    @GetMapping("/findByIdUtente")
    @ResponseBody
    public ResponseEntity<?> findByidUtente(@RequestParam Long id) {
        Utente result;
        try {
            result = utenteService.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Errore", null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (result == null) {
            return new ResponseEntity<>("not found!", null, HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(result);
    }

    @PostMapping(path = "/registrazione", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> addMember(@RequestBody Utente utente) {
        try {
            utenteService.inserisciNuovo(utente);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>("Errore", null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.ok(utente);
    }

    @PutMapping(path = "/addRuoli")
    public ResponseEntity<?> adRuoli(@RequestBody List<Ruolo> ruoli, @RequestParam long id) {
        Utente result;
        Utente utente;

        try {
            utente = utenteService.findById(id);
            for (Ruolo item : ruoli) {
                utente.getRuoli().add(item);
            }

            result = utenteService.aggiorna(utente);
            if (result == null) {
                return new ResponseEntity<>("not found!", null, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<String>("Errore", null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/findHeroesByUtente")
    @ResponseBody
    public ResponseEntity<?> findHeroesByUtenteId(@RequestParam Long id) {
        List<Hero> result;
        try {
            result = heroService.findAllHeroesByUtenteId(id);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Errore", null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.ok(result);
    }

    @GetMapping(path = "/provaUtenteEagerHero")
    public ResponseEntity<?> provaEager(@RequestParam Long id) {
        Utente result;
        result = utenteService.findUtenteByIdEagerHeroes(id);
        return ResponseEntity.ok(result);
    }


    @PostMapping(path = "/modifica", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> modificaUtente(@RequestBody Utente utente) {
        try {
            utenteService.inserisciNuovo(utente);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>("Errore", null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.ok(utente);
    }



}
