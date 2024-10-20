package com.javaRush.module4.controller;

import com.javaRush.module4.config.MySessionFactory;
import com.javaRush.module4.dao.*;
import com.javaRush.module4.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class ManagementCommandDB {
    private final SessionFactory sessionFactory;
    private final ActorDAO actorDAO;
    private final AddressDAO addressDAO;
    private final CategoryDAO categoryDAO;
    private final CityDAO cityDAO;
    private final CountryDAO countryDAO;
    private final CustomerDAO customerDAO;
    private final FilmDAO filmDAO;
    private final FilmTextDAO filmTextDAO;
    private final InventoryDAO inventoryDAO;
    private final LanguageDAO languageDAO;
    private final PaymentDAO paymentDAO;
    private final RentalDAO rentalDAO;
    private final StaffDAO staffDAO;
    private final StoreDAO storeDAO;

    public ManagementCommandDB() {
        MySessionFactory mySessionFactory = new MySessionFactory();
        sessionFactory = mySessionFactory.getSessionFactory();
        actorDAO = new ActorDAO(sessionFactory);
        addressDAO = new AddressDAO(sessionFactory);
        categoryDAO = new CategoryDAO(sessionFactory);
        cityDAO = new CityDAO(sessionFactory);
        countryDAO = new CountryDAO(sessionFactory);
        customerDAO = new CustomerDAO(sessionFactory);
        filmDAO = new FilmDAO(sessionFactory);
        filmTextDAO = new FilmTextDAO(sessionFactory);
        inventoryDAO = new InventoryDAO(sessionFactory);
        languageDAO = new LanguageDAO(sessionFactory);
        paymentDAO = new PaymentDAO(sessionFactory);
        rentalDAO = new RentalDAO(sessionFactory);
        staffDAO = new StaffDAO(sessionFactory);
        storeDAO = new StoreDAO(sessionFactory);
    }


    public void newMovieIsAvailable() {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Language language = languageDAO.findById(ThreadLocalRandom.current().nextInt(1, 7));
            Category category1 = categoryDAO.findById(ThreadLocalRandom.current().nextInt(1, 9));
            Category category2 = categoryDAO.findById(ThreadLocalRandom.current().nextInt(9, 17));
            List<Category> categories = Arrays.asList(category1, category2);
            Actor actor1 = actorDAO.findById(ThreadLocalRandom.current().nextInt(1, 91));
            Actor actor2 = actorDAO.findById(ThreadLocalRandom.current().nextInt(91, 163));
            Actor actor3 = actorDAO.findById(ThreadLocalRandom.current().nextInt(163, 201));
            List<Actor> actors = Arrays.asList(actor1, actor2, actor3);
            Film film = Film.builder()
                    .title("My first Film!")
                    .categories(categories)
                    .description("opisanie fima")
                    .actors(actors)
                    .length((short) 84)
                    .language(language)
                    .year(2024)
                    .rentalDuration((byte) 3)
                    .rentalRate(BigDecimal.valueOf(2.33))
                    .rating(Rating.PG)
                    .specialFeatures("Trailers")
                    .replacementCost(BigDecimal.valueOf(22))
                    .build();
            filmDAO.save(film);
            FilmText filmText = FilmText.builder()
                    .id(film.getId())
                    .film(film)
                    .description(film.getDescription())
                    .title(film.getTitle())
                    .build();
            filmTextDAO.save(filmText);
            session.getTransaction().commit();
        }catch (Exception e) {
            sessionFactory.getCurrentSession().getTransaction().rollback();
            throw new RuntimeException(e);
        }

    }

    public void buyerRentedInventory() {
        Customer customer = createCustomer();
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Film film = filmDAO.findFirstAvailableFilm();
            Store store = storeDAO.findById(1);
            Inventory inventory = Inventory.builder()
                    .film(film).store(store).build();
            inventoryDAO.save(inventory);
            Staff staff = store.getStaff();
            Rental rental = Rental.builder()
                    .customer(customer)
                    .rentalDate(LocalDateTime.now())
                    .staff(staff).inventory(inventory)
                    .build();
            rentalDAO.save(rental);
            Payment payment = Payment.builder()
                    .customer(customer)
                    .rental(rental)
                    .staff(staff)
                    .amount(BigDecimal.valueOf(33.2))
                    .build();
            paymentDAO.save(payment);
            session.getTransaction().commit();
        } catch (Exception e) {
            sessionFactory.getCurrentSession().getTransaction().rollback();
            throw new RuntimeException(e);
        }
    }

    public Customer createCustomer() {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Address address = Address.builder().address("Pushkina street")
                    .district("topp")
                    .city(cityDAO.findById(3))
                    .phone("880055355")
                    .build();
            Store store = storeDAO.findById(1);
            Address saveAddress = addressDAO.save(address);
            Customer customer = Customer.builder()
                    .address(saveAddress)
                    .firstName("Matvei")
                    .lastName("Zhukov")
                    .email("matvei@gmail.com")
                    .store(store)
                    .isActive(true)
                    .build();
            Customer save = customerDAO.save(customer);
            session.getTransaction().commit();
            return save;
        } catch (Exception e) {
            sessionFactory.getCurrentSession().getTransaction().rollback();
            throw new RuntimeException(e);
        }
    }

    public void buyerReturnedRentedFilm() {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Rental rental = rentalDAO.findById(1);
            rental.setReturnDate(LocalDateTime.now());
            rentalDAO.update(rental);
            session.getTransaction().commit();
        } catch (Exception e) {
            sessionFactory.getCurrentSession().getTransaction().rollback();
            throw new RuntimeException(e);
        }
    }
}
