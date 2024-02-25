package com.admiral26.noteapponline1.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.admiral26.movieappmvvmauth.util.ResultWrapper
import com.admiral26.noteapponline1.core.model.request.NoteRequest
import com.admiral26.noteapponline1.core.model.respons.NoteResponse
import com.admiral26.noteapponline1.core.model.respons.NoteResponseItem
import com.admiral26.noteapponline1.core.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModelImp @Inject constructor(
    private val repository: NoteRepository
) : DetailViewModel, ViewModel() {

    private val _noteLd = MutableLiveData<NoteResponseItem?>()
    private val _error = MutableLiveData<String>()

    override val noteLd: LiveData<NoteResponseItem?> = _noteLd
    override val errorLd: LiveData<String> = _error

    override fun getItemNote(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = repository.loadNoteItem(id)) {
                is ResultWrapper.Success -> {
                    _noteLd.postValue(result.response)
                }

                is ResultWrapper.NetworkError -> {
                    _error.postValue("Network Error")
                }

                is ResultWrapper.ErrorResponse -> {
                    _error.postValue("Error")
                }
            }
        }
    }

    override fun createNote(body: NoteRequest) {
        viewModelScope.launch(Dispatchers.IO){
            when (val result = repository.createNote(body)) {
                is ResultWrapper.Success -> {
                    _noteLd.postValue(result.response)
                }

                is ResultWrapper.NetworkError -> {
                    _error.postValue("Network Error")
                }

                is ResultWrapper.ErrorResponse -> {
                    _error.postValue("Error")
                }
            }
        }
    }
}