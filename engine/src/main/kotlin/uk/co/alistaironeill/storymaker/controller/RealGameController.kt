package uk.co.alistaironeill.storymaker.controller

import com.ubertob.kondor.outcome.*
import uk.co.alistaironeill.storymaker.error.PerformError
import uk.co.alistaironeill.storymaker.error.PerformError.*
import uk.co.alistaironeill.storymaker.language.dictionary.CompositeDictionary
import uk.co.alistaironeill.storymaker.language.dictionary.DefaultDictionary
import uk.co.alistaironeill.storymaker.parser.RealParser
import uk.co.alistaironeill.storymaker.state.GameState

class RealGameController(
    private val gameState: GameState
): GameController {
    override fun perform(input: String): Outcome<Terminating, String> =
        dictionary.bind { dictionary ->
            RealParser(dictionary)
                .parse(input)
                .let { actions ->
                    when (actions.size) {
                        0 -> Unparseable(input).asFailure()
                        1 -> gameState.perform(actions.single())
                        else -> Ambiguity(actions).asFailure()
                    }
                }.bindFailure { error ->
                    when (error) {
                        is Ambiguity,
                        is InvalidAction,
                        is Unparseable -> error.msg.asSuccess()
                        is CatastrophicError,
                        is GameWin -> (error as Terminating).asFailure()
                    }
                }
        }

    private val dictionary get() = CompositeDictionary(
        DefaultDictionary,
        gameState.dictionary
    )
}