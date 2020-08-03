package com.provahero.provahero.web.dto.hero;

import com.provahero.provahero.model.Hero;

import java.util.ArrayList;
import java.util.List;

public class HeroDto {

    private Long id;
    private String name;
    private String cognome;
    private Integer potenza;

    public HeroDto() {
    }

    public HeroDto(Long id, String name, String cognome, Integer potenza) {
        this.id = id;
        this.name = name;
        this.cognome = cognome;
        this.potenza = potenza;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public Integer getPotenza() {
        return potenza;
    }

    public void setPotenza(Integer potenza) {
        this.potenza = potenza;
    }

    @Override
    public String toString() {
        return "HeroDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cognome='" + cognome + '\'' +
                ", potenza=" + potenza +
                '}';
    }


}
