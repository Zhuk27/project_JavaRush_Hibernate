package com.javaRush.module4.dao;

import com.javaRush.module4.entity.Address;
import org.hibernate.SessionFactory;

public class AddressDAO extends GenericDAO<Address> {

    public AddressDAO(SessionFactory sessionFactory) {
        super(Address.class, sessionFactory);
    }
}
