package com.provahero.provahero.service.utente;


import com.provahero.provahero.model.Hero;
import com.provahero.provahero.model.Utente;

import java.util.List;

public interface UtenteService {

    Utente aggiorna(Utente utenteInstance) throws Exception;

    void inserisciNuovo(Utente utenteInstance) throws Exception;

    void rimuovi(Long id) throws Exception;

    Utente findByUsernameAndPassword(String user, String password);

    Utente findById(long id) throws Exception;

    Utente findUtenteByIdEagerHeroes(Long id);

    Utente findUtenteByIdEagerRuoli(Long id);

    List<Utente> findAll();
}
