package com.diary.services;

import com.diary.models.Diary;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class DiaryService {
    private final EntityManagerFactory emf;

    public DiaryService(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public List<Diary> getAllDiaries() {
        EntityManager em = emf.createEntityManager();
        List<Diary> diaries = em.createQuery("SELECT d FROM Diary d", Diary.class).getResultList();
        em.close();
        return diaries;
    }

    public Diary getDiaryById(Long id) {
        EntityManager em = emf.createEntityManager();
        Diary diary = em.find(Diary.class, id);
        em.close();
        return diary;
    }

    public Diary createDiary(Diary diary) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(diary);
        em.getTransaction().commit();
        em.close();
        return diary;
    }

    public Diary updateDiary(Long id, Diary updatedDiary) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Diary diary = em.find(Diary.class, id);
        if (diary != null) {
            diary.setTitle(updatedDiary.getTitle());
            diary.setContent(updatedDiary.getContent());
            em.merge(diary);
        }
        em.getTransaction().commit();
        em.close();
        return diary;
    }

    public void deleteDiary(Long id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Diary diary = em.find(Diary.class, id);
        if (diary != null) {
            em.remove(diary);
        }
        em.getTransaction().commit();
        em.close();
    }
}
