package uk.co.alistaironeill.storymaker.parser

import com.ubertob.kondor.outcome.Outcome
import com.ubertob.kondor.outcome.OutcomeError
import uk.co.alistaironeill.storymaker.action.Action

interface Parser {
    fun parse(input: String): Outcome<ParseError, Action>

    sealed interface ParseError: OutcomeError {

        object UnrecognizedInput: ParseError {
            override val msg = "Failed to parse"
        }

        object NoInput: ParseError {
            override val msg = "No input detected"
        }
    }
}