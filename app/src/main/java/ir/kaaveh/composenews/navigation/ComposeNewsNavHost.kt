package ir.kaaveh.composenews.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import ir.kaaveh.favoritenews.FavoriteNewsScreen
import ir.kaaveh.newsdetail.NewsDetailScreen
import ir.kaaveh.newslist.NewsListScreen
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun ComposeNewsNavHost(navController: NavHostController, modifier: Modifier) {
    NavHost(
        navController = navController,
        startDestination = Destinations.NewsListScreen.route,
        modifier = modifier
    ) {
        composable(Destinations.NewsListScreen.route) {
            NewsListScreen(
                onNavigateToDetailScreen = { arg ->
                    val encodedUrl = URLEncoder.encode(arg, StandardCharsets.UTF_8.toString())
                    navController.navigate(Destinations.NewsDetailScreen.route + "/$encodedUrl")
                }
            )
        }
        composable(Destinations.FavoriteNewsScreen.route) {
            FavoriteNewsScreen(
                onNavigateToDetailScreen = { arg ->
                    val encodedUrl = URLEncoder.encode(arg, StandardCharsets.UTF_8.toString())
                    navController.navigate(Destinations.NewsDetailScreen.route + "/$encodedUrl")
                }
            )
        }
        composable(
            route = Destinations.NewsDetailScreen.route + "/{news_link}",
            arguments = listOf(
                navArgument("news_link") {
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = false
                }
            )
        ) { entry ->
            NewsDetailScreen(newsLink = entry.arguments?.getString("news_link") ?: "")
        }
    }
}