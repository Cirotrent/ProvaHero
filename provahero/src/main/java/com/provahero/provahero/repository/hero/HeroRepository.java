package com.provahero.provahero.repository.hero;

import com.provahero.provahero.model.Hero;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface HeroRepository extends CrudRepository<Hero,Long>{

    List<Hero> findAllByNameContains(String token);

    Hero findById(long id);

    @Query("from Hero p order by p.potenza desc")
    List<Hero> findAllOrderByPotenzaDesc();


}
