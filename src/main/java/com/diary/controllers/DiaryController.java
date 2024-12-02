package com.diary.controllers;

import com.diary.models.Diary;
import com.diary.services.DiaryService;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/diaries")
public class DiaryController {

    private final DiaryService diaryService;

    public DiaryController() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("diary-unit");
        this.diaryService = new DiaryService(emf);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Diary> getAllDiaries() {
        return diaryService.getAllDiaries();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Diary getDiaryById(@PathParam("id") Long id) {
        return diaryService.getDiaryById(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Diary createDiary(Diary diary) {
        return diaryService.createDiary(diary);
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Diary updateDiary(@PathParam("id") Long id, Diary updatedDiary) {
        return diaryService.updateDiary(id, updatedDiary);
    }

    @DELETE
    @Path("/{id}")
    public void deleteDiary(@PathParam("id") Long id) {
        diaryService.deleteDiary(id);
    }
}
