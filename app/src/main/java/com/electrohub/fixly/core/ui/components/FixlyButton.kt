package com.electrohub.fixly.core.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.electrohub.fixly.ui.theme.BorderColor
import com.electrohub.fixly.ui.theme.DarkTeal
import com.electrohub.fixly.ui.theme.FixlyWhite
import com.electrohub.fixly.ui.theme.PrimaryGreen

enum class FixlyButtonVariant {
    Primary,
    Secondary,
    Outline
}

@Composable
fun FixlyButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    variant: FixlyButtonVariant = FixlyButtonVariant.Primary,
    enabled: Boolean = true,
    isLoading: Boolean = false,
    icon: ImageVector? = null
) {
    val shape = RoundedCornerShape(12.dp)

    val containerColor = when (variant) {
        FixlyButtonVariant.Primary -> PrimaryGreen
        FixlyButtonVariant.Secondary -> DarkTeal
        FixlyButtonVariant.Outline -> Color.Transparent
    }

    val contentColor = when (variant) {
        FixlyButtonVariant.Primary -> FixlyWhite
        FixlyButtonVariant.Secondary -> FixlyWhite
        FixlyButtonVariant.Outline -> PrimaryGreen
    }

    if (variant == FixlyButtonVariant.Outline) {
        OutlinedButton(
            onClick = onClick,
            modifier = modifier
                .fillMaxWidth()
                .height(52.dp),
            enabled = enabled && !isLoading,
            shape = shape,
            border = BorderStroke(1.5.dp, if (enabled) PrimaryGreen else BorderColor),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = contentColor
            ),
            contentPadding = PaddingValues(horizontal = 20.dp, vertical = 12.dp)
        ) {
            ButtonContent(text, icon, isLoading, contentColor)
        }
    } else {
        Button(
            onClick = onClick,
            modifier = modifier
                .fillMaxWidth()
                .height(52.dp),
            enabled = enabled && !isLoading,
            shape = shape,
            colors = ButtonDefaults.buttonColors(
                containerColor = containerColor,
                contentColor = contentColor,
                disabledContainerColor = containerColor.copy(alpha = 0.5f),
                disabledContentColor = FixlyWhite.copy(alpha = 0.7f)
            ),
            contentPadding = PaddingValues(horizontal = 20.dp, vertical = 12.dp)
        ) {
            ButtonContent(text, icon, isLoading, contentColor)
        }
    }
}

@Composable
private fun ButtonContent(
    text: String,
    icon: ImageVector?,
    isLoading: Boolean,
    contentColor: Color
) {
    if (isLoading) {
        CircularProgressIndicator(
            modifier = Modifier.size(24.dp),
            color = contentColor,
            strokeWidth = 2.5.dp
        )
    } else {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (icon != null) {
                FaIcon(
                    icon = icon,
                    contentDescription = null,
                    modifier = Modifier.size(18.dp),
                    tint = contentColor
                )
                Spacer(modifier = Modifier.width(8.dp))
            }
            Text(
                text = text,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = contentColor
            )
        }
    }
}
