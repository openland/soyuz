package com.openland.soyuz

actual class WebSocket {
    actual constructor(url: String) {
        TODO("not implemented")
    }

    actual fun connect() {
    }

    actual fun postMessage(message: String) {
    }

    actual fun onMessage(handler: (message: String) -> Unit) {
    }

    actual fun onConnected(handler: () -> Unit) {
    }

    actual fun onDisconnected(handler: () -> Unit) {
    }

    actual fun close() {
    }

}