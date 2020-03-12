package com.codecool.shop.dao;

import com.codecool.shop.model.User;

public interface UserDao {
    User add(User user) throws SecurityException;
    User find(int id);
    void remove(int id);
    User getBy(String username);
    boolean isUnique(String username);
}
