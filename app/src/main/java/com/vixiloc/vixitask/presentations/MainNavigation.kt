package com.vixiloc.vixitask.presentations

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.vixiloc.vixitask.presentations.screens.about.AboutScreen
import com.vixiloc.vixitask.presentations.screens.add.AddScreen
import com.vixiloc.vixitask.presentations.screens.detail.DetailScreen
import com.vixiloc.vixitask.presentations.screens.home.HomeScreen
import com.vixiloc.vixitask.presentations.screens.license.OsLicense
import com.vixiloc.vixitask.presentations.screens.splash.SplashScreen
import com.vixiloc.vixitask.presentations.screens.update.UpdateScreen

@Composable
fun MainNavigation(navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = MainDestination.Splash.route) {
        composable(
            route = MainDestination.Splash.route,
            exitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Up,
                    animationSpec = tween(700)
                )
            }
        ) {
            SplashScreen(
                navHostController = navHostController,
            )
        }
        composable(
            route = MainDestination.Home.route,
        ) {
            HomeScreen(navHostController = navHostController)
        }
        composable(
            route = MainDestination.Detail.route,
            enterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Left,
                    animationSpec = tween(700)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Right,
                    animationSpec = tween(700)
                )
            },
            popEnterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Right,
                    animationSpec = tween(700)
                )
            },
            arguments = listOf(navArgument("taskId") { type = NavType.IntType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("taskId")
            DetailScreen(
                navHostController = navHostController,
                taskId = id
            )
        }
        composable(
            route = MainDestination.Add.route,
            enterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Left,
                    animationSpec = tween(700)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Right,
                    animationSpec = tween(700)
                )
            },
            popEnterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Right,
                    animationSpec = tween(700)
                )
            },
        ) {
            AddScreen(navHostController = navHostController)
        }
        composable(
            route = MainDestination.Update.route,
            enterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Left,
                    animationSpec = tween(700)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Right,
                    animationSpec = tween(700)
                )
            },
            popEnterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Right,
                    animationSpec = tween(700)
                )
            },
            arguments = listOf(navArgument("taskId") { type = NavType.IntType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("taskId")
            UpdateScreen(navHostController = navHostController, taskId = id)
        }
        composable(
            route = MainDestination.About.route,
            enterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Right,
                    animationSpec = tween(700)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Left,
                    animationSpec = tween(700)
                )
            },
        ) {
            AboutScreen(navHostController = navHostController)
        }
        composable(
            route = MainDestination.OsLicense.route,
            enterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Left,
                    animationSpec = tween(700)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Right,
                    animationSpec = tween(700)
                )
            },
        ) {
            OsLicense(navHostController = navHostController)
        }
    }
}

sealed class MainDestination(val route: String) {
    object Splash : MainDestination(route = "splash")
    object Home : MainDestination(route = "home")
    object About : MainDestination(route = "about")
    object OsLicense : MainDestination(route = "license")
    object Detail : MainDestination(route = "detail/{taskId}") {
        fun createRoute(taskId: Int) = "detail/$taskId"
    }

    object Add : MainDestination(route = "add")
    object Update : MainDestination(route = "update/{taskId}") {
        fun createRoute(taskId: Int) = "update/$taskId"
    }
}