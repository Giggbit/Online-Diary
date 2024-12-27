package com.diary.online_diary.service;

import com.diary.online_diary.model.Tag;
import com.diary.online_diary.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TagService {
    @Autowired
    private TagRepository tagRepository;

    public Tag createTag(Tag tag) {
        if (tagRepository.findByName(tag.getName()).isPresent()) {
            throw new IllegalArgumentException("Tag already exists with name: " + tag.getName());
        }
        return tagRepository.save(tag);
    }

    public Optional<Tag> getTagByName(String name) {
        return tagRepository.findByName(name);
    }

    public void deleteTag(Long tagId) {
        tagRepository.deleteById(tagId);
    }
}
