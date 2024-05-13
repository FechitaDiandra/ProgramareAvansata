package org.example.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * This class represents a publishing house entity.
 */
@Entity
@Table(name = "publishing_houses")
public class PublishingHouse implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "publishing_house_id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "publishingHouse", cascade = CascadeType.ALL)
    private List<Book> books;

    /**
     * Default constructor for PublishingHouse class.
     */
    public PublishingHouse() {
    }

    /**
     * Get the ID of the publishing house.
     * @return the ID of the publishing house.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Set the ID of the publishing house.
     * @param id the ID of the publishing house to set.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Get the name of the publishing house.
     * @return the name of the publishing house.
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of the publishing house.
     * @param name the name of the publishing house to set.
     */
    public void setName(String name) {
        this.name = name;
    }
}
