package uk.co.alistaironeill.storymaker.parser

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import com.ubertob.kondortools.expectFailure
import com.ubertob.kondortools.expectSuccess
import org.junit.jupiter.api.Test
import uk.co.alistaironeill.storymaker.language.Keyword
import uk.co.alistaironeill.storymaker.language.LocationName
import uk.co.alistaironeill.storymaker.language.RecognizedWord
import uk.co.alistaironeill.storymaker.language.Dictionary.LookUpError
import uk.co.alistaironeill.storymaker.language.Dictionary.LookUpError.UnknownWord
import uk.co.alistaironeill.storymaker.language.RealDictionary

class RealDictionaryTest {
    private val house = LocationName("house")
    private val stream = LocationName("stream")

    private val dictionary = RealDictionary(
        locations = setOf(house, stream)
    )

    private infix fun String.parsesAs(word: RecognizedWord) =
        assertThat(
            dictionary.lookUp(this).expectSuccess(),
            equalTo(word)
        )

    private infix fun String.errorsAs(error: LookUpError) =
        assertThat(
            dictionary.lookUp(this).expectFailure(),
            equalTo(error)
        )

    @Test
    fun `can look up keywords`() =
        Keyword.values().forEach { word ->
            word.name.lowercase() parsesAs word
        }

    @Test
    fun `can look up locations`() {
        "house" parsesAs house
        "stream" parsesAs stream
    }

    @Test
    fun `returns error if unrecognised`() {
        "kasdaghfuka" errorsAs UnknownWord
    }
}