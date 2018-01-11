package com.mycompany.ejb;

import com.mycompany.entity.User;

public interface UserDao {
    public User getForUsername(String username);

    public void createUser(User user);
}
