package com.example.trivial

interface TrivialDestinations {
    val route: String
}

object Welcome : TrivialDestinations {
    override val route = "welcome"
}

object Questions : TrivialDestinations {
    override val route = "questions"
}

object Scores : TrivialDestinations {
    override val route = "scores"
}