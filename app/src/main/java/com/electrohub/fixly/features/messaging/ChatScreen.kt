package com.electrohub.fixly.features.messaging

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.electrohub.fixly.core.ui.components.FaIcon
import com.electrohub.fixly.core.ui.components.FaIcons
import com.electrohub.fixly.core.ui.components.FixlyTextField
import com.electrohub.fixly.data.model.ChatMessage
import com.electrohub.fixly.data.repository.MockDataRepository
import com.electrohub.fixly.ui.theme.DarkTeal
import com.electrohub.fixly.ui.theme.FixlyWhite
import com.electrohub.fixly.ui.theme.MainBackground
import com.electrohub.fixly.ui.theme.PrimaryGreen
import com.electrohub.fixly.ui.theme.PrimaryText
import com.electrohub.fixly.ui.theme.SecondaryText

@Composable
fun ChatScreen(
    providerId: String,
    onBackClick: () -> Unit
) {
    val provider = remember(providerId) {
        MockDataRepository.providers.find { it.id == providerId } ?: MockDataRepository.providers.first()
    }

    val messages = remember { mutableStateListOf(*MockDataRepository.chatMessages.toTypedArray()) }
    var inputMessage by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MainBackground)
    ) {
        // Top Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(FixlyWhite)
                .padding(horizontal = 20.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(MainBackground)
                    .clickable { onBackClick() },
                contentAlignment = Alignment.Center
            ) {
                FaIcon(
                    icon = FaIcons.ArrowLeft,
                    contentDescription = "Back",
                    modifier = Modifier.size(20.dp),
                    tint = DarkTeal
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(DarkTeal),
                contentAlignment = Alignment.Center
            ) {
                FaIcon(
                    icon = FaIcons.User,
                    contentDescription = null,
                    modifier = Modifier.size(22.dp),
                    tint = FixlyWhite
                )
            }

            Spacer(modifier = Modifier.width(10.dp))

            Column {
                Text(
                    text = provider.name,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryText
                )
                Text(
                    text = "Online",
                    fontSize = 12.sp,
                    color = PrimaryGreen,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }

        // Messages Area
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 20.dp, vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(messages) { msg ->
                ChatBubble(message = msg)
            }
        }

        // Input Area
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(FixlyWhite)
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier.weight(1f)) {
                FixlyTextField(
                    value = inputMessage,
                    onValueChange = { inputMessage = it },
                    label = "",
                    placeholder = "Type a message..."
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(PrimaryGreen)
                    .clickable {
                        if (inputMessage.isNotBlank()) {
                            messages.add(
                                ChatMessage(
                                    id = "msg_${System.currentTimeMillis()}",
                                    senderName = "Me",
                                    text = inputMessage,
                                    timestamp = "Just now",
                                    isFromUser = true
                                )
                            )
                            inputMessage = ""
                        }
                    },
                contentAlignment = Alignment.Center
            ) {
                FaIcon(
                    icon = FaIcons.ChevronRight,
                    contentDescription = "Send",
                    modifier = Modifier.size(20.dp),
                    tint = FixlyWhite
                )
            }
        }
    }
}

@Composable
fun ChatBubble(message: ChatMessage) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = if (message.isFromUser) Alignment.CenterEnd else Alignment.CenterStart
    ) {
        Column(
            modifier = Modifier
                .clip(
                    RoundedCornerShape(
                        topStart = 16.dp,
                        topEnd = 16.dp,
                        bottomStart = if (message.isFromUser) 16.dp else 2.dp,
                        bottomEnd = if (message.isFromUser) 2.dp else 16.dp
                    )
                )
                .background(if (message.isFromUser) PrimaryGreen else DarkTeal)
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            Text(
                text = message.text,
                fontSize = 14.sp,
                color = FixlyWhite
            )
            Text(
                text = message.timestamp,
                fontSize = 10.sp,
                color = FixlyWhite.copy(alpha = 0.7f),
                modifier = Modifier.align(Alignment.End).padding(top = 4.dp)
            )
        }
    }
}
