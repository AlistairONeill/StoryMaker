package uk.co.alistaironeill.storymaker

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import com.ubertob.kondortools.expectFailure
import org.junit.jupiter.api.Test
import uk.co.alistaironeill.storymaker.controller.RealGameController
import uk.co.alistaironeill.storymaker.error.PerformError.GameWin
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
        controller.perform("go garden")
        controller.perform("go house")
        controller.perform("go garden")
        assertThat(
            controller.perform("GO POnD").expectFailure(),
            equalTo(GameWin(epilogue))
        )

    }
}