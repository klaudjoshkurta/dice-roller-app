package com.klaudjoshkurta.thoughts.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.klaudjoshkurta.thoughts.ui.home.composables.InputSheet
import com.klaudjoshkurta.thoughts.ui.theme.ThoughtsTheme

@Composable
fun HomeScreen() {
    Scaffold(
        bottomBar = {
            InputSheet(
                value = "",
                onValueChange = {},
                onFullScreenClick = {},
                onCameraClick = {},
                onVoiceClick = {}
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {

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