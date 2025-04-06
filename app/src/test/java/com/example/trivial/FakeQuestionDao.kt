package com.example.trivial

import com.example.trivial.data.TrivialDatabase.QuestionDao
import com.example.trivial.data.TrivialDatabase.QuestionEntity
import com.example.trivial.data.TrivialDatabase.ScoreDao
import com.example.trivial.data.TrivialDatabase.ScoreEntity

class FakeQuestionDao : QuestionDao {
    private var questions = emptyList<QuestionEntity>()

    fun setQuestions(data: List<QuestionEntity>) { questions = data }
    override suspend fun getCount(): Int {
        return questions.size
    }

    override suspend fun getRandomQuestions(offset: Int): List<QuestionEntity> {
        return questions
    }

    override suspend fun insertAll(newQuestions: List<QuestionEntity>) {
        newQuestions.forEach {
            questions += it
        }
    }
}

class FakeScoreDao : ScoreDao {
    private val scores = mutableListOf<ScoreEntity>()

    override suspend fun insertScore(score: ScoreEntity) {
        scores.add(score)
    }

    override suspend fun getTopScores(): List<ScoreEntity> {
        return scores.sortedByDescending { it.score }.take(10)
    }

    fun setScores(data: List<ScoreEntity>) {
        scores.clear()
        scores.addAll(data)
    }
}
