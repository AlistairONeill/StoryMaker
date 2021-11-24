package uk.co.alistaironeill.storymaker.game.scene

import com.ubertob.kondor.outcome.asFailure
import uk.co.alistaironeill.storymaker.error.PerformError.CatastrophicError
import uk.co.alistaironeill.storymaker.error.PerformError.GameWin
import uk.co.alistaironeill.storymaker.language.LocationName

data class WinningSceneDefinition(
    val epilogue: String
): SceneDefinition {
    override val destinations = emptySet<LocationName>()
    override val dictionary = CatastrophicError("The game is over").asFailure()
    override fun onEntry() = GameWin(epilogue).asFailure()
}