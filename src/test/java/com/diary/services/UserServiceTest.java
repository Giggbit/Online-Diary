package com.diary.services;

import com.diary.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Тесты для класса UserService.
 */
public class UserServiceTest {

    @Mock
    private EntityManager entityManager;

    @Mock
    private EntityTransaction entityTransaction;

    @InjectMocks
    private UserService userService;

    /**
     * Инициализация моков перед каждым тестом.
     */
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("diary-unit");
        entityManager = emf.createEntityManager();

        when(entityManager.getTransaction()).thenReturn(entityTransaction);
    }

    /**
     * Тест получения всех пользователей.
     */
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

    /**
     * Тест получения пользователя по ID.
     */
    @Test
    public void testGetUserById() {
        User expectedUser = new User();
        when(entityManager.find(User.class, 1L)).thenReturn(expectedUser);
        User result = userService.getUserById(1L);
        assertEquals(expectedUser, result);
        verify(entityManager).find(User.class, 1L);
    }

    /**
     * Тест создания пользователя.
     */
    @Test
    public void testCreateUser() {
        User user = new User();
        User result = userService.createUser(user);
        assertEquals(user, result);
        verify(entityTransaction).begin();
        verify(entityManager).persist(user);
        verify(entityTransaction).commit();
    }

    /**
     * Тест обновления пользователя.
     */
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

    /**
     * Тест удаления пользователя.
     */
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
