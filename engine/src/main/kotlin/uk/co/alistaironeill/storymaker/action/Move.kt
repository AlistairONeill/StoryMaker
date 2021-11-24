package uk.co.alistaironeill.storymaker.action

import uk.co.alistaironeill.storymaker.language.LocationName

data class Move(val destination: LocationName): Action {
    override fun desc() = "move to ${destination.value}"
}