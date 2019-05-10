package ru.itis.trip.repositories.trip;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.trip.entities.Trip;
import ru.itis.trip.entities.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class TripRepositoryCustomImpl implements TripRepositoryCustom {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Trip> getAllNotExpired() {
        List<Trip> trips = em.createQuery("select t from Trip as t where " +
                "t.date > :now", Trip.class)
                .setParameter("now", LocalDateTime.now())
                .getResultList();
        return trips;
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
        User passanger = em.find(User.class, passangerId);
        Trip trip = em.createQuery("select t from Trip as t where id=:tripId", Trip.class)
                .setParameter("tripId", tripId).getSingleResult();
        trip.getPassangers().add(passanger);
        trip.setFreeSeats(trip.getFreeSeats() - 1);
        em.persist(trip);

    }

    @Override
    public List<Trip> getByUserId(Long userId) {
        User user = em.find( User.class, userId);
        return em.createQuery("select t from Trip as t where t.iniciator=:user", Trip.class)
                .setParameter("user", user).getResultList();
    }

    @Override
    public List<Trip> getByParameters(String departure, String destination, String freeSeats, LocalDateTime date) {
        List<Trip> trips;
        StringBuilder query = new StringBuilder("SELECT t from Trip as t where ");

        if (departure != null && !departure.equals("")) {
            query.append("t.departurePoint LIKE \'").append(departure).append("%\' AND ");
        }
        if (destination != null && !destination.equals("")) {
            query.append("t.arrivalPoint LIKE \'").append(destination).append("%\' AND ");
        }
        if (freeSeats != null && !freeSeats.equals("")) {
            query.append("t.freeSeats >= ").append(freeSeats);
            query.append(" AND ");
        }
        if (date != null) {
            query.append("t.dateTime >= :dateTime").append(" AND ");
        }
        query.append("free_seats > 0");
        trips = em.createQuery(query.toString(), Trip.class).setParameter("date", date).getResultList();
        return trips;
    }

}
