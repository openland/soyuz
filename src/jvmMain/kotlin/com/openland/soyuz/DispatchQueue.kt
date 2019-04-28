package com.openland.soyuz

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext

actual class DispatchQueue {
    private val channel = Channel<suspend () -> Unit>()

    init {
        GlobalScope.launch(newSingleThreadContext("DispatchQueue")) {
            var res = channel.receive()
            while (true) {
                res()
                res = channel.receive()
            }
        }
    }

    actual fun async(handler: suspend () -> Unit) {
        GlobalScope.launch {
            channel.send(handler)
        }
    }
}