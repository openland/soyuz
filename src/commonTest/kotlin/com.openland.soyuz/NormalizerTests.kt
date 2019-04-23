package com.openland.soyuz

import com.openland.soyuz.resolver.*
import kotlinx.serialization.json.JsonLiteral
import kotlinx.serialization.json.JsonObject
import kotlin.test.Test

class NormalizerTests {
    @Test
    fun testNormalizer() {
        val selection = Selection(
            arrayOf(
                SelectionField(
                    "query1", "ROOT_QUERY.query1", arrayOf(
                        SelectionField("id", "id", emptyArray()),
                        SelectionField("firstName", "firstName", emptyArray()),
                        SelectionField("lastName", "lastName", emptyArray()),
                        SelectionField("fullName", "name", emptyArray()),
                        SelectionField(
                            "likes", "likes", arrayOf(
                                SelectionField("key", "key", emptyArray())
                            )
                        )
                    )
                )
            )
        )
        val normalized = normalizeResponse(
            Response(
                JsonObject(
                    mapOf(
                        "query1" to JsonObject(
                            mapOf(
                                "id" to JsonLiteral("user1"),
                                "firstName" to JsonLiteral("Jane"),
                                "lastName" to JsonLiteral("Kite"),
                                "fullName" to JsonLiteral("Jane Kite"),
                                "likes" to JsonObject(mapOf(
                                    "key" to JsonLiteral("like1")
                                ))
                            )
                        )
                    )
                )
            ),
            selection
        )
    }
}