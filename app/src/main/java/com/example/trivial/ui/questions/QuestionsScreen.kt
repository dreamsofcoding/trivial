package com.example.trivial.ui.questions

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.trivial.R
import com.example.trivial.data.QuizViewModel
import com.example.trivial.data.TrivialDatabase

@Composable
fun QuestionsScreen(
    viewModel: QuizViewModel = hiltViewModel(),
    onNavigateToScores: () -> Unit
) {
    val question = viewModel.currentQuestion
    val index = viewModel.currentIndex + 1
    val total = viewModel.totalQuestions
    val score = viewModel.score

    QuestionView(
        question = question,
        onAnswerSelected = { selectedIndex ->
            viewModel.submitAnswer(selectedIndex)
        },
        onNavigateToScores = {
            viewModel.saveScore()
            onNavigateToScores.invoke() },
        currentIndex = index,
        totalQuestions = total,
        currentScore = score
    )
}

@Composable
fun QuestionView(
    question: TrivialDatabase.QuestionEntity,
    onAnswerSelected: (Int) -> Unit,
    onNavigateToScores: () -> Unit,
    currentIndex: Int,
    totalQuestions: Int,
    currentScore: Int
) {

    var selectedIndex by remember { mutableIntStateOf(-1) }
    val image = painterResource(id = R.drawable.android_category_simple)

    Column(modifier = Modifier.padding(16.dp)) {

        Image(
            painter = image,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(bottom = 16.dp),
            contentScale = ContentScale.Fit
        )

        // Score and Question Number Row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Question $currentIndex of $totalQuestions",
                modifier = Modifier
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "Score: $currentScore"
            )
        }


        // Question Text
        Text(
            text = question.text,
            fontSize = 20.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Answers as radio options
        question.answers.forEachIndexed { index, answer ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { selectedIndex = index }
                    .padding(vertical = 4.dp)
            ) {
                RadioButton(
                    selected = selectedIndex == index,
                    onClick = { selectedIndex = index }
                )
                Text(
                    text = answer,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (currentIndex == totalQuestions) {
                    onNavigateToScores.invoke()
                } else {
                    onAnswerSelected(selectedIndex)
                    selectedIndex = -1
                }
            },
            enabled = selectedIndex != -1,
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Submit")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun QuestionViewPreview() {
    val mockQuestion = TrivialDatabase.QuestionEntity(
        id = 1,
        text = "Which planet is known as the Red Planet?",
        answers = listOf("Earth", "Mars", "Jupiter", "Venus"),
        correctAnswerIndex = 1
    )

    QuestionView(
        question = mockQuestion,
        onAnswerSelected = {},
        onNavigateToScores = {},
        currentIndex = 3,
        totalQuestions = 10,
        currentScore = 2
    )
}
