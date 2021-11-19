package uk.co.alistaironeill.storymaker.language

import com.ubertob.kondor.outcome.Outcome
import com.ubertob.kondor.outcome.asFailure
import com.ubertob.kondor.outcome.asSuccess
import uk.co.alistaironeill.storymaker.language.Dictionary.LookUpError
import uk.co.alistaironeill.storymaker.language.Dictionary.LookUpError.UnknownWord

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