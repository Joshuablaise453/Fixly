package com.electrohub.fixly.features.search

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
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
import com.electrohub.fixly.core.ui.components.FixlyTextField
import com.electrohub.fixly.data.repository.MockDataRepository
import com.electrohub.fixly.features.home.ProviderItemCard
import com.electrohub.fixly.ui.theme.DarkTeal
import com.electrohub.fixly.ui.theme.FixlyWhite
import com.electrohub.fixly.ui.theme.MainBackground
import com.electrohub.fixly.ui.theme.PrimaryGreen
import com.electrohub.fixly.ui.theme.PrimaryText
import com.electrohub.fixly.ui.theme.SecondaryText

@Composable
fun SearchServicesScreen(
    initialCategoryId: String? = null,
    onNavigateToProviderDetails: (String) -> Unit,
    onBackClick: () -> Unit
) {
    var searchQuery by remember { mutableStateOf("") }
    var selectedCategoryId by remember { mutableStateOf(initialCategoryId ?: "all") }

    val allCategories = remember {
        listOf(
            com.electrohub.fixly.data.model.ServiceCategory("all", "All Services", FaIcons.Search, 0)
        ) + MockDataRepository.categories
    }

    val filteredProviders = remember(searchQuery, selectedCategoryId) {
        MockDataRepository.providers.filter { provider ->
            val matchesCategory = selectedCategoryId == "all" || provider.categoryId == selectedCategoryId
            val matchesQuery = searchQuery.isBlank() ||
                    provider.name.contains(searchQuery, ignoreCase = true) ||
                    provider.categoryName.contains(searchQuery, ignoreCase = true) ||
                    provider.aboutText.contains(searchQuery, ignoreCase = true)
            matchesCategory && matchesQuery
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MainBackground)
    ) {
        // Top Header
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
                text = "Discover Services",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = PrimaryText
            )
        }

        // Search Input
        Box(modifier = Modifier.padding(horizontal = 20.dp)) {
            FixlyTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                label = "",
                placeholder = "Search plumbers, electricians, mechanics...",
                leadingIcon = FaIcons.Search
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Category Filter Chips
        LazyRow(
            contentPadding = PaddingValues(horizontal = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(allCategories) { cat ->
                val isSelected = cat.id == selectedCategoryId
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .background(if (isSelected) PrimaryGreen else FixlyWhite)
                        .clickable { selectedCategoryId = cat.id }
                        .padding(horizontal = 16.dp, vertical = 10.dp)
                ) {
                    Text(
                        text = cat.name,
                        fontSize = 13.sp,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                        color = if (isSelected) FixlyWhite else PrimaryText
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Provider Count Summary
        Text(
            text = "${filteredProviders.size} providers available in Kampala",
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            color = SecondaryText,
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 4.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Provider Results List
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 20.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(filteredProviders) { provider ->
                ProviderItemCard(
                    provider = provider,
                    onClick = { onNavigateToProviderDetails(provider.id) }
                )
            }
        }
    }
}
