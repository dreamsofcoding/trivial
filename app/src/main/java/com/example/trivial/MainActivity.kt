package com.example.trivial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.trivial.ui.settings.SettingsScreen
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

        Scaffold(
            contentWindowInsets = WindowInsets.systemBars,
            topBar = { TopAppBarWithMenu(navController) },
            content = { innerPadding ->
                TrivialNavHost(
                    navController = navController,
                    modifier = Modifier.padding(innerPadding)
                )

            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarWithMenu(navController: NavHostController) {
    var showMenu by remember { mutableStateOf(false) }

    TopAppBar(
        title = { Text("Trivial") },
        actions = {
            IconButton(onClick = { showMenu = true }) {
                Icon(Icons.Default.MoreVert, contentDescription = "Menu")
            }

            DropdownMenu(
                expanded = showMenu,
                onDismissRequest = { showMenu = false }
            ) {
                DropdownMenuItem(
                    text = { Text("Settings") },
                    onClick = {
                        navController.navigate(Settings.route)
                        showMenu = false
                    }
                )
                DropdownMenuItem(
                    text = { Text("About") },
                    onClick = {
                        navController.navigate(About.route)
                        showMenu = false
                    }
                )
            }
        }
    )
}


