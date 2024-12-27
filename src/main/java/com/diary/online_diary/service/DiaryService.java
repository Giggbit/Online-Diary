package com.diary.online_diary.service;

import com.diary.online_diary.model.Diary;
import com.diary.online_diary.repository.DiaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DiaryService {
    @Autowired
    private DiaryRepository diaryRepository;

    public Diary createDiary(Diary diary) {
        return diaryRepository.save(diary);
    }

    public List<Diary> getDiariesByUserId(Long userId) {
        return diaryRepository.findByUserId(userId);
    }

    public void deleteDiary(Long diaryId) {
        if (!diaryRepository.existsById(diaryId)) {
            throw new RuntimeException("Diary not found with id: " + diaryId);
        }
        diaryRepository.deleteById(diaryId);
    }

    public Diary updateDiary(Long diaryId, Diary updatedDiary) {
        Optional<Diary> existingDiary = diaryRepository.findById(diaryId);
        if (existingDiary.isEmpty()) {
            throw new RuntimeException("Diary not found with id: " + diaryId);
        }

        Diary diary = existingDiary.get();
        diary.setTitle(updatedDiary.getTitle());
        diary.setContent(updatedDiary.getContent());
        diary.setArchived(updatedDiary.isArchived());
        diary.setTags(updatedDiary.getTags());
        return diaryRepository.save(diary);
    }

    public void archiveDiary(Long diaryId) {
        Diary diary = diaryRepository.findById(diaryId).orElseThrow();
        diary.setArchived(true);
        diaryRepository.save(diary);
    }
}