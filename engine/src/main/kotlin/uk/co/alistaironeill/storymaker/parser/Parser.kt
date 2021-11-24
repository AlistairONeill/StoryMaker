package uk.co.alistaironeill.storymaker.parser

import com.ubertob.kondor.outcome.Outcome
import uk.co.alistaironeill.storymaker.action.Action
import uk.co.alistaironeill.storymaker.error.ParseError

interface Parser {
    fun parse(input: String): Outcome<ParseError, Action>
}