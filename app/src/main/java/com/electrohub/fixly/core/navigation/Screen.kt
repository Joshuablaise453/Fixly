package com.electrohub.fixly.core.navigation

import androidx.compose.ui.graphics.vector.ImageVector
import com.electrohub.fixly.core.ui.components.FaIcons

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Onboarding : Screen("onboarding")
    object Auth : Screen("auth")
    object Home : Screen("home")
    object Search : Screen("search")
    object ProviderDetails : Screen("provider_details/{providerId}") {
        fun createRoute(providerId: String) = "provider_details/$providerId"
    }
    object RequestQuotation : Screen("request_quotation/{providerId}") {
        fun createRoute(providerId: String) = "request_quotation/$providerId"
    }
    object Bookings : Screen("bookings")
    object Messages : Screen("messages")
    object Chat : Screen("chat/{providerId}") {
        fun createRoute(providerId: String) = "chat/$providerId"
    }
    object Wallet : Screen("wallet")
    object Notifications : Screen("notifications")
    object Profile : Screen("profile")
    object EditProfile : Screen("edit_profile")
    object SavedAddresses : Screen("saved_addresses")
    object HelpSupport : Screen("help_support")
    object Settings : Screen("settings")
    object ProviderDashboard : Screen("provider_dashboard")
    object ProviderEarnings : Screen("provider_earnings")
    object ProviderSchedule : Screen("provider_schedule")
}

data class BottomNavItem(
    val route: String,
    val label: String,
    val icon: ImageVector
)

val customerBottomNavItems = listOf(
    BottomNavItem(Screen.Home.route, "Home", FaIcons.Home),
    BottomNavItem(Screen.Bookings.route, "Bookings", FaIcons.Bookings),
    BottomNavItem(Screen.Messages.route, "Messages", FaIcons.Messages),
    BottomNavItem(Screen.Profile.route, "Profile", FaIcons.User)
)

val providerBottomNavItems = listOf(
    BottomNavItem(Screen.ProviderDashboard.route, "Dashboard", FaIcons.Dashboard),
    BottomNavItem(Screen.Bookings.route, "Bookings", FaIcons.Bookings),
    BottomNavItem(Screen.Messages.route, "Messages", FaIcons.Messages),
    BottomNavItem(Screen.Profile.route, "Profile", FaIcons.User)
)
