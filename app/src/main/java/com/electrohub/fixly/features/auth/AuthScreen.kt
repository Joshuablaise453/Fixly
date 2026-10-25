package com.electrohub.fixly.features.auth

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.electrohub.fixly.core.ui.components.FaIcon
import com.electrohub.fixly.core.ui.components.FaIcons
import com.electrohub.fixly.core.ui.components.FixlyButton
import com.electrohub.fixly.core.ui.components.FixlyTextField
import com.electrohub.fixly.data.model.UserRole
import com.electrohub.fixly.ui.theme.BorderColor
import com.electrohub.fixly.ui.theme.DarkTeal
import com.electrohub.fixly.ui.theme.FixlyWhite
import com.electrohub.fixly.ui.theme.MainBackground
import com.electrohub.fixly.ui.theme.PrimaryGreen
import com.electrohub.fixly.ui.theme.PrimaryText
import com.electrohub.fixly.ui.theme.SecondaryText

@Composable
fun AuthScreen(
    onLoginSuccess: (UserRole) -> Unit
) {
    var isSignUp by remember { mutableStateOf(false) }
    var selectedRole by remember { mutableStateOf(UserRole.CUSTOMER) }

    var fullName by remember { mutableStateOf("") }
    var emailOrPhone by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var fullNameError by remember { mutableStateOf<String?>(null) }
    var emailError by remember { mutableStateOf<String?>(null) }
    var passwordError by remember { mutableStateOf<String?>(null) }

    var isLoading by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MainBackground)
            .padding(24.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(24.dp))

        // Brand Emblem
        Box(
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape)
                .background(PrimaryGreen),
            contentAlignment = Alignment.Center
        ) {
            FaIcon(
                icon = FaIcons.ShieldCheck,
                contentDescription = null,
                modifier = Modifier.size(36.dp),
                tint = FixlyWhite
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = if (isSignUp) "Create Your Account" else "Welcome Back",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = PrimaryText
        )

        Text(
            text = if (isSignUp) "Join Fixly and get access to trusted service providers." else "Login to your account",
            fontSize = 14.sp,
            color = SecondaryText,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 4.dp, bottom = 24.dp)
        )

        if (isSignUp) {
            // Account Type Selector (Customer vs Provider)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFEBF2F0), RoundedCornerShape(12.dp))
                    .padding(4.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(10.dp))
                        .background(if (selectedRole == UserRole.CUSTOMER) PrimaryGreen else Color.Transparent)
                        .clickable { selectedRole = UserRole.CUSTOMER }
                        .padding(vertical = 10.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Customer",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (selectedRole == UserRole.CUSTOMER) FixlyWhite else SecondaryText
                    )
                }

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(10.dp))
                        .background(if (selectedRole == UserRole.PROVIDER) PrimaryGreen else Color.Transparent)
                        .clickable { selectedRole = UserRole.PROVIDER }
                        .padding(vertical = 10.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Provider",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (selectedRole == UserRole.PROVIDER) FixlyWhite else SecondaryText
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            FixlyTextField(
                value = fullName,
                onValueChange = {
                    fullName = it
                    fullNameError = null
                },
                label = "Full Name",
                placeholder = "John Mwangi",
                leadingIcon = FaIcons.User,
                errorMessage = fullNameError
            )

            Spacer(modifier = Modifier.height(12.dp))

            FixlyTextField(
                value = phoneNumber,
                onValueChange = { phoneNumber = it },
                label = "Phone Number",
                placeholder = "+256 700 123 456",
                leadingIcon = FaIcons.Phone
            )

            Spacer(modifier = Modifier.height(12.dp))
        }

        FixlyTextField(
            value = emailOrPhone,
            onValueChange = {
                emailOrPhone = it
                emailError = null
            },
            label = "Email Address",
            placeholder = "name@example.com",
            leadingIcon = FaIcons.Envelope,
            errorMessage = emailError
        )

        Spacer(modifier = Modifier.height(12.dp))

        FixlyTextField(
            value = password,
            onValueChange = {
                password = it
                passwordError = null
            },
            label = "Password",
            placeholder = "••••••••",
            leadingIcon = FaIcons.Lock,
            isPassword = true,
            errorMessage = passwordError
        )

        if (!isSignUp) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                contentAlignment = Alignment.CenterEnd
            ) {
                Text(
                    text = "Forgot Password?",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = PrimaryGreen,
                    modifier = Modifier.clickable { /* Forgot Password */ }
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        FixlyButton(
            text = if (isSignUp) "Sign Up" else "Log In",
            isLoading = isLoading,
            onClick = {
                // Form validation
                var isValid = true
                if (emailOrPhone.isBlank()) {
                    emailError = "Please enter your email"
                    isValid = false
                }
                if (password.length < 6) {
                    passwordError = "Password must be at least 6 characters"
                    isValid = false
                }
                if (isSignUp && fullName.isBlank()) {
                    fullNameError = "Please enter your name"
                    isValid = false
                }

                if (isValid) {
                    isLoading = true
                    onLoginSuccess(if (isSignUp) selectedRole else UserRole.CUSTOMER)
                }
            }
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Toggle Auth mode
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = if (isSignUp) "Already have an account? " else "Don't have an account? ",
                fontSize = 14.sp,
                color = SecondaryText
            )
            Text(
                text = if (isSignUp) "Log In" else "Sign Up",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = PrimaryGreen,
                modifier = Modifier.clickable { isSignUp = !isSignUp }
            )
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}
