package com.mycompany.ejb;

import com.mycompany.entity.User;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.*;
import java.util.List;

@Stateless
@LocalBean
public class EJBUserDao implements UserDao {

    @PersistenceContext(unitName = "PU")
    private EntityManager entityManager;

    @Override
    public User getForUsername(String username) {
        try {
            Query query = entityManager.createQuery("select u from User u where u.name = :username");
            query.setParameter("username", username);
            return (User) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public void createUser(User user) {
        entityManager.persist(user);
    }

    // Получаем все пользователей с БД
    public List<User> getAll(){
        TypedQuery<User> namedQuery = entityManager.createQuery("from User ", User.class);
        return namedQuery.getResultList();
    }

}
