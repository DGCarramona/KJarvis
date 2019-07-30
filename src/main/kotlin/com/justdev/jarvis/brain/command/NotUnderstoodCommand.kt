package com.justdev.jarvis.brain.command

import com.justdev.jarvis.voice.JarvisVoiceAggregator
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.regex.Pattern

@Component
class NotUnderstoodCommand: JarvisCommandInterface {
    @Autowired
    private lateinit var voice: JarvisVoiceAggregator

    override fun getKeywordPattern(): Pattern {
        return Pattern.compile(".*")
    }

    override fun execute(text: String) {
        voice.speak("Je suis désolé monsieur, je n'ai pas compris votre demande.")
    }
}