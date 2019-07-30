package com.justdev.jarvis.brain

import com.justdev.jarvis.Language
import com.justdev.jarvis.brain.command.CommandExecutor
import com.justdev.jarvis.ears.JarvisEarsAggregator
import com.justdev.jarvis.voice.JarvisVoiceAggregator
import kotlinx.coroutines.runBlocking
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.logging.Logger

@Component
class Brain: JarvisBrainInterface {
    @Autowired
    private lateinit var ears: JarvisEarsAggregator
    @Autowired
    private lateinit var voice: JarvisVoiceAggregator
    @Autowired
    private lateinit var commandExecutor: CommandExecutor
    private val logger = Logger.getLogger(this.javaClass.name)

    override fun boot() {
        voice.boot()
        voice.speak("Bonjour Monsieur, je m'appelle Jarvisse. Que puis je faire pour vous ?", Language.FRENCH)
        ears.boot()
        start()
    }

    fun start() {
        ears.startListening(this)
    }

    fun stop() {
        ears.stopListening()
    }

    override fun shutdown() {
        ears.shutdown()
        voice.shutdown()
    }

    override fun onListened(text: String) {
        logger.info("Brain: Received $text")
        ears.stopListening()
        logger.info("Brain: Stopping listening")
        commandExecutor.execute(text)
        logger.info("Brain: Executed found command")
        ears.startListening(this)
        logger.info("Brain: Started listening")
    }
}