package com.klaudjoshkurta.thoughts.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.klaudjoshkurta.thoughts.model.Thought
import com.klaudjoshkurta.thoughts.repository.ThoughtsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: ThoughtsRepository
) : ViewModel() {

    val allThoughts: LiveData<List<Thought>> = repository.allThoughts.asLiveData()

    fun insert(thought: Thought) = viewModelScope.launch {
        repository.insert(thought)
    }

    fun delete(thought: Thought) = viewModelScope.launch {
        repository.delete(thought)
    }
}