package com.provahero.provahero.repository.utente;

import com.provahero.provahero.model.Hero;
import com.provahero.provahero.model.Utente;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UtenteRepository extends CrudRepository<Utente, Long> {

    Utente findByUsernameAndPassword(String user, String password);

    Utente findById(long id);

//    @Query(value = "SELECT u from utente u where u.id=?1",nativeQuery = true)
//    Utente findByIdUtenteTest(long id);
@Query("from Hero h join h.utente u where u.id = ?1")
List<Hero> findAllHeroesByUtenteId(long id);
}
