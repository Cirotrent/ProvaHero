package com.provahero.provahero.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "hero")
public class Hero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String cognome;
    private Integer potenza;
    @ManyToMany(fetch = FetchType.LAZY,
            mappedBy = "heroes")
    private List<Utente> utenti = new ArrayList<>();

    //@ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "utente_id")
//    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
//    @JoinTable(name="utente_hero",
//            joinColumns={
//                    @JoinColumn(name="heroId", referencedColumnName="id")},
//            inverseJoinColumns={
//                    @JoinColumn(name="utenteId", referencedColumnName="id")})
//    private Utente utente;

    @Override
    public String toString() {
        return "Hero{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cognome='" + cognome + '\'' +
                ", potenza=" + potenza +
                '}';
    }

    public Hero() {
    }

    public Hero(Long id, String name, String cognome, Integer potenza) {
        this.id = id;
        this.name = name;
        this.cognome = cognome;
        this.potenza = potenza;
    }

    public Hero(String name, String cognome, Integer potenza) {
        this.name = name;
        this.cognome = cognome;
        this.potenza = potenza;
    }
    @JsonIgnore
    public List<Utente> getUtenti() {
        return utenti;
    }

    public void setUtenti(List<Utente> utenti) {
        this.utenti = utenti;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hero hero = (Hero) o;
        return Objects.equals(id, hero.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
