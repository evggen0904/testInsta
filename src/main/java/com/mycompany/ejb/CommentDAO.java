package com.mycompany.ejb;


import com.mycompany.entity.Comment;

import java.util.Date;
import java.util.List;

public interface CommentDAO {
    public int addComment(Comment comment);
    public List<Comment> getComments(int picture_id);
    public int deleteComment(int comment_id);

}
