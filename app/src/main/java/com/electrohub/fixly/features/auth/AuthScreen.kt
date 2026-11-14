package com.electrohub.fixly.features.auth

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.electrohub.fixly.R
import com.electrohub.fixly.core.ui.components.FaIcon
import com.electrohub.fixly.core.ui.components.FaIcons
import com.electrohub.fixly.core.ui.components.FixlyButton
import com.electrohub.fixly.core.ui.components.FixlyTextField
import com.electrohub.fixly.data.model.UserRole
import com.electrohub.fixly.ui.theme.BorderColor
import com.electrohub.fixly.ui.theme.FixlyWhite
import com.electrohub.fixly.ui.theme.MainBackground
import com.electrohub.fixly.ui.theme.PrimaryGreen
import com.electrohub.fixly.ui.theme.PrimaryText
import com.electrohub.fixly.ui.theme.SecondaryText
import kotlin.math.roundToInt

/**
 * Custom modifier adding a tactile spring bounce on press.
 */
@Composable
fun Modifier.bouncyClickable(
    enabled: Boolean = true,
    onClick: () -> Unit
): Modifier {
    var isPressed by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.93f else 1f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessMedium
        ),
        label = "bouncyScale"
    )

    return this
        .graphicsLayer {
            scaleX = scale
            scaleY = scale
        }
        .pointerInput(enabled) {
            detectTapGestures(
                onPress = {
                    if (enabled) {
                        isPressed = true
                        tryAwaitRelease()
                        isPressed = false
                    }
                },
                onTap = {
                    if (enabled) onClick()
                }
            )
        }
}

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

    // Infinite breathing and bouncing animation for logo and glowing halo
    val infiniteTransition = rememberInfiniteTransition(label = "logoBounce")
    val logoScale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.07f,
        animationSpec = infiniteRepeatable(
            animation = tween(2200, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "logoScale"
    )
    val logoOffsetY by infiniteTransition.animateFloat(
        initialValue = -5f,
        targetValue = 5f,
        animationSpec = infiniteRepeatable(
            animation = tween(1800, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "logoOffsetY"
    )

    val glowAlpha by infiniteTransition.animateFloat(
        initialValue = 0.35f,
        targetValue = 0.75f,
        animationSpec = infiniteRepeatable(
            animation = tween(1600, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "glowAlpha"
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFE8F5F0),
                        MainBackground,
                        Color(0xFFEFF7F4)
                    )
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(36.dp))

            // Animated Glowing Ambient Halo & Fixly Logo from Drawables (R.drawable.fixly)
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .offset { IntOffset(0, logoOffsetY.roundToInt()) }
                    .graphicsLayer {
                        scaleX = logoScale
                        scaleY = logoScale
                    }
            ) {
                // Soft Radial Glowing Background Aura
                Box(
                    modifier = Modifier
                        .size(110.dp)
                        .clip(CircleShape)
                        .background(
                            Brush.radialGradient(
                                colors = listOf(
                                    PrimaryGreen.copy(alpha = glowAlpha * 0.6f),
                                    PrimaryGreen.copy(alpha = glowAlpha * 0.2f),
                                    Color.Transparent
                                )
                            )
                        )
                )

                // White Container Card for Fixly Logo
                Surface(
                    modifier = Modifier.size(86.dp),
                    shape = CircleShape,
                    color = FixlyWhite,
                    shadowElevation = 8.dp,
                    border = BorderStroke(2.dp, PrimaryGreen.copy(alpha = 0.3f))
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.fixly),
                            contentDescription = "Fixly Logo",
                            modifier = Modifier
                                .size(64.dp)
                                .clip(CircleShape),
                            contentScale = ContentScale.Fit
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = if (isSignUp) "Create Account" else "Welcome Back",
                fontSize = 26.sp,
                fontWeight = FontWeight.ExtraBold,
                color = PrimaryText,
                letterSpacing = (-0.5).sp
            )

            Text(
                text = if (isSignUp) "Join Fixly & connect with verified experts." else "Sign in to access your services & bookings",
                fontSize = 14.sp,
                color = SecondaryText,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 6.dp, bottom = 24.dp, start = 12.dp, end = 12.dp)
            )

            // Modern Form Container Box with Glowing Gradient Border
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(12.dp, shape = RoundedCornerShape(24.dp), spotColor = PrimaryGreen.copy(alpha = 0.15f)),
                shape = RoundedCornerShape(24.dp),
                color = FixlyWhite,
                border = BorderStroke(1.dp, Brush.horizontalGradient(listOf(PrimaryGreen.copy(0.25f), BorderColor, PrimaryGreen.copy(0.15f))))
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                ) {
                    if (isSignUp) {
                        // Bouncy Animated Role Switcher (Customer vs Provider)
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color(0xFFF0F5F3), RoundedCornerShape(14.dp))
                                .padding(4.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            val customerBg by animateColorAsState(
                                targetValue = if (selectedRole == UserRole.CUSTOMER) PrimaryGreen else Color.Transparent,
                                animationSpec = spring(stiffness = Spring.StiffnessLow),
                                label = "customerBg"
                            )
                            val providerBg by animateColorAsState(
                                targetValue = if (selectedRole == UserRole.PROVIDER) PrimaryGreen else Color.Transparent,
                                animationSpec = spring(stiffness = Spring.StiffnessLow),
                                label = "providerBg"
                            )

                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(customerBg)
                                    .bouncyClickable { selectedRole = UserRole.CUSTOMER }
                                    .padding(vertical = 11.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    FaIcon(
                                        icon = FaIcons.User,
                                        contentDescription = null,
                                        modifier = Modifier.size(16.dp),
                                        tint = if (selectedRole == UserRole.CUSTOMER) FixlyWhite else SecondaryText
                                    )
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text(
                                        text = "Customer",
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = if (selectedRole == UserRole.CUSTOMER) FixlyWhite else SecondaryText
                                    )
                                }
                            }

                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(providerBg)
                                    .bouncyClickable { selectedRole = UserRole.PROVIDER }
                                    .padding(vertical = 11.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    FaIcon(
                                        icon = FaIcons.ShieldCheck,
                                        contentDescription = null,
                                        modifier = Modifier.size(16.dp),
                                        tint = if (selectedRole == UserRole.PROVIDER) FixlyWhite else SecondaryText
                                    )
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text(
                                        text = "Provider",
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = if (selectedRole == UserRole.PROVIDER) FixlyWhite else SecondaryText
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))
                    }

                    // Dynamic Smooth Animated Field Transitions
                    AnimatedVisibility(
                        visible = isSignUp,
                        enter = fadeIn() + expandVertically(),
                        exit = fadeOut() + shrinkVertically()
                    ) {
                        Column {
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
                                fontWeight = FontWeight.Bold,
                                color = PrimaryGreen,
                                modifier = Modifier.bouncyClickable { /* Forgot Password action */ }
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    // Bouncy Animated Primary Button
                    Box(modifier = Modifier.bouncyClickable(enabled = !isLoading) {
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
                    }) {
                        FixlyButton(
                            text = if (isSignUp) "Sign Up" else "Log In",
                            isLoading = isLoading,
                            onClick = { /* Handled by bouncy container */ }
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    // Modern Divider with subtle line glow
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        HorizontalDivider(
                            modifier = Modifier.weight(1f),
                            color = BorderColor,
                            thickness = 1.dp
                        )
                        Text(
                            text = "Or continue with",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Medium,
                            color = SecondaryText,
                            modifier = Modifier.padding(horizontal = 12.dp)
                        )
                        HorizontalDivider(
                            modifier = Modifier.weight(1f),
                            color = BorderColor,
                            thickness = 1.dp
                        )
                    }

                    Spacer(modifier = Modifier.height(18.dp))

                    // Bouncy Social Login Buttons (Google, Facebook, Apple)
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        SocialAuthButton(
                            icon = FaIcons.Google,
                            label = "Google",
                            onClick = { onLoginSuccess(selectedRole) },
                            modifier = Modifier.weight(1f)
                        )
                        SocialAuthButton(
                            icon = FaIcons.Facebook,
                            label = "Facebook",
                            onClick = { onLoginSuccess(selectedRole) },
                            modifier = Modifier.weight(1f)
                        )
                        SocialAuthButton(
                            icon = FaIcons.Apple,
                            label = "Apple",
                            onClick = { onLoginSuccess(selectedRole) },
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(28.dp))

            // Toggle Auth mode with bouncy spring transition
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .bouncyClickable {
                        isSignUp = !isSignUp
                        fullNameError = null
                        emailError = null
                        passwordError = null
                    }
                    .padding(8.dp)
            ) {
                Text(
                    text = if (isSignUp) "Already have an account? " else "Don't have an account? ",
                    fontSize = 14.sp,
                    color = SecondaryText
                )
                Text(
                    text = if (isSignUp) "Log In" else "Sign Up",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = PrimaryGreen
                )
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun SocialAuthButton(
    icon: ImageVector,
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    iconTint: Color = Color.Unspecified
) {
    Surface(
        modifier = modifier
            .height(48.dp)
            .bouncyClickable(onClick = onClick),
        shape = RoundedCornerShape(14.dp),
        color = FixlyWhite,
        shadowElevation = 2.dp,
        border = BorderStroke(1.dp, BorderColor)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            FaIcon(
                icon = icon,
                contentDescription = label,
                modifier = Modifier.size(20.dp),
                tint = iconTint
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = label,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = PrimaryText
            )
        }
    }
}
