package com.electrohub.fixly.features.provider_dashboard

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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.electrohub.fixly.core.ui.components.FaIcon
import com.electrohub.fixly.core.ui.components.FaIcons
import com.electrohub.fixly.core.ui.components.FixlyCard
import com.electrohub.fixly.core.ui.components.StatusChip
import com.electrohub.fixly.core.ui.components.VerifiedBadge
import com.electrohub.fixly.data.repository.MockDataRepository
import com.electrohub.fixly.ui.theme.DarkTeal
import com.electrohub.fixly.ui.theme.FixlyWhite
import com.electrohub.fixly.ui.theme.MainBackground
import com.electrohub.fixly.ui.theme.PrimaryGreen
import com.electrohub.fixly.ui.theme.PrimaryText
import com.electrohub.fixly.ui.theme.SecondaryText

@Composable
fun ProviderDashboardScreen(
    onNavigateToEarnings: () -> Unit,
    onNavigateToSchedule: () -> Unit,
    onNavigateToChat: (String) -> Unit
) {
    val provider = remember { MockDataRepository.providers.first() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MainBackground)
            .padding(top = 16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "Provider Dashboard",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = PrimaryText,
            modifier = Modifier.padding(horizontal = 20.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Provider Header Card
        FixlyCard(
            modifier = Modifier.padding(horizontal = 20.dp),
            backgroundColor = DarkTeal
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(56.dp)
                        .clip(CircleShape)
                        .background(FixlyWhite),
                    contentAlignment = Alignment.Center
                ) {
                    FaIcon(
                        icon = FaIcons.Wrench,
                        contentDescription = null,
                        modifier = Modifier.size(28.dp),
                        tint = PrimaryGreen
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = provider.name,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = FixlyWhite
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        VerifiedBadge()
                    }
                    Text(
                        text = provider.categoryName,
                        fontSize = 13.sp,
                        color = FixlyWhite.copy(alpha = 0.8f),
                        modifier = Modifier.padding(top = 2.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Metric Cards (Today's Bookings & Earnings)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            FixlyCard(
                modifier = Modifier.weight(1f),
                onClick = onNavigateToSchedule
            ) {
                Column {
                    Text(
                        text = "Today's Bookings",
                        fontSize = 12.sp,
                        color = SecondaryText
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "5",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        color = PrimaryGreen
                    )
                }
            }

            FixlyCard(
                modifier = Modifier.weight(1f),
                onClick = onNavigateToEarnings
            ) {
                Column {
                    Text(
                        text = "Earnings (This Week)",
                        fontSize = 12.sp,
                        color = SecondaryText
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "UGX 780,000",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = PrimaryGreen
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Quick Actions Grid
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            ProviderQuickAction("Bookings", FaIcons.Bookings, onNavigateToSchedule)
            ProviderQuickAction("Services", FaIcons.Wrench) { }
            ProviderQuickAction("Earnings", FaIcons.Wallet, onNavigateToEarnings)
            ProviderQuickAction("Reports", FaIcons.Dashboard) { }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Recent Incoming Requests
        Text(
            text = "Recent Incoming Bookings",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = PrimaryText,
            modifier = Modifier.padding(horizontal = 20.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))

        Column(
            modifier = Modifier.padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            FixlyCard(onClick = { onNavigateToChat(provider.id) }) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "Leak Repair • John Mwangi",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = PrimaryText
                        )
                        Text(
                            text = "Mon, 26 May 2025 • 10:00 AM",
                            fontSize = 12.sp,
                            color = SecondaryText,
                            modifier = Modifier.padding(top = 2.dp)
                        )
                    }
                    StatusChip(status = com.electrohub.fixly.core.ui.components.BookingStatus.Pending)
                }
            }

            FixlyCard(onClick = { onNavigateToChat(provider.id) }) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "Pipe Installation • Sarah K.",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = PrimaryText
                        )
                        Text(
                            text = "Fri, 23 May 2025 • 03:00 PM",
                            fontSize = 12.sp,
                            color = SecondaryText,
                            modifier = Modifier.padding(top = 2.dp)
                        )
                    }
                    StatusChip(status = com.electrohub.fixly.core.ui.components.BookingStatus.Confirmed)
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
fun ProviderQuickAction(
    label: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable { onClick() }
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(Color(0xFFEBF2F0)),
            contentAlignment = Alignment.Center
        ) {
            FaIcon(
                icon = icon,
                contentDescription = label,
                modifier = Modifier.size(22.dp),
                tint = PrimaryGreen
            )
        }
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = label,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            color = PrimaryText
        )
    }
}
