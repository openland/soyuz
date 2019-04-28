package com.openland.soyuz.resolver

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject

data class Response(val response: JsonObject)

fun parseResponse(data: String): Response {
    val parsed = Json.parse(JsonElement.serializer(), data).jsonObject["data"] as JsonObject
    return Response(parsed)
}