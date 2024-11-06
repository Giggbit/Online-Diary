package com.diary.services;

import com.diary.models.Tag;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class TagService {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("diary-unit");

    public List<Tag> getAllTags() {
        EntityManager em = emf.createEntityManager();
        List<Tag> tags = em.createQuery("SELECT t FROM Tag t", Tag.class).getResultList();
        em.close();
        return tags;
    }

    public Tag getTagById(Long id) {
        EntityManager em = emf.createEntityManager();
        Tag tag = em.find(Tag.class, id);
        em.close();
        return tag;
    }

    public Tag createTag(Tag tag) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(tag);
        em.getTransaction().commit();
        em.close();
        return tag;
    }

    public Tag updateTag(Long id, Tag updatedTag) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Tag existingTag = em.find(Tag.class, id);
        if (existingTag != null) {
            existingTag.setName(updatedTag.getName());
            em.merge(existingTag);
        }
        em.getTransaction().commit();
        em.close();
        return existingTag;
    }

    public void deleteTag(Long id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Tag tag = em.find(Tag.class, id);
        if (tag != null) {
            em.remove(tag);
        }
        em.getTransaction().commit();
        em.close();
    }
}
