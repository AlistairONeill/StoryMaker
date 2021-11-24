package uk.co.alistaironeill.storymaker.game.scene

import com.ubertob.kondor.outcome.asSuccess
import uk.co.alistaironeill.storymaker.language.LocationName
import uk.co.alistaironeill.storymaker.language.dictionary.RealDictionary

data class BasicSceneDefinition(
    override val description: String,
    override val destinations: Set<LocationName>
): SceneDefinition {
    override val dictionary = RealDictionary(destinations).asSuccess()
}