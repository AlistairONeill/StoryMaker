package uk.co.alistaironeill.storymaker.language.dictionary

import uk.co.alistaironeill.storymaker.language.Keyword
import uk.co.alistaironeill.storymaker.language.RecognizedWord

object DefaultDictionary: Dictionary {
    private val keywords = Keyword.values().associateBy { it.name.lowercase() }

    override fun lookUp(input: String): Set<RecognizedWord> =
        keywords[input.lowercase()]?.let(::setOf) ?: emptySet()
}