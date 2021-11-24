package uk.co.alistaironeill.storymaker.controller

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import com.natpryce.hamkrest.isEmpty
import com.ubertob.kondortools.expectFailure
import com.ubertob.kondortools.expectSuccess
import org.junit.jupiter.api.Test
import uk.co.alistaironeill.storymaker.action.Move
import uk.co.alistaironeill.storymaker.error.PerformError.Ambiguity
import uk.co.alistaironeill.storymaker.error.PerformError.Unparseable
import uk.co.alistaironeill.storymaker.language.Keyword.GO
import uk.co.alistaironeill.storymaker.language.LocationName
import uk.co.alistaironeill.storymaker.language.dictionary.StubDictionary
import uk.co.alistaironeill.storymaker.state.StubGameState

class RealGameControllerTest {
    private val gameState = StubGameState()
    private val controller = RealGameController(gameState)

    private val house = LocationName("house")
    private val church = LocationName("church")

    @Test
    fun `uses the gameState's dictionary to parse commands`() {
        assertThat(
            controller.perform("go house")
                .expectFailure(),
            equalTo(Unparseable("go house"))
        )

        assertThat(
            gameState.performedActions,
            isEmpty
        )

        gameState.setDictionary(
            StubDictionary(
                "go" to setOf(GO),
                "house" to setOf(house)
            )
        )

        assertThat(
            controller.perform("go house")
                .expectSuccess(),
            equalTo("action performed")
        )

        assertThat(
            gameState.performedActions
                .single(),
            equalTo(Move(house))
        )
    }

    @Test
    fun `returns error when there is ambiguity`() {
        gameState.setDictionary(
            StubDictionary(
                "go" to setOf(GO),
                "building" to setOf(house, church)
            )
        )

        assertThat(
            controller.perform("go building")
                .expectFailure(),
            equalTo(
                Ambiguity(
                    setOf(
                        Move(house),
                        Move(church)
                    )
                )
            )
        )
    }
}