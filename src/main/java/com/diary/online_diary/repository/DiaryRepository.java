package com.diary.online_diary.repository;

import com.diary.online_diary.model.Diary;
import com.diary.online_diary.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DiaryRepository extends JpaRepository<Diary, Long> {
    List<Diary> findByUserId(Long userId);
    List<Diary> findByTagsContaining(Tag tag);

    @Query("SELECT d FROM Diary d JOIN d.tags t WHERE t.id = :tagId")
    List<Diary> findByTagId(@Param("tagId") Long tagId);
}
