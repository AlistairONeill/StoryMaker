package uk.co.alistaironeill.storymaker.game.scene

import uk.co.alistaironeill.storymaker.consequence.Consequence
import uk.co.alistaironeill.storymaker.consequence.DescriptionConsequence
import uk.co.alistaironeill.storymaker.consequence.GameStateConsequence
import uk.co.alistaironeill.storymaker.consequence.InvalidAction
import uk.co.alistaironeill.storymaker.language.LocationName
import uk.co.alistaironeill.storymaker.language.dictionary.RealDictionary

data class BasicSceneDefinition(
    private val description: String,
    override val destinations: Set<LocationName>
): SceneDefinition {
    override val dictionary = RealDictionary(destinations)

    override fun onEntry() = DescriptionConsequence(description)

    override fun move(destination: LocationName): Consequence =
        when {
            destinations.contains(destination) -> GameStateConsequence { move(destination) }
            else -> InvalidAction("I cannot move to ${destination.value}")
        }
}