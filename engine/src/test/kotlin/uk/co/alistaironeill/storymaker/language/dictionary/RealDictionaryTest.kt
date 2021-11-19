package uk.co.alistaironeill.storymaker.language.dictionary

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import com.natpryce.hamkrest.isEmpty
import org.junit.jupiter.api.Test
import uk.co.alistaironeill.storymaker.language.Keyword
import uk.co.alistaironeill.storymaker.language.LocationName
import uk.co.alistaironeill.storymaker.language.RecognizedWord

class RealDictionaryTest {
    private val house = LocationName("house")
    private val stream = LocationName("stream")

    private val dictionary = RealDictionary(
        locations = setOf(house, stream)
    )

    private infix fun String.parsesAs(word: RecognizedWord) =
        assertThat(
            dictionary.lookUp(this).single(),
            equalTo(word)
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
    fun `returns empty set if unrecognised`() =
        assertThat(
            dictionary.lookUp("kajsdkajf"),
            isEmpty
        )
}