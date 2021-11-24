package uk.co.alistaironeill.storymaker.game.scene

import com.ubertob.kondor.outcome.Outcome
import uk.co.alistaironeill.storymaker.error.PerformError.CatastrophicError
import uk.co.alistaironeill.storymaker.error.PerformError.Terminating
import uk.co.alistaironeill.storymaker.language.LocationName
import uk.co.alistaironeill.storymaker.language.dictionary.Dictionary

interface SceneDefinition {
    val destinations: Set<LocationName>
    val dictionary: Outcome<CatastrophicError, Dictionary>
    fun onEntry(): Outcome<Terminating, String>
}