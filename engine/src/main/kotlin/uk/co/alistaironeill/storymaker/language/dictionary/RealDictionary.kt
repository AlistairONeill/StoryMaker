package uk.co.alistaironeill.storymaker.language.dictionary

import uk.co.alistaironeill.storymaker.language.LocationName

class RealDictionary(
    locations: Set<LocationName>
) : Dictionary {
    private val locations = locations.associateBy { it.value.lowercase() }

    override fun lookUp(input: String) =
        input.lowercase()
            .let { lower ->
                setOfNotNull(
                    locations[lower]
                )
            }
}