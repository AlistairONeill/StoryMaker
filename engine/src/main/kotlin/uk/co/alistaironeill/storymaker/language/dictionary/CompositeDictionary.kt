package uk.co.alistaironeill.storymaker.language.dictionary

import uk.co.alistaironeill.storymaker.language.RecognizedWord

class CompositeDictionary(
    private val dictionaries: Collection<Dictionary>
): Dictionary {
    constructor(vararg dictionaries: Dictionary) : this(dictionaries.toList())

    override fun lookUp(input: String): Set<RecognizedWord> =
        dictionaries.flatMap { it.lookUp(input) }.toSet()
}