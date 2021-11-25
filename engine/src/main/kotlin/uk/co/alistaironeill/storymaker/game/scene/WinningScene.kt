package uk.co.alistaironeill.storymaker.game.scene

import uk.co.alistaironeill.storymaker.consequence.CompositeConsequence
import uk.co.alistaironeill.storymaker.consequence.DescriptionConsequence
import uk.co.alistaironeill.storymaker.consequence.GameOverConsequence
import uk.co.alistaironeill.storymaker.language.LocationName

data class WinningSceneDefinition(
    val epilogue: String
): SceneDefinition {
    override val dictionary get() = throw RuntimeException("The game is over!")
    override fun onEntry() = CompositeConsequence(
        DescriptionConsequence(epilogue),
        GameOverConsequence
    )
    override fun move(destination: LocationName) = throw RuntimeException("The game is over!")
}