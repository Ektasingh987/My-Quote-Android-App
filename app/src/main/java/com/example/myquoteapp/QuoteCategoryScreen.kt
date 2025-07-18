package com.example.myquoteapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.*

import androidx.navigation.NavController
import com.example.myquoteapp.data.allCategories


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuoteCategoryScreen(navController: NavController) {
    var searchQuery by remember { mutableStateOf(TextFieldValue("")) }

    val filteredCategories = allCategories.filter {
        it.title.contains(searchQuery.text, ignoreCase = true)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    TextField(
                        value = searchQuery,
                        onValueChange = { searchQuery = it },
                        placeholder = { Text("Search categories...") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )

                }
            )
        }
    ) { padding ->
        LazyColumn(modifier = Modifier.padding(padding)) {
            items(filteredCategories) { category ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Image(
                        painter = painterResource(id = category.imageRes),
                        contentDescription = null,
                        modifier = Modifier.size(64.dp)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text(text = category.title,
                            style = MaterialTheme.typography.bodyLarge)
                        Text(
                            text = "View details",
                            color = MaterialTheme.colorScheme.primary,
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.clickable {

                            }
                        )
                    }
                }
            }
        }
    }
}