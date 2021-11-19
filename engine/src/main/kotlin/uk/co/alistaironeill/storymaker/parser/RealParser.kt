package uk.co.alistaironeill.storymaker.parser

import com.ubertob.kondor.outcome.*
import uk.co.alistaironeill.storymaker.action.Action
import uk.co.alistaironeill.storymaker.action.Move
import uk.co.alistaironeill.storymaker.language.Dictionary
import uk.co.alistaironeill.storymaker.language.Keyword.GO
import uk.co.alistaironeill.storymaker.language.LocationName
import uk.co.alistaironeill.storymaker.language.RecognizedWord
import uk.co.alistaironeill.storymaker.parser.Parser.ParseError
import uk.co.alistaironeill.storymaker.parser.Parser.ParseError.NoInput
import uk.co.alistaironeill.storymaker.parser.Parser.ParseError.UnrecognizedInput

class RealParser(
    private val dictionary: Dictionary
): Parser {
    override fun parse(input: String): Outcome<ParseError, Action> =
        input.split(" ")
            .filter(String::isNotEmpty)
            .map(dictionary::lookUp)
            .extractList()
            .transformFailure { UnrecognizedInput }
            .bind(::toAction)

    private fun toAction(words: List<RecognizedWord>): Outcome<ParseError, Action> =
        when (words.size) {
            0 -> toAction()
            1 -> toAction(words[0])
            2 -> toAction(words[0], words[1])
            else -> UnrecognizedInput.asFailure()
        }

    private fun toAction(): Outcome<ParseError, Action> = NoInput.asFailure()

    @Suppress("UNUSED_PARAMETER")
    private fun toAction(w1: RecognizedWord): Outcome<ParseError, Action> = UnrecognizedInput.asFailure()

    private fun toAction(w1: RecognizedWord, w2: RecognizedWord): Outcome<ParseError, Action> =
        when (w1) {
            GO -> when (w2) {
                GO -> UnrecognizedInput.asFailure()
                is LocationName -> Move(w2).asSuccess()
            }
            is LocationName -> UnrecognizedInput.asFailure()
        }
}