package com.example.trivial.ui.about

import com.example.trivial.BuildConfig
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.trivial.R

@Composable
fun AboutScreen() {

    val image = painterResource(id = R.drawable.about_android_trivia)

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)
    ) {

        Image(
            painter = image,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(bottom = 16.dp),
            contentScale = ContentScale.Fit
        )

        Text(
            text = "Trivial is a fun quiz app designed to test your general knowledge and entertain. " +
                    "Created with ❤️ using Jetpack Compose and Kotlin.",
            fontSize = 16.sp
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text("Version: ${BuildConfig.VERSION_NAME}", fontSize = 14.sp)
        Spacer(modifier = Modifier.height(8.dp))
        Text("Developer: dreamsofcoding", fontSize = 14.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun AboutScreenPreview() {
    AboutScreen(
    )
}