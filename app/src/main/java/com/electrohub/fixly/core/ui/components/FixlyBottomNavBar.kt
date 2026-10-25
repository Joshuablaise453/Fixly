package com.electrohub.fixly.core.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.electrohub.fixly.core.navigation.BottomNavItem
import com.electrohub.fixly.ui.theme.CardBackground
import com.electrohub.fixly.ui.theme.PrimaryGreen
import com.electrohub.fixly.ui.theme.SecondaryText

@Composable
fun FixlyBottomNavBar(
    items: List<BottomNavItem>,
    currentRoute: String?,
    onItemClick: (BottomNavItem) -> Unit
) {
    NavigationBar(
        containerColor = CardBackground,
        contentColor = SecondaryText,
        tonalElevation = 0.dp
    ) {
        items.forEach { item ->
            val isSelected = currentRoute == item.route
            NavigationBarItem(
                selected = isSelected,
                onClick = { onItemClick(item) },
                icon = {
                    FaIcon(
                        icon = item.icon,
                        contentDescription = item.label,
                        modifier = Modifier.size(22.dp),
                        tint = if (isSelected) PrimaryGreen else SecondaryText
                    )
                },
                label = {
                    Text(
                        text = item.label,
                        fontSize = 12.sp,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                        color = if (isSelected) PrimaryGreen else SecondaryText
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color(0xFFE6F3ED),
                    selectedIconColor = PrimaryGreen,
                    unselectedIconColor = SecondaryText,
                    selectedTextColor = PrimaryGreen,
                    unselectedTextColor = SecondaryText
                )
            )
        }
    }
}
