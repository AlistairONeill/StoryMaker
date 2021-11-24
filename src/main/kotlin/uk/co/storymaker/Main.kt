package uk.co.storymaker

import com.ubertob.kondor.outcome.*
import uk.co.alistaironeill.storymaker.error.PerformError.Terminating

fun main() {
    val gameController = DemoGame

    println("Welcome to the game")
    var result: Outcome<Terminating, String>
    do {
        val input = readLine() ?: break
        if (input == "EXIT") break
        result = gameController.perform(input)
            .withSuccess(::println)
            .withFailure { println(it.msg) }
    } while (result is Success)

    println("Goodbye")
}