package com.electrohub.fixly.features.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.electrohub.fixly.core.ui.components.FaIcon
import com.electrohub.fixly.core.ui.components.FaIcons
import com.electrohub.fixly.core.ui.components.FixlyCard
import com.electrohub.fixly.data.repository.MockDataRepository
import com.electrohub.fixly.ui.theme.DarkTeal
import com.electrohub.fixly.ui.theme.ErrorRed
import com.electrohub.fixly.ui.theme.FixlyWhite
import com.electrohub.fixly.ui.theme.MainBackground
import com.electrohub.fixly.ui.theme.PrimaryGreen
import com.electrohub.fixly.ui.theme.PrimaryText
import com.electrohub.fixly.ui.theme.SecondaryText

@Composable
fun ProfileScreen(
    onNavigateToWallet: () -> Unit,
    onNavigateToNotifications: () -> Unit,
    onSwitchRole: () -> Unit,
    onLogoutClick: () -> Unit
) {
    val user = MockDataRepository.currentUser

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MainBackground)
            .padding(top = 16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "My Profile",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = PrimaryText,
            modifier = Modifier.padding(horizontal = 20.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Profile Header Card
        FixlyCard(modifier = Modifier.padding(horizontal = 20.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(64.dp)
                        .clip(CircleShape)
                        .background(DarkTeal),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "JM",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = FixlyWhite
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                Column {
                    Text(
                        text = user.fullName,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = PrimaryText
                    )

                    Spacer(modifier = Modifier.height(2.dp))

                    Box(
                        modifier = Modifier
                            .background(Color(0xFFE6F3ED), RoundedCornerShape(10.dp))
                            .padding(horizontal = 8.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = "Customer",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = PrimaryGreen
                        )
                    }

                    Text(
                        text = user.email,
                        fontSize = 12.sp,
                        color = SecondaryText,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Profile Menu Items
        Column(
            modifier = Modifier.padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            ProfileMenuItem("Edit Profile", FaIcons.User) { }
            ProfileMenuItem("My Addresses", FaIcons.MapMarker) { }
            ProfileMenuItem("Wallet & Payments", FaIcons.Wallet, onClick = onNavigateToWallet)
            ProfileMenuItem("Notifications", FaIcons.Bell, onClick = onNavigateToNotifications)
            ProfileMenuItem("Switch to Provider View", FaIcons.Dashboard, onClick = onSwitchRole)
            ProfileMenuItem("Help & Support", FaIcons.Headset) { }
            ProfileMenuItem("Settings", FaIcons.Wrench) { }

            Spacer(modifier = Modifier.height(12.dp))

            // Logout Option
            ProfileMenuItem("Log Out", FaIcons.SignOut, isDestructive = true, onClick = onLogoutClick)
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
fun ProfileMenuItem(
    title: String,
    icon: ImageVector,
    isDestructive: Boolean = false,
    onClick: () -> Unit
) {
    FixlyCard(onClick = onClick) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(if (isDestructive) Color(0xFFFFEBEE) else Color(0xFFEBF2F0)),
                    contentAlignment = Alignment.Center
                ) {
                    FaIcon(
                        icon = icon,
                        contentDescription = null,
                        modifier = Modifier.size(18.dp),
                        tint = if (isDestructive) ErrorRed else DarkTeal
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                Text(
                    text = title,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = if (isDestructive) ErrorRed else PrimaryText
                )
            }

            FaIcon(
                icon = FaIcons.ChevronRight,
                contentDescription = null,
                modifier = Modifier.size(14.dp),
                tint = SecondaryText
            )
        }
    }
}
