package com.justdev.jarvis.brain.command

import com.justdev.jarvis.ears.Sphinx
import com.justdev.jarvis.voice.JarvisVoiceAggregator
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.regex.Pattern

@Component
class ListenCommand: JarvisCommandInterface {
    @Autowired
    private lateinit var sphinx: Sphinx
    @Autowired
    private lateinit var voice: JarvisVoiceAggregator

    override fun getKeywordPattern(): Pattern {
        return Pattern.compile("^listen$")
    }

    override fun execute(text: String) {
        sphinx.unmute()
        voice.speak("Je suis prêt à vous écouter monsieur")
    }
}