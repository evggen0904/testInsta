package com.mycompany.ejb;


import com.mycompany.entity.Comment;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;

@Stateless
@LocalBean
public class EJBCommentDAO implements CommentDAO {

    @PersistenceContext(unitName = "PU")
    private EntityManager entityManager;

    @Override
    public int addComment(Comment comment) {

//        Comment newComment = new Comment(user_id, commentDate, comment, picture_id);
        try {
            entityManager.persist(comment);
            return 1;
        } catch (NoResultException e) {
            return -1;
        }
    }

    @Override
    public List<Comment> getComments(int picture_id) {
        try {
            Query query = entityManager.createQuery("select c from Comment c " +
                    "where c.picture_id=:picture_id ");
            query.setParameter("picture_id", picture_id);
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public int deleteComment(int comment_id) {
        return -1;
    }
}
