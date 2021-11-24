package uk.co.alistaironeill.storymaker.parser

import com.ubertob.kondor.outcome.Outcome
import com.ubertob.kondor.outcome.asFailure
import com.ubertob.kondor.outcome.asSuccess
import uk.co.alistaironeill.storymaker.action.Action
import uk.co.alistaironeill.storymaker.action.Move
import uk.co.alistaironeill.storymaker.error.ParseError
import uk.co.alistaironeill.storymaker.error.ParseError.Ambiguity
import uk.co.alistaironeill.storymaker.error.ParseError.Unparseable
import uk.co.alistaironeill.storymaker.language.dictionary.Dictionary
import uk.co.alistaironeill.storymaker.language.Keyword.GO
import uk.co.alistaironeill.storymaker.language.LocationName
import uk.co.alistaironeill.storymaker.language.RecognizedWord

class RealParser(
    private val dictionary: Dictionary
) : Parser {
    override fun parse(input: String): Outcome<ParseError, Action> =
        input.split(" ")
            .filter(String::isNotEmpty)
            .map(dictionary::lookUp)
            .toPermutations()
            .mapNotNull(::toAction)
            .toSet()
            .toSingle()


    private fun toAction(words: List<RecognizedWord>): Action? =
        when (words.size) {
            0 -> toAction()
            1 -> toAction(words[0])
            2 -> toAction(words[0], words[1])
            else -> null
        }

    private fun toAction(): Action? = null

    @Suppress("UNUSED_PARAMETER")
    private fun toAction(w1: RecognizedWord): Action? = null

    private fun toAction(w1: RecognizedWord, w2: RecognizedWord): Action? =
        when (w1) {
            GO -> when (w2) {
                GO -> null
                is LocationName -> Move(w2)
            }
            is LocationName -> null
        }

    private fun List<Set<RecognizedWord>>.toPermutations(): List<List<RecognizedWord>> =
        if (size == 0) {
            listOf(emptyList())
        } else {
            first().flatMap { word -> drop(1).toPermutations().map { list -> listOf(word) + list } }
        }

    private fun Set<Action>.toSingle() =
        when (size) {
            0 -> Unparseable.asFailure()
            1 -> single().asSuccess()
            else -> Ambiguity(this).asFailure()
        }

}