package com.example.myquoteapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class Quote(
    val text: String = "",
    val author: String = "",
    val category: String = "",
    val likes: Int = 0
)

