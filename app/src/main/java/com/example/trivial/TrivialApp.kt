package com.example.trivial

import android.app.Application
import com.example.trivial.data.QuizRepository
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltAndroidApp
class TrivialApp : Application() {

    @Inject lateinit var questionRepo: QuizRepository

    override fun onCreate() {
        super.onCreate()

        CoroutineScope(Dispatchers.IO).launch {
            questionRepo.preloadQuestionsIfNeeded()
        }
    }

}