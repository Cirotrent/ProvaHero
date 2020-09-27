package com.provahero.provahero.repository.hero;

import com.provahero.provahero.model.Hero;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HeroRepository extends CrudRepository<Hero,Long>{

    List<Hero> findAllByNameContains(String token);

    Hero findById(long id);

    @Query("from Hero p order by p.potenza desc")
    List<Hero> findAllOrderByPotenzaDesc();

//    @Procedure("heroesByUtente(:id)")
//    List<Object> getHeroesByUtenteId(@Param("id") Long id);

    @Query(value = "call heroesByUtente(:id);", nativeQuery = true)
    List<Hero> getHeroesByUtenteId(@Param("id") Long id);

    @Procedure(name="rimuoviHeroDaUtente")
    void rimuoviHeroDaUtente(@Param("idUtente") Long idUtente,@Param("idHero") Long idHero );

    @Query("from Hero h join h.utenti u where u.id = ?1")
    List<Hero> findAllHeroesByUtenteId(Long id);


}
