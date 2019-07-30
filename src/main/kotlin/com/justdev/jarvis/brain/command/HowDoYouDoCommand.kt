package com.justdev.jarvis.brain.command

import com.justdev.jarvis.Language
import com.justdev.jarvis.voice.JarvisVoiceAggregator
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.regex.Pattern

@Component
class HowDoYouDoCommand : JarvisCommandInterface {
    @Autowired
    private lateinit var voice : JarvisVoiceAggregator

    override fun getKeywordPattern(): Pattern {
        return Pattern.compile(".*comment vas tu.*", Pattern.CASE_INSENSITIVE)
    }

    override fun execute(text: String) {
        voice.speak("Tout va pour le mieux monsieur", Language.FRENCH)
    }
}