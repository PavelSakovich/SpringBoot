package com.example.service;


import com.example.entity.User;
import com.example.exception.UserOutputException;

import java.util.List;

public interface UserService {
    void addUser(User user);

    void deleteUserById(int id);

    List<User> getAllUsers();

    User getUserById(int id) throws UserOutputException;

    void update(User user);

    void deleteAllUsers();

    List<User> getUsersByNumberHouse(int house);
}
