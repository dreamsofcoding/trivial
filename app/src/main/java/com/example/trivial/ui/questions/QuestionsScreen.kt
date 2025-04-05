package com.example.trivial.ui.questions

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.trivial.data.QuizViewModel
import com.example.trivial.data.TrivialDatabase

@Composable
fun QuestionsScreen(
    viewModel: QuizViewModel,
    onNextQuestionSelected: () -> Unit,
    onNavigateToScores: () -> Unit
) {
    val question = viewModel.currentQuestion

    QuestionView(
        question = question,
        onAnswerSelected = { isCorrect ->
            // handle score update
        },
        onNext = {
            viewModel.goToNextQuestion()
            if (viewModel.isQuizComplete()) {
                onNavigateToScores.invoke()
            }
        }
    )


}

@Composable
fun QuestionView(
    question: TrivialDatabase.QuestionEntity,
    onAnswerSelected: (Int) -> Unit,
    onNext: () -> Unit
) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = question.text, fontSize = 20.sp, modifier = Modifier.padding(bottom = 16.dp))

        question.answers.forEachIndexed { index, answer ->
            Button(
                onClick = { onAnswerSelected(index); onNext() },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            ) {
                Text(answer)
            }
        }
    }
}