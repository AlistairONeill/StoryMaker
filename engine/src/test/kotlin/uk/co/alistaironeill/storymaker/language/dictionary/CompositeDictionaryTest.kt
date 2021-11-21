package uk.co.alistaironeill.storymaker.language.dictionary

import org.junit.jupiter.api.Test
import uk.co.alistaironeill.storymaker.language.LocationName

class CompositeDictionaryTest : AbstractDictionaryTest() {
    override lateinit var dictionary: CompositeDictionary

    private val house = LocationName("house")
    private val river = LocationName("river")

    @Test
    fun `can get words from any subsidiary dictionary`() {
        dictionary = CompositeDictionary(
            StubDictionary("house" to setOf(house)),
            StubDictionary("river" to setOf(river))
        )

        "house" parsesAs house
        "river" parsesAs river
    }

    @Test
    fun `combines definitions when multiple exist`() {
        dictionary = CompositeDictionary(
            StubDictionary("foo" to setOf(house)),
            StubDictionary("foo" to setOf(river))
        )

        "foo" parsesAs setOf(house, river)
    }
}

