package com.openland.soyuz

expect class WebSocket {

    constructor(url: String)

    fun connect()
    fun postMessage(message: String)
    fun onMessage(handler: (message: String) -> Unit)
    fun onConnected(handler: () -> Unit)
    fun onDisconnected(handler: () -> Unit)
    fun close()
}