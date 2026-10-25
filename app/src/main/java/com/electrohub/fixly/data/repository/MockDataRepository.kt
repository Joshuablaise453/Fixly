package com.electrohub.fixly.data.repository

import com.electrohub.fixly.core.ui.components.BookingStatus
import com.electrohub.fixly.core.ui.components.FaIcons
import com.electrohub.fixly.data.model.Booking
import com.electrohub.fixly.data.model.ChatMessage
import com.electrohub.fixly.data.model.NotificationItem
import com.electrohub.fixly.data.model.ServiceCategory
import com.electrohub.fixly.data.model.ServiceItem
import com.electrohub.fixly.data.model.ServiceProvider
import com.electrohub.fixly.data.model.User
import com.electrohub.fixly.data.model.UserRole
import com.electrohub.fixly.data.model.WalletTransaction
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

object MockDataRepository {

    val currentUser = User(
        id = "usr_001",
        fullName = "John Mwangi",
        email = "john.mwangi@example.com",
        phoneNumber = "+256 700 123 456",
        role = UserRole.CUSTOMER,
        isVerified = true,
        defaultAddress = "Kampala, Uganda"
    )

    val categories = listOf(
        ServiceCategory("cat_1", "Plumbing", FaIcons.Plumbing, 42),
        ServiceCategory("cat_2", "Electrical", FaIcons.Electrical, 38),
        ServiceCategory("cat_3", "Mechanics", FaIcons.Mechanics, 29),
        ServiceCategory("cat_4", "Cleaning", FaIcons.Cleaning, 51),
        ServiceCategory("cat_5", "Repairs", FaIcons.Wrench, 24),
        ServiceCategory("cat_6", "More", FaIcons.Dashboard, 15)
    )

    val providers = listOf(
        ServiceProvider(
            id = "prov_1",
            name = "Bright Plumbing Solutions",
            businessName = "Bright Plumbing Ltd",
            categoryId = "cat_1",
            categoryName = "Plumbing",
            rating = 4.8,
            reviewCount = 304,
            isVerified = true,
            distanceKm = 1.2,
            startingPriceUGX = 50000,
            phone = "+256 701 234 567",
            address = "Kisementi, Kampala",
            aboutText = "Professional and reliable plumbing services for homes and businesses. Fast, clean and affordable.",
            services = listOf(
                ServiceItem("srv_1", "Leak Repair", "Fix leaking pipes, taps, and toilets", 30000),
                ServiceItem("srv_2", "Pipe Installation", "New water or drainage pipe fittings", 70000),
                ServiceItem("srv_3", "Drain Cleaning", "Unclogging kitchen and bathroom drains", 50000)
            )
        ),
        ServiceProvider(
            id = "prov_2",
            name = "Riverside Plumbing",
            businessName = "Riverside Engineering",
            categoryId = "cat_1",
            categoryName = "Plumbing",
            rating = 4.6,
            reviewCount = 182,
            isVerified = true,
            distanceKm = 2.5,
            startingPriceUGX = 45000,
            phone = "+256 702 345 678",
            address = "Nakawa, Kampala",
            aboutText = "Certified residential and commercial plumbing solutions with over 8 years experience in Kampala.",
            services = listOf(
                ServiceItem("srv_4", "Geyser Repair", "Water heater maintenance and installation", 80000),
                ServiceItem("srv_5", "Water Tank Fitting", "Roof water tank connection", 120000)
            )
        ),
        ServiceProvider(
            id = "prov_3",
            name = "HomeFix Plumbers",
            businessName = "HomeFix Services",
            categoryId = "cat_1",
            categoryName = "Plumbing",
            rating = 4.5,
            reviewCount = 95,
            isVerified = false,
            distanceKm = 3.8,
            startingPriceUGX = 40000,
            phone = "+256 703 456 789",
            address = "Ntinda, Kampala",
            aboutText = "Affordable local plumbing fixes and emergency response in Kampala suburbs.",
            services = listOf(
                ServiceItem("srv_6", "Tap Replacement", "Replacing old or broken faucets", 40000)
            )
        ),
        ServiceProvider(
            id = "prov_4",
            name = "Riverside Electricals",
            businessName = "Riverside Electricals",
            categoryId = "cat_2",
            categoryName = "Electrical",
            rating = 4.9,
            reviewCount = 210,
            isVerified = true,
            distanceKm = 1.8,
            startingPriceUGX = 55000,
            phone = "+256 704 567 890",
            address = "Kololo, Kampala",
            aboutText = "Licensed electricians specializing in home wiring, generator maintenance, and circuit repairs.",
            services = listOf(
                ServiceItem("srv_7", "Wiring Fix", "Diagnose and repair short circuits", 55000),
                ServiceItem("srv_8", "Sockets & Switches", "Installation of wall sockets", 35000)
            )
        ),
        ServiceProvider(
            id = "prov_5",
            name = "FixTech Mobile",
            businessName = "FixTech Gadget Repair",
            categoryId = "cat_5",
            categoryName = "Phone Repair",
            rating = 4.7,
            reviewCount = 142,
            isVerified = true,
            distanceKm = 0.8,
            startingPriceUGX = 35000,
            phone = "+256 705 678 901",
            address = "Kampala Road, City Centre",
            aboutText = "Express phone, laptop, and tablet repair. Screen replacement, battery swaps, and software flashing.",
            services = listOf(
                ServiceItem("srv_9", "Screen Replacement", "Original OLED/LCD screen replacement", 85000)
            )
        )
    )

