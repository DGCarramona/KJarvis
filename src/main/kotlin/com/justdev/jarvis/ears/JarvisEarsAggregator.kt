package com.justdev.jarvis.ears

import com.justdev.jarvis.brain.JarvisComponentInterface
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class JarvisEarsAggregator: JarvisComponentInterface {
    @Autowired
    private lateinit var ears: Array<JarvisEarsInterface>

    fun startListening(receiver: JarvisEarsReceiverInterface) {
        ears.forEach { it.startListening(receiver) }
    }

    fun stopListening() {
        ears.forEach { it.stopListening() }
    }

    override fun boot() {
        ears.forEach { it.boot() }
    }

    override fun shutdown() {
        ears.forEach { it.shutdown() }
    }
}