package org.example.model;

import jakarta.persistence.*;

import org.example.util.EntityManagerUtil;

import java.io.Serializable;
import java.util.List;

/**
 * The {@code Book} class represents a book entity in the database.
 */
@Entity
@Table(name = "books")
@NamedQueries({
        @NamedQuery(name = "Book.findAll",
                query = "SELECT b FROM Book b ORDER BY b.title"),
        @NamedQuery(name = "Book.findByName",
                query = "SELECT b FROM Book b WHERE b.title LIKE :title"),
        @NamedQuery(name = "Book.findById",
                query = "SELECT b FROM Book b WHERE b.id = :id")
})
public class Book implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id") // Adjusted column name here
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "language")
    private String language;

    @Column(name = "publication_date")
    private java.sql.Date publicationDate;

    @Column(name = "number_of_pages")
    private Integer numberOfPages;

    @Column(name = "author_id")
    private Integer authorId;

    // Constructors
    public Book() {}

    public Book(String title, String language, java.sql.Date publicationDate, Integer numberOfPages, Integer authorId) {
        this.title = title;
        this.language = language;
        this.publicationDate = publicationDate;
        this.numberOfPages = numberOfPages;
        this.authorId = authorId;
    }

    // Getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public java.sql.Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(java.sql.Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public Integer getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(Integer numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    // Other methods, if any

    /**
     * Retrieves a list of books whose title contains the specified name.
     *
     * @param name The name to search for within book titles.
     * @return A list of books matching the specified name.
     */
    public static List<Book> findByName(String name) {
        EntityManager em = EntityManagerUtil.getInstance().getEntityManager();
        List<Book> books = em.createNamedQuery("Book.findByName", Book.class)
                .setParameter("title", "%" + name + "%")
                .getResultList();
        em.close();
        return books;
    }

    /**
     * Retrieves the book with the specified ID.
     *
     * @param id The ID of the book to retrieve.
     * @return The book with the specified ID, or null if not found.
     */
    public static Book findById(int id) {
        EntityManager em = EntityManagerUtil.getInstance().getEntityManager();
        Book book = em.find(Book.class, id);
        em.close();
        return book;
    }
}
