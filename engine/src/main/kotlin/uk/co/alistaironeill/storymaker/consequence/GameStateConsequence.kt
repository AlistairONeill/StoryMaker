package uk.co.alistaironeill.storymaker.consequence

import uk.co.alistaironeill.storymaker.state.GameState

data class GameStateConsequence(
    val mutator: GameState.() -> Consequence
) : Consequence