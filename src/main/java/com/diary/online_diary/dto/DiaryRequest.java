package com.diary.online_diary.dto;

import java.util.List;

public class DiaryRequest {
    private String title;
    private String content;
    private Long userId;
    private List<Long> tagIds;
    private boolean archived;

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getContent() { return content; }

    public void setContent(String content) { this.content = content; }

    public Long getUserId() { return userId; }

    public void setUserId(Long userId) { this.userId = userId; }

    public List<Long> getTagIds() { return tagIds; }

    public void setTagIds(List<Long> tagIds) { this.tagIds = tagIds; }

    public boolean isArchived() { return archived; }

    public void setArchived(boolean archived) { this.archived = archived; }
}
