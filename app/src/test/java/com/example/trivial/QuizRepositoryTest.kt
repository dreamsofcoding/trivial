package com.example.trivial

import com.example.trivial.data.QuizRepository
import com.example.trivial.data.TrivialDatabase.ScoreEntity
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class QuizRepositoryTest {

    private lateinit var repository: QuizRepository
    private lateinit var fakeScoreDao: FakeScoreDao

    @Before
    fun setup() {
        fakeScoreDao = FakeScoreDao()
        repository = QuizRepository(FakeQuestionDao(), fakeScoreDao)
    }

    @Test
    fun `getTopScores returns ordered scores`() = runTest {
        val scores = listOf(
            ScoreEntity(score = 10, timestamp = 1),
            ScoreEntity(score = 30, timestamp = 2),
            ScoreEntity(score = 20, timestamp = 3)
        )
        fakeScoreDao.setScores(scores)

        val result = repository.getTopScores()

        assertEquals(listOf(30, 20, 10), result.map { it.score })
    }
}
