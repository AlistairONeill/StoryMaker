package uk.co.alistaironeill.storymaker.controller

import com.ubertob.kondor.outcome.Outcome
import com.ubertob.kondor.outcome.OutcomeError
import uk.co.alistaironeill.storymaker.action.Action


interface GameController {
    fun perform(input: String): Outcome<PerformError, String>

    sealed interface PerformError: OutcomeError {
        data class Unparseable(val input: String): PerformError {
            override val msg = "Failed to parse an action from [$input]"
        }
        data class Ambiguity(val actions: Set<Action>): PerformError {
            override val msg = "Input could mean multiple things: [${actions.joinToString(", ") { it.desc() }}]"
        }
    }
}