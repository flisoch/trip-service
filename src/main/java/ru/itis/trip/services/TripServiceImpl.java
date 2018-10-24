package ru.itis.trip.services;

import ru.itis.trip.dao.TripDao;
import ru.itis.trip.entities.Trip;

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
}
