package com.admiral26.noteapponline1.core.repository

import com.admiral26.movieappmvvmauth.util.ResultWrapper
import com.admiral26.noteapponline1.core.model.request.NoteRequest
import com.admiral26.noteapponline1.core.model.respons.NoteResponse
import com.admiral26.noteapponline1.core.model.respons.NoteResponseItem

interface NoteRepository {
    suspend fun loadNote():ResultWrapper<NoteResponse?,Any>
    suspend fun loadNoteItem(id:Int):ResultWrapper<NoteResponseItem?,Any>
    suspend fun createNote(data:NoteRequest):ResultWrapper<NoteResponseItem?,Any>
    suspend fun deleteNote(id:Int):ResultWrapper<NoteResponseItem?,Any>
}