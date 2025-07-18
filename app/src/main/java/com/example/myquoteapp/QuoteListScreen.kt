package com.example.myquoteapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myquoteapp.QuoteViewModel

@Composable
fun QuoteListScreen(navController: NavController, viewModel: QuoteViewModel) {
    val quotes by viewModel.allQuotes.collectAsState() // <-- This should match your ViewModel state flow

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(quotes) { quote ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "\"${quote.text}\"",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "- ${quote.author}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}
