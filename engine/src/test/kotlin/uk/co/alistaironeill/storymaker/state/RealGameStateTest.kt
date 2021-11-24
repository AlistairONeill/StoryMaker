package uk.co.alistaironeill.storymaker.state

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import com.ubertob.kondortools.expectFailure
import com.ubertob.kondortools.expectSuccess
import org.junit.jupiter.api.Test
import uk.co.alistaironeill.storymaker.action.Move
import uk.co.alistaironeill.storymaker.error.PerformError.InvalidAction
import uk.co.alistaironeill.storymaker.game.TestGame
import uk.co.alistaironeill.storymaker.game.TestGame.LocationNames
import uk.co.alistaironeill.storymaker.game.TestGame.SceneDefinitions

class RealGameStateTest {
    private val gameState = RealGameState(
        TestGame.definition
    )

    @Test
    fun `can move from one location to another`() {
        assertThat(
            gameState.perform(Move(LocationNames.garden)).expectSuccess(),
            equalTo(SceneDefinitions.garden.description)
        )

        assertThat(
            gameState.perform(Move(LocationNames.house)).expectSuccess(),
            equalTo(SceneDefinitions.house.description)
        )
    }

    @Test
    fun `moving to an illegal location returns an error`() {
        assertThat(
            gameState.perform(Move(LocationNames.void)).expectFailure(),
            equalTo(InvalidAction(Move(LocationNames.void)))
        )
    }

    @Test
    fun `moving to the location you are returns an amusing response`() {
        assertThat(
            gameState.perform(Move(LocationNames.house)).expectSuccess(),
            equalTo("You are already there, silly")
        )
    }
}