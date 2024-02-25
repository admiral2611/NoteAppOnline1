package com.admiral26.noteapponline1.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.admiral26.movieappmvvmauth.util.ResultWrapper
import com.admiral26.noteapponline1.core.model.respons.NoteResponse
import com.admiral26.noteapponline1.core.model.respons.NoteResponseItem
import com.admiral26.noteapponline1.core.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModelImp @Inject constructor(
    private val repository: NoteRepository
) : HomeViewModel, ViewModel() {

    private val _getNotes = MutableLiveData<NoteResponse?>()
    private val _deleteLd = MutableLiveData<NoteResponseItem?>()
    private val _error = MutableLiveData<String>()

    override val noteLd: LiveData<NoteResponse?> = _getNotes
    override val deleteLd: LiveData<NoteResponseItem?> = _deleteLd
    override val errorLd: LiveData<String> = _error

    override fun getNotes() {

        viewModelScope.launch(Dispatchers.IO) {
            when (val result = repository.loadNote()) {
                is ResultWrapper.Success -> {
                    _getNotes.postValue(result.response)
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

    override fun deleteNote(id:Int) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = repository.deleteNote(id)) {
                is ResultWrapper.Success -> {
                    _deleteLd.postValue(result.response)
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