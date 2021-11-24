package uk.co.alistaironeill.storymaker.state

import uk.co.alistaironeill.storymaker.action.Action
import uk.co.alistaironeill.storymaker.consequence.Consequence
import uk.co.alistaironeill.storymaker.consequence.NoOpConsequence
import uk.co.alistaironeill.storymaker.language.LocationName
import uk.co.alistaironeill.storymaker.language.dictionary.StubDictionary

class StubGameState: GameState {
    override var dictionary = StubDictionary()

    val performedActions = mutableListOf<Action>()
    var returnValue : Consequence = NoOpConsequence

    override fun perform(action: Action): Consequence = returnValue.also { performedActions.add(action) }

    override fun move(destination: LocationName) = NoOpConsequence
}