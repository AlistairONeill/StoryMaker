package uk.co.alistaironeill.storymaker.controller

import com.ubertob.kondor.outcome.*
import uk.co.alistaironeill.storymaker.action.Action
import uk.co.alistaironeill.storymaker.action.Start
import uk.co.alistaironeill.storymaker.consequence.*
import uk.co.alistaironeill.storymaker.error.ParseError
import uk.co.alistaironeill.storymaker.error.ParseError.*
import uk.co.alistaironeill.storymaker.language.dictionary.CompositeDictionary
import uk.co.alistaironeill.storymaker.language.dictionary.DefaultDictionary
import uk.co.alistaironeill.storymaker.parser.RealParser
import uk.co.alistaironeill.storymaker.state.GameState

class RealGameController(
    private val gameState: GameState
) : GameController {
    override fun start() = perform(Start)

    override fun perform(input: String): List<String> =
        RealParser(dictionary)
            .parse(input)
            .transform(::perform)
            .recover(::resolveParseError)

    private fun perform(action: Action): List<String> =
        gameState.perform(action)
            .run(::resolveConsequence)

    private val dictionary
        get() = CompositeDictionary(
            DefaultDictionary,
            gameState.dictionary
        )

    private fun resolveConsequence(initial: Consequence): List<String> {
        val output = ArrayList<String>()
        val stack = arrayListOf(initial)

        while (stack.isNotEmpty()) {
            @Suppress("IMPLICIT_CAST_TO_ANY")
            when (val consequence = stack.removeAt(0)) {
                is DescriptionConsequence -> output.add(consequence.description)
                is GameOverConsequence -> throw GameOverException(output)
                is GameStateConsequence -> stack.add(consequence.mutator(gameState))
                is InvalidAction -> output.add(consequence.text)
                is CompositeConsequence -> stack.addAll(0, consequence.consequences)
                NoOpConsequence -> {}
            }.toString()
        }

        return output
    }

    private fun resolveParseError(error: ParseError): List<String> =
        when (error) {
            is Ambiguity -> listOf(error.msg)
            Unparseable -> listOf(error.msg)
        }
}