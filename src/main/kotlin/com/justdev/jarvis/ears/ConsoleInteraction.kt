package com.justdev.jarvis.ears

import org.beryx.textio.TextIoFactory
import org.springframework.stereotype.Component
import java.util.logging.Logger

@Component
class ConsoleInteraction: JarvisEarsInterface {
    private val logger = Logger.getLogger(this.javaClass.name)
    private val textIo = TextIoFactory.getTextIO().newStringInputReader()
    private var receiver: JarvisEarsReceiverInterface? = null
    private var listen: Boolean = false

    override fun startListening(receiver: JarvisEarsReceiverInterface) {
        this.receiver = receiver
    }

    override fun stopListening() {
        this.receiver = null
    }

    override fun boot() {
        listen = true
        Thread {
            Runnable {
                logger.info("Started console interaction")
                var text = textIo.read("jarvis>")
                while (listen) {
                    logger.info("Console interaction received: $text")
                    this.receiver?.onListened(text)
                    text = textIo.read("jarvis>")
                }
            }.run()
        }.start()
        logger.info("Booted console interaction")
    }

    override fun shutdown() {
        listen = false
    }
}