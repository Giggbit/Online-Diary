package com.diary.controllers;

import com.diary.models.Diary;
import com.diary.services.DiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/diaries")
public class DiaryController {
    @Autowired
    private DiaryService diaryService;

    @GetMapping
    public List<Diary> getAllDiaries() {
        return diaryService.getAllDiaries();
    }

    @GetMapping("/{id}")
    public Diary getDiaryById(@PathVariable Long id) {
        return diaryService.getDiaryById(id);
    }

    @PostMapping
    public Diary createDiary(@RequestBody Diary diary) {
        return diaryService.createDiary(diary);
    }

    @PutMapping("/{id}")
    public Diary updateDiary(@PathVariable Long id, @RequestBody Diary updatedDiary) {
        return diaryService.updateDiary(id, updatedDiary);
    }

    @DeleteMapping("/{id}")
    public void deleteDiary(@PathVariable Long id) {
        diaryService.deleteDiary(id);
    }
}
