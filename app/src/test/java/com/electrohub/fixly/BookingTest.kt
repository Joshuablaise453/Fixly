package com.electrohub.fixly

import com.electrohub.fixly.core.ui.components.BookingStatus
import com.electrohub.fixly.data.model.Booking
import org.junit.Assert.assertEquals
import org.junit.Test

class BookingTest {

    @Test
    fun `booking status can be created and read correctly`() {
        val booking = Booking(
            id = "bk_1",
            providerId = "prov_1",
            providerName = "Bright Plumbing Solutions",
            categoryName = "Plumbing",
            serviceTitle = "Leak Repair",
            dateString = "Mon, 26 May 2025",
            timeString = "10:00 AM",
            status = BookingStatus.Pending,
            address = "Kampala",
            amountUGX = 50000,
            notes = "Test note"
        )

        assertEquals("bk_1", booking.id)
        assertEquals("Pending", booking.status.label)
        assertEquals(50000L, booking.amountUGX)
    }
}
