package com.nextory.testapp.ui.bookdetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nextory.testapp.data.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookDetailsViewModel @Inject constructor(
    private val bookRepository: BookRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val bookId = savedStateHandle.get<Long>("bookId")?: -1L

    var book = bookRepository.getBookById(bookId)
        .stateIn(
            viewModelScope,
            SharingStarted.Lazily,
            null
        )

    fun bookmark() {
        val updatedBook = book.value?.copy(bookmarked = true)
        viewModelScope.launch {
            updatedBook?.let {
                bookRepository.bookmark(it)
            }
        }
    }




}
