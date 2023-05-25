package com.example.service;

import com.example.entity.User;
import com.example.exception.UserOutputException;
import com.example.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    @InjectMocks
    private UserServiceImpl usersService;

    @Mock
    private UserRepository userRepositoryMock;

    @Test
    void addUsers() {
        final User user = new User(1, "Pavel", "Sakovic", 34);
//        given(userRepositoryMock.findById(user.getId())).willReturn(Optional.empty());
//        given(userRepositoryMock.save(user)).willAnswer(invocationOnMock -> invocationOnMock.getArgument(0));
        usersService.addUser(user);
        verify(userRepositoryMock, times(1)).save(user);
    }

    @Test
    void deleteUserById() throws UserOutputException {
        final Integer userId = 1;
        usersService.deleteUserById(userId);
        usersService.deleteUserById(userId);
        verify(userRepositoryMock, times(2)).deleteById(userId);
    }

    @Test
    void getAllUsers() {
        List<User> userList = new ArrayList<>();
        userList.add(new User(2, "Df", "D", 34));
        when(userRepositoryMock.findAll()).thenReturn(userList);
        List<User> users = usersService.getAllUsers();
        assertEquals(userList, users);
    }

    @Test
    void getUserById() throws UserOutputException {
        User user = new User(1, "Pavel", "Sakovic", 34);
        when(userRepositoryMock.findById(Mockito.anyInt())).thenReturn(Optional.ofNullable(user));
        assertEquals(user, usersService.getUserById(1));
    }

    @Test
    void deleteAllUsers() {
        usersService.deleteAllUsers();
        usersService.deleteAllUsers();
        verify(userRepositoryMock, times(2)).deleteAll();
    }
}