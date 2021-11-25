package uk.co.storymaker

import uk.co.alistaironeill.storymaker.controller.GameOverException

fun main() {
    val gameController = DemoGame
    println("Welcome to the game")
    gameController.start().forEach(::println)
    try {
        while (true) {
            val input = readLine() ?: break
            if (input == "EXIT") break
            gameController.perform(input)
                .forEach(::println)
        }
    }
    catch (e: GameOverException) {
        e.epilogue.forEach(::println)
    }

    println("Goodbye")
}