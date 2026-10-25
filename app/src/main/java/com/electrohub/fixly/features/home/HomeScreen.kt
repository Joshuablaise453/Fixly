package com.electrohub.fixly.features.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.electrohub.fixly.core.ui.components.FaIcon
import com.electrohub.fixly.core.ui.components.FaIcons
import com.electrohub.fixly.core.ui.components.FixlyButton
import com.electrohub.fixly.core.ui.components.FixlyCard
import com.electrohub.fixly.core.ui.components.FixlyTextField
import com.electrohub.fixly.core.ui.components.RatingBadge
import com.electrohub.fixly.core.ui.components.VerifiedBadge
import com.electrohub.fixly.data.model.ServiceProvider
import com.electrohub.fixly.data.repository.MockDataRepository
import com.electrohub.fixly.ui.theme.DarkTeal
import com.electrohub.fixly.ui.theme.FixlyWhite
import com.electrohub.fixly.ui.theme.MainBackground
import com.electrohub.fixly.ui.theme.PrimaryGreen
import com.electrohub.fixly.ui.theme.PrimaryText
import com.electrohub.fixly.ui.theme.SecondaryText

@Composable
fun HomeScreen(
    onNavigateToSearch: () -> Unit,
    onNavigateToProviderDetails: (String) -> Unit,
    onNavigateToNotifications: () -> Unit,
    onNavigateToCategory: (String) -> Unit
) {
    var searchQuery by remember { mutableStateOf("") }
    val categories = remember { MockDataRepository.categories }
    val topProviders = remember { MockDataRepository.providers }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MainBackground)
            .padding(top = 16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        // Top Location Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.clickable { /* Change location */ }
            ) {
                FaIcon(
                    icon = FaIcons.MapMarker,
                    contentDescription = "Location",
                    modifier = Modifier.size(20.dp),
                    tint = PrimaryGreen
                )
                Spacer(modifier = Modifier.width(6.dp))
                Column {
                    Text(
                        text = "Kampala, Uganda",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = PrimaryText
                    )
                }
                Spacer(modifier = Modifier.width(4.dp))
                FaIcon(
                    icon = FaIcons.ChevronRight,
                    contentDescription = "Select",
                    modifier = Modifier.size(14.dp),
                    tint = SecondaryText
                )
            }

            // Bell Notifications Icon
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(FixlyWhite)
                    .clickable { onNavigateToNotifications() },
                contentAlignment = Alignment.Center
            ) {
                FaIcon(
                    icon = FaIcons.Bell,
                    contentDescription = "Notifications",
                    modifier = Modifier.size(20.dp),
                    tint = DarkTeal
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Hero Banner Card
        FixlyCard(
            modifier = Modifier.padding(horizontal = 20.dp),
            backgroundColor = DarkTeal
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Need a service?",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = FixlyWhite
                    )
                    Text(
                        text = "Find certified & trusted professionals near you.",
                        fontSize = 13.sp,
                        color = FixlyWhite.copy(alpha = 0.85f),
                        modifier = Modifier.padding(top = 4.dp, bottom = 12.dp)
                    )
                    Box(modifier = Modifier.width(120.dp)) {
                        FixlyButton(
                            text = "Book Now",
                            onClick = { onNavigateToSearch() }
                        )
                    }
                }

                Spacer(modifier = Modifier.width(12.dp))

                Box(
                    modifier = Modifier
                        .size(72.dp)
                        .clip(CircleShape)
                        .background(PrimaryGreen),
                    contentAlignment = Alignment.Center
                ) {
                    FaIcon(
                        icon = FaIcons.Wrench,
                        contentDescription = null,
                        modifier = Modifier.size(36.dp),
                        tint = FixlyWhite
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Search Bar Input
        Box(modifier = Modifier.padding(horizontal = 20.dp)) {
            FixlyTextField(
                value = searchQuery,
                onValueChange = {
                    searchQuery = it
                    if (it.isNotBlank()) onNavigateToSearch()
                },
                label = "",
                placeholder = "Search for a service (e.g. plumber)",
                leadingIcon = FaIcons.Search
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Categories Row
        Text(
            text = "Categories",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = PrimaryText,
            modifier = Modifier.padding(horizontal = 20.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))

        LazyRow(
            contentPadding = PaddingValues(horizontal = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(categories) { category ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .clickable { onNavigateToCategory(category.id) }
                        .padding(4.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(56.dp)
                            .clip(CircleShape)
                            .background(FixlyWhite),
                        contentAlignment = Alignment.Center
                    ) {
                        FaIcon(
                            icon = category.icon,
                            contentDescription = category.name,
                            modifier = Modifier.size(24.dp),
                            tint = PrimaryGreen
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = category.name,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Medium,
                        color = PrimaryText
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Top Rated Providers Section
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Top Rated Providers",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = PrimaryText
            )
            Text(
                text = "See All >",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = PrimaryGreen,
                modifier = Modifier.clickable { onNavigateToSearch() }
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Column(
            modifier = Modifier.padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            topProviders.forEach { provider ->
                ProviderItemCard(
                    provider = provider,
                    onClick = { onNavigateToProviderDetails(provider.id) }
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
fun ProviderItemCard(
    provider: ServiceProvider,
    onClick: () -> Unit
) {
    FixlyCard(onClick = onClick) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(64.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(0xFFEBF2F0)),
                contentAlignment = Alignment.Center
            ) {
                FaIcon(
                    icon = FaIcons.User,
                    contentDescription = null,
                    modifier = Modifier.size(32.dp),
                    tint = DarkTeal
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = provider.name,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = PrimaryText
                    )
                    if (provider.isVerified) {
                        Spacer(modifier = Modifier.width(6.dp))
                        VerifiedBadge()
                    }
                }

                Spacer(modifier = Modifier.height(4.dp))

                RatingBadge(rating = provider.rating, reviewCount = provider.reviewCount)

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "From UGX ${provider.startingPriceUGX} • ${provider.distanceKm} km",
                    fontSize = 13.sp,
                    color = SecondaryText
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            Box(modifier = Modifier.width(72.dp)) {
                FixlyButton(
                    text = "Book",
                    onClick = onClick
                )
            }
        }
    }
}
