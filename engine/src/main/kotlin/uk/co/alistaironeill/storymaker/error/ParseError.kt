package uk.co.alistaironeill.storymaker.error

import com.ubertob.kondor.outcome.OutcomeError
import uk.co.alistaironeill.storymaker.action.Action

sealed interface ParseError: OutcomeError {
    object Unparseable: ParseError {
        override val msg = "Huh?"
    }

    data class Ambiguity(val actions: Set<Action>): ParseError {
        override val msg = "Input could mean multiple things: [${actions.joinToString(", ") { it.desc() }}]"
    }
}