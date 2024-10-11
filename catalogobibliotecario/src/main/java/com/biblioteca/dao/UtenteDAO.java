package com.biblioteca.dao;

import com.biblioteca.entita.Utente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class UtenteDAO {
    private EntityManager em;

    public UtenteDAO(EntityManager em) {
        this.em = em;
    }

    public void aggiungiUtente(Utente utente) {
        EntityTransaction transaction = em.getTransaction();
        try {
            if (!transaction.isActive()) {
                transaction.begin();
            }
            em.persist(utente);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }
}
