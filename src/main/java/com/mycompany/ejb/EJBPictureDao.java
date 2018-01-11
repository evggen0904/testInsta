package com.mycompany.ejb;

import com.mycompany.entity.Picture;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
@LocalBean
public class EJBPictureDao implements PictureDao {

    @PersistenceContext(unitName = "PU")
    private EntityManager entityManager;

    @Override
    public void addPicture(int user_id, Picture picture) {

    }

    @Override
    public List<Picture> getMorePictures(int user_id, int startFrom, int rows_count) {
        try {
            Query query = entityManager.createQuery("select p from Picture p");
//            query.setParameter("user", user);
            query.setFirstResult(startFrom);
            query.setMaxResults(rows_count);
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<Picture> getAllPictures(int user_id) {
        try {
            Query query = entityManager.createQuery("select p from Picture p");
//            query.setParameter("user", user);
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public Picture getPicture(int user_id, int pictureId) {
        try {
            Query query = entityManager.createQuery("select p from Picture p where p.id=:pictureId");
            query.setParameter("pictureId", pictureId);
            return (Picture) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
