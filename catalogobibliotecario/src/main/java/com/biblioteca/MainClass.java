package com.biblioteca;

import com.biblioteca.dao.ElementoDAO;
import com.biblioteca.dao.PrestitoDAO;
import com.biblioteca.dao.UtenteDAO;
import com.biblioteca.entita.Libro;
import com.biblioteca.entita.Prestito;
import com.biblioteca.entita.Rivista;
import com.biblioteca.entita.Utente;
import com.biblioteca.entita.Periodicita;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class MainClass {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("CatalogoBibliotecaPU");
        EntityManager em = emf.createEntityManager();

        ElementoDAO elementoDAO = new ElementoDAO(em);
        UtenteDAO utenteDAO = new UtenteDAO(em);
        PrestitoDAO prestitoDAO = new PrestitoDAO(em);

        Libro libro1 = new Libro();
        libro1.setIsbn("987654321");
        libro1.setTitolo("Pdor figlio di Kmer");
        libro1.setAnnoPubblicazione(1999);
        libro1.setNumeroPagine(200);
        libro1.setAutore("Aldo, Giovanni e Giacomo");
        libro1.setGenere("Fantasy");

        Libro libro2 = new Libro();
        libro2.setIsbn("9780679783268");
        libro2.setTitolo("1984");
        libro2.setAnnoPubblicazione(1949);
        libro2.setNumeroPagine(328);
        libro2.setAutore("George Orwell");
        libro2.setGenere("Distopia");

        Libro libro3 = new Libro();
        libro3.setIsbn("9780544003415");
        libro3.setTitolo("Il Signore degli Anelli");
        libro3.setAnnoPubblicazione(1954);
        libro3.setNumeroPagine(1178);
        libro3.setAutore("J.R.R. Tolkien");
        libro3.setGenere("Fantasy");

        em.getTransaction().begin();
        try {
            elementoDAO.aggiungiElemento(libro1);
            elementoDAO.aggiungiElemento(libro2);
            elementoDAO.aggiungiElemento(libro3);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        }

        Rivista rivista1 = new Rivista();
        rivista1.setIsbn("1122334455");
        rivista1.setTitolo("L'espresso");
        rivista1.setPeriodicita(Periodicita.SETTIMANALE);
        rivista1.setAnnoPubblicazione(2024);
        rivista1.setNumeroPagine(100);

        Rivista rivista2 = new Rivista();
        rivista2.setIsbn("2233445566");
        rivista2.setTitolo("Panorama");
        rivista2.setPeriodicita(Periodicita.SETTIMANALE);
        rivista2.setAnnoPubblicazione(2024);
        rivista2.setNumeroPagine(120);

        Rivista rivista3 = new Rivista();
        rivista3.setIsbn("3344556677");
        rivista3.setTitolo("Il Venerdì di Repubblica");
        rivista3.setPeriodicita(Periodicita.SETTIMANALE);
        rivista3.setAnnoPubblicazione(2024);
        rivista3.setNumeroPagine(80);

        em.getTransaction().begin();
        try {
            elementoDAO.aggiungiElemento(rivista1);
            elementoDAO.aggiungiElemento(rivista2);
            elementoDAO.aggiungiElemento(rivista3);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        }

        Utente utente1 = new Utente();
        utente1.setNumeroTessera("001");
        utente1.setNome("Aldo");
        utente1.setCognome("Baglio");
        utente1.setDataNascita(LocalDate.of(1958, 9, 28));

        Utente utente2 = new Utente();
        utente2.setNumeroTessera("002");
        utente2.setNome("Giovanni");
        utente2.setCognome("Storti");
        utente2.setDataNascita(LocalDate.of(1957, 5, 21));

        Utente giacomino = new Utente();
        giacomino.setNumeroTessera("003");
        giacomino.setNome("Giacomino");
        giacomino.setCognome("Poretti");
        giacomino.setDataNascita(LocalDate.of(1956, 4, 26));

        em.getTransaction().begin();
        try {
            utenteDAO.aggiungiUtente(utente1);
            utenteDAO.aggiungiUtente(utente2);
            utenteDAO.aggiungiUtente(giacomino);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        }

        Prestito prestitoGiacomino = new Prestito();
        prestitoGiacomino.setUtente(giacomino);
        prestitoGiacomino.setElemento(libro1);
        prestitoGiacomino.setDataInizioPrestito(LocalDate.now());
        prestitoGiacomino.setDataRestituzionePrevista(LocalDate.now().plus(30, ChronoUnit.DAYS));

        em.getTransaction().begin();
        try {
            prestitoDAO.aggiungiPrestito(prestitoGiacomino);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        }

        em.close();
        emf.close();
    }
}
