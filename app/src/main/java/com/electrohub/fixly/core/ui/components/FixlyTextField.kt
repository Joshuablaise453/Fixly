package com.electrohub.fixly.core.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.electrohub.fixly.ui.theme.BorderColor
import com.electrohub.fixly.ui.theme.ErrorRed
import com.electrohub.fixly.ui.theme.FixlyWhite
import com.electrohub.fixly.ui.theme.InputBackground
import com.electrohub.fixly.ui.theme.PrimaryGreen
import com.electrohub.fixly.ui.theme.PrimaryText
import com.electrohub.fixly.ui.theme.SecondaryText

@Composable
fun FixlyTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    placeholder: String = "",
    leadingIcon: ImageVector? = null,
    isPassword: Boolean = false,
    errorMessage: String? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = true
) {
    var passwordVisible by remember { mutableStateOf(false) }

    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = label,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = PrimaryText,
            modifier = Modifier.padding(bottom = 6.dp)
        )

        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                if (placeholder.isNotEmpty()) {
                    Text(text = placeholder, color = SecondaryText, fontSize = 15.sp)
                }
            },
            leadingIcon = if (leadingIcon != null) {
                {
                    FaIcon(
                        icon = leadingIcon,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp),
                        tint = SecondaryText
                    )
                }
            } else null,
            trailingIcon = if (isPassword) {
                {
                    Box(
                        modifier = Modifier
                            .clickable { passwordVisible = !passwordVisible }
                            .padding(8.dp)
                    ) {
                        FaIcon(
                            icon = FaIcons.Lock,
                            contentDescription = if (passwordVisible) "Hide password" else "Show password",
                            modifier = Modifier.size(18.dp),
                            tint = if (passwordVisible) PrimaryGreen else SecondaryText
                        )
                    }
                }
            } else null,
            visualTransformation = if (isPassword && !passwordVisible) PasswordVisualTransformation() else VisualTransformation.None,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            singleLine = singleLine,
            isError = errorMessage != null,
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = InputBackground,
                unfocusedContainerColor = InputBackground,
                disabledContainerColor = InputBackground,
                errorContainerColor = InputBackground,
                focusedBorderColor = PrimaryGreen,
                unfocusedBorderColor = BorderColor,
                errorBorderColor = ErrorRed,
                focusedTextColor = PrimaryText,
                unfocusedTextColor = PrimaryText
            )
        )

        if (errorMessage != null) {
            Text(
                text = errorMessage,
                color = ErrorRed,
                fontSize = 12.sp,
                modifier = Modifier.padding(top = 4.dp, start = 4.dp)
            )
        }
    }
}
