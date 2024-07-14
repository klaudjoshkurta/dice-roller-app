package com.klaudjoshkurta.thoughts.ui.home.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.MoreHoriz
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.klaudjoshkurta.thoughts.R
import com.klaudjoshkurta.thoughts.data.model.Thought
import com.klaudjoshkurta.thoughts.ui.theme.ThoughtsTheme

@Composable
fun ThoughtItem(
    modifier: Modifier = Modifier,
    thought: Thought = Thought(text = "Hello world"),
    onDelete: (Thought) -> Unit
) {
    
    var expanded by remember { mutableStateOf(false) }
    
    Column(
        modifier = modifier
    ) {
        Text(
            text = thought.text,
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.onBackground,
            lineHeight = 24.sp
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.weight(1f))
            Box(
                modifier = Modifier.fillMaxSize().wrapContentSize(Alignment.TopEnd),
            ) {
                IconButton(onClick = { expanded = !expanded }) {
                    Icon(
                        imageVector = Icons.Outlined.MoreHoriz,
                        contentDescription = stringResource(R.string.more_options_button),
                        modifier = Modifier.size(20.dp)
                    )
                }
                DropdownMenu(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.background)
                        .border(1.dp, MaterialTheme.colorScheme.outlineVariant),
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    DropdownMenuItem(
                        text = { Text(text = "Delete thought") },
                        onClick = { onDelete(thought) },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Outlined.Delete,
                                contentDescription = null,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    )
                }
            }
        }
    }
}

@Preview(widthDp = 412, showBackground = true)
@Composable
fun ThoughtItemPreview() {
    ThoughtsTheme {
        ThoughtItem(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            thought = Thought(text = "Hello world"),
            onDelete = {}
        )
    }
}