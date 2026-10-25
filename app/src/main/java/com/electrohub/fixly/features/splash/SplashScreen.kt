package com.electrohub.fixly.features.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.electrohub.fixly.core.ui.components.FaIcon
import com.electrohub.fixly.core.ui.components.FaIcons
import com.electrohub.fixly.ui.theme.FixlyWhite
import com.electrohub.fixly.ui.theme.PrimaryGreen
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    onNavigateToOnboarding: () -> Unit
) {
    LaunchedEffect(Unit) {
        delay(2000)
        onNavigateToOnboarding()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(PrimaryGreen),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(24.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(96.dp)
                    .clip(CircleShape)
                    .background(FixlyWhite),
                contentAlignment = Alignment.Center
            ) {
                FaIcon(
                    icon = FaIcons.ShieldCheck,
                    contentDescription = "Fixly Logo",
                    modifier = Modifier.size(56.dp),
                    tint = PrimaryGreen
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Fixly",
                fontSize = 42.sp,
                fontWeight = FontWeight.Bold,
                color = FixlyWhite
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Find trusted service providers",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = FixlyWhite.copy(alpha = 0.9f)
            )
        }
    }
}
