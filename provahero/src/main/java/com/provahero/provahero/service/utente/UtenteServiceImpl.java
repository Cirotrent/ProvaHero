package com.provahero.provahero.service.utente;

import com.provahero.provahero.model.Utente;
import com.provahero.provahero.repository.utente.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public Utente findByUsernameAndPassword(String user, String password) {
        return utenteRepository.findByUsernameAndPassword(user, password);
    }

    @Override
    public Utente findById(long id) throws Exception {
        Utente result;
        try {
            result = utenteRepository.findById(id);
        } catch (Exception e) {
            throw new Exception("Internal server error!");
        }
        return result;
    }
}
