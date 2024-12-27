package com.diary.online_diary.controller;

import com.diary.online_diary.model.Diary;
import com.diary.online_diary.service.DiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/diaries")
public class DiaryController {
    @Autowired
    private DiaryService diaryService;

    @PostMapping
    public Diary createDiary(@RequestBody Diary diary) {
        return diaryService.createDiary(diary);
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
}
