package com.nextory.testapp.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BookRepository @Inject constructor(
    private val bookDao: BookDao
) {
    fun observePagedBooks(pagingConfig: PagingConfig): Flow<PagingData<Book>> {
        return Pager(config = pagingConfig) {
            bookDao.observePagedBooks()
        }.flow
    }

    fun getBookById(id: Long): Flow<Book?> {
        return bookDao.getBookById(id)
    }

    suspend fun bookmark(book: Book) {
        bookDao.bookmark(book)
    }
}