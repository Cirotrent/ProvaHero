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

    @Query("from Utente u left join fetch u.heroes where u.id = ?1")
    Utente findUtenteByIdEagerHeroes(Long id);

    @Query("from Utente u join fetch u.ruoli where u.id = ?1")
    Utente findUtenteByIdEagerRuoli(Long id);

}
