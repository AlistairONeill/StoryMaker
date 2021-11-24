package uk.co.alistaironeill.storymaker.controller

import com.ubertob.kondor.outcome.Outcome
import uk.co.alistaironeill.storymaker.error.PerformError


interface GameController {
    fun perform(input: String): Outcome<PerformError, String>
}