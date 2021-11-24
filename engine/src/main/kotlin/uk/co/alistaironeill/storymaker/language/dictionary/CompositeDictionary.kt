package uk.co.alistaironeill.storymaker.language.dictionary

import com.ubertob.kondor.outcome.Outcome
import com.ubertob.kondor.outcome.extractList
import uk.co.alistaironeill.storymaker.error.PerformError.CatastrophicError
import uk.co.alistaironeill.storymaker.language.RecognizedWord

class CompositeDictionary(
    private val dictionaries: Collection<Dictionary>
) : Dictionary {
    constructor(vararg dictionaries: Dictionary) : this(dictionaries.toList())

    companion object {
        operator fun invoke(
            vararg dictionaries: Outcome<CatastrophicError, Dictionary>
        ): Outcome<CatastrophicError, Dictionary> =
            dictionaries.toList()
                .extractList()
                .transform(::CompositeDictionary)

        operator fun invoke(
            dictionary1: Dictionary,
            dictionary2: Outcome<CatastrophicError, Dictionary>
        ): Outcome<CatastrophicError, Dictionary> =
            dictionary2.transform {
                CompositeDictionary(dictionary1, it)
            }
    }

    override fun lookUp(input: String): Set<RecognizedWord> =
        dictionaries.flatMap { it.lookUp(input) }.toSet()
}