package uk.co.alistaironeill.storymaker

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import uk.co.alistaironeill.storymaker.controller.GameOverException
import uk.co.alistaironeill.storymaker.controller.RealGameController
import uk.co.alistaironeill.storymaker.game.TestGame
import uk.co.alistaironeill.storymaker.game.TestGame.epilogue
import uk.co.alistaironeill.storymaker.state.RealGameState

class RealStoryTest {
    private val controller = RealGameController(
        RealGameState(
            TestGame.definition
        )
    )

    @Test
    fun `can complete the game`() {
        controller.start()
        controller.perform("go garden")
        controller.perform("go house")
        controller.perform("go garden")
        assertThat(
            assertThrows<GameOverException> {
                controller.perform("GO POnD")
            }.epilogue,
            equalTo(listOf(epilogue))
        )
    }
}