package com.provahero.provahero.service.ruolo;

import com.provahero.provahero.model.Ruolo;
import com.provahero.provahero.repository.ruolo.RuoloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RuoloServiceImpl implements RuoloService {

    @Autowired
    private RuoloRepository ruoloRepository;

    @Override
    public List<Ruolo> findAllRuoli() {
        return (List<Ruolo>) ruoloRepository.findAll();
    }

    @Override
    public Ruolo findById(long id) {
        return ruoloRepository.findById(id);
    }
}
