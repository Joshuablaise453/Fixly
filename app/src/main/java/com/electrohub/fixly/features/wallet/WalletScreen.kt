package com.electrohub.fixly.features.wallet

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.electrohub.fixly.core.ui.components.FaIcon
import com.electrohub.fixly.core.ui.components.FaIcons
import com.electrohub.fixly.core.ui.components.FixlyCard
import com.electrohub.fixly.data.repository.MockDataRepository
import com.electrohub.fixly.ui.theme.DarkTeal
import com.electrohub.fixly.ui.theme.ErrorRed
import com.electrohub.fixly.ui.theme.FixlyWhite
import com.electrohub.fixly.ui.theme.MainBackground
import com.electrohub.fixly.ui.theme.PrimaryGreen
import com.electrohub.fixly.ui.theme.PrimaryText
import com.electrohub.fixly.ui.theme.SecondaryText

@Composable
fun WalletScreen(
    onBackClick: () -> Unit
) {
    val transactions = remember { MockDataRepository.walletTransactions }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MainBackground)
    ) {
        // Header Bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(FixlyWhite)
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

            Text(
                text = "Wallet",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = PrimaryText
            )
        }

        Column(modifier = Modifier.padding(horizontal = 20.dp)) {
            // Balance Card
            FixlyCard(backgroundColor = DarkTeal) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Available Balance",
                        fontSize = 14.sp,
                        color = FixlyWhite.copy(alpha = 0.8f)
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "UGX 120,000",
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        color = FixlyWhite
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        WalletActionButton("Top Up", FaIcons.Plus) { }
                        WalletActionButton("Withdraw", FaIcons.Wallet) { }
                        WalletActionButton("History", FaIcons.Clock) { }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Recent Transactions",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = PrimaryText
            )

            Spacer(modifier = Modifier.height(12.dp))

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(transactions) { tx ->
                    FixlyCard {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Box(
                                    modifier = Modifier
                                        .size(40.dp)
                                        .clip(CircleShape)
                                        .background(if (tx.isCredit) Color(0xFFE8F5E9) else Color(0xFFFFEBEE)),
                                    contentAlignment = Alignment.Center
                                ) {
                                    FaIcon(
                                        icon = if (tx.isCredit) FaIcons.Plus else FaIcons.Wallet,
                                        contentDescription = null,
                                        modifier = Modifier.size(18.dp),
                                        tint = if (tx.isCredit) PrimaryGreen else ErrorRed
                                    )
                                }

                                Spacer(modifier = Modifier.width(12.dp))

                                Column {
                                    Text(
                                        text = tx.title,
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = PrimaryText
                                    )
                                    Text(
                                        text = tx.date,
                                        fontSize = 12.sp,
                                        color = SecondaryText,
                                        modifier = Modifier.padding(top = 2.dp)
                                    )
                                }
                            }

                            Text(
                                text = "${if (tx.isCredit) "+" else "-"} UGX ${tx.amountUGX}",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = if (tx.isCredit) PrimaryGreen else ErrorRed
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun WalletActionButton(
    label: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable { onClick() }
    ) {
        Box(
            modifier = Modifier
                .size(44.dp)
                .clip(CircleShape)
                .background(FixlyWhite.copy(alpha = 0.2f)),
            contentAlignment = Alignment.Center
        ) {
            FaIcon(
                icon = icon,
                contentDescription = label,
                modifier = Modifier.size(20.dp),
                tint = FixlyWhite
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = label,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            color = FixlyWhite
        )
    }
}
