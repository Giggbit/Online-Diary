package com.diary.services;

import com.diary.models.Diary;
import com.diary.repositories.DiaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class DiaryService {
    @Autowired
    private DiaryRepository diaryRepository;

    public List<Diary> getAllDiaries() {
        return diaryRepository.findAll();
    }

    public Diary getDiaryById(Long id) {
        return diaryRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Diary not found!"));
    }

    public Diary createDiary(Diary diary) {
        diary.setCreatedAt(LocalDateTime.now());
        diary.setUpdatedAt(LocalDateTime.now());
        return diaryRepository.save(diary);
    }

    public Diary updateDiary(Long id, Diary updatedDiary) {
        Diary diary = diaryRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Diary not found!"));
        diary.setTitle(updatedDiary.getTitle());
        diary.setContent(updatedDiary.getContent());
        diary.setTags(updatedDiary.getTags());
        diary.setUpdatedAt(LocalDateTime.now());
        return diaryRepository.save(diary);
    }

    public void deleteDiary(Long id) {
        diaryRepository.deleteById(id);
    }
}
