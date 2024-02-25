package com.admiral26.noteapponline1.core.repository

import com.admiral26.movieappmvvmauth.util.ResultWrapper
import com.admiral26.movieappmvvmauth.util.parseResponse
import com.admiral26.noteapponline1.core.model.request.NoteRequest
import com.admiral26.noteapponline1.core.model.respons.NoteResponse
import com.admiral26.noteapponline1.core.model.respons.NoteResponseItem
import com.admiral26.noteapponline1.core.network.NoteService
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class NoteRepositoryImp @Inject constructor(
    private val service: NoteService
) : NoteRepository {
    override suspend fun loadNote(): ResultWrapper<NoteResponse?, Any> {
        return parseResponse(Dispatchers.IO) {
            service.loadNote()
        }
    }

    override suspend fun loadNoteItem(id: Int): ResultWrapper<NoteResponseItem?, Any> {
        return parseResponse(Dispatchers.IO) {
            service.loadNoteItem(id)
        }

    }

    override suspend fun createNote(data: NoteRequest): ResultWrapper<NoteResponseItem?, Any> {
        return parseResponse(Dispatchers.IO) {
            service.createNote(data)
        }
    }

    override suspend fun deleteNote(id: Int): ResultWrapper<NoteResponseItem?, Any> {
        return parseResponse(Dispatchers.IO) {
            service.deleteNote(id)
        }
    }
}