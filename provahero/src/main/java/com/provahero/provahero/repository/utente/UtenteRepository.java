package com.provahero.provahero.repository.utente;

import com.provahero.provahero.model.Utente;
import org.springframework.data.repository.CrudRepository;

public interface UtenteRepository extends CrudRepository<Utente,Long> {

    Utente findByUsernameAndPassword(String user,String password);

    Utente findById(long id);

}
