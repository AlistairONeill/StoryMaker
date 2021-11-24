package uk.co.alistaironeill.storymaker.consequence

data class CompositeConsequence(
    val consequences: List<Consequence>
): Consequence {
    constructor(vararg consequences: Consequence): this(consequences.toList())
}