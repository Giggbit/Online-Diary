package com.diary.services;

import com.diary.models.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

/**
 * Сервис для управления пользователями.
 * Реализует CRUD операции с использованием JPA.
 */
public class UserService {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("diary-unit");

    /**
     * Получает всех пользователей из базы данных.
     *
     * @return список всех пользователей.
     */
    public List<User> getAllUsers() {
        EntityManager em = emf.createEntityManager();
        List<User> users = em.createQuery("SELECT u FROM User u", User.class).getResultList();
        em.close();
        return users;
    }

    /**
     * Получает пользователя по его идентификатору.
     *
     * @param id идентификатор пользователя.
     * @return пользователь или null, если пользователь не найден.
     */
    public User getUserById(Long id) {
        EntityManager em = emf.createEntityManager();
        User user = em.find(User.class, id);
        em.close();
        return user;
    }

    /**
     * Создает нового пользователя.
     *
     * @param user объект пользователя для сохранения.
     * @return сохраненный пользователь.
     */
    public User createUser(User user) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
        em.close();
        return user;
    }

    /**
     * Обновляет данные существующего пользователя.
     *
     * @param id          идентификатор пользователя.
     * @param updatedUser объект с обновленными данными.
     * @return обновленный пользователь или null, если пользователь не найден.
     */
    public User updateUser(Long id, User updatedUser) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        User user = em.find(User.class, id);
        if (user != null) {
            user.setName(updatedUser.getName());
            user.setEmail(updatedUser.getEmail());
            user.setPassword(updatedUser.getPassword());
            em.merge(user);
        }
        em.getTransaction().commit();
        em.close();
        return user;
    }

    /**
     * Удаляет пользователя по его идентификатору.
     *
     * @param id идентификатор пользователя.
     */
    public void deleteUser(Long id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        User user = em.find(User.class, id);
        if (user != null) {
            em.remove(user);
        }
        em.getTransaction().commit();
        em.close();
    }
}
