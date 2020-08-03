package com.provahero.provahero.web.rest.resources;


import com.provahero.provahero.model.Hero;
import com.provahero.provahero.repository.hero.HeroRepository;
import com.provahero.provahero.service.hero.HeroService;
import com.provahero.provahero.web.dto.hero.HeroDto;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class HeroResources {

    @Autowired
    HeroService heroService;

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

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam Long id) {
        try {
            heroService.rimuovi(id);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>("Errore", null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.ok(id);
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

    @GetMapping(path ="/search")
    public ResponseEntity<?>search(@RequestParam String token){
        List<Hero> result;
        result= heroService.findAllByNameContains(token);
        return ResponseEntity.ok(result);
    }

}
