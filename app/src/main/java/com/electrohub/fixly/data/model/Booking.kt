package com.electrohub.fixly.data.model

import com.electrohub.fixly.core.ui.components.BookingStatus

data class Booking(
    val id: String,
    val providerId: String,
    val providerName: String,
    val categoryName: String,
    val serviceTitle: String,
    val dateString: String,
    val timeString: String,
    val status: BookingStatus,
    val address: String,
    val amountUGX: Long,
    val notes: String = ""
)
