package com.example.myquoteapp.data

import com.example.myquoteapp.R

data class QuoteCategory(
    val title: String,
    val imageRes: Int
)

val allCategories = listOf(
    QuoteCategory("Inspiring words", R.drawable.inspiring),
    QuoteCategory("Motivational quote", R.drawable.motivational),
    QuoteCategory("Life quote", R.drawable.life),
    QuoteCategory("Love quote", R.drawable.love),
    QuoteCategory("Friendship quote", R.drawable.friendship),
    QuoteCategory("Happiness quote", R.drawable.happiness),
    QuoteCategory("Success quote", R.drawable.success),
    QuoteCategory("Wisdom quote", R.drawable.wisdom)
)