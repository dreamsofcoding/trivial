package com.example.trivial.data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trivial.data.TrivialDatabase.QuestionDao
import com.example.trivial.data.TrivialDatabase.QuestionEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuizViewModel @Inject constructor(
    private val questionDao: QuestionDao
) : ViewModel() {

    var questions by mutableStateOf(listOf<QuestionEntity>())
        private set

    var currentIndex by mutableStateOf(0)
        private set

    val currentQuestion: QuestionEntity
        get() = questions.getOrNull(currentIndex) ?: QuestionEntity(0, "", listOf(), 0)

    init {
        viewModelScope.launch {
            questions = questionDao.getRandomQuestions()
        }
    }

    fun goToNextQuestion() {
        currentIndex++
    }

    fun isQuizComplete(): Boolean = currentIndex >= questions.size - 1
}
