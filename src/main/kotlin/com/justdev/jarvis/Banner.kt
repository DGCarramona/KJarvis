package com.justdev.jarvis

import com.justdev.jarvis.console.ConsoleColorize.Color
import com.justdev.jarvis.console.ConsoleColorize
import org.springframework.boot.Banner
import org.springframework.core.env.Environment
import java.io.PrintStream

class WelcomeBanner : Banner {
    override fun printBanner(environment: Environment?, sourceClass: Class<*>?, out: PrintStream?) {
        out?.println(ConsoleColorize().apply(Color.GREEN, "Welcome to........JARVIS !"))
    }
}