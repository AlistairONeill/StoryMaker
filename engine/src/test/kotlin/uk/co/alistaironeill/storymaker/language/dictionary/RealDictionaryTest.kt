package uk.co.alistaironeill.storymaker.language.dictionary

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import com.natpryce.hamkrest.isEmpty
import org.junit.jupiter.api.Test
import uk.co.alistaironeill.storymaker.language.Keyword
import uk.co.alistaironeill.storymaker.language.LocationName
import uk.co.alistaironeill.storymaker.language.RecognizedWord

class RealDictionaryTest: AbstractDictionaryTest() {
    private val house = LocationName("house")
    private val stream = LocationName("stream")

    override val dictionary = RealDictionary(
        locations = setOf(house, stream)
    )

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