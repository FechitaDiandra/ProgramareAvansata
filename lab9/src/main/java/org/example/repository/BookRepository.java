package org.example.repository;

import org.example.model.Book;
import org.example.util.EntityManagerUtil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

/**
 * The {@code BookRepository} class encapsulates methods for CRUD operations on Book entities.
 */
public class BookRepository {
    private final EntityManagerUtil entityManagerUtil;

    /**
     * Constructs a new BookRepository instance.
     */
    public BookRepository(EntityManagerUtil entityManagerUtil) {
        this.entityManagerUtil = EntityManagerUtil.getInstance();
    }

    /**
     * Creates a new book entity in the database.
     *
     * @param book The book entity to be created.
     */
    public void create(Book book) {
        EntityManager entityManager = entityManagerUtil.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(book);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    /**
     * Retrieves a book entity by its ID from the database.
     *
     * @param id The ID of the book entity to retrieve.
     * @return The book entity with the specified ID, or null if not found.
     */
    public Book findById(int id) {
        EntityManager entityManager = entityManagerUtil.getEntityManager();
        Book book = entityManager.find(Book.class, id);
        entityManager.close();
        return book;
    }

    /**
     * Retrieves a list of book entities with titles containing the specified pattern from the database.
     *
     * @param title The pattern to search for in book titles.
     * @return A list of book entities whose titles contain the specified pattern.
     */
    public List<Book> findByName(String title) {
        EntityManager entityManager = entityManagerUtil.getEntityManager();
        TypedQuery<Book> query = entityManager.createNamedQuery("Book.findByName", Book.class);
        query.setParameter("title", "%" + title + "%");
        List<Book> books = query.getResultList();
        entityManager.close();
        return books;
    }
}
