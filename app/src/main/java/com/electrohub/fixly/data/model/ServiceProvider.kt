package com.electrohub.fixly.data.model

data class ServiceItem(
    val id: String,
    val name: String,
    val description: String,
    val priceUGX: Long
)

data class ServiceProvider(
    val id: String,
    val name: String,
    val businessName: String,
    val categoryId: String,
    val categoryName: String,
    val rating: Double,
    val reviewCount: Int,
    val isVerified: Boolean,
    val distanceKm: Double,
    val startingPriceUGX: Long,
    val imageUrl: String = "",
    val phone: String,
    val address: String,
    val aboutText: String,
    val services: List<ServiceItem> = emptyList()
)
