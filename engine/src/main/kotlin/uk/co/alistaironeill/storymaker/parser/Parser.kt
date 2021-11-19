package uk.co.alistaironeill.storymaker.parser

import uk.co.alistaironeill.storymaker.action.Action

interface Parser {
    fun parse(input: String): Set<Action>
}