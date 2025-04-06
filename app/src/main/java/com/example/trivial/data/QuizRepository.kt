package com.example.trivial.data

import android.content.Context
import com.example.trivial.data.TrivialDatabase.QuestionDao
import com.example.trivial.data.TrivialDatabase.QuestionEntity
import com.example.trivial.data.TrivialDatabase.ScoreDao
import com.example.trivial.data.TrivialDatabase.ScoreEntity
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class QuizRepository @Inject constructor(
    private val questionDao: QuestionDao,
    private val scoreDao: ScoreDao,
    @ApplicationContext private val context: Context
) {

    suspend fun preloadQuestionsIfNeeded() {
        if (getQuestionCount() == 0) {
            val questions = loadQuestionsFromAssets(context)
            questionDao.insertAll(questions)
        }
    }

    private suspend fun loadQuestionsFromAssets(context: Context): List<QuestionEntity> =
        withContext(Dispatchers.IO) {
            val json = context.assets.open("questions.json")
                .bufferedReader().use { it.readText() }

            val jsonFormat = Json { ignoreUnknownKeys = true }

            val questions = jsonFormat.decodeFromString<List<QuestionJson>>(json)
            questions.map { it.toEntity() }
        }

    suspend fun saveScore(score: Int) {
        scoreDao.insertScore(ScoreEntity(score = score))
    }

    suspend fun getTopScores(): List<ScoreEntity> {
        return scoreDao.getTopScores()
    }

    suspend fun getQuestionCount() : Int {
        return questionDao.getCount()
    }

    suspend fun getRandomQuestions(random: Int): List<QuestionEntity> {
        return questionDao.getRandomQuestions(
            random
        )
    }

}