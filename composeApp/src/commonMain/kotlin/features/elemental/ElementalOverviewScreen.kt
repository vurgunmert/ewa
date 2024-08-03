package features.elemental

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun ElementalOverviewScreen() {
    var selectedLanguage by remember { mutableStateOf("English") }

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Header
        Text(
            text = "Elemental Learning Through Waves",
            style = MaterialTheme.typography.h4,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Language Selector
        LanguageSelector(selectedLanguage) { newLanguage ->
            selectedLanguage = newLanguage
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Overview Section
        SectionTitle("Overview")
        GameOverview(selectedLanguage)

        Spacer(modifier = Modifier.height(16.dp))

        // Rules Section
        SectionTitle("Rules")
        GameRules(selectedLanguage)

        Spacer(modifier = Modifier.height(16.dp))

        // Criteria Section
        SectionTitle("Criteria")
        GameCriteria(selectedLanguage)

        Spacer(modifier = Modifier.height(32.dp))

        // Start Button
        Button(
            onClick = { /* Start the game */ },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Start Game")
        }
    }
}

@Composable
fun LanguageSelector(selectedLanguage: String, onLanguageSelected: (String) -> Unit) {
    val languages = listOf("English", "Turkish")

    Row {
        languages.forEach { language ->
            Button(
                onClick = { onLanguageSelected(language) },
                colors = if (selectedLanguage == language) ButtonDefaults.buttonColors(MaterialTheme.colors.primary) else ButtonDefaults.buttonColors(
                    MaterialTheme.colors.secondary
                ),
                modifier = Modifier.padding(4.dp)
            ) {
                Text(language)
            }
        }
    }
}

@Composable
fun SectionTitle(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.caption,
        fontSize = 20.sp,
        modifier = Modifier.padding(vertical = 8.dp)
    )
}

@Composable
fun GameOverview(language: String) {
    val overview = when (language) {
        "Turkish" -> """
            Oyun, elementler ve İngilizce öğrenme konularının izole bloklar yerine bir dalga deseni izlediği fikrini kullanır. Her element/konu bağlantılıdır ve doğal öğrenmenin nasıl geliştiğini yansıtarak öngörülebilir çokluklarda ilerler.
        """.trimIndent()

        else -> """
            The game leverages the idea that elements and English learning topics follow a wave pattern rather than isolated blocks. Each element/topic is connected and progresses in predictable multiples, reflecting how natural learning evolves.
        """.trimIndent()
    }

    Text(overview)
}

@Composable
fun GameRules(language: String) {
    val rules = when (language) {
        "Turkish" -> """
            Kurallar:
            Element Yapısı:
            - Elementler: Her element belirli bir İngilizce öğrenme konusunu temsil eder.
            - Oktavlar: Elementler oktavlar halinde gruplanır, her oktav dil öğreniminde farklı bir karmaşıklık seviyesini temsil eder.
            Dalga Konsepti:
            - Konu İlerlemesi: Her oktav içindeki konular bir dalga desenini takip eder, her bir sonraki konu bir öncekine dayanarak ilerler.
            - Örnek: İlerleme aynı oktav içinde temel selamlaşmalardan (Hidrojen) basit kişisel bilgilere (Karbon) geçebilir.
        """.trimIndent()

        else -> """
            Rules:
            Element Structure:
            - Elements: Each element represents a specific English learning topic.
            - Octaves: Elements are grouped into octaves, each octave representing a different level of complexity in language learning.
            Wave Concept:
            - Topics Progression: Topics within each octave follow a wave pattern, where each subsequent topic builds on the previous one.
            - Example: Progression might go from basic greetings (Hydrogen) to simple personal information (Carbon) within the same octave.
        """.trimIndent()
    }

    Text(rules)
}

@Composable
fun GameCriteria(language: String) {
    val criteria = when (language) {
        "Turkish" -> """
            Kriterler:
            Öğrenme İlerlemesi:
            - Başlangıç Noktası: Oyuncular en düşük oktavdan başlar.
            - İlerleme: Oyunda etkileşim kurarak her konu boyunca ilerlerler.
            - Başarı: Başarılı etkileşimler ve doğru yanıtlar oyuncuların dalgada ilerleyerek daha karmaşık konulara geçmelerine yardımcı olur.
            Etkileşim ve Puanlama:
            - Sohbet Etkileşimi: Her seviye, konuya odaklanan bir sohbet etkileşimi ile başlar.
            - Gerçek Zamanlı Analiz: Yanıtlar Gemini API kullanılarak gerçek zamanlı olarak analiz edilir.
            - Puanlama: Kullanılan dilin doğruluğu, ilgisi ve karmaşıklığına dayanır.
            Dalga Eşleşmesi:
            - Doğal Eşler: Belirli konular, Terrence Howard'ın tablosunda olduğu gibi "doğal eşlere" sahip olacaktır (örneğin, kelime bilgisi ve dilbilgisi).
            - Verimlilik: Bu eşleşmeleri anlamak verimli ilerleme için önemlidir.
            Geri Bildirim ve İlerleme:
            - Geri Bildirim: Her etkileşimden sonra iyileştirilmesi gereken alanları vurgulayan geri bildirim sağlanır.
            - Sonraki Oktav: Oyuncular mevcut oktavdaki tüm konuları tamamlayıp bu seviyede ustalaştıklarını gösterdiklerinde bir sonraki oktava geçerler.
        """.trimIndent()

        else -> """
            Criteria:
            Learning Progression:
            - Starting Point: Players start at the lowest octave.
            - Advancement: Progress through each topic by interacting with the game.
            - Success: Successful interactions and correct responses help players move up the wave, advancing to more complex topics.
            Interaction and Scoring:
            - Chat Interaction: Each level starts with a chat interaction focused on the topic.
            - Real-Time Analysis: Responses are analyzed in real-time using the Gemini API.
            - Scoring: Based on accuracy, relevance, and complexity of the language used.
            Wave Matching:
            - Natural Mates: Certain topics will have "natural mates," similar to how elements pair in Terrence Howard's table (e.g., vocabulary and grammar).
            - Efficiency: Understanding these pairings is crucial for efficient progression.
            Feedback and Advancement:
            - Feedback: Provided after each interaction, highlighting areas for improvement.
            - Next Octave: Players advance to the next octave once they complete all topics in the current one, demonstrating mastery of that level.
        """.trimIndent()
    }

    Text(criteria)
}

@Preview
@Composable
fun PreviewElementalOverviewScreen() {
    ElementalOverviewScreen()
}
