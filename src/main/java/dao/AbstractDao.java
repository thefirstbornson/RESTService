package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

public abstract class AbstractDao<T> implements Dao<T> {

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private EntityTransaction entityTransaction;

    AbstractDao(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public T persist(T object) {
        entityManager = entityManagerFactory.createEntityManager();
        entityTransaction = entityManager.getTransaction();
        beginTransaction();
        T entity = (T) entityManager.merge(object);
        commitTransaction();
        return entity;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public EntityTransaction getEntityTransaction() {
        return entityTransaction;
    }

    public T getByPK( Class<T> clazz,long key) {
        entityManager = entityManagerFactory.createEntityManager();
        T entity = (T) entityManager.find(clazz, key);
        entityManager.close();
        return entity;
    }

    private void beginTransaction() {
        try {
            entityTransaction.begin();
        }
        catch (IllegalStateException e) {
            rollbackTransaction();
        }
    }

    private void commitTransaction() {
        try {
            entityTransaction.commit();
            entityManager.close();
        }
        catch (IllegalStateException e) {
            rollbackTransaction();
        }
    }

    public EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }

    private void rollbackTransaction() {
        try {
            entityTransaction.rollback();
        }
        catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }

}
