package com.kampplus.hava

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kampplus.hava.core.ui.theme.HavaTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HavaTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

/**
 * Ana ekran bileşeni: Ekranın genel düzenini ve içeriklerini barındırır.
 */
@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Hava Durumu Keşif",
            style = MaterialTheme.typography.headlineMedium
        )

        ContentCard(
            title = "Hoş Geldiniz",
            description = "Şehirlerin anlık hava durumunu keşfetmek ve tahminleri incelemek için hazır mısınız?"
        )
    }
}

/**
 * Kitapçıktaki sözleşmeye uygun ContentCard bileşeni.
 * title, description ve dış düzen için modifier alır.
 */
@Composable
fun ContentCard(title: String, description: String, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

// 1. Önizleme: Kısa metin durumu
@Preview(showBackground = true, name = "Kısa Metin Önizleme")
@Composable
private fun ContentCardShortPreview() {
    HavaTheme {
        ContentCard(
            title = "İstanbul",
            description = "21°C, Güneşli",
            modifier = Modifier.padding(16.dp)
        )
    }
}

// 2. Önizleme: Uzun metin durumu (kitapçık 3. adım gereksinimi)
@Preview(showBackground = true, name = "Uzun Metin Önizleme")
@Composable
private fun ContentCardLongPreview() {
    HavaTheme {
        ContentCard(
            title = "Haftalık Hava Tahmini Raporu",
            description = "Önümüzdeki günlerde Marmara bölgesi genelinde yer yer " +
                "kuvvetli sağanak yağış ve rüzgar beklenmektedir. " +
                "Sıcaklıklarda 4 ila 6 derece düşüş görülebilir.",
            modifier = Modifier.padding(16.dp)
        )
    }
}

// 3. Önizleme: Dar ekran (240dp) ve büyük yazı ölçeği (Erişilebilirlik - %150 font scale)
@Preview(
    showBackground = true,
    name = "Erişilebilirlik ve Dar Ekran",
    widthDp = 240,
    fontScale = 1.5f
)
@Composable
private fun ContentCardAccessibilityPreview() {
    HavaTheme {
        ContentCard(
            title = "Ankara",
            description = "18°C, Parçalı Bulutlu ve Hafif Rüzgarlı",
            modifier = Modifier.padding(8.dp)
        )
    }
}
