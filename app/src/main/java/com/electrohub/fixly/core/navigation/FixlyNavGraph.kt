package com.electrohub.fixly.core.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.electrohub.fixly.core.ui.components.FixlyBottomNavBar
import com.electrohub.fixly.data.model.UserRole
import com.electrohub.fixly.features.auth.AuthScreen
import com.electrohub.fixly.features.bookings.MyBookingsScreen
import com.electrohub.fixly.features.bookings.RequestQuotationScreen
import com.electrohub.fixly.features.home.HomeScreen
import com.electrohub.fixly.features.messaging.ChatScreen
import com.electrohub.fixly.features.notifications.NotificationsScreen
import com.electrohub.fixly.features.onboarding.OnboardingScreen
import com.electrohub.fixly.features.profile.ProfileScreen
import com.electrohub.fixly.features.provider_dashboard.ProviderDashboardScreen
import com.electrohub.fixly.features.providers.ProviderDetailsScreen
import com.electrohub.fixly.features.search.SearchServicesScreen
import com.electrohub.fixly.features.splash.SplashScreen
import com.electrohub.fixly.features.wallet.WalletScreen

@Composable
fun FixlyApp() {
    val navController = rememberNavController()
    var currentRole by remember { mutableStateOf(UserRole.CUSTOMER) }

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val bottomNavItems = if (currentRole == UserRole.PROVIDER) providerBottomNavItems else customerBottomNavItems

    val showBottomBar = currentRoute in listOf(
        Screen.Home.route,
        Screen.Bookings.route,
        Screen.Messages.route,
        Screen.Profile.route,
        Screen.ProviderDashboard.route
    )

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                FixlyBottomNavBar(
                    items = bottomNavItems,
                    currentRoute = currentRoute,
                    onItemClick = { item ->
                        if (currentRoute != item.route) {
                            navController.navigate(item.route) {
                                popUpTo(Screen.Home.route) { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    }
                )
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Splash.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Splash.route) {
                SplashScreen(
                    onNavigateToOnboarding = {
                        navController.navigate(Screen.Onboarding.route) {
                            popUpTo(Screen.Splash.route) { inclusive = true }
                        }
                    }
                )
            }

            composable(Screen.Onboarding.route) {
                OnboardingScreen(
                    onFinishOnboarding = {
                        navController.navigate(Screen.Auth.route) {
                            popUpTo(Screen.Onboarding.route) { inclusive = true }
                        }
                    }
                )
            }

            composable(Screen.Auth.route) {
                AuthScreen(
                    onLoginSuccess = { role ->
                        currentRole = role
                        val dest = if (role == UserRole.PROVIDER) Screen.ProviderDashboard.route else Screen.Home.route
                        navController.navigate(dest) {
                            popUpTo(Screen.Auth.route) { inclusive = true }
                        }
                    }
                )
            }

            composable(Screen.Home.route) {
                HomeScreen(
                    onNavigateToSearch = { navController.navigate(Screen.Search.route) },
                    onNavigateToProviderDetails = { providerId ->
                        navController.navigate(Screen.ProviderDetails.createRoute(providerId))
                    },
                    onNavigateToNotifications = { navController.navigate(Screen.Notifications.route) },
                    onNavigateToCategory = { catId ->
                        navController.navigate(Screen.Search.route)
                    }
                )
            }

            composable(Screen.Search.route) {
                SearchServicesScreen(
                    onNavigateToProviderDetails = { providerId ->
                        navController.navigate(Screen.ProviderDetails.createRoute(providerId))
                    },
                    onBackClick = { navController.popBackStack() }
                )
            }

            composable(
                route = Screen.ProviderDetails.route,
                arguments = listOf(navArgument("providerId") { type = NavType.StringType })
            ) { backStackEntry ->
                val providerId = backStackEntry.arguments?.getString("providerId") ?: "prov_1"
                ProviderDetailsScreen(
                    providerId = providerId,
                    onNavigateToChat = { pId ->
                        navController.navigate(Screen.Chat.createRoute(pId))
                    },
                    onNavigateToBooking = { pId ->
                        navController.navigate(Screen.RequestQuotation.createRoute(pId))
                    },
                    onBackClick = { navController.popBackStack() }
                )
            }

            composable(
                route = Screen.RequestQuotation.route,
                arguments = listOf(navArgument("providerId") { type = NavType.StringType })
            ) { backStackEntry ->
                val providerId = backStackEntry.arguments?.getString("providerId") ?: "prov_1"
                RequestQuotationScreen(
                    providerId = providerId,
                    onBookingSubmitted = {
                        navController.navigate(Screen.Bookings.route) {
                            popUpTo(Screen.Home.route)
                        }
                    },
                    onBackClick = { navController.popBackStack() }
                )
            }

            composable(Screen.Bookings.route) {
                MyBookingsScreen(
                    onNavigateToChat = { pId ->
                        navController.navigate(Screen.Chat.createRoute(pId))
                    }
                )
            }

            composable(Screen.Messages.route) {
                // Messages list navigates to default chat
                ChatScreen(
                    providerId = "prov_1",
                    onBackClick = { navController.popBackStack() }
                )
            }

            composable(
                route = Screen.Chat.route,
                arguments = listOf(navArgument("providerId") { type = NavType.StringType })
            ) { backStackEntry ->
                val providerId = backStackEntry.arguments?.getString("providerId") ?: "prov_1"
                ChatScreen(
                    providerId = providerId,
                    onBackClick = { navController.popBackStack() }
                )
            }

            composable(Screen.Wallet.route) {
                WalletScreen(
                    onBackClick = { navController.popBackStack() }
                )
            }

            composable(Screen.Notifications.route) {
                NotificationsScreen(
                    onBackClick = { navController.popBackStack() }
                )
            }

            composable(Screen.Profile.route) {
                ProfileScreen(
                    onNavigateToWallet = { navController.navigate(Screen.Wallet.route) },
                    onNavigateToNotifications = { navController.navigate(Screen.Notifications.route) },
                    onSwitchRole = {
                        currentRole = if (currentRole == UserRole.CUSTOMER) UserRole.PROVIDER else UserRole.CUSTOMER
                        val target = if (currentRole == UserRole.PROVIDER) Screen.ProviderDashboard.route else Screen.Home.route
                        navController.navigate(target) {
                            popUpTo(Screen.Home.route) { inclusive = true }
                        }
                    },
                    onLogoutClick = {
                        navController.navigate(Screen.Auth.route) {
                            popUpTo(Screen.Home.route) { inclusive = true }
                        }
                    }
                )
            }

            composable(Screen.ProviderDashboard.route) {
                ProviderDashboardScreen(
                    onNavigateToEarnings = { navController.navigate(Screen.Wallet.route) },
                    onNavigateToSchedule = { navController.navigate(Screen.Bookings.route) },
                    onNavigateToChat = { pId ->
                        navController.navigate(Screen.Chat.createRoute(pId))
                    }
                )
            }
        }
    }
}
