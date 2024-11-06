package com.diary.services;

import com.diary.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Mock
    private EntityManager entityManager;

    @Mock
    private EntityTransaction entityTransaction;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        when(entityManager.getTransaction()).thenReturn(entityTransaction);
    }

    @Test
    public void testGetAllUsers() {
        List<User> expectedUsers = new ArrayList<>();
        TypedQuery<User> mockQuery = mock(TypedQuery.class);

        when(entityManager.createQuery("SELECT u FROM User u", User.class)).thenReturn(mockQuery);
        when(mockQuery.getResultList()).thenReturn(expectedUsers);
        List<User> result = userService.getAllUsers();

        assertEquals(expectedUsers, result);
        verify(entityManager).createQuery("SELECT u FROM User u", User.class);
        verify(mockQuery).getResultList();
    }

    @Test
    public void testGetUserById() {
        User expectedUser = new User();
        when(entityManager.find(User.class, 1L)).thenReturn(expectedUser);
        User result = userService.getUserById(1L);
        assertEquals(expectedUser, result);
        verify(entityManager).find(User.class, 1L);
    }

    @Test
    public void testCreateUser() {
        User user = new User();
        User result = userService.createUser(user);
        assertEquals(user, result);
        verify(entityTransaction).begin();
        verify(entityManager).persist(user);
        verify(entityTransaction).commit();
    }

    @Test
    public void testUpdateUser() {
        User existingUser = new User();
        existingUser.setName("Old Name");
        User updatedUser = new User();
        updatedUser.setName("New Name");
        when(entityManager.find(User.class, 1L)).thenReturn(existingUser);

        User result = userService.updateUser(1L, updatedUser);
        assertEquals("New Name", result.getName());
        verify(entityTransaction).begin();
        verify(entityManager).merge(existingUser);
        verify(entityTransaction).commit();
    }

    @Test
    public void testDeleteUser() {
        User user = new User();
        when(entityManager.find(User.class, 1L)).thenReturn(user);
        userService.deleteUser(1L);
        verify(entityTransaction).begin();
        verify(entityManager).remove(user);
        verify(entityTransaction).commit();
    }
}
