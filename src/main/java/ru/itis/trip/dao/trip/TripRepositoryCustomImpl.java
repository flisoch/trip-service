package ru.itis.trip.dao.trip;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.trip.entities.Trip;
import ru.itis.trip.entities.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class TripRepositoryCustomImpl implements TripRepositoryCustom {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Trip> findByIniciatorId(Long userId) {
        return em.createQuery("Select t from Trip as t where t.iniciator = :userId", Trip.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    @Override
    public List<Trip> getAllNotExpired() {
        return em.createQuery("select t from Trip as t where t.date > :now", Trip.class)
                .setParameter("now", LocalDateTime.now())
                .getResultList();
    }


    @Override
    public List<Trip> getBookedByUserId(Long userId) {
        User user = em.createQuery("select u from User as u where id=:userId", User.class)
                .setParameter("userId", userId).getSingleResult();
        return em.createQuery("select t from Trip as t where :user member of t.passangers", Trip.class)
                .setParameter("user", user).getResultList();
    }

    @Override
    @Transactional
    public void addUserToTrip(Long passangerId, Long tripId) {
        User passanger = em.createQuery("select u from User as u where id=:userId", User.class)
                .setParameter("userId", passangerId).getSingleResult();
        Trip trip = em.createQuery("select t from Trip as t where id=:tripId", Trip.class)
                .setParameter("tripId", tripId).getSingleResult();
        trip.getPassangers().add(passanger);
        trip.setFreeSeats(trip.getFreeSeats() - 1);
        em.persist(trip);

    }

    @Override
    public List<Trip> getByUserId(Long userId) {
        User user = em.createQuery("select u from User as u where id=:userId", User.class)
                .setParameter("userId", userId).getSingleResult();
        return em.createQuery("select t from Trip as t where t.iniciator=:user", Trip.class)
                .setParameter("user", user).getResultList();
    }


}
