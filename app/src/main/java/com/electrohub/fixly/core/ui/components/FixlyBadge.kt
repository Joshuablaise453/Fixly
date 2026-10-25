package com.electrohub.fixly.core.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.electrohub.fixly.ui.theme.ConfirmedStatusBg
import com.electrohub.fixly.ui.theme.ConfirmedStatusText
import com.electrohub.fixly.ui.theme.ErrorRed
import com.electrohub.fixly.ui.theme.PendingStatusBg
import com.electrohub.fixly.ui.theme.PendingStatusText
import com.electrohub.fixly.ui.theme.SuccessGreen
import java.util.Locale

@Composable
fun VerifiedBadge(
    modifier: Modifier = Modifier,
    text: String = "Verified"
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .background(ConfirmedStatusBg, RoundedCornerShape(20.dp))
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        FaIcon(
            icon = FaIcons.ShieldCheck,
            contentDescription = "Verified",
            modifier = Modifier.size(14.dp),
            tint = SuccessGreen
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = text,
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold,
            color = SuccessGreen
        )
    }
}

enum class BookingStatus(val label: String, val bgColor: Color, val textColor: Color) {
    Pending("Pending", PendingStatusBg, PendingStatusText),
    Confirmed("Confirmed", ConfirmedStatusBg, ConfirmedStatusText),
    Completed("Completed", ConfirmedStatusBg, SuccessGreen),
    Cancelled("Cancelled", Color(0xFFFFEBEE), ErrorRed)
}

@Composable
fun StatusChip(
    status: BookingStatus,
    modifier: Modifier = Modifier
) {
    Text(
        text = status.label,
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold,
        color = status.textColor,
        modifier = modifier
            .background(status.bgColor, RoundedCornerShape(12.dp))
            .padding(horizontal = 10.dp, vertical = 4.dp)
    )
}

@Composable
fun RatingBadge(
    rating: Double,
    reviewCount: Int,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        FaIcon(
            icon = FaIcons.Star,
            contentDescription = "Rating",
            modifier = Modifier.size(16.dp),
            tint = Color(0xFFFFB800)
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = String.format(Locale.US, "%.1f", rating),
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF12313B)
        )
        Text(
            text = " ($reviewCount reviews)",
            fontSize = 12.sp,
            color = Color(0xFF647780)
        )
    }
}
