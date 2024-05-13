package org.example.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import org.example.util.EntityManagerFactorySingleton;

import java.util.List;

public abstract class AbstractRepository<T> {
    protected final EntityManagerFactory entityManagerFactory;

    public AbstractRepository() {
        entityManagerFactory = EntityManagerFactorySingleton.getEntityManagerFactory();
    }

    public void create(T entity) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(entity);
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

    public T findById(Class<T> clazz, int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        T entity = null;
        try {
            entity = entityManager.find(clazz, id);
        } catch (Exception ex) {
            ex.printStackTrace(); // Handle or log exception appropriately
        } finally {
            entityManager.close();
        }
        return entity;
    }

    // Add other CRUD methods as needed

    protected List<T> executeQuery(String jpqlQuery, Class<T> clazz) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<T> resultList = null;
        try {
            TypedQuery<T> query = entityManager.createQuery(jpqlQuery, clazz);
            resultList = query.getResultList();
        } catch (Exception ex) {
            ex.printStackTrace(); // Handle or log exception appropriately
        } finally {
            entityManager.close();
        }
        return resultList;
    }
}
