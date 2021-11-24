package uk.co.alistaironeill.storymaker.state

import uk.co.alistaironeill.storymaker.action.Action
import uk.co.alistaironeill.storymaker.consequence.Consequence
import uk.co.alistaironeill.storymaker.language.LocationName
import uk.co.alistaironeill.storymaker.language.dictionary.Dictionary

interface GameState {
    val dictionary: Dictionary

    fun perform(action: Action): Consequence
    fun move(destination: LocationName): Consequence
}