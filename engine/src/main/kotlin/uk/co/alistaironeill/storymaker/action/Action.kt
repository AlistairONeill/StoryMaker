package uk.co.alistaironeill.storymaker.action

sealed interface Action {
    fun desc(): String
}