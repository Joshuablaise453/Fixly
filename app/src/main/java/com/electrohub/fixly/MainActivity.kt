package com.electrohub.fixly

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.electrohub.fixly.core.navigation.FixlyApp
import com.electrohub.fixly.ui.theme.FixlyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FixlyTheme {
                FixlyApp()
            }
        }
    }
}
