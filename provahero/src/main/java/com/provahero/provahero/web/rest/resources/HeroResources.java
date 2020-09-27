package com.provahero.provahero.web.rest.resources;


import com.provahero.provahero.model.Hero;
import com.provahero.provahero.model.Ruolo;
import com.provahero.provahero.model.Utente;
import com.provahero.provahero.repository.hero.HeroRepository;
import com.provahero.provahero.service.hero.HeroService;
import com.provahero.provahero.service.utente.UtenteService;
import com.provahero.provahero.web.dto.hero.HeroDto;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("provahero")
public class HeroResources {

    @Autowired
    HeroService heroService;

    @Autowired
    UtenteService utenteService;

    @Autowired
    private HeroRepository heroRepository;

    @GetMapping("/allHeroes")
    @ResponseBody
    public ResponseEntity<?> listAll() throws InvocationTargetException, IllegalAccessException {
        List<Hero> heroes;
        try {
            heroes = heroService.findAllOrderByPotenzaDesc();
        } catch (Exception e) {
            return new ResponseEntity<>("Errore", null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        List<HeroDto> heroDto = new ArrayList<>();

        for (Hero hero : heroes
        ) {
            HeroDto temp = new HeroDto();
            BeanUtils.copyProperties(temp, hero);
            heroDto.add(temp);
        }
        return ResponseEntity.ok(heroDto);
    }

    @GetMapping("/findById")
    @ResponseBody
    public ResponseEntity<?> findByid(@RequestParam Long id) {
        Hero result;
        try {
            result = heroService.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Errore", null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (result == null) {
            return new ResponseEntity<>("not found!", null, HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(result);
    }

    @PostMapping(path = "/inserisci", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> addMember(@RequestBody Hero hero) {
        try {
            heroService.inserisciNuovo(hero);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>("Errore", null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.ok(hero);
    }

    @PutMapping(path = "/update")
    public ResponseEntity<?> updateUser(@RequestBody Hero hero) {
        Hero response;

        try {
            response = heroService.aggiorna(hero);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>("Errore!", null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/search")
    public ResponseEntity<?> search(@RequestParam String token) {
        List<Hero> result;
        result = heroService.findAllByNameContains(token);
        return ResponseEntity.ok(result);
    }

    @PutMapping(path = "/buy")
    public ResponseEntity<?> BuyHero(@RequestBody Hero hero, @RequestParam Long id) {
        Utente utente;
        List<Hero> heroes;

        try {
            utente = utenteService.findUtenteByIdEagerHeroes(id);
            if (utente.getHeroes() != null && utente.getHeroes().contains(hero)) {
                return new ResponseEntity<>("hero già comprato!", null, HttpStatus.LOCKED);
            }
            utente.getHeroes().add(hero);
            utenteService.aggiorna(utente);
            if (hero == null) {
                return new ResponseEntity<>("not found!", null, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<String>("Errore", null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok(hero);
    }


    @GetMapping("/findAllHeroByUtenteId")
    @ResponseBody
    public ResponseEntity<List<Hero>> findAllheroByUtenteId(@RequestParam Long id) {
        List<Hero> result = null;
        try {
            result = heroService.getHeroesByUtenteId(id);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(result, null, HttpStatus.INTERNAL_SERVER_ERROR);

        }
        if (result == null) {
            return new ResponseEntity<>(result, null, HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(result);
    }

    @PutMapping("/rimuoviHeroDaUtente")
    @ResponseBody
    public ResponseEntity<?> rimuoviHeroDaUtente(@RequestParam Long idUtente, @RequestParam Long idHero) {

        System.out.println(idHero + "---- " + idUtente);
        try {
            heroService.rimuoviHeroDaUtente(idUtente, idHero);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Errore", null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.ok("Eliminazione avvenuta con successo");
    }

    @GetMapping(path = "/provaFindAllHeroesByUtenteId")
    public ResponseEntity<?> provafindAllHeroesByUtenteId(@RequestParam Long id) {
        List<Hero> result;
        result = heroService.findAllHeroesByUtenteId(id);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/cancellaHero")
    @ResponseBody
    public ResponseEntity<?> cancellaHero(@RequestParam Long idHero, @RequestParam Long idUtente) {
        List<Utente> allUtenti;
        Utente utenteInSessione;

        try {
            utenteInSessione = utenteService.findUtenteByIdEagerRuoli(idUtente);
            for (Ruolo ruolo :
                    utenteInSessione.getRuoli()) {
                if (ruolo.getCodice().equals(Ruolo.ADMIN_ROLE)) {
                    allUtenti = utenteService.findAll();
                    for (Utente utente : allUtenti
                    ) {
                        heroService.rimuoviHeroDaUtente(utente.getId(), idHero);
                    }
                    heroService.rimuovi(idHero);
                    return ResponseEntity.ok("Cancellazione avvenuta con successo!");
                }
            }
            return new ResponseEntity<>("Utente non autorizzato!", null, HttpStatus.PRECONDITION_FAILED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Errore", null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


}
