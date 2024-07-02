package com.klaudjoshkurta.thoughts.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.klaudjoshkurta.thoughts.model.Thought
import com.klaudjoshkurta.thoughts.ui.theme.ThoughtsTheme

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {

    val thoughts by viewModel.allThoughts.observeAsState(emptyList())
    var text by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        LazyColumn(
            modifier = Modifier.weight(1f),
        ) {
            items(thoughts) { thought ->
                Text(
                    text = thought.text,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                value = text,
                onValueChange = { text = it },
                modifier = Modifier.weight(1f)
            )
            Button(
                onClick = {
                    viewModel.insert(Thought(text = text))
                    text = ""
                }
            ) {
                Text(text = "Add")
            }
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    ThoughtsTheme {
        HomeScreen()
    }
}