package com.diary.online_diary.controller;

import com.diary.online_diary.model.Tag;
import com.diary.online_diary.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tags")
public class TagController {
    @Autowired
    private TagService tagService;

    @PostMapping
    public Tag createTag(@RequestBody Tag tag) {
        return tagService.createTag(tag);
    }

    @GetMapping("/{name}")
    public Tag getTagByName(@PathVariable String name) {
        return tagService.getTagByName(name).orElseThrow(() -> new RuntimeException("Tag not found"));
    }

    @DeleteMapping("/{id}")
    public void deleteTag(@PathVariable Long id) {
        tagService.deleteTag(id);
    }
}
