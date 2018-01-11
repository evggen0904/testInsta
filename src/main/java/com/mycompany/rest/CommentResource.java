package com.mycompany.rest;

import com.mycompany.ejb.EJBCommentDAO;
import com.mycompany.entity.Comment;

import javax.ejb.EJB;
import javax.persistence.Column;
import javax.persistence.NoResultException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("pictures/{picture_id :\\d+}")
public class CommentResource {

    @EJB
    private EJBCommentDAO ejbCommentDAO;

    @GET
    @Path("/comments")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Comment> getComments(@PathParam("picture_id") int pictureId){
        try {
            return ejbCommentDAO.getComments(pictureId);
        } catch (NoResultException e) {
            return null;
        }
    }

    @POST
    @Path("/comments")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public int addComment(@PathParam("picture_id") int pictureId, Comment comment){
//        Comment newComment = new Comment(comment.getAuthor_id(),comment.getComment_date(),
//                comment.getComment(), comment.getPicture_id());
        return ejbCommentDAO.addComment(comment);
    }
}
