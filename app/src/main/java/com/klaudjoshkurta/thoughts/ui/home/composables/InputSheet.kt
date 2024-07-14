package com.klaudjoshkurta.thoughts.ui.home.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.klaudjoshkurta.thoughts.R

@Composable
fun InputSheet(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    onSaveClick: () -> Unit,
    focusRequester: FocusRequester,
    enabled: Boolean = false
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 20.dp)
                .focusRequester(focusRequester),
            textStyle = TextStyle(
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.onBackground
            ),
            decorationBox = { innerTextField ->
                if (value.isEmpty()) {
                    Text(
                        text = stringResource(R.string.thought_entry_hint),
                        color = MaterialTheme.colorScheme.onBackground.copy(0.6f),
                        fontSize = 20.sp
                    )
                }
                innerTextField()
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = onSaveClick,
            enabled = enabled
        ) {
            Text(text = stringResource(R.string.save))
        }
    }
}