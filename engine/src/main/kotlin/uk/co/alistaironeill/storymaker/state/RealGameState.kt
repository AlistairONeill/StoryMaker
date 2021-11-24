package uk.co.alistaironeill.storymaker.state

import uk.co.alistaironeill.storymaker.action.Action
import uk.co.alistaironeill.storymaker.action.Move
import uk.co.alistaironeill.storymaker.consequence.Consequence
import uk.co.alistaironeill.storymaker.game.GameDefinition
import uk.co.alistaironeill.storymaker.language.LocationName
import uk.co.alistaironeill.storymaker.language.dictionary.CompositeDictionary

class RealGameState(private val gameDefinition: GameDefinition) : GameState {
    private var locationName: LocationName = gameDefinition.start
    private val location get() = gameDefinition[locationName]

    override val dictionary
        get() =
            CompositeDictionary(
                gameDefinition.dictionary,
                location.dictionary
            )

    override fun perform(action: Action): Consequence =
        when (action) {
            is Move -> location.move(action.destination)
        }

    override fun move(destination: LocationName): Consequence {
        locationName = destination
        return location.onEntry()
    }
}