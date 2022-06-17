package com.nextory.testapp.ui.utils

sealed class Screen(val route: String) {
    object BookList : Screen("book_list")
    object BookDetails : Screen("book_details")
}