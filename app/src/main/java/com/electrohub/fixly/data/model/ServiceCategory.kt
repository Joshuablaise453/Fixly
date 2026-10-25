package com.electrohub.fixly.data.model

import androidx.compose.ui.graphics.vector.ImageVector

data class ServiceCategory(
    val id: String,
    val name: String,
    val icon: ImageVector,
    val providerCount: Int = 0
)
