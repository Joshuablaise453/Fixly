package com.electrohub.fixly.features.providers

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.electrohub.fixly.core.ui.components.FaIcon
import com.electrohub.fixly.core.ui.components.FaIcons
import com.electrohub.fixly.core.ui.components.FixlyButton
import com.electrohub.fixly.core.ui.components.FixlyCard
import com.electrohub.fixly.core.ui.components.RatingBadge
import com.electrohub.fixly.core.ui.components.VerifiedBadge
import com.electrohub.fixly.data.repository.MockDataRepository
import com.electrohub.fixly.ui.theme.DarkTeal
import com.electrohub.fixly.ui.theme.FixlyWhite
import com.electrohub.fixly.ui.theme.MainBackground
import com.electrohub.fixly.ui.theme.PrimaryGreen
import com.electrohub.fixly.ui.theme.PrimaryText
import com.electrohub.fixly.ui.theme.SecondaryText

@Composable
fun ProviderDetailsScreen(
    providerId: String,
    onNavigateToChat: (String) -> Unit,
    onNavigateToBooking: (String) -> Unit,
    onBackClick: () -> Unit
) {
    val provider = remember(providerId) {
        MockDataRepository.providers.find { it.id == providerId } ?: MockDataRepository.providers.first()
    }

    var selectedTab by remember { mutableIntStateOf(1) } // 0: About, 1: Services, 2: Reviews

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MainBackground)
    ) {
        // Top Bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(FixlyWhite)
                    .clickable { onBackClick() },
                contentAlignment = Alignment.Center
            ) {
                FaIcon(
                    icon = FaIcons.ArrowLeft,
                    contentDescription = "Back",
                    modifier = Modifier.size(20.dp),
                    tint = DarkTeal
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Text(
                text = provider.categoryName,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = PrimaryText
            )
        }

        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp)
        ) {
            // Header Provider Card
            FixlyCard {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .size(80.dp)
                            .clip(CircleShape)
                            .background(DarkTeal),
                        contentAlignment = Alignment.Center
                    ) {
                        FaIcon(
                            icon = FaIcons.User,
                            contentDescription = null,
                            modifier = Modifier.size(44.dp),
                            tint = FixlyWhite
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = provider.name,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = PrimaryText
                        )
                        if (provider.isVerified) {
                            Spacer(modifier = Modifier.width(6.dp))
                            VerifiedBadge()
                        }
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    RatingBadge(rating = provider.rating, reviewCount = provider.reviewCount)

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = "${provider.address} • ${provider.distanceKm} km away",
                        fontSize = 13.sp,
                        color = SecondaryText
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Action Buttons (Call, Message)
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Box(modifier = Modifier.weight(1f)) {
                            FixlyButton(
                                text = "Call",
                                icon = FaIcons.Phone,
                                onClick = { /* Call provider */ }
                            )
                        }

                        Box(modifier = Modifier.weight(1f)) {
                            FixlyButton(
                                text = "Chat",
                                icon = FaIcons.Messages,
                                onClick = { onNavigateToChat(provider.id) }
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Tabs Selector
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(FixlyWhite, RoundedCornerShape(12.dp))
                    .padding(4.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                listOf("About", "Services & Prices", "Reviews").forEachIndexed { index, title ->
                    val isSelected = selectedTab == index
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(10.dp))
                            .background(if (isSelected) PrimaryGreen else FixlyWhite)
                            .clickable { selectedTab = index }
                            .padding(vertical = 10.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = title,
                            fontSize = 13.sp,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                            color = if (isSelected) FixlyWhite else SecondaryText
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Tab Content
            when (selectedTab) {
                0 -> {
                    // About
                    FixlyCard {
                        Column {
                            Text(
                                text = "About ${provider.name}",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = PrimaryText
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = provider.aboutText,
                                fontSize = 14.sp,
                                color = SecondaryText,
                                lineHeight = 20.sp
                            )
                            Spacer(modifier = Modifier.height(12.dp))
                            Text(
                                text = "Phone: ${provider.phone}",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = PrimaryText
                            )
                        }
                    }
                }
                1 -> {
                    // Services & Prices
                    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                        provider.services.forEach { service ->
                            FixlyCard {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Column(modifier = Modifier.weight(1f)) {
                                        Text(
                                            text = service.name,
                                            fontSize = 15.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = PrimaryText
                                        )
                                        Text(
                                            text = service.description,
                                            fontSize = 13.sp,
                                            color = SecondaryText,
                                            modifier = Modifier.padding(top = 2.dp)
                                        )
                                        Text(
                                            text = "From UGX ${service.priceUGX}",
                                            fontSize = 14.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = PrimaryGreen,
                                            modifier = Modifier.padding(top = 6.dp)
                                        )
                                    }

                                    Spacer(modifier = Modifier.width(8.dp))

                                    Box(modifier = Modifier.width(72.dp)) {
                                        FixlyButton(
                                            text = "Book",
                                            onClick = { onNavigateToBooking(provider.id) }
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
                2 -> {
                    // Reviews
                    FixlyCard {
                        Column {
                            Text(
                                text = "Customer Reviews",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = PrimaryText
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            RatingBadge(rating = provider.rating, reviewCount = provider.reviewCount)
                            Spacer(modifier = Modifier.height(12.dp))
                            Text(
                                text = "\"Great experience! Very punctual, polite and fixed the leaking tap within 30 minutes in Kisementi.\"",
                                fontSize = 13.sp,
                                color = SecondaryText
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}
