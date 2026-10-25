package com.electrohub.fixly.features.onboarding

import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.electrohub.fixly.core.ui.components.FaIcon
import com.electrohub.fixly.core.ui.components.FaIcons
import com.electrohub.fixly.core.ui.components.FixlyButton
import com.electrohub.fixly.ui.theme.DarkTeal
import com.electrohub.fixly.ui.theme.MainBackground
import com.electrohub.fixly.ui.theme.PrimaryGreen
import com.electrohub.fixly.ui.theme.PrimaryText
import com.electrohub.fixly.ui.theme.SecondaryText

data class OnboardingPageData(
    val title: String,
    val description: String,
    val icon: ImageVector
)

@Composable
fun OnboardingScreen(
    onFinishOnboarding: () -> Unit
) {
    val pages = listOf(
        OnboardingPageData(
            title = "Trusted Service\nProviders Near You",
            description = "Find reliable plumbers, electricians, mechanics, cleaners and phone repair technicians near you.",
            icon = FaIcons.Home
        ),
        OnboardingPageData(
            title = "Verified & Trusted",
            description = "All providers are verified, rated by real customers and background checked for your safety.",
            icon = FaIcons.ShieldCheck
        ),
        OnboardingPageData(
            title = "Book with Ease",
            description = "Search nearby, compare prices, request quotations and book in just a few taps.",
            icon = FaIcons.MapMarker
        )
    )

    var currentPage by remember { mutableIntStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MainBackground)
            .padding(24.dp)
    ) {
        // Top Bar with Skip
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (currentPage < pages.size - 1) {
                Text(
                    text = "Skip",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = SecondaryText,
                    modifier = Modifier
                        .clickable { onFinishOnboarding() }
                        .padding(8.dp)
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        // Center Illustration Icon
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .size(160.dp)
                    .clip(CircleShape)
                    .background(PrimaryGreen.copy(alpha = 0.1f)),
                contentAlignment = Alignment.Center
            ) {
                FaIcon(
                    icon = pages[currentPage].icon,
                    contentDescription = null,
                    modifier = Modifier.size(80.dp),
                    tint = PrimaryGreen
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Title and Description
        Text(
            text = pages[currentPage].title,
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = PrimaryText,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = pages[currentPage].description,
            fontSize = 15.sp,
            color = SecondaryText,
            textAlign = TextAlign.Center,
            lineHeight = 22.sp,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.weight(1f))

        // Page Indicator Dots
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            pages.indices.forEach { index ->
                val isSelected = index == currentPage
                Box(
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .size(
                            width = if (isSelected) 24.dp else 8.dp,
                            height = 8.dp
                        )
                        .clip(RoundedCornerShape(4.dp))
                        .background(if (isSelected) PrimaryGreen else DarkTeal.copy(alpha = 0.2f))
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Action Button
        FixlyButton(
            text = if (currentPage == pages.size - 1) "Get Started" else "Next",
            onClick = {
                if (currentPage < pages.size - 1) {
                    currentPage++
                } else {
                    onFinishOnboarding()
                }
            }
        )
    }
}
