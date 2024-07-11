package com.klaudjoshkurta.thoughts.ui.home.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Camera
import androidx.compose.material.icons.outlined.Mic
import androidx.compose.material.icons.outlined.OpenInFull
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.klaudjoshkurta.thoughts.ui.theme.ThoughtsTheme

@Composable
fun InputSheet(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    onFullScreenClick: () -> Unit,
    onCameraClick: () -> Unit,
    onVoiceClick: () -> Unit
) {

    Column(
        modifier = modifier
            .fillMaxWidth()
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
            .imePadding()
    ) {
        Row(
            verticalAlignment = Alignment.Top,
        ) {
            BasicTextField(
                value = value,
                onValueChange = onValueChange,
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 16.dp)
                    .weight(1f),
                textStyle = TextStyle(
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.onBackground
                ),
                decorationBox = { innerTextField ->
                    if (value.isEmpty()) {
                        Text(
                            text = "What's on your mind?",
                            color = MaterialTheme.colorScheme.onBackground.copy(0.6f),
                            fontSize = 20.sp
                        )
                    }
                    innerTextField()
                }
            )
            IconButton(onClick = onFullScreenClick) {
                Icon(
                    imageVector = Icons.Outlined.OpenInFull,
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            Surface(
                shape = RoundedCornerShape(200.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    IconButton(onClick = onVoiceClick) {
                        Icon(imageVector = Icons.Outlined.Mic, contentDescription = null)
                    }
                    IconButton(onClick = onCameraClick) {
                        Icon(imageVector = Icons.Outlined.Camera, contentDescription = null)
                    }
                }
            }
        }
    }
}

@Preview(widthDp = 412)
@Composable
fun InputSheetPreview() {
    ThoughtsTheme {
        InputSheet(
            value = "",
            onValueChange = {},
            onFullScreenClick = {},
            onCameraClick = {},
            onVoiceClick = {}
        )
    }
}