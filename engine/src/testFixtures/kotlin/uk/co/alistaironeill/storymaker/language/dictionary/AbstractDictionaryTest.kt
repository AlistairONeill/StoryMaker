package uk.co.alistaironeill.storymaker.language.dictionary

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import uk.co.alistaironeill.storymaker.language.RecognizedWord

abstract class AbstractDictionaryTest {
    protected abstract val dictionary: Dictionary

    protected infix fun String.parsesAs(word: RecognizedWord) =
        assertThat(
            dictionary.lookUp(this).single(),
            equalTo(word)
        )

    protected infix fun String.parsesAs(words: Set<RecognizedWord>) =
        assertThat(
            dictionary.lookUp(this),
            equalTo(words)
        )
}

