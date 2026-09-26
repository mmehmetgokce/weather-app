package com.kampplus.hava.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.kampplus.hava.feature.weather.presentation.detail.CityDetailScreen
import com.kampplus.hava.feature.weather.presentation.list.CityListRoute

object HavaDestinations {
    const val CITY_LIST = "city_list"
    const val CITY_DETAIL = "city_detail/{cityName}"

    fun detailRoute(cityName: String): String = "city_detail/$cityName"
}

@Composable
fun HavaNavHost(modifier: Modifier = Modifier, navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = HavaDestinations.CITY_LIST,
        modifier = modifier
    ) {
        // 1. Ekran: Şehir Listesi
        composable(route = HavaDestinations.CITY_LIST) {
            CityListRoute(
                onCityClick = { selectedCityName ->
                    navController.navigate(HavaDestinations.detailRoute(selectedCityName))
                }
            )
        }

        // 2. Ekran: Şehir Detayı (Parametre alan hedef)
        composable(
            route = HavaDestinations.CITY_DETAIL,
            arguments = listOf(
                navArgument("cityName") {
                    type = NavType.StringType
                    nullable = true
                }
            )
        ) { backStackEntry ->
            val cityName = backStackEntry.arguments?.getString("cityName")
            CityDetailScreen(
                cityName = cityName,
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}
