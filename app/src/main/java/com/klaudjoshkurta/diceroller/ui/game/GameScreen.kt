package com.klaudjoshkurta.diceroller.ui.game

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.klaudjoshkurta.diceroller.R
import com.klaudjoshkurta.diceroller.ui.game.composables.DiceWithButtonAndImage
import com.klaudjoshkurta.diceroller.ui.theme.DiceRollerTheme

@Composable
fun GameScreen() {
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->

        var result by remember { mutableIntStateOf(1) }
        val imageResource = when (result) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            DiceWithButtonAndImage(
                onRoll = { result = (1..6).random() },
                imageResource = imageResource,
                contentDescription = result.toString()
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GameScreenPreview() {
    DiceRollerTheme {
        GameScreen()
    }
}