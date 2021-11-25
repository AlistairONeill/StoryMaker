package uk.co.alistaironeill.storymaker.game.scene

import uk.co.alistaironeill.storymaker.consequence.Consequence
import uk.co.alistaironeill.storymaker.language.LocationName
import uk.co.alistaironeill.storymaker.language.dictionary.Dictionary

interface SceneDefinition {
    val dictionary: Dictionary
    fun onEntry(): Consequence
    fun move(destination: LocationName): Consequence
}