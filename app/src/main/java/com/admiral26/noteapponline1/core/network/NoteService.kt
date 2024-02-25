package com.admiral26.noteapponline1.core.network

import com.admiral26.noteapponline1.core.model.request.NoteRequest
import com.admiral26.noteapponline1.core.model.respons.NoteResponse
import com.admiral26.noteapponline1.core.model.respons.NoteResponseItem
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface NoteService {
    @GET("/note")
    suspend fun loadNote(): Response<NoteResponse?>

    @GET("/note/{id}")
    suspend fun loadNoteItem(@Path("id") id: Int): Response<NoteResponseItem?>

    @POST("/note")
    suspend fun createNote(@Body data: NoteRequest): Response<NoteResponseItem?>
    @DELETE("/note/{id}")
    suspend fun deleteNote(@Path("id") id:Int): Response<NoteResponseItem?>
}