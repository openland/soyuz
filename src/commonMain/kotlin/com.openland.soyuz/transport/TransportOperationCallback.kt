package com.openland.soyuz.transport

import kotlinx.serialization.json.JsonObject

interface TransportOperationCallback {
    fun onError(error: JsonObject)
    fun onResult(data: JsonObject)
    fun onCompleted()
}