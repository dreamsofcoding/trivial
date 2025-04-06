package com.example.trivial.data

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import kotlinx.serialization.Serializable

class TrivialDatabase {

    @Database(entities = [QuestionEntity::class, ScoreEntity::class], version = 1)
    @TypeConverters(Converters::class)
    abstract class QuizDatabase : RoomDatabase() {
        abstract fun questionDao(): QuestionDao
        abstract fun scoreDao(): ScoreDao
    }


    @Entity(tableName = "questions")
    data class QuestionEntity(
        @PrimaryKey(autoGenerate = true) val id: Int = 0,
        val text: String,
        val answers: List<String>,
        val correctAnswerIndex: Int
    )

    class Converters {
        @TypeConverter
        fun fromList(list: List<String>): String = list.joinToString("|")

        @TypeConverter
        fun toList(data: String): List<String> = data.split("|")
    }

    @Dao
    interface QuestionDao {
        @Query("SELECT COUNT(*) FROM questions")
        suspend fun getCount(): Int

        @Query("SELECT * FROM questions LIMIT 10 OFFSET :offset")
        suspend fun getRandomQuestions(offset : Int): List<QuestionEntity>

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun insertAll(questions: List<QuestionEntity>)
    }



    @Entity(tableName = "scores")
    data class ScoreEntity(
        @PrimaryKey(autoGenerate = true) val id: Int = 0,
        val score: Int,
        val timestamp: Long = System.currentTimeMillis()
    )

    @Dao
    interface ScoreDao {
        @Insert
        suspend fun insertScore(score: ScoreEntity)

        @Query("SELECT * FROM scores ORDER BY score DESC LIMIT 10")
        suspend fun getTopScores(): List<ScoreEntity>
    }

}