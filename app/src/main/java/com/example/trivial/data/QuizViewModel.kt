package com.example.trivial.data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trivial.data.TrivialDatabase.QuestionDao
import com.example.trivial.data.TrivialDatabase.QuestionEntity
import com.example.trivial.data.TrivialDatabase.ScoreEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class QuizViewModel @Inject constructor(
    private val quizRepo: QuizRepository,
) : ViewModel() {

    var questions by mutableStateOf(listOf<QuestionEntity>())
        private set

    var currentIndex by mutableIntStateOf(0)
        private set

    var totalQuestions by mutableIntStateOf(10)
        private set

    var score by mutableIntStateOf(0)

    var correctStreak = 0

    val currentQuestion: QuestionEntity
        get() = questions.getOrNull(currentIndex) ?: QuestionEntity(0, "", listOf(), 0)

    init {
        viewModelScope.launch {
            val random = Random.nextInt(0, (quizRepo.getQuestionCount() - 10).coerceAtLeast(0))
            questions = quizRepo.getRandomQuestions(random)
            totalQuestions = questions.size
        }
    }

    fun submitAnswer(selectedIndex: Int) {
        val isCorrect = selectedIndex == currentQuestion.correctAnswerIndex

        if (isCorrect) {
            correctStreak++
            score += correctStreak
        } else {
            correctStreak = 0
        }

        goToNextQuestion()
    }

    fun goToNextQuestion() {
        currentIndex++
    }

    fun saveScore() {
        viewModelScope.launch {
            quizRepo.saveScore(score)
        }
    }



    private val _topScores = mutableStateOf(listOf<ScoreEntity>())
    val topScores = _topScores

    fun updateScoresList() {
        viewModelScope.launch {
            _topScores.value = quizRepo.getTopScores()
        }
    }
}

