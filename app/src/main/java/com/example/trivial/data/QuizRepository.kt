package com.example.trivial.data

import android.content.Context
import com.example.trivial.data.TrivialDatabase.QuestionDao
import com.example.trivial.data.TrivialDatabase.QuestionEntity
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class QuizRepository @Inject constructor(
    private val dao: QuestionDao,
    @ApplicationContext private val context: Context
) {

    suspend fun preloadQuestionsIfNeeded() {
        if (dao.getCount() == 0) {
            val questions = loadQuestionsFromAssets(context)
            dao.insertAll(questions)
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

}