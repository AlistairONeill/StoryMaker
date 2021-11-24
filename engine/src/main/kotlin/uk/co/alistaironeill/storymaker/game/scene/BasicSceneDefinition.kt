package uk.co.alistaironeill.storymaker.game.scene

import com.ubertob.kondor.outcome.Outcome
import com.ubertob.kondor.outcome.asSuccess
import uk.co.alistaironeill.storymaker.error.PerformError
import uk.co.alistaironeill.storymaker.language.LocationName
import uk.co.alistaironeill.storymaker.language.dictionary.RealDictionary

data class BasicSceneDefinition(
    private val description: String,
    override val destinations: Set<LocationName>
): SceneDefinition {
    override val dictionary = RealDictionary(destinations)

    override fun onEntry(): Outcome<PerformError.Terminating, String> = description.asSuccess()
}