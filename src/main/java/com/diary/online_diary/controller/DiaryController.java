package com.diary.online_diary.controller;

import com.diary.online_diary.dto.DiaryRequest;
import com.diary.online_diary.model.Diary;
import com.diary.online_diary.service.DiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/diaries")
public class DiaryController {
    @Autowired
    private DiaryService diaryService;

    @PostMapping
    public ResponseEntity<?> createDiary(@RequestBody DiaryRequest request) {
        diaryService.createDiary(request);
        return ResponseEntity.ok("Diary created successfully");
    }

    @GetMapping("/user/{userId}")
    public List<Diary> getDiariesByUser(@PathVariable Long userId) {
        return diaryService.getDiariesByUserId(userId);
    }

    @DeleteMapping("/{diaryId}")
    public void deleteDiary(@PathVariable Long diaryId) {
        diaryService.deleteDiary(diaryId);
    }

    @PutMapping("/{diaryId}")
    public Diary updateDiary(@PathVariable Long diaryId, @RequestBody Diary updatedDiary) {
        return diaryService.updateDiary(diaryId, updatedDiary);
    }

    @GetMapping("/{diaryId}")
    public void archiveDiary(@PathVariable Long diaryId) {
        diaryService.archiveDiary(diaryId);
    }

    @GetMapping("/by-tag/{tagId}")
    public ResponseEntity<List<Diary>> getDiariesByTag(@PathVariable Long tagId) {
        List<Diary> diaries = diaryService.findDiariesByTag(tagId);
        if (diaries.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(diaries);
    }
}
