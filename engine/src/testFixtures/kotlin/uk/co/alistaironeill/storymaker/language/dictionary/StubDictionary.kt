package uk.co.alistaironeill.storymaker.language.dictionary

import uk.co.alistaironeill.storymaker.language.RecognizedWord

class StubDictionary(
    private val words: Map<String, Set<RecognizedWord>>
): Dictionary {
    constructor(vararg pairs: Pair<String, Set<RecognizedWord>>): this(pairs.toMap())

    override fun lookUp(input: String): Set<RecognizedWord> = words[input] ?: emptySet()
}