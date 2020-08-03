package com.provahero.provahero.service.utente;


import com.provahero.provahero.model.Utente;

public interface UtenteService {

    Utente aggiorna(Utente utenteInstance) throws Exception;

    void inserisciNuovo(Utente utenteInstance) throws Exception;

    void rimuovi(Long id) throws Exception;

    Utente findByUsernameAndPassword(String user, String password);

    Utente findById(long id) throws Exception;
}
