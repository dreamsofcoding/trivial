package com.example.trivial.ui.scores

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.trivial.data.QuizViewModel
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.sp

@Composable
fun ScoresScreen(
    viewModel: QuizViewModel = hiltViewModel(),
) {
    val scores by viewModel.topScores

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "High Scores",
            fontSize = 24.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            itemsIndexed(scores.distinctBy { it.score }) { index, score ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Absolute.Center
                ) {
                    Text(text = "${index + 1}.")
                    Text(text = "Score: ${score.score}")
                }
            }
        }
    }
}