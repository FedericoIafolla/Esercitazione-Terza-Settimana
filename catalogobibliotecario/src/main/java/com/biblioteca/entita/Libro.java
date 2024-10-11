package com.biblioteca.entita;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "libro")
public class Libro extends Elemento {
    private String autore;
    private String genere;

    public String getAutore() {
        return autore;
    }

    public void setAutore(String autore) {
        this.autore = autore;
    }

    public String getGenere() {
        return genere;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }
}
