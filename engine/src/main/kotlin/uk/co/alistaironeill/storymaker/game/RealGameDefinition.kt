package uk.co.alistaironeill.storymaker.game

import uk.co.alistaironeill.storymaker.game.scene.SceneDefinition
import uk.co.alistaironeill.storymaker.language.LocationName
import uk.co.alistaironeill.storymaker.language.dictionary.Dictionary

class RealGameDefinition(
    override val start: LocationName,
    private val locations: Map<LocationName, SceneDefinition>,
    override val dictionary: Dictionary
): GameDefinition {
    override fun get(locationName: LocationName) =
        locations[locationName] ?: throw RuntimeException("Missing Scene Definition")
}