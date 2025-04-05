package com.example.trivial.ui.questions

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.trivial.data.QuizViewModel

@Composable
fun QuestionsScreen(
    viewModel: QuizViewModel = hiltViewModel(),
    onNextQuestionSelected: () -> Unit,
    onNavigateToScores: () -> Unit
) {
//    val question = viewModel.currentQuestion

//    QuestionView(
//        question = question,
//        onAnswerSelected = { isCorrect ->
//            // handle score update
//        },
//        onNext = {
//            viewModel.goToNextQuestion()
////            if (viewModel.isQuizComplete()) {
////                onNavigateToScores.invoke()
////            }
//        }
//    )


}

@Composable
fun QuestionView(
    question: String,
    onAnswerSelected: (Boolean) -> Unit,
    onNext: () -> Unit
) {
    TODO("Not yet implemented")
}