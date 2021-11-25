package uk.co.alistaironeill.storymaker.controller


interface GameController {
    fun start(): List<String>
    fun perform(input: String): List<String>
}