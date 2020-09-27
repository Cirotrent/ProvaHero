package com.provahero.provahero.service.hero;

import com.provahero.provahero.model.Hero;

import java.util.List;

public interface HeroService  {

     Hero aggiorna(Hero heroInstance) throws Exception;

     void inserisciNuovo(Hero heroInstance);

     void rimuovi(Long id);

     List<Hero> findAllByNameContains(String tokenInput);

     Hero findById(long id) throws Exception;

     List<Hero> findAllOrderByPotenzaDesc();

     List<Hero> getHeroesByUtenteId(Long id);

     void rimuoviHeroDaUtente(Long idUtente,Long idHero);

     List<Hero> findAllHeroesByUtenteId(Long id);
}
