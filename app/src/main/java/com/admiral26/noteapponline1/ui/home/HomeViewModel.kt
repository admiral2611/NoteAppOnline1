package com.admiral26.noteapponline1.ui.home

import androidx.lifecycle.LiveData
import com.admiral26.noteapponline1.core.model.respons.NoteResponse
import com.admiral26.noteapponline1.core.model.respons.NoteResponseItem

interface HomeViewModel {
    val noteLd:LiveData<NoteResponse?>
    val deleteLd:LiveData<NoteResponseItem?>
    val errorLd:LiveData<String>
    fun getNotes()
    fun deleteNote(id:Int)
}