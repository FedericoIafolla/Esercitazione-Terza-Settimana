package com.biblioteca.dao;

import com.biblioteca.entita.Elemento;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

public class ElementoDAO {
    private EntityManager em;

    public ElementoDAO(EntityManager em) {
        this.em = em;
    }

    public void aggiungiElemento(Elemento elemento) {
        if (!esisteElemento(elemento)) {
            em.persist(elemento);
        }
    }

    public boolean esisteElemento(Elemento elemento) {
        TypedQuery<Elemento> query = em.createQuery(
                "SELECT e FROM Elemento e WHERE e.isbn = :isbn AND e.titolo = :titolo", Elemento.class);
        query.setParameter("isbn", elemento.getIsbn());
        query.setParameter("titolo", elemento.getTitolo());
        try {
            query.getSingleResult();
            return true;
        } catch (NoResultException e) {
            return false;
        }
    }
}
