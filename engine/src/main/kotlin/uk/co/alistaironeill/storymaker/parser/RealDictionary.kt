package uk.co.alistaironeill.storymaker.parser

import com.ubertob.kondor.outcome.Outcome
import com.ubertob.kondor.outcome.asFailure
import com.ubertob.kondor.outcome.asSuccess
import uk.co.alistaironeill.storymaker.language.Keyword
import uk.co.alistaironeill.storymaker.language.LocationName
import uk.co.alistaironeill.storymaker.language.RecognizedWord
import uk.co.alistaironeill.storymaker.parser.Dictionary.LookUpError
import uk.co.alistaironeill.storymaker.parser.Dictionary.LookUpError.UnknownWord

class RealDictionary(
    locations: Set<LocationName> = emptySet()
) : Dictionary {

    private val keywords = Keyword.values().associateBy { it.name.lowercase() }
    private val locations = locations.associateBy { it.value.lowercase() }

    override fun lookUp(input: String): Outcome<LookUpError, RecognizedWord> =
        input.lowercase()
            .let { lower ->
                keywords[lower]?.asSuccess()
                    ?: locations[lower]?.asSuccess()
                    ?: UnknownWord.asFailure()
            }
}