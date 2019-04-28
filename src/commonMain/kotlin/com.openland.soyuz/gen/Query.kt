package com.openland.soyuz.gen

import com.openland.soyuz.store.RecordSet
import kotlinx.serialization.json.JsonObject

enum class OperationKind {
    QUERY,
    MUTATION,
    SUBSCRIPTION
}


interface OperationDefinition {
    val kind: OperationKind
    val body: String
    fun normalizeResponse(response: JsonObject): RecordSet
}