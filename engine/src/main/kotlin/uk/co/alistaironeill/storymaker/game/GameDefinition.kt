package uk.co.alistaironeill.storymaker.game

import com.ubertob.kondor.outcome.Outcome
import uk.co.alistaironeill.storymaker.error.PerformError.CatastrophicError
import uk.co.alistaironeill.storymaker.game.scene.SceneDefinition
import uk.co.alistaironeill.storymaker.language.LocationName
import uk.co.alistaironeill.storymaker.language.dictionary.Dictionary

interface GameDefinition {
    operator fun get(locationName: LocationName): Outcome<CatastrophicError, SceneDefinition>
    val dictionary: Outcome<CatastrophicError, Dictionary>
    val start: LocationName
}