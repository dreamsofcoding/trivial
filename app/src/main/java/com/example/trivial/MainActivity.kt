package com.example.trivial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.trivial.ui.theme.TrivialTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            TrivialMain()
        }
    }
}


@Composable
fun TrivialMain() {
    TrivialTheme {
        val navController = rememberNavController()

        Scaffold (
            contentWindowInsets = WindowInsets.systemBars
        ) { innerPadding ->
            TrivialNavHost(
                navController = navController,
                modifier = Modifier.padding(innerPadding)
            )

        }

    }
}

