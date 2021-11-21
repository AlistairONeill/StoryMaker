package uk.co.alistaironeill.storymaker.language.dictionary

import org.junit.jupiter.api.Test
import uk.co.alistaironeill.storymaker.language.Keyword

class DefaultDictionaryTest: AbstractDictionaryTest() {
    override val dictionary = DefaultDictionary

    @Test
    fun `can look up keywords`() =
        Keyword.values().forEach { word ->
            word.name.uppercase() parsesAs word
            word.name.lowercase() parsesAs word
        }
}