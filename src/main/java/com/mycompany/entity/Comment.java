package com.mycompany.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@Entity
@Table(name = "comments")
@XmlRootElement
public class Comment {

    private int id;
    private int author_id;
    private Date comment_date;
    private String comment;
    private int picture_id;

    public Comment() {
    }

    public Comment(int author_id, Date comment_date, String comment, int picture_id) {
        this.author_id = author_id;
        this.comment_date = comment_date;
        this.comment = comment;
        this.picture_id = picture_id;
    }

    @Id
    @GeneratedValue
    @Column(name = "comment_id")
    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "picture_id")
    public int getPicture_id() {
        return picture_id;
    }

    public void setPicture_id(int picture_id) {
        this.picture_id = picture_id;
    }

    @Column(name = "comment_date")
    @Temporal(value = TemporalType.DATE)
    public Date getComment_date() {
        return comment_date;
    }

    public void setComment_date(Date comment_date) {
        this.comment_date = comment_date;
    }

    @Column(name = "comment")
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Column(name = "author_id")
    public int getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }
}
