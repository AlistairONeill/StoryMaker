package uk.co.storymaker

import uk.co.alistaironeill.storymaker.controller.RealGameController
import uk.co.alistaironeill.storymaker.game.RealGameDefinition
import uk.co.alistaironeill.storymaker.game.scene.BasicSceneDefinition
import uk.co.alistaironeill.storymaker.game.scene.WinningSceneDefinition
import uk.co.alistaironeill.storymaker.language.LocationName
import uk.co.alistaironeill.storymaker.language.dictionary.RealDictionary
import uk.co.alistaironeill.storymaker.state.RealGameState

val DemoGame = RealGameController(
    RealGameState(
        RealGameDefinition(
            LocationNames.house,
            mapOf(
                LocationNames.house to SceneDefinitions.house,
                LocationNames.garden to SceneDefinitions.garden,
                LocationNames.pond to SceneDefinitions.pond
            ),
            RealDictionary(emptySet())
        )
    )
)

private object SceneDefinitions {
    val house = BasicSceneDefinition(
        "You find yourself in a small house. Where is the Pond? Through the open back door, you see a garden",
        setOf(LocationNames.garden)
    )

    val garden = BasicSceneDefinition(
        "You are in a brightly lit garden. There is a house with an open door and a pond",
        setOf(LocationNames.house, LocationNames.pond)
    )

    val pond = WinningSceneDefinition(
        "You made it to the pond! Congratulations!"
    )
}

private object LocationNames {
    val house = LocationName("house")
    val garden = LocationName("garden")
    val pond = LocationName("pond")
}
