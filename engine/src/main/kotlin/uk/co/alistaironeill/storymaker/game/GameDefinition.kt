package uk.co.alistaironeill.storymaker.game

import uk.co.alistaironeill.storymaker.game.scene.SceneDefinition
import uk.co.alistaironeill.storymaker.language.LocationName
import uk.co.alistaironeill.storymaker.language.dictionary.Dictionary

interface GameDefinition {
    operator fun get(locationName: LocationName): SceneDefinition
    val dictionary: Dictionary
    val start: LocationName
}