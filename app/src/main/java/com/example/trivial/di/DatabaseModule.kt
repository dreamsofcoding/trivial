package com.example.trivial.di

import android.content.Context
import androidx.room.Room
import com.example.trivial.data.TrivialDatabase.QuestionDao
import com.example.trivial.data.TrivialDatabase.QuizDatabase
import com.example.trivial.data.TrivialDatabase.ScoreDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): QuizDatabase =
        Room.databaseBuilder(context, QuizDatabase::class.java, "quiz-db")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideQuestionDao(db: QuizDatabase): QuestionDao = db.questionDao()

    @Provides
    fun provideScoreDao(db: QuizDatabase): ScoreDao = db.scoreDao()
}
