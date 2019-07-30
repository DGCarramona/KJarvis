package com.justdev.jarvis.brain.command

import java.util.regex.Pattern

interface JarvisCommandInterface {
    fun getKeywordPattern(): Pattern
    fun execute(text: String)
}