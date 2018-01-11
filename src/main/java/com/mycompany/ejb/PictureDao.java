package com.mycompany.ejb;

import com.mycompany.entity.Picture;

import java.util.List;

public interface PictureDao {

    public void addPicture(int user_id, Picture picture);
    public List<Picture> getMorePictures(int user_id, int startFrom, int rows_count);
    public List<Picture> getAllPictures(int user_id);
    public Picture getPicture(int user_id, int pictureId);
}
