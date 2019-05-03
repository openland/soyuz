package com.openland.soyuz

import com.openland.soyuz.gen.OperationDefinition
import com.openland.soyuz.gen.OperationKind
import com.openland.soyuz.store.RecordSet
import com.openland.soyuz.store.RecordStore
import com.openland.soyuz.store.RecordStoreBus
import com.openland.soyuz.store.readFromStore
import com.openland.soyuz.transport.TransportOperationCallback
import com.openland.soyuz.transport.WebSocketTransport
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive

interface OperationCallback {
    fun onResult(result: JsonObject)
    fun onError(result: JsonObject)
}

class SoyuzClient(url: String) {
    private val transport: WebSocketTransport = WebSocketTransport(url)
    private val store = RecordStore()
    private val bus = RecordStoreBus()
    private val transportQueue = DispatchQueue()
    private val cacheQueue = DispatchQueue()

    fun query(operation: OperationDefinition, arguments: JsonObject, callback: OperationCallback) {
        if (operation.kind != OperationKind.QUERY) {
            throw Error("Invalid operation kind")
        }
        var completed = false
        cacheQueue.async {
            val existing = readFromStore("ROOT_QUERY", store, operation.selector!!)
            if (existing.first) {
                callback.onResult(existing.second!!)
            } else {
                transportQueue.async {
                    transport.operation(JsonObject(
                        mapOf(
                            "query" to JsonPrimitive(
                                operation.body
                            ),
                            "name" to JsonPrimitive(
                                operation.name
                            ),
                            "variables" to arguments
                        )
                    ), object : TransportOperationCallback {
                        override fun onError(error: JsonObject) {
                            cacheQueue.async {
                                if (!completed) {
                                    completed = true
                                    callback.onError(error)
                                }
                            }
                        }

                        override fun onResult(data: JsonObject) {
                            cacheQueue.async {
                                if (!completed) {
                                    completed = true
                                    val normalized = operation.normalizeResponse(data)
                                    val changed = store.merge(normalized)
                                    bus.publish(changed)
                                    callback.onResult(data)
                                }
                            }
                        }

                        override fun onCompleted() {
                            // Nothing to do
                        }
                    })
                }
            }
        }
    }

    fun mutation(operation: OperationDefinition, arguments: JsonObject, callback: OperationCallback) {
        if (operation.kind != OperationKind.MUTATION) {
            throw Error("Invalid operation kind")
        }
        var completed = false
        transportQueue.async {
            transport.operation(JsonObject(
                mapOf(
                    "query" to JsonPrimitive(
                        operation.body
                    ),
                    "name" to JsonPrimitive(
                        operation.name
                    ),
                    "variables" to arguments
                )
            ), object : TransportOperationCallback {
                override fun onError(error: JsonObject) {
                    cacheQueue.async {
                        if (!completed) {
                            completed = true
                            callback.onError(error)
                        }
                    }
                }

                override fun onResult(data: JsonObject) {
                    cacheQueue.async {
                        if (!completed) {
                            completed = true
                            val normalized = operation.normalizeResponse(data)
                            val changed = store.merge(normalized)
                            bus.publish(changed)
                            callback.onResult(data)
                        }
                    }
                }

                override fun onCompleted() {
                    // Nothing to do
                }
            })
        }
    }
}