package com.klaudjoshkurta.diceroller.ui.game.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.klaudjoshkurta.diceroller.R
import com.klaudjoshkurta.diceroller.ui.theme.DiceRollerTheme

@Composable
fun DiceWithButtonAndImage(
    modifier: Modifier = Modifier,
    onRoll: () -> Unit = {},
    imageResource: Int = R.drawable.dice_1,
    contentDescription: String = "1"
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = imageResource),
            contentDescription = contentDescription
        )
        Button(
            onClick = onRoll
        ) {
            Text(text = stringResource(R.string.roll))
        }
    }
}

@Preview(widthDp = 412, showBackground = true)
@Composable
fun DiceWithButtonAndImagePreview() {
    DiceRollerTheme {
        DiceWithButtonAndImage()
    }
}