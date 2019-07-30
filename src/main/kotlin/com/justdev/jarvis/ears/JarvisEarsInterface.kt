package com.justdev.jarvis.ears

import com.justdev.jarvis.brain.JarvisVocalComponentInterface

interface JarvisEarsInterface: JarvisVocalComponentInterface {
    fun startListening(receiver: JarvisEarsReceiverInterface)
    fun stopListening()
}