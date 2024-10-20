package com.javaRush.module4.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public abstract class GenericDAO<T> {
    private final Class<T> entityClass;
    protected SessionFactory sessionFactory;

    public GenericDAO(Class<T> entityClass, SessionFactory sessionFactory) {
        this.entityClass = entityClass;
        this.sessionFactory = sessionFactory;
    }

    public T findById(int id) {
        return sessionFactory.getCurrentSession().get(entityClass, id);
    }

    public List<T> findAll() {
        Session session = sessionFactory.openSession();
        return session.createQuery("from " + entityClass.getSimpleName(), entityClass).list();
    }

    public T save(T entity) {
        sessionFactory.getCurrentSession().persist(entity);
        return entity;
    }

    public void delete(T entity) {
        sessionFactory.getCurrentSession().evict(entity);
    }

    public T update(T entity) {
        return sessionFactory.getCurrentSession().merge(entity);
    }

}