    private val _bookingsFlow = MutableStateFlow(
        listOf(
            Booking(
                id = "bk_101",
                providerId = "prov_1",
                providerName = "Bright Plumbing Solutions",
                categoryName = "Plumbing",
                serviceTitle = "Leak Repair",
                dateString = "Mon, 26 May 2025",
                timeString = "10:00 AM",
                status = BookingStatus.Pending,
                address = "Plot 14 Kisementi, Kampala",
                amountUGX = 50000,
                notes = "Kitchen sink pipe is leaking heavily under the counter."
            ),
            Booking(
                id = "bk_102",
                providerId = "prov_4",
                providerName = "Riverside Electricals",
                categoryName = "Electrical",
                serviceTitle = "Wiring Fix",
                dateString = "Fri, 23 May 2025",
                timeString = "02:00 PM",
                status = BookingStatus.Confirmed,
                address = "Plot 14 Kisementi, Kampala",
                amountUGX = 55000,
                notes = "Living room breaker keeps tripping."
            ),
            Booking(
                id = "bk_103",
                providerId = "prov_5",
                providerName = "FixTech Mobile",
                categoryName = "Phone Repair",
                serviceTitle = "Screen Replacement",
                dateString = "Wed, 21 May 2025",
                timeString = "11:30 AM",
                status = BookingStatus.Completed,
                address = "Kampala Road, City Centre",
                amountUGX = 85000,
                notes = "Cracked front glass on Samsung S21."
            )
        )
    )

    val bookings: Flow<List<Booking>> = _bookingsFlow.asStateFlow()

    fun addBooking(booking: Booking) {
        val current = _bookingsFlow.value.toMutableList()
        current.add(0, booking)
        _bookingsFlow.value = current
    }

    val chatMessages = listOf(
        ChatMessage("m1", "Bright Plumbing Solutions", "Hi, I'd like to fix a leaking tap. Can you provide a quotation for the service?", "09:24 AM", true),
        ChatMessage("m2", "Bright Plumbing Solutions", "Hello! Yes, we can help with that. The cost is UGX 50,000. Would you like to book for tomorrow or another date?", "09:26 AM", false),
        ChatMessage("m3", "Bright Plumbing Solutions", "Tomorrow morning works for me. Thank you!", "09:27 AM", true)
    )

    val notifications = listOf(
        NotificationItem("n1", "Booking Confirmed", "Your booking with Riverside Electricals has been confirmed.", "10 mins ago", "Booking", false),
        NotificationItem("n2", "New Message", "Bright Plumbing Solutions sent you a message.", "1 hr ago", "Message", false),
        NotificationItem("n3", "Price Update", "Plumbing service prices have been updated.", "1 day ago", "Promotion", true),
        NotificationItem("n4", "Special Offer", "Get 15% off all cleaning services this weekend!", "2 days ago", "Promotion", true)
    )

    val walletTransactions = listOf(
        WalletTransaction("tx_1", "Service Payment (Plumbing)", "26 May 2025 - 10:00 AM", 50000, false),
        WalletTransaction("tx_2", "Wallet Top Up", "24 May 2025 - 04:15 PM", 150000, true),
        WalletTransaction("tx_3", "Service Payment (Electrical)", "23 May 2025 - 02:00 PM", 55000, false)
    )
}
