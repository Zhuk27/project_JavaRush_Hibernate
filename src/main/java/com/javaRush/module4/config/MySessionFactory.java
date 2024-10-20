package com.javaRush.module4.config;

import com.javaRush.module4.entity.*;
import lombok.Getter;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@Getter
public class MySessionFactory {
    private final SessionFactory sessionFactory;

    public MySessionFactory() {
        Configuration configuration = new Configuration().configure();
        sessionFactory = configuration.addAnnotatedClass(Actor.class)
                .addAnnotatedClass(Address.class)
                .addAnnotatedClass(Category.class)
                .addAnnotatedClass(City.class)
                .addAnnotatedClass(Country.class)
                .addAnnotatedClass(Customer.class)
                .addAnnotatedClass(Film.class)
                .addAnnotatedClass(FilmText.class)
                .addAnnotatedClass(Inventory.class)
                .addAnnotatedClass(Language.class)
                .addAnnotatedClass(Payment.class)
                .addAnnotatedClass(Rental.class)
                .addAnnotatedClass(Store.class)
                .addAnnotatedClass(Staff.class)
                .buildSessionFactory();
    }


}
