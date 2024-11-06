package com.diary.services;

import com.diary.models.Diary;
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

public class DiaryServiceTest {

    @Mock
    private EntityManager entityManager;

    @Mock
    private EntityTransaction entityTransaction;

    @InjectMocks
    private DiaryService diaryService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        when(entityManager.getTransaction()).thenReturn(entityTransaction);
    }

    @Test
    public void testGetAllDiaries() {
        List<Diary> expectedDiaries = new ArrayList<>();
        TypedQuery<Diary> mockQuery = mock(TypedQuery.class);
        when(entityManager.createQuery("SELECT d FROM Diary d", Diary.class)).thenReturn(mockQuery);
        when(mockQuery.getResultList()).thenReturn(expectedDiaries);

        List<Diary> result = diaryService.getAllDiaries();

        assertEquals(expectedDiaries, result);
        verify(entityManager).createQuery("SELECT d FROM Diary d", Diary.class);
        verify(mockQuery).getResultList();
    }

    @Test
    public void testGetDiaryById() {
        Diary expectedDiary = new Diary();
        when(entityManager.find(Diary.class, 1L)).thenReturn(expectedDiary);

        Diary result = diaryService.getDiaryById(1L);
        assertEquals(expectedDiary, result);
        verify(entityManager).find(Diary.class, 1L);
    }

    @Test
    public void testCreateDiary() {
        Diary diary = new Diary();
        Diary result = diaryService.createDiary(diary);
        assertEquals(diary, result);
        verify(entityTransaction).begin();
        verify(entityManager).persist(diary);
        verify(entityTransaction).commit();
    }

    @Test
    public void testUpdateDiary() {
        Diary existingDiary = new Diary();
        existingDiary.setTitle("Old Title");
        Diary updatedDiary = new Diary();
        updatedDiary.setTitle("New Title");
        when(entityManager.find(Diary.class, 1L)).thenReturn(existingDiary);

        Diary result = diaryService.updateDiary(1L, updatedDiary);
        assertEquals("New Title", result.getTitle());
        verify(entityTransaction).begin();
        verify(entityManager).merge(existingDiary);
        verify(entityTransaction).commit();
    }

    @Test
    public void testDeleteDiary() {
        Diary diary = new Diary();
        when(entityManager.find(Diary.class, 1L)).thenReturn(diary);

        diaryService.deleteDiary(1L);
        verify(entityTransaction).begin();
        verify(entityManager).remove(diary);
        verify(entityTransaction).commit();
    }
}
