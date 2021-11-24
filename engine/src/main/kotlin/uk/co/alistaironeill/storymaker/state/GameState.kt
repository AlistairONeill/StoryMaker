package uk.co.alistaironeill.storymaker.state

import com.ubertob.kondor.outcome.Outcome
import uk.co.alistaironeill.storymaker.action.Action
import uk.co.alistaironeill.storymaker.controller.GameController.PerformError
import uk.co.alistaironeill.storymaker.language.dictionary.Dictionary


interface GameState {
    val dictionary: Dictionary

    fun perform(action: Action): Outcome<PerformError, String>
}