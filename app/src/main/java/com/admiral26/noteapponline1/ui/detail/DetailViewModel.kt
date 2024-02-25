package com.admiral26.noteapponline1.ui.detail

import androidx.lifecycle.LiveData
import com.admiral26.noteapponline1.core.model.request.NoteRequest
import com.admiral26.noteapponline1.core.model.respons.NoteResponseItem

interface DetailViewModel {

    val noteLd:LiveData<NoteResponseItem?>
    val errorLd:LiveData<String>

    fun getItemNote(id:Int)
    fun createNote(body:NoteRequest)
}