package com.diary.controllers;

import com.diary.models.Tag;
import com.diary.services.TagService;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/api/tags")
public class TagController {

    private final TagService tagService;

    public TagController() {
        this.tagService = new TagService(); // Инициализация сервиса
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Tag> getAllTags() {
        return tagService.getAllTags();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Tag getTagById(@PathParam("id") Long id) {
        return tagService.getTagById(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Tag createTag(Tag tag) {
        return tagService.createTag(tag);
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Tag updateTag(@PathParam("id") Long id, Tag updatedTag) {
        return tagService.updateTag(id, updatedTag);
    }

    @DELETE
    @Path("/{id}")
    public void deleteTag(@PathParam("id") Long id) {
        tagService.deleteTag(id);
    }
}
