package com.openland.soyuz.transport

import com.openland.soyuz.DispatchQueue
import com.openland.soyuz.WebSocket
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive

class PendingOperation(val id: String, val query: JsonObject, val callback: TransportOperationCallback)

class WebSocketTransport {
    private val url: String
    private var ws: WebSocket? = null
    private var queue = DispatchQueue()
    private var nextId = 1
    private var connected = false
    private var liveOperations = mutableMapOf<String, PendingOperation>()

    constructor(url: String) {
        this.url = url
        this.connect()
    }

    private fun connect() {
        queue.async {
            val ws = WebSocket(url)
            ws.onConnected {
                queue.async {
                    if (this.ws == ws) {
                        this.onConnected()
                    }
                }
            }
            ws.onDisconnected {
                queue.async {
                    if (this.ws == ws) {
                        this.onDisconnected()
                    }
                }
            }
            ws.onMessage {
                queue.async {
                    if (this.ws == ws) {
                        this.handleMessage(it)
                    }
                }
            }
            this.ws = ws
            ws.connect()
        }
    }

    private fun reconnect() {
        queue.async {
            this.connected = false
            if (this.ws != null) {
                this.ws!!.close()
                this.ws = null
            }
        }
    }

    //
    // External Methods
    //

    fun operation(operation: JsonObject, callback: TransportOperationCallback) {
        queue.async {
            val id = nextId++.toString()
            val op = PendingOperation(id, operation, callback)
            liveOperations[id] = op
            if (this.connected) {
                postMessage("start", operation, id)
            }
        }
    }

    //
    // All methods are called in Dispatch Queue
    //

    private fun onConnected() {
        postMessage("connection_init", JsonObject(mapOf()), nextId++.toString())
    }

    private fun onDisconnected() {
        reconnect()
    }

    private fun handleMessage(msg: String) {
        val response = Json.parse(JsonObject.serializer(), msg)
        val type = response["type"]!!.primitive.content
        if (type == "ka") {
            // Keep Alive
            // TODO: Handle
        } else if (type == "connection_ack") {
            this.connected = true
            for (p in this.liveOperations) {
                postMessage("start", p.value.query, p.value.id)
            }
        } else if (type == "data") {

            // Handle data and resolver level errors
            val id = response["id"]!!.primitive.content
            val payload = response["payload"]!!.jsonObject
            val errors = payload["error"]
            val data = payload["data"]
            if (this.liveOperations.containsKey(id)) {
                if (errors != null) {
                    this.liveOperations[id]!!.callback.onError(errors.jsonObject)
                } else {
                    this.liveOperations[id]!!.callback.onResult(data!!.jsonObject)
                }
            }
        } else if (type == "error") {

            // Handle low level error
            val id = response["id"]!!.primitive.content
            val payload = response["payload"]!!.jsonObject
            if (this.liveOperations.containsKey(id)) {
                this.liveOperations[id]!!.callback.onError(payload)
            }

        } else if (type == "complete") {
            val id = response["id"]!!.primitive.content
            if (this.liveOperations.containsKey(id)) {
                this.liveOperations.remove(id)!!.callback.onCompleted()
            }
        } else {
            // Ignore unknown message
        }
    }

    private fun postMessage(type: String, payload: JsonObject, id: String) {
        this.ws!!.postMessage(
            Json.stringify(
                JsonObject.serializer(), JsonObject(
                    mapOf(
                        "id" to JsonPrimitive(id),
                        "type" to JsonPrimitive(type),
                        "payload" to payload
                    )
                )
            )
        )
    }
}