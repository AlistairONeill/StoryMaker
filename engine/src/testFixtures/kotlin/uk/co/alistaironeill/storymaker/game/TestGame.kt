package uk.co.alistaironeill.storymaker.game

import uk.co.alistaironeill.storymaker.game.scene.BasicSceneDefinition
import uk.co.alistaironeill.storymaker.game.scene.WinningSceneDefinition
import uk.co.alistaironeill.storymaker.language.LocationName
import uk.co.alistaironeill.storymaker.language.dictionary.StubDictionary

object TestGame {
    object LocationNames {
        val house = LocationName("house")
        val garden = LocationName("garden")
        val pond = LocationName("pond")
        val void = LocationName("void")
    }

    object SceneDescriptions {
        const val house = "You find yourself in a small house. Where is the Pond? Through the open back door, you see a garden"
        const val garden = "You are in a brightly lit garden. There is a house with an open door and a pond"
    }

    const val epilogue = "You made it to the pond! Congratulations!"

    object SceneDefinitions {
        val house = BasicSceneDefinition(
            SceneDescriptions.house,
            setOf(LocationNames.garden)
        )

        val garden = BasicSceneDefinition(
            SceneDescriptions.garden,
            setOf(LocationNames.house, LocationNames.pond)
        )

        val pond = WinningSceneDefinition(
            epilogue
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
            LocationNames.pond to SceneDefinitions.pond,
            LocationNames.void to SceneDefinitions.void
        ),
        StubDictionary()
    )
}