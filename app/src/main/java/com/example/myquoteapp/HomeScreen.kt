package com.example.myquoteapp.ui.screens

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myquoteapp.QuoteViewModel
import com.example.myquoteapp.data.sampleQuotes
import java.time.format.TextStyle


@Composable
fun HomeScreen(navController: NavController, viewModel: QuoteViewModel) {
    var selectedTab by remember { mutableStateOf(0) }
    val tabs = listOf("Home", "Search", "Profile")
    val trendingQuotes by viewModel.trendingQuotes.collectAsState()

    Scaffold(
        bottomBar = {
            NavigationBar {
                tabs.forEachIndexed { index, tab ->
                    NavigationBarItem(
                        icon = {
                            when (tab) {
                                "Home" -> Icon(Icons.Default.Home, contentDescription = null)
                                "Search" -> Icon(Icons.Default.Search, contentDescription = null)
                                "Profile" -> Icon(Icons.Default.Person, contentDescription = null)
                            }
                        },
                        label = { Text(tab) },
                        selected = selectedTab == index,
                        onClick = {
                            selectedTab = index
                            when (tab) {
                                "Home" -> navController.navigate("home") {
                                    popUpTo("home") { inclusive = true }
                                    launchSingleTop = true
                                }
                                "Search" -> navController.navigate("category_list") {
                                    popUpTo("home") { inclusive = false }
                                    launchSingleTop = true
                                }
                                "Profile" -> navController.navigate("profile") {
                                    popUpTo("home") { inclusive = false }
                                    launchSingleTop = true
                                }
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
            ) {
                Text(
                    text = "Quotations",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.Center),
                    textAlign = TextAlign.Center
                )
                IconButton(
                    onClick = { navController.navigate("admin") },
                    modifier = Modifier.align(Alignment.CenterEnd)
                ) {
                    Icon(Icons.Default.Add, contentDescription = "Admin")
                }
            }

            Text("Trending", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))

            if (trendingQuotes.isEmpty()) {
                Text(
                    text = "No trending quotes found.",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    textAlign = TextAlign.Center,
                    color = Color.Gray
                )
            } else {
                LazyRow {
                    items(trendingQuotes) { quote ->
                        Card(
                            modifier = Modifier
                                .width(180.dp)
                                .height(150.dp)
                                .padding(end = 8.dp),
                            colors = CardDefaults.cardColors(containerColor = Color(0xFFE3F2FD))
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(12.dp),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = "\"${quote.text}\"",
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    textAlign = TextAlign.Center,
                                    maxLines = 3
                                )
                                Spacer(modifier = Modifier.height(6.dp))
                                Text(
                                    text = "- ${quote.author}",
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Light,
                                    textAlign = TextAlign.Center
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = "❤️ ${quote.likes}",
                                    fontSize = 11.sp,
                                    color = Color.Gray
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            Text("Categories", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))

            val categories = listOf("Love", "Friendship", "Happiness", "Success", "Wisdom", "All")
            LazyRow {
                items(categories) {
                    AssistChip(
                        onClick = { /* TODO: Implement filtering */ },
                        label = { Text(it) },
                        modifier = Modifier.padding(end = 8.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

              Text("All Quotes", fontSize = 20.sp,
                  fontWeight = FontWeight.Bold,
                  style = androidx.compose.ui.text.TextStyle(textDecoration = TextDecoration.Underline),
                  modifier = Modifier
                      .clickable {
                          navController.navigate("quote_list")
                      }
                  )


                Spacer(modifier = Modifier.height(8.dp))

                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(sampleQuotes) { quote ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(220.dp),
                            colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5))
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(16.dp),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = "\"${quote.text}\"",
                                    fontSize = 14.sp,
                                    textAlign = TextAlign.Center
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = "- ${quote.author}",
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Light,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }
                }
            }
        }
    }

