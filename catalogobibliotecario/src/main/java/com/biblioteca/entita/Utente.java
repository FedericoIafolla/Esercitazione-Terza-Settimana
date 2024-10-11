package com.biblioteca.entita;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "utente")
public class Utente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cognome;
    private LocalDate dataNascita;
    private String numeroTessera;

    @OneToOne(mappedBy = "utente")
    private Prestito prestito;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public LocalDate getDataNascita() {
        return dataNascita;
    }

    public void setDataNascita(LocalDate dataNascita) {
        this.dataNascita = dataNascita;
    }

    public String getNumeroTessera() {
        return numeroTessera;
    }

    public void setNumeroTessera(String numeroTessera) {
        this.numeroTessera = numeroTessera;
    }

    public Prestito getPrestito() {
        return prestito;
    }

    public void setPrestito(Prestito prestito) {
        this.prestito = prestito;
    }
}
