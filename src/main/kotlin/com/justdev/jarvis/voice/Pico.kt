package com.justdev.jarvis.voice

import com.justdev.jarvis.Language
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.io.File
import java.util.logging.Logger
import javax.sound.sampled.*

@Component
class Pico: JarvisVoiceInterface {
    private val logger = Logger.getLogger(this.javaClass.name)

    override fun speak(text: String, lang: Language) {
        generateSpeach(text, lang).let {
            play(it)
            it.delete()
        }
    }

    private fun generateSpeach(text: String,lang: Language = Language.FRENCH): File = File.createTempFile("picotts", ".wav")
        .also {
            logger.info("Created tmp file ${it.absolutePath}")
            ProcessBuilder("pico2wave", "--lang=${lang.value}", "--wave=${it.absolutePath}", text)
                    .also { processBuilder -> logger.info("Executing ${processBuilder.command().joinToString(" ")}") }
                    .start()
                    .waitFor()
        }

    private fun play(file: File) {
        AudioSystem.getAudioInputStream(file).let { input ->
            (AudioSystem.getLine(DataLine.Info(SourceDataLine::class.java, input.format)) as SourceDataLine).also { output ->
                output.open(input.format)
                output.start()

                var nBytesRead = 0;
                val abData = ByteArray(524288)
                while (nBytesRead != -1) {
                    nBytesRead = input.read(abData, 0, abData.size)
                    if (nBytesRead >= 0) {
                        output.write(abData, 0, nBytesRead)
                    }
                }

                output.drain()
                output.close()
            }
        }
    }
}