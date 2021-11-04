package uk.co.alistaironeill.storymaker.parser

import com.ubertob.kondor.outcome.Outcome
import com.ubertob.kondor.outcome.OutcomeError
import uk.co.alistaironeill.storymaker.language.RecognizedWord

interface Dictionary {
    fun lookUp(input: String): Outcome<LookUpError, RecognizedWord>

    sealed interface LookUpError: OutcomeError {
        object UnknownWord: LookUpError {
            override val msg = "Failed to find word"
        }
    }
}