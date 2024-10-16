package com.biblioteca.dao;

import com.biblioteca.entita.Prestito;
import com.biblioteca.entita.Utente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

public class PrestitoDAO {
    private EntityManager em;

    public PrestitoDAO(EntityManager em) {
        this.em = em;
    }

    public void aggiungiPrestito(Prestito prestito) {
        if (!haPrestitoAttivo(prestito.getUtente())) {
            em.persist(prestito);
        }
    }

    public boolean haPrestitoAttivo(Utente utente) {
        TypedQuery<Prestito> query = em.createQuery(
                "SELECT p FROM Prestito p WHERE p.utente = :utente AND p.dataRestituzioneEffettiva IS NULL", Prestito.class);
        query.setParameter("utente", utente);
        try {
            query.getSingleResult();
            return true;
        } catch (NoResultException e) {
            return false;
        }
    }
}
