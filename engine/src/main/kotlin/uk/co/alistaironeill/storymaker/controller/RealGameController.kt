package uk.co.alistaironeill.storymaker.controller

import com.ubertob.kondor.outcome.Outcome
import com.ubertob.kondor.outcome.asFailure
import uk.co.alistaironeill.storymaker.action.Action
import uk.co.alistaironeill.storymaker.controller.GameController.PerformError
import uk.co.alistaironeill.storymaker.controller.GameController.PerformError.Ambiguity
import uk.co.alistaironeill.storymaker.language.dictionary.CompositeDictionary
import uk.co.alistaironeill.storymaker.language.dictionary.DefaultDictionary
import uk.co.alistaironeill.storymaker.parser.RealParser
import uk.co.alistaironeill.storymaker.state.GameState

class RealGameController(
    private val gameState: GameState
): GameController {
    override fun perform(input: String): Outcome<PerformError, String> =
        RealParser(dictionary)
            .parse(input)
            .let { actions ->
                when (actions.size) {
                    0 -> PerformError.Unparseable(input).asFailure()
                    1 -> gameState.perform(actions.single())
                    else -> Ambiguity(actions).asFailure()
                }
            }

    private val dictionary get() = CompositeDictionary(
        DefaultDictionary,
        gameState.dictionary
    )
}