package uk.co.alistaironeill.storymaker.controller

import com.ubertob.kondor.outcome.Outcome
import com.ubertob.kondor.outcome.OutcomeError


interface GameController {
    fun perform(input: String): Outcome<PerformError, String>

    sealed interface PerformError: OutcomeError
}