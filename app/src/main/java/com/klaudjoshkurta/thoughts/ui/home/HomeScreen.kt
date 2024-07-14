package com.klaudjoshkurta.thoughts.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
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

    Scaffold(
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
            )
        }
    }
}

@Composable
private fun ThoughtsList(
    modifier: Modifier = Modifier,
    thoughtsList: Flow<List<Thought>> = flowOf(listOf()),
    onDelete: (Thought) -> Unit,
) {
    val thoughts = thoughtsList.collectAsState(initial = listOf()).value
    
    LazyColumn(
        modifier = modifier.fillMaxSize()
    ) {
        items(thoughts, key = { it.id }) { thought -> 
            ThoughtItem(
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 8.dp),
                thought = thought,
                onDelete = onDelete
            )
        }
    }
}