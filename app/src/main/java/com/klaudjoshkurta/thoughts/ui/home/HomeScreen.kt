@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.klaudjoshkurta.thoughts.ui.home

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.klaudjoshkurta.thoughts.ui.home.composables.InputSheet
import com.klaudjoshkurta.thoughts.ui.theme.ThoughtsTheme

@Composable
fun HomeScreen(
    onFullScreenClick: () -> Unit,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope
) {

    var value by remember { mutableStateOf("") }

    Scaffold(
        bottomBar = {
            with(sharedTransitionScope) {
                InputSheet(
                    value = value,
                    onValueChange = { value = it },
                    onFullScreenClick = onFullScreenClick,
                    onCameraClick = {},
                    onVoiceClick = {},
                    modifier = Modifier.Companion.sharedElement(
                        sharedTransitionScope.rememberSharedContentState(key = "inputSheet"),
                        animatedVisibilityScope = animatedContentScope,
                    )
                )
            }
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