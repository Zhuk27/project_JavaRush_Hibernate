package com.javaRush.module4.dao;

import com.javaRush.module4.entity.Film;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class FilmDAO extends GenericDAO<Film> {
    public FilmDAO(SessionFactory sessionFactory) {
        super(Film.class, sessionFactory);
    }

    public Film findFirstAvailableFilm() {
        Session session = sessionFactory.getCurrentSession();
        Query<Film> query = session.createQuery("select f  from Film f where f.id not in" +
                "(select distinct film.id from Inventory)", Film.class);
        query.setMaxResults(1);
        return query.getSingleResult();
    }
}
