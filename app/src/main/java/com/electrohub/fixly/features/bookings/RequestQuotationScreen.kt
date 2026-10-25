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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import com.electrohub.fixly.core.ui.components.BookingStatus
import com.electrohub.fixly.core.ui.components.FaIcon
import com.electrohub.fixly.core.ui.components.FaIcons
import com.electrohub.fixly.core.ui.components.FixlyButton
import com.electrohub.fixly.core.ui.components.FixlyCard
import com.electrohub.fixly.core.ui.components.FixlyTextField
import com.electrohub.fixly.data.model.Booking
import com.electrohub.fixly.data.repository.MockDataRepository
import com.electrohub.fixly.ui.theme.DarkTeal
import com.electrohub.fixly.ui.theme.FixlyWhite
import com.electrohub.fixly.ui.theme.MainBackground
import com.electrohub.fixly.ui.theme.PrimaryGreen
import com.electrohub.fixly.ui.theme.PrimaryText
import com.electrohub.fixly.ui.theme.SecondaryText

@Composable
fun RequestQuotationScreen(
    providerId: String,
    onBookingSubmitted: () -> Unit,
    onBackClick: () -> Unit
) {
    val provider = remember(providerId) {
        MockDataRepository.providers.find { it.id == providerId } ?: MockDataRepository.providers.first()
    }

    var selectedServiceIndex by remember { mutableStateOf(0) }
    var notes by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("Plot 14 Kisementi, Kampala") }
    var dateString by remember { mutableStateOf("Mon, 26 May 2025") }
    var timeString by remember { mutableStateOf("10:00 AM") }

    var isSubmitting by remember { mutableStateOf(false) }

    val selectedService = provider.services.getOrElse(selectedServiceIndex) {
        provider.services.firstOrNull() ?: com.electrohub.fixly.data.model.ServiceItem("s0", "General Service", "Standard inspection", provider.startingPriceUGX)
    }

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
                text = "Book Service",
                fontSize = 20.sp,
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
            // Provider Info Card
            FixlyCard {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .clip(CircleShape)
                            .background(DarkTeal),
                        contentAlignment = Alignment.Center
                    ) {
                        FaIcon(
                            icon = FaIcons.User,
                            contentDescription = null,
                            modifier = Modifier.size(26.dp),
                            tint = FixlyWhite
                        )
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    Column {
                        Text(
                            text = provider.name,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = PrimaryText
                        )
                        Text(
                            text = provider.categoryName,
                            fontSize = 13.sp,
                            color = SecondaryText
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Service Selection
            Text(
                text = "Select Service",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = PrimaryText
            )

            Spacer(modifier = Modifier.height(8.dp))

            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                provider.services.forEachIndexed { idx, srv ->
                    val isSelected = idx == selectedServiceIndex
                    FixlyCard(
                        onClick = { selectedServiceIndex = idx },
                        backgroundColor = if (isSelected) Color(0xFFE6F3ED) else FixlyWhite
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                Text(
                                    text = srv.name,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = PrimaryText
                                )
                                Text(
                                    text = srv.description,
                                    fontSize = 12.sp,
                                    color = SecondaryText
                                )
                            }
                            Text(
                                text = "UGX ${srv.priceUGX}",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = PrimaryGreen
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Address Input
            FixlyTextField(
                value = address,
                onValueChange = { address = it },
                label = "Service Location",
                placeholder = "Address in Kampala",
                leadingIcon = FaIcons.MapMarker
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Problem Description Notes
            FixlyTextField(
                value = notes,
                onValueChange = { notes = it },
                label = "Problem Description",
                placeholder = "Describe the issue or requirements...",
                singleLine = false
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Summary Card
            FixlyCard(backgroundColor = DarkTeal) {
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Estimated Total:",
                            fontSize = 15.sp,
                            color = FixlyWhite
                        )
                        Text(
                            text = "UGX ${selectedService.priceUGX}",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = FixlyWhite
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Submit Button
            FixlyButton(
                text = "Submit Booking Request",
                isLoading = isSubmitting,
                onClick = {
                    isSubmitting = true
                    val newBooking = Booking(
                        id = "bk_${System.currentTimeMillis()}",
                        providerId = provider.id,
                        providerName = provider.name,
                        categoryName = provider.categoryName,
                        serviceTitle = selectedService.name,
                        dateString = dateString,
                        timeString = timeString,
                        status = BookingStatus.Pending,
                        address = address,
                        amountUGX = selectedService.priceUGX,
                        notes = notes
                    )
                    MockDataRepository.addBooking(newBooking)
                    onBookingSubmitted()
                }
            )

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}
