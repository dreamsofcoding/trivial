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

object Settings : TrivialDestinations {
    override val route = "settings"
}

object About : TrivialDestinations {
    override val route = "about"
}