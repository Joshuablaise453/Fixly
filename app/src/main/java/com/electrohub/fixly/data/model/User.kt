package com.electrohub.fixly.data.model

enum class UserRole {
    CUSTOMER,
    PROVIDER,
    ADMIN
}

data class User(
    val id: String = "",
    val fullName: String = "",
    val email: String = "",
    val phoneNumber: String = "",
    val role: UserRole = UserRole.CUSTOMER,
    val profilePictureUrl: String? = null,
    val isVerified: Boolean = false,
    val defaultAddress: String = "Kampala, Uganda"
)
