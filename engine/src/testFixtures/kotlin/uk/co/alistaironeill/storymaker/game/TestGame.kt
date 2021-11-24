package uk.co.alistaironeill.storymaker.game

import uk.co.alistaironeill.storymaker.game.scene.BasicSceneDefinition
import uk.co.alistaironeill.storymaker.language.LocationName
import uk.co.alistaironeill.storymaker.language.dictionary.StubDictionary

object TestGame {
    object LocationNames {
        val house = LocationName("house")
        val garden = LocationName("garden")
        val void = LocationName("void")
    }

    object SceneDefinitions {
        val house = BasicSceneDefinition(
            "You find yourself in a small house. Through the open back door, you see a garden",
            setOf(LocationNames.garden)
        )

        val garden = BasicSceneDefinition(
            "You are in a brightly lit garden, There is a house with an open door",
            setOf(LocationNames.house)
        )

        val void = BasicSceneDefinition(
            "Uh... How did you get here?",
            emptySet()
        )
    }

    val definition = RealGameDefinition(
        LocationNames.house,
        mapOf(
            LocationNames.house to SceneDefinitions.house,
            LocationNames.garden to SceneDefinitions.garden,
            LocationNames.void to SceneDefinitions.void
        ),
        StubDictionary()
    )
}