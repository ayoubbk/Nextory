package com.nextory.testapp.ui.bookdetails

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nextory.testapp.data.Book
import com.nextory.testapp.data.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookDetailsViewModel @Inject constructor(
    private val bookRepository: BookRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    var book by mutableStateOf<Book?>(null)
        private set

    init {
        val bookId = savedStateHandle.get<Long>("bookId")!!
        if(bookId != -1L) {
            viewModelScope.launch {
                bookRepository.getBookById(bookId).let { book ->
                    this@BookDetailsViewModel.book = book
                }
            }
        }
    }


}
