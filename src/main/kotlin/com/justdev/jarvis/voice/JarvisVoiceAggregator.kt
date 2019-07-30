package com.justdev.jarvis.voice

import com.justdev.jarvis.Language
import com.justdev.jarvis.brain.JarvisComponentInterface
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class JarvisVoiceAggregator: JarvisComponentInterface {
    @Autowired
    private lateinit var voices: Array<JarvisVoiceInterface>

    override fun boot() {
        super.boot()
        voices.forEach { it.boot() }
    }

    fun speak (text: String, lang: Language = Language.FRENCH) {
        voices.forEach { it.speak(text, lang) }
    }

    override fun shutdown() {
        super.shutdown()
        voices.forEach { it.shutdown() }
    }
}