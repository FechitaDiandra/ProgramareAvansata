package org.example.util;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 * The {@code EntityManagerFactorySingleton} class provides a singleton instance
 * of the {@link jakarta.persistence.EntityManagerFactory} for accessing the
 * persistence unit.
 */
public class EntityManagerFactorySingleton {
    private static final String PERSISTENCE_UNIT_NAME = "PersistenceUnit";
    private static EntityManagerFactory emf;

    private EntityManagerFactorySingleton() {
    }

    public static EntityManagerFactory getEntityManagerFactory() {
        if (emf == null) {
            synchronized (EntityManagerFactorySingleton.class) {
                if (emf == null) {
                    emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
                }
            }
        }
        return emf;
    }

    public static void closeEntityManagerFactory() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}