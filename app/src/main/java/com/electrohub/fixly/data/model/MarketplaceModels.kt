package com.electrohub.fixly.data.model

data class ChatMessage(
    val id: String,
    val senderName: String,
    val text: String,
    val timestamp: String,
    val isFromUser: Boolean
)

data class NotificationItem(
    val id: String,
    val title: String,
    val message: String,
    val timeAgo: String,
    val type: String, // "Booking", "Message", "Promotion"
    val isRead: Boolean = false
)

data class WalletTransaction(
    val id: String,
    val title: String,
    val date: String,
    val amountUGX: Long,
    val isCredit: Boolean
)
