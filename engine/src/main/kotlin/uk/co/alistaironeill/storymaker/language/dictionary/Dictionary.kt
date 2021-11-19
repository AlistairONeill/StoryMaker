package uk.co.alistaironeill.storymaker.language.dictionary

import uk.co.alistaironeill.storymaker.language.RecognizedWord

interface Dictionary {
    fun lookUp(input: String): Set<RecognizedWord>
}