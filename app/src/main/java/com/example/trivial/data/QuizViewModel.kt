package com.example.trivial.data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject

@HiltViewModel
class QuizViewModel @Inject constructor() : ViewModel() {
    private val questions = listOf("")
    var currentQuestionIndex by mutableIntStateOf(0)
        private set

//    val currentQuestion: Question
//        get() = questions[currentQuestionIndex]

    fun goToNextQuestion() {
        currentQuestionIndex++
    }
}
