package com.example.trivial

import com.example.trivial.data.QuizViewModel
import com.example.trivial.data.TrivialDatabase.QuestionEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class QuizViewModelTest {

    private lateinit var viewModel: QuizViewModel
    private lateinit var fakeDao: FakeQuestionDao
    private lateinit var fakeScoreDao: FakeScoreDao

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setup() {
        fakeDao = FakeQuestionDao()
        fakeScoreDao = FakeScoreDao()
        viewModel = QuizViewModel(fakeDao, fakeScoreDao)
    }

    @Test
    fun `submit correct answer increases score`() = runTest {
        val question = QuestionEntity(1, "Capital of France?", listOf("Paris", "Berlin"), 0)
        fakeDao.setQuestions(listOf(question))
        viewModel.submitAnswer(0)
        assertEquals(1, viewModel.score)
    }

    @Test
    fun `submit incorrect answer resets streak`() = runTest {
        val question = QuestionEntity(1, "Capital of France?", listOf("Paris", "Berlin"), 0)
        fakeDao.setQuestions(listOf(question))
        viewModel.submitAnswer(1)

        assertEquals(0, viewModel.correctStreak)
        assertEquals(0, viewModel.score)
    }
}
