package com.openland.soyuz

import okhttp3.*
import okhttp3.WebSocket

actual class WebSocket {
    private val client = OkHttpClient()
    private val url: String
    private var started = false
    private var closed = false
    private var onMessageHandler: ((message: String) -> Unit)? = null
    private var onConnectedHandler: (() -> Unit)? = null
    private var onDisconnectedHandler: (() -> Unit)? = null
    private var connection: WebSocket? = null

    private var callback = object : WebSocketListener() {
        override fun onOpen(webSocket: WebSocket, response: Response?) {
            if (!this@WebSocket.closed) {
                this@WebSocket.onConnectedHandler?.invoke()
            }
        }

        override fun onMessage(webSocket: WebSocket, text: String) {
            if (!this@WebSocket.closed) {
                this@WebSocket.onMessageHandler?.invoke(text)
            }
        }

        override fun onFailure(webSocket: WebSocket, t: Throwable?, response: Response?) {
            terminate()
        }

        override fun onClosed(webSocket: WebSocket, code: Int, reason: String?) {
            terminate()
        }

        override fun onClosing(webSocket: WebSocket?, code: Int, reason: String?) {
            terminate()
        }
    }

    actual constructor(url: String) {
        this.url = url
    }

    actual fun connect() {
        if (!this.started) {
            val request = Request.Builder().url(url)
                .addHeader("Sec-WebSocket-Protocol", "graphql-ws")
                .addHeader("Cookie", "")
                .build()
            this.connection = client.newWebSocket(request, callback)
        } else {
            throw Error("Already connected!")
        }
    }

    actual fun postMessage(message: String) {
        this.connection!!.send(message)
    }

    actual fun onMessage(handler: (message: String) -> Unit) {
        this.onMessageHandler = handler
    }

    actual fun onConnected(handler: () -> Unit) {
        this.onConnectedHandler = handler
    }

    actual fun onDisconnected(handler: () -> Unit) {
        this.onDisconnectedHandler = handler
    }

    actual fun close() {
        if (this.closed) {
            return
        }
        this.closed = true
        this.connection?.close(0, "")
    }

    private fun terminate() {
        if (this.closed) {
            return
        }
        this.closed = true
        this.connection?.close(0, "")
        this.onDisconnectedHandler?.invoke()
    }
}