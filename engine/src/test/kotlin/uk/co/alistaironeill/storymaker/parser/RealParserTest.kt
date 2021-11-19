package uk.co.alistaironeill.storymaker.parser

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import com.ubertob.kondortools.expectFailure
import com.ubertob.kondortools.expectSuccess
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import uk.co.alistaironeill.storymaker.action.Action
import uk.co.alistaironeill.storymaker.action.Move
import uk.co.alistaironeill.storymaker.language.LocationName
import uk.co.alistaironeill.storymaker.language.RealDictionary
import uk.co.alistaironeill.storymaker.parser.Parser.ParseError.NoInput
import uk.co.alistaironeill.storymaker.parser.Parser.ParseError.UnrecognizedInput

class RealParserTest {
    private val house = LocationName("house")

    private val dictionary = RealDictionary(
        locations = setOf(house)
    )

    private val parser = RealParser(dictionary)

    private infix fun String.parsesAs(action: Action) =
        assertThat(
            parser.parse(this).expectSuccess(),
            equalTo(action)
        )

    private infix fun String.errorsAs(error: Parser.ParseError) =
        assertThat(
            parser.parse(this).expectFailure(),
            equalTo(error)
        )

    @Nested
    inner class Errors {
        @Test
        fun `Nonsense input returns a useful error`() {
            "asidh" errorsAs UnrecognizedInput
        }


        @Test
        fun `Empty input returns a distinct error`() {
            "" errorsAs NoInput
            "    " errorsAs NoInput
        }
    }

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