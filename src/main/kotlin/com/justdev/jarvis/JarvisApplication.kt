package com.justdev.jarvis

import com.justdev.jarvis.brain.JarvisBrainInterface
import com.justdev.jarvis.ears.ConsoleInteraction
import com.justdev.jarvis.ears.Sphinx
import com.justdev.jarvis.voice.JarvisVoiceAggregator
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class JarvisApplication : CommandLineRunner {
    @Autowired
    private lateinit var brain: JarvisBrainInterface

    override fun run(vararg args: String?) {
        brain.boot()
    }
}

fun main(args: Array<String>) {
    SpringApplication(JarvisApplication::class.java).apply { setBanner(WelcomeBanner()) }.run(*args)
}
