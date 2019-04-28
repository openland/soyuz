package com.openland.soyuz

import com.openland.soyuz.gen.OperationDefinition
import com.openland.soyuz.gen.OperationKind
import com.openland.soyuz.store.RecordSet
import com.openland.soyuz.transport.TransportOperationCallback
import com.openland.soyuz.transport.WebSocketTransport
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive

interface OperationCallback {
    fun onResult(result: RecordSet)
    fun onError(result: JsonObject)
}

class SoyuzClient(url: String) {
    private val transport: WebSocketTransport = WebSocketTransport(url)
    private val dispatchQueue = DispatchQueue()

    fun query(definition: OperationDefinition, callback: OperationCallback) {
        if (definition.kind != OperationKind.QUERY) {
            throw Error("Invalid operation kind")
        }
        var completed = false
        transport.operation(JsonObject(
            mapOf(
                "query" to JsonPrimitive(
                    definition.body
                )
            )
        ), object : TransportOperationCallback {
            override fun onError(error: JsonObject) {
                dispatchQueue.async {
                    if (!completed) {
                        completed = true
                        callback.onError(error)
                    }
                }
            }

            override fun onResult(data: JsonObject) {
                dispatchQueue.async {
                    if (!completed) {
                        completed = true
                        callback.onResult(definition.normalizeResponse(data))
                    }
                }
            }

            override fun onCompleted() {
                // Nothing to do
            }
        })
    }
}