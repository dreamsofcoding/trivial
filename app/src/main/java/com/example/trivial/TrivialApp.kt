package com.example.trivial

import android.app.Application
import com.example.trivial.data.QuizRepository
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import timber.log.Timber.Tree
import javax.inject.Inject

@HiltAndroidApp
class TrivialApp : Application() {

    @Inject lateinit var questionRepo: QuizRepository

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())

        CoroutineScope(Dispatchers.IO).launch {
            questionRepo.preloadQuestionsIfNeeded()
        }

    }

}