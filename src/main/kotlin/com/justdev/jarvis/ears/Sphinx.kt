package com.justdev.jarvis.ears

import edu.cmu.sphinx.api.Configuration
import edu.cmu.sphinx.api.LiveSpeechRecognizer
import org.springframework.stereotype.Component
import java.util.logging.Logger

@Component
class Sphinx: JarvisEarsInterface {
    private var mute: Boolean = false
    private var run: Boolean = false
    private var receiver: JarvisEarsReceiverInterface? = null
    private val logger = Logger.getLogger(this.javaClass.name)

    override fun startListening(receiver: JarvisEarsReceiverInterface) {
        logger.info("Voice Recognition: Start listening")
        this.receiver = receiver
    }

    override fun stopListening() {
        logger.info("Voice Recognition: Stop listening")
        this.receiver = null
    }

    override fun boot() {
        run = true
        Thread {
            Runnable {
                val recognizer = LiveSpeechRecognizer(Configuration().apply {
                    acousticModelPath = "resource:/sphinx/fr/model"
                    dictionaryPath = "resource:/sphinx/fr/fr.dict"
                    languageModelPath = "resource:/sphinx/fr/fr.lm.dmp"
                })

                recognizer.startRecognition(true)
                logger.info("Started voice recognition")
                var result = recognizer.result
                while (run && result != null) {
                    result.takeIf { !mute }?.let {
                        logger.info("Vocal recognition listened: ${it.hypothesis}")
                        receiver?.onListened(it.hypothesis)
                    }
                    result = recognizer.result
                }

                if (!run) {
                    recognizer.stopRecognition()
                }
            }.run()
        }.start()
        logger.info("Booted voice recognition")
    }

    override fun shutdown() {
        run = false
    }

    override fun mute() {
        mute = true
    }

    override fun unmute() {
        mute = false
    }
}