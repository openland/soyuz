package com.openland.soyuz

import com.openland.soyuz.resolver.*
import kotlinx.serialization.json.JsonLiteral
import kotlinx.serialization.json.JsonObject
import kotlin.test.Test

class NormalizerTests {
    @Test
    fun testNormalizer() {
        val selection = Selection(
            listOf(
                SelectionField(
                    "query1", "ROOT_QUERY.query1", listOf(
                        SelectionField("id", "id", emptyList()),
                        SelectionField("firstName", "firstName", emptyList()),
                        SelectionField("lastName", "lastName", emptyList()),
                        SelectionField("fullName", "name", emptyList()),
                        SelectionField(
                            "likes", "likes", listOf(
                                SelectionField("key", "key", emptyList())
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