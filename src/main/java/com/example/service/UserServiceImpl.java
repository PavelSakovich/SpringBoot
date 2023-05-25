package com.example.service;

import com.example.entity.User;
import com.example.exception.UserOutputException;
import com.example.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
@Service
@RequiredArgsConstructor
@Log
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public void addUser(User user) {
        userRepository.save(user);
        log.info("User added in database");

    }

    @Override
    public void deleteUserById(int id) {
        userRepository.deleteById(id);
        log.info("User deleted with index " + id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        log.info("List  was got users");
        return userRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserById(int id) throws UserOutputException {
        return userRepository.findById(id).orElseThrow(() -> new UserOutputException("User with index " + id + "does not exist"));
    }

    @Override
    public void update(User user) {
        userRepository.save(user);
        log.info("User update");
    }

    @Override
    public void deleteAllUsers() {
        userRepository.deleteAll();
        log.info("Users deleted");
    }
    @Transactional(readOnly = true)
    public List<User> getUsersByNumberHouse(int house){
        log.info("List  was got users");
        return userRepository.getUsersByNumberHouse(house);
    }
}
