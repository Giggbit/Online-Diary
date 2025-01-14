package com.diary.online_diary.service;

import com.diary.online_diary.dto.DiaryRequest;
import com.diary.online_diary.model.Diary;
import com.diary.online_diary.model.Tag;
import com.diary.online_diary.model.User;
import com.diary.online_diary.repository.DiaryRepository;
import com.diary.online_diary.repository.TagRepository;
import com.diary.online_diary.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DiaryService {
    private final DiaryRepository diaryRepository;
    private final UserRepository userRepository;
    private final TagRepository tagRepository;

    public DiaryService(DiaryRepository diaryRepository, UserRepository userRepository, TagRepository tagRepository) {
        this.diaryRepository = diaryRepository;
        this.userRepository = userRepository;
        this.tagRepository = tagRepository;
    }

    @Transactional
    public void createDiary(DiaryRequest request) {
        User user = userRepository.findById(request.getUserId()).orElseThrow(() -> new IllegalArgumentException("User not found"));

        Diary diary = new Diary();
        diary.setTitle(request.getTitle());
        diary.setContent(request.getContent());
        diary.setUser(user);

        List<Tag> tags = tagRepository.findAllById(request.getTagIds());
        diary.setTags(tags);
        diaryRepository.save(diary);
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

    public List<Diary> findDiariesByTag(Long tagId) {
        return diaryRepository.findByTagId(tagId);
    }
}
