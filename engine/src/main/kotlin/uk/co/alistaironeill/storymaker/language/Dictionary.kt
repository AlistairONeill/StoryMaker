package uk.co.alistaironeill.storymaker.language

import com.ubertob.kondor.outcome.Outcome
import com.ubertob.kondor.outcome.OutcomeError

interface Dictionary {
    fun lookUp(input: String): Outcome<LookUpError, RecognizedWord>

    sealed interface LookUpError: OutcomeError {
        object UnknownWord: LookUpError {
            override val msg = "Failed to find word"
        }
    }
}