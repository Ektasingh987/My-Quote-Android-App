package com.example.myquoteapp

import androidx.lifecycle.ViewModel
import com.example.myquoteapp.data.dummyTrendingQuotes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class QuoteViewModel : ViewModel() {


    // State holders
    private val _allQuotes = MutableStateFlow<List<Quote>>(emptyList())
    val allQuotes: StateFlow<List<Quote>> = _allQuotes.asStateFlow()

    private val _trendingQuotes = MutableStateFlow<List<Quote>>(dummyTrendingQuotes)
    val trendingQuotes: StateFlow<List<Quote>> = _trendingQuotes.asStateFlow()

    init {
        loadDummyQuotes()
    }

    private fun loadDummyQuotes() {
        // In real app, this will come from Firestore.
        _allQuotes.value = dummyTrendingQuotes

        // You can still simulate trending
        _trendingQuotes.value = dummyTrendingQuotes.sortedByDescending { it.likes }.take(10)
    }
}
