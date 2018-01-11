package com.mycompany.rest;


import com.mycompany.ejb.EJBUserDao;
import com.mycompany.entity.User;

import javax.ejb.EJB;
import javax.persistence.NoResultException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/users")
public class UserResource {

    @EJB
    private EJBUserDao userBean;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getAllUsers(){
        try {
            return userBean.getAll();
        } catch (NoResultException e) {
            return null;
        }
    }

   /* @GET
    @Path("/{user_id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getUser(@PathParam("user_id") int user_id){
        User user = userBean.get(user_id);
        if (user == null)
            return "";
        return user.toString();
    }*/
}
