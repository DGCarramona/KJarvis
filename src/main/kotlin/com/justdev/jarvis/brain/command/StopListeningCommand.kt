package com.justdev.jarvis.brain.command

import com.justdev.jarvis.ears.Sphinx
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.regex.Pattern

@Component
class StopListeningCommand: JarvisCommandInterface {
    @Autowired
    private lateinit var sphinx: Sphinx

    override fun getKeywordPattern(): Pattern {
        return Pattern.compile("stop listening")
    }

    override fun execute(text: String) {
        sphinx.mute()
    }
}