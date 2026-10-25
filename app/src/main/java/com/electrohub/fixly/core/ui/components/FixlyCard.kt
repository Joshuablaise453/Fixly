package com.electrohub.fixly.core.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.electrohub.fixly.ui.theme.BorderColor
import com.electrohub.fixly.ui.theme.CardBackground

@Composable
fun FixlyCard(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    border: BorderStroke? = BorderStroke(1.dp, BorderColor),
    backgroundColor: Color = CardBackground,
    cornerRadius: Dp = 16.dp,
    content: @Composable BoxScope.() -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .then(if (onClick != null) Modifier.clickable { onClick() } else Modifier),
        shape = RoundedCornerShape(cornerRadius),
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        border = border,
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Box(modifier = Modifier.padding(16.dp)) {
            content()
        }
    }
}
