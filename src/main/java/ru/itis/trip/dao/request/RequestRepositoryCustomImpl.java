package ru.itis.trip.dao.request;

import org.springframework.stereotype.Component;
import ru.itis.trip.entities.Request;
import ru.itis.trip.entities.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class RequestRepositoryCustomImpl implements RequestRepositoryCustom {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Request> findByUserId(Long userId) {
        User user = em.createQuery("select u from User as u where id=:userId", User.class)
                .setParameter("userId", userId).getSingleResult();
        return em.createQuery("select r from Request as r where r.user = :user or r.trip.iniciator=:user", Request.class)
                .setParameter("user", user).getResultList();
    }

}
