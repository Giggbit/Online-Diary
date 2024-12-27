package com.diary.online_diary.repository;

import com.diary.online_diary.model.Diary;
import com.diary.online_diary.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiaryRepository extends JpaRepository<Diary, Long> {
    List<Diary> findByUserId(Long userId);
    List<Diary> findByTagsContaining(Tag tag);
}
