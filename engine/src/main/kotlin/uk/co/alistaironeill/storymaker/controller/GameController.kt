package uk.co.alistaironeill.storymaker.controller


interface GameController {
    fun perform(input: String): List<String>
}