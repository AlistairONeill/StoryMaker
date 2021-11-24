package uk.co.alistaironeill.storymaker.game

import com.ubertob.kondor.outcome.asFailure
import com.ubertob.kondor.outcome.asSuccess
import uk.co.alistaironeill.storymaker.error.PerformError.CatastrophicError
import uk.co.alistaironeill.storymaker.game.scene.SceneDefinition
import uk.co.alistaironeill.storymaker.language.LocationName
import uk.co.alistaironeill.storymaker.language.dictionary.Dictionary

class RealGameDefinition(
    override val start: LocationName,
    private val locations: Map<LocationName, SceneDefinition>,
    dictionary: Dictionary
): GameDefinition {
    override fun get(locationName: LocationName) =
        locations[locationName]?.asSuccess() ?: CatastrophicError("Failed to find SceneDefinition").asFailure()

    override val dictionary = dictionary.asSuccess()
}