package uk.co.alistaironeill.storymaker.error

import com.ubertob.kondor.outcome.OutcomeError
import uk.co.alistaironeill.storymaker.action.Action

sealed interface PerformError: OutcomeError {
    data class Unparseable(val input: String): PerformError {
        override val msg = "Failed to parse an action from [$input]"
    }

    data class Ambiguity(val actions: Set<Action>): PerformError {
        override val msg = "Input could mean multiple things: [${actions.joinToString(", ") { it.desc() }}]"
    }

    data class CatastrophicError(override val msg: String): OutcomeError, PerformError

    data class InvalidAction(val action: Action): PerformError {
        override val msg = "You can't \"${action.desc()}\""
    }
}