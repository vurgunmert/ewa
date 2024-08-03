package com.vurgun.ewa.presentation.games.matchgame.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import domain.model.TextElement
import org.koin.androidx.compose.koinViewModel

@Composable
fun MatchGameScreen(viewModel: AndroidMatchGameViewModel = koinViewModel()) {
    val textElements by viewModel.textElements.collectAsState()

    val primaryLanguage = "en"
    val secondaryLanguage = "tr"

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            "Match the primary language translations with the secondary language translations:",
            fontSize = 20.sp,
            modifier = Modifier.padding(8.dp)
        )

        FlowLayout(textElements, primaryLanguage)
        Spacer(modifier = Modifier.height(16.dp))
        FlowLayout(textElements, secondaryLanguage)
    }
}

@Composable
fun FlowLayout(textElements: List<TextElement>, language: String) {
    Column {
        textElements.forEach { textElement ->
            val translation = textElement.translations[language]
            if (translation != null) {
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
}
