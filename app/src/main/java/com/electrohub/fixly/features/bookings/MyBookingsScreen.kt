package com.electrohub.fixly.features.bookings

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import com.electrohub.fixly.core.ui.components.BookingStatus
import com.electrohub.fixly.core.ui.components.FaIcons
import com.electrohub.fixly.core.ui.components.FixlyButton
import com.electrohub.fixly.core.ui.components.FixlyCard
import com.electrohub.fixly.core.ui.components.StatusChip
import com.electrohub.fixly.data.model.Booking
import com.electrohub.fixly.data.repository.MockDataRepository
import com.electrohub.fixly.ui.theme.FixlyWhite
import com.electrohub.fixly.ui.theme.MainBackground
import com.electrohub.fixly.ui.theme.PrimaryGreen
import com.electrohub.fixly.ui.theme.PrimaryText
import com.electrohub.fixly.ui.theme.SecondaryText

@Composable
fun MyBookingsScreen(
    onNavigateToChat: (String) -> Unit
) {
    val bookings by MockDataRepository.bookings.collectAsState(initial = emptyList())
    var selectedTab by remember { mutableIntStateOf(0) } // 0: Upcoming, 1: Past, 2: Cancelled

    val filteredBookings = remember(bookings, selectedTab) {
        bookings.filter { bk ->
            when (selectedTab) {
                0 -> bk.status == BookingStatus.Pending || bk.status == BookingStatus.Confirmed
                1 -> bk.status == BookingStatus.Completed
                2 -> bk.status == BookingStatus.Cancelled
                else -> true
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MainBackground)
            .padding(top = 16.dp)
    ) {
        Text(
            text = "My Bookings",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = PrimaryText,
            modifier = Modifier.padding(horizontal = 20.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Filter Tabs
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .background(FixlyWhite, RoundedCornerShape(12.dp))
                .padding(4.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            listOf("Upcoming", "Past", "Cancelled").forEachIndexed { index, title ->
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

        if (filteredBookings.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "No bookings found in this section.",
                    fontSize = 14.sp,
                    color = SecondaryText
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(filteredBookings) { booking ->
                    BookingItemCard(
                        booking = booking,
                        onChatClick = { onNavigateToChat(booking.providerId) }
                    )
                }
            }
        }
    }
}

@Composable
fun BookingItemCard(
    booking: Booking,
    onChatClick: () -> Unit
) {
    FixlyCard {
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "${booking.categoryName} - ${booking.serviceTitle}",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = PrimaryText
                    )
                    Text(
                        text = booking.providerName,
                        fontSize = 13.sp,
                        color = SecondaryText,
                        modifier = Modifier.padding(top = 2.dp)
                    )
                }
                StatusChip(status = booking.status)
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "${booking.dateString} • ${booking.timeString}",
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold,
                color = PrimaryText
            )

            Text(
                text = "Address: ${booking.address}",
                fontSize = 12.sp,
                color = SecondaryText,
                modifier = Modifier.padding(top = 2.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "UGX ${booking.amountUGX}",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryGreen
                )

                Box(modifier = Modifier.width(100.dp)) {
                    FixlyButton(
                        text = "Chat",
                        icon = FaIcons.Messages,
                        onClick = onChatClick
                    )
                }
            }
        }
    }
}
