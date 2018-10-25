package ru.itis.trip.services;

import ru.itis.trip.dao.TripDao;
import ru.itis.trip.entities.Trip;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class TripServiceImpl implements TripService {
    TripDao tripDao;

    public TripServiceImpl(TripDao tripDao) {
        this.tripDao = tripDao;
    }

    @Override
    public List<Trip> getAllTrips() {
        return tripDao.getAllNotExpired();
    }

    @Override
    public Trip getById(Long id) {
        return tripDao.read(id).get();
    }

    @Override
    public List<Trip> getTripsWithParameters(HttpServletRequest request) {
        String departure = request.getParameter("departure");
        String destination = request.getParameter("destination");
        return tripDao.getByDirection(departure, destination);
    }
}
