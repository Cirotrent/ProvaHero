package com.provahero.provahero.service.hero;


import com.provahero.provahero.model.Hero;

import com.provahero.provahero.repository.hero.HeroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class heroServiceImpl implements HeroService {

    @Autowired
    private HeroRepository heroRepository;


    @Override
    public Hero aggiorna(Hero heroInstance) throws Exception {
        Hero result;
        try {
            Hero temp= findById(heroInstance.getId());
            if(temp==null){
                throw new Exception("not found!");
            }
            result =heroRepository.save(heroInstance);
        }catch (Exception e){
            throw new Exception("internal server error!");
        }
        return result;
    }

    @Override
    public void inserisciNuovo(Hero heroInstance) {
        heroRepository.save(heroInstance);
    }

    @Override
    public void rimuovi(Long id) {
        heroRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Hero> findAllByNameContains(String tokenInput) {
        return heroRepository.findAllByNameContains(tokenInput);
    }

    @Transactional(readOnly = true)
    public Hero findById(long id) throws Exception  {
        if (id <= 0) {
            throw new  Exception("not found!");
        }
        return heroRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Hero> findAllOrderByPotenzaDesc() {
        return heroRepository.findAllOrderByPotenzaDesc();
    }
}
