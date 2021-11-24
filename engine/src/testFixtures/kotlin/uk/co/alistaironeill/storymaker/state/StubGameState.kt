package uk.co.alistaironeill.storymaker.state

import com.ubertob.kondor.outcome.Outcome
import com.ubertob.kondor.outcome.asFailure
import com.ubertob.kondor.outcome.asSuccess
import uk.co.alistaironeill.storymaker.action.Action
import uk.co.alistaironeill.storymaker.error.PerformError
import uk.co.alistaironeill.storymaker.language.dictionary.Dictionary
import uk.co.alistaironeill.storymaker.language.dictionary.StubDictionary

class StubGameState: GameState {
    override var dictionary = StubDictionary()

    var error: PerformError? = null
    val performedActions = mutableListOf<Action>()
    var returnValue = "action performed"

    override fun perform(action: Action): Outcome<PerformError, String> =
        error?.asFailure()
            ?: returnValue.asSuccess().also {
                performedActions.add(action)
            }
}