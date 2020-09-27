package com.provahero.provahero.service.utente;

import com.provahero.provahero.model.Hero;
import com.provahero.provahero.model.Utente;
import com.provahero.provahero.repository.utente.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UtenteServiceImpl implements UtenteService {

    @Autowired
    private UtenteRepository utenteRepository;



    @Override
    public Utente aggiorna(Utente utenteInstance) throws Exception {

        Utente result;
        try {
            result = utenteRepository.save(utenteInstance);
        } catch (Exception e) {
            throw new Exception("Internal server error");
        }

        return result;
    }

    @Override
    public void inserisciNuovo(Utente utenteInstance) throws Exception {
        try {
            utenteRepository.save(utenteInstance);
        } catch (Exception e) {
            throw new Exception("Internal server error!");
        }
    }

    @Override
    public void rimuovi(Long id) throws Exception {

        try {
            utenteRepository.deleteById(id);
        } catch (Exception e) {
            throw new Exception("Internal server error!");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Utente findByUsernameAndPassword(String user, String password) {
        return utenteRepository.findByUsernameAndPassword(user, password);
    }

    @Override
    @Transactional(readOnly = true)
    public Utente findById(long id) throws Exception {
        Utente result;
        try {
            result = utenteRepository.findById(id);
        } catch (Exception e) {
            throw new Exception("Internal server error!");
        }
        return result;
    }

    @Override
    public Utente findUtenteByIdEagerHeroes(Long id) {
        return utenteRepository.findUtenteByIdEagerHeroes(id);
    }

    @Override
    public Utente findUtenteByIdEagerRuoli(Long id) {
        return utenteRepository.findUtenteByIdEagerRuoli(id);
    }

    @Override
    public List<Utente> findAll() {
        return (List<Utente>) utenteRepository.findAll();
    }
}
