package com.vurgun.ewa.presentation.games.match.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import domain.model.GameConfig
import domain.model.TextElement
import org.koin.androidx.compose.koinViewModel

@Composable
fun MatchGameScreen(
    game: GameConfig,
    viewModel: AndroidMatchGameViewModel = koinViewModel()
) {
    val textElements by viewModel.textElements.collectAsState()

    val primaryLanguage = "en"
    val secondaryLanguage = "tr"

    LaunchedEffect(game) {
        viewModel.loadTextElements(game)
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = game.names[secondaryLanguage] ?: "EWA: Eșleștirme Oyunu",
            fontSize = 24.sp,
            modifier = Modifier.padding(8.dp)
        )
        Text(
            text = game.descriptions[secondaryLanguage] ?: "EWA: İngilizce ve Türkçe kelimeleri eşleştirin.",
            fontSize = 20.sp,
            modifier = Modifier.padding(8.dp)
        )
        FlowLayout(textElements, isPrimary = true)
        Spacer(modifier = Modifier.height(16.dp))
        FlowLayout(textElements, isPrimary = false)
        Button(onClick = { viewModel.nextLevel() }) {
            Text(text = "Next Level")
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FlowLayout(textElements: List<TextElement>, isPrimary: Boolean = true) {
    FlowRow {
        textElements.forEach { textElement ->
            val translation = if (isPrimary) textElement.primaryText else textElement.secondaryText
            Text(
                text = translation,
                fontSize = 16.sp,
                modifier = Modifier
                    .padding(8.dp)
                    .background(Color.LightGray)
                    .clickable { /* Handle click event */ }
            )
        }
    }
}
