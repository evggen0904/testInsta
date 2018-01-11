package com.mycompany.rest;


import com.mycompany.ejb.EJBPictureDao;
import com.mycompany.entity.Picture;

import javax.ejb.EJB;
import javax.persistence.NoResultException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/pictures")
@Produces(MediaType.APPLICATION_JSON)
public class PictureResource {

    @EJB
    private EJBPictureDao pictureBean;

    @GET
    public List<Picture> getAllPictures(){
        try {
            return pictureBean.getAllPictures(2);
        } catch (NoResultException e) {
            return null;
        }
    }

    @GET
    @Path("/some")
    public List<Picture> getPictures(@HeaderParam("startFrom") int startFrom,
                                     @HeaderParam("maxRows") int maxRows){
        try {
            return pictureBean.getMorePictures(2, startFrom, maxRows);
        } catch (NoResultException e) {
            return null;
        }
    }

    @GET
    @Path("{picture_id}")
    public Picture getPicture(@PathParam("picture_id") int picture_id){
        try {
            return pictureBean.getPicture(2, picture_id);
        } catch (NoResultException e) {
            return null;
        }
    }

}
