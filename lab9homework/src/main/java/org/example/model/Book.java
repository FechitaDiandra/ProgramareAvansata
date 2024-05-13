package org.example.model;


import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * The {@code Book} class represents a book entity in the database.
 */
@Entity
@Table(name = "books")
@NamedQueries({
        @NamedQuery(name = "Book.findAll",
                query = "select e from Book e order by e.title"),
        @NamedQuery(name = "Book.findByName",
                query = "select e from Book e where e.title LIKE :title"),
        @NamedQuery(name = "Book.findById",
                query = "select e from Book e where e.id = :id")
})
public class Book implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "title")
    private String title;

    @ManyToOne
    @JoinColumn(name = "publishing_house_id")
    private PublishingHouse publishingHouse;

    /**
     * Retrieves a list of book entities with titles containing the specified pattern from the database.
     *
     * @param name The pattern to search for in book titles.
     * @return A list of book entities whose titles contain the specified pattern.
     */
    public static List<Book> findByName(String name) {
        EntityManager em = Persistence.createEntityManagerFactory("YourPersistenceUnitName")
                .createEntityManager();
        return em.createNamedQuery("Book.findByName", Book.class)
                .setParameter("title", name)
                .getResultList();
    }

    /**
     * Retrieves a book entity by its ID from the database.
     *
     * @param id The ID of the book entity to retrieve.
     * @return The book entity with the specified ID, or null if not found.
     */
    public static Book findById(int id) {
        EntityManager em = Persistence.createEntityManagerFactory("YourPersistenceUnitName")
                .createEntityManager();
        return em.find(Book.class, id);
    }

    // Other methods

    /**
     * Sets the title of the book.
     *
     * @param title The title of the book.
     */
    public void setTitle(String title){
        this.title = title;
    }

    /**
     * Retrieves the title of the book.
     *
     * @return The title of the book.
     */
    public String getTitle(){
        return title;
    }

    /**
     * Retrieves the ID of the book.
     *
     * @return The ID of the book.
     */
    public int getId(){
        return id;
    }

    public void setPublishingHouse(PublishingHouse publishingHouse) {
        this.publishingHouse = publishingHouse;
    }

    public PublishingHouse getPublishingHouse() {
        return publishingHouse;
    }

}