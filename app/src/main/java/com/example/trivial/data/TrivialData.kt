package com.example.trivial.data

import com.example.trivial.data.TrivialDatabase.QuestionEntity
import kotlinx.serialization.Serializable

@Serializable
data class QuestionJson(
    val text: String,
    val answers: List<String>,
    val correctAnswerIndex: Int
)

fun QuestionJson.toEntity(): QuestionEntity {
    return QuestionEntity(
        text = this.text,
        answers = this.answers,
        correctAnswerIndex = this.correctAnswerIndex
    )
}