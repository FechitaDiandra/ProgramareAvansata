package org.example.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import org.example.model.PublishingHouse;
import org.example.util.EntityManagerFactorySingleton;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * This class provides methods to perform CRUD operations on PublishingHouse entities.
 */
public class PublishingHouseRepository {

    private static final Logger logger = LogManager.getLogger(PublishingHouseRepository.class);

    private final EntityManagerFactory entityManagerFactory;

    /**
     * Constructs a PublishingHouseRepository object.
     * Initializes the entity manager factory using the singleton pattern.
     */
    public PublishingHouseRepository() {
        entityManagerFactory = EntityManagerFactorySingleton.getEntityManagerFactory();
    }

    /**
     * Creates a new publishing house entity in the database.
     * @param publishingHouse the publishing house entity to be created.
     */
    public void create(PublishingHouse publishingHouse) {
        long startTime = System.currentTimeMillis();
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(publishingHouse);
            transaction.commit();
            entityManager.close();
            long endTime = System.currentTimeMillis();
            long executionTime = endTime - startTime;
            logger.info("PublishingHouse created successfully in {} milliseconds", executionTime);
        } catch (Exception ex) {
            logger.error("An error occurred while creating the publishing house", ex);
        }
    }

    /**
     * Finds a publishing house entity by its ID.
     * @param id the ID of the publishing house to find.
     * @return the publishing house entity with the specified ID, or null if not found.
     */
    public PublishingHouse findById(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        PublishingHouse publishingHouse = null;
        try {
            publishingHouse = entityManager.find(PublishingHouse.class, id);
        } catch (Exception ex) {
            ex.printStackTrace(); // Handle or log exception appropriately
        } finally {
            entityManager.close();
        }
        return publishingHouse;
    }

    /**
     * Retrieves all publishing house entities from the database.
     * @return a list of all publishing house entities.
     */
    public List<PublishingHouse> findAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<PublishingHouse> publishingHouses = null;
        try {
            TypedQuery<PublishingHouse> query = entityManager.createQuery("SELECT ph FROM PublishingHouse ph", PublishingHouse.class);
            publishingHouses = query.getResultList();
        } catch (Exception ex) {
            ex.printStackTrace(); // Handle or log exception appropriately
        } finally {
            entityManager.close();
        }
        return publishingHouses;
    }

    /**
     * Updates an existing publishing house entity in the database.
     * @param publishingHouse the publishing house entity to be updated.
     */
    public void update(PublishingHouse publishingHouse) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(publishingHouse);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            ex.printStackTrace(); // Handle or log exception appropriately
        } finally {
            entityManager.close();
        }
    }

    /**
     * Deletes a publishing house entity from the database.
     * @param publishingHouse the publishing house entity to be deleted.
     */
    public void delete(PublishingHouse publishingHouse) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.remove(entityManager.contains(publishingHouse) ? publishingHouse : entityManager.merge(publishingHouse));
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            ex.printStackTrace(); // Handle or log exception appropriately
        } finally {
            entityManager.close();
        }
    }
}
