package com.kampplus.hava.feature.weather.presentation.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kampplus.hava.core.ui.theme.HavaTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CityDetailScreen(cityName: String?, onBackClick: () -> Unit, modifier: Modifier = Modifier) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { Text(text = cityName?.takeIf { it.isNotBlank() } ?: "Detay") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Geri"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            // Kitapçık 4. Adım: null, boşluk veya rota şablonu durumunu geçersiz sayma
            if (cityName.isNullOrBlank() || cityName == "{cityName}") {
                InvalidCityContent(onBackClick = onBackClick)
            } else {
                ValidCityContent(cityName = cityName)
            }
        }
    }
}

@Composable
private fun ValidCityContent(cityName: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.padding(16.dp)
    ) {
        Text(
            text = cityName,
            style = MaterialTheme.typography.headlineLarge
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Hava durumu tahmini ve detay bilgileri hazırlanıyor...",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun InvalidCityContent(onBackClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.padding(16.dp)
    ) {
        Text(
            text = "Geçersiz Şehir Parametresi!",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.error
        )
        Spacer(modifier = Modifier.height(12.dp))
        Button(onClick = onBackClick) {
            Text("Listeye Geri Dön")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CityDetailScreenPreview() {
    HavaTheme {
        CityDetailScreen(
            cityName = "İstanbul",
            onBackClick = {}
        )
    }
}

@Preview(showBackground = true, name = "Geçersiz Parametre Önizleme")
@Composable
private fun CityDetailScreenInvalidPreview() {
    HavaTheme {
        CityDetailScreen(
            cityName = null,
            onBackClick = {}
        )
    }
}
