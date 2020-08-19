package com.provahero.provahero.repository.ruolo;

import com.provahero.provahero.model.Ruolo;
import org.springframework.data.repository.CrudRepository;

public interface RuoloRepository extends CrudRepository<Ruolo,Long> {

    Ruolo findById(long id);
}
