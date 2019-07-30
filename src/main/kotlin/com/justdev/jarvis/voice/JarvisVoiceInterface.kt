package com.justdev.jarvis.voice

import com.justdev.jarvis.Language
import com.justdev.jarvis.brain.JarvisComponentInterface

interface JarvisVoiceInterface: JarvisComponentInterface {
    fun speak(text: String, lang: Language = Language.FRENCH)
}