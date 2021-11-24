package uk.co.alistaironeill.storymaker.state

import com.ubertob.kondor.outcome.Outcome
import com.ubertob.kondor.outcome.asFailure
import com.ubertob.kondor.outcome.asSuccess
import com.ubertob.kondor.outcome.bind
import uk.co.alistaironeill.storymaker.action.Action
import uk.co.alistaironeill.storymaker.action.Move
import uk.co.alistaironeill.storymaker.error.PerformError
import uk.co.alistaironeill.storymaker.error.PerformError.InvalidAction
import uk.co.alistaironeill.storymaker.game.GameDefinition
import uk.co.alistaironeill.storymaker.game.scene.SceneDefinition
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

    override fun perform(action: Action): Outcome<PerformError, String> =
        when (action) {
            is Move -> move(action.destination)
        }

    private fun move(destination: LocationName): Outcome<PerformError, String> =
        when {
            destination == locationName -> "You are already there, silly".asSuccess()
            location.destinations.contains(destination) -> {
                locationName = destination
                location.onEntry()
            }
            else -> InvalidAction(Move(destination)).asFailure()
        }

}