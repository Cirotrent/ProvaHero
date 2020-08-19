package com.provahero.provahero.service.ruolo;

import com.provahero.provahero.model.Ruolo;

import java.util.List;

public interface RuoloService {

    List<Ruolo> findAllRuoli();

    Ruolo findById(long id);


}
