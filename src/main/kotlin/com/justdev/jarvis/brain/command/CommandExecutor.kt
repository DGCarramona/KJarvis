package com.justdev.jarvis.brain.command

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class CommandExecutor {
    @Autowired
    private lateinit var commands: Array<JarvisCommandInterface>

    fun execute(text: String) {
        findCommand(text)?.execute(text)
    }

    private fun findCommand(text: String): JarvisCommandInterface? {
        return commands.find { it.getKeywordPattern().matcher(text).find() }
    }
}