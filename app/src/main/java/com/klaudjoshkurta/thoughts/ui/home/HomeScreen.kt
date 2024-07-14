@file:OptIn(ExperimentalMaterial3Api::class)

package com.klaudjoshkurta.thoughts.ui.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.klaudjoshkurta.thoughts.R
import com.klaudjoshkurta.thoughts.data.model.Thought
import com.klaudjoshkurta.thoughts.ui.home.composables.InputSheet
import com.klaudjoshkurta.thoughts.ui.home.composables.ThoughtItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
) {

    var value by remember { mutableStateOf("") }
    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current
    val scrollState = rememberLazyListState()
    val titleYPosition = remember { mutableFloatStateOf(0f) }
    val showTopAppBarTitle by remember {
        derivedStateOf {
            titleYPosition.floatValue < 0f // Check if the title has scrolled off the screen
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    AnimatedVisibility(
                        visible = showTopAppBarTitle,
                        enter = fadeIn(),
                        exit = fadeOut()
                    ) {
                        Text(
                            text = stringResource(R.string.thoughts_title),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        },
        bottomBar = {
            InputSheet(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp)
                    .shadow(
                        elevation = 10.dp,
                        RoundedCornerShape(topStart = 18.dp, topEnd = 18.dp)
                    )
                    .background(MaterialTheme.colorScheme.background)
                    .border(
                        1.dp,
                        MaterialTheme.colorScheme.outlineVariant,
                        RoundedCornerShape(topStart = 18.dp, topEnd = 18.dp)
                    )
                    .navigationBarsPadding()
                    .imePadding(),
                value = value,
                onValueChange = { value = it },
                onSaveClick = {
                    keyboardController?.hide()
                    focusRequester.freeFocus()
                },
                focusRequester = focusRequester,
                enabled = value.isNotEmpty(),
            )
        },
        modifier = modifier
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            val thoughts = listOf(
                Thought(text = "The sunset today was incredible. I need to find more time to appreciate nature."),
                Thought(text = "I had the best cup of coffee this morning. Need to remember which beans they were."),
                Thought(text = "Feeling overwhelmed with work, but taking a deep breath helps."),
                Thought(text = "So excited about my upcoming vacation! Can't wait to explore."),
                Thought(text = "Just finished a great book. Time to find my next adventure."),
                Thought(text = "Had a wonderful conversation with a friend today. Gratitude."),
                Thought(text = "Trying to learn a new recipe tonight. Wish me luck!"),
                Thought(text = "Feeling a bit stressed, but reminding myself it's okay to not be okay."),
                Thought(text = "Made a small progress on a personal project. Feels good to be moving forward."),
                Thought(text = "Random thought: I wonder what it would be like to live in a different country."),
                Thought(text = "Feeling inspired by a documentary I watched. Time to take action."),
            )

            ThoughtsList(
                thoughtsList = flowOf(thoughts),
                onDelete = {},
                scrollState = scrollState,
                titleYPosition = titleYPosition
            )
        }
    }
}

@Composable
private fun ThoughtsList(
    modifier: Modifier = Modifier,
    thoughtsList: Flow<List<Thought>> = flowOf(listOf()),
    onDelete: (Thought) -> Unit,
    scrollState: LazyListState,
    titleYPosition: MutableState<Float>
) {
    val thoughts = thoughtsList.collectAsState(initial = listOf()).value
    
    LazyColumn(
        state = scrollState,
        modifier = modifier.fillMaxSize()
    ) {
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 60.dp)
                    .onGloballyPositioned { coordinates ->
                        titleYPosition.value = coordinates.positionInRoot().y
                    },
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = stringResource(R.string.thoughts_title),
                    fontSize = 24.sp,
                    color = MaterialTheme.colorScheme.onBackground,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
                Text(
                    text = "${thoughts.size} thoughts",
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onBackground,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
        items(thoughts, key = { it.id }) { thought -> 
            ThoughtItem(
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 8.dp),
                thought = thought,
                onDelete = onDelete
            )
        }
    }
}