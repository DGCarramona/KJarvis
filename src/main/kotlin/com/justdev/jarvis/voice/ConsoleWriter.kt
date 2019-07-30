package com.justdev.jarvis.voice

import com.justdev.jarvis.Language
import org.springframework.stereotype.Component

@Component
class ConsoleWriter: JarvisVoiceInterface {
    override fun speak(text: String, lang: Language) {
        println(text)
    }
}