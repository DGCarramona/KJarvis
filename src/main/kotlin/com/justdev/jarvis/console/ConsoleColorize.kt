package com.justdev.jarvis.console

import org.springframework.stereotype.Component

@Component
class ConsoleColorize {
    fun apply (color: Color, string: String): String {
        return "${color.definition}$string${Color.RESET.definition}"
    }

    enum class Color (val definition: String) {
        RESET("\u001B[0m"),
        BLACK("\u001B[30m"),
        RED("\u001B[31m"),
        GREEN("\u001B[32m"),
        YELLOW("\u001B[33m"),
        BLUE("\u001B[34m"),
        PURPLE("\u001B[35m"),
        CYAN("\u001B[36m"),
        WHITE("\u001B[37m")
    }
}