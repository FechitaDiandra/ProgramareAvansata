package org.example.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 * The {@code EntityManagerUtil} class provides utilities for managing
 * the {@link jakarta.persistence.EntityManager} instance.
 */
public class EntityManagerUtil {
    private static final String PERSISTENCE_UNIT_NAME = "PersistenceUnit";
    private static EntityManagerUtil instance;
    private EntityManagerFactory emf;

    /**
     * Constructs a new EntityManagerUtil object and initializes the
     * EntityManagerFactory.
     */
    private EntityManagerUtil() {
        emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    }

    /**
     * Retrieves the singleton instance of the EntityManagerUtil.
     *
     * @return The singleton instance of the EntityManagerUtil.
     */
    public static synchronized EntityManagerUtil getInstance() {
        if (instance == null) {
            instance = new EntityManagerUtil();
        }
        return instance;
    }

    /**
     * Retrieves the EntityManager instance.
     *
     * @return The EntityManager instance.
     */
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    /**
     * Closes the EntityManagerFactory.
     */
    /**
     * Closes the EntityManagerFactory instance.
     */
    public void closePersistenceUnit() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }

}
