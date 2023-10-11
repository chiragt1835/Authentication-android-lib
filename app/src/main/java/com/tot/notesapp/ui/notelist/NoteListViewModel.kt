package com.tot.notesapp.ui.notelist


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.tot.notesapp.data.local.NotesData
import com.tot.notesapp.db.NotesRepository
import kotlinx.coroutines.launch

class NoteListViewModel(private val notesRepository: NotesRepository) : ViewModel() {


    var notesList = MutableLiveData<List<NotesData>>()

    fun getNoteList() = viewModelScope.launch {
        notesList.postValue(notesRepository.getList())
    }

}

class NoteListModelFactory(private val notesRepository: NotesRepository) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NoteListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return NoteListViewModel(notesRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}



