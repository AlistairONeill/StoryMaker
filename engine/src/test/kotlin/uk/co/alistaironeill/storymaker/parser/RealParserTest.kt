package uk.co.alistaironeill.storymaker.parser

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import uk.co.alistaironeill.storymaker.action.Action
import uk.co.alistaironeill.storymaker.action.Move
import uk.co.alistaironeill.storymaker.language.LocationName
import uk.co.alistaironeill.storymaker.language.dictionary.CompositeDictionary
import uk.co.alistaironeill.storymaker.language.dictionary.DefaultDictionary
import uk.co.alistaironeill.storymaker.language.dictionary.RealDictionary

class RealParserTest {
    private val house = LocationName("house")

    private val dictionary = CompositeDictionary(
        DefaultDictionary,
        RealDictionary(
            locations = setOf(house)
        )
    )

    private val parser = RealParser(dictionary)

    private infix fun String.parsesAs(action: Action) =
        assertThat(
            parser.parse(this).single(),
            equalTo(action)
        )

    @Nested
    inner class Fundamentals {
        @Test
        fun `Capitalization doesn't matter`() {
            "Go hOuSe" parsesAs Move(house)
        }

        @Test
        fun `Extra spaces are irrelevant`() {
            "GO       HOUSE" parsesAs Move(house)
        }
    }

    @Nested
    inner class Move {
        @Test
        fun `can recognise move actions`() {
            "Go house" parsesAs Move(house)
        }
    }
}