package features.userpreferences

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun UserPreferencesScreen(googleClicked: () -> Unit) {

    Column {
        Text("Pick Targets")
        Row {
            Button(googleClicked) {
                Text("Turkce")
            }
            Button(googleClicked, enabled = false) {
                Text("English")
            }
        }

        Button(googleClicked) {
            Text("Start with Google")
        }
    }
}